import java.util.Scanner;

import java.math.BigInteger;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Crypt {

    //Some issues with Crypt-2
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if(args.length == 0 || args[0].equals("help")) {
            System.out.println("Usage: ");
            System.out.println("java -jar Cipher encrypt file/text");
            System.out.println("java -jar Cipher decrypt file/text");
        } else if(args[0].equals("encrypt")) {
            if(args[1].equals("text")) {
                System.out.print("Plain Text: ");
                String text = scanner.nextLine();
                System.out.print("Encryption Level (1-2): ");
                int level = scanner.nextInt();
                System.out.println(encrypt(text, level));
            } else if(args[1].equals("file")) {
                System.out.print("File Name: ");
                String file = scanner.nextLine();
                System.out.print("Encryption Level (1-2): ");
                int level = scanner.nextInt();
                encryptFile(file, level);
                System.out.println("Done!");
            }
        } else if(args[0].equals("decrypt")) {
            if(args[1].equals("text")) {
                System.out.print("Encrypted Note: ");
                String encrypted = scanner.nextLine();
                System.out.println("Decrypted Text: " + decrypt(encrypted));
            } else if (args[1].equals("file")) {
                System.out.print("Encrypted File: ");
                String encrypted = scanner.nextLine();
                decryptFile(encrypted);
                System.out.println("Done!");
            }
        }
    }

    public static String encrypt(String base, int id) {
        //~ not supported!
        String uid = "0";
        String encrypted = "";
        if(id == 2) {
            uid = unify(2);
            String unpadded_bin = new BigInteger(uid, 16).toString(2);
            String bin = String.format("%8s", unpadded_bin).replace(' ', '0');
            for(char c:base.toCharArray()) {
                encrypted += (char) xor((int) c, bin);
            }
        } else {
            for(char c:base.toCharArray()) {
                encrypted += (char) ((c % 2 == 0) ? c + 1 : c - 1);
            }
        }
        return id + toHex(encrypted) + ":" + uid;
    }

    public static String decrypt(String encrypted) {
        int id = Integer.parseInt(encrypted.substring(0, 1));
        String uid = numberToNumber(encrypted.substring(encrypted.lastIndexOf(":") + 1), 16, 2);
        String decpart;
        String decrypted = "";
        if(id == 2) {
            //decpart = encrypted.substring(1, encrypted.length()-uid.length() + 3);
            decpart = encrypted.substring(1, encrypted.lastIndexOf(":"));
            for(String num:decpart.split("\\.")) {
                int decimal = Integer.parseInt(numberToNumber(num, 16, 10));
                decrypted += (char) xor(decimal, uid);
            }
        } else {
            decpart = toSource(encrypted.substring(1, encrypted.length()-uid.length() - 1));
            for(char c:decpart.toCharArray()) {
                decrypted += (char) ((c % 2 == 0) ? c + 1 : c - 1);
            }
        }
        return decrypted;
    }

    public static void encryptFile(String file, int level) {
        String line = null;
        try {
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter("enc_" + file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(encrypt(line, level));
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");
        } catch(IOException ex) {
            System.out.println("Error reading or writing file '" + file + "'");
        }
    }

    public static void decryptFile(String file) {
        String line = null;
        try {
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter("dec_" + file.substring(4));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(decrypt(line));
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");
        } catch(IOException ex) {
            System.out.println("Error reading or writing file '" + file + "'");
        }
    }

    public static String unify(int a) {
        String unique = "";
        for(int i = 0; i < a; i++) {
            int random = (int) (16*Math.random());
            unique += (random < 10) ? random : (random == 10) ? "a" :
                (random == 11) ? "b" : (random == 12) ? "c" : (random == 13) ? "d" : (random == 15) ? "e" : "f";
        }
        return unique;
    }

    public static int xor(int c, String mod) {
        return c^Integer.parseInt(numberToNumber(mod, 2, 10));
    }

    public static String toHex(String source) {
        String result = "";
        for(char c:source.toCharArray()) {
            result += charToNumber(c, 16) + ".";
        }
        return result;
    }

    public static String toSource(String hex) {
        String result = "";
        for(String num:hex.split("\\.")) {
            result += (char) Integer.parseInt(numberToNumber(num, 16, 10));
        }
        return result;
    }

    public static String numberToNumber(String num, int iradix, int rradix) {
        return new BigInteger(num, iradix).toString(rradix);
    }

    public static String charToNumber(char c, int radix) {
        return new BigInteger(String.valueOf((int) c)).toString(radix);
    }

    public static String[] split(String hex) {
        return hex.split("\\.");
    }
}
