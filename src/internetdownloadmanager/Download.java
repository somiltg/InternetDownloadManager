
package internetdownloadmanager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.net.*;
import java.text.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.logging.Logger;

/**
 *
 * @author somil
 */
class Download extends Observable implements Runnable 
{
	/* Attributes */
	private long BytesDownloaded;   //Bytes Downloaded
	private int BufferSize;
	private SubDownload SubDownloadsArray[];             //array of objects of SubDownload class    
	private long StartTime;
        private DownloadRecord myRecord;
        private int status;
        
  // These are the status codes.
  public static final int DOWNLOADING = 0;
  public static final int PAUSED = 1;
  public static final int COMPLETE = 2;
  public static final int CANCELLED = 3;
  public static final int ERROR = 4;
  public static final int SCHEDULED = 5;
  public static final int WAITING = 6;
          
        
	public Download(String URL,int DownloadId,String FileName){
		
                myRecord=new DownloadRecord();
		myRecord.FileURL=URL;
		myRecord.TotalConnections= DashboardController.getTotalConnection();
		BufferSize = DashboardController.getBufferSize();
                myRecord.FilePath=DashboardController.getDestinationFolder();
		myRecord.DownloadID= DownloadId;
                myRecord.FileName=FileName;
                myRecord.Type="";
                myRecord.BytesDownloaded=BytesDownloaded=0;
                SubDownloadsArray=null;
                StartTime=0;
                myRecord.AssociatedTime=0;
                
		try{
				URL url = new URL(myRecord.FileURL);
                                System.out.println("start");
				URLConnection uc = url.openConnection();
                            myRecord.FileSize = uc.getContentLength();
                            System.out.println("passed");
                            if(!uc.getHeaderField("Accept-Ranges").equals("bytes")){ 
                                DashboardController.DisplayMessage("Fast Downloading and resume facility not possible on this server!!", "Information", 1); 
                            myRecord.TotalConnections=1;
                            myRecord.Type="none";
                            }
                          //System.out.println(myRecord.FileSize);
                            if(myRecord.FileSize<=0)
                            {
                                status=myRecord.status=ERROR;
                            }
                            else
                            {
                                
                                status=myRecord.status=WAITING;
                            }       
			}
                catch(Exception e){
                    //System.out.println(e.toString());
                    error();
			}
			
				
		}
        public Download(DownloadRecord record){
	//Create new object
             myRecord=record;
            SetDownloadFromRecord();
	}
        private void SetDownloadFromRecord()
        {//to set parameters of download from download record
                BytesDownloaded=myRecord.BytesDownloaded;
		BufferSize=DashboardController.getBufferSize();
                status=myRecord.status;
                StartTime=0;
                int SubId;
                SubDownloadsArray=new SubDownload[myRecord.TotalConnections];
                for(SubId=0;SubId<myRecord.TotalConnections;SubId++)
                {
            SubDownloadsArray[SubId]=new SubDownload(this,myRecord.FileURL,myRecord.SubDownloadsArray[SubId], BufferSize);
                }
        }
				
	public String getTimeForDownload(){
            
                if(status>0)
                    return "N/A";
            calcBytesDownloaded();
            long CurrBytesDownloaded=getSessionalBytesDownloaded();
		long time_elapsed,required_time;
		time_elapsed = System.currentTimeMillis() - StartTime;
		if (CurrBytesDownloaded > 0)
		required_time = (long)(((myRecord.FileSize-myRecord.BytesDownloaded) * time_elapsed)/CurrBytesDownloaded) - time_elapsed;
		else
		required_time =0;
		
		return " " + DownloadRecord.millisecondsToString(required_time) + " ";
	
	}
        
	public String getDownloadSpeed(){ 
	
		float current_speed;NumberFormat formatter ;
                if(status>0)
                    return "N/A";
                long currBytesDown=getSessionalBytesDownloaded();
		if (currBytesDown > 0 )
		current_speed = ((float) (currBytesDown*1000) / (System.currentTimeMillis() - StartTime));
		else
		current_speed = 0;
            formatter = NumberFormat.getNumberInstance();
		formatter.setMaximumFractionDigits(2);
                if(current_speed<1024)return " " + formatter.format(current_speed) + " Bytes/sec ";
                current_speed/=1024;
                if(current_speed<1024)return " " + formatter.format(current_speed) + " KB/sec ";
                return " " + formatter.format(current_speed) + " MB/sec ";
	
	}
	
        public String getFilePath()
        {
            return myRecord.FilePath+File.separator+myRecord.FileName;
        }
        public String getFileLocation()
        {
            return myRecord.FilePath;
        }
        public String getSubDownFileLocation()
        {
            return getFileLocation()+File.separator+"tmp";
        }
       public long getSessionalBytesDownloaded()
       {
           calcBytesDownloaded();
           return BytesDownloaded-myRecord.BytesDownloaded;
       }
        public String getBytesDownloaded()
       {
           calcBytesDownloaded();
           return DownloadRecord.BytestoString(BytesDownloaded);
       }
       public String getSize()
       {   
           if(myRecord.FileSize==-1)return "N/A";
           return DownloadRecord.BytestoString(myRecord.FileSize);
       }
       public float getProgress(){ 
			calcBytesDownloaded();
			if ( BytesDownloaded > 0 && myRecord.FileSize > 0 )
			return ((float)( BytesDownloaded * 100 ) / myRecord.FileSize) ;
                        else return 0.0f;
		}
       public boolean isSubDownComplete(int id)
	{
		return myRecord.SubDownloadsArray[id].complete;
	}
	Object getUrl() {
        return myRecord.FileURL;
    }
        
	public int getStatus()
        {
            return status;
        }
         public DownloadRecord  getDownloadRecord()
        {
            return myRecord;
        }
       void NotifySubDownloadComplete()
       {
           int SubDownid;
           for(SubDownid=0;SubDownid<myRecord.TotalConnections;SubDownid++)
           {
            if(!isSubDownComplete(SubDownid))break; 
           }
           if(SubDownid==myRecord.TotalConnections)DownloadCompleted();
       }
	public int StartDownload(){ 
					
			      //   System.out.println(myRecord.DownloadID);
                        int SubDownId=0;
			long SubDownStart,SubDownEnd,ld_partsize;
                        status=DOWNLOADING;
                        stateChanged();
                        //Creating the subDownload array and their corresponding SubsegmentRecord array
			SubDownloadsArray = new SubDownload[myRecord.TotalConnections]; 
                        myRecord.SubDownloadsArray=new SubDownloadRecord[myRecord.TotalConnections];
			//Size of each part to be downloaded
			ld_partsize= (long)(myRecord.FileSize/myRecord.TotalConnections);
                        SubDownloadRecord temp;
			for (SubDownId=0;SubDownId < myRecord.TotalConnections ;SubDownId++){
				//Create record member and initialize all members
                                temp=new SubDownloadRecord();
                                temp.SubDownloadId=SubDownId;
                                temp.complete=false;
                                temp.BytesDownloaded=0;
					if ( SubDownId == (myRecord.TotalConnections - 1))
					{
					SubDownStart=SubDownId*ld_partsize;
					SubDownEnd= myRecord.FileSize;
					}
					else
					{
					SubDownStart=SubDownId*ld_partsize;
					SubDownEnd= SubDownStart + ld_partsize - 1;
					}
                		temp.FileStartLocation=SubDownStart;
                                temp.FileEndLocation=SubDownEnd;
                                temp.SubDownloadFile = "SubSec" +  String.valueOf(myRecord.DownloadID) +"_"+String.valueOf(SubDownId) + ".txt";
				myRecord.SubDownloadsArray[SubDownId]=temp;
                               SubDownloadsArray[SubDownId] = new SubDownload(this,myRecord.FileURL,myRecord.SubDownloadsArray[SubDownId],BufferSize);
					myRecord.AssociatedTime=0;
                                        StartTime=System.currentTimeMillis();
						SubDownloadsArray[SubDownId].start();
			}
			myRecord.status=DOWNLOADING;
			return SubDownId;			 
			}
	
	
        
	public void calcBytesDownloaded(){
		BytesDownloaded=0;	
		if(myRecord==null)return;
                else if(SubDownloadsArray==null)return;
                for (int SubDownId=0;SubDownId < myRecord.TotalConnections  ;SubDownId++)
		{
			BytesDownloaded=BytesDownloaded + SubDownloadsArray[SubDownId].getBytesDownloaded();
		}
	}	
	
	
        @Override
	public void run(){
			if ( myRecord.FileSize > 0 )
                        StartDownload();
                        else{
                            error();
                        }
		}
       
         void stateChanged() {
        setChanged();
        notifyObservers();
    }
	 public void pause()
                   {      
                       if(SubDownloadsArray==null)return;
                         this.status=PAUSED;
                         for(int SubId=0;SubId<this.SubDownloadsArray.length;SubId++)
                               {
                                            
                              this.SubDownloadsArray[SubId].pause();
                                
                                 myRecord.SubDownloadsArray[SubId]=this.SubDownloadsArray[SubId].getRecord();
                                 
                               }
                         calcBytesDownloaded();
                         myRecord.BytesDownloaded=BytesDownloaded;
                         myRecord.status=status;
                         myRecord.AssociatedTime=System.currentTimeMillis();
                         myRecord.ScheduledDate=new Date();
                              stateChanged();
                   }
                   
                   public void resume()
                   {
                                        BytesDownloaded=myRecord.BytesDownloaded;
                                        BufferSize=DashboardController.getBufferSize();
                                        SetDownloadFromRecord();
                                            for(int SubId=0;SubId<this.SubDownloadsArray.length;SubId++)
                                        {
                                       
                                            StartTime=System.currentTimeMillis();  
                                        this.SubDownloadsArray[SubId].start();
                                        }
                                        myRecord.status=this.status=DOWNLOADING;
                                        stateChanged();
                     }
                   
                   public void cancel()
                   {
                                       this.status=CANCELLED;
                                       if(SubDownloadsArray==null)return;
                         for(int SubId=0;SubId<this.SubDownloadsArray.length;SubId++)
                               {
                                           this.SubDownloadsArray[SubId].stop();
                              
                                 myRecord.SubDownloadsArray[SubId]=this.SubDownloadsArray[SubId].getRecord();
                               }
                         calcBytesDownloaded();
                         myRecord.BytesDownloaded=BytesDownloaded;
                         myRecord.status=status;
                         myRecord.AssociatedTime=System.currentTimeMillis();
                         myRecord.ScheduledDate=new Date();
                              stateChanged();
                              DeleteSubDownFiles();
                   }
         
    // Mark this download as having an error.
    void error() {
        status=myRecord.status = ERROR;
        System.out.println("error");
        int SubDownid;
         myRecord.AssociatedTime=System.currentTimeMillis();
                         myRecord.ScheduledDate=new Date();
                         myRecord.FileSize=-1;
        stateChanged();
        if(SubDownloadsArray==null)return;
         DeleteSubDownFiles();
               
        DashboardController.UpdateMyRecord(myRecord);
        stateChanged();
    }
    private void DeleteSubDownFiles()
    {
        for(SubDownloadRecord record:myRecord.SubDownloadsArray)
      {//deleting subdownloads
          try{
          new File(getSubDownFileLocation()+File.separator+record.SubDownloadFile).delete();
          }catch(Exception e)
          {System.out.println("deletion error"); error(); return;}
      }
    }
    private void DownloadCompleted() {
       FormFileFromSubdownloads();
       DeleteSubDownFiles();
       myRecord.BytesDownloaded=myRecord.FileSize;
       myRecord.status=status=COMPLETE;
       DashboardController.UpdateMyRecord(myRecord);
       stateChanged();
    }
    public boolean FormFileFromSubdownloads()
	{
	 SubDownloadFileList FileList= new SubDownloadFileList();

       try {
       	File f = new File(getFilePath());
        if(f==null){error(); return false;}
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
			if(raf==null){error(); return false;}
         SequenceInputStream CombFileStream = new SequenceInputStream( FileList );
         int c;
         byte buffer[] = new byte[1024*BufferSize];
         
         while ( ( c = CombFileStream.read(buffer)) != -1 ) {
           raf.write(buffer,0,c);
         }
         raf.setLength(myRecord.FileSize);
         CombFileStream.close();
         raf.close();
       } catch ( IOException e ) {
        
           System.err.println( "Concatenate: " + e );  error(); return false;
      }

	return true;
	}
	
    private class SubDownloadFileList implements Enumeration {

    private String[] FileList;
    private int currentStream = 0;

    public SubDownloadFileList() {
     int i;
     FileList=new String[Download.this.myRecord.TotalConnections];
     for(i=0;i<Download.this.myRecord.TotalConnections;i++)
     {
        FileList[i]=Download.this.myRecord.SubDownloadsArray[i].SubDownloadFile;
    }
    }
    @Override
    public boolean hasMoreElements() {
        return currentStream < FileList.length;
    }

    @Override
    public Object nextElement() {
        InputStream in = null;

        if (!hasMoreElements())
            throw new NoSuchElementException("No more files.");
        else {
            String nextElement = FileList[currentStream];
            currentStream++;
            try {
                in = new FileInputStream(Download.this.getSubDownFileLocation()+File.separator+nextElement);
            } catch (FileNotFoundException e) {
                System.out.println("ListOfFiles: Can't open " + nextElement);
                Download.this.error();
            }
        }
        return in;
    }
}
}
