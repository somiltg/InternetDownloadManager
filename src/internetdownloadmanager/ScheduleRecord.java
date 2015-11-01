/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.util.Date;

/**
 *
 * @author prakhar
 */
public class ScheduleRecord {
    public int ScheduleID;
        public String FileURL;
        public String FilePath;
        public String FileName;
	public long AssociatedTime;
        public Date ScheduledDate;
ScheduleRecord(int id,String URl,String FileNm,Date date)
{
    ScheduleID=id;
    FileName=FileNm;
    FilePath=DashboardController.getDestinationFolder();
    ScheduledDate=date;
    FileURL=URl;
    AssociatedTime=0;
}

}

