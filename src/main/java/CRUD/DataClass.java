package CRUD;

import java.util.Date;

import java.io.Serializable;

/**
 * This class is the class that stores the data of the journal entries 
 * I had origionally decided to paramitize the class as at the start I wasn't sure what kind of data I would be receiving
 * however since then I have changed it to just a set type since I knew what I'd be taking in
 */
public class DataClass implements Serializable{
    private String data;
    private Date lastEdited = new Date();
    private String name;
    
    public DataClass(String data, String name){
        this.data = data;
        this.name = name;
        this.lastEdited.setTime(System.currentTimeMillis());
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return lastEdited.toString();
    }

    public String read(){
        return data;
    }

    public void update(String data){
        this.data = data;
    }

    public void delete(){
        this.delete();
    }
}