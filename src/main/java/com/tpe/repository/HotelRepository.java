package com.tpe.repository;
//5
import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepository {

    private Session session;//ihtiyac durumunda acmamız icin kayıt edicemiz zaman acıcaz

    //HotelManagementSystem ,displayHotelOperationsMenu switch de 1) icinde 1-a dedik orada
    //1-b:
    public void save(Hotel hotel){
        //eger bi hatayla karşılasırsak uygulama durmasın try actık
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            session.save(hotel);//insert into t_hotel values hb tarafında oluşturup db kayıt edilir
            t.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }


    }

    //2-c:DB den id verilen satırı getirme
    //id si verilen satırı hotel tablosundan getiroyr
    public Hotel findById(Long id) {


        try {
            session =HibernateUtils.getSessionFactory().openSession();
            //Transaction transaction = session.beginTransaction(); --->fetch işlemlerinde transaction gerek yok yani data cekme
            return session.get(Hotel.class,id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;//id si bulunamayan durum da null döndürecek yani data bulunamazsa
    }

    //3-c:Db den tablonun tüm kayıtlarını cekme
    public List<Hotel> findAll() {
        try {
            session=HibernateUtils.getSessionFactory().openSession();
            ///hql sorgusu yada sql sorgusu yazarak bulabiliriz
           return session.createQuery("FROM HOTEL",Hotel.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;

    }


    //7:c:delete methodu otel silme
    public void delete(Hotel foundHotel) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();

            //sql ve hql de yapabiliriz
            //delete from t_hotel where id=+foundHotel.getId
            session.delete(foundHotel);//objenin tamamı varsa yani id sini bulmuşsak ki bulduk önceki methodda böylece direk silebiliyoruz.


            t.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }



    }

    //8:c
    public void update(Hotel existingHotel) {
        try {

            session = HibernateUtils.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();

            session.update(existingHotel);

            t.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }
}
