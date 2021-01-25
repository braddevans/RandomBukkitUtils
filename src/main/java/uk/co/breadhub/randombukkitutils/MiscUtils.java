package uk.co.breadhub.randombukkitutils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

public class MiscUtils {
    private static final DecimalFormat formatter = new DecimalFormat("#0.00");

    public static String formatCurrency(final double amount) {
        return formatter.format(amount);
    }

    public static int getMinutesFromTimeCode(String timeCode) {
        if (timeCode.toLowerCase().contains("mo")) {
            return Integer.parseInt(timeCode.substring(0, timeCode.indexOf("m"))) * 302400;
        }
        else if (timeCode.toLowerCase().contains("h")) {
            return Integer.parseInt(timeCode.substring(0, timeCode.indexOf("h"))) * 60;
        }
        else if (timeCode.toLowerCase().contains("d")) {
            return Integer.parseInt(timeCode.substring(0, timeCode.indexOf("d"))) * 1440;
        }
        else if (timeCode.toLowerCase().contains("s")) {
            return Integer.parseInt(timeCode.substring(0, timeCode.indexOf("s"))) / 60;
        }
        else if (timeCode.toLowerCase().contains("w")) {
            return Integer.parseInt(timeCode.substring(0, timeCode.indexOf("w"))) * 10080;
        }
        else if (timeCode.toLowerCase().contains("m")) {
            return Integer.parseInt(timeCode.substring(0, timeCode.indexOf("m")));
        }
        return 1;
    }

}