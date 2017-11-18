/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTabbedPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author PaPa
 */
public class SoftwareComputer extends javax.swing.JPanel {

    /**
     * Creates new form SoftwareComputer
     */
     Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
   
    JTabbedPane jt=null;
    
    public SoftwareComputer(JTabbedPane jtp) {
        initComponents();
         jt=jtp;
        conn=JavaConnect.ConnecrDb();
        soft_Panel.setVisible(false); //
        FillpcCombo();
        FillsoftCombo();
        Fillsoft2Combo();
        
        
    }
    
    // for fill up pc id combo box
    private void FillpcCombo(){
        try{
            String sql="select * from MIcroComputerDB";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
        
                String name = rs.getString("PCID");
                pcid_ComboBox.addItem(name);
        
             }
        
    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
    }   
     private void FillsoftCombo(){
        try{
            String sql="select * from SoftwareDB";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
        
                String name = rs.getString("SoftwareID");
                softid_ComboBox.addItem(name);
        
             }
        
    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
    }  
     private void Fillsoft2Combo(){
        try{
            String sql="select * from SoftwareDB";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
        
                String name = rs.getString("SoftwareID");
                ComboBox_soft.addItem(name);
        
             }
        
    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
    }   
      private void Update_pctable(){
        try{
        
            String sql = "SELECT MicroComputerDB.PCID,MicroComputerDB.Name_of_user,MicroComputerDB.Location,MicroComputerSoftwareJUNCTION.Date_of_installation "+
                         "FROM MicroComputerSoftwareJUNCTION,MicroComputerDB "+
                         "WHERE MicroComputerSoftwareJUNCTION.SoftwareID=? AND MicroComputerSoftwareJUNCTION.PCID=MicroComputerDB.PCID";
          
            pst=conn.prepareStatement(sql);
            
            pst.setString(1,(String)ComboBox_soft.getSelectedItem());
            
         
            rs=pst.executeQuery(); 
            SoftPCSearch_table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }finally{
            try{
            rs.close();
            pst.close();
            }
            catch(Exception e){
            }
        }
     }
       private void Update_softwaretable(){
        try{
        
            String sql ="SELECT SoftwareDB.SoftwareID,SoftwareDB.Software_name,SoftwareDB.Description,MicroComputerSoftwareJUNCTION.Date_of_installation "+
                        "FROM SoftwareDB,MicroComputerSoftwareJUNCTION "+
                        "WHERE SoftwareDB.SoftwareID=MicroComputerSoftwareJUNCTION.SoftwareID AND MicroComputerSoftwareJUNCTION.PCID=?";
            pst=conn.prepareStatement(sql);
            
            pst.setString(1,(String)pcid_ComboBox.getSelectedItem());
            
         
            rs=pst.executeQuery(); 
            installedsoft_table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }finally{
            try{
            rs.close();
            pst.close();
            }
            catch(Exception e){
            }
        }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        installedsoft_table = new javax.swing.JTable();
        PC_panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pcid_ComboBox = new javax.swing.JComboBox();
        txt_username = new javax.swing.JTextField();
        txt_location = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        cmd_install = new javax.swing.JButton();
        cmd_report = new javax.swing.JButton();
        cmd_print1 = new javax.swing.JButton();
        soft_Panel = new javax.swing.JPanel();
        txt_description = new javax.swing.JTextField();
        txt_remark = new javax.swing.JTextField();
        softid_ComboBox = new javax.swing.JComboBox();
        txt_softname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmd_instll = new javax.swing.JButton();
        txt_choosedate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        SoftPCSearch_table1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        txt_des = new javax.swing.JTextField();
        txt_rmk = new javax.swing.JTextField();
        ComboBox_soft = new javax.swing.JComboBox();
        txt_sname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setName("Softwares & MicroComputers");

        jPanel7.setName("SoftwareComputer");

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 255, 204), null, null), "Installed Softwares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        installedsoft_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(installedsoft_table);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        PC_panel1.setBackground(new java.awt.Color(204, 204, 255));
        PC_panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Micro-Computers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(153, 0, 153))); // NOI18N

        jLabel2.setText("PC-ID");

        pcid_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "< PC_ID>" }));
        pcid_ComboBox.setToolTipText("PC_ID of available microcomputers ");
        pcid_ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                pcid_ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txt_username.setEditable(false);
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        txt_location.setEditable(false);

        jLabel7.setText("Name of user");

        jLabel8.setText("Location");

        javax.swing.GroupLayout PC_panel1Layout = new javax.swing.GroupLayout(PC_panel1);
        PC_panel1.setLayout(PC_panel1Layout);
        PC_panel1Layout.setHorizontalGroup(
            PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PC_panel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pcid_ComboBox, 0, 219, Short.MAX_VALUE)
                    .addComponent(txt_username)
                    .addComponent(txt_location))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        PC_panel1Layout.setVerticalGroup(
            PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PC_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pcid_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(PC_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.gray));

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setText("Remove software");
        jButton3.setToolTipText("Select a PC in Micro-Computer Info table to remove or uninstall selected software ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cmd_install.setBackground(new java.awt.Color(102, 255, 102));
        cmd_install.setText("Install new software");
        cmd_install.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_installActionPerformed(evt);
            }
        });

        cmd_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/report-icon.png"))); // NOI18N
        cmd_report.setText("Report");
        cmd_report.setToolTipText("Reports.....");

        cmd_print1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/printer-icon.png"))); // NOI18N
        cmd_print1.setText("Print");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmd_install, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmd_print1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(cmd_report)
                    .addContainerGap(105, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cmd_install)
                .addGap(150, 150, 150)
                .addComponent(cmd_print1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(25, 25, 25))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(207, 207, 207)
                    .addComponent(cmd_report)
                    .addContainerGap(207, Short.MAX_VALUE)))
        );

        soft_Panel.setBackground(new java.awt.Color(255, 255, 204));
        soft_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 255, 51), null, null), "Softwares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(51, 0, 0))); // NOI18N

        txt_description.setEditable(false);

        txt_remark.setEditable(false);

        softid_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Software_ID>" }));
        softid_ComboBox.setToolTipText("Available ID of Softwares");
        softid_ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                softid_ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txt_softname.setEditable(false);

        jLabel9.setText("Software_ID");

        jLabel10.setText("Software_name");

        jLabel11.setText("Description");

        jLabel12.setText("Remark");

        cmd_instll.setText("Install");
        cmd_instll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_instllActionPerformed(evt);
            }
        });

        txt_choosedate.setDateFormatString("dd/MM/yyyy");

        jLabel1.setText("Date of installation");

        javax.swing.GroupLayout soft_PanelLayout = new javax.swing.GroupLayout(soft_Panel);
        soft_Panel.setLayout(soft_PanelLayout);
        soft_PanelLayout.setHorizontalGroup(
            soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soft_PanelLayout.createSequentialGroup()
                .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, soft_PanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmd_instll, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, soft_PanelLayout.createSequentialGroup()
                        .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_remark, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_description, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_softname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(softid_ComboBox, 0, 193, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, soft_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_choosedate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        soft_PanelLayout.setVerticalGroup(
            soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soft_PanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(softid_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(23, 23, 23)
                .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_softname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_remark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(soft_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_choosedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(cmd_instll)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PC_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soft_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(PC_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(soft_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(134, 134, 134))
        );

        jTabbedPane2.addTab("MicroComputer", jPanel8);

        jPanel10.setToolTipText("Admin can know in which PCs' a selected software is installed");

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Micro-Computer Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(153, 0, 153))); // NOI18N

        SoftPCSearch_table1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(SoftPCSearch_table1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addGap(73, 73, 73))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 255, 51), null, null), "Softwares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(51, 0, 0))); // NOI18N

        ComboBox_soft.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Software_ID>" }));
        ComboBox_soft.setToolTipText("Available ID of Softwares");
        ComboBox_soft.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ComboBox_softPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setText("Software_ID");

        jLabel4.setText("Software_name");

        jLabel5.setText("Description");

        jLabel6.setText("Remark");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_rmk, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_des, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_sname)
                    .addComponent(ComboBox_soft, 0, 219, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBox_soft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_des, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_rmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jButton5.setBackground(new java.awt.Color(255, 102, 102));
        jButton5.setText("Delete PC");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(63, 63, 63)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton5)
                        .addGap(191, 191, 191)))
                .addGap(63, 63, 63))
        );

        jTabbedPane2.addTab("Software ", jPanel10);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void cmd_installActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_installActionPerformed
        soft_Panel.setVisible(true);
    }//GEN-LAST:event_cmd_installActionPerformed

    private void pcid_ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_pcid_ComboBoxPopupMenuWillBecomeInvisible
       Update_softwaretable();
        
        String tmp=(String)pcid_ComboBox.getSelectedItem();
       String sql ="select * from MicroComputerDB where PCID =?";
        try{
           
            pst=conn.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                String add1=rs.getString("Name_of_user");
                txt_username.setText(add1);
                
                 String add2=rs.getString("Location");
                txt_location.setText(add2);
                
                 
            
            
            }
           
            
        
        
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        
        }
         finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
        
    }//GEN-LAST:event_pcid_ComboBoxPopupMenuWillBecomeInvisible

    private void softid_ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_softid_ComboBoxPopupMenuWillBecomeInvisible
          String tmp=(String)softid_ComboBox.getSelectedItem();
       String sql ="select * from SoftwareDB where SoftwareID =?";
        try{
           
            pst=conn.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                String add1=rs.getString("Software_name");
                txt_softname.setText(add1);
                
                 String add2=rs.getString("Description");
                txt_description.setText(add2);
                
                 String add3=rs.getString("Remark");
                txt_remark.setText(add3);
                
                 
            
            
            }
           
            
        
        
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        
        }
         finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
        
    }//GEN-LAST:event_softid_ComboBoxPopupMenuWillBecomeInvisible

    private void ComboBox_softPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ComboBox_softPopupMenuWillBecomeInvisible
        Update_pctable();
        
        String tmp=(String)ComboBox_soft.getSelectedItem();
       String sql ="select * from SoftwareDB where SoftwareID =?";
        try{
           
            pst=conn.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                String add1=rs.getString("Software_name");
                txt_sname.setText(add1);
                
                 String add2=rs.getString("Description");
                txt_des.setText(add2);
                
                 String add3=rs.getString("Remark");
                txt_rmk.setText(add3);
                
                 
            
            
            }
           
            
        
        
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        
        }
         finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
        
    }//GEN-LAST:event_ComboBox_softPopupMenuWillBecomeInvisible

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            
        try{
            int row=SoftPCSearch_table1.getSelectedRow();
            String Table_click=(SoftPCSearch_table1.getModel().getValueAt(row,0).toString());
            
            String sql="delete from MicroComputerSoftwareJUNCTION where PCID=? and SoftwareID='"+(String)ComboBox_soft.getSelectedItem()+"'";
             pst=conn.prepareStatement(sql);
            
            pst.setString(1,Table_click);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data is deleted");
            }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        
        }
         finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
        Update_pctable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         
        soft_Panel.setVisible(false);
        try{
            int row=installedsoft_table.getSelectedRow();
            String Table_click=(installedsoft_table.getModel().getValueAt(row,0).toString());
            
            String sql="delete from MicroComputerSoftwareJUNCTION where SoftwareID=? and PCID='"+(String)pcid_ComboBox.getSelectedItem()+"'";
             pst=conn.prepareStatement(sql);
            
            pst.setString(1,Table_click);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data is deleted");
            }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        
        }
         finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){
        
             }
        }
        Update_softwaretable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmd_instllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_instllActionPerformed
         try{
            
            String sql = "Insert into MicroComputerSoftwareJUNCTION (PCID,SoftwareID,Date_of_installation) values (?,?,?)";
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1,(String)pcid_ComboBox.getSelectedItem());
            pst.setString(2,(String)softid_ComboBox.getSelectedItem());
            pst.setString(3,((JTextField)txt_choosedate.getDateEditor().getUiComponent()).getText());
            
            
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "New Software is installed");
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
            
            pst.close();
            }catch(Exception e){
        
            }
        }
        Update_softwaretable();
        
    }//GEN-LAST:event_cmd_instllActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBox_soft;
    private javax.swing.JPanel PC_panel1;
    private javax.swing.JTable SoftPCSearch_table1;
    private javax.swing.JButton cmd_install;
    private javax.swing.JButton cmd_instll;
    private javax.swing.JButton cmd_print1;
    private javax.swing.JButton cmd_report;
    private javax.swing.JTable installedsoft_table;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox pcid_ComboBox;
    private javax.swing.JPanel soft_Panel;
    private javax.swing.JComboBox softid_ComboBox;
    private com.toedter.calendar.JDateChooser txt_choosedate;
    private javax.swing.JTextField txt_des;
    private javax.swing.JTextField txt_description;
    private javax.swing.JTextField txt_location;
    private javax.swing.JTextField txt_remark;
    private javax.swing.JTextField txt_rmk;
    private javax.swing.JTextField txt_sname;
    private javax.swing.JTextField txt_softname;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
