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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ImageIcon;
import java.awt.Image;
/**
 *
 * @author MRHSCode
 */
class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public void setValue(Object value) {
        if (value != null && value instanceof String) {
            String path = "src/gambar_menu/" + value;
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(img));
            setText("");
        } else {
            setIcon(null);
            setText("No Image");
        }
    }
}

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
        
        txt_gambar.setEditable(false);
        txt_gambar.setBackground(java.awt.Color.LIGHT_GRAY);
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_data_menu.setModel(tableModel);
        tabel_data_menu.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());
        
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
            String SQL = "select * from menu where id_user = '" + session.id_user + "'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(3);
                data[2] = res.getString(4);
                data[3] = res.getString(5);
                data[4] = res.getString(6);
                data[5] = res.getString(7);
                data[6] = res.getString(8);
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
    
    private String pathGambar = "";
    private String namaFileGambar = "";

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
        jPanel2 = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btn_tampil_semua_data = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_pilih_gambar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(44, 118, 149));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA MENU MAKANAN & MINUMAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(188, 188, 188))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addContainerGap(45, Short.MAX_VALUE))
        );

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Kode Menu");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Kode Kategori");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Nama Menu");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Deskripsi");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Harga");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Stok");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Gambar");

        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });

        combo_kode_kategori.setMinimumSize(new java.awt.Dimension(38, 26));
        combo_kode_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_kode_kategoriActionPerformed(evt);
            }
        });

        txt_deskripsi.setColumns(20);
        txt_deskripsi.setRows(5);
        jScrollPane2.setViewportView(txt_deskripsi);

        jPanel2.setBackground(new java.awt.Color(44, 118, 149));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_tambah.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambah.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/register.png"))); // NOI18N
        btn_tambah.setText("Tambah Menu");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btn_edit.setText("    Edit Menu");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hapus.png"))); // NOI18N
        btn_hapus.setText("Hapus Menu");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_kembali.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/kembali.png"))); // NOI18N
        btn_kembali.setText(" Kembali");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(247, 194, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/kantin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_kembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_tampil_semua_data.setBackground(new java.awt.Color(51, 153, 255));
        btn_tampil_semua_data.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_tampil_semua_data.setText("Tampilkan Semua Data");
        btn_tampil_semua_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampil_semua_dataActionPerformed(evt);
            }
        });

        btn_cari.setBackground(new java.awt.Color(51, 153, 255));
        btn_cari.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(btn_tampil_semua_data, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tampil_semua_data, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_pilih_gambar.setText("...");
        btn_pilih_gambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilih_gambarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_kode_kategori, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)
                                    .addComponent(txt_nama_menu)
                                    .addComponent(txt_kode_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel6))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_gambar)
                                        .addGap(4, 4, 4)
                                        .addComponent(btn_pilih_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_kode_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_kode_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nama_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(txt_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pilih_gambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        if (kode_menu.isEmpty() || kode_kategori.isEmpty() || nama_menu.isEmpty() ||
            deskripsi.isEmpty() || harga.isEmpty() || stok.isEmpty() || gambar.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan lengkapi");
            txt_kode_menu.requestFocus();
            return;
        }

        try {
            // Upload gambar baru jika ada file baru dipilih
            if (pathGambar != null && !pathGambar.isEmpty()) {
                File folder = new File("src/gambar_menu");
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                File source = new File(pathGambar);
                File dest = new File(folder, namaFileGambar);
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Update database
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            Timestamp waktuSekarang = new Timestamp(new Date().getTime());

            String SQL = "UPDATE menu SET " +
                         "kode_kategori='" + kode_kategori + "', " +
                         "nama_menu='" + nama_menu + "', " +
                         "deskripsi='" + deskripsi + "', " +
                         "harga='" + harga + "', " +
                         "stok='" + stok + "', " +
                         "gambar='" + namaFileGambar + "', " +
                         "update_at='" + waktuSekarang + "' " +
                         "WHERE kode_menu='" + tableModel.getValueAt(row, 0).toString() + "'";

            stt.executeUpdate(SQL);

            // Update ke tabel
            String[] data = new String[7];
            data[0] = kode_menu;
            data[1] = kode_kategori;
            data[2] = nama_menu;
            data[3] = deskripsi;
            data[4] = harga;
            data[5] = stok;
            data[6] = namaFileGambar;

            tableModel.removeRow(row);
            tableModel.insertRow(row, data);

            stt.close();
            kon.close();
            membersihkan_text();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        String data[] = new String[7];

        if (txt_kode_menu.getText().isEmpty() || 
            txt_nama_menu.getText().isEmpty() ||
            txt_deskripsi.getText().isEmpty() ||
            txt_harga.getText().isEmpty() || 
            txt_stok.getText().isEmpty() ||
            txt_gambar.getText().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan lengkapi");
            txt_kode_menu.requestFocus();
            return;
        }

        try {
            // Pastikan folder image/ ada sebelum copy
            File folder = new File("src/gambar_menu");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Salin file gambar ke folder image/
           File source = new File(pathGambar);
           File dest = new File(folder, namaFileGambar);
           Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Simpan ke database
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            Timestamp waktuSekarang = new Timestamp(new Date().getTime());

            String SQL = "INSERT INTO menu(kode_menu, id_user, kode_kategori, nama_menu, deskripsi, harga, stok, gambar, created_at, update_at) " +
                         "VALUES('" + txt_kode_menu.getText() + "', " +
                         "'" + session.id_user + "', " +
                         "'" + combo_kode_kategori.getSelectedItem() + "', " +
                         "'" + txt_nama_menu.getText() + "', " +
                         "'" + txt_deskripsi.getText() + "', " +
                         "'" + txt_harga.getText() + "', " +
                         "'" + txt_stok.getText() + "', " +
                         "'" + namaFileGambar + "', " +
                         "'" + waktuSekarang + "', " +
                         "'" + waktuSekarang + "')";

            stt.executeUpdate(SQL);

            // Tambahkan ke table
            data[0] = txt_kode_menu.getText();
            data[1] = combo_kode_kategori.getSelectedItem().toString();
            data[2] = txt_nama_menu.getText();
            data[3] = txt_deskripsi.getText();
            data[4] = txt_harga.getText();
            data[5] = txt_stok.getText();
            data[6] = namaFileGambar;
            tableModel.insertRow(0, data);

            stt.close();
            kon.close();
            membersihkan_text();

            JOptionPane.showMessageDialog(null, "Data menu berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace(); // untuk debug di console
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        if ((txt_kode_menu.getText().isEmpty()) || (txt_nama_menu.getText().isEmpty()) || (txt_deskripsi.getText().isEmpty()) || (txt_harga.getText().isEmpty()) || (txt_stok.getText().isEmpty()) || (txt_gambar.getText().isEmpty())) {

            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan lengkapi");
            txt_kode_menu.requestFocus();

        } else {
            try {
                // Ambil nama file gambar dari tabel (kolom ke-6)
                String namaGambar = tableModel.getValueAt(row, 6).toString();

                // Hapus file gambar dari folder
                File fileGambar = new File("src/gambar_menu/" + namaGambar);
                if (fileGambar.exists()) {
                    fileGambar.delete();
                }

                // Hapus data dari database
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "DELETE FROM menu WHERE kode_menu='" + tableModel.getValueAt(row, 0).toString() + "'";
                stt.executeUpdate(SQL);

                // Hapus baris dari tabel
                tableModel.removeRow(row);
                stt.close();
                kon.close();
                membersihkan_text();

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        dashboard dashboard_frame = new dashboard();
        dashboard_frame.setVisible(true);
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
        dashboard dashboard_frame = new dashboard();
        dashboard_frame.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btn_tampil_semua_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampil_semua_dataActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        settableload();
    }//GEN-LAST:event_btn_tampil_semua_dataActionPerformed

    private void combo_kode_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_kode_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_kode_kategoriActionPerformed

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void btn_pilih_gambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilih_gambarActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Gambar Menu");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Gambar", "jpg", "jpeg", "png"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            pathGambar = selectedFile.getAbsolutePath();
            namaFileGambar = selectedFile.getName();

            txt_gambar.setText(namaFileGambar);
        }
    }//GEN-LAST:event_btn_pilih_gambarActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

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
    private javax.swing.JButton btn_pilih_gambar;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil_semua_data;
    private javax.swing.JComboBox combo_kode_kategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
