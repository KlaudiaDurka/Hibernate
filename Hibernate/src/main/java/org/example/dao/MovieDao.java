package org.example.dao;

import org.example.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDao extends EntityDao<Movie> {

    public MovieDao(SessionFactory sessionFactory){
        super(sessionFactory, Movie.class);
    }
}