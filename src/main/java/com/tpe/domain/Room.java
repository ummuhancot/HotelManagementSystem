package com.tpe.domain;
//7
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
public class Room {

    @Id
    private Long id;

    @Column(nullable = false)
    private String number;//kapı no

    @Column(nullable = false)
    private Integer capacity;//odalar kac kişilik

    @ManyToOne(fetch = FetchType.LAZY)//eager--> lazy yaptık gerek yok zorunlu değildi örnek yaptık
    @JoinColumn(nullable = false)//bu oda kesinlikle bir otele ait olmalı
    private Hotel hotel;//bu oda hangi otelin:ID ekler

    //oda rezarvasyon list:1,2,3
    //rezerve de sorunn oldu 1 id li olanı cıkardım listeden:bir odaya ait değilse tablodanda sileriz
    //eger listeden cıkarmışsam tablodanda sil dedik orphanRemoval = true
    @OneToMany(mappedBy = "room", orphanRemoval = true)//todo: ilişki reservatin tarafında kurucak yine
    private List<Reservation> reservations = new ArrayList<>();//bir odanın bir cok rezervasyonu olabilir bugün yarın rezervesi olabilir

    public Room() {
    }

    //kullanmama ihtimali de var ama yinede aldık
    //kullanıcıdan bilgiler alıcamız icin gerek olmayabilir.
    public Room(Long id, String number, Integer capacity, Hotel hotel) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    //otel rezerveleri icin get methodu ile yazdırırız.
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
