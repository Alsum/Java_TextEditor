package sumnotepad;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class MyPad extends JFrame implements ActionListener, KeyListener
{
boolean txtChanged = false;
String fname = "";
JMenuBar mbar;
JMenu mnuFile, mnuEdit, mnuHelp;
JMenuItem fileNew, fileOpen, fileSave, fileExit;
JMenuItem editCut, editCopy, editPaste, editSelectAll, editDel;
JMenuItem helpAbout;

JToolBar tlbr;
ImageIcon iconNew, iconOpen, iconSave;
ImageIcon iconCut, iconCopy, iconPaste;
JButton bttnNew, bttnOpen, bttnSave;
JButton bttnCut, bttnCopy, bttnPaste;

JTextArea txtPad;
Container c;
MyPad()
{
initComponents();
setTitle("MyPad");
setSize(400,300);
setVisible(true);

setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
addWindowListener(new WinHandler());
}

void initComponents()
{
//get a handle (reference) to the
//content pane of window
c = getContentPane();
//set BorderLayout Manager to arrange components in content pane
c.setLayout(new BorderLayout());

initMenu();
initToolbar();

//create textarea
txtPad = new JTextArea();
Font f = new Font("Comic Sans MS", Font.PLAIN, 20);
txtPad.setFont(f);
txtPad.addKeyListener(this);

//add txtPad in scrollpane
JScrollPane jscroll = new JScrollPane(txtPad);
//add scrollpane in window
c.add(jscroll, BorderLayout.CENTER);

}

void initMenu()
{
//create menubar
mbar = new JMenuBar();

//create menus
mnuFile = new JMenu("File");
mnuEdit = new JMenu("Edit");
mnuHelp= new JMenu("Help");

//create menuitems
fileNew = new JMenuItem("New");
fileOpen= new JMenuItem("Open");
fileSave= new JMenuItem("Save");
fileExit = new JMenuItem("Exit");

editCut = new JMenuItem("Cut");
editCopy= new JMenuItem("Copy");
editPaste = new JMenuItem("Paste");
editSelectAll = new JMenuItem("Select All");
editDel= new JMenuItem("Delete");

helpAbout= new JMenuItem("About");

//add menuitems in menus
mnuFile.add(fileNew);
mnuFile.add(fileOpen);
mnuFile.add(fileSave);
mnuFile.add(fileExit);

mnuEdit.add(editCut);
mnuEdit.add(editCopy);
mnuEdit.add(editPaste);
mnuEdit.addSeparator();
mnuEdit.add(editSelectAll);
mnuEdit.add(editDel);

mnuHelp.add(helpAbout);

//attach menus to menubar
mbar.add(mnuFile);
mbar.add(mnuEdit);
mbar.add(mnuHelp);

//attach menubar to window
setJMenuBar(mbar);


//attach actionlister to menuitems
fileNew.addActionListener(this);
fileOpen.addActionListener(this);
fileSave.addActionListener(this);
fileExit.addActionListener(this);

editCut.addActionListener(this);
editCopy.addActionListener(this);
editPaste.addActionListener(this);
editSelectAll.addActionListener(this);
editDel.addActionListener(this);

helpAbout.addActionListener(this);
}

void initToolbar()
{
iconNew = new ImageIcon("c:/java/images/new.gif");
iconOpen = new ImageIcon("c:/java/images/open.gif");
iconSave = new ImageIcon("c:/java/images/save.gif");

iconCut = new ImageIcon("c:/java/images/cut.gif");
iconCopy = new ImageIcon("c:/java/images/copy.gif");
iconPaste = new ImageIcon("c:/java/images/paste.gif");

bttnNew = new JButton(iconNew);
bttnOpen = new JButton(iconOpen);
bttnSave = new JButton(iconSave);
bttnCut = new JButton(iconCut);
bttnCopy = new JButton(iconCopy);
bttnPaste = new JButton(iconPaste);


//create a toolbar
tlbr = new JToolBar();

//add buttons into toolbar
tlbr.add(bttnNew);
tlbr.add(bttnOpen);
tlbr.add(bttnSave);
tlbr.addSeparator();
tlbr.add(bttnCut);
tlbr.add(bttnCopy);
tlbr.add(bttnPaste);

//add toolbar into window
c.add(tlbr, BorderLayout.NORTH);

//attach actionlistener to buttons
bttnNew.addActionListener(this);
bttnOpen.addActionListener(this);
bttnSave.addActionListener(this);
bttnCut.addActionListener(this);
bttnCopy.addActionListener(this);
bttnPaste.addActionListener(this);

}

public void actionPerformed(ActionEvent e)
{
Object src = e.getSource();
if(src.equals(bttnNew) || src.equals(fileNew))
{
newFile();
}
else if(src.equals(bttnOpen) || src.equals(fileOpen))
{
openFile();
}
else if(src.equals(bttnSave) || src.equals(fileSave))
{
saveFile();
}
else if(src.equals(fileExit))
{
exitFile();
}
else if(src.equals(bttnCut) || src.equals(editCut))
{
txtPad.cut();
}
else if(src.equals(bttnCopy) || src.equals(editCopy))
{
txtPad.copy();
}
else if(src.equals(bttnPaste) || src.equals(editPaste))
{
txtPad.paste();
}
else if(src.equals(editSelectAll))
{
txtPad.selectAll();
}
else if(src.equals(editDel))
{
//replace selected content with a blank
txtPad.replaceSelection("");
}
else if(src.equals(helpAbout))
{
aboutHelp();
}
}//end of actionPerformed



void newFile()
{
if(txtChanged)
{
int res;
res = JOptionPane.showConfirmDialog
(
this, //parent
"Do You Want To Save Changes",
"File New",
JOptionPane.YES_NO_CANCEL_OPTION
);

if(res == JOptionPane.YES_OPTION)
{
saveFile();
}
else if(res == JOptionPane.CANCEL_OPTION)
{
return;//terminate fn
//so that dispose() is not called
}

//no need to handle no as nothing is to be
//saved on no and only exit has tobe peformed
}
//code for file new
fname = "";
txtChanged = false;
txtPad.setText("");

}

void saveFile()
{
if(fname.equals(""))
{
JFileChooser jfc = new JFileChooser();
int res;
res = jfc.showSaveDialog(this);
if(res == jfc.APPROVE_OPTION)
{
fname = jfc.getSelectedFile().getAbsolutePath();
}
else
{//cancelled save
return;
}
}

try
{
FileWriter fw = new FileWriter(fname);
fw.write(txtPad.getText());
fw.flush();
fw.close();

//file is saved
txtChanged = false;
}
catch(Exception ex)
{
JOptionPane.showMessageDialog
(
this,
ex.getMessage(),
"Save Err",
JOptionPane.ERROR_MESSAGE
);
}

}

void openFile()
{
//JColorChooser jcc = new JColorChooser();
//Color c = jcc.showDialog(this,"dfdf",Color.RED);
//txtPad.setForeground(c);

int res;
JFileChooser jfc = new JFileChooser("c:/");
res = jfc.showOpenDialog(this);//parent window
if(res == jfc.APPROVE_OPTION)
{//open
fname = jfc.getSelectedFile().getAbsolutePath();

try
{
FileReader fr = new FileReader(fname);
BufferedReader br = new BufferedReader(fr);

//clear existing content of text area
txtPad.setText("");
String s;

while( (s = br.readLine()) != null )
{
txtPad.append(s);
txtPad.append("\n");
}
br.close();
fr.close();
}
catch(Exception ex)
{
JOptionPane.showMessageDialog
(
this, //parent window
ex.getMessage(), //msg
"Open Error", //title
JOptionPane.ERROR_MESSAGE //icon
);
}

}
}
void exitFile()
{
if(txtChanged)
{
int res;
res = JOptionPane.showConfirmDialog
(
this, //parent
"Do You Want To Save Changes",
"File Exit",
JOptionPane.YES_NO_CANCEL_OPTION
);

if(res == JOptionPane.YES_OPTION)
{
saveFile();
}
else if(res == JOptionPane.CANCEL_OPTION)
{
return;//terminate fn
//so that dispose() is not called
}

//no need to handle no as nothing is to be
//saved on no and only exit has tobe peformed
}
dispose();//exit
}

void aboutHelp()
{
new HelpDlg(this);
}

public void keyPressed(KeyEvent e)
{
//System.out.println("KP");
}

public void keyReleased(KeyEvent e)
{
//System.out.println("KR");
}

public void keyTyped(KeyEvent e)
{
txtChanged = true;
}


class WinHandler extends WindowAdapter
{
public void windowClosing(WindowEvent e)
{
exitFile();
}
}


public static void main(String args[])
{
MyPad mp = new MyPad();
}
}



class HelpDlg extends JDialog
{
public HelpDlg(MyPad m)
{
//MyPad object is the parent window of this dialog
super(m,true); //register it
//m is the MYPad ref that will be the owner
//true means current dialog will be a modal window

setSize(200, 150);
setVisible(true);
setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

}



