/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author somil
 */
public class SettingsRecord {
    public int ProxyPort;
    public String ProxyAddress;
    public String Username;
    public String Password;
    public int BufferSize;
    public int TotalConnections;
    public String DestinationFolder;
public SettingsRecord()
{
    ProxyAddress="";
    ProxyPort=0;
    Username="";
    Password="";
    BufferSize=10;
    TotalConnections=8;
    DestinationFolder="";
}
 public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception ex) {
            System.out.println("Decryption error");
        }
        return null;
    }
    private static Key generateKey() throws Exception {
        final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
}
}
