/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.java.three;

/**
 *
 * @author Rz Rasel
 */
public class ObserverPatternMain {

    /**
     * @Author arpit mandliya
     */
    public static void main(String[] args) {
        Person arpitPerson = new Person("Arpit");
        Person johnPerson = new Person("John");

        Product samsungMobile = new Product("Samsung", "Mobile", "Not available");

    //When you opt for option "Notify me when product is available".Below registerObserver method
        //get executed   
        samsungMobile.registerObserver(arpitPerson);
        samsungMobile.registerObserver(johnPerson);

        //Now product is available
        samsungMobile.setAvailability("Available");

    }
}
//https://sourcemaking.com/design_patterns/observer/java/1
//https://sourcemaking.com/design_patterns/observer/java/2
//https://code.tutsplus.com/tutorials/android-design-patterns-the-observer-pattern--cms-28963