package sample.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class utils {

    public static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static java.sql.Date anotherFormatOfDate( String dateString){

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
        java.util.Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date sqlDate = null;
        if (date != null) {
            sqlDate = new Date(date.getTime());
        }

        return sqlDate;
    }
}
