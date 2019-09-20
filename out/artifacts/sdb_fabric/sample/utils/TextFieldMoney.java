package sample.utils;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.util.Locale;

public class TextFieldMoney extends TextField {
    private int maxlength;
    private String valor = "";

    public TextFieldMoney() {
        this.maxlength = 15;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        // Delete or backspace user input.
        if (getText() == null || getText().equalsIgnoreCase("")) {
            valor = "";
        }
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else{

            text = text.replaceAll("[^0-9]", "");
            valor += text;

            super.replaceText(start, end, text);
            if (!valor.equalsIgnoreCase(""))
                setText(formata(valor));
        }
    }

    @Override
    public void replaceSelection(String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxlength) {
            // Add characters, but don't exceed maxlength.
            // text = MascaraFinanceira.show(text);
            if (text.length() > maxlength - getText().length()) {
                // text = MascaraFinanceira.show(text);
                text = text.substring(0, maxlength - getText().length());
            }
            super.replaceSelection(text);
        }
    }

    /*
     *Return the number without money mask
     **/

    public String getCleanValue(){
        String cleanString = getText().replaceAll("[^0-9]", "");
        Double cleanNumber = new Double(cleanString);
        return String.valueOf(cleanNumber/100);
    }

    private String formata(Double valor) {
        Locale locale = new Locale("US","US");
        NumberFormat nf = NumberFormat.getInstance(locale);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        return nf.format(valor);
    }

    public String formata(String valor) {
        double v = new Double(valor);
        return formata(v/100);
    }

}
