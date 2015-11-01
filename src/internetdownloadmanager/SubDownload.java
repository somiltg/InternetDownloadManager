/* 
Exception Handlers
*/
package internetdownloadmanager;
import java.net.*;
import java.io.*;


public class SubDownload extends Thread{
	private String FileLoc;
	private long BytesDownloaded;
	private byte Buffer[];
	private SubDownloadRecord myRecord;
        private boolean paused;
	Download parent;
	public SubDownload(Download parent,String FileLoc,SubDownloadRecord record,int BufferSize){
		
		this.FileLoc=FileLoc;
		Buffer = new byte[1024 * BufferSize];	
		BytesDownloaded=record.BytesDownloaded;
                myRecord=record;
                this.parent=parent;
                paused=false;
		}

    public long getBytesDownloaded() {
        return BytesDownloaded;
    }
    public void pause()
    {
        paused=true;
    }
        @Override
	public void run(){
		
		try{
           //  DashboardController.setProxySettings();
		paused=false;
			URL url = new URL(FileLoc);
			URLConnection uc = url.openConnection();
			BufferedInputStream instream;
			uc.setRequestProperty("Range","bytes=" + (myRecord.FileStartLocation+BytesDownloaded) + "-"+ myRecord.FileEndLocation);
			instream = new BufferedInputStream(uc.getInputStream());
			int li_bytesRead;
                        String filepath=parent.getSubDownFileLocation()+File.separator+myRecord.SubDownloadFile;    
                        File f=new File(filepath);
                      if(!f.exists())f.createNewFile();
                      if(f==null){parent.error();return;}
                        RandomAccessFile raf = new RandomAccessFile(f, "rw");
                        if(raf==null){parent.error(); return;}
			raf.seek(BytesDownloaded);
                        while(!paused && BytesDownloaded < (myRecord.FileEndLocation - myRecord.FileStartLocation))
			{
				li_bytesRead = instream.read(Buffer);
				raf.write(Buffer,0,li_bytesRead);
				BytesDownloaded = BytesDownloaded + li_bytesRead;
                                parent.stateChanged();
                                
			}
                         
                        if(!paused)
                        {
                        //truncating the file to original length
                        raf.setLength(myRecord.FileEndLocation-myRecord.FileStartLocation+1);
                       BytesDownloaded=myRecord.FileEndLocation-myRecord.FileStartLocation+1;
                        myRecord.complete=true;	
                        }
                        else{
                            raf.setLength(BytesDownloaded);
                        }
                    //System.out.println(myRecord.SubDownloadFile+"BytesDownloaded: " + BytesDownloaded+" "+myRecord.FileStartLocation+" "+myRecord.FileEndLocation+" "+raf.length());	
                        instream.close();
			raf.close();
			//Finished Downloading
                        myRecord.BytesDownloaded=BytesDownloaded;
                        if(!paused)parent.NotifySubDownloadComplete();
		}catch(Exception e){
                    System.out.println(e);
                    System.out.println("hi"+myRecord.SubDownloadId);parent.error();
                }
        }

    /**
     *
     * @return
     */
    public SubDownloadRecord getRecord()
    {
        myRecord.BytesDownloaded=BytesDownloaded;
        return myRecord;
    }
		
	
}