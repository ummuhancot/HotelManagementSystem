package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.exceptions.HotelNotFoundException;
import com.tpe.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;
//6
public class HotelService {

    private Scanner scanner = new Scanner(System.in);


    private final HotelRepository hotelRepository; //= new HotelRepository();--> yerine cont verif finaly yaptık
    //parametreli cont oluşturunca  finall hata almayız
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }//uygulamanın istediğim yerinde kullanabilirim bu sayede


    //1-c:save hotel oteli kayıt etme
    public void saveHotel(){
        Hotel hotel=new Hotel();
        System.out.println("Enter hotel ID: ");
        //long id = scanner1.nextLong();
        //hotel.setId(id);yerine asağıdaki gibi aldık
        hotel.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter hotel name: ");
        hotel.setName(scanner.nextLine());

        System.out.println("Enter hotel location: ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);

        System.out.println("Hotel is saved successfully. Hotel ID:"+hotel.getId());

    }

    //2-b: idsi verilen otelin konsolda yazılması
    //bulunan otel null ise exceptin fırlatıyor bulunan otel varsa geriye oteli döndürür
    public Hotel findHotelById(Long id) {


        //Hotel foundHotel;//idsi verilen hotel
        //2-c
        Hotel foundHotel=hotelRepository.findById(id);//ihtiyac olduğu anda bunu belirleriz oluştur deriz
        try {
            if (foundHotel!=null){
                System.out.println("-------------------------------");
                System.out.println(foundHotel);
                System.out.println("-------------------------------");
                return foundHotel;
            }else {
                //System.out.println("hotel not found...."); bekentin kullanıcısı frontend dir önyüz yani
                ///gercek uygulamaya yakın old icin bir exception fırlatıcaz
                throw  new HotelNotFoundException("Hotel not found by ID : " + id);//daha anlamlı icersindeki problemle ilişkili bi excepiton fırlatırız.
                ///gercek uygulamada mesajları exception ile iletiriz.consola sout ile değil.
            }
        }catch (HotelNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;//id si bulunamayan durum da exception fırlatacak null repository den null gelince service null alınca exception fırlatacak
    }

    //3-b:tüm otelleri yazdırma
    public void getAllHotels() {
        ///3-c:findAll
        List<Hotel> allHotels = hotelRepository.findAll();// böyle bi method olsa tüm otelleri yazdırsa dedik geriye döndürdük
        //hic bir otel yok satır yok select * from t_hotel dedimiz de boş bir liste döndürür.
        if (allHotels.isEmpty()){//eger boşsa dedik boş olmasını kontrol ediyoz
            System.out.println("Hotel list is EMPTY!!!");//burda da bir exception fırlatabiliriz
        }else {
            System.out.println("--------------ALL HOTELS-------------");
            for (Hotel hotel:allHotels){//lamda da kullanabilirdik
                System.out.println(hotel);
            }
            System.out.println("--------------ALL HOTELS-------------");
        }
    }

    //7-b
    public void deleteHotelById(Long hotelid){
        //idsi verilen hotel var mı
        //2:b den sildik
        Hotel foundHotel =findHotelById(hotelid);//aydiyi zaten getirdik deleteById gerek yok

        if (foundHotel!=null){

            System.out.println(foundHotel);
            System.out.println("Are you sure to delete : ");//silmek istiyor musunuz
            System.out.println("Please answer qith Y or N :");
            String select = scanner.next();
            if (select.equalsIgnoreCase("Y")){
                hotelRepository.delete(foundHotel);//delete yeterli
                System.out.println("Hotel is deleted...");
            }else {
                System.out.println("Delete operation is CANCELLED!!!");
            }
        }else {
            System.out.println("Delete operation is CANCELLED!!!");
        }

    }

    //8:b
     public void ubdateHotelById(Long updateHotelid) {//mantıksal işlem yapılan kısım update delete save gibi işlemler.


        Hotel existingHotel = findHotelById(updateHotelid);//id: lismi:A localation:X ise değiştiriyor

        if (existingHotel != null){
            System.out.println("Enter the new hotel name : ");
            //değişkene set etmeden doğrudan parametrede kullandık
            existingHotel.setName(scanner.nextLine());
            System.out.println("Enter the new hotel location : ");
            existingHotel.setLocation(scanner.nextLine());
            hotelRepository.update(existingHotel);//id: ismi:B loca:Y oldu uygulama da bunu db  de değiştirmek icin repde bir method yazarız.
            System.out.println("Hotel is update...");

        }


    }


}
