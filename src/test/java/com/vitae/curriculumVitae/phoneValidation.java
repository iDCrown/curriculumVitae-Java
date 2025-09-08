package com.vitae.curriculumVitae;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.vitae.curriculumVitae.validations.Phone;

public class phoneValidation {
    @Test
    void testValidPhoneNumber() {
        assertTrue(Phone.validatePhoneNumber("+573195082543", "CO"));  // válido
        assertTrue(Phone.validatePhoneNumber("3195082543", "CO"));     // válido
        assertTrue(Phone.validatePhoneNumber("+12025550173", "US"));   // válido
        assertTrue(Phone.validatePhoneNumber("3195082543", "MX"));           // ahora inválido
        assertFalse(Phone.validatePhoneNumber("12345", "US")); 
        assertFalse(Phone.validatePhoneNumber("abcdefg", "ES"));       // inválido
    }

}
