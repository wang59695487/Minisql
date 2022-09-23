package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import Interpreter.*;


public class GUI {
    public static int FirstWrite0=1,FirstWrite1=1;
    public static JTextArea readArea,writeArea;
    public static void Initialize(){
        JFrame GUI_Frame=new JFrame("miniSQL");
        GUI_Frame.setSize(700,800);
        GUI_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUImake(GUI_Frame);
        GUI_Frame.setLocationRelativeTo(null);
        GUI_Frame.setVisible(true);
    }
    public static void GUImake(final JFrame frame) {
        JScrollPane scrollPane1 = new JScrollPane();
        readArea = new JTextArea();
        scrollPane1.setViewportView(readArea);

        JScrollPane scrollPane2 = new JScrollPane();
        writeArea = new JTextArea();
        scrollPane2.setViewportView(writeArea);

        JButton button = new JButton("选择文件");
        JButton Run_button = new JButton("运行");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                String defaultDirectory = "\\\\Mac\\Home\\Desktop\\MiniSQL-Final1\\tests";
                fc.setCurrentDirectory(new File(defaultDirectory));
                fc.showOpenDialog(frame);
                try{
                    File file = fc.getSelectedFile();
                    FilePrintText(file,0);
                } catch(Exception e1){
                    JPanel panel1 = new JPanel();
                    JOptionPane.showMessageDialog(panel1,"没有选择任何文件","提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Run_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstWrite1=1;
                writeArea.setText("");
                try{
                    BufferedReader reader=GetText(0);
                    //String line;
                    //String content = new String();
                    //while((line=reader.readLine())!=null) content += (line+'\n');
                    //System.out.print(content);
                    //StringPrintText(readArea.getText());
                    Interpreter.Parsing(reader);
                    reader.close();
                    //System.out.print("\n\n");
                } catch (Exception e2) {
                    GUI.StringPrintText("Interpreter error:" + e2.getMessage());
                    e2.printStackTrace();
                }
                //readArea.setText("");
            }
        });

        Box hBox = Box.createHorizontalBox();
        Box vBox = Box.createVerticalBox();
        vBox.add(new JLabel("Welcome to my SQL, please write your command below"));
        vBox.add(scrollPane1);
        vBox.add(new JLabel(" 在上方文本框中输入代码或者选择文件，点击 运行 即得到结果。"));
        hBox.add(Box.createHorizontalGlue());
        hBox.add(button);
        hBox.add(Box.createHorizontalGlue());
        hBox.add(Run_button);
        hBox.add(Box.createHorizontalGlue());
        vBox.add(hBox);
        vBox.add(scrollPane2);
        frame.setContentPane(vBox);
    }
    public static void FilePrintText(final File file,int type) throws Exception{
        if(type==0) {
            readArea.setText("");
            FirstWrite0=1;
        }
        else {
            writeArea.setText("");
            FirstWrite1=1;
        }
        String path = file.getPath();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                if(type==0) {
                    if(FirstWrite0==1) {
                        readArea.append(line);
                        //readArea.paintImmediately(readArea.getBounds());
                        FirstWrite0 = 0;
                    }
                    else {
                        readArea.append("\n"+line);
                        //readArea.paintImmediately(readArea.getBounds());
                    }
                }
                else{
                    if(FirstWrite1==1) {
                        writeArea.append(line);
                        //writeArea.paintImmediately(writeArea.getBounds());
                        FirstWrite1=0;
                    }
                    else {
                        writeArea.append("\n" + line);
                        //writeArea.paintImmediately(writeArea.getBounds());
                    }
                }
            }
        } catch (IOException e) {
            writeArea.append("\nFile error:"+e.getMessage());
            //writeArea.paintImmediately(writeArea.getBounds());
            e.printStackTrace();
        }
    }
    public static void StringPrintText(String s) {
        if(FirstWrite1==1) {
            writeArea.append(s);
            //writeArea.paintImmediately(writeArea.getBounds());
            FirstWrite1=0;
        }
        else {
            writeArea.append("\n" + s);
            //writeArea.paintImmediately(writeArea.getBounds());
        }
    }
    public static void StringPrintAttribute(String s) {
        writeArea.append(s);
        //writeArea.paintImmediately(writeArea.getBounds());
    }
    public static BufferedReader GetText(int type) throws Exception{

        String content;
        if(type==0) content = readArea.getText();
        else content = writeArea.getText();

        InputStream is = new ByteArrayInputStream(content.getBytes("GBK"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        return reader;
    }
}