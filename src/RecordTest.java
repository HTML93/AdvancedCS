package src;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;

public class RecordTest extends JPanel{
    public JButton startButton;
    public JButton endButton;
    public AudioFormat audioFormat;
    public DataLine.Info dataInfo;
    public TargetDataLine targetLine;
    public JPanel recordContainer;
    public Boolean isRecording = false;
    RecordTest(String fileName) {
        recordContainer = new JPanel();
        recordContainer.setLayout(new GridLayout(1, 1));
        this.setBackground(Color.BLACK);
        //start button
        startButton= new JButton("Start Recording Line");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                recordGo(fileName);
                isRecording= true;        
                add(endButton);
                remove(startButton);
                revalidate();
                repaint();
            }});

        //end recording button
        endButton = new JButton("End Recording");
        endButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            targetLine.stop();
            targetLine.close();
            remove(endButton);
            startButton.setText("Rerecord Line");
            add(startButton);
            isRecording = false;
            revalidate();
            repaint();
            }
        }));

        add(startButton);
        this.setVisible(true);
    }

    public void recordGo(String filePath) {
        try {

            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 24000, 16, 1, 2, 48000, false);
            dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            if (!AudioSystem.isLineSupported(dataInfo)) {
                System.out.println("Not Sup");
            }

            Mixer myMixer;
            ArrayList<Mixer> mixerArray = new ArrayList<Mixer>();
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();

            for (int i = 0; i < mixerInfo.length; i++) {
                myMixer = AudioSystem.getMixer(mixerInfo[i]);

                if (myMixer.isLineSupported(Port.Info.MICROPHONE)) {
                    mixerArray.add(myMixer);
                }

            }
            targetLine = (TargetDataLine) AudioSystem.getLine(dataInfo);


            targetLine.open();


            targetLine.start();
            Thread audioRecorderThread = new Thread() {
                @Override
                public void run() {
                    AudioInputStream recordingStream = new AudioInputStream(targetLine);
                    File outputFile = new File(filePath);
                    try {
                        AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    System.out.println("finished");
                }
            };
            audioRecorderThread.start();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

    }
}