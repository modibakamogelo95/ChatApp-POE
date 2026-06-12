package chatapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    
    private String id;
    private int seq;
    private String target;
    private String content;
    private String hash;
    
    public Message(int n, String to, String txt) {
        seq = n;
        target = to;
        content = txt;
        id = genId();
        hash = buildHash();
    }
    
    // for tests only (fixed ID)
    public Message(int n, String to, String txt, String fixed) {
        seq = n;
        target = to;
        content = txt;
        id = fixed;
        hash = buildHash();
    }
    
    private String genId() {
        Random r = new Random();
        long val = 1000000000L + (long)(r.nextDouble() * 9000000000L);
        return Long.toString(val);
    }
    
    private String letters(String w) {
        return w.replaceAll("[^A-Za-z]", "");
    }
    
    public String buildHash() {
        String start = id.substring(0, 2);
        String[] parts = content.trim().split("\\s+");
        String first = letters(parts[0]).toUpperCase();
        String last = letters(parts[parts.length - 1]).toUpperCase();
        return start + ":" + seq + ":" + first + last;
    }
    
    public boolean idOk() {
        return id != null && id.length() <= 10;
    }
    
    public String checkTarget() {
        if (target == null || !target.matches("^\\+27[0-9]{9}$"))
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        return "Cell phone number successfully captured.";
    }
    
    public void saveToFile() {
        try (FileWriter fw = new FileWriter("chat.json", true)) {
            fw.write("{\"id\":\"" + id + "\", \"hash\":\"" + hash + "\", \"to\":\"" + target + "\", \"msg\":\"" + content + "\"}\n");
        } catch (IOException e) {
            System.out.println("JSON error.");
        }
    }
    
    public String getId() { return id; }
    public int getSeq() { return seq; }
    public String getReceiver() { return target; }
    public String getText() { return content; }
    public String getHash() { return hash; }
}