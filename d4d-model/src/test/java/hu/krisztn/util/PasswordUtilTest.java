package hu.krisztn.util;

import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 05..
 */
public class PasswordUtilTest {

    @Test
    public void hashString() {
        String passwordTyped = "apple123";
        String hashedPasswordAsString = PasswordUtil.hash(passwordTyped.toCharArray());
        assertTrue(PasswordUtil.verify(hashedPasswordAsString, passwordTyped.toCharArray(), Charset.defaultCharset()));
    }

    @Test
    public void hashString2() {
        String passwordTyped = "apple123";
        String wrongPassword = "apple1234";
        String hashedPasswordAsString = PasswordUtil.hash(passwordTyped.toCharArray());
        assertFalse(PasswordUtil.verify(hashedPasswordAsString, wrongPassword.toCharArray(), Charset.defaultCharset()));
    }

    @Test
    public void hashString3() {
        String passwordTyped = "apple123";
        String hashedPasswordAsString = PasswordUtil.hash(passwordTyped.toCharArray());
        assertEquals(98, hashedPasswordAsString.length());
        passwordTyped = passwordTyped + passwordTyped;
        hashedPasswordAsString = PasswordUtil.hash(passwordTyped.toCharArray());
        assertEquals(98, hashedPasswordAsString.length());
    }
}
