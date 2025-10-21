import java.awt.Color;

import javax.swing.*;

public class MemorizationFrame extends JFrame {
    public JButton button;
    public JLabel title;
    public JTextField lineText;
    public LineInput input1;
    public LineInput input2;

    MemorizationFrame(){ 
        setTitle("App");
        setLayout(null);
        setSize(500, 350);
        input1 =new LineInput();
        add(input1);
        setVisible(true);
        input1.setBounds(100,50, 300, 250);
        input1.setBackground(Color.RED);
        input2 =new LineInput();
        add(input2);
        input2.setBounds(150,50, 300, 250);
        input2.setBackground(Color.RED);

        System.out.println("background:" + input1.getBackground());
    }
    @SuppressWarnings("unused")
    public static void main(String args[]){  
        MemorizationFrame mainFrame = new MemorizationFrame();  
        
        
         
    }
}
