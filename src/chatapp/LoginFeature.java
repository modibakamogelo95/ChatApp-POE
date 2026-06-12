package chatapp;
// LoginFeature - handles registration, login, and validation checks
import java.util.regex.Pattern;

public class LoginFeature {
    
    private String savedU;
    private String savedP;
    private String savedF;
    private String savedL;
    private String savedM;
    
    public boolean checkUserName(String s) {
        if (s == null) return false;
        return s.contains("_") && s.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String s) {
        if (s == null) return false;
        boolean enough = s.length() >= 8;
        boolean cap = s.matches(".*[A-Z].*");
        boolean dig = s.matches(".*[0-9].*");
        boolean spec = s.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?~`].*");
        return enough && cap && dig && spec;
    }
    
    // regex from https://regex101.com/
    public boolean checkCellPhoneNumber(String s) {
        if (s == null) return false;
        return Pattern.matches("^\\+27[0-9]{9}$", s);
    }
    
    public String registerUser(String u, String p, String f, String l, String m) {
        StringBuilder out = new StringBuilder();
        boolean allGood = true;
        
        if (!checkUserName(u)) {
            out.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n");
            allGood = false;
        } else {
            out.append("Username successfully captured.\n");
        }
        
        if (!checkPasswordComplexity(p)) {
            out.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
            allGood = false;
        } else {
            out.append("Password successfully captured.\n");
        }
        
        if (!checkCellPhoneNumber(m)) {
            out.append("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.\n");
            allGood = false;
        } else {
            out.append("Cell phone number successfully added.\n");
        }
        
        if (allGood) {
            savedU = u;
            savedP = p;
            savedF = f;
            savedL = l;
            savedM = m;
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
        } else {
            return out.toString().trim();
        }
    }
    
    public boolean loginUser(String u, String p) {
        if (u == null || p == null) return false;
        return u.equals(savedU) && p.equals(savedP);
    }
    
    public String returnLoginStatus(boolean success, String f, String l) {
        if (success)
            return "Welcome " + f + ", " + l + " it is great to see you again.";
        else
            return "Username or password incorrect, please try again.";
    }
}
