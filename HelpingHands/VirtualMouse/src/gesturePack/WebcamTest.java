

package gesturePack;

import JMyron.JMyron;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import JavaLib.*;
import com.sun.image.codec.jpeg.*;
import java.util.TimerTask;
import javax.swing.*;
import javax.imageio.*;



public class WebcamTest extends javax.swing.JFrame {
    MainMenu parent;
    public BufferedImage thumbImage2;
    public Graphics2D graphics2D2;
    
    java.util.Timer t1;
    MyTimerTask tt;
    public boolean running;
    
    BufferedImage bi;
    public JMyron m; //a camera object
    public int cw, sw;
    public int ch, sh;
    public int frameRate; //fps

    class MyTimerTask extends TimerTask{
        public MyTimerTask() {
            ;
        }
        public void run() {
            if(!running) {
                return;
            }
            updateImage();
        }
    }
    
    public WebcamTest(MainMenu parent) {
        this.parent = parent;
        initComponents();
        Dimension sd  = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth()/ 2, sd.height / 2 - this.getHeight()/ 2);
        
        cw = 400;
        ch = 300;
        sw = 320;
        sh = 240;
        
        System.out.println("Initializing Webcam, w:" + cw + ", h:" + ch);
        m = new JMyron();//make a new instance of the object
        m.start(cw,ch);//start a capture at 320x240
        m.findGlobs(0);//disable the intelligence to speed up frame rate
        cw = m.getForcedWidth();
        ch = m.getForcedHeight();
        System.out.println("Forced Dimensions, w:" + cw + ", h:" + ch);
        
        m.stop();
        
        // Reinitializing with required dimensions
        System.out.println("Re-Initializing Webcam, w:" + cw + ", h:" + ch);
        m = new JMyron();//make a new instance of the object
        m.start(cw,ch);//start a capture at 320x240
        m.findGlobs(0);//disable the intelligence to speed up frame rate
        cw = m.getForcedWidth();
        ch = m.getForcedHeight();
        System.out.println("Forced Dimensions, w:" + cw + ", h:" + ch);
        bi = new BufferedImage(cw,ch, BufferedImage.TYPE_INT_ARGB);
        
        running = false;
        t1 = new java.util.Timer();
        tt = new MyTimerTask();
        t1.schedule(tt,100,100);
    }
    
    public void updateImage() {
        m.update();//update the camera view
        int[] img = m.image(); //get the normal image of the camera
        bi.setRGB(0,0,cw,ch,img,0,cw);
        ImageIcon ii = new ImageIcon(bi);
        jLabelFeed.setIcon(ii);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jBLoadNew = new javax.swing.JButton();
        jBLoadNew1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabelFeed = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBLoadNew.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBLoadNew.setText("START");
        jBLoadNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNewActionPerformed(evt);
            }
        });

        jBLoadNew1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBLoadNew1.setText("STOP");
        jBLoadNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNew1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("B A C K");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelFeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFeed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgPack/Back320x240.PNG"))); // NOI18N
        jLabelFeed.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelFeed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFeedMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("WEBCAM FEED");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jBLoadNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jBLoadNew1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .add(152, 152, 152)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .add(jLabelFeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(new java.awt.Component[] {jBLoadNew, jBLoadNew1, jButton1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jLabelFeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 266, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .add(278, 278, 278)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jBLoadNew)
                    .add(jBLoadNew1)
                    .add(jButton1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        new LoadForm();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Square721 BT", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("CAM INTERFACE TEST");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(72, 72, 72)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1)
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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

    private void jLabelFeedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFeedMouseClicked
// TODO add your handling code here:
        m.settings();
        
    }//GEN-LAST:event_jLabelFeedMouseClicked

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFeed;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
    
}
