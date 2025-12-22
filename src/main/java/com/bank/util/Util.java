package com.bank.util;

public class Util {

   private final String ibanConstant = "NL25BANQ";

   private static Util INSTANCE;

   public static Util getInstance() {
      if(INSTANCE == null) {
         INSTANCE = new Util();
      }

      return INSTANCE;
   }

   public Integer checkBankAccountNumber(String ibanAccountNumber) {

      if (!ibanAccountNumber.contains(ibanConstant))  {
         return 1;
      }

      int sum = 0;

      for (int i = 8; i < (ibanAccountNumber.length()-1); i++) {

         sum += Integer.parseInt(String.valueOf(ibanAccountNumber.charAt(i)));

      }

      int checkNumber = Integer.parseInt(String.valueOf(ibanAccountNumber.charAt(ibanAccountNumber.length()-1)));

      if (sum % 10 != checkNumber) {

        return 2;
      }

      return 0;
   }

}
