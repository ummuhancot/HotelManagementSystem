package com.tpe.service;

import com.tpe.domain.Address;
import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.Scanner;

public class GuesService {

    private Scanner scanner = new Scanner(System.in);

    private final GuestRepository guestRepository;

    //parametreli contstaktır.
    public GuesService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    //9-b
    public void saveGuest() {

        Guest guest = new Guest();

        System.out.println("Enter guest name : ");
        guest.setName(scanner.nextLine());

        Address address = new Address();
        System.out.println("Enter guest street : ");
        address.setStreet(scanner.nextLine());

        System.out.println("Enter guest city : ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter guest country : ");
        address.setCountry(scanner.nextLine());

        System.out.println("Enter guest zipcode : ");
        address.setZipcode(scanner.nextLine());

        guest.setAddress(address);

        guestRepository.save(guest);
        System.out.println("Guest is saved successfully....");
    }

    //ödev:2-b
    public Guest findGuestById(Long guestId) {

        try {

            Guest foundGuest=guestRepository.findById(guestId);//burda repoda döndürmeyi oluşturduk
             //bulunan guest konsola yazdırma
                if (foundGuest!=null){
                    System.out.println("*-----------------------------------*");
                    System.out.println(foundGuest);
                    System.out.println("*-----------------------------------*");
                    return foundGuest;
                }else {
                    throw new GuestNotFoundException("Guest not found by ID: "+guestId);
                }


        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
