package com.visualnovel.novel;

public class Utils {
    public static int findSequentialIndex(String searchTerm, String[] searchArray) {
        for (int x = 0; x <= searchArray.length; x++) {
            if (searchArray[x].equals(searchTerm)) {
                return x;
            }
        }
        return -1;
    }

    public static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}
