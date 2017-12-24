/*
 * Created by JFormDesigner on Thu Dec 21 10:54:38 CST 2017
 */

package han;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author han
 */
public class JFswift extends JFrame {
    private File inputTxtFile;
    private File outSwiftFile;
    private boolean fileExited;
    private JFswift() {
        initComponents();

    }
    public  static void  main(String args[]) {

        JFswift jf = new JFswift();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setVisible(true);
    }

    private void menuItemInputMouseReleased(MouseEvent e) {
        JFileChooser chooser= new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
      //  chooser.setSelectedFile(new File("CCB.txt"));
        FileFilter filter;
        filter = new FileNameExtensionFilter("EXCEL","xls","xlsx");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(filter);
        int result= chooser.showOpenDialog (JFswift.this);
        if (result==JFileChooser.APPROVE_OPTION){
            this.inputTxtFile=chooser.getSelectedFile();
            chooser.setVisible(false);
            if (inputTxtFile.exists() ){
                textArea1.append("导入EXCEL文件：->\n");
                textArea1.append(inputTxtFile.getAbsolutePath());
                textArea1.append("\n");
            }else{
                textArea1.append(inputTxtFile.getAbsolutePath());
                textArea1.append("不存在\n");
            }


        }
    }


    private void menuItemOutputMouseReleased(MouseEvent e) {
        JFileChooser chooser= new JFileChooser();
        chooser.setCurrentDirectory(inputTxtFile);
        chooser.setSelectedFile(new File("CCB.MT940"));
        FileFilter filter;
        filter = new FileNameExtensionFilter("MT940客户对账单","*.MT940");

        chooser.addChoosableFileFilter(filter);
        int result= chooser.showSaveDialog (JFswift.this);
        if (result==JFileChooser.APPROVE_OPTION){
            this.outSwiftFile=chooser.getSelectedFile();
            chooser.setVisible(false);
            if (outSwiftFile.exists()){

                 JOptionPane jp= new JOptionPane();
                  result= jp.showConfirmDialog(chooser,"文件存在是否覆盖？");
                 if (result==JOptionPane.YES_OPTION){
                     fileExited=true;
                 }else if (result==JOptionPane.CANCEL_OPTION){
                     fileExited=false;
                 }
                     ;

            }
            textArea1.append("导出Swift文件：->\n");
            textArea1.append(outSwiftFile.getAbsolutePath());
            textArea1.append("\n");
            if(fileExited) textArea1.append("文件转换后被覆盖！\n");
        }
    }

    private void menuItemTransMouseReleased(MouseEvent e) {
        try {
            FileInputStream fin= new FileInputStream(inputTxtFile);
            Scanner scanner= new Scanner(fin);
            if (scanner.hasNextLine()) {
                String strLine= scanner.nextLine();
                System.out.print(strLine);
            }

        } catch (FileNotFoundException e1) {
            textArea1.append("读取导入EXCEL文件：->\n");
            textArea1.append(inputTxtFile.getAbsolutePath());
            textArea1.append("出错！\n");
            e1.printStackTrace();
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menuFile = new JMenu();
        menuItemInput = new JMenuItem();
        menuItemOutput = new JMenuItem();
        menuTrans = new JMenu();
        menuItemTrans = new JMenuItem();
        menuAbout = new JMenu();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menuFile ========
            {
                menuFile.setText("\u6587\u4ef6  ");
                menuFile.setActionCommand("text");

                //---- menuItemInput ----
                menuItemInput.setText("EXCEL\u6587\u4ef6");
                menuItemInput.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItemInputMouseReleased(e);
                    }
                });
                menuFile.add(menuItemInput);

                //---- menuItemOutput ----
                menuItemOutput.setText("MT940\u6587\u4ef6");
                menuItemOutput.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItemOutputMouseReleased(e);
                    }
                });
                menuFile.add(menuItemOutput);
            }
            menuBar1.add(menuFile);

            //======== menuTrans ========
            {
                menuTrans.setText("\u8f6c\u6362   ");

                //---- menuItemTrans ----
                menuItemTrans.setText("\u7acb\u5373\u8f6c\u6362\u6587\u4ef6");
                menuItemTrans.setActionCommand("text");
                menuItemTrans.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItemTransMouseReleased(e);
                    }
                });
                menuTrans.add(menuItemTrans);
            }
            menuBar1.add(menuTrans);

            //======== menuAbout ========
            {
                menuAbout.setText("\u5173\u4e8e   ");
            }
            menuBar1.add(menuAbout);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menuFile;
    private JMenuItem menuItemInput;
    private JMenuItem menuItemOutput;
    private JMenu menuTrans;
    private JMenuItem menuItemTrans;
    private JMenu menuAbout;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
