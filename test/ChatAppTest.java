package chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatAppTest {

    @Test
    public void testValidUsername() {
        LoginFeature login = new LoginFeature();
        assertTrue(login.checkUserName("a_bc"));
    }

    @Test
    public void testInvalidUsername() {
        LoginFeature login = new LoginFeature();
        assertFalse(login.checkUserName("abcdef"));
    }

    @Test
    public void testValidPassword() {
        LoginFeature login = new LoginFeature();
        assertTrue(login.checkPasswordComplexity("Password1!"));
    }

    @Test
    public void testInvalidPassword() {
        LoginFeature login = new LoginFeature();
        assertFalse(login.checkPasswordComplexity("pass"));
    }

    @Test
    public void testValidCellNumber() {
        LoginFeature login = new LoginFeature();
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testInvalidCellNumber() {
        LoginFeature login = new LoginFeature();
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    @Test
    public void testSuccessfulLogin() {

        LoginFeature login = new LoginFeature();

        login.registerUser(
                "a_bc",
                "Password1!",
                "John",
                "Smith",
                "+27831234567"
        );

        assertTrue(
                login.loginUser(
                        "a_bc",
                        "Password1!"
                )
        );
    }

    @Test
    public void testFailedLogin() {

        LoginFeature login = new LoginFeature();

        login.registerUser(
                "a_bc",
                "Password1!",
                "John",
                "Smith",
                "+27831234567"
        );

        assertFalse(
                login.loginUser(
                        "wrong",
                        "wrong"
                )
        );
    }

    @Test
    public void testMessageIdLength() {

        Message msg =
                new Message(
                        1,
                        "+27831234567",
                        "Hello world"
                );

        assertTrue(msg.getId().length() <= 10);
    }

    @Test
    public void testMessageHashGeneration() {

        Message msg =
                new Message(
                        1,
                        "+27831234567",
                        "Hi there",
                        "1234567890"
                );

        assertEquals(
                "12:1:HITHERE",
                msg.getHash()
        );
    }

    @Test
    public void testRecipientValidation() {

        Message msg =
                new Message(
                        1,
                        "+27831234567",
                        "Hello"
                );

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkTarget()
        );
    }

    @Test
    public void testInvalidRecipientValidation() {

        Message msg =
                new Message(
                        1,
                        "0831234567",
                        "Hello"
                );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkTarget()
        );
    }
}