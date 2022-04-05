/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Sameer
 */
public class Passenger {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger(int id, String name, String age, String gender) {
        
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
    int id;
    String name;
    String age;
    String gender;
    
    
            
    
}
