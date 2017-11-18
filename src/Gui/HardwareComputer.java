/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import java.util.Date;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Font;
import java.io.FileOutputStream;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author PaPa
 */
public class HardwareComputer extends javax.swing.JPanel {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    JTabbedPane jt = null;

    /**
     * Creates new form HardwareComputer
     */
    public HardwareComputer(JTabbedPane jtp) {
        initComponents();
        jt = jtp;
        conn = JavaConnect.ConnecrDb();
        FillpcCombo();
       cmd_add.setEnabled(false);
   

    }

    private void FillpcCombo() {
        try {
            String sql = "select DISTINCT PCID from MIcroComputerDB";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String name = rs.getString("PCID");
                ComboBox_pcid.addItem(name);



            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    private void FillitemCombo() {
        try {

            String sql = "SELECT DISTINCT PurchaseDB.Item_name_or_Accesories "
                    + "FROM HardwareDB,PurchaseDB "
                    + "WHERE PurchaseDB.PurchaseID=HardwareDB.PurchaseID";
                   
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String name = rs.getString("Item_name_or_Accesories");
                ComboBox_hardware.addItem(name);



            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    private void FillhardwareidCombo() {
        try {
            String tmp = (String) ComboBox_hardware.getSelectedItem();

            String sql = "SELECT HardwareDB.HardwareID "
                    + "FROM HardwareDB,PurchaseDB "
                    + "WHERE PurchaseDB.Item_name_or_Accesories=? and PurchaseDB.PurchaseID=HardwareDB.PurchaseID and HardwareDB.PCID is Null";

            pst = conn.prepareStatement(sql);
            pst.setString(1, tmp);
            rs = pst.executeQuery();


            while (rs.next()) {

                String name = rs.getString("HardwareID");
                ComboBox_hardwareid.addItem(name);



            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    private void Update_hardwaretable() {
        try {

            String sql = "SELECT HardwareID,Item_name_or_Accesories,HardwareDB.PurchaseID, Brand,Model,Description,Price_BDT,CurrentStatus,Date_of_installation "
                    + "FROM HardwareDB,PurchaseDB "
                    + "WHERE HardwareDB.PCID=? and PurchaseDB.PurchaseID=HardwareDB.PurchaseID "
                    + "GROUP BY HardwareID";
            pst = conn.prepareStatement(sql);
            pst.setString(1, (String) ComboBox_pcid.getSelectedItem());
            rs = pst.executeQuery();
            Hardware_table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    private void Clear_AddNewHardwarePanel() {
        txt_puchaseid.setText("");
        txt_brand.setText("");
        txt_model.setText("");
        txt_description.setText("");
        txt_price.setText("");
        txt_status.setText("");
        txt_DateChooser.setDate(null);
        ComboBox_hardwareid.removeAllItems();
        ComboBox_hardware.removeAllItems();
        cmd_add.setEnabled(false);


    }
    public String CurrentDate() {
         
        String date_time=null;
        Calendar cal = new GregorianCalendar();
        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);
        Integer day = cal.get(Calendar.DAY_OF_MONTH);
        
        Integer second = cal.get(Calendar.SECOND);
        Integer minute = cal.get(Calendar.MINUTE);
        Integer hour = cal.get(Calendar.HOUR);
        date_time=year.toString()+month.toString()+day.toString()+hour.toString()+minute.toString()+second.toString();
        return date_time;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_pcid = new javax.swing.JTextField();
        ComboBox_pcid = new javax.swing.JComboBox();
        txt_location = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        Add_New_HardwarePanel = new javax.swing.JPanel();
        ComboBox_hardware = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_puchaseid = new javax.swing.JTextField();
        txt_model = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_brand = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_status = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_description = new javax.swing.JTextField();
        ComboBox_hardwareid = new javax.swing.JComboBox();
        txt_price = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmd_add = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cmd_deletehardware = new javax.swing.JButton();
        cmd_newhardware = new javax.swing.JButton();
        cmd_update = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Hardware_table = new javax.swing.JTable();
        cmd_report = new javax.swing.JButton();
        cmd_print = new javax.swing.JButton();

        setName("Hardwares & MicoComputer");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 255, 204), null), "Micro-Computer Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        ComboBox_pcid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<PC_ID>" }));
        ComboBox_pcid.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ComboBox_pcidPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txt_location.setEditable(false);

        jLabel4.setText("Name_of_user");

        txt_DateChooser.setDateFormatString("dd/MM/yyyy");

        jLabel2.setText("PC_ID");

        jLabel5.setText("Loaction");

        jLabel3.setText("Date_of_installation ");

        txt_username.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(39, 39, 39)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(txt_pcid)
                    .addComponent(txt_username)
                    .addComponent(txt_location))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboBox_pcid, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_pcid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboBox_pcid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        Add_New_HardwarePanel.setBackground(new java.awt.Color(204, 204, 255));
        Add_New_HardwarePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.magenta, null), "Add new Hardwares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(153, 0, 102))); // NOI18N

        ComboBox_hardware.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Item name /Accesories>" }));
        ComboBox_hardware.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ComboBox_hardwarePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel9.setText("Model");

        jLabel11.setText("Current Status");

        jLabel6.setText("Hardware_ID");

        txt_puchaseid.setEditable(false);

        txt_model.setEditable(false);

        jLabel7.setText("Purchase_ID");

        jLabel1.setText("Hardware item");

        txt_brand.setEditable(false);

        jLabel8.setText("Brand");

        jLabel10.setText("Description");

        txt_description.setEditable(false);
        jScrollPane1.setViewportView(txt_description);

        ComboBox_hardwareid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Hardware_ID>" }));
        ComboBox_hardwareid.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ComboBox_hardwareidPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txt_price.setEditable(false);

        jLabel12.setText("Price(BDT)");

        cmd_add.setBackground(new java.awt.Color(51, 204, 0));
        cmd_add.setText("Add");
        cmd_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Add_New_HardwarePanelLayout = new javax.swing.GroupLayout(Add_New_HardwarePanel);
        Add_New_HardwarePanel.setLayout(Add_New_HardwarePanelLayout);
        Add_New_HardwarePanelLayout.setHorizontalGroup(
            Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_New_HardwarePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmd_add, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(Add_New_HardwarePanelLayout.createSequentialGroup()
                            .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_model)
                                .addComponent(txt_brand, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ComboBox_hardwareid, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_puchaseid)
                                .addComponent(jScrollPane1)
                                .addComponent(ComboBox_hardware, 0, 254, Short.MAX_VALUE)
                                .addComponent(txt_price)))
                        .addGroup(Add_New_HardwarePanelLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                            .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(169, 169, 169))
        );
        Add_New_HardwarePanelLayout.setVerticalGroup(
            Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_New_HardwarePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ComboBox_hardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ComboBox_hardwareid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_puchaseid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Add_New_HardwarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmd_add)
                .addGap(6, 6, 6))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmd_deletehardware.setBackground(new java.awt.Color(255, 51, 51));
        cmd_deletehardware.setText("Delete Hardware");
        cmd_deletehardware.setToolTipText("Remove items,accesories,equipments connected to Microcomputer ");
        cmd_deletehardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_deletehardwareActionPerformed(evt);
            }
        });

        cmd_newhardware.setBackground(new java.awt.Color(153, 255, 153));
        cmd_newhardware.setText("Add Hardwares");
        cmd_newhardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_newhardwareActionPerformed(evt);
            }
        });

        cmd_update.setBackground(new java.awt.Color(51, 204, 255));
        cmd_update.setText("Update Hardware");
        cmd_update.setToolTipText("Update the date of installation and the current status of selected hardware in the corresponding table");
        cmd_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmd_newhardware)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmd_update)
                .addGap(46, 46, 46)
                .addComponent(cmd_deletehardware)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_newhardware)
                    .addComponent(cmd_deletehardware)
                    .addComponent(cmd_update))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Installed Hardware Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(0, 51, 102))); // NOI18N

        Hardware_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Hardware_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Hardware_tableMouseClicked(evt);
            }
        });
        Hardware_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Hardware_tableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Hardware_table);

        cmd_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/report-icon.png"))); // NOI18N
        cmd_report.setText("Report");
        cmd_report.setToolTipText("Reports.....");
        cmd_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_reportActionPerformed(evt);
            }
        });

        cmd_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/printer-icon.png"))); // NOI18N
        cmd_print.setText("Print");
        cmd_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cmd_report)
                        .addGap(18, 18, 18)
                        .addComponent(cmd_print, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_report)
                    .addComponent(cmd_print))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Add_New_HardwarePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Add_New_HardwarePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBox_hardwarePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ComboBox_hardwarePopupMenuWillBecomeInvisible
        ComboBox_hardwareid.removeAllItems();
        FillhardwareidCombo();
    }//GEN-LAST:event_ComboBox_hardwarePopupMenuWillBecomeInvisible

    private void ComboBox_pcidPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ComboBox_pcidPopupMenuWillBecomeInvisible

        Update_hardwaretable();


        String tmp = (String) ComboBox_pcid.getSelectedItem();

        String sql = "select * from MicroComputerDB where PCID =?";
        try {

            txt_pcid.setText(tmp);
            pst = conn.prepareStatement(sql);
            pst.setString(1, tmp);
            rs = pst.executeQuery();

            if (rs.next()) {

                String add1 = rs.getString("Name_of_user");
                txt_username.setText(add1);

                String add2 = rs.getString("Location");
                txt_location.setText(add2);
            } else {
                txt_username.setText("");
                txt_location.setText("");

            }




        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }


    }//GEN-LAST:event_ComboBox_pcidPopupMenuWillBecomeInvisible

    private void ComboBox_hardwareidPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ComboBox_hardwareidPopupMenuWillBecomeInvisible
        String tmp = (String) ComboBox_hardwareid.getSelectedItem();

        String sql = "SELECT HardwareID,Item_name_or_Accesories,HardwareDB.PurchaseID, Brand,Model,Description,Price_BDT,CurrentStatus "
                + "FROM HardwareDB,PurchaseDB "
                + "WHERE HardwareDB.HardwareID=? and PurchaseDB.PurchaseID=HardwareDB.PurchaseID "
                + "GROUP BY HardwareID";
        try {


            pst = conn.prepareStatement(sql);
            pst.setString(1, tmp);
            rs = pst.executeQuery();

            if (rs.next()) {


                String item = rs.getString("Item_name_or_Accesories");
                ComboBox_hardware.setSelectedItem(item);

                String add1 = rs.getString("PurchaseID");
                txt_puchaseid.setText(add1);

                String add2 = rs.getString("Brand");
                txt_brand.setText(add2);

                String add3 = rs.getString("Model");
                txt_model.setText(add3);
                String add4 = rs.getString("Description");
                txt_description.setText(add4);
                String add5 = rs.getString("Price_BDT");
                txt_price.setText(add5);
                String add6 = rs.getString("CurrentStatus");
                txt_status.setText(add6);

            }




        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_ComboBox_hardwareidPopupMenuWillBecomeInvisible

    private void cmd_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_addActionPerformed
        
        try {

            String pcid = (String) ComboBox_pcid.getSelectedItem();
            String hardwareid = (String) ComboBox_hardwareid.getSelectedItem();
            String date_of_installation = ((JTextField) txt_DateChooser.getDateEditor().getUiComponent()).getText();
            String status = txt_status.getText();

            String sql = "Update HardwareDB set PCID='" + pcid + "',Date_of_installation='" + date_of_installation + "',CurrentStatus='" + status + "' where HardwareID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, hardwareid);
            pst.execute();
            JOptionPane.showMessageDialog(null, "New item is added to MicroComputer");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        Update_hardwaretable();
        Clear_AddNewHardwarePanel();
    }//GEN-LAST:event_cmd_addActionPerformed

    private void cmd_newhardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_newhardwareActionPerformed
        FillitemCombo();
       cmd_add.setEnabled(true);

    }//GEN-LAST:event_cmd_newhardwareActionPerformed

    private void cmd_deletehardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_deletehardwareActionPerformed
    

       cmd_add.setEnabled(false);

        try {
            int row = Hardware_table.getSelectedRow();
            String Table_click = (Hardware_table.getModel().getValueAt(row, 0).toString());

            String sql = "Update HardwareDB set PCID=Null where HardwareID=?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, Table_click);

            pst.execute();
            JOptionPane.showMessageDialog(null, "Selected Hardware is removed");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        Update_hardwaretable();
    }//GEN-LAST:event_cmd_deletehardwareActionPerformed

    private void Hardware_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Hardware_tableMouseClicked

        int row = Hardware_table.getSelectedRow();

         String hardwareid = (Hardware_table.getModel().getValueAt(row, 0).toString());
        ComboBox_hardwareid.setSelectedItem(hardwareid);
        String item = (Hardware_table.getModel().getValueAt(row, 1).toString());
        ComboBox_hardware.setSelectedItem(item);
       
        String purchaseid = (Hardware_table.getModel().getValueAt(row, 2).toString());
        txt_puchaseid.setText(purchaseid);

        String brand = (Hardware_table.getModel().getValueAt(row, 3).toString());
        String model = (Hardware_table.getModel().getValueAt(row, 4).toString());
        String description = (Hardware_table.getModel().getValueAt(row, 5).toString());
        String price = (Hardware_table.getModel().getValueAt(row, 6).toString());
        String status = (Hardware_table.getModel().getValueAt(row, 7).toString());


        txt_brand.setText(brand);
        txt_model.setText(model);
        txt_description.setText(description);
        txt_price.setText(price);
        txt_status.setText(status);
        cmd_add.setEnabled(false);
    }//GEN-LAST:event_Hardware_tableMouseClicked

    private void Hardware_tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Hardware_tableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {

            int row = Hardware_table.getSelectedRow();

             String hardwareid = (Hardware_table.getModel().getValueAt(row, 0).toString());
            ComboBox_hardwareid.setSelectedItem(hardwareid);
            String item = (Hardware_table.getModel().getValueAt(row, 1).toString());
            ComboBox_hardware.setSelectedItem(item);
           
            String purchaseid = (Hardware_table.getModel().getValueAt(row, 2).toString());
            txt_puchaseid.setText(purchaseid);

            String brand = (Hardware_table.getModel().getValueAt(row, 3).toString());
            String model = (Hardware_table.getModel().getValueAt(row, 4).toString());
            String description = (Hardware_table.getModel().getValueAt(row, 5).toString());
            String price = (Hardware_table.getModel().getValueAt(row, 6).toString());
            String status = (Hardware_table.getModel().getValueAt(row, 7).toString());


            txt_brand.setText(brand);
            txt_model.setText(model);
            txt_description.setText(description);
            txt_price.setText(price);
            txt_status.setText(status);

          cmd_add.setEnabled(false);
        }
    }//GEN-LAST:event_Hardware_tableKeyReleased

    private void cmd_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_updateActionPerformed
       
        cmd_add.setEnabled(false);
        
        try {

            int row = Hardware_table.getSelectedRow();
            String pcid = (String) ComboBox_pcid.getSelectedItem();
            String hardwareid = (Hardware_table.getModel().getValueAt(row, 0).toString());
            String date_of_installation = ((JTextField) txt_DateChooser.getDateEditor().getUiComponent()).getText();
            String status = txt_status.getText();

            String sql = "Update HardwareDB set PCID='" + pcid + "',Date_of_installation='" + date_of_installation + "',CurrentStatus='" + status + "' where HardwareID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, hardwareid);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Item info is updated in MicroComputer");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        Update_hardwaretable();
        Clear_AddNewHardwarePanel();
    }//GEN-LAST:event_cmd_updateActionPerformed

    private void cmd_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_printActionPerformed
      MessageFormat header = new MessageFormat(" IUT Computer Center :: Installed Hardware Information");
      MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {

            Hardware_table.print(JTable.PrintMode.NORMAL, header, footer);
            
        } catch (java.awt.print.PrinterException e) {
            System.err.format("can't print", e.getMessage());

        }
        JOptionPane.showMessageDialog(null, "Report Printed");       
    }//GEN-LAST:event_cmd_printActionPerformed

    private void cmd_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_reportActionPerformed
          try {

            
            Document doc = new Document();
            PdfAWriter.getInstance(doc, new FileOutputStream("Report"+CurrentDate()+".pdf"));
            doc.open();
            doc.add(new Paragraph("IUT Computer Center :: Admin Information", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY)));
            doc.add(new Paragraph(new java.util.Date().toString()));
            doc.add(new Paragraph("             "));

            int col = Hardware_table.getColumnCount()-3;
            PdfPTable table = new PdfPTable(col);

            for (int i = 0; i <col; i++) {
                String col_name =  Hardware_table.getColumnName(i);
                table.addCell(new Paragraph(col_name, FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLUE)));
            }
            int row =  Hardware_table.getRowCount();

            for (int i = 0; i < row; i++) {
                for(int j=0;j<col;j++){
                String temp = ( Hardware_table.getModel().getValueAt(i,j).toString());
                table.addCell(temp);
                }
            }


           doc.add(table);
           doc.close();
           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        JOptionPane.showMessageDialog(null, "Report Printed");



  


    }//GEN-LAST:event_cmd_reportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Add_New_HardwarePanel;
    private javax.swing.JComboBox ComboBox_hardware;
    private javax.swing.JComboBox ComboBox_hardwareid;
    private javax.swing.JComboBox ComboBox_pcid;
    private javax.swing.JTable Hardware_table;
    private javax.swing.JButton cmd_add;
    private javax.swing.JButton cmd_deletehardware;
    private javax.swing.JButton cmd_newhardware;
    private javax.swing.JButton cmd_print;
    private javax.swing.JButton cmd_report;
    private javax.swing.JButton cmd_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser txt_DateChooser;
    private javax.swing.JTextField txt_brand;
    private javax.swing.JTextField txt_description;
    private javax.swing.JTextField txt_location;
    private javax.swing.JTextField txt_model;
    private javax.swing.JTextField txt_pcid;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_puchaseid;
    private javax.swing.JTextField txt_status;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
