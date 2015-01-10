package concept.com.labtech.util;

import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class FormValidator
{
    public static boolean inputMatchesLength(String errorMessage, EditText editText, Integer... possibleMaxes)
    {
        ArrayList<Integer> items = new ArrayList<>(Arrays.asList(possibleMaxes));

        if (!items.contains(Integer.valueOf(editText.getText().length()))) {
            editText.setError(errorMessage);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String errorMsg, TextView... textViews)
    {
        for (TextView view : textViews) {
            if (view.getText().toString().isEmpty()) {
                view.setError(errorMsg);
                view.requestFocus();
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailValid(String errorMsg, TextView... textViews)
    {
        for (TextView view : textViews) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(view.getText()).matches()) {
                view.setError(errorMsg);
                view.requestFocus();
                return false;
            }
        }
        return true;
    }

    /**
     * Our rule for phone numbers is that they must be 10 digits. Nothing else.
     *
     * @param errorMsg
     * @param textViews
     * @return
     */
    public static boolean isPhoneValid(String errorMsg, TextView... textViews)
    {
        Pattern pattern = Pattern.compile("\\d{10}");
        for (TextView view : textViews) {
            if (!pattern.matcher(view.getText()).matches()) {
                view.setError(errorMsg);
                view.requestFocus();
                return false;
            }
        }
        return true;
    }

    public static boolean isPasswordValid(String errorMsg, TextView... textViews)
    {
        for (TextView view : textViews) {
            if (view.getText().length() < 8) {
                view.setError(errorMsg);
                view.requestFocus();
                return false;
            }
        }
        return true;
    }

    public static boolean areTextViewsEqual(String errorMsg, TextView initialView, TextView confirmView)
    {
        if (!(initialView.getText().toString().equals(confirmView.getText().toString()))) {
            confirmView.setError(errorMsg);
            confirmView.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isSwitched(String errorMsg, SwitchCompat... switches)
    {
        for (SwitchCompat switchBtn : switches) {
            if (!switchBtn.isChecked()) {
                switchBtn.setError(errorMsg);
                switchBtn.requestFocus();
                return false;
            }
        }
        return true;
    }

    /**
     * See package com.gnuc.java.ccc;
     *
     * @param errorMsg
     * @param textView
     * @return true if CC is valid according to Luhn algorithm
     */
    public static boolean isValidCreditCard(String errorMsg, TextView textView)
    {
        String ccNumber = textView.getText().toString().replace(" ", "");
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        if (sum % 10 == 0) {
            return true;
        } else {
            textView.setError(errorMsg);
            textView.requestFocus();
            return false;
        }
    }

    /**
     * Validates an expiration date, expects the date to be in a '01/2014' format
     *
     * @param errorMsg
     * @param textViews
     * @return
     */
    public static boolean isValidExpirationDate(String errorMsg, TextView... textViews)
    {
        for (TextView view : textViews) {
            String[] date = TextUtils.split(view.getText().toString(), "/");
            if (Integer.valueOf(date[1]) == (Calendar.getInstance().get(Calendar.YEAR))) {
                if (Integer.valueOf(date[0]) < (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
                    view.setError(errorMsg);
                    view.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param minError  error displayed when entry is too low
     * @param maxError  error displayed when entry is too high
     * @param minBudget low boundary
     * @param maxBudget high boundary
     * @param textInput the input to check
     * @return T if valid
     */
    public static boolean isValidBudget(String minError, String maxError, float minBudget, float maxBudget, TextView textInput)
    {
        if (Float.valueOf(textInput.getText().toString().replace("$", "")) > maxBudget) {
            textInput.setError(String.format(maxError, maxBudget));
            return false;
        } else if (Float.valueOf(textInput.getText().toString().replace("$", "")) < minBudget) {
            textInput.setError(String.format(minError, minBudget));
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidZipCode(String minError, TextView view)
    {
        if (view.getText().length() < 5) {
            view.setError(minError);
            view.requestFocus();
            return false;
        }
        return true;
    }
}
