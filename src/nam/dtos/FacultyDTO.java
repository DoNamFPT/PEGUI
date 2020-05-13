/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nam.dtos;

import java.io.Serializable;
import java.util.Vector;


public class FacultyDTO implements Serializable{
    private int id;
    private String FullName;
    private int Age;
    private String Address;


    public FacultyDTO() {
        
    }

    public FacultyDTO(int id, String FullName, int Age, String Address) {
        this.id = id;
        this.FullName = FullName;
        this.Age = Age;
        this.Address = Address;
    }

 
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(id);
        v.add(FullName);
        v.add(Age);
        v.add(Address);
        return v;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }


    
}
