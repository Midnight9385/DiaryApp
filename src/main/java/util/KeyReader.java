package util;

import io.github.cdimascio.dotenv.Dotenv;

public class KeyReader {
    public static void main(String[] args) {
        System.out.println(KeyReader.getAccessKey());
    }
    public static String getAccessKey(){
        Dotenv dotenv = Dotenv.configure().load();
        return dotenv.get("ACCESS_TOKEN");
    }
}
