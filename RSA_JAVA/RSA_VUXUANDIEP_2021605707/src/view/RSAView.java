package view;

import common.Common;
import static common.Common.readFile;
import model.RSA;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;



/**
 *
 * @author DucDang
 */
public class RSAView extends javax.swing.JFrame {

    RSA rsa = new RSA();
    String pathVanBanCanKy;
    String pathChuKy;
    String vanBanCanKy;
    int co = 0;

    /**
     * Creates new form RSAView
     */
    public RSAView() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtP = new javax.swing.JTextField();
        txtQ = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtB = new javax.swing.JTextField();
        txtNPublic = new javax.swing.JTextField();
        txtNPrivate = new javax.swing.JTextField();
        txtA1 = new javax.swing.JTextField();
        btnTaoKhoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnTaoKhoaNgauNhien = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtVanBanKy = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChuKy = new javax.swing.JTextArea();
        btnTaiFile = new javax.swing.JButton();
        btnKy = new javax.swing.JButton();
        btnLuuChuKy = new javax.swing.JButton();
        btnThoat1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtVanBanCanXacNhan1 = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnTaiVanBan1 = new javax.swing.JButton();
        btnTaiChuKy1 = new javax.swing.JButton();
        btnXacNhan1 = new javax.swing.JButton();
        txtXacNhanChuKy2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtChuKy2 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ứng dụng chữ ký RSA"));

        jLabel2.setText("Chọn số nguyên tố");

        jLabel3.setText("P");

        jLabel4.setText("Q");

        txtP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPActionPerformed(evt);
            }
        });

        jLabel1.setText("Tính các giá trị");

        jLabel5.setText("Khóa công khai");

        jLabel6.setText("Khóa bí mật");

        jLabel7.setText("B");

        jLabel8.setText("N");

        jLabel9.setText("A");

        jLabel10.setText("N");

        txtNPublic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNPublicActionPerformed(evt);
            }
        });

        txtA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtA1ActionPerformed(evt);
            }
        });

        btnTaoKhoa.setText("Tạo Khóa");
        btnTaoKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoKhoaActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnTaoKhoaNgauNhien.setText("Tạo khóa P,Q ngẫu nhiên");
        btnTaoKhoaNgauNhien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoKhoaNgauNhienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnTaoKhoa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel7)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel8)
                        .addGap(347, 347, 347)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtNPublic, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtA1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(txtNPrivate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(btnThoat)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtP))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtQ, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnTaoKhoaNgauNhien)))))
                .addGap(244, 384, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoKhoaNgauNhien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNPublic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNPrivate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoKhoa)
                    .addComponent(btnThoat))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tạo khóa", jPanel2);

        jLabel11.setText("Văn bản cần ký");

        jScrollPane1.setViewportView(txtVanBanKy);

        jLabel13.setText("Chữ ký");

        txtChuKy.setColumns(20);
        txtChuKy.setRows(5);
        jScrollPane2.setViewportView(txtChuKy);

        btnTaiFile.setText("Tải File");
        btnTaiFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiFileActionPerformed(evt);
            }
        });

        btnKy.setText("Ký");
        btnKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKyActionPerformed(evt);
            }
        });

        btnLuuChuKy.setText("Lưu chữ ký");
        btnLuuChuKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuChuKyActionPerformed(evt);
            }
        });

        btnThoat1.setText("Chuyển");
        btnThoat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat1ActionPerformed(evt);
            }
        });

        txtVanBanCanXacNhan1.setColumns(20);
        txtVanBanCanXacNhan1.setRows(5);
        jScrollPane4.setViewportView(txtVanBanCanXacNhan1);

        jLabel35.setText("Văn bản cần xác nhận");

        jLabel36.setText("Xác nhận chữ ký");

        btnTaiVanBan1.setText("Tải văn bản");
        btnTaiVanBan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiVanBan1ActionPerformed(evt);
            }
        });

        btnTaiChuKy1.setText("Tải chữ ký");
        btnTaiChuKy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiChuKy1ActionPerformed(evt);
            }
        });

        btnXacNhan1.setText("Kiểm tra");
        btnXacNhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhan1ActionPerformed(evt);
            }
        });

        txtChuKy2.setColumns(20);
        txtChuKy2.setRows(5);
        jScrollPane5.setViewportView(txtChuKy2);

        jLabel12.setText("PHÁT SINH CHỮ KÝ");

        jLabel14.setText("KIỂM TRA CHỮ KÝ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel13)
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addComponent(jLabel36))
                            .addComponent(btnLuuChuKy)
                            .addComponent(btnThoat1)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(btnKy))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaiFile)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel35)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXacNhan1)
                        .addGap(253, 253, 253))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtXacNhanChuKy2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTaiVanBan1)
                                    .addComponent(btnTaiChuKy1))
                                .addGap(13, 13, 13))))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaiVanBan1))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaiChuKy1))
                        .addGap(34, 34, 34)
                        .addComponent(btnXacNhan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtXacNhanChuKy2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaiFile)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addComponent(btnKy)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(btnLuuChuKy)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThoat1))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ký văn bản", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     /*Đoạn code trên được sử dụng để tạo chữ ký số cho một văn bản bằng cách sử dụng thuật toán RSA. 
       Đầu tiên, chương trình kiểm tra xem người dùng đã nhập văn bản hay chưa. 
       Nếu không, nó sẽ hiển thị một thông báo lỗi. 
       Nếu có, nó sẽ tạo một chuỗi chữ ký số cho văn bản đó.
       Đoạn code tiếp theo sử dụng thuật toán MD5 để tạo một mã băm cho văn bản cần ký. 
       Sau đó, nó sử dụng thuật toán RSA để tạo chữ ký số cho mã băm đó.
       Cuối cùng, chương trình sẽ hiển thị chuỗi chữ ký số được tạo ra.
       Đoạn code mới này được sử dụng để xác minh tính toàn vẹn của văn bản đã được ký. 
       Nó sử dụng thuật toán MD5 để tạo mã băm cho văn bản cần xác minh và sau đó so sánh mã băm này với mã băm của văn bản gốc. 
       Nếu hai mã băm khớp nhau, điều đó có nghĩa là văn bản không bị thay đổi và chữ ký số là hợp lệ.   
     */
    //kiểm tra
    private void btnXacNhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhan1ActionPerformed
        try {

            String vanBanCanXacNhan = txtVanBanCanXacNhan1.getText();

            //Generate chu ky voi van ban can xac nhan
            ArrayList<Long> signature = new ArrayList<>();
            String arrayhashMD5 = null;

            arrayhashMD5 = Common.getMD5(vanBanCanXacNhan);

            //Check van van co bi chinh sua hay khong
            String content = txtVanBanKy.getText();
            String rs = Common.getMD5(content);
            if (!rs.equalsIgnoreCase(arrayhashMD5)) {
                txtXacNhanChuKy2.setText("Chữ kí đã được thay đổi");
            }
            if (arrayhashMD5 != null) {
                for (int i = 0; i < arrayhashMD5.length(); i++) {
                    signature.add(rsa.calculatePow(arrayhashMD5.charAt(i), rsa.a, rsa.n));
                }
            }
            StringBuilder chuKyXacNhan = new StringBuilder();
            for (int i = 0; i < signature.size(); i++) {
                chuKyXacNhan.append(signature.get(i));
                chuKyXacNhan.append("-");
            }
            chuKyXacNhan.deleteCharAt(chuKyXacNhan.length() - 1);

            if (!txtChuKy.getText().equalsIgnoreCase(txtChuKy2.getText().trim())) {
                txtXacNhanChuKy2.setText("Chữ kí không chính xác");
            } else if (chuKyXacNhan.toString().equalsIgnoreCase(txtChuKy.getText())) {
                txtXacNhanChuKy2.setText("Chữ kí chính xác");
            } else {
                txtXacNhanChuKy2.setText("Chữ kí không chính xác");
            }

        } catch (HeadlessException | NumberFormatException exc) {
            JOptionPane.showMessageDialog(
                this,
                "Please try or contact to admin!",
                "About",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXacNhan1ActionPerformed

    private void btnTaiChuKy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiChuKy1ActionPerformed
        JFileChooser jfile = new JFileChooser();
        jfile.showOpenDialog(jPanel2);
        File f = jfile.getSelectedFile();
        pathChuKy = f.getAbsolutePath();
        try {
            String content = readFile(pathChuKy);
            txtChuKy2.setText(content);
        } catch (IOException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnTaiChuKy1ActionPerformed

    private void btnTaiVanBan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiVanBan1ActionPerformed
        JFileChooser jfile = new JFileChooser();
        jfile.showOpenDialog(jPanel2);
        File f = jfile.getSelectedFile();
        String ext = f.getName();
        pathVanBanCanKy = f.getAbsolutePath();
        try {
            if (co == 1) {
                String content = txtVanBanCanXacNhan1.getText();
                if (content == "") {
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập văn bản", "About", JOptionPane.ERROR_MESSAGE);
                } else {
                    txtVanBanCanXacNhan1.setText(content);
                }
            }
            if (ext.contains(".txt")) {
                String content = readFile(pathVanBanCanKy);

                txtVanBanCanXacNhan1.setText(content);
            } else {
                FileInputStream fis = new FileInputStream(pathVanBanCanKy);
                XWPFDocument document = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphList = document.getParagraphs();
                String content = "";
                for (XWPFParagraph paragraph : paragraphList) {
                    content += paragraph.getText();
                }

                txtVanBanCanXacNhan1.setText(content);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnTaiVanBan1ActionPerformed

    private void btnThoat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat1ActionPerformed
        String next = txtVanBanKy.getText();
        String next1 = txtChuKy.getText();
        txtVanBanCanXacNhan1.setText(next);
        txtChuKy2.setText(next1);
    }//GEN-LAST:event_btnThoat1ActionPerformed

    private void btnLuuChuKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuChuKyActionPerformed
        JFileChooser jfile = new JFileChooser();
        jfile.showOpenDialog(jPanel2);
        File f = jfile.getSelectedFile();
        pathChuKy = f.getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(pathChuKy);
            String s = txtChuKy.getText();
            byte b[] = s.getBytes();
            fout.write(b);
            fout.close();
            JOptionPane.showMessageDialog(
                this,
                "Lưu chữ ký thành công!",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnLuuChuKyActionPerformed
    //hàm ký
     /*Nó được sử dụng để tạo chữ ký số cho một văn bản bằng cách sử dụng thuật toán RSA.
      Đầu tiên, chương trình kiểm tra xem người dùng đã nhập văn bản hay chưa. 
      Nếu không, nó sẽ hiển thị một thông báo lỗi. 
      Nếu có, nó sẽ tạo một chuỗi chữ ký số cho văn bản đó.
      Đoạn code tiếp theo sử dụng thuật toán MD5 để tạo một mã băm cho văn bản cần ký. 
      Sau đó, nó sử dụng thuật toán RSA để tạo chữ ký số cho mã băm đó.
      Cuối cùng, chương trình sẽ hiển thị chuỗi chữ ký số được tạo ra. 
      */
    private void btnKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKyActionPerformed
        if (co == 1) {
            String content = txtVanBanKy.getText();
            if (content == "") {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập văn bản", "About", JOptionPane.ERROR_MESSAGE);
            } else {
                vanBanCanKy=content;
                String rs = Common.getMD5(content);
               // txtBam.setText(rs);
            }
        } else{
            JOptionPane.showMessageDialog(this, "Bạn chưa tạo khóa", "About", JOptionPane.ERROR_MESSAGE);
        }
        ArrayList<Long> signature = new ArrayList<>();
        String arrayhashMD5 = null;

        arrayhashMD5 = Common.getMD5(vanBanCanKy);
        System.out.println(arrayhashMD5);

        if (arrayhashMD5 != null) {
            for (int i = 0; i < arrayhashMD5.length(); i++) {
                signature.add(rsa.calculatePow(arrayhashMD5.charAt(i), rsa.a, rsa.n));
            }
        }
        StringBuilder signatureString = new StringBuilder();
        for (int i = 0; i < signature.size(); i++) {
            signatureString.append(signature.get(i));
            signatureString.append("-");
        }
        signatureString.deleteCharAt(signatureString.length() - 1);
        txtChuKy.setText(signatureString.toString());
    }//GEN-LAST:event_btnKyActionPerformed
    //đọc nội dung file
    private void btnTaiFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiFileActionPerformed
        JFileChooser jfile = new JFileChooser();
        jfile.showOpenDialog(jPanel2);
        File f = jfile.getSelectedFile();
        String ext = f.getName();
        pathVanBanCanKy = f.getAbsolutePath();
        //".docx"".txt"
        try {
            if (ext.contains(".txt")) {
                String content = readFile(pathVanBanCanKy);
                vanBanCanKy = content;
                txtVanBanKy.setText(content);
                String rs = Common.getMD5(content);
                //txtBam.setText(rs);
            } else {
                FileInputStream fis = new FileInputStream(pathVanBanCanKy);
                XWPFDocument document = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphList = document.getParagraphs();
                String content = "";
                for (XWPFParagraph paragraph : paragraphList) {
                    content += paragraph.getText();
                }
                vanBanCanKy=content;
                txtVanBanKy.setText(content);
                String rs = Common.getMD5(content);
                //txtBam.setText(rs);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnTaiFileActionPerformed
    //Tạo p, q ngẫu nhiên
    /*
    Tạo ra hai số nguyên tố p và q bằng cách lấy ngẫu nhiên hai số nguyên tố 
    từ một mảng các số nguyên tố. Mảng này được khai báo trước đó và chứa 24 số nguyên tố. 
    Sau đó, giá trị của p và q được đặt cho các giá trị trong mảng này. 
    Cuối cùng, giá trị của p và q được hiển thị trong hai ô văn bản khác nhau.
    */
    private void btnTaoKhoaNgauNhienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKhoaNgauNhienActionPerformed
        // TODO add your handling code here:
        long[] primeNumbers = { 4999, 4993, 4987, 4969, 4967, 4961,
                                4957, 4937, 4933, 4931, 4909, 4903, 
                                4889, 4877, 4871, 4861, 4859, 4831, 
                                4817, 4813, 4801, 4787, 4783, 4759};
        Random ran = new Random();
        int id = ran.nextInt(0,17);
        txtP.setText(Long.toString(primeNumbers[id]));
        txtQ.setText(Long.toString(primeNumbers[id+8]));
    }//GEN-LAST:event_btnTaoKhoaNgauNhienActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    // tạo khoá
    /*
    Đoạn mã này lấy hai số nguyên dài p và q làm đầu vào và tạo một đối tượng RSA 
    bằng cách sử dụng các giá trị này. Sau đó, nó kiểm tra xem p và q có phải là số nguyên tố 
    duy nhất hay không. Nếu chúng không duy nhất, một thông báo lỗi được hiển thị. 
    Nếu chúng là duy nhất, mã đặt giá trị của n, b và a cho cả khóa công khai và khóa riêng tư.
    Cuối cùng, giá trị của co được đặt thành 1.
    */
    private void btnTaoKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKhoaActionPerformed
        try {
            long p = Long.parseLong(txtP.getText());
            long q = Long.parseLong(txtQ.getText());
            rsa = new RSA(p, q);
            if (!rsa.checkUiquePQ()) {
                JOptionPane.showMessageDialog(this, "P, Q cần khác nhau", "About", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!rsa.isPrime(p)) {
                JOptionPane.showMessageDialog(this, "P không phải số nguyên tố", "About", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!rsa.isPrime(q)) {
                JOptionPane.showMessageDialog(this, "Q không phải số nguyên tố", "About", JOptionPane.ERROR_MESSAGE);
                return;
            }
            txtNPrivate.setText((Long.toString(rsa.n)));
            txtNPublic.setText(Long.toString(rsa.n));
            txtB.setText(Long.toString(rsa.b));
            txtA1.setText(Long.toString(rsa.a));
            co = 1;
        } catch (Exception e) {
            txtB.setText("");
            txtQ.setText("");
            JOptionPane.showMessageDialog(
                this,
                "P, Q không hợp lệ ",
                "About",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTaoKhoaActionPerformed

    private void txtA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtA1ActionPerformed

    private void txtNPublicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNPublicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNPublicActionPerformed

    private void txtPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKy;
    private javax.swing.JButton btnLuuChuKy;
    private javax.swing.JButton btnTaiChuKy1;
    private javax.swing.JButton btnTaiFile;
    private javax.swing.JButton btnTaiVanBan1;
    private javax.swing.JButton btnTaoKhoa;
    private javax.swing.JButton btnTaoKhoaNgauNhien;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThoat1;
    private javax.swing.JButton btnXacNhan1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtA1;
    private javax.swing.JTextField txtB;
    private javax.swing.JTextArea txtChuKy;
    private javax.swing.JTextArea txtChuKy2;
    private javax.swing.JTextField txtNPrivate;
    private javax.swing.JTextField txtNPublic;
    private javax.swing.JTextField txtP;
    private javax.swing.JTextField txtQ;
    private javax.swing.JTextArea txtVanBanCanXacNhan1;
    private javax.swing.JTextPane txtVanBanKy;
    private javax.swing.JTextField txtXacNhanChuKy2;
    // End of variables declaration//GEN-END:variables
}
