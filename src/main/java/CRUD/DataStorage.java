package CRUD;

import java.util.ArrayList;
import java.util.Base64;

import util.Exceptions.ErrorHandler;
import util.Exceptions.TestException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class stores all the journal entries as objects of DataClass
 */
public class DataStorage{
    private ArrayList<DataClass<?>> dataStorage;
    private Boolean testErrorHandler = false;

    public static void main(String[] args) {
        System.out.println(new File("").getAbsolutePath()+"\\CRUD\\Data\\Data.txt");
    }

    /**
     * @param serial the serial to contruct the dataStorage object with or null if you want it empty
     */
    public DataStorage(String serial){
        dataStorage = (serial!=null?contructDataFromString(serial):new ArrayList<DataClass<?>>());
    }

    /**
     * sets the status of the boolean flag to test error handlers
     * @param status true to test or false to not test
     */
    public void setErrorTestStatus(boolean status){
        this.testErrorHandler = status;
    }

    /**
     * used in {@link App.DiaryApp} to save the serial string to the user
     * @return the serial string of the data list
     */
    public String close(){
        return sendDataSerial();
    }

    /**
     * creates an entry in the data list
     * @param data the data inside the object (the entry itself)
     * @param name the name of the entry
     */
    public <type> void createData(type data, String name){
        dataStorage.add(new DataClass<type>(data, name));
        // System.out.println("Created entry: "+dataStorage.get(dataStorage.size()-1).getName());
    }

    /**
     * @param index the index in the data list to return
     * @return the DataClass object specified
     */
    public DataClass<?> readData(int index){
        return index<dataStorage.size()?dataStorage.get(index):new DataClass<>("error: index out of bounds", "");
    }

    /**
     * retrives the journal entry with the specified name
     * @param name the name of the entry to read
     * @return the DataClass object, if no name matches returns an empty object with data of "error"
     */
    public DataClass<?> readData(String name){
        for (DataClass<?> data : dataStorage) {
            if(data.getName().equals(name)){
                return data;
            }
        }

        return new DataClass<>("error", "");
    }

    /**
     * updates the data inside the specified entry
     * @param data2 the new data
     * @param name the entry to update
     */
    public void updateData(Object data2, String name){
        int index = -1;
        for (DataClass<?> data : dataStorage) {
            if(data.getName().equals(name)){
                index = dataStorage.indexOf(data);
            }
        }
        if(index!=-1){
            updateData(index, data2);
        }

        // return new DataClass<>("error: no file with name found", "");
    }

    public void updateData(int index, Object data){
        createData(data, dataStorage.get(index).getName());
        deleteData(index);
    }

    public void deleteData(int index){
        dataStorage.remove(index);
    }

    /**
     * deletes the entry with the specified name
     * @param name the name of the entry to delete
     */
    public void deleteData(String name){
        for (DataClass<?> data : dataStorage) {
            if(data.getName().equals(name)){
                dataStorage.remove(data);
                return;
            }
        }
    }

    public ArrayList<DataClass<?>> getSavedData(){
        try{
            if(testErrorHandler){
                throw new TestException();
            }

            FileReader reader = new FileReader(new File("").getAbsolutePath()+"\\CRUD\\Data\\Data.txt");
            int content;
            String n = "";
            while((content=reader.read()) !=-1){
                n+=(char)content;
            }
            if(!n.equals("")){
                byte[] b = n.getBytes();
                ByteArrayInputStream bo = new ByteArrayInputStream(Base64.getDecoder().decode(b));
                ObjectInputStream so = new ObjectInputStream(bo);
                Object o = so.readObject();
                reader.close();
                return (ArrayList<DataClass<?>>) o;
            }
            else{
                reader.close();
                return new ArrayList<DataClass<?>>();
            }
        }catch(Exception i){
            // i.printStackTrace();
            ErrorHandler.sendErrorMessage("Reading Data", "error reading data from file, however you can continue using an empty data list");
            return new ArrayList<DataClass<?>>();
        }
    }

    /**
     * constructs the data list from a serial string
     * @param serial the serial to contruct the list from
     * @return the contructed list or an empty list if the serial isn't valid
     */
    public ArrayList<DataClass<?>> contructDataFromString(String serial){
        try{
            if(testErrorHandler){
                throw new TestException();
            }

            if(!serial.equals("")){
                byte[] b = serial.getBytes();
                ByteArrayInputStream bo = new ByteArrayInputStream(Base64.getDecoder().decode(b));
                ObjectInputStream so = new ObjectInputStream(bo);
                Object o = so.readObject();
                return (ArrayList<DataClass<?>>) o;
            }
            else{
                return new ArrayList<DataClass<?>>();
            }
        }catch(Exception i){
            // i.printStackTrace();
            ErrorHandler.sendErrorMessage("Contructing Data", "the serial was either empty or not valid for the type, however you can continue using an empty data list");
            return new ArrayList<DataClass<?>>();
        }
    }

    public void saveData(){
        try {
            if(testErrorHandler){
                throw new TestException();
            }

            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(dataStorage);
            so.flush();
            FileWriter writer = new FileWriter(new File(new File("").getAbsolutePath()+"\\CRUD\\Data\\Data.txt"));
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            writer.write(code);
            writer.flush();
        } catch (Exception e) {
            if(ErrorHandler.sendErrorMessageWithRetry("Save Error", "there was an error saving the data, you can retry or continue without saving").equals(true)){
                saveData();
            }
            // e.printStackTrace();
        }
    }

    /**
     * used to get the serial string of the entry list
     * @return the entry list serial string
     */
    public String sendDataSerial(){
        try {
            if(testErrorHandler){
                throw new TestException();
            }

            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(dataStorage);
            so.flush();
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            return code;
        } catch (Exception e) {
            if(ErrorHandler.sendErrorMessageWithRetry("Send Serial Error", "there was an error sending the serial of the data, you can retry or continue without saving").equals(true)){
                sendDataSerial();
            }
            // e.printStackTrace();
            return "error";
        }
    }

    /**
     * @return the list of strings from the title and date last changed of each entry
     */
    public String[] getList(){
        ArrayList<String> output = new ArrayList<>();
        dataStorage.forEach((i)->output.add(i.getName()+" -\t last changed:"+i.getDate()));
        return output.toArray(new String[0]);
    }  
    
    public ArrayList<String[]> getTableList(){
        ArrayList<String[]> output = new ArrayList<>();
        dataStorage.forEach((t) -> output.add(new String[]{t.getName(), t.getDate()}));
        return output;
    }

}