package com.tpe.controller;
//3 runner deki start methodunu burda oluşturduk
import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.HotelRepository;
import com.tpe.repository.ReservationRepository;
import com.tpe.repository.RoomRepository;
import com.tpe.service.GuesService;
import com.tpe.service.HotelService;
import com.tpe.service.ReservationService;
import com.tpe.service.RoomService;

import java.util.Scanner;

public class HotelManagementSystem {

    private static Scanner scanner = new Scanner(System.in);

    //ana menü kullanıcıya gösterilir ve seçimi alınır
    public static void displayHotelManagementSystemMenu() {
        ///burayı 1-a icin yaptık
        ///NOT:sadece 1'er tane service ve repo objeleri oluşturulur ve tüm uygulamada kullanılır.
        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);

        ///Roomm icin verdik 2- ye param olarak roomRepository yazdık
        ///displayRoomOperationsMenu oluşturduk parametre istedi
        ///NOT:sadece 1'er tane service ve repo objeleri oluşturulur ve tüm uygulamada kullanılır.
        RoomRepository roomRepository=new RoomRepository();
        RoomService roomService=new RoomService(roomRepository,hotelService);//aynısını roomservicede verdim

        GuestRepository guestRepository = new GuestRepository();
        GuesService guesService = new GuesService(guestRepository);

        //reservationServicede constakter oluşturduk burda o constraktırların paramlarını istedi verdik.
        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService = new ReservationService(reservationRepository,roomService,guesService);
        int choice;

        do {

            System.out.println("========== Hotel Management System ==========");
            System.out.println("1.Hotel Operations");
            System.out.println("2.Room Operations");
            System.out.println("3.Guest Operations");
            System.out.println("4.Reservation Operations");
            System.out.println("0.Exit");
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();// \n
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ///burayı 1-a icin yaptık
                    //burada parametre istediğim icin parametre istiyor bunun icin yukarda objemizi hazırladık buraya ekledik
                    displayHotelOperationsMenu(hotelService);

                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guesService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 0:
                    System.out.println("Good Bye...");
                    HibernateUtils.shutDown();//uygulamayı sonlandırır
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        } while (choice != 0);


    }


    //her bir kategori için ayrı metodlar oluşturalım:işlemleri gösteren ve seçimini alan
    //hotel operations
    private static void displayHotelOperationsMenu(HotelService hotelService) {
        ///burayı 1:a icin yaptık
        //HotelService hotelService = new HotelService();
        // 2.kez cağırıldığında yeniden otel objesi oluşturur
        // bunu önlemek icin parametrede hotelServiceyi aldık

        System.out.println("Hotel Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                //1-a : oteli kaydetme
                hotelService.saveHotel();
                    break;
                case 2:
                    //2-a:hotel bulma
                    System.out.println("Enter hotel ID: ");
                    Long id= scanner.nextLong();
                    scanner.nextLine();

                    //2-b:
                    hotelService.findHotelById(id);
                    break;
                case 3:
                    //7-a
                    //id si verilen oteli silme (delete)
                    //şimdilik gectik
                    System.out.println("Enter hotel ID: ");
                    Long hotelid= scanner.nextLong();
                    scanner.nextLine();

                    hotelService.deleteHotelById(hotelid);                    break;
                case 4:
                    //3-a: tüm otelleri listeleme
                    hotelService.getAllHotels();//böyle bir methodum olsaydı dioyrum
                    break;
                case 5:
                    //8-a
                    //update etme
                    //ertelendi
                    System.out.println("Enter hotel ID: ");
                    Long updateHotelid= scanner.nextLong();
                    scanner.nextLine();

                    hotelService.ubdateHotelById(updateHotelid);

                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    ///buraya gectik--> parametre olarak roomservice class alsın dedik
    //room operations kayıt etmek icin service objesine ihtiyac var her seferinde serviceye gerek yok yukarda oluşturduk
    private static void displayRoomOperationsMenu(RoomService roomService) {



        System.out.println("Room Operation Menu");
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //4-a:bir odayı oluşturma
                    ///room entitiy oluşturduk room class oluşturduk
                    //4-b
                    roomService.saveRoom();//bu metod olsaydı kayıt etme yapardım diyip burdan oluşturuyoz
                    break;
                case 2:
                    //5-a:ÖDEV
                    System.out.println("Enter hotel ID: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    roomService.findRoomById(id);
                    break;
                case 3:
                //oteli silmek icin
                    //ÖDEV1:id si verien odayı silme
                    break;
                case 4:
                    //6-a:ÖDEV
                    roomService.getAllRooms();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //guest operations
    //1param verdik
    //2 yukarda sevice ve repostoriy objesini tanımladık
    //3 service de repoya baglayıp finaly yapıp param verdikten sonra
    //4 burda objeye service kısmına reponun fiıldını yazdık .
    //5 kısmı swicteki kızaran kısma guestserviceyi ekledik
    private static void displayGuestOperationsMenu(GuesService guesService) {
        System.out.println("Guest Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //9-a
                    guesService.saveGuest();

                    break;
                case 2:
                    //ÖDEV2:guesti bulma
                    break;
                case 3:
                    //ÖDEV3:guesti silme
                    break;
                case 4:
                    //ÖDEV 4:tüm konukları listeleme
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    break;
            }
        }
    }

    //reservation operations
    private static void displayReservationOperationsMenu(ReservationService reservationService) {
        System.out.println("Reservation Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //10-a:yeni rezervasyon oluşturma
                    reservationService.createReservation();
                    break;
                case 2:
                    //ÖDEV5:
                    break;
                case 3:
                    //ÖDEV6:
                    break;
                case 4:
                    //ÖDEV7:
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }


}