package com.bank.bank.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IbanValidator implements ConstraintValidator<Iban, String> {
    /**
     * IBAN controle, moet bestaan uit landcode, controlenummer , bankkey en BBAN
     */
    private static final String FULL_NAME_REG_EXP = "NL25BANQ\\d{10}";

    @Override
    public boolean isValid(String ibanField, ConstraintValidatorContext context) {
        if (ibanField == null) {
            // mag niet leeg zijn
            return false;
        }

        if (!ibanField.matches(FULL_NAME_REG_EXP)) {
            // moet bestaan uit landcode, controlenummer , bankkey en BBAN
            return false;
        }

        StringBuilder sbIbanAccountNumber = new StringBuilder(ibanField);

        int IBAN_END_NUMBER_LENGTH = 17;
        int IBAN_CONTROL_NUMBER_INDEX = 17;
        int IBAN_START_NUMBER_INDEX = 8;

        int ibanDigits = Integer.parseInt(sbIbanAccountNumber.substring(IBAN_START_NUMBER_INDEX, IBAN_END_NUMBER_LENGTH));
        int ibanControlNumber = Integer.parseInt(sbIbanAccountNumber.substring(IBAN_CONTROL_NUMBER_INDEX));

        int sum = String.valueOf(ibanDigits)
                .chars()
                .map(c -> c - '0')
                .sum();

        /* De modules van de som van de eerste 9 BBAN getallen moet overeen komen met het
           het laatste BBAN controle getal */
        return (sum % 10) == ibanControlNumber;

    }
}
