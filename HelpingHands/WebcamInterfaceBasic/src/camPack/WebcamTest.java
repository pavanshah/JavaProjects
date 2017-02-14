package camPack;

import JMyron.JMyron;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import JavaLib.*;
import java.util.TimerTask;
import javax.swing.*;
import javax.imageio.*;

public class WebcamTest extends javax.swing.JFrame {

    public BufferedImage thumbCam, thumbImage;
    public Graphics2D g2DImage;
    public ImageIcon iiImage;
    java.util.Timer t1;
    MyTimerTask tt;
    public boolean running;
    public JMyron m; //a camera object
    public int cw, ch; // camera dimensions
    public int ww, hh; // required dimensions
    public int frameRate; //fps
    MainForm parent;

    public WebcamTest(MainForm parent) {
        this.parent = parent;

        initComponents();
        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);

        cw = 400;
        ch = 300;
        ww = 320;
        hh = 240;

        System.out.println("Initializing Webcam, w:" + cw + ", h:" + ch);
        m = new JMyron();//make a new instance of the object
        m.start(cw, ch);//start a capture at 320x240
        m.findGlobs(0);//disable the intelligence to speed up frame rate
        cw = m.getForcedWidth();
        ch = m.getForcedHeight();
        System.out.println("Forced Dimensions, w:" + cw + ", h:" + ch);

        m.stop();

        // Reinitializing with required dimensions
        System.out.println("Re-Initializing Webcam, w:" + cw + ", h:" + ch);
        m = new JMyron();//make a new instance of the object
        m.start(cw, ch);//start a capture at camera dimensions
        m.findGlobs(0);//disable the intelligence to speed up frame rate


        thumbCam = new BufferedImage(cw, ch, BufferedImage.TYPE_INT_RGB);
        thumbImage = new BufferedImage(ww, hh, BufferedImage.TYPE_INT_RGB);
        g2DImage = thumbImage.createGraphics();
        iiImage = new ImageIcon(thumbImage);
        jLabelFeed.setIcon(iiImage);

        running = false;
        t1 = new java.util.Timer();
        tt = new MyTimerTask();
        t1.schedule(tt, 100, 100);
    }

    class MyTimerTask extends TimerTask {

        public MyTimerTask() {
            ;
        }

        public void run() {
            if (!running) {
                return;
            }
            updateImage();
        }
    }

    public void updateImage() {
        m.update();//update the camera view
        int[] img = m.image(); //get the normal image of the camera
        thumbCam.setRGB(0, 0, cw, ch, img, 0, cw);
        g2DImage.drawImage(thumbCam, 0, 0, ww, hh, null);
        jLabelFeed.repaint();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelFeed = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jBLoadNew = new javax.swing.JButton();
        jBLoadNew1 = new javax.swing.JButton();
        jBLoadNew2 = new javax.swing.JButton();
        jBLoadNew3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Video Feed"));

        jLabelFeed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagePack/Back320x240.PNG"))); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelFeed)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelFeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        new LoadForm();

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control Panel"));

        jBLoadNew.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBLoadNew.setText("Start Feed");
        jBLoadNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNewActionPerformed(evt);
            }
        });

        jBLoadNew1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBLoadNew1.setText("Stop Feed");
        jBLoadNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNew1ActionPerformed(evt);
            }
        });

        jBLoadNew2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBLoadNew2.setText("Settings");
        jBLoadNew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNew2ActionPerformed(evt);
            }
        });

        jBLoadNew3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBLoadNew3.setText("Save Image");
        jBLoadNew3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNew3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("B A C K");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jBLoadNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jBLoadNew1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jBLoadNew2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jBLoadNew3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(new java.awt.Component[] {jBLoadNew, jBLoadNew1, jBLoadNew2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel4Layout.linkSize(new java.awt.Component[] {jBLoadNew3, jButton1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jBLoadNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBLoadNew1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBLoadNew2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBLoadNew3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton1)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(new java.awt.Component[] {jBLoadNew, jBLoadNew1, jBLoadNew2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jPanel4Layout.linkSize(new java.awt.Component[] {jBLoadNew3, jButton1}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("WEBCAM INTERFACE TEST");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBLoadNew3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoadNew3ActionPerformed
// TODO add your handling code here:
        if (!running) {
            new MessageBox(this, "Please Start Webcam Feed First!").setVisible(true);
            return;
        }

        String fname;
        BufferedImage thumbSave;
        Graphics2D g2DSave;

        thumbSave = new BufferedImage(ww, hh, BufferedImage.TYPE_INT_RGB);
        g2DSave = thumbSave.createGraphics();

        m.update();//update the camera view
        int[] img = m.image(); //get the normal image of the camera
        thumbCam.setRGB(0, 0, cw, ch, img, 0, cw);

        g2DSave.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2DSave.drawImage(thumbCam, 0, 0, ww, hh, null);

        FileDialog fd = new FileDialog(this, "Select File To Save To", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() == null) {
            return;
        }
        fname = fd.getDirectory() + fd.getFile();
        if (!fname.endsWith(".png")) {
            fname += ".png";
        }

        try {
            ImageIO.write(thumbSave, "png", new File(fname));
            System.out.println("Image File Written Successfully!");
        } catch (Exception e) {
            System.out.println("Error Writing File: " + e);
        }

    }//GEN-LAST:event_jBLoadNew3ActionPerformed

    private void jBLoadNew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoadNew2ActionPerformed
// TODO add your handling code here:
        m.settings();
    }//GEN-LAST:event_jBLoadNew2ActionPerformed

    private void jBLoadNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoadNew1ActionPerformed
// TODO add your handling code here:
        running = false;
    }//GEN-LAST:event_jBLoadNew1ActionPerformed

    private void jBLoadNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoadNewActionPerformed
// TODO add your handling code here:
        running = true;

    }//GEN-LAST:event_jBLoadNewActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        running = false;
        m.stop();
        setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBLoadNew;
    private javax.swing.JButton jBLoadNew1;
    private javax.swing.JButton jBLoadNew2;
    private javax.swing.JButton jBLoadNew3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFeed;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
