package org.example.dao.old;

import lombok.AllArgsConstructor;
import org.example.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

// CRUD - create read update delete
// DAO - data access object
@AllArgsConstructor
public class OldMovieDao {

    private SessionFactory sessionFactory;

    //crate
    public void save(Movie movie){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            session.close();
        } catch (HibernateException e){
            Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
            transaction.rollback();
        }

    }
    //readOne
    public Optional<Movie> getById(Long id){
        Session session = sessionFactory.openSession();
        Movie movie = session.find(Movie.class, id);
        session.close();
        return Optional.ofNullable(movie);
    }

    public Optional<Movie> dummyOptionalExample() {
        if (5 > 4) {
            Movie movie = new Movie("asd", null);
            return Optional.of(movie);
        }
        return Optional.empty();
    }
    //readAll ---
    //update
    public void update(Movie movie){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(movie);
        transaction.commit();
        session.close();
    }
    //delete
}