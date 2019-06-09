package sample.components.sell.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.Main;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class Utils {
    public static Optional<ButtonType> ErrorAlert(String title, String header, String content) {
        if (sample.components.sell.Main.isStageAlive) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            setAlertData(alert, title, header, content);
            return alert.showAndWait();
        }
        return Optional.empty();
    }

    public static Optional<ButtonType> InfoAlert(String title, String header, String content) {
        if (sample.components.sell.Main.isStageAlive) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            setAlertData(alert, title, header, content);
            return alert.showAndWait();
        }
        return Optional.empty();
    }

    public static Optional<ButtonType> WarningAlert(String title, String header, String content) {
        if (sample.components.sell.Main.isStageAlive) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            setAlertData(alert, title, header, content);
            return alert.showAndWait();
        }
        return Optional.empty();
    }

    private static void setAlertData(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }

    public static String getCurrnetDateInStandardFormat() {
        return convertDateToStandardFormat(getCurrentDate());
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String convertDateToStandardFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
        return dateFormat.format(date);
    }

    private static Matcher createMatcher(String input, String regex) {
        Pattern r = Pattern.compile(regex);
        return r.matcher(input);
    }

    public static <T extends Comparable<T>> T isNumberInRange(T number, T min, T max) {

        if (number.compareTo(max) > 0) {
            return max;
        }
        if (number.compareTo(min) < 0) {
            return min;
        }
        return number;
    }


    public static boolean isNumberValid(String number, Number type) {
        try {
            switch (type) {
                case INT: {
                    return createMatcher(number, "^[0-9]\\d*$").matches();
                }
                case FLOAT: {
                    return createMatcher(number, "^(?=.+)(?:[0-9]\\d*|0)?(?:\\.\\d+)?$").matches();
                }
                case DOUBLE: {
                    return createMatcher(number, "^(?=.+)(?:[0-9]\\d*|0)?(?:\\.\\d+)?$").matches();
                }
                default:
                    return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }

    }

    public enum Number {
        FLOAT,
        INT,
        DOUBLE
    }
    public static String ThousandDivider(Float value){
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        return String.valueOf(formatter.format(value));
    }

}
