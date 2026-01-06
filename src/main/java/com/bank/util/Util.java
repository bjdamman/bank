package com.bank.util;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Util {

   private static Util INSTANCE;

   public static Util getInstance() {
      if(INSTANCE == null) {
         INSTANCE = new Util();
      }

      return INSTANCE;
   }


   public boolean isIbanValid(String ibanAccountNumber) throws NumberFormatException {

      /* Deze methode geeft de som van de eerste 9 digits van het BBAN nummer
         en rekent de modules uit en vergelijkt de uitkomst met het laatste BBAN controle
         nummer */

       StringBuilder sbIbanAccountNumber = new StringBuilder(ibanAccountNumber);

       int IBAN_NUMBER_LENGTH = 17;
       int IBAN_CONTROL_NUMBER_INDEX = 17;
       int IBAN_START_NUMBER_INDEX = 8;

       int ibanDigits = Integer.parseInt(sbIbanAccountNumber.substring(IBAN_START_NUMBER_INDEX, IBAN_NUMBER_LENGTH));
       int ibanControlNumber = Integer.parseInt(sbIbanAccountNumber.substring(IBAN_CONTROL_NUMBER_INDEX));

       int sum = String.valueOf(ibanDigits)
                 .chars()
                 .map(c -> c - '0')
                 .sum();

       return (sum % 10) == ibanControlNumber;
   }

   public boolean isIBANAccountNumberValid(String ibanAccountNumber) {

       return isIBANKeyformat(ibanAccountNumber) &&
              isIbanValid(ibanAccountNumber);

   }

   public boolean isIBANKeyformat(String ibanAccountNumber) {

      /*Check de IBAN landcode , controlenummer en bankkey */

      String IBAN_COUNTRY_BANK_KEY = "NL25BANQ";
      Pattern pattern = Pattern.compile(IBAN_COUNTRY_BANK_KEY + "\\d{10}");
      return pattern.matcher(ibanAccountNumber).find();

   }

}
