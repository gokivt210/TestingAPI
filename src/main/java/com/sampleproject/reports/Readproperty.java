package com.sampleproject.reports;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

public class Readproperty {
   public static String readProperty(String key) {
        String value="";
        try{
        File filepath=new File("./Utilities//Environment.properties");
        FileInputStream fileInputStream=new FileInputStream(filepath);
        Properties properties=new Properties();
        properties.load(fileInputStream);
            value  = properties.getProperty(key);
            return value;
       }

        catch(Exception e){
            System.out.println(e.getMessage());

        }
        return value;

    }

    public String generateRandomName() {

            String name[]={"Krishnan","Gopala","Dinesh","Kumar"};
            Random random=new Random();
            int i = random.nextInt(name.length-1);
            int randomnumber=random.nextInt(0,1000);
            String randomname=name[i]+randomnumber;
            return randomname;

    }
    public static String generateRandomNumber() {
        Random random=new Random();
        int randomnumber=random.nextInt(100,1000);
        String randomNumber=Integer.toString(randomnumber);
        return  randomNumber;
    }
}
