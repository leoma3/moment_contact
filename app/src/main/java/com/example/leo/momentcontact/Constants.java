package com.example.leo.momentcontact;

/**
 * Created by LEO on 2016-03-05.
 */
public class Constants {
    public static final String DATABASE_NAME = "momentcontactdatabase";
    public static final String TABLE_NAME = "USERTABLE";
    public static final String UID = "_id";
    public static final String NAME = "Name";
    public static final String PASSWORD = "Password";
    public static final String STANLEY_PARK = "StanleyPark";
    public static final String POLICE_STATION = "PoliceStation";
    public static final int DATABASE_VERSION = 12;

    public static String strSeparator = ",";
    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
}
