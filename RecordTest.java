
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class RecordTest {

    public static void main(String[] args) {
        try {

            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            if (!AudioSystem.isLineSupported(dataInfo)) {
                System.out.println("Not Sup");
            }

            TargetDataLine targetLine;
            Mixer myMixer;
            ArrayList<Mixer> mixerArray = new ArrayList<Mixer>();
            Mixer miced = null;
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();

            for (int i = 0; i < mixerInfo.length; i++) {

                System.out.println("Name: " + mixerInfo[i].getName());
                myMixer = AudioSystem.getMixer(mixerInfo[i]);

                if (myMixer.isLineSupported(Port.Info.MICROPHONE)) {
                    System.out.println("Mic is supported!");
                    mixerArray.add(myMixer);
                }

            }
            // miced=mixerArray.get(0);



                targetLine = (TargetDataLine) AudioSystem.getLine(dataInfo);


            targetLine.open();

            JOptionPane.showMessageDialog(null, "hit ok to record");
            targetLine.start();
            Thread audioRecorderThread = new Thread() {
                @Override
                public void run() {
                    AudioInputStream recordingStream = new AudioInputStream(targetLine);
                    File outputFile = new File("record.wav");
                    try {
                        AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    System.out.println("finished");
                }
            };
            audioRecorderThread.start();
            JOptionPane.showMessageDialog(null, "hit ok to stop");
            targetLine.stop();
            targetLine.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
