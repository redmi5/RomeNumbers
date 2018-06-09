import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    private static long convertRoman(String romanNumber) throws IndexOutOfBoundsException {
        if (romanNumber.isEmpty()) return 0;
        if (romanNumber.startsWith("M")) return 1000 + convertRoman(romanNumber.substring(1));
        if (romanNumber.startsWith("CM")) return 900 + convertRoman(romanNumber.substring(2));
        if (romanNumber.startsWith("D")) return 500 + convertRoman(romanNumber.substring(1));
        if (romanNumber.startsWith("CD")) return 400 + convertRoman(romanNumber.substring(2));
        if (romanNumber.startsWith("C")) return 100 + convertRoman(romanNumber.substring(1));
        if (romanNumber.startsWith("XC")) return 90 + convertRoman(romanNumber.substring(2));
        if (romanNumber.startsWith("L")) return 50 + convertRoman(romanNumber.substring(1));
        if (romanNumber.startsWith("XL")) return 40 + convertRoman(romanNumber.substring(2));
        if (romanNumber.startsWith("X")) return 10 + convertRoman(romanNumber.substring(1));
        if (romanNumber.startsWith("IX")) return 9 + convertRoman(romanNumber.substring(2));
        if (romanNumber.startsWith("V")) return 5 + convertRoman(romanNumber.substring(1));
        if (romanNumber.startsWith("IV")) return 4 + convertRoman(romanNumber.substring(2));
        if (romanNumber.startsWith("I")) return 1 + convertRoman(romanNumber.substring(1));
        throw new IndexOutOfBoundsException("something bad happened");
    }

    //возвращает наибольший общий делитель
    private static long findGreatestCommonDenominator(long number1, long number2) {
        return number2 == 0 ? number1 : findGreatestCommonDenominator(number2, number1 % number2);
    }

    private static long[] simplifyFraction(long numerator, long denominator) {
        long commonDenominator = findGreatestCommonDenominator(numerator, denominator);
        long[] simplifiedFraction = new long[2];
        simplifiedFraction[0] = numerator/commonDenominator;
        simplifiedFraction[1] = denominator/commonDenominator;
        return simplifiedFraction;
    }

    private static String convertToRoman(long toConvert) {
        LinkedHashMap<String, Long> roman_numerals = new LinkedHashMap<>();
        roman_numerals.put("M", 1000L);
        roman_numerals.put("CM", 900L);
        roman_numerals.put("D", 500L);
        roman_numerals.put("CD", 400L);
        roman_numerals.put("C", 100L);
        roman_numerals.put("XC", 90L);
        roman_numerals.put("L", 50L);
        roman_numerals.put("XL", 40L);
        roman_numerals.put("X", 10L);
        roman_numerals.put("IX", 9L);
        roman_numerals.put("V", 5L);
        roman_numerals.put("IV", 4L);
        roman_numerals.put("I", 1L);
        StringBuilder res = new StringBuilder();
        for(Map.Entry<String, Long> entry : roman_numerals.entrySet()){
            long matches = toConvert/entry.getValue();
            res.append(convertToRomanHelper(entry.getKey(), matches));
            toConvert = toConvert % entry.getValue();
        }
        return res.toString();
    }
    private static String convertToRomanHelper(String s, long n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    private static String simplifyRomanFraction (String romanFraction) {
        String [] inputSplit = romanFraction.split("/");
        long numerator;
        try {
            numerator = convertRoman(inputSplit[0]);
        } catch (IndexOutOfBoundsException e) {
            return "ERROR";
        }
        long denominator = convertRoman(inputSplit[1]);
        long [] simplifiedFraction = simplifyFraction(numerator, denominator);
        if(simplifiedFraction[1] == 1) {
            return convertToRoman(simplifiedFraction[0]);
        }
        String simplifiedRomanNumerator = convertToRoman(simplifiedFraction[0]);
        String simplifiedRomanDenominator = convertToRoman(simplifiedFraction[1]);
        return simplifiedRomanNumerator + "/" + simplifiedRomanDenominator;
    }


    public static void main(String[] args) {
       /* "II/IV"; "XXIV/VIII"; "12/16"; */
        try {
            String input = FileDao.readOneLineFromFile();
            String result = simplifyRomanFraction(input);
            FileDao.writeStringToFile(result.toString());
        } catch (IOException | NullPointerException e) {
            System.err.println(e.toString());
        }

    }

}
