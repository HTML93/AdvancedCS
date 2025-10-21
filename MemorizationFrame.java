import java.awt.*;
import java.awt.event.*;
/* We have extended the Frame class here,
 * thus our class "SimpleExample" would behave
 * like a Frame
 */
public class MemorizationFrame extends Frame {
    Button button;
    static Label title;
    MemorizationFrame(){ 
        setTitle("urmom:)");
        setLayout(null);
        setSize(500, 350);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        @Override public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
    public static void main(String args[]){  
        MemorizationFrame mainFrame = new MemorizationFrame();  
        LineInput input1 = new LineInput();
        mainFrame.add(input1);
        input1.setVisible(true);
        input1.validate();
         
    }
}
