package BeatBox;

import javax.sound.midi.*;
import java.util.Scanner;

public class MiniMusicCmdLine {

    public static void main(String[] args) {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a initial data (\"-1\" is exit)");
            System.out.print("instrument--> ");
            int instrument = input.nextInt();
            mini.exit(instrument);
            System.out.print("note--------> ");
            int note = input.nextInt();
            mini.exit(note);
            mini.play(instrument, note);
            System.out.println();
        }
    }

    private void play(int instrument, int note) {

        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();

//            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,instrument,0);
            MidiEvent changeInstrument = new MidiEvent(first,1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,note,100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);
            player.setSequence(seq);
            player.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void exit(int status) {
        if (status == -1) {
            System.exit(0);
        }
    }
}
