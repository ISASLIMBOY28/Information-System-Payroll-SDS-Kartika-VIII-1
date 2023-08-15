/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utama;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.util.GregorianCalendar;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.*;
import database.koneksi;
import static java.lang.String.format;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ThanatosXYZ
 */
public class absen extends javax.swing.JFrame implements Runnable{
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    int timerun=0;
    String idabsen="";
    /**
     * Creates new form absen
     */
    public absen() {
        initComponents();
        Thread t = new Thread(this);
        t.start();
        aktif();
        datatable();
        autonumber();
        this.setLocationRelativeTo(null);
    }
    
    protected void aktif(){
 idkaryawan.requestFocus();
}
    protected void kosong(){
        idkaryawan.setText("");
        nama.setText("");
    }
    
    protected void autonumber(){
 try{
 String sql = "SELECT id_absensi FROM absen order by id_absensi asc";
 PreparedStatement stat = conn.prepareStatement(sql);
 ResultSet rs=stat.executeQuery(sql);
 while(rs.next()){
 idabsen=rs.getString("id_absensi");
 }
 }catch(SQLException sqle){idabsen="";} 
 if(idabsen.length() <1){
 idabsen="IN0000";
 }
 String ur=idabsen.substring(2);
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
 idabsen ="IN"+ur; 
}
    
    protected void datatable(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
   String tngl = tgl.format(date);
   ltanggal.setText(tngl);
 Object[] Baris ={"ID Karyawan","Tanggal","Jam Masuk","Jam Pulang"};
 tabmode = new DefaultTableModel(null, Baris);
 try {
 String sql = "SELECT id_karyawan, tanggal_absen, jam_masuk, jam_pulang FROM absen where tanggal_absen='"+tngl+"' order by tanggal_absen asc ";
 java.sql.Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
 tabmode.addRow(new Object[]{
 hasil.getString(1),
 hasil.getString(2),
 hasil.getString(3),
 hasil.getString(4)
 });
 }
 tableabsen.setModel(tabmode);
 } catch (Exception e) {
     JOptionPane.showMessageDialog(null, "Error tabel absen harian: "+e);
 }
}
    
     public void run(){
        while (true){
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minute = cal.get(Calendar.MINUTE);
                    int second = cal.get(Calendar.SECOND);
                    Date dat = cal.getTime();
                    SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
                    String time24 = sdf24.format(dat);
                    jam.setText(time24);
        }
    }
     
     public void datakaryawan(){
         try{
            String sql = "SELECT nama_karyawan FROM karyawan where id_karyawan='"+idkaryawan.getText()+"';";
            Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
    String a = hasil.getString("nama_karyawan");
    nama.setText(a);
 }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error search karyawan absen: "+e);
        }
     }
     
     /**
     public void cekwaktu(){
         Date date = new Date();
         SimpleDateFormat frmt = new SimpleDateFormat("HH:mm:ss");
         String jam = frmt.format(date);
    LocalTime startLocalTime = LocalTime.parse("17:59:59");
    LocalTime endLocalTime = LocalTime.parse("23:59:59");
    LocalTime checkLocalTime = LocalTime.parse(jam);
    
    boolean isInBetween = false;
    if (endLocalTime.isAfter(startLocalTime)) {
      if (startLocalTime.isBefore(checkLocalTime) && endLocalTime.isAfter(checkLocalTime)) {
          isInBetween = true;
      }
    } else if (checkLocalTime.isAfter(startLocalTime) || checkLocalTime.isBefore(endLocalTime)) {
        isInBetween = true;
    }

    if (isInBetween) {
        JOptionPane.showMessageDialog(null, "Anda telat masuk kerja! Batas masuk kerja sampai jam 12:00 WIB!");
    } else {
        absenmasuk();
    }
     } 
     
     * SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    String jamkeluar = formatter.format(date);
   SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
   String tngl = tgl.format(date);
      try{
        String sql = "SELECT jam_masuk FROM absen where id_karyawan like '%"+idkaryawan.getText()+"%' and tanggal_absen = '"+tngl+"';";
    Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
    String a = hasil.getString("jam_masuk");
    Date d1 = null;
    Date d2 = null;
    d1 = formatter.parse(a);
    d2 = formatter.parse(jamkeluar);
    long diff = d2.getTime() - d1.getTime();
    long days = TimeUnit.MILLISECONDS.toDays(diff);
        long remainingHoursInMillis = diff - TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(remainingHoursInMillis);
        if (hours<4){
            JOptionPane.showMessageDialog(null, "Jangan malas! Minimal jam kerja adalah 4 jam");
        } else{
        sql = "update absen set jam_pulang=?where id_karyawan ='"+idkaryawan.getText()+"' and tanggal_absen = '"+tngl+"';";
        PreparedStatement sta = conn.prepareStatement(sql);
        sta.setString(1, jamkeluar);
        sta.executeUpdate();
 JOptionPane.showMessageDialog(null, "Anda sudah keluar kerja!");
 idkaryawan.requestFocus();
 }
 }
        }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error submit absen keluar: "+e);
        }
     
     */
    
     
     public void absenmasuk(){
         SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    String jammasuk = formatter.format(date);
   SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
   String tngl = tgl.format(date);
    try{
       String sql = "insert into absen values (?,?,?,?,?)";
       PreparedStatement stat = conn.prepareStatement(sql);
       stat.setString(1, idabsen);
       stat.setString(2, idkaryawan.getText());
 stat.setString(3, tngl);
 stat.setString(4, jammasuk);
 stat.setString(5, null);
 stat.executeUpdate();
 JOptionPane.showMessageDialog(null, "Anda sudah masuk kerja!");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error submit absen masuk: "+e);
    }
     kosong();
     datatable();
     autonumber();
     }
     
     
     public void absenkeluar(){
         SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    String jamkeluar = formatter.format(date);
   SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
   String tngl = tgl.format(date);
      try{
        String sql = "update absen set jam_pulang=?where id_karyawan ='"+idkaryawan.getText()+"' and tanggal_absen = '"+tngl+"';";
         PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, jamkeluar);
        stat.executeUpdate();
 JOptionPane.showMessageDialog(null, "Anda sudah keluar kerja!");
 idkaryawan.requestFocus();
 }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error submit absen keluar: "+e);
        }
      kosong();
     datatable();
     autonumber();
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        idkaryawan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jam = new javax.swing.JLabel();
        ltanggal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableabsen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));

        jButton1.setBackground(new java.awt.Color(20, 195, 142));
        jButton1.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entry.png"))); // NOI18N
        jButton1.setText("Absen Masuk");
        jButton1.setMaximumSize(new java.awt.Dimension(129, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(129, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(129, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(211, 171, 171));
        jButton2.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        jButton2.setText("Absen Pulang");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(146, 180, 236));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 51), 2, true));

        idkaryawan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idkaryawanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID Karyawan");

        jLabel2.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nama Karyawan");

        nama.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton3.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        jButton3.setText("Find");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setBackground(new java.awt.Color(211, 171, 171));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Absensi Karyawan");

        jam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jam.setForeground(new java.awt.Color(255, 255, 255));
        jam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jam.setText("12:01:58");

        ltanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ltanggal.setForeground(new java.awt.Color(255, 255, 255));
        ltanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltanggal.setText("Selasa, 20 Januari 2024");
        ltanggal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tableabsen.setBackground(new java.awt.Color(226, 226, 226));
        tableabsen.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tableabsen.setModel(new javax.swing.table.DefaultTableModel(
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
        tableabsen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tableabsen);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(163, 163, 163)
                                .addComponent(jLabel5))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ltanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(jam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltanggal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jam)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Button Absen Masuk
        if(idkaryawan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan ID Karyawan anda!");
        } else{
         absenmasuk();   
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //BUtton Absen Keluar
        if(idkaryawan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan ID Karyawan anda!");
        } else{
         absenkeluar();   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idkaryawanActionPerformed
        // TODO add your handling code here:
        datakaryawan();
    }//GEN-LAST:event_idkaryawanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:'
        datakaryawan();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new login().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new absen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idkaryawan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jam;
    private javax.swing.JLabel ltanggal;
    private javax.swing.JTextField nama;
    private javax.swing.JTable tableabsen;
    // End of variables declaration//GEN-END:variables
}
