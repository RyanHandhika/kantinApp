/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kantinapp;
import javax.swing.*;
//fungsi import yang digunakan untuk SQL
import java.sql.*;
//fungsi import yang digunakan untuk Tanggal
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author MRHSCode
 */
public class data_menu extends javax.swing.JFrame {
//deklarasi variabel
koneksi dbsetting;
String driver, database, user, pass;
Object tabel;

    /**
     * Creates new form data_menu
     */
    public data_menu() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_data_menu.setModel(tableModel);
        
        settableload();
        id_kategori();
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    private javax.swing.table.DefaultTableModel getDefaultTableModel()
    {
        return new javax.swing.table.DefaultTableModel
        (
            new Object[][] {},
            new String [] {"Kode Menu",
                           "Kode Kategori",
                           "Nama menu",
                           "Deskripsi",
                           "Harga",
                           "Stok",
                           "Gambar"}
        )
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false
            };
            
            public boolean isCellEditTable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };

    }
    
    String data[] = new String[7];
    private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select * from menu";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public void membersihkan_text()
    {
        txt_kode_menu.setText("");
        combo_kode_kategori.setSelectedItem("");
        txt_nama_menu.setText("");
        txt_deskripsi.setText("");
        txt_harga.setText("");
        txt_stok.setText("");
        txt_gambar.setText("");
    }
    
    public void nonaktif_teks()
    {
        txt_kode_menu.setEnabled(false);
        combo_kode_kategori.setEnabled(false);
        txt_nama_menu.setEnabled(false);
        txt_deskripsi.setEnabled(false);
        txt_harga.setEnabled(false);
        txt_stok.setEnabled(false);
        txt_gambar.setEnabled(false);
    }
    
    public void aktif_text()
    {
        txt_kode_menu.setEnabled(true);
        combo_kode_kategori.setEnabled(true);
        txt_nama_menu.setEnabled(true);
        txt_deskripsi.setEnabled(true);
        txt_harga.setEnabled(true);
        txt_stok.setEnabled(true);
        txt_gambar.setEnabled(true);
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_data_menu.getSelectedRow();
        txt_kode_menu.setText(tableModel.getValueAt(row, 0).toString());
        combo_kode_kategori.setSelectedItem(tableModel.getValueAt(row, 1).toString());
        txt_nama_menu.setText(tableModel.getValueAt(row, 2).toString());
        txt_deskripsi.setText(tableModel.getValueAt(row, 3).toString());
        txt_harga.setText(tableModel.getValueAt(row, 4).toString());
        txt_stok.setText(tableModel.getValueAt(row, 5).toString());
        txt_gambar.setText(tableModel.getValueAt(row, 6).toString());
        btn_tambah.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_cari.setEnabled(true);
        aktif_text();
    }
    
    public void id_kategori()
    {
        try
        {
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            ResultSet res = stt.executeQuery("SELECT * FROM kategori_menu");
            while(res.next())
            {
                combo_kode_kategori.addItem(res.getString("kode_kategori"));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data_menu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_kode_menu = new javax.swing.JTextField();
        txt_nama_menu = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        combo_kode_kategori = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_deskripsi = new javax.swing.JTextArea();
        txt_gambar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("DATA MENU MAKANAN & MINUMAN");

        btn_tambah.setBackground(new java.awt.Color(0, 153, 255));
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("Tambah Menu");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(51, 204, 0));
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setText("Edit Menu");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(204, 0, 0));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus Menu");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_kembali.setText("Kembali");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        btn_cari.setBackground(new java.awt.Color(0, 153, 255));
        btn_cari.setForeground(new java.awt.Color(255, 255, 255));
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        tabel_data_menu.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_data_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_menuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data_menu);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Kode Menu");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Id Kategori");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Nama Menu");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Deskripsi");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Harga");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Stok");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Gambar");

        txt_deskripsi.setColumns(20);
        txt_deskripsi.setRows(5);
        jScrollPane2.setViewportView(txt_deskripsi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_kode_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_nama_menu)
                                        .addComponent(combo_kode_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(190, 190, 190)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(80, 80, 80)
                                .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(97, 97, 97)
                                .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(57, 57, 57)
                                .addComponent(txt_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jLabel2)))
                    .addComponent(txt_kode_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_kode_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nama_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        String kode_menu = txt_kode_menu.getText();
        String kode_kategori = combo_kode_kategori.getSelectedItem().toString();
        String nama_menu = txt_nama_menu.getText();
        String deskripsi = txt_deskripsi.getText();
        String harga = txt_harga.getText();
        String stok = txt_stok.getText();
        String gambar = txt_gambar.getText();
        
        if((kode_menu.isEmpty()) || (kode_kategori.isEmpty()) || (nama_menu.isEmpty()) || (deskripsi.isEmpty()) || (harga.isEmpty()) || (stok.isEmpty()) || (gambar.isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan dilengkapi");
            txt_kode_menu.requestFocus();
        }
        else
        {
            try
            {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE `menu` SET "
                            + "`kode_menu`='" + kode_menu + "', "
                            + "`kode_kategori`='" + kode_kategori + "', "
                            + "`nama_menu`='" + nama_menu + "', "
                            + "`deskripsi`='" + deskripsi + "', "
                            + "`harga`='" + harga + "', "
                            + "`stok`='" + stok + "', "
                            + "`gambar`='" + gambar + "' "
                            + "WHERE `kode_menu`='" + tableModel.getValueAt(row, 0).toString() + "';";
                stt.executeUpdate(SQL);
                data[0] = kode_menu;
                data[1] = kode_kategori;
                data[2] = nama_menu;
                data[3] = deskripsi;
                data[4] = harga;
                data[5] = stok;
                data[6] = gambar;
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_text();
            }
            catch(Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        String data[] = new String[7];
        
        if((txt_kode_menu.getText().isEmpty()) || (txt_nama_menu.getText().isEmpty())|| (txt_deskripsi.getText().isEmpty()) || (txt_harga.getText().isEmpty()) || (txt_stok.getText().isEmpty()) || (txt_gambar.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan lengkapi");
            txt_kode_menu.requestFocus();
        }
        else
        {
            try
            {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "INSERT INTO menu(kode_menu," + "kode_kategori," + "nama_menu," + "deskripsi," + "harga," + "stok," + "gambar)"
                    + "VALUES"
                    + "( '" + txt_kode_menu.getText() + "', "
                    + "'"+ combo_kode_kategori.getSelectedItem() + "', "
                    + "'" + txt_nama_menu.getText() + "', "
                    + "'" + txt_deskripsi.getText() + "', "
                    + "'" + txt_harga.getText() + "', "
                    + "'" + txt_stok.getText() + "', "
                    + "'" + txt_gambar.getText() + "')";
            stt.executeUpdate(SQL);
            data[0] = txt_kode_menu.getText();
            data[1] = combo_kode_kategori.getSelectedItem().toString();
            data[2] = txt_nama_menu.getText();
            data[3] = txt_deskripsi.getText();
            data[4] = txt_harga.getText();
            data[5] = txt_stok.getText();
            data[6] = txt_gambar.getText();
            tableModel.insertRow(0, data);
            stt.close();
            kon.close();
            membersihkan_text();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
       
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tabel_data_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_menuMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1)
        {
            tampil_field();
        }
    }//GEN-LAST:event_tabel_data_menuMouseClicked

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "DELETE FROM menu "
                    + "WHERE "
                    + "kode_menu='"+tableModel.getValueAt(row, 0).toString()+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_text();
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
         dashboard dashboard = new dashboard();
         dashboard.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        // menghapus seluruh isi data didalam tabel
        tableModel.setRowCount(0);
        // gunakan query untuk mencari
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM menu WHERE kode_menu="
                    + "'" +txt_cari.getText()+ "'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        dashboard dashboard = new dashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(data_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox combo_kode_kategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabel_data_menu;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextArea txt_deskripsi;
    private javax.swing.JTextField txt_gambar;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_kode_menu;
    private javax.swing.JTextField txt_nama_menu;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
