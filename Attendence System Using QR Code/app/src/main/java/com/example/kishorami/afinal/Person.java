package com.example.kishorami.afinal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kishorami on 24-Nov-16.
 */
public class Person {

    //name and id string
    private String name;
    private String id;
   public int [] Attendence = new int[26];
  // Map<String, Object> childUpdates = new HashMap<>();
    public Person() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
