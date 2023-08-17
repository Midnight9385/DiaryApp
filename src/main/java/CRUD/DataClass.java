package CRUD;

import java.util.Date;

import java.io.Serializable;

//This class is the class that stores the data of the journal entries
//I decided to paramitize the class as at the start I wasn't sure what kind of data I would be receiving
public class DataClass<type> implements Serializable{
    private type data;
    private Date lastEdited = new Date();
    private String name;
    
    public DataClass(type data, String name){
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

    public type read(){
        return data;
    }

    public void update(type data){
        this.data = data;
    }

    public void delete(){
        this.delete();
    }
}