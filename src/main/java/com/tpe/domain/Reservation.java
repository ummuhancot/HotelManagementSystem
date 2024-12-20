package com.tpe.domain;
///room oluşturduktan sonra olusturduk
//8
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//otomatik olarak veri tabanı tarafından yaparız//id otomatik olarak yaptık
    //Auto de secebiliyoruz
    private Long id;

    @Column(nullable = false)
    private LocalDate checkInDate;//giriş tarihi

    @Column(nullable = false)
    private LocalDate checkOutDate;//cıkış tarihi

    @ManyToOne//bir rezervasyonu aynı anda 1 konuk oluşturabilir fakat 1 konuk 1 den fazla rezerve oluşturabilir
    //rezervasyonla gestkonuk arasında iliski var
    @JoinColumn(nullable = false)
    private Guest guest;//konuk istediği kadar rezerve edebilir


    @ManyToOne//bir oda icin birden fazla rezerve olabilir amabir rezervasyon 1 odaya ayit olabilir
    @JoinColumn(nullable = false)//ilişki burda kuruldu rezerve icin
    private Room room;//rezerve edilen kim ve hangi odadan oldunu belirlemek icin yazdık

    ///neden cont olusturmadık
    //id oto old icin gast room ise ayrıca set etmek istiyorum bazı işlemler old ici iki fild icin cons oluşturmadık
    //ihtiyaca göre oluşturmamız lazım ihtiyac duyduğumuzda gelip oluşturabiliriz


    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;//ihtiyac olmasa bile yaz id oto olsada gerek duyulabilir
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    //toString
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", room=" + room +
                '}';
    }
}
