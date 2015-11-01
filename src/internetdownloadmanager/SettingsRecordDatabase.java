/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author prakhar
 */
public class SettingsRecordDatabase {
    FileInputStream fis;
    FileOutputStream fos;
    Properties properties;
    SettingsRecord settingsRecord;
    
    public SettingsRecordDatabase(SettingsRecord sr) {
        settingsRecord = sr;
        properties =new Properties();
    }
    public boolean update()
    {
        try {
            fos = new FileOutputStream("Settings.properties");
            properties.setProperty("ProxyPort",""+settingsRecord.ProxyPort);
            properties.setProperty("ProxyAddress",""+settingsRecord.ProxyAddress);
            properties.setProperty("Username",settingsRecord.Username);
            properties.setProperty("Password",settingsRecord.Password);
            properties.setProperty("BufferSize",""+settingsRecord.BufferSize);
            properties.setProperty("TotalConnections",""+settingsRecord.TotalConnections);
            properties.setProperty("DestinationFolder",""+settingsRecord.DestinationFolder);
            properties.store(fos, "");
            fos.close();
            
        } catch (Exception ex) {
             System.out.println("hi"); System.out.println(ex);return false;
        }
        return true;
    }
    
    public SettingsRecord seek()
    {
        SettingsRecord ret=new SettingsRecord();
        try {
            fis = new FileInputStream("Settings.properties");
            properties.load(fis);
            ret.ProxyPort=Integer.parseInt(properties.getProperty("ProxyPort"));
            ret.ProxyAddress=properties.getProperty("ProxyAddress");
            ret.Username=properties.getProperty("Username");
            ret.Password=properties.getProperty("Password");
            ret.BufferSize=Integer.parseInt(properties.getProperty("BufferSize"));
            ret.TotalConnections=Integer.parseInt(properties.getProperty("TotalConnections"));
            ret.DestinationFolder=properties.getProperty("DestinationFolder");
        } catch (Exception ex) {
            System.out.println(ex);return null;
        }
        return ret;
    }
    
}
