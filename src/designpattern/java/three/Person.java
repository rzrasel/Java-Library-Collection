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
public class Person implements Observer {

    String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void update(String availabiliy) {

        System.out.println("Hello " + personName + ", Product is now " + availabiliy + " on flipkart");
    }
}
