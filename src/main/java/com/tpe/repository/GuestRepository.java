package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GuestRepository {

    private Session session;

    //9-c
    public void save(Guest guest) {

        try {
            session= HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.saveOrUpdate(guest);//id;1 ismi;Ali,.... bu aydiye sahip olan bir guest var mı der yok böylece save eder.
            //kayıtlı ise değiştirir.böylece ayrıca bir update yazmadan hem save hemupdate işlemi yapar.
            //parametrede verdiğiniz entitiyi kontrol eder.
            //senaryoya uygunsa kod tekrarını önlemiş olur.
            //varsa update yap yoksa yeniden oluştur denir.
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }



    }

    //Ödev:2-c tablo aşaması yani
    public Guest findById(Long guestId) {

        try {
            session= HibernateUtils.getSessionFactory().openSession();
            return session.get(Guest.class,guestId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;

    }
}
