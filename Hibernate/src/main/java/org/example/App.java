package org.example;

import org.example.dao.ActorDao;
import org.example.dao.AuthorDao;
import org.example.dao.BadgeDao;
import org.example.dao.MovieDao;
import org.example.dao.old.OldAuthorDao;
import org.example.dao.old.OldMovieDao;
import org.example.model.Actor;
import org.example.model.Author;
import org.example.model.Badge;
import org.example.model.Movie;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class App
{
    public static void main( String[] args ) {

        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        OldAuthorDao oldAuthorDao = new OldAuthorDao(sessionFactory);
        OldMovieDao oldMovieDao = new OldMovieDao(sessionFactory);

        saveAuthorExaple(oldAuthorDao);
        getByIdWithOptional(oldMovieDao);
        updateExample(oldMovieDao);
        deleteExample(oldAuthorDao);


        MovieDao movieDao = new MovieDao(sessionFactory);
        BadgeDao badgeDao = new BadgeDao(sessionFactory);
        AuthorDao authorDao = new AuthorDao(sessionFactory);
        ActorDao actorDao = new ActorDao(sessionFactory);

        OneToOneExample(movieDao, badgeDao);
        OneToManyExale(movieDao, authorDao);
        manyToManyExample(movieDao, actorDao);
        hqlExamples(badgeDao, actorDao);

        sessionFactory.close();
    }

    private static void hqlExamples(BadgeDao badgeDao, ActorDao actorDao) {
        System.out.println("ALL BADGES");
        badgeDao.getAllBadges().forEach(System.out::println);
        System.out.println("ALL ZENEKS");
        actorDao.getByName("Zenek").forEach(System.out::println);
        System.out.println("Update");
        actorDao.updateAllNames("Zenek", "Zenon");
        actorDao.getByName("Zenon").forEach(System.out::println);
    }

    private static void manyToManyExample(MovieDao movieDao, ActorDao actorDao) {
        Actor jurek = new Actor();
        jurek.setName("Jurek");
        jurek.setYearsOfExperience(4);

        Actor zenek = new Actor();
        zenek.setName("Zenek");
        zenek.setYearsOfExperience(2);

        Movie jurekIZenek = new Movie("Jurek i Zenek", LocalDate.now());
        Movie jurekIZenekOstateczneStarcie = new Movie("Jurek i Zenek ostateczne starcie",
                LocalDate.now());

        // set w jurku i zenku jest nadmiarowy
        jurek.setMovies(Set.of(jurekIZenek, jurekIZenekOstateczneStarcie));
        zenek.setMovies(Set.of(jurekIZenek, jurekIZenekOstateczneStarcie));

        jurekIZenek.setActors(Set.of(jurek, zenek));
        jurekIZenekOstateczneStarcie.setActors(Set.of(jurek, zenek));

        actorDao.save(jurek);
        actorDao.save(zenek);
        movieDao.save(jurekIZenek);
        movieDao.save(jurekIZenekOstateczneStarcie);
    }

    private static void OneToManyExale(MovieDao movieDao, AuthorDao authorDao) {
        Author author = new Author("Franek", "Frankowiski", "Radom");
        Movie trójkąt = new Movie("Trójkąt", LocalDate.now());
        Movie bermudzki = new Movie("Bermudzki", LocalDate.now());

        trójkąt.setAuthor(author);
        bermudzki.setAuthor(author);
        author.setMovies(Set.of(trójkąt, bermudzki));

        authorDao.save(author);
        movieDao.save(trójkąt);
        movieDao.save(bermudzki);
    }

    private static void OneToOneExample(MovieDao movieDao, BadgeDao badgeDao) {
        Movie movie = new Movie("Trzy świnki", LocalDate.of(1997, 12, 12));
        Badge badge = new Badge();
        badge.setName("SUPER ODZNAKA, SUPER FILM");
        badge.setValue(3);

        movie.setBadge(badge);
//        badge.setMovie(movie);

        badgeDao.save(badge);
        movieDao.save(movie);

        System.out.println(badge);
    }

    private static void deleteExample(OldAuthorDao authorDao) {
        Long id = authorDao.save(new Author("Leszek", "Leszkowiski", "Wejcherowo"));
        authorDao.delete(id);
        System.out.println("Author with id " + id + " was deleted");
    }

    private static void updateExample(OldMovieDao movieDao) {
        Optional<Movie> byId = movieDao.getById(1L);
        if (byId.isPresent()){
            Movie movie = byId.get();
            System.out.println("Movie before update: " + movie);
            movie.setTitle("Update Title");
            movieDao.update(movie);
//            System.out.println("Movie after update: " + movie);
        }

        Optional<Movie> updateMovie = movieDao.getById(1L);
        updateMovie.ifPresent(movie -> System.out.println("Movie after update: " + movie));
    }

    private static void getByIdWithOptional(OldMovieDao movieDao) {
        //get movie by id
        movieDao.save(new Movie("Świnka Peppa", LocalDate.now()));
        Optional<Movie> optionalMovie = movieDao.getById(1L);
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            System.out.println(movie);
        }
        // wyjątek bez sprawdzania isPresent
        String title = optionalMovie.get().getTitle();

        //or else czyli wartość default
        movieDao.getById(99L).orElse(new Movie("film nie znaleziony", LocalDate.MAX));

        optionalMovie.ifPresent(movie2 -> {
            System.out.println("Znaleziono !");
            System.out.println(movie2);
        });
    }

    private static void saveAuthorExaple(OldAuthorDao authorDao) {
        // save example 1
        Author tomek = new Author();
        tomek.setFirstName("Tomek");
        tomek.setLastName("Tomczuk");
        tomek.setAddress("Ciechocinek");
        authorDao.save(tomek);

        // save example 2
        authorDao.save(new Author("Maciej", "Maciejewski", "Gdynia"));

        // save example 3
        authorDao.save(Author.builder()
                .firstName("Robert")
                .lastName("Robertowski")
                .address("Sosnowiec")
                .build());
    }
}