package com.tpe.service;
//6
import com.tpe.domain.Hotel;
import com.tpe.domain.Room;
import com.tpe.repository.RoomRepository;

import java.util.Scanner;
//service classları repository classları ile görüşür
///hotelden sonra bu tarafa gectik
/// 1 -2-4 yapıp buraya gectik

//NOT:entitynin service classları kendi repository classları ile görüşür
//başka bir entity ile ilgili işlem gerekirse diğer entitynin service ile görüşür
public class RoomService {

    private Scanner scanner = new Scanner(System.in);

    private final RoomRepository roomRepository;

    private final HotelService hotelService;

    public RoomService(RoomRepository roomRepository,HotelService hotelService) {//hotelmanagementSystem icinde sete ettik
        this.roomRepository = roomRepository;
        this.hotelService= hotelService;
    }

    //4-b:alınan bilgiler ile odayı kaydetme
    public void saveRoom() {
        //prametreli kullanabiliriz ama fiald lara set edicez buyüzden paramsız kullandık
        Room room = new Room();

        System.out.println("Enter room ID: ");
        room.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter room number: ");
        room.setNumber(scanner.next());

        System.out.println("Enter room capacity: ");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter hotel ID: ");
        Long hotelId = scanner.nextLong();
        scanner.nextLine();

        //idsi verilen oteli tablodan bulma
        //2-c
        //hotelrepository.findById(olmayanOtelin)=null döndürür burda kontrol etmemiz lazım
        //2-b:
        //tekrar kontrol yapmaya gerek yok
        //hotelService methodunu kullanısak olur yukarda hotel servis objesi oluşturduk medhodları kullanmak icin

        Hotel hotel = hotelService.findHotelById(hotelId);//burda kontrol etti
        if (hotel!=null){//eger null değilse oteli set et diyoruz
            room.setHotel(hotel);

            //ben bir odayı otele set ettimde odayı hallediyor
            //hotel.getRooms().add(room);//mappedby tarafından yapıldı

            //4-c:
            roomRepository.save(room);//bu methot yok oluşturcaz
            System.out.println("Room is saved successfully.Room id " +room.getId());
        }else {
            System.out.println("Room could't saved!!!");
        }








    }
}
