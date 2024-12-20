package com.tpe.domain;
//9
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//oto arttırırvarsayılan
    private Long id;

    @Column(nullable = false)
    private String name;

    //set islemi icinde yapıcaz
    private LocalDateTime createDate;//uygulamaya kayıt old tarih

    //gest rezervasyon olusturabilir
    @OneToMany(mappedBy = "gust",orphanRemoval = true)//önce rezerveleri sil sonra gast i sil dedik orphanRemoval kullandık köklü silmek icin
    // bir kisi birden fazzla rezervasyon yapabilir
    private List<Reservation> reservations = new ArrayList<>();

    //adress icin farklı bi class actık obje olusturup ekledik
    @Embedded//opsiyonel kullanmasak da olur
    private Address address;

    //adres kalıyor cont oda parametreli cont olusturduk gerisini biz yapcaz

    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    //objeyi kayıt etmeden önce bu değeri set etmesi icin
    @PrePersist
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = LocalDateTime.now();//burda setteri düzenledik
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //toString
    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", address=" + address +
                '}';
    }
}
