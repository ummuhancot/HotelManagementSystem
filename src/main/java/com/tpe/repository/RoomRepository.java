package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoomRepository {

    private Session session;

    private Transaction transaction;
    //4-c
    public void save(Room room) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }




    }
}
