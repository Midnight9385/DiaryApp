package CRUD;

/**
 * this class is used to interface with the DataStorage class
 * however for ease of use you can also get the DataStorage object
*/
public class DataInterface {
    public  DataStorage d = new DataStorage(null);

    public  void updateDataStorage(String serial){
        d = new DataStorage(serial);
    }

    public String exit() {
        return d.close();
    }
    public String[] getList() {
        return d.getList();
    }
    public  void create(String name) {
        d.createData("", name);
    }

    public DataStorage getDataStorage(){
        return d;
    }

    /**
     * for use in the UIBooster table
     * @return
     */
    public String[][] getDataList(){
        return d.getTableList();
    }
}
