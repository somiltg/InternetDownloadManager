/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetdownloadmanager;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author somil
 */
public class DownloadTable extends AbstractTableModel
        implements Observer {
    
private static final String[] columnNames = {"URL","Filepath","Downloaded","Total Size","Download Speed",
    "Progress", "Time left","Status"};  
    private static final Class[] columnClasses = {String.class,
    String.class,String.class,String.class,String.class, JProgressBar.class, String.class,String.class};
     
    // The table's list of downloads.
    private ArrayList<Download> downloadList = new ArrayList<Download>();
    public void addDownload(Download download) {
         
        // Register to be notified when the download changes.
        download.addObserver(this);
        downloadList.add(download);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
     
    // Get a download for the specified row.
    public Download getDownload(int row) {
        return (Download) downloadList.get(row);
    }
     
    // Remove a download from the list.
    public void clearDownload(int row) {
        downloadList.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
     
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    public int getRowCount() {
        return downloadList.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Download download;
    download = (Download) downloadList.get(rowIndex);
        switch (columnIndex) {
            case 0: // URL
                return download.getUrl(); 
            case 1: return download.getFilePath();
            case 2:    return download.getBytesDownloaded();
            case 3: return download.getSize();
            case 4:return download.getDownloadSpeed();
            case 5: return download.getProgress();
            case 6:return download.getTimeForDownload();
            case 7: // Status
                return DashboardController.STATUSES[download.getStatus()];
        }
        return "";
    }
    
    @Override
    public void update(Observable o, Object arg) {
        int index=downloadList.indexOf(o);
        fireTableRowsUpdated(index, index);
    }
    
}
