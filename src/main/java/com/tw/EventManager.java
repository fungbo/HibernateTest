package com.tw;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class EventManager {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        if (args[0].equals("store")) {
            manager.createAndStoreEvent("My Event", new Date());
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
}
