package com.tpe.domain;
//4
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "t_hotel")
public class Hotel {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    //A otemi odamarı:11,12,13 var
    //oda listesinden 11 cıkarsam :12,13 -->room tablosunda kalmaya devam etsin tablodan silinmesin bu yüzden cascade=CascadeType.REMOVE dicez.
    //orphanRemoval = true:11 i tablodan da siler di buyüzden cascade sectik.
    @OneToMany(mappedBy = "hotel",cascade = CascadeType.REMOVE)//todo:ilişki daha sonra düzenlenecek room u oluşturduk burayı devam ettirdik ilişkiye mappedBy ekledik room la eşleştirdik
    //ilişki diğer tarafta olacak
    private List<Room> rooms = new ArrayList<>();

    //cont
    public Hotel() {
    }

    public Hotel(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    //toString
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
