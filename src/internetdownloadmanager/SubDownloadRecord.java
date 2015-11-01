/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

/**
 *
 * @author somil
 */
public class SubDownloadRecord {
    public int SubDownloadId;
    public String SubDownloadFile;
    public long BytesDownloaded;
    public long FileStartLocation;
    public long FileEndLocation;
    public boolean complete;
}
