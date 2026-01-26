package com.pembukuan_cv_abba_barokah.Util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class FormatUtil {
    
    public static String formatRupiah(BigDecimal amount) {
        if(amount == null) return "Rp 0,00";
        Locale localeID = new Locale ("in","ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(amount);
    }

    public static String formatTanggal(LocalDate date) {
        if(date == null) return "-";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("in","ID"));
        return date.format(formatter);
    }

    public static BigDecimal parseToBigDecimal (String input) {
        try {
            if(input == null || input.trim().isEmpty()) return BigDecimal.ZERO;
            String cleanString = input.replaceAll("[^\\d.]","");
            return new BigDecimal(cleanString);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
}
