package com.vitae.curriculumVitae.validations;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.NumberParseException;

//helper class to validate phone numbers
public class Phone {
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public static Boolean validatePhoneNumber(String number, String region) {
       
          if (!number.matches("^\\+?(?=(?:.*\\d){7,15}$)[0-9\\s]{7,15}$")) {
            return false;
         }
        

        // Validación de número de teléfono usando libphonenumber
           try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(number, region);

            if (!phoneNumberUtil.isValidNumberForRegion(phoneNumber, region)) {
                return false;
            }

                   
            return true;
           } catch (NumberParseException e) {
               return false;
           }
    }

}
