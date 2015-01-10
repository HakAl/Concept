package concept.com.labtech.util;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTextUtils {
    /**
     * Adds white space to a String to make it more easily readable.
     *
     * @param unmasked the String to mask
     * @return a String masked with white space at every 4 characters
     */
    public static String maskCardStringWhiteSpace(String unmasked) {
        return maskCardStringWhiteSpace(unmasked, 4);
    }

    /**
     * Adds white space to a String to make it more easily readable.
     *
     * @param unmasked the String to mask
     * @param where    how many character to pass before adding a space
     * @return the masked String
     */
    public static String maskCardStringWhiteSpace(String unmasked, int where) {
        int iterations = unmasked.length() / where;
        String masked = "";
        for (int i = 0; i < iterations; i++) {
            masked += unmasked.substring((i * where), (i * where) + where);
            masked += " ";
        }
        return (unmasked.length() % where != 0) ?
                masked + unmasked.substring(
                        (where * iterations),
                        ((where * iterations) + (unmasked.length() % where)))
                :
                masked;
    }

    /**
     * @param unformatted an unformatted string 10 chars in length
     * @return a phone number of the form (XXX) XXX-XXXX
     */
    public static String phoneFormatString(String unformatted) {
        String result = "(";
        result += unformatted.substring(0, 3);
        result += ") ";
        result += unformatted.substring(3, 6);
        result += "-";
        result += unformatted.substring(6, 10);
        return result;
    }

    /**
     * Masks the account number to a consistent format if there are at least 4 digits
     *
     * @param unmasked the String to mask
     * @return if unmasked > 4, a masked String
     */
    public static String simpleCardNumberMask(String unmasked) {
        if (unmasked.length() > 4) {
            return String.format("****-****-****-", unmasked.substring(unmasked.length() - 4, unmasked.length()));
        } else {
            return unmasked;
        }
    }


    public static String formatDate(Context context, String unformatted) {
        String result = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date parsedDate = inputFormat.parse(unformatted);
            result = outputFormat.format(parsedDate);
        } catch (ParseException e) {
            result = unformatted;
            e.printStackTrace();
        }
        return String.format("Today: ", result);
    }

    public static String getUnixTime() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return String.valueOf(unixTime);
    }
}