package com.prokhorov.signallab.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

    public static void play(int noise) {
        File file = new File("src/main/resources/static/Voice.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            AudioFormat audioFormat = audioStream.getFormat();

            SourceDataLine sourceLine = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try {
                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[128000];
            while (nBytesRead != -1) {
                try {
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                    abData = change(abData, noise);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0) {
                    @SuppressWarnings("unused")
                    int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] change(byte[] validData, int noise) {
        for (int i = 0; i < validData.length ; i++) {
            validData[i] = (byte) (validData[i]*noise);
        }
        return validData;
    }

    public static void scramble() {
        File file = new File("src/main/resources/static/Voice.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            AudioFormat audioFormat = audioStream.getFormat();

            SourceDataLine sourceLine = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try {
                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[64000];
            while (nBytesRead != -1) {
                try {
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0) {
                    byte[] reverse = reverse(abData);
                    @SuppressWarnings("unused")
                    int nBytesWritten = sourceLine.write(reverse, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] reverse(byte[] data) {
        if (data == null)
            return null;

        int length = data.length;
        byte[] result = new byte[length];
        byte[] result1 = new byte[length/2];
        byte[] result2 = new byte[length-length/2];
        if (length == 0)
            return result;

        System.arraycopy(data, 0, result1, 0, length / 2);

        for (int j=0, i = length/2; j<length-length/2; i++, j++) {
            result2[j] = data[i];
        }

        System.arraycopy(result2, 0, result, 0, length / 2);

        for (int j=0, i = length/2; j<length-length/2; i++, j++) {
            result[i] = result1[j];
        }

        return result;
    }
}
