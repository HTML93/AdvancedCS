import java.awt.*;
import java.awt.event.*;


public class LineInput extends Panel implements ActionListener {
    public Button button;
    public Label title;
    public TextField lineText;
    public Container inputContatiner;
    LineInput(){

        //title
        title = new Label("Memorization Application");
        title.setAlignment(Label.CENTER);
        title.setBounds(100, 100, 200, 30);  
       
        //button
        button = new Button("Submit");
        button.setBounds(100,200,75,20);
        button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            MemorizationFrame.title.setText(lineText.getText());
    }
        });

        //input
        lineText = new TextField();
        lineText.setBounds(100,150,100,20);
        inputContatiner = new Container();
        inputContatiner.add(button);
        inputContatiner.add(title);
        inputContatiner.add(lineText);
        if(title!=null&&button!=null&&lineText!=null){
            System.out.println("1234");
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
      
}