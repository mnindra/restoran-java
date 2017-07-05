/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.Position;
import org.javalite.http.Get;
import org.javalite.http.Http;
import org.javalite.http.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utility.Session;
/**
 *
 * @author aka
 */
public class PemesananForm extends BaseForm {

    /**
     * Creates new form PemesananForm
     */
        
    public JSONArray menu = new JSONArray();
    public JSONArray pesanan = new JSONArray();
    public int total;
    
    public PemesananForm() {
        initComponents();
        getMenu();
    }
    
    public void addPesanan(JSONObject json) {
       
        // cek apakah id_menu sudah ada
        int index = 0;
        boolean found = false;
        for(int i = 0; i < pesanan.size(); i++) {
            JSONObject jsonLineItem = (JSONObject) pesanan.get(i);
            
            if(jsonLineItem.get("id_menu") == json.get("id_menu")) {
                found = true;
                index = i;
                break;
            }
        }
        
        if(found) {
            // tambahkan jumlah menu apabila menu sudah dipesan
            JSONObject jsonCurrentPesanan = (JSONObject) pesanan.get(index);
            int jumlah = (int) jsonCurrentPesanan.get("jumlah");
            int subtotal = (int) json.get("subtotal") + (int) jsonCurrentPesanan.get("subtotal");
            jumlah++;
            jsonCurrentPesanan.put("jumlah", jumlah);
            jsonCurrentPesanan.put("subtotal", subtotal);
            pesanan.set(index, jsonCurrentPesanan);            
        } else {
            // jika menu belum ada di pesanan maka tambahkan ke pesanan            
            this.pesanan.add(json);
        }
               
        System.out.println(this.pesanan);
       
        this.total += (int) json.get("subtotal");
        label_total.setText(Integer.toString(this.total));
        
        System.out.println("total : " + this.total);
       
        this.renderPesanan();
    }
    
    public void removePesanan(int id_menu) {
        
        for(int i = 0; i < pesanan.size(); i++) {
            JSONObject jsonLineItem = (JSONObject) pesanan.get(i);
            
            if((int) jsonLineItem.get("id_menu") == id_menu) {
                this.total -= (int) jsonLineItem.get("subtotal");
                pesanan.remove(i);
                break;
            }
        }
        
        System.out.println(this.pesanan);
        label_total.setText(Integer.toString(this.total));
        this.renderPesanan();
    }
    
    public void getMenu() {
        Get get = Http.get(baseURL + "menu");
        
        JSONParser parser = new JSONParser();
        
        try {
            JSONObject json = (JSONObject) parser.parse(get.text());
            JSONArray arrMenu = (JSONArray) json.get("menu");
            
            this.menu = arrMenu;
            this.renderMenu();
            
            System.out.println(this.menu);
            
        } catch (ParseException ex) {
            Logger.getLogger(PemesananForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void renderMenu() {
        
        panel_menu.removeAll();
        
        for(Object o : this.menu) {
            JSONObject jsonLineItem = (JSONObject) o;
            JSONObject kategoriJson = (JSONObject) jsonLineItem.get("kategori");
                
            int id_menu = (int) (long) jsonLineItem.get("id_menu");
            String nama = String.valueOf(jsonLineItem.get("nama"));
            String kategori = String.valueOf(kategoriJson.get("nama"));
            String gambar = String.valueOf(jsonLineItem.get("gambar"));
            int harga = (int) (long) jsonLineItem.get("harga");
            panel_menu.add(new MenuPanel(id_menu, nama, harga, kategori, gambar, this));
        }
        
        panel_menu.revalidate();
        panel_menu.repaint();
    }
    
    public void renderPesanan() {
        panel_pesanan.removeAll();
        
        JSONParser parser = new JSONParser();
        
        for(Object o : this.pesanan) {
            JSONObject jsonLineItem = (JSONObject) o;
            
            Get get = Http.get(baseURL + "menu/" + jsonLineItem.get("id_menu"));
            
            try {
                JSONObject menuJson = (JSONObject) parser.parse(get.text());
                JSONObject kategoriJson = (JSONObject) menuJson.get("kategori");
                
                int id_menu = (int) (long) menuJson.get("id_menu");
                String nama = String.valueOf(menuJson.get("nama"));
                int subtotal = (int) (long) menuJson.get("harga") * (int) jsonLineItem.get("jumlah");
                int harga = (int) (long) menuJson.get("harga");
                int jumlah = (int) jsonLineItem.get("jumlah");
                String kategori = String.valueOf(kategoriJson.get("nama"));
                String gambar = String.valueOf(menuJson.get("gambar"));

                panel_pesanan.add(new PesananPanel(id_menu, nama, subtotal, jumlah, harga, kategori, gambar, this));
                
            } catch (ParseException ex) {
                Logger.getLogger(PemesananForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        panel_pesanan.revalidate();
        panel_pesanan.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel_menu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panel_pesanan = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_selesai = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        label_total = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jLabel2.setFont(new java.awt.Font("Hack", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Nama Menu");

        jTextField1.setBackground(new java.awt.Color(250, 250, 250));
        jTextField1.setFont(new java.awt.Font("Hack", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Hack", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Kategori");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel_menu.setBackground(new java.awt.Color(250, 250, 250));
        panel_menu.setLayout(new javax.swing.BoxLayout(panel_menu, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(panel_menu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 470, 630));

        jPanel2.setBackground(new java.awt.Color(4, 182, 169));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBackground(new java.awt.Color(4, 182, 169));
        jScrollPane3.setBorder(null);

        panel_pesanan.setBackground(new java.awt.Color(4, 182, 169));
        panel_pesanan.setLayout(new javax.swing.BoxLayout(panel_pesanan, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane3.setViewportView(panel_pesanan);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 586, 540));

        jPanel5.setBackground(new java.awt.Color(46, 63, 81));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_selesai.setBackground(new java.awt.Color(4, 182, 169));
        btn_selesai.setFont(new java.awt.Font("Hack", 0, 14)); // NOI18N
        btn_selesai.setForeground(new java.awt.Color(255, 255, 255));
        btn_selesai.setText("Selesai");
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });
        jPanel5.add(btn_selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 107, 40));

        jLabel5.setFont(new java.awt.Font("Hack", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Bayar : ");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 60));

        label_total.setFont(new java.awt.Font("Hack", 0, 18)); // NOI18N
        label_total.setForeground(new java.awt.Color(255, 255, 255));
        label_total.setText(Integer.toString(this.total));
        jPanel5.add(label_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 60));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 610, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 610, 630));

        jPanel3.setBackground(new java.awt.Color(46, 63, 81));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Hack", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Daftar Pesanan");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 215, 40));

        jLabel4.setFont(new java.awt.Font("Hack", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Daftar Menu");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 457, 40));

        jLabel6.setFont(new java.awt.Font("Hack", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Meja " + Session.nama);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 215, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
        // TODO add your handling code here:
        
        Post post = Http.post(baseURL + "pemesanan")
                        .param("id_meja", Integer.toString(Session.id_meja))
                        .param("total", Integer.toString(this.total))
                        .param("pesanan", String.valueOf(this.pesanan));
        
        if (post.responseCode() == 200) {
            JOptionPane.showMessageDialog(this, "Pemesanan berhasil silahkan tunggu pesanan anda datang", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            this.total = 0;
            this.pesanan = new JSONArray();
            this.renderPesanan();
            label_total.setText(Integer.toString(this.total));
        } else {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan, silahkan coba kembali", "Gagal", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_selesaiActionPerformed

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
            java.util.logging.Logger.getLogger(PemesananForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PemesananForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PemesananForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PemesananForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PemesananForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_selesai;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label_total;
    private javax.swing.JPanel panel_menu;
    private javax.swing.JPanel panel_pesanan;
    // End of variables declaration//GEN-END:variables
}
