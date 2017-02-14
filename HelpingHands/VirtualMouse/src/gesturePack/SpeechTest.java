

package gesturePack;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class SpeechTest extends javax.swing.JFrame {
    MainMenu parent;
    
    Timer mouseTestTimer;
    public boolean running;
    
    public SpeechTest(MainMenu parent) {
        this.parent = parent;
        
        initComponents();
        Dimension sd  = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth()/ 2, sd.height / 2 - this.getHeight()/ 2);
        
        mouseTestTimer = new Timer();
        ScanTimerTask mouseTestTimerTask = new ScanTimerTask();
        mouseTestTimer.schedule(mouseTestTimerTask,100,100);

//        writeCommands();

//        try {
//            new ProcessBuilder(System.getProperty("user.dir") + "\\SAPIOS\\SAPI.EXE").start();
//        }catch(Exception e) {
//            System.out.println("Exception: " + e);
//        }

    }

//    public void writeCommands() {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\SAPIOS\\cmdList.txt"));
//            for(int i=0;i<parent.settings.appLinks.size();i++) {
//                bw.write(parent.settings.appLinks.get(i).command.toUpperCase());
//                bw.write(13);
//                bw.write(10);
//            }
//            for(int i=0;i<parent.settings.feedList.size();i++) {
//                bw.write(parent.settings.feedList.get(i).description.toUpperCase());
//                bw.write(13);
//                bw.write(10);
//            }
//            for(int i=0;i<parent .settings.fixedCommands.length;i++) {
//                bw.write(parent.settings.fixedCommands[i].toUpperCase());
//                bw.write(13);
//                bw.write(10);
//            }
//
//            bw.close();
//        }catch(Exception e ){
//            System.out.println("Exception Writing CMD LIST: " + e);
//        }
//    }

    class ScanTimerTask extends TimerTask{
        public ScanTimerTask() {
            ;
        }
        public void run() {
            if(!running) {
                return;
            }
            update();
        }
    }

    
    public void update() {
        // click scanner...
        try {
            File f = new File(System.getProperty("user.dir") + "\\command.txt");
            String cmd = "";
            if(f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\command.txt"));
                cmd = br.readLine();
                System.out.println("CMD: " + cmd);
                br.close();
            }
            cmd = cmd.toUpperCase();
            if(!cmd.equals("")) {
                addClick(cmd);
            }
            f.delete();
        }catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    
    public void addClick(String str) {
        jTextScanner.setText(str + "\n" + jTextScanner.getText());
    }
    

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        ;
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextScanner = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("COMMAND SCANNER");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));

        jTextScanner.setColumns(20);
        jTextScanner.setEditable(false);
        jTextScanner.setRows(5);
        jScrollPane2.setViewportView(jTextScanner);

        jButton6.setText("C L E A R");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("S T A R T");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("S T O P");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("B A C K");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .add(jButton6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton3)
                    .add(jButton1))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SPEECH INTERFACE TEST");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// TODO add your handling code here:
        jTextScanner.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
        running = false;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        try { new File(System.getProperty("user.dir") + "\\sapi\\command.txt").delete(); } catch(Exception e) { ; }
        running  = true;
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
//        Vector <String> cmds = new Vector <String> ();
//        cmds.add("taskkill");
//        cmds.add("/F");
//        cmds.add("/IM");
//        cmds.add("SAPI.EXE");
//        try { new ProcessBuilder(cmds).start(); } catch(Exception e) { ; }

        running  = false;
        mouseTestTimer.cancel();
        
        setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextScanner;
    // End of variables declaration//GEN-END:variables
    
}
