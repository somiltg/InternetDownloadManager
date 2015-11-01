/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author prakhar
 */
public class DownloadRecordDatabase {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/IDM";
    static final String USER="root";
    static final String PASS="shriya";
    public static Connection conn=null;
    public static PreparedStatement ins=null,seek=null,query=null,delete=null,seekall=null;
    DownloadRecord downloadRecord;
    
    public DownloadRecordDatabase(DownloadRecord dr) {
        downloadRecord = dr;
    }
     public DownloadRecordDatabase() {
         downloadRecord=null;
    }
    
    public boolean insert()
    {
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            int n=downloadRecord.TotalConnections;
            final String insstr="INSERT INTO Downloads VALUES";      
            //final String downloadstr="(DownloadID,TotalConnections,FileURL,FilePath,FileName,status,FileSize,BytesDownloaded,AssociatedTime,ScheduleDate,Type,";
            //final String subdownloadstr="SubDownloadFile,FileStartLocation,FileEndLocation,complete,SubDownloadID,SubBytesDownloaded) ";
            final String values="(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
           
            for(int i=0;i<n;i++)
            {
               
                ins=(PreparedStatement)conn.prepareStatement(insstr+values);
               
                ins.setInt(1,downloadRecord.DownloadID);
                ins.setInt(2,downloadRecord.TotalConnections);
                ins.setString(3,downloadRecord.FileURL);
                ins.setString(4,downloadRecord.FilePath);
                ins.setString(5,downloadRecord.FileName);
                ins.setInt(6,downloadRecord.status);
                ins.setLong(7,downloadRecord.FileSize);
                ins.setLong(8,downloadRecord.BytesDownloaded);
                ins.setLong(9,downloadRecord.AssociatedTime);
               if(downloadRecord.ScheduledDate==null) ins.setDate(10,new java.sql.Date(0)); 
               else ins.setDate(10,new java.sql.Date(downloadRecord.ScheduledDate.getTime()));
                ins.setString(11,downloadRecord.Type);
                  
               /* if(i==0)
                {downloadRecord.SubDownloadsArray=new SubDownloadRecord[downloadRecord.TotalConnections];
                }
                downloadRecord.SubDownloadsArray[i]=new SubDownloadRecord();
                       */
                    ins.setString(12,downloadRecord.SubDownloadsArray[i].SubDownloadFile);
                ins.setLong(13,downloadRecord.SubDownloadsArray[i].FileStartLocation);
                ins.setLong(14,downloadRecord.SubDownloadsArray[i].FileEndLocation);
                ins.setBoolean(15,downloadRecord.SubDownloadsArray[i].complete);
                ins.setInt(16,downloadRecord.SubDownloadsArray[i].SubDownloadId);
                ins.setLong(17,downloadRecord.SubDownloadsArray[i].BytesDownloaded);
                ins.executeUpdate();
                    
            }
        } catch (Exception ex) {
            System.out.println("Insert "+ex);
            String exc="Connectivity could not be established!!";
            return false;
        }
        return true;
    }
    
    public boolean update()
    {
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            int n=downloadRecord.TotalConnections;
            final String updstr="UPDATE Downloads SET ";
            final String downloadstr="status=?,BytesDownloaded=?,AssociatedTime=?,ScheduleDate=?,";
            final String subdownloadstr="SubBytesDownloaded=?,complete=? WHERE DownloadID=? AND SubDownloadID=?;";
            for(int i=0;i<n;i++)
            {
                SubDownloadRecord sdr=downloadRecord.SubDownloadsArray[i];
                query=(PreparedStatement)conn.prepareStatement(updstr+downloadstr+subdownloadstr);
               
                query.setInt(1,downloadRecord.status);
                query.setLong(2,downloadRecord.BytesDownloaded);
                query.setLong(3,downloadRecord.AssociatedTime);
               if(downloadRecord.ScheduledDate==null) query.setDate(4,new java.sql.Date(0)); 
               else query.setDate(4,new java.sql.Date(downloadRecord.ScheduledDate.getTime()));
                query.setLong(5,sdr.BytesDownloaded);
                query.setBoolean(6,sdr.complete);
                query.setInt(7,downloadRecord.DownloadID);
                query.setInt(8,sdr.SubDownloadId);
               // System.out.println(query.toString());
                query.executeUpdate();
            }
        } catch (Exception ex) {
             System.out.println("UPDATE"+ex);
            String exc="Connectivity could not be established!!";
            return false;
        }
        return true;
    }
    
   private DownloadRecord seek()
    {
        DownloadRecord ret=new DownloadRecord();
        try {
            
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            final String seekstr="SELECT * FROM Downloads WHERE DownloadID=?;";
            seek=(PreparedStatement)conn.prepareStatement(seekstr);
            
            seek.setInt(1,downloadRecord.DownloadID);
            ResultSet rs=seek.executeQuery();
            int i=0;
            while(rs.next())
            {
                ret.DownloadID=rs.getInt(1);
                ret.TotalConnections=rs.getInt(2);
                ret.FileURL=rs.getString(3);
                ret.FilePath=rs.getString(4);
                ret.FileName=rs.getString(5);
                ret.status=rs.getInt(6);
                ret.FileSize=rs.getLong(7);
                ret.BytesDownloaded=rs.getLong(8);
                ret.AssociatedTime=rs.getLong(9);
                ret.ScheduledDate=rs.getDate(10);
                ret.Type=rs.getString(11);
                //System.out.println("Yahanft");
                if(i==0)
                {ret.SubDownloadsArray=new SubDownloadRecord[ret.TotalConnections];
                }
                ret.SubDownloadsArray[i]=new SubDownloadRecord();
                ret.SubDownloadsArray[i].SubDownloadFile=rs.getString(12);
                //System.out.println("Yahan");
                ret.SubDownloadsArray[i].FileStartLocation=rs.getLong(13);
                ret.SubDownloadsArray[i].FileEndLocation=rs.getLong(14);
                ret.SubDownloadsArray[i].complete=rs.getBoolean(15);
                ret.SubDownloadsArray[i].SubDownloadId=rs.getInt(16);
                ret.SubDownloadsArray[i].BytesDownloaded=rs.getLong(17);
                i++;
                //System.out.println(" "+i);
            }
        } catch (Exception ex) {
            System.out.println("xxx");
            return null;
        }
        return ret;
    }
    
    public boolean delete()
    {
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            final String delstr="DELETE FROM Downloads WHERE DownloadID=?;";
            delete=(PreparedStatement)conn.prepareStatement(delstr);
            delete.setInt(1,downloadRecord.DownloadID);
            delete.executeUpdate();
        } catch (Exception ex) {
             System.out.println("delete"+ex);
            String exc="Connectivity could not be established!!";
            return false;
        }
        return true;
    }
     Vector<DownloadRecord> seekall(){
        Vector<DownloadRecord> adr = new Vector<DownloadRecord>();
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            final String seekallstr="SELECT DISTINCT DownloadID FROM Downloads;";
            seekall=(PreparedStatement)conn.prepareStatement(seekallstr);
            ResultSet rs=seekall.executeQuery();
            while(rs.next())
            {
                DownloadRecord temp=new DownloadRecord();
                temp.DownloadID=rs.getInt(1);
                //System.out.println("xxxx -> "+temp.DownloadID);
                DownloadRecordDatabase getrcd=new DownloadRecordDatabase(temp);
                
                temp=getrcd.seek();
                adr.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("seek all"+ex);return null;
        }
        return adr; 
    }
}
