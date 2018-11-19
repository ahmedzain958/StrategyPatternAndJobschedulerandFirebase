package com.mobiledoctors24.rxaffectsui;


public class Utilities {

    public static String convertEnglishNumbersToArabic(String englishNumberAsString) {
        try {

            char[] arabicChars = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
            StringBuilder builder = new StringBuilder();
            try {
                for (int i = 0; i < englishNumberAsString.length(); i++) {
                    if (Character.isDigit(englishNumberAsString.charAt(i))) {
                        builder.append(arabicChars[(int) (englishNumberAsString.charAt(i)) - 48]);
                    } else {
                        builder.append(englishNumberAsString.charAt(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            return builder.toString();
        } catch (Exception e) {

        }
        return englishNumberAsString;
    }
}
