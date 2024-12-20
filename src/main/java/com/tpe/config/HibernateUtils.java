package com.tpe.config;
//1 tüm classlardan önce bu oluşturulur.statik yapcaz ki tüm classlardan ve packagelerden önce calışır
import com.tpe.domain.Guest;
import com.tpe.domain.Hotel;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    //uygulama ayaga kalktığında ilk bu calışır
        static {
            try {
                Configuration configuration = new Configuration().configure().
                        addAnnotatedClass(Hotel.class).
                        addAnnotatedClass(Room.class).
                        addAnnotatedClass(Reservation.class).
                        addAnnotatedClass(Guest.class);
                //burda classların ismini oluşturalım ki burası hata vermesin
                //domainde oluşturduk classları hotel,room,reservation guest oluşturduk buraya girmek icin.


                sessionFactory=configuration.buildSessionFactory();


            }catch (Exception e){
                System.out.println("Initialization of session factory is FAILED!!!");//Session factoriy başlatılması başarısız oldu!!!
            }

        }

        //getter
        public static SessionFactory getSessionFactory() {
            return sessionFactory;

        }

        //SESSİONFACTORY kapatalım
        public static void  shutDown(){
            getSessionFactory().close();
        }

    //sessionı kapatma
    public static void closeSession(Session session){
        if (session!=null && session.isOpen()){
            session.close();
        }
    }

}

