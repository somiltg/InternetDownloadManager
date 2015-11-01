/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Dashboard.java
 *
 * Created on Sep 15, 2015, 7:34:15 PM
 */
package internetdownloadmanager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author somil
 */
public class Dashboard extends javax.swing.JFrame implements Observer{

    private DownloadTable tableModel;
    private  DefaultTableModel ScheduleModel;
    private Download SelectedDownload;
    private Date ChosenDate;
     // Flag for whether or not table selection is being cleared.
    private boolean clearing;
    static boolean Hostname; 
    DashboardController controller;// for connecting to the controller
    /** Creates new form Dashboard */
    public Dashboard(DashboardController controller) {
        tableModel = new DownloadTable();
        String columnNames[]={"URL","Filepath","Scheduled Time","Scheduled Date","Status"};
       ScheduleModel=new  DefaultTableModel(columnNames,3);
        //jDownloadsTable.
        initComponents();
        //arranging the screen 
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int jFrameh=this.getSize().height;
        int jFramew=this.getSize().width;
        setLocation((dim.width-jFramew)/2,(dim.height-jFrameh)/2);
       jChangesSuccessfulLabel.setVisible(false);
        this.controller=controller;
        jRecommendedLabel1.setVisible(false);
        setTable(jDownloadsTable,0);
        setTable(jScheduledTable, 1);
        //Set Settings
       jConnectionSpinner.setValue(DashboardController.getTotalConnection());
       jDestinationField.setText(DashboardController.getDestinationFolder());
       jBufferField.setText(DashboardController.getBufferSize()+"");
       jProxyCheckBox.setSelected(!(DashboardController.getProxyAddress().isEmpty()));
        ProxySet(!(DashboardController.getProxyAddress().isEmpty()));
        while(DashboardController.getDestinationFolder().isEmpty())
        {
             JOptionPane.showMessageDialog(this,"Destination folder not set. Compulsory to fill this field before downloading", "Required",
                    JOptionPane.INFORMATION_MESSAGE);
             jDestinationButton.doClick();
             DashboardController.setDestinationFolder(jDestinationField.getText());
        } 
        jDateChooser.setMinSelectableDate(new Date());
       jDateChooser.setDateFormatString("dd-MM-yyyy");
        jDateChooser.setMaxSelectableDate(new Date(new Date().getYear()+1, new Date().getMonth(),new Date().getDay()));
JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser.getDateEditor();
editor.setEditable(false); editor.setAlignmentX(CENTER_ALIGNMENT);
jTimeChooser.setFont(new Font("SANS SERIF", Font.BOLD, 18));
jTimeChooser.setAlignmentX(CENTER_ALIGNMENT);
//Scheduled Tab
jTimeDateLabel.setVisible(false);
        Component[] components = jDateChooser.getComponents();
        for(Component comp:components)
        {
            comp.addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseMoved(MouseEvent e) {
                   jTimeDateLabel.setText("Enter the date by clicking the calender button"); 
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                  jTimeDateLabel.setText("Enter the date by clicking the calender button");  //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        components=jTimeChooser.getComponents();
        for(Component comp:components)
        {
            comp.addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseMoved(MouseEvent e) {
                  jTimeDateLabel.setText("Enter the time by dragging the mouse up and down for increment/decrement after highlighting the specific column");
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                 jTimeDateLabel.setText("Enter the time by dragging the mouse up and down for increment/decrement after highlighting the specific column");
                }
            });
        }
        jTimeChooser.setTime(new Date());
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDownloadTable = new javax.swing.JPanel();
        jURLField = new javax.swing.JTextField();
        jPauseButton = new javax.swing.JButton();
        jDownloadButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jClearButton = new javax.swing.JButton();
        jFileName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDownloadsTable = new javax.swing.JTable(tableModel);
        jScheduler = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScheduledTable = new javax.swing.JTable(ScheduleModel);
        jSchedulingURLField = new javax.swing.JTextField();
        jSchedulingFileName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jTimeChooser = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jTimeDate = new javax.swing.JLabel();
        jTimeToggleButton = new javax.swing.JToggleButton();
        jScheduleDownloadButton = new javax.swing.JButton();
        jTimeDateLabel = new javax.swing.JLabel();
        jSettings = new javax.swing.JPanel();
        jProxyLabel1 = new javax.swing.JLabel();
        jRecommendedLabel1 = new javax.swing.JLabel();
        jConnectionSpinner = new javax.swing.JSpinner();
        jConnectionsLabel1 = new javax.swing.JLabel();
        jDestinationField = new javax.swing.JTextField();
        jDestinationButton = new javax.swing.JButton();
        jConnectionsLabel2 = new javax.swing.JLabel();
        jProxyField = new javax.swing.JTextField();
        jProxyLabel3 = new javax.swing.JLabel();
        jUsernameField = new javax.swing.JTextField();
        jProxyLabel4 = new javax.swing.JLabel();
        jBufferField = new javax.swing.JTextField();
        jApplyButton = new javax.swing.JButton();
        jCancelChangesButton = new javax.swing.JButton();
        jPortField = new javax.swing.JTextField();
        jProxyLabel2 = new javax.swing.JLabel();
        jConnectionsLabel6 = new javax.swing.JLabel();
        jProxyCheckBox = new javax.swing.JCheckBox();
        jPasswordField = new javax.swing.JPasswordField();
        jProxyLabel5 = new javax.swing.JLabel();
        jConnectionsLabel9 = new javax.swing.JLabel();
        jProxyLabel6 = new javax.swing.JLabel();
        jClearChangesButton = new javax.swing.JButton();
        jChangesSuccessfulLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Internet Download Manager");
        setBackground(new java.awt.Color(237, 239, 181));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(176, 224, 222));
        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jTabbedPane1.setForeground(new java.awt.Color(58, 152, 233));
        jTabbedPane1.setToolTipText("DOWNLOAD MANAGER");
        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        jDownloadTable.setBackground(new java.awt.Color(217, 230, 241));
        jDownloadTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDownloadTable.setForeground(new java.awt.Color(67, 254, 242));
        jDownloadTable.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jURLField.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jURLField.setText("Enter the URL of the source file here");
        jURLField.setToolTipText("");
        jURLField.setDoubleBuffered(true);
        jURLField.setPreferredSize(new java.awt.Dimension(700, 31));
        jURLField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jURLFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jURLFieldFocusLost(evt);
            }
        });

        jPauseButton.setBackground(new java.awt.Color(185, 164, 216));
        jPauseButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jPauseButton.setText("Pause");
        jPauseButton.setActionCommand("jDownloadButton");
        jPauseButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPauseButtonActionPerformed(evt);
            }
        });

        jDownloadButton.setBackground(new java.awt.Color(185, 164, 216));
        jDownloadButton.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jDownloadButton.setText("Download");
        jDownloadButton.setActionCommand("jDownloadButton");
        jDownloadButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDownloadButtonActionPerformed(evt);
            }
        });

        jCancelButton.setBackground(new java.awt.Color(185, 164, 216));
        jCancelButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jCancelButton.setText("Cancel");
        jCancelButton.setActionCommand("jDownloadButton");
        jCancelButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jClearButton.setBackground(new java.awt.Color(185, 164, 216));
        jClearButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jClearButton.setText("Clear");
        jClearButton.setActionCommand("jDownloadButton");
        jClearButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearButtonActionPerformed(evt);
            }
        });

        jFileName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jFileName.setText("Enter a name for the downloaded file");
        jFileName.setToolTipText("");
        jFileName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFileNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFileNameFocusLost(evt);
            }
        });

        jDownloadsTable.setBackground(new java.awt.Color(239, 251, 146));
        jDownloadsTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDownloadsTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jDownloadsTable.setModel(tableModel);
        jDownloadsTable.setToolTipText("Table for active downloads");
        jDownloadsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jDownloadsTable);

        org.jdesktop.layout.GroupLayout jDownloadTableLayout = new org.jdesktop.layout.GroupLayout(jDownloadTable);
        jDownloadTable.setLayout(jDownloadTableLayout);
        jDownloadTableLayout.setHorizontalGroup(
            jDownloadTableLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDownloadTableLayout.createSequentialGroup()
                .addContainerGap()
                .add(jDownloadTableLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jFileName)
                    .add(jURLField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(34, 34, 34)
                .add(jDownloadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jDownloadTableLayout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 944, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(108, 108, 108))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jDownloadTableLayout.createSequentialGroup()
                .add(44, 44, 44)
                .add(jCancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPauseButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(324, 324, 324)
                .add(jClearButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(128, 128, 128))
        );
        jDownloadTableLayout.setVerticalGroup(
            jDownloadTableLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDownloadTableLayout.createSequentialGroup()
                .add(jDownloadTableLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jDownloadTableLayout.createSequentialGroup()
                        .add(38, 38, 38)
                        .add(jURLField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(22, 22, 22)
                        .add(jFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(30, 30, 30))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jDownloadTableLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jDownloadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(52, 52, 52)))
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34)
                .add(jDownloadTableLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jClearButton)
                    .add(jPauseButton)
                    .add(jCancelButton))
                .addContainerGap(3235, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Downloading  table", jDownloadTable);
        jDownloadTable.getAccessibleContext().setAccessibleParent(jTabbedPane1);

        jScheduler.setBackground(new java.awt.Color(217, 230, 241));
        jScheduler.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScheduler.setForeground(new java.awt.Color(67, 254, 242));
        jScheduler.setAutoscrolls(true);
        jScheduler.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jScheduledTable.setBackground(new java.awt.Color(239, 251, 146));
        jScheduledTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScheduledTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jScheduledTable.setModel(ScheduleModel);
        jScheduledTable.setToolTipText("Table for scheduled downloads");
        jScheduledTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScheduledTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jScheduledTable);
        jScheduledTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jSchedulingURLField.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jSchedulingURLField.setText("Enter the URL of the source file here");
        jSchedulingURLField.setToolTipText("");
        jSchedulingURLField.setDoubleBuffered(true);
        jSchedulingURLField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSchedulingURLFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSchedulingURLFieldFocusLost(evt);
            }
        });

        jSchedulingFileName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jSchedulingFileName.setText("Enter a name for the downloaded file");
        jSchedulingFileName.setToolTipText("");
        jSchedulingFileName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSchedulingFileNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSchedulingFileNameFocusLost(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(222, 196, 128));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(253, 50, 2));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("If the date passes due to close of software, the scheduled process will start as soon as software reopens.");

        jLabel3.setBackground(new java.awt.Color(222, 196, 128));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(253, 50, 2));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("NOTE: Download can be scheduled to be downloaded at a later date/time.");

        jDateChooser.setBackground(new java.awt.Color(249, 219, 189));
        jDateChooser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDateChooser.setDate(new Date());
        jDateChooser.setDateFormatString(" dd/MM/ yyyy");
        jDateChooser.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jDateChooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDateChooserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDateChooserMouseExited(evt);
            }
        });
        jDateChooser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateChooserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDateChooserFocusLost(evt);
            }
        });

        jTimeChooser.setBackground(new java.awt.Color(253, 197, 112));
        jTimeChooser.setForeground(new java.awt.Color(6, 1, 50));
        jTimeChooser.setToolTipText("");
        jTimeChooser.setFont(new java.awt.Font("TakaoPGothic", 1, 15)); // NOI18N
        jTimeChooser.setHighlightingColor(new java.awt.Color(226, 207, 0));
        jTimeChooser.setTime(new Date());
        jTimeChooser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTimeChooserFocusGained(evt);
            }
        });

        jTimeDate.setBackground(new java.awt.Color(220, 202, 2));
        jTimeDate.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        jTimeDate.setForeground(new java.awt.Color(94, 78, 232));
        jTimeDate.setText("HH:MM:SS");
        jTimeDate.setFocusable(false);

        jTimeToggleButton.setBackground(new java.awt.Color(205, 209, 211));
        jTimeToggleButton.setFont(new java.awt.Font("Ubuntu Light", 1, 15)); // NOI18N
        jTimeToggleButton.setForeground(new java.awt.Color(14, 132, 4));
        jTimeToggleButton.setText("Fix time and date");
        jTimeToggleButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTimeToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTimeToggleButtonStateChanged(evt);
            }
        });

        jScheduleDownloadButton.setBackground(new java.awt.Color(185, 164, 216));
        jScheduleDownloadButton.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jScheduleDownloadButton.setText("Schedule Download");
        jScheduleDownloadButton.setActionCommand("jDownloadButton");
        jScheduleDownloadButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScheduleDownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScheduleDownloadButtonActionPerformed(evt);
            }
        });

        jTimeDateLabel.setBackground(new java.awt.Color(220, 202, 2));
        jTimeDateLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTimeDateLabel.setForeground(new java.awt.Color(121, 14, 1));
        jTimeDateLabel.setText("Scroll the hours, minutes and seconds spinner after highlighting");
        jTimeDateLabel.setFocusable(false);

        org.jdesktop.layout.GroupLayout jSchedulerLayout = new org.jdesktop.layout.GroupLayout(jScheduler);
        jScheduler.setLayout(jSchedulerLayout);
        jSchedulerLayout.setHorizontalGroup(
            jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSchedulerLayout.createSequentialGroup()
                .add(jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSchedulerLayout.createSequentialGroup()
                        .add(229, 229, 229)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 502, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSchedulerLayout.createSequentialGroup()
                        .add(142, 142, 142)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 685, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSchedulerLayout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jSchedulerLayout.createSequentialGroup()
                                .add(jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jSchedulingURLField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                                    .add(jSchedulingFileName))
                                .add(50, 50, 50)
                                .add(jScheduleDownloadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jSchedulerLayout.createSequentialGroup()
                                .add(jDateChooser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(60, 60, 60)
                                .add(jTimeChooser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTimeDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(51, 51, 51)
                                .add(jTimeToggleButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jScrollPane2)))
                    .add(jSchedulerLayout.createSequentialGroup()
                        .add(77, 77, 77)
                        .add(jTimeDateLabel)))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        jSchedulerLayout.setVerticalGroup(
            jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSchedulerLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSchedulerLayout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSchedulingURLField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSchedulingFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jSchedulerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jTimeChooser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jDateChooser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTimeDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jTimeToggleButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTimeDateLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSchedulerLayout.createSequentialGroup()
                        .add(73, 73, 73)
                        .add(jScheduleDownloadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3200, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Schedule Downloads", jScheduler);

        jSettings.setBackground(new java.awt.Color(217, 230, 241));
        jSettings.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jSettings.setForeground(new java.awt.Color(67, 254, 242));
        jSettings.setAutoscrolls(true);
        jSettings.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jSettings.setPreferredSize(new java.awt.Dimension(1313, 2500));

        jProxyLabel1.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jProxyLabel1.setForeground(new java.awt.Color(24, 0, 161));
        jProxyLabel1.setText("Proxy Address/Hostname:");
        jProxyLabel1.setFocusable(false);
        jProxyLabel1.setRequestFocusEnabled(false);

        jRecommendedLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jRecommendedLabel1.setForeground(new java.awt.Color(246, 17, 17));
        jRecommendedLabel1.setText("Recommended connections less that equal to 8");
        jRecommendedLabel1.setToolTipText("");
        jRecommendedLabel1.setFocusable(false);
        jRecommendedLabel1.setRequestFocusEnabled(false);

        jConnectionSpinner.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jConnectionSpinner.setModel(new javax.swing.SpinnerNumberModel(8, 1, 14, 1));
        jConnectionSpinner.setToolTipText("");
        jConnectionSpinner.setDoubleBuffered(true);
        jConnectionSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jConnectionSpinnerStateChanged(evt);
            }
        });

        jConnectionsLabel1.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jConnectionsLabel1.setForeground(new java.awt.Color(24, 0, 161));
        jConnectionsLabel1.setText("Total number of connections for each file");
        jConnectionsLabel1.setFocusable(false);
        jConnectionsLabel1.setRequestFocusEnabled(false);

        jDestinationField.setFont(new java.awt.Font("TlwgTypewriter", 0, 15)); // NOI18N
        jDestinationField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDestinationFieldFocusLost(evt);
            }
        });

        jDestinationButton.setBackground(new java.awt.Color(15, 25, 50));
        jDestinationButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jDestinationButton.setForeground(new java.awt.Color(244, 236, 236));
        jDestinationButton.setText("Choose Folder...");
        jDestinationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDestinationButtonActionPerformed(evt);
            }
        });

        jConnectionsLabel2.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jConnectionsLabel2.setForeground(new java.awt.Color(24, 0, 161));
        jConnectionsLabel2.setText("Destination folder for downloaded files");
        jConnectionsLabel2.setFocusable(false);
        jConnectionsLabel2.setRequestFocusEnabled(false);

        jProxyField.setFont(new java.awt.Font("TlwgTypewriter", 0, 15)); // NOI18N
        jProxyField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jProxyFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jProxyFieldFocusLost(evt);
            }
        });

        jProxyLabel3.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jProxyLabel3.setForeground(new java.awt.Color(24, 0, 161));
        jProxyLabel3.setText("User for Authentication:");
        jProxyLabel3.setFocusable(false);
        jProxyLabel3.setRequestFocusEnabled(false);

        jUsernameField.setFont(new java.awt.Font("TlwgTypewriter", 0, 15)); // NOI18N
        jUsernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUsernameFieldFocusLost(evt);
            }
        });

        jProxyLabel4.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jProxyLabel4.setForeground(new java.awt.Color(24, 0, 161));
        jProxyLabel4.setText("Authentication Password:");
        jProxyLabel4.setFocusable(false);
        jProxyLabel4.setRequestFocusEnabled(false);

        jBufferField.setFont(new java.awt.Font("TlwgTypewriter", 0, 15)); // NOI18N
        jBufferField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBufferFieldFocusLost(evt);
            }
        });

        jApplyButton.setBackground(new java.awt.Color(185, 164, 216));
        jApplyButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jApplyButton.setText("Apply Changes");
        jApplyButton.setActionCommand("jDownloadButton");
        jApplyButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jApplyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jApplyButtonActionPerformed(evt);
            }
        });

        jCancelChangesButton.setBackground(new java.awt.Color(185, 164, 216));
        jCancelChangesButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jCancelChangesButton.setText("Cancel Changes");
        jCancelChangesButton.setActionCommand("jDownloadButton");
        jCancelChangesButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCancelChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelChangesButtonActionPerformed(evt);
            }
        });

        jPortField.setFont(new java.awt.Font("TlwgTypewriter", 0, 15)); // NOI18N
        jPortField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPortFieldFocusLost(evt);
            }
        });

        jProxyLabel2.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jProxyLabel2.setForeground(new java.awt.Color(24, 0, 161));
        jProxyLabel2.setText("Proxy Port:");
        jProxyLabel2.setFocusable(false);
        jProxyLabel2.setRequestFocusEnabled(false);

        jConnectionsLabel6.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        jConnectionsLabel6.setForeground(new java.awt.Color(24, 0, 161));
        jConnectionsLabel6.setText("Buffer Size");
        jConnectionsLabel6.setFocusable(false);
        jConnectionsLabel6.setRequestFocusEnabled(false);

        jProxyCheckBox.setBackground(new java.awt.Color(243, 231, 165));
        jProxyCheckBox.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jProxyCheckBox.setForeground(new java.awt.Color(37, 24, 24));
        jProxyCheckBox.setText("Proxy Settings");
        jProxyCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jProxyCheckBoxStateChanged(evt);
            }
        });

        jPasswordField.setBackground(new java.awt.Color(255, 233, 200));
        jPasswordField.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jPasswordField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPasswordField.setText("jPasswordField");
        jPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFieldFocusLost(evt);
            }
        });

        jProxyLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jProxyLabel5.setForeground(new java.awt.Color(161, 17, 0));
        jProxyLabel5.setText("IPv6 not supported");
        jProxyLabel5.setFocusable(false);
        jProxyLabel5.setRequestFocusEnabled(false);

        jConnectionsLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jConnectionsLabel9.setForeground(new java.awt.Color(161, 17, 0));
        jConnectionsLabel9.setText("in KB, Recommended: <10 KB");
        jConnectionsLabel9.setFocusable(false);
        jConnectionsLabel9.setRequestFocusEnabled(false);

        jProxyLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jProxyLabel6.setForeground(new java.awt.Color(161, 17, 0));
        jProxyLabel6.setText("Leave empty in case of no authentication");
        jProxyLabel6.setFocusable(false);
        jProxyLabel6.setRequestFocusEnabled(false);

        jClearChangesButton.setBackground(new java.awt.Color(185, 164, 216));
        jClearChangesButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jClearChangesButton.setText("Clear Changes");
        jClearChangesButton.setActionCommand("jDownloadButton");
        jClearChangesButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jClearChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearChangesButtonActionPerformed(evt);
            }
        });

        jChangesSuccessfulLabel.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        jChangesSuccessfulLabel.setForeground(new java.awt.Color(8, 8, 8));
        jChangesSuccessfulLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChangesSuccessfulLabel.setText("Changes Applied Successfully");
        jChangesSuccessfulLabel.setFocusable(false);

        jLabel1.setBackground(new java.awt.Color(202, 253, 194));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 160, 35));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Next>");
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jSettingsLayout = new org.jdesktop.layout.GroupLayout(jSettings);
        jSettings.setLayout(jSettingsLayout);
        jSettingsLayout.setHorizontalGroup(
            jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSettingsLayout.createSequentialGroup()
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jSettingsLayout.createSequentialGroup()
                        .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSettingsLayout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(jClearChangesButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 184, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(38, 38, 38))
                            .add(jSettingsLayout.createSequentialGroup()
                                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jSettingsLayout.createSequentialGroup()
                                        .add(94, 94, 94)
                                        .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jProxyLabel3)
                                            .add(jProxyLabel4)))
                                    .add(jSettingsLayout.createSequentialGroup()
                                        .add(149, 149, 149)
                                        .add(jConnectionsLabel6))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSettingsLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .add(jProxyLabel1)
                                        .add(13, 13, 13)))
                                .add(3, 3, 3)))
                        .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSettingsLayout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jSettingsLayout.createSequentialGroup()
                                        .add(jConnectionsLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jSettingsLayout.createSequentialGroup()
                                                .add(107, 107, 107)
                                                .add(jPortField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(jSettingsLayout.createSequentialGroup()
                                                .add(69, 69, 69)
                                                .add(jBufferField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(jSettingsLayout.createSequentialGroup()
                                        .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jSettingsLayout.createSequentialGroup()
                                                .add(jProxyLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                            .add(jSettingsLayout.createSequentialGroup()
                                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(55, 55, 55)))
                                        .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jUsernameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jProxyField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                .add(38, 38, 38))
                            .add(jSettingsLayout.createSequentialGroup()
                                .add(111, 111, 111)
                                .add(jCancelChangesButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 184, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(120, 120, 120)
                                .add(jApplyButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jSettingsLayout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jProxyLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 339, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(231, 231, 231))
            .add(jSettingsLayout.createSequentialGroup()
                .add(56, 56, 56)
                .add(jConnectionsLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jConnectionSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(352, 352, 352))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSettingsLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jRecommendedLabel1)
                .add(332, 332, 332))
            .add(jSettingsLayout.createSequentialGroup()
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSettingsLayout.createSequentialGroup()
                        .add(152, 152, 152)
                        .add(jProxyLabel2))
                    .add(jSettingsLayout.createSequentialGroup()
                        .add(45, 45, 45)
                        .add(jConnectionsLabel2)
                        .add(132, 132, 132)
                        .add(jDestinationField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 434, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSettingsLayout.createSequentialGroup()
                        .add(369, 369, 369)
                        .add(jProxyCheckBox)
                        .add(217, 217, 217)
                        .add(jDestinationButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSettingsLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jChangesSuccessfulLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jSettingsLayout.setVerticalGroup(
            jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSettingsLayout.createSequentialGroup()
                .add(jChangesSuccessfulLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jConnectionsLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jConnectionSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRecommendedLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jDestinationField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jConnectionsLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jDestinationButton)
                    .add(jProxyCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jProxyField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jProxyLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .add(jProxyLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jProxyLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .add(jPortField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jUsernameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jProxyLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jProxyLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jProxyLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .add(jPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jConnectionsLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jBufferField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jConnectionsLabel9)))
                .add(18, 18, 18)
                .add(jSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jCancelChangesButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jApplyButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jClearChangesButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(3189, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Settings", jSettings);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
  private void setTable(JTable jTable,final int i)
  {
      jTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      if(i==0){
          jTable.getSelectionModel().addListSelectionListener(new
                ListSelectionListener() {
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
               tableSelectionChanged();
            }
        });
      }
      else 
      {
          jTable.getSelectionModel().addListSelectionListener(new
               ListSelectionListener() {

              @Override
              public void valueChanged(ListSelectionEvent e) {
                 
              }
          });
      }
       //HEADER 
       JTableHeader header = jTable.getTableHeader();
      header.setBackground(Color.blue);
      header.setForeground(Color.white);
      //setting column width
        jTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(120);
       if(i==0) jTable.getColumnModel().getColumn(5).setPreferredWidth(120);
         jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
       if(i==0) jTable.getColumnModel().getColumn(4).setPreferredWidth(110);
      Font font=new Font("Arial Black", Font.PLAIN,13 );
      header.setFont(font);
      ProgressRenderer renderer=null;
         if(i==0)
         {renderer = new ProgressRenderer(0, 100);
        renderer.setStringPainted(true); // show progress text
     jTable.setDefaultRenderer(JProgressBar.class, renderer);
         }
     CenterRenderer renderer1=new CenterRenderer();
     jTable.setDefaultRenderer(String.class, renderer1);
     
          // Set table's row height large enough to fit JProgressBar.
        if(i==0)jTable.setRowHeight(
                (int) renderer.getPreferredSize().getHeight());
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
  }
    private void disableService(int i)
    {
        switch(i)
        {
            case 1:jLabel1.setEnabled(false);
                jProxyField.setEnabled(false);
                        jProxyLabel1.setEnabled(false);
                jProxyLabel5.setEnabled(false);
            case 2:jPortField.setEnabled(false);
                      jProxyLabel2.setEnabled(false);
            case 3: jUsernameField.setText(null);
                    jUsernameField.setEnabled(false);
                    jProxyLabel3.setEnabled(false);
                  jProxyLabel6.setEnabled(false);
            case 4:jPasswordField.setText(null);
                    jPasswordField.setEnabled(false);
                   jProxyLabel4.setEnabled(false);
 
        }
    }
    private void enableService(int i)
    {
        switch(i)
        {
            case 1:jLabel1.setEnabled(true);
                jProxyField.setEnabled(true);
               jProxyField.requestFocus();
                        jProxyLabel1.setEnabled(true);
                jProxyLabel5.setEnabled(true);
                break;
            case 2:jPortField.setEnabled(true);
                        jPortField.requestFocus();
                      jProxyLabel2.setEnabled(true);
                break;
            case 3: jUsernameField.setText(null);
                    jUsernameField.setEnabled(true);
                    jUsernameField.requestFocus();
                    jProxyLabel6.setEnabled(true);
                    jProxyLabel3.setEnabled(true);
                break;
            case 4:jPasswordField.setText(null);
                    jPasswordField.setEnabled(true);
                    jPasswordField.requestFocus();
                   jProxyLabel4.setEnabled(true);
 
        }
    }
    private void ProxySet(boolean status)
    {
        int s;
       if(status)
       {
           enableService(1);
           jProxyField.setText(DashboardController.getProxyAddress());
        if(jProxyField.getText().isEmpty()){ disableService(2); return;}
           enableService(2);
        jPortField.setText((s=DashboardController.getProxyPort())>0?s+"":"");
        if(jPortField.getText().isEmpty()){ disableService(3); return;}
           enableService(3);
              jUsernameField.setText(DashboardController.getUserName());
          if(jUsernameField.getText().isEmpty()){ disableService(4); return;}
         enableService(4);
          try {
           jPasswordField.setText(
              SettingsRecord.decrypt(
              DashboardController.getPassword()
                      )
                      );
          } catch (Exception ex) {
          System.out.println("Error in Encryption");
        }
    }
        else
        {
            disableService(1);
            jProxyField.setText("");
       jPortField.setText("");
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        controller.SaveSettingsonClose();
        for(int index=0;index<tableModel.getRowCount();index++)
        {
            Download download=tableModel.getDownload(index);
           if(download.getStatus()==0)controller.actionPause(download);  
        }
        controller.UpdateModelRecordsonClose();
    }//GEN-LAST:event_formWindowClosing

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        jLabel1.requestFocus();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jClearChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearChangesButtonActionPerformed
        jChangesSuccessfulLabel.setForeground(Color.red);
        jChangesSuccessfulLabel.setText("Crucial Fields empty! Until applied, old settings would work...");
        jConnectionSpinner.setValue(8);
        jDestinationField.setText(null);
        jBufferField.setText(null);
        ProxySet(false);
        jChangesSuccessfulLabel.setVisible(true);
        new Display(this).start();
    }//GEN-LAST:event_jClearChangesButtonActionPerformed

    private void jPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldFocusLost

    private void jProxyCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jProxyCheckBoxStateChanged
        ProxySet(jProxyCheckBox.isSelected());
    }//GEN-LAST:event_jProxyCheckBoxStateChanged

    private void jPortFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPortFieldFocusLost

        if(jPortField.getText().isEmpty())
        {disableService(3);
            return;
        }

        int port;
        try
        {
            port=Integer.parseInt(jPortField.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Port has to be an integer between 0-65536!!", "Error",
                JOptionPane.ERROR_MESSAGE);
            jPortField.selectAll();jPortField.requestFocus(); return;
        }
        if(port>65536 || port<=0){
            JOptionPane.showMessageDialog(this,"Port has to be an integer between 0-65536!!", "Error",
                JOptionPane.ERROR_MESSAGE);
            jPortField.selectAll();jPortField.requestFocus(); return;  }
        enableService(3);
    }//GEN-LAST:event_jPortFieldFocusLost

    private void jCancelChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelChangesButtonActionPerformed
        jDestinationField.setText(DashboardController.getDestinationFolder());
        jBufferField.setText(""+DashboardController.getBufferSize());
        jConnectionSpinner.setValue(DashboardController.getTotalConnection());
        ProxySet(!DashboardController.getProxyAddress().isEmpty());
    }//GEN-LAST:event_jCancelChangesButtonActionPerformed

    private void jApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jApplyButtonActionPerformed
        if(jDestinationField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Destination Folder not given", "Error",
                JOptionPane.ERROR_MESSAGE);
            jDestinationField.selectAll();jDestinationField.requestFocus(); return;
        }
        if(jBufferField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Buffer Size not given", "Error",
                JOptionPane.ERROR_MESSAGE);
            jBufferField.selectAll();jBufferField.requestFocus(); return;
        }
        if(jProxyCheckBox.isSelected())
        {
            if( jProxyField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Proxy address required in proxy mode", "Error",
                    JOptionPane.ERROR_MESSAGE);
                jProxyField.requestFocus(); return;
            }
            if( jPortField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Proxy Port required in proxy mode", "Error",
                    JOptionPane.ERROR_MESSAGE);
                jPortField.requestFocus(); return;
            }
        }
        DashboardController.setDestinationFolder(jDestinationField.getText());
        DashboardController.setBufferSize(Integer.parseInt(jBufferField.getText()));
        DashboardController.setProxyAddress(jProxyField.getText());
        try{
            DashboardController.setProxyPort(Integer.parseInt(jPortField.getText()));
        }catch(NumberFormatException e)
        {
            DashboardController.setProxyPort(0);
        }
        DashboardController.setUserName(jUsernameField.getText());
        DashboardController.setTotalConnection(Integer.parseInt(jConnectionSpinner.getValue().toString()));
        try {
            DashboardController.setPassword(
                SettingsRecord.encrypt(jPasswordField.getText().toString()));
            //DashboardController.setPassword(jPasswordField.getText());
        } catch (Exception ex) {
            System.out.println("Error in Encryption");
            DashboardController.setPassword(jPasswordField.getPassword().toString());
        }
        jChangesSuccessfulLabel.setForeground(Color.RED);
        jChangesSuccessfulLabel.setText("Changes Applied Successfully!!");
        jChangesSuccessfulLabel.setVisible(true);
        new Display(this).start();
        DashboardController.setProxySettings();
    }//GEN-LAST:event_jApplyButtonActionPerformed

    private void jBufferFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBufferFieldFocusLost

        int buffer;
        try
        {
            buffer=Integer.parseInt(jBufferField.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Buffer Size has to be an integer!!", "Error",
                JOptionPane.ERROR_MESSAGE);
            jBufferField.selectAll();jBufferField.requestFocus(); return;
        }
        if(buffer>1024 || buffer<=0){
            JOptionPane.showMessageDialog(this,"Illegal or too much buffer!", "Error",
                JOptionPane.ERROR_MESSAGE);
            jBufferField.selectAll();jBufferField.requestFocus(); return; }
    }//GEN-LAST:event_jBufferFieldFocusLost

    private void jUsernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUsernameFieldFocusLost

        if(jUsernameField.getText().isEmpty())disableService(4);
        else enableService(4);
    }//GEN-LAST:event_jUsernameFieldFocusLost

    private void jProxyFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jProxyFieldFocusLost
        // TODO add your handling code here:
        String ip=jProxyField.getText();
        if (ip.isEmpty() ){disableService(2);
            return;
        }
        int i,num;
        for(i=0;i<ip.length();i++)
        {//to check for other characters
            if(ip.charAt(i)=='/' || Character.isLetterOrDigit(ip.charAt(i)) || ip.charAt(i)=='.' ||ip.charAt(i)==':'|| ip.charAt(i)=='-'); else break;
        }
        if(i!=ip.length())
        {
            JOptionPane.showMessageDialog(this,"Address/Hostname can only contain A_Z,0-9,.,/,-:", "Error",
                JOptionPane.ERROR_MESSAGE);
            jProxyField.requestFocus();jProxyField.selectAll();return;
        }
        if(ip.endsWith("http://") || ip.endsWith("https://"))
        {
            JOptionPane.showMessageDialog(this,"Incomplete names, Must have something other than protocols", "Error",
                JOptionPane.ERROR_MESSAGE);
            jProxyField.requestFocus();jProxyField.selectAll();return;
        }
        //to check for ipv4 checking
        StringTokenizer parts = new StringTokenizer(ip, "/.");
        if ( parts.countTokens() > 5 || parts.countTokens()<=2) {
            JOptionPane.showMessageDialog(this,"Considered Hostname", "Information",
                JOptionPane.INFORMATION_MESSAGE);Hostname=true;  enableService(2); return;
        }
        int n=parts.countTokens();
        for ( i=1;i<=n;i++)
        {//to check for hostname or ip
            String s=parts.nextToken().toString();
            try{
                int parseInt = Integer.parseInt( s );
            }
            catch (NumberFormatException nfe) {
                if(s.isEmpty()) {JOptionPane.showMessageDialog(this,"Invalid name or address", "Error",
                    JOptionPane.ERROR_MESSAGE); jProxyField.requestFocus();jProxyField.selectAll();return;}
            JOptionPane.showMessageDialog(this,"Considered as Hostname", "Information",
                JOptionPane.INFORMATION_MESSAGE); Hostname=true;  enableService(2); return;
        }
        }
        Hostname=false;
        parts=new StringTokenizer(ip, ".");
        if(parts.countTokens()!=4 || ip.endsWith(".") || ip.indexOf('/')!=-1)
        {
            JOptionPane.showMessageDialog(this,"Invalid Proxy IP Address", "Error",
                JOptionPane.ERROR_MESSAGE);
            jProxyField.requestFocus();jProxyField.selectAll(); return;
        }

        for ( i=0; i<4;i++)
        {//valid periods
            num = Integer.parseInt( parts.nextToken().toString() );
            if(num<0 || num>255) {JOptionPane.showMessageDialog(this,"Valid IP Address has periods from 0 to 255", "Error",
                JOptionPane.ERROR_MESSAGE);
            jProxyField.requestFocus();jProxyField.selectAll(); return;
        }
        }
        enableService(2);
    }//GEN-LAST:event_jProxyFieldFocusLost

    private void jProxyFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jProxyFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jProxyFieldFocusGained

    private void jDestinationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDestinationButtonActionPerformed
        if(!jDestinationField.getText().isEmpty() && !new File(jDestinationField.getText()).exists()) return;
        JFileChooser filechooser;
        //if(controller.getDestinationFolder().equals(""))
        String filepath=jDestinationField.getText();
        filechooser=new JFileChooser();
        filechooser.removeChoosableFileFilter(filechooser.getAcceptAllFileFilter());
        filechooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.canWrite() && pathname.canExecute();
            }

            @Override
            public String getDescription() {
                return "Executable and writable folders";
            }
        });
        //else filechooser=new JFileChooser(new File(controller.getDestinationFolder()));
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.setMultiSelectionEnabled(false);
        if(filechooser.showDialog(this,"Choose Folder")==JFileChooser.APPROVE_OPTION)
        {
            if(!filechooser.getSelectedFile().exists())
            { JOptionPane.showMessageDialog(this,"Invalid Folder path", "Error",
                JOptionPane.ERROR_MESSAGE); jDestinationField.setText(filepath);
        }
        else jDestinationField.setText(filechooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jDestinationButtonActionPerformed

    private void jDestinationFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDestinationFieldFocusLost
        String destination=jDestinationField.getText(),message="";
        if(!destination.equals(""))
        {
            if(!new File(destination).exists())message="Not a valid Destination!";
            else if(!new File(destination).isDirectory())message="Not a directory!";
            else if(!(new File(destination).canWrite() && new File(destination).canExecute()))
            message="Folder not accessible by the application";
            else return;

            JOptionPane.showMessageDialog(this,message, "Error",
                JOptionPane.ERROR_MESSAGE);
            jDestinationField.selectAll();
            jDestinationField.requestFocus();
        }
    }//GEN-LAST:event_jDestinationFieldFocusLost

    private void jConnectionSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jConnectionSpinnerStateChanged

        if(Integer.parseInt(jConnectionSpinner.getValue().toString())>8)
        jRecommendedLabel1.setVisible(true);
        else jRecommendedLabel1.setVisible(false);
    }//GEN-LAST:event_jConnectionSpinnerStateChanged

    private void jScheduleDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScheduleDownloadButtonActionPerformed
if("Enter the URL of the source file here".equals(jSchedulingURLField.getText()))
        {JOptionPane.showMessageDialog(this,
            "Enter URL field!!", "Error",
            JOptionPane.ERROR_MESSAGE); jSchedulingURLField.requestFocus(); return;}
if(!jTimeToggleButton.isSelected())
{
    DisplayMessage("Fix time and date before scheduling", "Error", 0); return;
}
if(ChosenDate.before(new Date())){DisplayMessage("Time passed away before scheduling. Refix Date and Time", "Error", 0); jTimeToggleButton.setSelected(false);return;}
      actionScheduleAdd();
    }//GEN-LAST:event_jScheduleDownloadButtonActionPerformed

    private void jTimeChooserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTimeChooserFocusGained
        jTimeDateLabel.setText("Enter the time by dragging the mouse up and down for increment/decrement after highlighting the specific column");
    }//GEN-LAST:event_jTimeChooserFocusGained

    private void jDateChooserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooserFocusLost
        jTimeDateLabel.setText("Enter the time by dragging the mouse up and down for increment/decrement after highlighting the specific column");
    }//GEN-LAST:event_jDateChooserFocusLost

    private void jDateChooserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooserFocusGained
        jTimeDateLabel.setText("Enter the date by clicking the calender button");
    }//GEN-LAST:event_jDateChooserFocusGained

    private void jDateChooserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooserMouseExited
        jTimeDateLabel.setText("Enter the time by dragging the mouse up and down for increment/decrement after highlighting the specific column");

    }//GEN-LAST:event_jDateChooserMouseExited

    private void jDateChooserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooserMouseEntered
        jTimeDateLabel.setText("Enter the date by clicking the calender button");
    }//GEN-LAST:event_jDateChooserMouseEntered

    private void jSchedulingFileNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSchedulingFileNameFocusLost
        // TODO add your handling code here:
        String filename=jSchedulingFileName.getText();
        if("".equals(filename))jSchedulingFileName.setText("Enter a name for the downloaded file");
        else if(!controller.CheckNameValidity(filename))
        {
            JOptionPane.showMessageDialog(this,
                "Illegal File Name!", "Error",
                JOptionPane.ERROR_MESSAGE);
            jSchedulingFileName.selectAll(); jSchedulingFileName.requestFocus();
        }
    }//GEN-LAST:event_jSchedulingFileNameFocusLost

    private void jSchedulingFileNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSchedulingFileNameFocusGained
        // TODO add your handling code here:
        if("Enter a name for the downloaded file".equals(jSchedulingFileName.getText())) jSchedulingFileName.setText(null);
    }//GEN-LAST:event_jSchedulingFileNameFocusGained

    private void jSchedulingURLFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSchedulingURLFieldFocusLost
        // TODO add your handling code here:
        if(jSchedulingURLField.getText().equals(""))jSchedulingURLField.setText("Enter the URL of the source file here");
    }//GEN-LAST:event_jSchedulingURLFieldFocusLost

    private void jSchedulingURLFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSchedulingURLFieldFocusGained
        // TODO add your handling code here:
        if(jSchedulingURLField.getText().equals("Enter the URL of the source file here")) jSchedulingURLField.setText(null);
    }//GEN-LAST:event_jSchedulingURLFieldFocusGained

    private void jFileNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFileNameFocusLost
        String filename=jFileName.getText();
        if("".equals(filename))jFileName.setText("Enter a name for the downloaded file");
        else if(!controller.CheckNameValidity(filename))
        {
            JOptionPane.showMessageDialog(this,
                "Illegal File Name!", "Error",
                JOptionPane.ERROR_MESSAGE);
            jFileName.selectAll(); jFileName.requestFocus();
        }
    }//GEN-LAST:event_jFileNameFocusLost

    private void jFileNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFileNameFocusGained
        if("Enter a name for the downloaded file".equals(jFileName.getText())) jFileName.setText(null);
    }//GEN-LAST:event_jFileNameFocusGained

    private void jClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearButtonActionPerformed
        actionClear();
    }//GEN-LAST:event_jClearButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        controller.actionCancel(SelectedDownload);
        updateButtons();
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void jDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDownloadButtonActionPerformed
        // TODO add your handling code here:
        if("Enter the URL of the source file here".equals(jURLField.getText()))
        {JOptionPane.showMessageDialog(this,
            "Enter URL field!!", "Error",
            JOptionPane.ERROR_MESSAGE); return;}
    actionAdd();
    }//GEN-LAST:event_jDownloadButtonActionPerformed

    private void jPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPauseButtonActionPerformed
        // TODO add your handling code here:
        if("Pause".equals(jPauseButton.getText())){controller.actionPause(SelectedDownload); jPauseButton.setText("Resume");}
        else {controller.actionResume(SelectedDownload); jPauseButton.setText("Pause");}
        updateButtons();
    }//GEN-LAST:event_jPauseButtonActionPerformed

    private void jURLFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jURLFieldFocusLost
        if(jURLField.getText().equals(""))jURLField.setText("Enter the URL of the source file here");
    }//GEN-LAST:event_jURLFieldFocusLost

    private void jURLFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jURLFieldFocusGained
        // TODO add your handling code here:
        if(jURLField.getText().equals("Enter the URL of the source file here")) jURLField.setText(null);
    }//GEN-LAST:event_jURLFieldFocusGained

    private void jTimeToggleButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTimeToggleButtonStateChanged
         jDateChooser.setEnabled(!jTimeToggleButton.isSelected());
         jTimeChooser.setEnabled(!jTimeToggleButton.isSelected());
         if(jTimeToggleButton.isSelected())
         {
        Date date = jDateChooser.getDate();
       date=jTimeChooser.getDateWithTime(date);
        if(date.after(new Date())){jTimeToggleButton.setSelected(true); jDateChooser.setEnabled(false); jTimeChooser.setEnabled(false);
        ChosenDate=date;
        } 
        else DisplayMessage("Cannot perform scheduling in the past","Time not in future", 0);
         }
    }//GEN-LAST:event_jTimeToggleButtonStateChanged
private void actionAdd() {
 URL verifiedUrl = controller.verifyURL(jURLField.getText());
        String filename=jFileName.getText();
        if (verifiedUrl != null) {
            if("Enter a name for the downloaded file".equals(filename)){ 
                filename=controller.getFileName(verifiedUrl);
        }
            else {
                filename+=verifiedUrl.toString().substring(verifiedUrl.toString().lastIndexOf('.'));
            }
         if(new File(controller.getDestinationFolder()+File.separator+filename).exists())
         {//if filename exists
             String [] options={"Overwrite existing file","Rename the new file","Cancel Download"};
             int k=JOptionPane.showOptionDialog(this,"File with the same name already exists, Choose from below:", "File name conflict"
                 , JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,options , options[1]);
             switch(k)
             {
                 case 0:break;
                 case 1:jFileName.requestFocus(); jFileName.selectAll(); return;
                 default:jURLField.setText("Enter the URL of the source file here"); // reset add text field
            jFileName.setText("Enter a name for the downloaded file"); return;    
             }
         }
         Download record=new Download(verifiedUrl.toString(),controller.getIndex(), filename);
             controller.RecordtoView(record, this);
            jURLField.setText("Enter the URL of the source file here"); // reset add text field
            jFileName.setText("Enter a name for the downloaded file");
         }
          else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Download URL", "Error",
                    JOptionPane.ERROR_MESSAGE);
            jURLField.selectAll();    
    }
}
    private void actionScheduleAdd() {
 URL verifiedUrl = controller.verifyURL(jSchedulingURLField.getText());
        String filename=jSchedulingFileName.getText();
        if (verifiedUrl != null) {
            if("Enter a name for the downloaded file".equals(filename)){ 
                filename=controller.getFileName(verifiedUrl);
        }
            else {
                filename+=verifiedUrl.toString().substring(verifiedUrl.toString().lastIndexOf('.'));
            }
         if(new File(controller.getDestinationFolder()+File.separator+filename).exists())
         {//if filename exists
             String [] options={"Overwrite existing file","Rename the new file","Cancel Download"};
             int k=JOptionPane.showOptionDialog(this,"File with the same name already exists, Choose from below: Affect when Downloaded", "File name conflict"
                 , JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,options , options[1]);
             switch(k)
             {
                 case 0:break;
                 case 1:jSchedulingFileName.requestFocus(); jFileName.selectAll(); return;
                 default:jSchedulingURLField.setText("Enter the URL of the source file here"); // reset add text field
            jSchedulingFileName.setText("Enter a name for the downloaded file"); return;    
             }
         }
         ScheduleRecord record=new ScheduleRecord(controller.getScheduleIndex(),verifiedUrl.toString(),filename,ChosenDate);
           DashboardController.UpdateScheduleRecord(record);
         //  jScheduledTable.getModel().get
         jSchedulingURLField.setText("Enter the URL of the source file here"); // reset add text field
            jSchedulingFileName.setText("Enter a name for the downloaded file");
         }
          else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Download URL", "Error",
                    JOptionPane.ERROR_MESSAGE);
            jSchedulingURLField.selectAll();    
    }
}
void DisplayMessage(String str, String title,int choice)
{
  if(choice==0)
  JOptionPane.showMessageDialog(this,
                    str, title,
                    JOptionPane.ERROR_MESSAGE);
  else 
      JOptionPane.showMessageDialog(this,
                    str, title,
                    JOptionPane.INFORMATION_MESSAGE);
  if(str.equals("HostName Unknown error!!"))jProxyField.requestFocus();
}
void AddEntry(Download download) {
       tableModel.addDownload(download);
    }
   // Called when table row selection changes.
    private void tableSelectionChanged() {
    /* Unregister from receiving notifications
       from the last selected download and if not downloading at the moment, add new observer the newly selected */
        if (SelectedDownload != null)
            SelectedDownload.deleteObserver(Dashboard.this);
        if (!clearing && jDownloadsTable.getSelectedRow()!=-1) {
            SelectedDownload =
                    tableModel.getDownload(jDownloadsTable.getSelectedRow());
            SelectedDownload.addObserver(Dashboard.this);
            updateButtons();
        }
    }
 
     
    // Clear the selected download.
    private void actionClear() {
       if(SelectedDownload==null)return;
        clearing= true;
        controller.DeleteMyRecord(SelectedDownload.getDownloadRecord());
        controller.ViewtoModelRecord(3, SelectedDownload.getDownloadRecord());
        tableModel.clearDownload(jDownloadsTable.getSelectedRow());
        clearing = false;
        SelectedDownload = null;
        updateButtons();
    }
     
  /* Update each button's state based off of the
     currently selected download's status. */
    private void updateButtons() {
        if (SelectedDownload != null) {
            int status = SelectedDownload.getStatus();
            switch (status) {
                case Download.DOWNLOADING:
                    jPauseButton.setText("Pause");
                    jPauseButton.setEnabled(!SelectedDownload.getDownloadRecord().Type.equals("none"));
                    jCancelButton.setEnabled(true);
                    jClearButton.setEnabled(false);
                    break;
                case Download.PAUSED:
                     jPauseButton.setText("Resume");
                      jPauseButton.setEnabled(true);
                    jCancelButton.setEnabled(true);
                    jClearButton.setEnabled(false);
                    break;
                case Download.ERROR:
                    jPauseButton.setEnabled(false);
                    jCancelButton.setEnabled(false);
                    jClearButton.setEnabled(true);
                    break;
                case Download.WAITING:
                    jPauseButton.setText("Pause");
                    jPauseButton.setEnabled(true);
                    jCancelButton.setEnabled(true);
                    jClearButton.setEnabled(false);
                    break;
                default: // COMPLETE or CANCELLED
                    jPauseButton.setEnabled(false);
                    jCancelButton.setEnabled(false);
                    jClearButton.setEnabled(true);
            }
        } else {
            // No download is selected in table.
            jPauseButton.setEnabled(false);
            jCancelButton.setEnabled(false);
            jClearButton.setEnabled(false);
        }
    }
     
  /* Update is called when a Download notifies its
     observers of any changes. */
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
     
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            }
        });
    }
void hideResult()
{
    jChangesSuccessfulLabel.setVisible(false);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jApplyButton;
    private javax.swing.JTextField jBufferField;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JButton jCancelChangesButton;
    private javax.swing.JLabel jChangesSuccessfulLabel;
    private javax.swing.JButton jClearButton;
    private javax.swing.JButton jClearChangesButton;
    private javax.swing.JSpinner jConnectionSpinner;
    private javax.swing.JLabel jConnectionsLabel1;
    private javax.swing.JLabel jConnectionsLabel2;
    private javax.swing.JLabel jConnectionsLabel6;
    private javax.swing.JLabel jConnectionsLabel9;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JButton jDestinationButton;
    private javax.swing.JTextField jDestinationField;
    private javax.swing.JButton jDownloadButton;
    private javax.swing.JPanel jDownloadTable;
    private javax.swing.JTable jDownloadsTable;
    private javax.swing.JTextField jFileName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JButton jPauseButton;
    private javax.swing.JTextField jPortField;
    private javax.swing.JCheckBox jProxyCheckBox;
    private javax.swing.JTextField jProxyField;
    private javax.swing.JLabel jProxyLabel1;
    private javax.swing.JLabel jProxyLabel2;
    private javax.swing.JLabel jProxyLabel3;
    private javax.swing.JLabel jProxyLabel4;
    private javax.swing.JLabel jProxyLabel5;
    private javax.swing.JLabel jProxyLabel6;
    private javax.swing.JLabel jRecommendedLabel1;
    private javax.swing.JButton jScheduleDownloadButton;
    private javax.swing.JTable jScheduledTable;
    private javax.swing.JPanel jScheduler;
    private javax.swing.JTextField jSchedulingFileName;
    private javax.swing.JTextField jSchedulingURLField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jSettings;
    private javax.swing.JTabbedPane jTabbedPane1;
    private lu.tudor.santec.jtimechooser.JTimeChooser jTimeChooser;
    private javax.swing.JLabel jTimeDate;
    private javax.swing.JLabel jTimeDateLabel;
    private javax.swing.JToggleButton jTimeToggleButton;
    private javax.swing.JTextField jURLField;
    private javax.swing.JTextField jUsernameField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
       if (SelectedDownload != null && SelectedDownload.equals(o))
            updateButtons();//To change body of generated methods, choose Tools | Templates.
    }

    private static class ProgressRenderer extends JProgressBar
        implements TableCellRenderer {
     
    // Constructor for ProgressRenderer.
    public ProgressRenderer(int min, int max) {
        super(min, max);
    }
     
  /* Returns this JProgressBar as the renderer
     for the given table cell. */
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        // Set JProgressBar's percent complete value.
        setValue((int) ((Float) value).floatValue());
        return this;
    }
    }
    private static class CenterRenderer extends DefaultTableCellRenderer {
     
  /* Centers the elelmets in all class of type string */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
{
    JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    renderedLabel.setHorizontalAlignment(CENTER);
    return renderedLabel;
}
   
    }

    private static class Display extends Thread {

        Dashboard board;
        public Display(Dashboard d) {
            this.board=d;
        }

        @Override
        public void run() {
            try {
                sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            board.hideResult();//To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
    
