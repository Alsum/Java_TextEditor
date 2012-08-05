/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Notepad.java
 *
 * Created on Jan 2, 2011, 4:25:07 PM
 */
package sumnotepad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author ELSUM
 */
public class Notepad extends javax.swing.JFrame {

    /** Creates new form Notepad */
    public Notepad() {
        initComponents();
        jLabel2.setVisible(false);
        getGui();

    }
    boolean txtChanged ;

    private void getGui() {
        jButton1.addActionListener(new open());
        jMenuItem1.addActionListener(new open());
        jButton2.addActionListener(new save());
        jMenuItem2.addActionListener(new save());
        jButton3.addActionListener(new cut());
        jMenuItem3.addActionListener(new cut());
        jButton4.addActionListener(new copy());
        jMenuItem4.addActionListener(new copy());
        jButton5.addActionListener(new paste());
        jMenuItem5.addActionListener(new paste());
        jMenuItem6.addActionListener(new exit());

    }
// <editor-fold defaultstate="collapsed" desc="Search">
    final Highlighter hilit = new DefaultHighlighter();
    final Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
    final static Color HILIT_COLOR = Color.LIGHT_GRAY;
    final static Color ERROR_COLOR = Color.PINK;

    public void search() {

        jTextArea1.setHighlighter(hilit);
        hilit.removeAllHighlights();

        String s = jTextField1.getText();
        if (s.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Nothing to search");
            return;
        }

        String content = jTextArea1.getText();
        int index = content.indexOf(s, 0);
        if (index >= 0) {   // match found
            try {
                int end = index + s.length();
                hilit.addHighlight(index, end, painter);
                jTextArea1.setCaretPosition(end);
                JOptionPane.showMessageDialog(this, "'" + s + "' found. Press ESC to end search");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        } else {
            jTextField1.setBackground(ERROR_COLOR);
            JOptionPane.showMessageDialog(this, "'" + s + "' not found. Press ESC to start a new search");
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="open">

    class open implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser choose = new JFileChooser("c:/");
            choose.showOpenDialog(rootPane);
            FileFilter filter = new FileFilter() {

                boolean selected = true;

                @Override
                public boolean accept(File f) {

                    return selected == f.getName().toLowerCase().endsWith(".txt");

                }

                @Override
                public String getDescription() {
                    return "TextFiles";
                }
            };
            choose.setFileFilter(filter);
            File scr = choose.getSelectedFile();
            try {
                FileInputStream in = new FileInputStream(scr);
                DataInputStream data = new DataInputStream(in);
                BufferedReader br = new BufferedReader(new InputStreamReader(data));
                StringBuilder str = new StringBuilder();
                String strLine = null;
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console
                    //System.out.println (strLine);
                    str.append(strLine);
                    str.append("\n");
                }
                jTextArea1.setText(str.toString());

                //Close the input stream
                in.close();
            } catch (IOException ex) {
                //System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
            } catch (Exception x) {//Catch exception if any
                //System.out.println(x.getLocalizedMessage());
                x.printStackTrace();
            }

        }
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Save">
    class save implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser ch = new JFileChooser();
            ch.showSaveDialog(rootPane);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new PrintWriter(ch.getSelectedFile()));
                String text = jTextArea1.getText();


                bw.write(text);

            } catch (Exception ex) {
                ex.getMessage();
            } finally {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }

            }

        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="cut">

    class cut implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            jTextArea1.cut();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="paste">

    class paste implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            jTextArea1.paste();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="copy">

    class copy implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            jTextArea1.copy();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="exit">

    class exit implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="file extension">

    public void fileExtension() {
        String fileName = "";
        String fname = "";
        String ext = "";
        int mid = fileName.lastIndexOf(".");
        fname = fileName.substring(0, mid);
        ext = fileName.substring(mid + 1, fileName.length());
        System.out.println("File name =" + fname);
        System.out.println("Extension =" + ext);
    }// </editor-fold>

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jnotepad");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/open.png"))); // NOI18N
        jButton1.setToolTipText("open");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/save.png"))); // NOI18N
        jButton2.setToolTipText("save");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/cut.png"))); // NOI18N
        jButton3.setToolTipText("cut");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/copy.png"))); // NOI18N
        jButton4.setToolTipText("copy");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/paste.png"))); // NOI18N
        jButton5.setToolTipText("paste");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 14));
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Find");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton6.setText("GO");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setText("Press F1 for help");

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("save");
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("open");
        jMenu1.add(jMenuItem1);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setText("exit");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("copy");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("paste");
        jMenu2.add(jMenuItem5);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("cut");
        jMenu2.add(jMenuItem3);

        jCheckBoxMenuItem1.setText("line warp");
        jCheckBoxMenuItem1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItem1ItemStateChanged(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("View");

        jCheckBoxMenuItem2.setText("status bar");
        jCheckBoxMenuItem2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItem2ItemStateChanged(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("help");

        jMenuItem7.setText("About author");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton6)
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int res;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (jTextArea1.getText().equals("")) {
            txtChanged = false;
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        if (txtChanged) {
            res = JOptionPane.showConfirmDialog(
                    this, //parent
                    "Do You Want To Save Changes",
                    "File Exit",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (res == JOptionPane.YES_OPTION) {
                new save().actionPerformed(null);

            } else if (res == JOptionPane.CANCEL_OPTION) {
               // jTextArea1.append("");
               this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            } else {
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }

        if (res == JOptionPane.CANCEL_OPTION) {
        }


    }//GEN-LAST:event_formWindowClosing

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        // TODO add your handling code here:
        txtChanged = true;
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        Thread t = new Thread(new Help());
        t.start();
        System.out.println(t.getName());

//         Help h=new Help();
//         h.setLocationRelativeTo(this);
//         h.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCheckBoxMenuItem2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ItemStateChanged
        // TODO add your handling code here:
        if (jCheckBoxMenuItem2.isSelected() == true) {
            jLabel2.setVisible(true);
        } else {
            jLabel2.setVisible(false);
        }

    }//GEN-LAST:event_jCheckBoxMenuItem2ItemStateChanged

    private void jCheckBoxMenuItem1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ItemStateChanged
        // TODO add your handling code here:
        if (jCheckBoxMenuItem1.isSelected() == true) {
            jTextArea1.setLineWrap(true);
        } else {
            jTextArea1.setLineWrap(false);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ItemStateChanged

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            JOptionPane.showMessageDialog(rootPane, "Ask Google Before Asking Me :D");
        }
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        jTextArea1KeyPressed(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Notepad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
