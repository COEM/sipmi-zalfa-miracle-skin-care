/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.view.menu.penjualan;

import aplikasi.config.KoneksiDB;
import aplikasi.config.ValueFormatterFactory;
import aplikasi.controller.TableViewController;
import aplikasi.entity.Barang;
import aplikasi.entity.Pelanggan;
import aplikasi.entity.Penjualan;
import aplikasi.entity.PenjualanDetail;
import aplikasi.repository.RepoPelanggan;
import aplikasi.repository.RepoPenjualan;
import aplikasi.service.ServicePelanggan;
import aplikasi.service.ServicePenjualan;
import aplikasi.view.MainMenuView;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dimmaryanto
 */
public class DataPenjualanView extends javax.swing.JInternalFrame {

    private final RepoPenjualan repoPenjualan;
    private final RepoPelanggan repoPelanggan;
    private final Penjualan penjualan;
    private final MainMenuView menuController;
    private final TableViewController tableController;

    private List<Pelanggan> daftarPelanggan = new ArrayList<>();
    private List<PenjualanDetail> daftarPenjualanBarang = new ArrayList<>();

    /**
     * Creates new form DataPenjualanView
     *
     * @param menuController
     */
    public DataPenjualanView(MainMenuView menuController) {
        this.repoPenjualan = new ServicePenjualan(KoneksiDB.getDataSource());
        this.repoPelanggan = new ServicePelanggan(KoneksiDB.getDataSource());

        this.penjualan = new Penjualan();
        this.menuController = menuController;
        initComponents();

        this.tableController = new TableViewController(tableView);
        this.txtTanggal.setDate(new java.util.Date());
        refreshPelanggan();
        try {
            List<Penjualan> daftarPenjualan = repoPenjualan.findAll();
            StringBuilder kodePenjualan = new StringBuilder("PNJ").append("-");
            kodePenjualan.append(ValueFormatterFactory.getLocalDateShort(LocalDate.now())).append("-");
            kodePenjualan.append(String.format("%02d", daftarPenjualan.size() + 1));
            this.penjualan.setKode(kodePenjualan.toString());
            txtKode.setText(this.penjualan.getKode());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat mendapatkan kode penjualan", getTitle(), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DataPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshPelanggan() {
        try {
            txtKodePelanggan.removeAllItems();
            daftarPelanggan = repoPelanggan.findAll();
            for (Pelanggan pelanggan : daftarPelanggan) {
                txtKodePelanggan.addItem(pelanggan.getKode());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat mendapatkan data pelanggan", getTitle(), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DataPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshDataTable() {
        tableController.clearData();
        Double total = 0D;
        for (PenjualanDetail pd : daftarPenjualanBarang) {
            Double subTotal = (pd.getHarga() * pd.getJumlah()) - pd.getDiskon();
            Barang brg = pd.getBarang();
            Object[] row = {
                brg.getKode(),
                brg.getNama(),
                ValueFormatterFactory.getNumberBulat(pd.getJumlah()),
                ValueFormatterFactory.getCurrency(pd.getHarga()),
                ValueFormatterFactory.getCurrency(pd.getDiskon()),
                ValueFormatterFactory.getCurrency(subTotal)
            };
            total += subTotal;
            tableController.getModel().addRow(row);
        }
        txtTotal.setText(ValueFormatterFactory.getCurrency(total));

    }

    public void tambahBarang(PenjualanDetail pd) {
        daftarPenjualanBarang.add(pd);
        refreshDataTable();
    }

    public void hapusBarang(PenjualanDetail pd) {
        daftarPenjualanBarang.remove(pd);
        refreshDataTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnCetak = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnSimpan = new javax.swing.JButton();
        btnKembeli = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKodePelanggan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelpPelanggan = new javax.swing.JTextField();
        txtKode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        btnTambahBarang = new javax.swing.JButton();
        btnHapusBarang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setTitle("Penjualan");

        jToolBar1.setRollover(true);

        btnCetak.setText("Cetak");
        btnCetak.setFocusable(false);
        btnCetak.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCetak.setMaximumSize(new java.awt.Dimension(120, 35));
        btnCetak.setMinimumSize(new java.awt.Dimension(120, 35));
        btnCetak.setPreferredSize(new java.awt.Dimension(120, 35));
        btnCetak.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCetak);
        jToolBar1.add(jSeparator1);

        btnSimpan.setText("Simpan");
        btnSimpan.setFocusable(false);
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan.setMaximumSize(new java.awt.Dimension(120, 35));
        btnSimpan.setMinimumSize(new java.awt.Dimension(120, 35));
        btnSimpan.setPreferredSize(new java.awt.Dimension(120, 35));
        btnSimpan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSimpan);

        btnKembeli.setText("Kembali");
        btnKembeli.setFocusable(false);
        btnKembeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKembeli.setMaximumSize(new java.awt.Dimension(120, 35));
        btnKembeli.setMinimumSize(new java.awt.Dimension(120, 35));
        btnKembeli.setPreferredSize(new java.awt.Dimension(120, 35));
        btnKembeli.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembeliActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKembeli);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pelanggan"));

        jLabel1.setText("Kode");

        txtKodePelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtKodePelanggan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtKodePelangganItemStateChanged(evt);
            }
        });

        jLabel2.setText("Nama");

        txtNamaPelanggan.setEditable(false);

        jLabel3.setText("Telp");

        txtTelpPelanggan.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKodePelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPelanggan)
                    .addComponent(txtTelpPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKodePelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelpPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtKode.setEditable(false);

        jLabel4.setText("Kode");

        jLabel5.setText("Tanggal");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar Barang"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jToolBar2.setRollover(true);

        btnTambahBarang.setText("Tambah");
        btnTambahBarang.setFocusable(false);
        btnTambahBarang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTambahBarang.setMaximumSize(new java.awt.Dimension(100, 30));
        btnTambahBarang.setMinimumSize(new java.awt.Dimension(100, 30));
        btnTambahBarang.setPreferredSize(new java.awt.Dimension(100, 30));
        btnTambahBarang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTambahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBarangActionPerformed(evt);
            }
        });
        jToolBar2.add(btnTambahBarang);

        btnHapusBarang.setText("Hapus");
        btnHapusBarang.setFocusable(false);
        btnHapusBarang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHapusBarang.setMaximumSize(new java.awt.Dimension(100, 30));
        btnHapusBarang.setMinimumSize(new java.awt.Dimension(100, 30));
        btnHapusBarang.setPreferredSize(new java.awt.Dimension(100, 30));
        btnHapusBarang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHapusBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusBarangActionPerformed(evt);
            }
        });
        jToolBar2.add(btnHapusBarang);

        jPanel3.add(jToolBar2, java.awt.BorderLayout.PAGE_END);

        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama", "Jumlah", "Harga", "Diskon", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableView);
        if (tableView.getColumnModel().getColumnCount() > 0) {
            tableView.getColumnModel().getColumn(0).setMinWidth(120);
            tableView.getColumnModel().getColumn(0).setPreferredWidth(120);
            tableView.getColumnModel().getColumn(0).setMaxWidth(120);
            tableView.getColumnModel().getColumn(1).setMinWidth(150);
            tableView.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableView.getColumnModel().getColumn(2).setMinWidth(80);
            tableView.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableView.getColumnModel().getColumn(2).setMaxWidth(80);
            tableView.getColumnModel().getColumn(3).setMinWidth(120);
            tableView.getColumnModel().getColumn(3).setPreferredWidth(120);
            tableView.getColumnModel().getColumn(3).setMaxWidth(120);
            tableView.getColumnModel().getColumn(4).setMinWidth(100);
            tableView.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableView.getColumnModel().getColumn(4).setMaxWidth(100);
            tableView.getColumnModel().getColumn(5).setMinWidth(120);
            tableView.getColumnModel().getColumn(5).setPreferredWidth(120);
            tableView.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Total");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKode)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodePelangganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtKodePelangganItemStateChanged
        if (txtKodePelanggan.getSelectedItem() != null) {
            Pelanggan pelanggan = daftarPelanggan.get(txtKodePelanggan.getSelectedIndex());
            txtNamaPelanggan.setText(pelanggan.getNama());
            txtTelpPelanggan.setText(pelanggan.getTlp());
        } else {
            txtNamaPelanggan.setText("");
            txtTelpPelanggan.setText("");
        }
    }//GEN-LAST:event_txtKodePelangganItemStateChanged

    private void btnTambahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBarangActionPerformed
        if (txtKodePelanggan.getSelectedItem() != null) {
            Pelanggan pelanggan = daftarPelanggan.get(txtKodePelanggan.getSelectedIndex());
            DataPenjualanDetailView view = new DataPenjualanDetailView(menuController, true, this, penjualan, pelanggan);
            view.setResizable(false);
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Data pelanggan belum dipilih", getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnTambahBarangActionPerformed

    private void btnHapusBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusBarangActionPerformed
        if (tableController.isSelected()) {
            PenjualanDetail pd = daftarPenjualanBarang.get(tableController.getRowSelected());
            hapusBarang(pd);
        } else {
            JOptionPane.showMessageDialog(this, "Data belanjaan belum dipilih", getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusBarangActionPerformed

    private void btnKembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembeliActionPerformed
        try {
            DataPenjualanView view = new DataPenjualanView(menuController);
            this.menuController.setInnerLayout(view);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(DataPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKembeliActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            this.penjualan.setPelanggan(daftarPelanggan.get(txtKodePelanggan.getSelectedIndex()));
            this.penjualan.setTgl(Date.valueOf(ValueFormatterFactory.getDateSQL(txtTanggal.getDate())));
            repoPenjualan.save(penjualan, daftarPenjualanBarang);

            JOptionPane.showMessageDialog(this, "Data penjualan berhasil disimpan", getTitle(), JOptionPane.INFORMATION_MESSAGE);

            try {
                DataPenjualanView view = new DataPenjualanView(menuController);
                this.menuController.setInnerLayout(view);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(DataPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat menyimpan data penjualan", getTitle(), JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(DataPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            String url = "/laporan/NotaPenjualan.jasper";
            Map<String, Object> map = new HashMap<>();
            map.put("kode", this.penjualan.getKode());
            map.put("tanggal", txtTanggal.getDate());

            Pelanggan p = null;
            if (txtKodePelanggan.getSelectedItem() != null) {
                p = daftarPelanggan.get(txtKodePelanggan.getSelectedIndex());
                map.put("kodePelanggan", p.getKode());
                map.put("namaPelanggan", p.getNama());
            }
            Double total = 0D;
            for (PenjualanDetail jual : daftarPenjualanBarang) {
                Double subTotal = jual.getHarga() * jual.getJumlah();
                total += (subTotal - jual.getDiskon());
            }
            map.put("total", total);
            JasperPrint print = JasperFillManager.fillReport(
                    getClass().getResourceAsStream(url),
                    map,
                    new JRBeanCollectionDataSource(daftarPenjualanBarang));
            JasperViewer view = new JasperViewer(print);
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(DataPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapusBarang;
    private javax.swing.JButton btnKembeli;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable tableView;
    private javax.swing.JTextField txtKode;
    private javax.swing.JComboBox<String> txtKodePelanggan;
    private javax.swing.JTextField txtNamaPelanggan;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private javax.swing.JTextField txtTelpPelanggan;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
