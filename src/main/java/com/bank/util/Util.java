package com.bank.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@FunctionalInterface
interface MyUtilInterface {

   // abstract method
   Boolean checkIBAN(String n);
}

public class Util {

   private final String ibanConstant = "NL25BANQ";

   private static Util INSTANCE;

   public static Util getInstance() {
      if(INSTANCE == null) {
         INSTANCE = new Util();
      }

      return INSTANCE;
   }

   public boolean checkBankAccountNumber(String ibanAccountNumber) {

     if (checkIBANformat(ibanAccountNumber) && checkIBANdigits(ibanAccountNumber.substring(8) ))
       return true;
     else
       return false;
   }

   public boolean checkIBANformat(String ibanAccountNumber) {
      Pattern pattern = Pattern.compile("NL25BANQ\\d{10}");

      Matcher matcher = pattern.matcher(ibanAccountNumber);

      if (!matcher.find())
         return false;
      else
         return true;

   }

   public boolean checkIBANdigits(String ibanAccountNumber) {

      MyUtilInterface ref = (str) -> {

         int sum = 0;

         for (int i = 0; i < (ibanAccountNumber.length()-1); i++) {

            sum += Integer.parseInt(String.valueOf(ibanAccountNumber.charAt(i)));

         }

         int checkNumber = Integer.parseInt(String.valueOf(ibanAccountNumber.charAt(ibanAccountNumber.length()-1)));

         if (sum % 10 != checkNumber)
            return false;
         else
            return true;

      };

      return ref.checkIBAN(ibanAccountNumber);

   }

}
