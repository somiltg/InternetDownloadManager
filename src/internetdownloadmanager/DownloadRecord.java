/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.util.Date;

/**
 *
 * @author somil
 */
public class DownloadRecord {
    
        public int DownloadID;
        public int TotalConnections;
        public String FileURL;
        public String FilePath;
        public String FileName;
         public int status;
	public long FileSize;	 	    //File Size
	public long BytesDownloaded;   //Bytes Downloaded
	public long AssociatedTime;
        public Date ScheduledDate;
        public String Type;
        public SubDownloadRecord[] SubDownloadsArray;

    public DownloadRecord() {
     DownloadID=-1;
        TotalConnections=DashboardController.getTotalConnection();
        FileURL="";
        FilePath="";
        FileName="";
         status=-1;
	FileSize=-1;	 	    //File Size
	BytesDownloaded=0;   //Bytes Downloaded
	AssociatedTime=0;
        ScheduledDate=null;
       Type="";
        SubDownloadsArray=null;
    }
        
        
        String getTimeRecord()
        {
            return ScheduledDate+" "+millisecondsToString(AssociatedTime);
        }
         public static String BytestoString(long bytes){
		if(bytes<1024)return bytes+ "Bytes";
                bytes/=1024;
                if(bytes<1024)return bytes+ "KB";
                bytes/=1024;
                if(bytes<1024)return bytes+ "MB";
                bytes/=1024;
		if(bytes<1024)return bytes+ "GB";
                bytes/=1024;
                return bytes+ "TB";
	}
        public static String millisecondsToString(long time){
		int milliseconds = (int)(time % 1000);
		int seconds = (int)((time/1000) % 60);
		int minutes = (int)((time/60000) % 60);
		int hours = (int)((time/3600000) % 24);

		String millisecondsStr = (milliseconds<10 ? "00" : (milliseconds<100 ? "0" : ""))+milliseconds;
		String secondsStr = (seconds<10 ? "0" : "")+seconds;
		String minutesStr = (minutes<10 ? "0" : "")+minutes;
		String hoursStr = (hours<10 ? "0" : "")+hours;
		
		return new String(hoursStr+":"+minutesStr+":"+secondsStr);
	}
        }
 
 