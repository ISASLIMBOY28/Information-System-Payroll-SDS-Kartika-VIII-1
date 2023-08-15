/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utama;
import java.sql.*;
import java.text.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.koneksi;
/**
 *
 * @author ThanatosXYZ
 */
public class rekap_gaji extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
NumberFormat formatter = new DecimalFormat("#0.00");
    private DefaultTableModel tabmode;
    static String a, b, c, y, bl, th;
    String nm, jaba, tuna, kd, idra, ms;
    static double gp, ts, tk, tkin, tt, tb, ra, rl, tgp, ttk, ttpk, ttps, ttpt, ttpb, tl, pb, pk, pt, ps, bj, jk, jkt, dapat, persen, mk;
    Date date = new Date(System.currentTimeMillis());
   SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
   String tngl = tgl.format(date);
    /**
     * Creates new form rekap_gaji
     */
    public rekap_gaji() {
        initComponents();
        autonumber();
        datatable();
        this.setLocationRelativeTo(null);
    }
    
    protected void autonumber(){
 String norg="";
 try{
 String sql = "SELECT id_rg FROM rekap_gaji order by id_rg asc";
 PreparedStatement stat = conn.prepareStatement(sql);
 ResultSet rs=stat.executeQuery(sql);
 while(rs.next()){
 norg=rs.getString("id_rg");
 }
 }catch(SQLException sqle){norg="";} 
 if(norg.length() <1){norg="RG0000";}
 String ur=norg.substring(2);
 int u=Integer.parseInt(ur)+1;
 System.out.println(ur+"=="+u);
 if(u<10)
 {ur="000"+u;}
 else if(u<100)
 {ur="00"+u;}
 else if(u<1000)
 {ur="0"+u;}
 else 
 {ur=""+u;}
 norg ="RG"+ur; 
 id.setText(norg);
}

    protected void kosong(){
    idkaryawan.setText("");
    nama.setText("");
    id.setText("");
    kjab.setText("");
    ktun.setText("");
     gapok.setText("");
    tukin.setText("");
    kesra.setText("");
    sehat.setText("");
     beras.setText("");
    transport.setText("");
    id.setText("");
    tgaji.setText("");
    cari.setText("");
    masa.setText("");
    nama.setEnabled(true);
    kjab.setEnabled(true);
    ktun.setEnabled(true);
    masa.setEnabled(true);
         tukin.setEnabled(true);
     beras.setEnabled(true);
     transport.setEnabled(true);
     sehat.setEnabled(true);
     kesra.setEnabled(true);
     gapok.setEnabled(true);
     tgaji.setEnabled(true);
    autonumber();
    datatable();
    }
    
    protected void datatable(){
 Object[] Baris ={"ID Gaji", "ID Karyawan","Bulan","Tahun","Tanggal","T. Gaji Pokok","T. Tukin", "T. Kesra","T. Sehat","T. Transportasi", "T. Beras","Total"};
 tabmode = new DefaultTableModel(null, Baris);
 String find=cari.getText();
 try {
 String sql = "SELECT * FROM rekap_gaji where id_karyawan like '%"+find+"%' or bulan_gaji like '%"+find+"%' or tahun_gaji like '%"+find+"%' or id_rg like '%"+find+"%' or tanggal_gaji like '%"+find+"%' order by id_rg asc";
 java.sql.Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
 tabmode.addRow(new Object[]{
 hasil.getString(1),
 hasil.getString(2),
 hasil.getString(3),
 hasil.getString(4),
 hasil.getString(5),
 hasil.getString(6),
 hasil.getString(7),
 hasil.getString(8),
 hasil.getString(9),
 hasil.getString(10),
 hasil.getString(11),
 hasil.getString(12)
 });
 }
 tablegaji.setModel(tabmode);
 } catch (Exception e) {
     JOptionPane.showMessageDialog(null, "Error tabel rekap_gaji: "+e);
 }
}
    
    public void pilih(){
 popupkaryawan1 pk = new popupkaryawan1();
 pk.rg = this;
 idkaryawan.setText(kd);
 nama.setText(nm);
 kjab.setText(jaba);
 ktun.setText(tuna);
 masa.setText(ms);
 nama.setEnabled(false);
 kjab.setEnabled(false);
 ktun.setEnabled(false);
 masa.setEnabled(false);
 tukin.setEnabled(false);
     beras.setEnabled(false);
     transport.setEnabled(false);
     sehat.setEnabled(false);
     kesra.setEnabled(false);
     gapok.setEnabled(false);
     tgaji.setEnabled(false);
}
    
    public void masag(){
        mk=Double.parseDouble(masa.getText());
        if (mk<2){
            persen=0;
        }else if (mk>=2 && mk<4){
            persen=0.035;
        }else if (mk>=4 && mk<6){
            persen=0.07;
        }else if (mk>=6 && mk<8){
            persen=0.105;
        }else if (mk>=8 && mk<10){
            persen=0.14;
        }else if (mk>=10 &&mk<12){
            persen=0.175;
        }else if (mk>=12 &&mk<14){
            persen=0.21;
        }else if (mk>=14 &&mk<16){
            persen=0.245;
        }else if (mk>=16 &&mk<18){
            persen=0.28;
        }else if (mk>=18 &&mk<20){
            persen=0.315;
        }else if (mk>=20 &&mk<22){
            persen=0.35;
        }else if (mk>=22 &&mk<24){
            persen=0.385;
        }else if (mk>=24 &&mk<26){
            persen=0.42;
        }else if (mk>=26 &&mk<28){
            persen=0.455;
        }else if (mk>=28 &&mk<30){
            persen=0.49;
        }else if (mk>=30 &&mk<32){
            persen=0.525;
        }else if(mk>=32){
            persen=0.56;
        }
        System.out.println(persen);
    }
    
            /**try{
            String sql = "update rekap_gaji set id_karyawan=?,bulan_gaji=?,tahun_gaji=?,tanggal_gaji=?,t_gapok=?,t_tukin=?,t_kesra=?,t_sehat=?,t_transport=?,t_beras=?,t_gaji=? where id_rg='"+id.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
             stat.setString(1, idkaryawan.getText());
            stat.setString(2, bulan.getSelectedItem().toString());
            stat.setString(3, tahun.getText());
            stat.setString(4, tngl);
            stat.setString(5, gapok.getText());
            stat.setString(6, tukin.getText());
            stat.setString(7, kesra.getText());
            stat.setString(8, sehat.getText());
            stat.setString(9, transport.getText());
            stat.setString(10, beras.getText());
            stat.setString(11, tgaji.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data gaji berhasil diedit!");
            kosong();
            id.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error update gaji: "+e);
        } */
    
    public void datagaji(){
       try{
            String sql = "SELECT nama_karyawan, id_jabatan, id_tunjangan, masa FROM karyawan where id_karyawan='"+idkaryawan.getText()+"';";
            Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
    a = hasil.getString("nama_karyawan");
    b = hasil.getString("id_jabatan");
    c = hasil.getString("id_tunjangan");
    y = hasil.getString("masa");
    nama.setText(a);
    kjab.setText(b);
    ktun.setText(c);
    masa.setText(y);
    nama.setEnabled(false);
    kjab.setEnabled(false);
    ktun.setEnabled(false);
    masa.setEnabled(false);
    tukin.setEnabled(false);
     beras.setEnabled(false);
     transport.setEnabled(false);
     sehat.setEnabled(false);
     kesra.setEnabled(false);
     gapok.setEnabled(false);
     tgaji.setEnabled(false);
 }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error search data gaji: "+e);
        }  
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        cari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        gapok = new javax.swing.JTextField();
        kesra = new javax.swing.JTextField();
        sehat = new javax.swing.JTextField();
        transport = new javax.swing.JTextField();
        beras = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tukin = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        tgaji = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        idkaryawan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bulan = new javax.swing.JComboBox<>();
        tahun = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        kjab = new javax.swing.JTextField();
        ktun = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        masa = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablegaji = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rekapitulasi Gaji");

        jButton7.setBackground(new java.awt.Color(211, 171, 171));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        jButton7.setText("Back");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Cari Gaji");

        jPanel4.setBackground(new java.awt.Color(146, 180, 236));

        jPanel3.setBackground(new java.awt.Color(146, 180, 236));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 51), 2, true));

        jLabel6.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total Gaji Pokok");

        jLabel8.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("T. Kesra ");

        jLabel9.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("T. Sehat");

        jLabel10.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("T. Beras");

        jLabel11.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("T. Transport ");

        jLabel22.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("T. Tukin");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tukin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gapok, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kesra, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beras, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sehat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(transport, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sehat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(transport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(beras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(gapok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tukin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kesra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(146, 180, 236));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 51), 2, true));

        jLabel15.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total Gaji");

        jButton6.setBackground(new java.awt.Color(20, 195, 142));
        jButton6.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/submit.png"))); // NOI18N
        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(211, 171, 171));
        jButton4.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 140, 140));
        jButton5.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash.png"))); // NOI18N
        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 239, 130));
        jButton9.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pencil.png"))); // NOI18N
        jButton9.setText("Edit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel15)
                        .addGap(12, 12, 12)
                        .addComponent(tgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tgaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(146, 180, 236));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 51), 2, true));

        jLabel2.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No. Gaji");

        jLabel3.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Karyawan");

        idkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idkaryawanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Karyawan");

        bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        tahun.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bulan dan Tahun");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcu.png"))); // NOI18N
        jButton3.setText("Hitung");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("ID Jabatan");

        jLabel19.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("ID Tunjangan");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/popup.png"))); // NOI18N
        jButton8.setText("PopUp");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Masa Jabatan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kjab, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(ktun, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))
                                    .addComponent(masa, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(kjab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(masa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ktun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tablegaji.setAutoCreateRowSorter(true);
        tablegaji.setBackground(new java.awt.Color(226, 226, 226));
        tablegaji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        tablegaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablegajiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablegaji);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(509, 509, 509))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7)
                        .addGap(478, 478, 478)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idkaryawanActionPerformed
        // TODO add your handling code here:
        datagaji();
    }//GEN-LAST:event_idkaryawanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Search button
        datagaji();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //hitung
        try{
            masag();
            Statement stat = conn.createStatement();
String sql = "select gaji_pokok, tukin from jabatan where id_jabatan='"+kjab.getText()+"'";
 ResultSet h = stat.executeQuery(sql);
    while (h.next()){
        gp = h.getDouble("gaji_pokok");
        double pgp=gp*persen;
        tgp=pgp+gp;
        tkin= h.getDouble("tukin");
        double ptk=tkin*persen;
        ttk = ptk+tkin;
     gapok.setText(String.valueOf(formatter.format(tgp)));
     tukin.setText(String.valueOf(formatter.format(ttk)));
    }
   sql = "select kesra, sehat, transport, beras from tunjangan where id_tunjangan='"+ktun.getText()+"'";
 ResultSet y = stat.executeQuery(sql);
    while (y.next()){
        pk = y.getDouble("kesra");
        double tpk = pk*persen;
        ttpk=pk+tpk;
        ps = y.getDouble("beras");
        double tps = ps*persen;
        ttps=ps+tps;
        pt = y.getDouble("transport");
        double tpt = pt*persen;
        ttpt=pt+tpt;
        pb = y.getDouble("beras");
        double tpb = pb*persen;
        ttpb=pb+tpb;
        kesra.setText(String.valueOf(formatter.format(ttpk)));
     sehat.setText(String.valueOf(formatter.format(ttps)));
     transport.setText(String.valueOf(formatter.format(ttpt)));
     beras.setText(String.valueOf(formatter.format(ttpb)));
    }
    dapat=tgp+ttk+ttpk+ttps+ttpt+ttpb;
     tgaji.setText(String.valueOf(formatter.format(dapat)));
     tukin.setEnabled(false);
     beras.setEnabled(false);
     transport.setEnabled(false);
     sehat.setEnabled(false);
     kesra.setEnabled(false);
     gapok.setEnabled(false);
     tgaji.setEnabled(false);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error hitung gaji: "+e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new dashboardadmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //submit gaji
         try{
        String sql = "INSERT INTO rekap_gaji values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, id.getText());
            stat.setString(2, idkaryawan.getText());
            stat.setString(3, bulan.getSelectedItem().toString());
            stat.setString(4, tahun.getText());
            stat.setString(5, tngl);
            stat.setString(6, gapok.getText());
            stat.setString(7, tukin.getText());
            stat.setString(8, kesra.getText());
            stat.setString(9, sehat.getText());
            stat.setString(10, transport.getText());
            stat.setString(11, beras.getText());
            stat.setString(12, tgaji.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data gaji disimpan!");
            kosong();
            id.requestFocus();
        } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error submit gaji: "+e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //find
        datatable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //cancel button
        kosong();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tablegajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablegajiMouseClicked
        // TODO add your handling code here:
        //klik table
        int bar = tablegaji.getSelectedRow();
 String i = tabmode.getValueAt(bar, 0).toString();
 String j = tabmode.getValueAt(bar, 1).toString();
 String k = tabmode.getValueAt(bar, 2).toString();
 String d = tabmode.getValueAt(bar, 3).toString();
 String f = tabmode.getValueAt(bar, 5).toString();
 String g = tabmode.getValueAt(bar, 6).toString();
 String l = tabmode.getValueAt(bar, 7).toString();
 String m = tabmode.getValueAt(bar, 8).toString();
 String n = tabmode.getValueAt(bar, 9).toString();
 String o = tabmode.getValueAt(bar, 10).toString();
 String p = tabmode.getValueAt(bar, 11).toString();
 id.setText(i);
 idkaryawan.setText(j);
    bulan.setSelectedItem(k);
    tahun.setText(d);
    gapok.setText(f);
     tukin.setText(g);
     kesra.setText(l);
     sehat.setText(m);
     transport.setText(n);
     beras.setText(o);
    tgaji.setText(p);
         tukin.setEnabled(false);
     beras.setEnabled(false);
     transport.setEnabled(false);
     sehat.setEnabled(false);
     kesra.setEnabled(false);
     gapok.setEnabled(false);
     tgaji.setEnabled(false);
    }//GEN-LAST:event_tablegajiMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //deletebutton
 int ok = JOptionPane.showConfirmDialog(null,"Ingin hapus data gaji terpilih?","Konfirmasi",JOptionPane.YES_NO_OPTION);
 if (ok==0){
 String sql = "delete from rekap_gaji where id_rg ='"+id.getText()+"'";
 try{
 PreparedStatement stat = conn.prepareStatement(sql);
 stat.executeUpdate();
 JOptionPane.showMessageDialog(null, "Data gaji dihapus!");
 kosong();
 idkaryawan.requestFocus();
 }
 catch (SQLException e){
 JOptionPane.showMessageDialog(null, "Error delete gaji: "+e);
 }
 }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //popup button
        bl=bulan.getSelectedItem().toString();
        th=tahun.getText();
        popupkaryawan1 pk = new popupkaryawan1();
        pk.rg = this;
        pk.setVisible(true);
        pk.setResizable(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_cariActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        //edit button
        try{
            String sql = "update rekap_gaji set id_karyawan=?,bulan_gaji=?,tahun_gaji=?,tanggal_gaji=?,t_gapok=?,t_tukin=?,t_kesra=?,t_sehat=?,t_transport=?,t_beras=?,t_gaji=? where id_rg='"+id.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
             stat.setString(1, idkaryawan.getText());
            stat.setString(2, bulan.getSelectedItem().toString());
            stat.setString(3, tahun.getText());
            stat.setString(4, tngl);
            stat.setString(5, gapok.getText());
            stat.setString(6, tukin.getText());
            stat.setString(7, kesra.getText());
            stat.setString(8, sehat.getText());
            stat.setString(9, transport.getText());
            stat.setString(10, beras.getText());
            stat.setString(11, tgaji.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data gaji diedit!");
            kosong();
            id.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error edit gaji: "+e);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(rekap_gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rekap_gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rekap_gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rekap_gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new rekap_gaji().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField beras;
    private javax.swing.JComboBox<String> bulan;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField gapok;
    private javax.swing.JTextField id;
    private javax.swing.JTextField idkaryawan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kesra;
    private javax.swing.JTextField kjab;
    private javax.swing.JTextField ktun;
    private javax.swing.JTextField masa;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField sehat;
    private javax.swing.JTable tablegaji;
    private javax.swing.JTextField tahun;
    private javax.swing.JTextField tgaji;
    private javax.swing.JTextField transport;
    private javax.swing.JTextField tukin;
    // End of variables declaration//GEN-END:variables
}
