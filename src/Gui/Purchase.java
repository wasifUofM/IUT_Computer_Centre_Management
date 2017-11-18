/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FindMatchInfoPanel.java
 *
 * Created on Apr 25, 2011, 8:16:40 PM
 */
package Gui;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import java.util.Date;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author emon
 */
public class Purchase extends javax.swing.JPanel {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    TableRowSorter<TableModel> sorter = null;
    JTabbedPane jtp;

    public Purchase(JTabbedPane jt) {
        initComponents();
        jtp = jt;
        conn = JavaConnect.ConnecrDb();
        Update_table();
        FillCombo();
        getLastInsertedHDid(); //txt_last holds the value

    }

    private void Update_table() {
        try {

            String sql = "select * from PurchaseDB";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            TableModel tm = DbUtils.resultSetToTableModel(rs);
            purchase_table.setModel(tm);
            sorter = new TableRowSorter<TableModel>(tm);
            purchase_table.setRowSorter(sorter);


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

    private void FillCombo() {
        try {
            String sql = "select * from PurchaseDB";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {


                String itemname = rs.getString("Item_name_or_Accesories");
                chooseitem_ComboBox.addItem(itemname);

                String brandname = rs.getString("Brand");
                choosebrand_ComboBox.addItem(brandname);


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

    private void getLastInsertedHDid() {

        try {

            String sql = "SELECT MAX(rowid) AS LastInsertedID FROM HardwareDB";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                String lastid = rs.getString("LastInsertedID");
                txt_last.setText(lastid);


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

    private void addHardware(String amount) {
        try {

            Statement st = conn.createStatement();
            for (Integer i = 1; i <= new Integer(amount); i++) {

                Integer n = new Integer(txt_last.getText()) + i;
                String hdid = "HD" + n.toString();
                String purchaseID = txt_purchaseid.getText();
                String sql = "Insert into HardwareDB (HardwareID,PurchaseID) values ('" + hdid +"','"+ purchaseID + "')";
                st.addBatch(sql);
            }
            SqlThread sqlthread = new SqlThread(st);
            sqlthread.start();
            JOptionPane.showMessageDialog(null, "New Hardware-items are added to Hardware Database");



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

    private void deletehardware() {

        String sql = "delete from HardwareDB where PurchaseID =?";
        try {
            pst = conn.prepareStatement(sql);
            int row = purchase_table.getSelectedRow();
            String puchaseid = (purchase_table.getModel().getValueAt(row, 0).toString());
            pst.setString(1, puchaseid);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Hardwares are deleted from database");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {

                pst.close();
            } catch (Exception e) {
            }
        }


    }

    private void countHardware() {

        try {

            int row = purchase_table.getSelectedRow();
            String pcid = (purchase_table.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT PurchaseID,Count(*) AS HardwareNumber From HardwareDB where PurchaseID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, pcid);
            rs = pst.executeQuery();
            if (rs.next()) {

                String num = rs.getString("HardwareNumber");
                txt_number.setText(num);


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

    private void updatehardware() {

        Integer p_amount = new Integer(txt_amount.getText());
        Integer h_number = new Integer(txt_number.getText());

        if (p_amount > h_number) {
            Integer diff = p_amount - h_number;
            addHardware(diff.toString());
        }
        if (h_number > p_amount) {
            JOptionPane.showMessageDialog(null, "Addiotional Hardware items are deleted from HardwareDB");
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

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
        filterText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_purchaseid = new javax.swing.JTextField();
        txt_datepurchase = new javax.swing.JTextField();
        txt_itemname = new javax.swing.JTextField();
        txt_brand = new javax.swing.JTextField();
        txt_model = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_remark = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cmd_update = new javax.swing.JButton();
        cmd_delete = new javax.swing.JButton();
        cmd_clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_description = new javax.swing.JTextField();
        txt_choosedate = new com.toedter.calendar.JDateChooser();
        chooseitem_ComboBox = new javax.swing.JComboBox();
        choosebrand_ComboBox = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        cmd_report = new javax.swing.JButton();
        txt_last = new javax.swing.JTextField();
        txt_number = new javax.swing.JTextField();
        cmd_print1 = new javax.swing.JButton();

        setName("Purchase Info"); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase Info"));

        purchase_table.setModel(new javax.swing.table.DefaultTableModel(
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
        purchase_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchase_tableMouseClicked(evt);
            }
        });
        purchase_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                purchase_tableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(purchase_table);

        filterText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextActionPerformed(evt);
            }
        });
        filterText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filterTextKeyPressed(evt);
            }
        });

        jLabel1.setText("Search");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        jLabel4.setText("Purchase-id");

        jLabel5.setText("Date-purchase");

        jLabel6.setText("Item-name");

        jLabel7.setText("Brand");

        jLabel8.setText("Model");

        jLabel9.setText("Description");

        jLabel10.setText("Price(BDT)");

        jLabel11.setText("Amount");

        jLabel12.setText("Remark");

        txt_remark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_remarkActionPerformed(evt);
            }
        });

        jButton1.setText("Insert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmd_update.setText("Update");
        cmd_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_updateActionPerformed(evt);
            }
        });

        cmd_delete.setText("Delete");
        cmd_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_deleteActionPerformed(evt);
            }
        });

        cmd_clear.setText("Clear");
        cmd_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_clearActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(txt_description);

        txt_choosedate.setDateFormatString("dd/MM/yyyy");

        chooseitem_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<None of above>" }));
        chooseitem_ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                chooseitem_ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        choosebrand_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<None of above>" }));
        choosebrand_ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                choosebrand_ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/Text-Edit-icon.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cmd_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/report-icon.png"))); // NOI18N
        cmd_report.setText("Report");
        cmd_report.setToolTipText("Reports.....");
        cmd_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_reportActionPerformed(evt);
            }
        });

        cmd_print1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/printer-icon.png"))); // NOI18N
        cmd_print1.setText("Print");
        cmd_print1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_print1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4))
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_itemname, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_brand, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_model, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_amount, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_remark, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_purchaseid, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_datepurchase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(choosebrand_ComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 153, Short.MAX_VALUE)
                                    .addComponent(chooseitem_ComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_choosedate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_last)
                                    .addComponent(txt_number, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addGap(32, 32, 32))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmd_delete)
                        .addGap(4, 4, 4)
                        .addComponent(cmd_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmd_clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmd_report)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmd_print1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_purchaseid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txt_datepurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txt_choosedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_itemname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(chooseitem_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(choosebrand_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_last, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_remark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(cmd_update)
                            .addComponent(cmd_delete)
                            .addComponent(cmd_clear)
                            .addComponent(cmd_report)
                            .addComponent(cmd_print1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_remarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_remarkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_remarkActionPerformed

    private void purchase_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase_tableMouseClicked
        try {

            int row = purchase_table.getSelectedRow();
            String Table_click = (purchase_table.getModel().getValueAt(row, 0).toString());
            String sql = "select * from PurchaseDB where PurchaseID='" + Table_click + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {

                String add1 = rs.getString("PurchaseID");
                txt_purchaseid.setText(add1);
                String add2 = rs.getString("Date_Purchased");
                txt_datepurchase.setText(add2);
                String add3 = rs.getString("Item_name_or_Accesories");
                txt_itemname.setText(add3);
                String add4 = rs.getString("Brand");
                txt_brand.setText(add4);
                String add5 = rs.getString("Model");
                txt_model.setText(add5);
                String add6 = rs.getString("Description");
                txt_description.setText(add6);
                String add7 = rs.getString("Price_BDT");
                txt_price.setText(add7);
                String add8 = rs.getString("Amount");
                txt_amount.setText(add8);
                String add9 = rs.getString("Remark");
                txt_remark.setText(add9);


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
        countHardware();
    }//GEN-LAST:event_purchase_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            String sql = "Insert into PurchaseDB (PurchaseID,Date_purchased,Item_name_or_Accesories,Brand,Model,Description,Price_BDT,Amount,Remark) values (?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql);

            pst.setString(1, txt_purchaseid.getText());
            pst.setString(2, txt_datepurchase.getText());
            pst.setString(3, txt_itemname.getText());
            pst.setString(4, txt_brand.getText());
            pst.setString(5, txt_model.getText());
            pst.setString(6, txt_description.getText());
            pst.setString(7, txt_price.getText());
            pst.setString(8, txt_amount.getText());
            pst.setString(9, txt_remark.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "New Data is added");
            addHardware(txt_amount.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {

                pst.close();
            } catch (Exception e) {
            }
        }

        Update_table();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmd_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_updateActionPerformed
        try {


            String value1 = txt_purchaseid.getText();
            String value2 = txt_datepurchase.getText();
            String value3 = txt_itemname.getText();
            String value4 = txt_brand.getText();
            String value5 = txt_model.getText();
            String value6 = txt_description.getText();
            String value7 = txt_price.getText();
            String value8 = txt_amount.getText();
            String value9 = txt_remark.getText();

            String sql = "update PurchaseDB set Date_purchased='" + value2 + "',Item_name_or_Accesories='" + value3 + "',Brand='" + value4 + "',Model='" + value5 + "',Description='" + value6 + "',Price_BDT='" + value7 + "',Amount='" + value8 + "',Remark='" + value9 + "' where  PurchaseID='" + value1 + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data is Updated");
            updatehardware();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {

                pst.close();
            } catch (Exception e) {
            }
        }
        Update_table();


    }//GEN-LAST:event_cmd_updateActionPerformed

    private void cmd_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_deleteActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete", JOptionPane.YES_NO_OPTION);


        if (p == 0) {
            String sql = "delete from PurchaseDB where PurchaseID =?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_purchaseid.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data is deleted");
                deletehardware();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {

                    pst.close();
                } catch (Exception e) {
                }
            }
            Update_table();


        }
    }//GEN-LAST:event_cmd_deleteActionPerformed

    private void cmd_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_clearActionPerformed
        txt_purchaseid.setText("");
        txt_datepurchase.setText("");
        txt_itemname.setText("");
        txt_brand.setText("");
        txt_model.setText("");
        txt_description.setText("");
        txt_price.setText("");
        txt_amount.setText("");
        txt_remark.setText("");
    }//GEN-LAST:event_cmd_clearActionPerformed

    private void purchase_tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_purchase_tableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
            try {

                int row = purchase_table.getSelectedRow();
                String Table_click = (purchase_table.getModel().getValueAt(row, 0).toString());
                String sql = "select * from PurchaseDB where PurchaseID='" + Table_click + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {

                    String add1 = rs.getString("PurchaseID");
                    txt_purchaseid.setText(add1);



                    String add2 = rs.getString("Date_Purchased");
                    txt_datepurchase.setText(add2);

                    String add3 = rs.getString("Item_name_or_Accesories");
                    txt_itemname.setText(add3);

                    String add4 = rs.getString("Brand");
                    txt_brand.setText(add4);
                    String add5 = rs.getString("Model");
                    txt_model.setText(add5);
                    String add6 = rs.getString("Description");
                    txt_description.setText(add6);
                    String add7 = rs.getString("Price_BDT");
                    txt_price.setText(add7);
                    String add8 = rs.getString("Amount");
                    txt_amount.setText(add8);
                    String add9 = rs.getString("Remark");
                    txt_remark.setText(add9);


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
    }//GEN-LAST:event_purchase_tableKeyReleased

    private void filterTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTextKeyPressed
        // TODO add your handling code here:
        String text = filterText.getText();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(text));
        }
    }//GEN-LAST:event_filterTextKeyPressed

    private void choosebrand_ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_choosebrand_ComboBoxPopupMenuWillBecomeInvisible
        String tmp = (String) choosebrand_ComboBox.getSelectedItem();
        txt_brand.setText(tmp);
    }//GEN-LAST:event_choosebrand_ComboBoxPopupMenuWillBecomeInvisible

    private void chooseitem_ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_chooseitem_ComboBoxPopupMenuWillBecomeInvisible
        String tmp = (String) chooseitem_ComboBox.getSelectedItem();
        txt_itemname.setText(tmp);
    }//GEN-LAST:event_chooseitem_ComboBoxPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String date = ((JTextField) txt_choosedate.getDateEditor().getUiComponent()).getText();
        txt_datepurchase.setText(date);

    }//GEN-LAST:event_jButton2ActionPerformed

    
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
    private void cmd_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_reportActionPerformed
         
        try {

            
            Document doc = new Document();
            PdfAWriter.getInstance(doc, new FileOutputStream("Report"+CurrentDate()+".pdf"));
            doc.open();
            doc.add(new Paragraph("IUT Computer Center :: Purchase Information", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY)));
            doc.add(new Paragraph(new java.util.Date().toString()));
            doc.add(new Paragraph("             "));

            int col = purchase_table.getColumnCount()-5;
            PdfPTable table = new PdfPTable(col);

            for (int i = 0; i < col; i++) {
                String col_name = purchase_table.getColumnName(i);
                table.addCell(new Paragraph(col_name, FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLUE)));
            }
            int row = purchase_table.getRowCount();

            for (int i = 0; i < row; i++) {
                for(int j=0;j<col;j++){
                String temp = (purchase_table.getModel().getValueAt(i,j).toString());
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

    private void cmd_print1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_print1ActionPerformed
        MessageFormat header = new MessageFormat(" IUT Computer Center :: Purchase Information");
        
       

        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {

            purchase_table.print(JTable.PrintMode.NORMAL, header, footer);
            
        } catch (java.awt.print.PrinterException e) {
            System.err.format("can't print", e.getMessage());

        }
        JOptionPane.showMessageDialog(null, "Report Printed");
        
        
    }//GEN-LAST:event_cmd_print1ActionPerformed

    private void filterTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox choosebrand_ComboBox;
    private javax.swing.JComboBox chooseitem_ComboBox;
    private javax.swing.JButton cmd_clear;
    private javax.swing.JButton cmd_delete;
    private javax.swing.JButton cmd_print1;
    private javax.swing.JButton cmd_report;
    private javax.swing.JButton cmd_update;
    private javax.swing.JTextField filterText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable purchase_table;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_brand;
    private com.toedter.calendar.JDateChooser txt_choosedate;
    private javax.swing.JTextField txt_datepurchase;
    private javax.swing.JTextField txt_description;
    private javax.swing.JTextField txt_itemname;
    private javax.swing.JTextField txt_last;
    private javax.swing.JTextField txt_model;
    private javax.swing.JTextField txt_number;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_purchaseid;
    private javax.swing.JTextField txt_remark;
    // End of variables declaration//GEN-END:variables
}
