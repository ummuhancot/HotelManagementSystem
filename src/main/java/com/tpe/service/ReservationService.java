package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import com.tpe.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class ReservationService {

    private Scanner scanner = new Scanner(System.in);

    private final ReservationRepository reservationRepository;

    private final RoomService roomService;

    private final GuesService guesService;

    //parametreli contrakter oluşturduk sonra.
    public ReservationService(ReservationRepository reservationRepository, RoomService roomService, GuesService guesService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
        this.guesService = guesService;
    }

    //10-b
    public void createReservation() {


        Reservation reservation = new Reservation();

        System.out.println("Enter check-in date (yyyy-MM-dd):");
        String checkin=scanner.nextLine();
        reservation.setCheckInDate(LocalDate.parse(checkin));//localdate formatına almış olduk

        System.out.println("Enter check-out date (yyyy-MM-dd):");
        String checkout= scanner.nextLine();
        reservation.setCheckInDate(LocalDate.parse(checkout));//localdate formatına almış olduk .

        //müsait günler ve onadın müsit olduğunu kabul ettik
        //1 id ve 1 id müsteri icin rezervasyon almak istiyorum dedik
        System.out.println("Enter room id : ");
        Long roomId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter guest id : ");
        Long guestId= scanner.nextLong();
        scanner.nextLine();

        //servicelere ihtiyacım oldu yukarıda belirledik
        Room room = roomService.findRoomById(roomId);
        Guest guest = guesService.findGuestById(guestId); //burda repoda method oluşturduk
        //id si verilen id getirdik
        //id si verilen guest i de getirdik ödev kısmı yapıyoruz burda
        //bulunan odayı set ettik if de

        //exception fırlatsada null döndürüyor ve ifte düzenledik
        if (room!= null && guest!=null){
            reservation.setRoom(room);//fk eklenir objeler arasında ki ilişki kuruldu
            reservation.setGuest(guest);//fk eklenir obyeler arasındaki ilişki kuruldu.

            reservationRepository.save(reservation);//repositorda rezerveyi kayıt etme methodu yazdık.oluşturalım
            //repoda savea oluşturduk
            System.out.println("Reservation is created successfully...");
        }else {
            System.out.println("Reservation is CANCELLED!!!");
        }


    }
}
