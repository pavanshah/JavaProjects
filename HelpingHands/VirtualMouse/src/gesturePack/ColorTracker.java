

package gesturePack;
import JMyron.JMyron;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import JavaLib.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class ColorTracker extends javax.swing.JFrame {
    MainMenu parent;
    
    public BufferedImage thumbIn, thumbGeneric, thumbHue, thumbSaturation, thumbThreshold ;
    public Graphics2D g2DIn, g2DGeneric, g2DHue, g2DSaturation, g2DThreshold;
    public ImageIcon iiGeneric, iiHue, iiThreshold, iiSaturation;
    public int pixelsGeneric[][], pixelsHSV[][][], pixelsThreshold[][], pixelsTemp[][];
    
    Timer gestureTestTimer;
    public boolean runningGestureTestTT;
    
    public boolean enableScan;
    
    public JMyron m; //a camera object
    public int cw, ww;
    public int ch, hh;

    boolean initialized, initNow;
    public int setX, setY, currX, currY;
    
    int updateIndex = -1, lockedX, lockedY;

    class MyTimerTask extends TimerTask{
        public MyTimerTask() {
            ;
        }
        public void run() {
            if(!runningGestureTestTT) {
                return;
            }
            updateImage();
        }
    }
    
    public ColorTracker(MainMenu parent) {
        this.parent = parent;
        
        initComponents();
        Dimension sd  = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth()/ 2, sd.height / 2 - this.getHeight()/ 2);
        

        // webcam resolution - will change automatically...
        cw = 800;
        ch = 600;
        
        // processed resolution
        ww = 320;
        hh = 240;
        
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
        
        // Buffered Images
        thumbIn = new BufferedImage(cw,ch, BufferedImage.TYPE_INT_RGB);
        thumbGeneric = new BufferedImage(ww,hh, BufferedImage.TYPE_INT_RGB);
        thumbHue = new BufferedImage(ww,hh, BufferedImage.TYPE_INT_RGB);
        thumbSaturation = new BufferedImage(ww,hh, BufferedImage.TYPE_INT_RGB);
        thumbThreshold = new BufferedImage(ww,hh, BufferedImage.TYPE_INT_RGB);
        
        g2DIn = thumbIn.createGraphics();
        g2DGeneric = thumbGeneric.createGraphics();
        g2DHue = thumbHue.createGraphics();
        g2DSaturation = thumbSaturation.createGraphics();
        g2DThreshold = thumbThreshold.createGraphics();
        
        iiGeneric  = new ImageIcon(thumbGeneric);
        iiHue = new ImageIcon(thumbHue);
        iiSaturation = new ImageIcon(thumbSaturation);
        iiThreshold = new ImageIcon(thumbThreshold);
        
        pixelsGeneric = new int [hh][ww];
        pixelsHSV = new int [hh][ww][3];
        pixelsTemp = new int [hh][ww];
        pixelsThreshold = new int [hh][ww];
        for(int y=0;y<hh;y++) {
            for(int x=0;x<ww;x++) {
                pixelsGeneric[y][x] = 0;
                pixelsHSV[y][x][0] = 0;
                pixelsHSV[y][x][1] = 0;
                pixelsHSV[y][x][2] = 0;
                pixelsThreshold[y][x] = 0;
                pixelsTemp[y][x] = 0;
            }
        }
        
        jLabelGeneric.setIcon(iiGeneric);
        jLabelHue.setIcon(iiHue);
        jLabelSaturation.setIcon(iiSaturation);
        jLabelThreshold.setIcon(iiThreshold);
        
        initialized = false;
        initNow = false;
        
        enableScan = false;
        
        // webcam timer
        runningGestureTestTT = false;
        gestureTestTimer = new Timer();
        MyTimerTask gestureTestTT = new MyTimerTask();
        gestureTestTimer.schedule(gestureTestTT,100,100);
        
        try { new ProcessBuilder(System.getProperty("user.dir") + "\\sapi\\sapi.exe").start(); } catch(Exception e) { ; }
        
    }
    
    public void updateImage() {
        int col, r, g, b;
        int rgbMax, rgbMin, h, s, v;

        h = s = 0;
        m.update();//update the camera view
        int[] img = m.image(); //get the normal image of the camera
        thumbIn.setRGB(0,0,cw,ch,img,0,cw); // copy the img matrix to thumbIn buffered image
        
        g2DGeneric.drawImage(thumbIn, 0, 0, ww, hh, null);
        jLabelGeneric.repaint();
        for(int yy=0;yy<hh;yy++) {
            for(int xx=0;xx<ww;xx++) {
                pixelsGeneric[yy][xx] = thumbGeneric.getRGB(xx,yy);
            }
        }

        // blur
        int sumR, sumG, sumB;
        for(int yy=2;yy<hh-2;yy++) {
            for(int xx=2;xx<ww-2;xx++) {
                sumR = sumG = sumB = 0;
                for(int y=yy-2;y<=yy+2;y++) {
                    for(int x=xx-2;x<=xx+2;x++) {
                        sumR += (pixelsGeneric[y][x] >> 16) & 0xff;
                        sumG += (pixelsGeneric[y][x] >> 8) & 0xff;
                        sumB += (pixelsGeneric[y][x]) & 0xff;
                    }
                }
                sumR /= 25;
                sumG /= 25;
                sumB /= 25;
                pixelsTemp[yy][xx] = (sumR << 16) | (sumG << 8) | sumB;
            }
        }
        for(int yy=2;yy<hh-2;yy++) {
            for(int xx=2;xx<ww-2;xx++) {
                pixelsGeneric[yy][xx] = pixelsTemp[yy][xx];
            }
        }

        // converting to HSV...
        for(int yy=0;yy<hh;yy++) {
            for(int xx=0;xx<ww;xx++) {
                col = pixelsGeneric[yy][xx];
                r = (col >> 16) & 0xff;
                g = (col >>  8) & 0xff;
                b = (col) & 0xff;

                rgbMin = Math.min(Math.min(r,g),b);
                rgbMax = Math.max(Math.max(r,g), b);
                v = rgbMax;
                if(v==0) {
                    h = s = 0;
                }else {
                    s = 255 * (rgbMax-rgbMin)/v;
                    if(s==0) {
                        h=0;
                    }else {
                        if(rgbMax == r) {
                            h = 0 + 43*(g-b)/(rgbMax-rgbMin);
                        }else if(rgbMax == g) {
                            h = 85 + 43*(b-r)/(rgbMax-rgbMin);
                        }else if(rgbMax == b) {
                            h = 171 + 43*(r-g)/(rgbMax-rgbMin);
                        }
                    }
                }
                if(h<0) {
                    h = 255+h;
                }

                pixelsHSV[yy][xx][0] = h;
                pixelsHSV[yy][xx][1] = s;
                pixelsHSV[yy][xx][2] = v;
                
                if(updateIndex!=-1) {
                    if(yy==lockedY & xx==lockedX) {
                        parent.db.list[updateIndex].hRequired = h;
                        parent.db.list[updateIndex].sMin = Math.max(0, s-50);
                        parent.db.list[updateIndex].vMin = Math.max(0,v-50);
                        updateIndex = -1;
                    }
                }

                thumbHue.setRGB(xx,yy,pixelsHSV[yy][xx][0] | (pixelsHSV[yy][xx][0] << 8) | (pixelsHSV[yy][xx][0] << 16));
                thumbSaturation.setRGB(xx,yy,pixelsHSV[yy][xx][1] | (pixelsHSV[yy][xx][1] << 8) | (pixelsHSV[yy][xx][1] << 16));

                col = 0;
                for(int i=0;i<1;i++) {
                    if(hInRange(h,parent.db.list[i].hRequired) && sInRange(s,parent.db.list[i].sMin) && vInRange(v,parent.db.list[i].vMin)) {
                        col = 0xFFFFFF;
                    }
                }
                
                pixelsThreshold[yy][xx] = col;
                thumbThreshold.setRGB(xx, yy, pixelsThreshold[yy][xx]);
            }
        }
        
        // detecting y axis only
        int cySum=0;
        int cyTotal=0;
        int cy = -1;
        for(int y=10;y<hh-10;y++) {
            int count = 0;
            for(int x=10;x<ww-10;x++) {
                if(pixelsThreshold[y][x]!=0) {
                    count ++;
                }
            }
            if(count > 40) {
                cySum += y;
                cyTotal++;
            }
            
            if(cyTotal==40) {
                cy = cySum / cyTotal;
                g2DThreshold.setColor(Color.red);
                g2DThreshold.drawLine(0, cy, ww, cy);
                break;
            }
        }

        // detecting x axis only
        int cx=-1;
        for(int y=10;y<hh-10;y+=5) {
            for(int x=10;x<ww-10;x+=5) {
                if(pixelsThreshold[y][x]!=0 && blob(x,y)) {
                    cx = x;
                    g2DThreshold.setColor(Color.green);
                    g2DThreshold.drawLine(cx, 0, cx, hh);
                    break;
                }else if(pixelsThreshold[y][x]!=0) {
                    break;
                }
            }
            if(cx!=-1) {
                break;
            }
        }

        jLabelHue.repaint();
        jLabelSaturation.repaint();

        if(cx!=-1 && cy!=-1) {
            g2DThreshold.setColor(Color.blue);
            g2DThreshold.drawOval(cx-10, cy-10, 20, 20);
            
            if(initNow) {
                setX = cx;
                setY = cy;
                initNow = false;
                initialized = true;
            }

            String out = "";
            if(initialized) {
                currX = cx;
                currY = cy;

                if(currX<setX && setX-currX>12) {
                    out += "TURBO LEFT\n";
                }else if(currX<setX && setX-currX>6) {
                    out += "LEFT\n";
                }else if(currX>setX && currX-setX>12) {
                    out += "TURBO RIGHT\n";
                }else if(currX>setX && currX-setX>6) {
                    out += "RIGHT\n";
                }else {
                    out += "IDLE HORIZ.\n";
                }

                if(currY<setY && setY-currY>10) {
                    out += "TURBO UP\n";
                }else if(currY<setY && setY-currY>5) {
                    out += "UP\n";
                }else if(currY>setY && currY-setY>10) {
                    out += "TURBO DOWN\n";
                }else if(currY>setY && currY-setY>5) {
                    out += "DOWN\n";
                }else {
                    out += "IDLE VERT.\n";
                }
            }else {
                out = "NOT YET INITIALISED";
            }
            jTextStatus.setText(out);

        }
        jLabelThreshold.repaint();
        
    }


    public boolean blob(int x, int y) {
        int count = 0;
        for(int xx=x-5;xx<=x+5;xx++) {
            for(int yy=y-5;yy<=y+5;yy++) {
                if(pixelsThreshold[yy][xx]!=0) {
                    count++;
                }
            }
        }
        if(count>80) {
            return true;
        }
        return false;
    }
    
    public boolean hInRange(int h, int hRequired) {
        if(Math.abs(hRequired - h) <= 20)
            return true;
        if(Math.abs(hRequired - (h-255)) <= 20)
            return true;
        return false;
    }

    public boolean sInRange(int s, int sMin) {
        if(s > sMin)
            return true;
        return false;
    }

    public boolean vInRange(int v, int vMin) {
        if(v > vMin) {
            return true;
        }
        return false;
    }    

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        new LoadForm();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelGeneric = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelSaturation = new javax.swing.JLabel();
        jLabelSValue = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelHue = new javax.swing.JLabel();
        jLabelThreshold = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelHValue = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextStatus = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("320x240 SCALED FEED");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelGeneric.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgPack/Back320x240.PNG"))); // NOI18N
        jLabelGeneric.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelGenericMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Saturation");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelSaturation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgPack/Back320x240.PNG"))); // NOI18N
        jLabelSaturation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSaturationMouseClicked(evt);
            }
        });

        jLabelSValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelSValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSValue.setText("S");
        jLabelSValue.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabelGeneric, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelSaturation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 143, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelSValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelGeneric)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jLabelSValue))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelSaturation)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hue");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelHue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgPack/Back320x240.PNG"))); // NOI18N
        jLabelHue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHueMouseClicked(evt);
            }
        });

        jLabelThreshold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgPack/Back320x240.PNG"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Threshold Output");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelHValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelHValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHValue.setText("H");
        jLabelHValue.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelHValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabelThreshold, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabelHue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jLabelHValue))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelHue)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelThreshold)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton5.setText("R E S E T - C E N T E R");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("VECTOR");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextStatus.setColumns(20);
        jTextStatus.setEditable(false);
        jTextStatus.setRows(5);
        jScrollPane1.setViewportView(jTextStatus);

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
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton1)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("CAMERA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton3)
                    .add(jButton4))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Square721 BT", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("COLOR TRACKER");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jLabelHueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHueMouseClicked
// TODO add your handling code here:
       

    }//GEN-LAST:event_jLabelHueMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
        if(!runningGestureTestTT) {
            return;
        }        
        initNow = true;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabelGenericMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelGenericMouseClicked
// TODO add your handling code here:

        if(evt.getButton()==MouseEvent.BUTTON1) {
            System.out.println("Button1");
            updateIndex = 0;
        }else if(evt.getButton()==MouseEvent.BUTTON3) {
            System.out.println("Button2");
            updateIndex = 1;
        }else {
            return;
        }
        
        if(evt.getX() < ww && evt.getY() < hh) {
            lockedX = evt.getX();
            lockedY = evt.getY();
        }else {
            updateIndex = -1;
        }
        
    }//GEN-LAST:event_jLabelGenericMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
        runningGestureTestTT = false;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        try { new File(System.getProperty("user.dir") + "\\sapi\\command.txt").delete(); } catch(Exception e) { ; }
        runningGestureTestTT = true;
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:

        parent.updateFeatures();
        
        Vector <String> cmds = new Vector <String> ();
        cmds.add("taskkill");
        cmds.add("/F");
        cmds.add("/IM");
        cmds.add("SAPI.EXE");
        
        try { new ProcessBuilder(cmds).start(); } catch(Exception e) { ; }
        
        enableScan = false;
        runningGestureTestTT = false;
        gestureTestTimer.cancel();
        
        m.stop();        
        setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelSaturationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSaturationMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabelSaturationMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        m.settings();

        
    }//GEN-LAST:event_jButton4ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelGeneric;
    private javax.swing.JLabel jLabelHValue;
    private javax.swing.JLabel jLabelHue;
    private javax.swing.JLabel jLabelSValue;
    private javax.swing.JLabel jLabelSaturation;
    private javax.swing.JLabel jLabelThreshold;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextStatus;
    // End of variables declaration//GEN-END:variables
    
}
