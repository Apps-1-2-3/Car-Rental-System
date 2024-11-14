import java.util.*;
import java.io.*;

////////////////////////////////Customer Class///////////////////////////////
public class Customer {
    private String firstName, lastName, gender, location, contact;
    public Customer() {
        firstName = "";
        lastName = "";
        gender = "";
        location = "";
        contact = "";
    }

    public void setCustomer(String fN, String lN, String g, String l, String c){
        firstName = fN;
        lastName = lN;
        gender = g;
        location = l;
        contact = c;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    public String getGender() {
        return gender;
    }
    public String getLocation() {
        return location;
    }
    public String getContact() {
        return contact;
    }

}
