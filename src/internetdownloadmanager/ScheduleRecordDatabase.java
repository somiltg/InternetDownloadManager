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

/**
 *
 * @author prakhar
 */
public class ScheduleRecordDatabase {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/IDM";
    static final String USER="root";
    static final String PASS="prakhar";
    public static Connection conn=null;
    public static PreparedStatement ins=null,seek=null,query=null,delete=null;
    ScheduleRecord scheduleRecord;
    
    public ScheduleRecordDatabase(ScheduleRecord sr) {
        scheduleRecord = sr;
    }
    
    public boolean insert()
    {
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            final String insstr="INSERT INTO Schedules ";      
            final String schedule="(DownloadID,FileURL,FilePath,FileName,AssociatedTime,ScheduledDate) ";
            final String values="('?','?','?','?','?','?');";
            ins=(PreparedStatement)conn.prepareStatement(insstr+schedule+values);
            ins.setInt(1,scheduleRecord.DownloadID);
            ins.setString(2,scheduleRecord.FileURL);
            ins.setString(3,scheduleRecord.FilePath);
            ins.setString(4,scheduleRecord.FileName);
            ins.setLong(5,scheduleRecord.AssociatedTime);
            ins.setDate(6,new java.sql.Date(scheduleRecord.ScheduledDate.getTime()));
            ins.executeUpdate();
        } catch (Exception ex) {
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
            final String updstr="SELECT Schedule SET ";      
            final String schedule="DownloadID='?',FileURL='?',FilePath='?',FileName='?',AssociatedTime='?',ScheduledDate='?';";
            query=(PreparedStatement)conn.prepareStatement(updstr+schedule);
            query.setInt(1,scheduleRecord.DownloadID);
            query.setString(2,scheduleRecord.FileURL);
            query.setString(3,scheduleRecord.FilePath);
            query.setString(4,scheduleRecord.FileName);
            query.setLong(5,scheduleRecord.AssociatedTime);
            query.setDate(6,new java.sql.Date(scheduleRecord.ScheduledDate.getTime()));
            query.executeUpdate();
        } catch (Exception ex) {
            String exc="Connectivity could not be established!!";
            return false;
        }
        return true;
    }
    
    public ScheduleRecord seek()
    {
        ScheduleRecord ret=new ScheduleRecord();
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            final String seekstr="SELECT Schedules WHERE DownloadID='?'";
            seek=(PreparedStatement)conn.prepareStatement(seekstr);
            seek.setInt(1,scheduleRecord.DownloadID);
            ResultSet rs=seek.executeQuery();
            while(rs.next())
            {
                ret.DownloadID=rs.getInt(1);
                ret.FileURL=rs.getString(2);
                ret.FilePath=rs.getString(3);
                ret.FileName=rs.getString(4);
                ret.AssociatedTime=rs.getLong(5);
                ret.ScheduledDate=rs.getDate(6);
            }
        } catch (Exception ex) {
            String exc="Connectivity could not be established!!";
            return null;
        }
        return ret;
    }
    public boolean delete()
    {
        try {
            Class.forName(JDBC_DRIVER);
            conn=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            final String delstr="DELETE FROM Schedules WHERE DownloadID='?'";
            delete=(PreparedStatement)conn.prepareStatement(delstr);
            delete.setInt(1,scheduleRecord.DownloadID);
            delete.executeQuery();
        } catch (Exception ex) {
            String exc="Connectivity could not be established!!";
            return false;
        }
        return true;
    }
}
