/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author somil
 */
public final class DashboardController {
    
    //private Vector<DownloadRecord> recordArray;
    private static HashMap<Integer,DownloadRecord> recordArray;
    private static HashMap<Integer,ScheduleRecord> ScheduleRecordArray;
    private static SettingsRecord currentSettings; 
    private static Dashboard board;
    public static final String STATUSES[] = {"Downloading",
    "Paused", "Complete", "Cancelled", "Error","Scheduled","Waiting"};
    
    public DashboardController()
    {
        currentSettings=new SettingsRecordDatabase(new SettingsRecord()).seek();
        recordArray=new HashMap<Integer,DownloadRecord>();
        
        Vector<DownloadRecord> recordArr =new DownloadRecordDatabase().seekall();
        if(recordArr==null)recordArr=new Vector<DownloadRecord>();
        for(int i=0;i<recordArr.size();i++)
         {
             DownloadRecord temp=recordArr.get(i);
             recordArray.put(temp.DownloadID, temp);
         }
       board=new Dashboard(this);
           ModeltoViewRecords(board, recordArr);
        board.setVisible(true);
                board.setFocusableWindowState(true);//to transfer focus to window
   setProxySettings();
    }

    public boolean CheckNameValidity(String filename)
    {
   final char[] ILLEGAL_CHARACTERS= { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };
        if(filename.length()>25)return false;
       File f = new File(filename);
  try {//tries to form filename, if seperator present then obtained filename and original would not match
    if(!f.getCanonicalFile().getName().equals(filename))return false;
    int k;
    for(k=0;k<ILLEGAL_CHARACTERS.length;k++)
    {
        if(filename.indexOf(ILLEGAL_CHARACTERS[k])!=-1)break;
    }
    if(k!=ILLEGAL_CHARACTERS.length)return false;
        return true;
  } catch (IOException e) {
    return false;
  }
    }
    public static void UpdateMyRecord(DownloadRecord record)
    {
        recordArray.put(record.DownloadID, record);
    }
    public static void UpdateScheduleRecord(ScheduleRecord record)
    {
        ScheduleRecordArray.put(record.ScheduleID, record);
    }
    public static void DeleteScheduleRecord(ScheduleRecord record)
    {
        ScheduleRecordArray.remove(record.ScheduleID);
    }
     public static void DeleteMyRecord(DownloadRecord record)
    {
        recordArray.remove(record.DownloadID);
    }
       public int getIndex()
       {
           int i=0;
           while(recordArray.containsKey(i)){i++;}
           return i;
       }
       public int getScheduleIndex()
       {
           int i=0;
           while(ScheduleRecordArray.containsKey(i)){i++;}
           return i;
       }
    public static int getTotalConnection()
    {
        return currentSettings.TotalConnections;
    }
    public static void setTotalConnection(int connection)
    {
        currentSettings.TotalConnections=connection;
    }
    public static int getBufferSize()
    {
        return currentSettings.BufferSize;
    }
    public static void setBufferSize(int size)
    {
        currentSettings.BufferSize=size;
    }
      public static String getDestinationFolder()
    {
        return currentSettings.DestinationFolder; 
    }
    public static void setDestinationFolder(String destination)
    {
        File newdir=new File(currentSettings.DestinationFolder+File.separator+"tmp");
        if(newdir.exists())
        {
            File[] contents=newdir.listFiles();
            for(File file:contents)
            {
                try{
                file.delete();
                }
                catch(Exception e){}
            }
            newdir.delete();
        }
        currentSettings.DestinationFolder=destination;
        newdir=new File(currentSettings.DestinationFolder+File.separator+"tmp");
        if(!newdir.exists())
        {
            newdir.mkdir();
        }
    }
    public static void setProxyAddress(String ProxyAdd)
    {
        currentSettings.ProxyAddress=ProxyAdd;
    }
    public static void setProxyPort(int port) {
        currentSettings.ProxyPort=port;
    }
    public static void setUserName(String Username)
    {
        currentSettings.Username=Username;
    }
    public static void setPassword(String pass)
    {
        currentSettings.Password=pass;
    }
    public static String getProxyAddress()
    {
        return currentSettings.ProxyAddress;
    }
    public static int getProxyPort() {
        return currentSettings.ProxyPort;
    }
    public static String getUserName()
    {
        return currentSettings.Username;
    }
    public static String getPassword()
    {
        return currentSettings.Password;
    }
    void RecordtoView(Download record,Dashboard obj)
  {
          obj.AddEntry(record);
          recordArray.put(record.getDownloadRecord().DownloadID,record.getDownloadRecord());
          if(record.getStatus()==Download.ERROR){record.error(); obj.DisplayMessage("Oops.. No internet Connectivity!!!","ERROR",0);}
          else {record.run();ViewtoModelRecord(1, record.getDownloadRecord()); }
  }
    void ViewtoModelRecord(int option,DownloadRecord record)
    {
        DownloadRecordDatabase modelRecord=new DownloadRecordDatabase(record);
       
      switch(option)
      {
          case 1: modelRecord.insert(); break;
          case 2: modelRecord.update(); break;
          case 3: modelRecord.delete(); break;
      }
    }
    
    static void DisplayMessage(String str, String title,int choice)
    {
        board.DisplayMessage(str, title,choice);
    }
    public static void setProxySettings()
        {  
     if (!currentSettings.ProxyAddress.isEmpty()){
 System.out.println(currentSettings.ProxyAddress+ " "+currentSettings.ProxyPort+" "+currentSettings.Username+" "+currentSettings.Password );
                // This can be put in a menu, updated via interface
                System.out.println("Setting proxy in download file");
                if(Dashboard.Hostname)
                {   try { 
                    currentSettings.ProxyAddress=InetAddress.getByName(currentSettings.ProxyAddress).getHostAddress();
                    } catch (UnknownHostException ex) {
                        board.DisplayMessage("HostName Unknown error!!","ERROR",0); return;
                    }
                                   }
                System.setProperty("http.proxyHost", currentSettings.ProxyAddress);
                System.setProperty("http.proxyPort", ""+currentSettings.ProxyPort);
               /*  System.setProperty("https.proxyHost", currentSettings.ProxyAddress);
                System.setProperty("https.proxyPort", ""+currentSettings.ProxyPort);
                 System.setProperty("ftp.proxyHost",currentSettings.ProxyAddress);
                System.setProperty("ftp.proxyPort", ""+currentSettings.ProxyPort);*/
                System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
                if(!currentSettings.Username.isEmpty())
                {
                    //For username proxy authentication
                    final String pass=SettingsRecord.decrypt(currentSettings.Password);
                            //SettingsRecord.decrypt(
                  
Authenticator.setDefault(
   new Authenticator() {
      @Override
      public PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(
               currentSettings.Username, pass.toCharArray());
      }
   }
);

System.setProperty("http.proxyUser",currentSettings.Username);
System.setProperty("http.proxyPassword",  pass);         
                /*System.setProperty("https.proxyUser",  currentSettings.Username);
                         System.setProperty("https.proxyPassword", pass);
                          System.setProperty("ftp.proxyUser",  currentSettings.Username);
                         System.set Property("ftp.proxyPassword", pass);*/
                }
                         System.getProperties().put("http.proxySet", "true");
            }
                    //end of proxy
        }
	
    public URL verifyURL(String url) {
         //to verify the URL
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
            return null;
        url = url.replaceAll("%20"," ");
        
        // Verify format of URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            System.out.println("Url"+e);return null;
        }
         
        // Make sure URL specifies a file.
        if (verifiedUrl.getFile().length() < 2)
            return null;
         
        return verifiedUrl;
    }
    // Get file name portion of URL.
    public String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\')) + 1);
    }
          // Pause the selected download.
    void actionPause(Download SelectedDownload) {
       if(SelectedDownload==null)return;
       SelectedDownload.pause();
        UpdateMyRecord(SelectedDownload.getDownloadRecord());
       }
     
    // Resume the selected download.
    void actionResume(Download SelectedDownload) {
        if(SelectedDownload==null)return;
            SelectedDownload.resume();
             UpdateMyRecord(SelectedDownload.getDownloadRecord());
    }
     
    // Cancel the selected download.
    void actionCancel(Download SelectedDownload) {
     if(SelectedDownload==null)return;
     SelectedDownload.cancel();
      UpdateMyRecord(SelectedDownload.getDownloadRecord());
    }
    void ModeltoViewRecords(Dashboard obj,Vector<DownloadRecord> recordArray)
  {
      for(DownloadRecord record:recordArray)
      {
          obj.AddEntry(new Download(record));
      }
  }

   void SaveSettingsonClose() {
        if(!new SettingsRecordDatabase(currentSettings).update())
            DisplayMessage("Database Connectivity error... Settings not saved","Settings not saved", 0);
           
    }   
   void UpdateModelRecordsonClose()
   {
    for(DownloadRecord record:recordArray.values())
    {
       if(record.status==4)
           ViewtoModelRecord(3, record);
       else  
           ViewtoModelRecord(2, record);
    }
   }
}
