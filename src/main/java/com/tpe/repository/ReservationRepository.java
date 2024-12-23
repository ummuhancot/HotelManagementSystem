package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReservationRepository {

    private Session session;




    public void save(Reservation reservation) {
        try {
            session= HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.save(reservation);


            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }


    }
}
