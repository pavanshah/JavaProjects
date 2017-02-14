package gesturePack;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.*;
import java.io.*;
import net.n3.nanoxml.*;
import java.util.*;

public class FeedAggregation extends javax.swing.JFrame {
    String parsedTitles[], parsedDescriptions[], parsedLinks[];
    int parseCount;
    MainMenu parent;
    
    public FeedAggregation(MainMenu parent) {
        initComponents();
        Dimension sd  = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth()/ 2, sd.height / 2 - this.getHeight()/ 2);
        
        this.parent = parent;
        jComboURLs.removeAllItems();
        for(int i=0;i<parent.settings.feedList.size();i++) {
            jComboURLs.addItem(parent.settings.feedList.get(i).description);
        }
        jComboGo.removeAllItems();
   }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jComboURLs = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextOutput = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboGo = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 102, 51))); // NOI18N

        jComboURLs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboURLs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboURLsItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SELECT FEED");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("A G G R E G A T E");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA FEED");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextOutput.setColumns(20);
        jTextOutput.setLineWrap(true);
        jTextOutput.setRows(5);
        jScrollPane1.setViewportView(jTextOutput);

        jButton3.setText("CLEAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("WEB LINKS:");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboGo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setText("GO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jButton3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboGo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 422, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton4))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboURLs, 0, 548, Short.MAX_VALUE))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jComboURLs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton3)
                    .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboGo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(new java.awt.Component[] {jButton3, jButton4, jComboGo, jLabel6}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jPanel3Layout.linkSize(new java.awt.Component[] {jComboURLs, jLabel4}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jButton1.setText("B A C K");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("FEED AGGREGATION");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
        if(jComboGo.getSelectedIndex()<0) {
            return;
        }
        try {
            Desktop.getDesktop().browse(new URI(parsedLinks[jComboGo.getSelectedIndex()]));
        }catch(Exception e) {
            System.out.println("Exception: " + e);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
        jTextOutput.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        int c;
        URL theURL;
        URLConnection con;
        String data = "";
        String outData = "";
        boolean errorOccured;
        
        try {
            theURL = new URL(parent.settings.feedList.get(jComboURLs.getSelectedIndex()).url);
            con = theURL.openConnection();
            InputStream input = con.getInputStream();
            c = input.read();
            while(c!=-1) {
                data += (char)c;
                c = input.read();
            }
            input.close();
            errorOccured = false;
        } catch(Exception e) {
            System.out.println("Some exception: " + e);
            data = "Error Connecting To URL\n";
            data += e.toString();
            errorOccured = true;
        }
        
        if(errorOccured) {
            jTextOutput.setText(data);
            return;
        }
        
        // data collected start parsing...
        IXMLElement book;
        IXMLParser xmlParser;
        IXMLReader xmlReader;
        
        parsedTitles = new String[100];
        parsedDescriptions = new String[100];
        parsedLinks = new String[100];
        parseCount = 0;
        
        boolean parseError = false;
        
        try
        {
            // Create the XML parser
            xmlParser = XMLParserFactory.createDefaultXMLParser();
            xmlReader = StdXMLReader.stringReader(data);
            xmlParser.setReader(xmlReader);

            // Read stream and parse it!
            IXMLElement xmlDoc = (IXMLElement) xmlParser.parse();

            System.out.println("Name: " + xmlDoc.getName());
            System.out.println("Version: " + xmlDoc.getAttribute("version","v"));

            Enumeration e1 = xmlDoc.getFirstChildNamed("channel").enumerateChildren();
            while(e1.hasMoreElements()) {
                IXMLElement node = (IXMLElement)e1.nextElement();
                if(node.getName().equals("item")) {
                    parsedTitles[parseCount] = node.getFirstChildNamed("title").getContent();
                    parsedDescriptions[parseCount] = node.getFirstChildNamed("description").getContent();
                    parsedLinks[parseCount] = node.getFirstChildNamed("link").getContent();
                    
                    if(parsedDescriptions[parseCount]==null) {
                        parseCount--;
                    }
                    
                    parseCount++;
                }
            }
        }
        catch (Exception e) {
                e.printStackTrace();
                parseError = true;
        }
        
        if(parseError) {
            jTextOutput.setText("Parser Error!");
            return;
        }
        
        jComboGo.removeAllItems();
        for(int i=0;i<parseCount;i++) {
            outData += (i+1) + "> Title: " + parsedTitles[i] + "\n";
            outData += "Description: " + parsedDescriptions[i] + "\n\n";
            jComboGo.addItem("" + (i+1) + "> " + parsedTitles[i]);
        }
        jTextOutput.setText(outData);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboURLsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboURLsItemStateChanged
// TODO add your handling code here:
        
    }//GEN-LAST:event_jComboURLsItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboGo;
    private javax.swing.JComboBox jComboURLs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextOutput;
    // End of variables declaration//GEN-END:variables
}
