package com.tw;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class EventManager {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        if (args[0].equals("store")) {
            manager.createAndStoreEvent("My Event", new Date());
        } else if (args[0].equals("list")) {
            List<EventEntity> events = manager.listEvents();
            for (EventEntity event : events) {
                System.out.println("event: " + event);
            }
        } else if (args[0].equals("query")) {
            EventEntity event = manager.search(2);
            System.out.println("event: " + event);
        } else if (args[0].equals("delete")) {
            manager.delete();
            System.out.println("delete successfully");
        }
        HibernateUtil.close();
    }

    private void createAndStoreEvent(String title, Date date) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        EventEntity event = new EventEntity();
        event.setTitle(title);
        event.setEventDate(date);
        session.save(event);
        tx.commit();
        HibernateUtil.closeSession();
    }

    private List<EventEntity> listEvents() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List result = session.createQuery("FROM EventEntity").list();
        tx.commit();
        HibernateUtil.closeSession();
        return result;
    }

    private EventEntity search(int id) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        EventEntity entity = session.get(EventEntity.class, id);
        tx.commit();
        HibernateUtil.closeSession();
        return entity;
    }

    private void delete() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        EventEntity event = new EventEntity();
        event.setId(1);
        session.delete(event);
        tx.commit();
        HibernateUtil.closeSession();
    }
}
