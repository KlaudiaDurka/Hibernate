package org.example.dao;

import org.example.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ActorDao extends EntityDao<Actor> {
    public ActorDao(SessionFactory sessionFactory) {
        super(sessionFactory, Actor.class);
    }

    public List<Actor> getByName(String name){
        Session session = sessionFactory.openSession();
        List<Actor> resultList;
        //opcja 1
        Query<Actor> query = session.createQuery("From Actor a WHERE a.name = :nameParameter",
                Actor.class);
        Query<Actor> nameParameter = query.setParameter("nameParameter", name);
        resultList = nameParameter.getResultList();

        //opcja 2
        resultList = session.createQuery("From Actor a WHERE a.name = :nameParameter", Actor.class)
                .setParameter("nameParameter", name).getResultList();

//        for(Actor actor : resultList){
//            Hibernate.initialize(actor.getMovies());
//        }


        session.close();
        return resultList;
    }

    public void updateAllNames(String nameFrom, String targetName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("Update Actor set name = :targetName WHERE name = :nameFrom")
                .setParameter("targetName", targetName)
                .setParameter("nameFrom", nameFrom)
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
