import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MemorizationFrame extends JFrame {
    public JButton button;
    public JLabel title;
    public JTextField lineText;
    public LineInput input1;
    public LineInput input2;

    MemorizationFrame(){ 
        setTitle("App");
        setLayout(new GridBagLayout());
        setSize(700, 350);


        GridBagConstraints MainFramegbc = new GridBagConstraints();
        MainFramegbc.gridx=0;
        MainFramegbc.gridy=0;
        input1 =new LineInput();
        add(input1, MainFramegbc);
        MainFramegbc.gridy=1;
        input2 =new LineInput();
        add(input2, MainFramegbc);
        this.setBackground(Color.BLACK);
        setVisible(true);


        System.out.println("background:" + input1.getBackground());
    }
    @SuppressWarnings("unused")
    public static void main(String args[]){  
        MemorizationFrame mainFrame = new MemorizationFrame();  
    
    }
}
