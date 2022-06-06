package org.example.dao;

import org.example.model.Badge;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BadgeDao extends EntityDao<Badge> {
    public BadgeDao(SessionFactory sessionFactory) {
        super(sessionFactory, Badge.class);
    }

    public List<Badge> getAllBadges(){
        Session session = sessionFactory.openSession();
        List<Badge> results = session.createQuery("From Badge", Badge.class).getResultList();

        session.close();
        return results;
    }
}
