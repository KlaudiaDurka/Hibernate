package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private LocalDate releaseDate;
    @OneToOne
    private Badge badge;
    @ManyToOne
    private Author author;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Actor> actors;

    public Movie(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public void getReleaseDate(LocalDate now) {
    }
}
