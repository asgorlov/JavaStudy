package BeatBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui implements ActionListener {

    JButton button;
    boolean isPressed = false;

    public static void main(String[] args) {
        SimpleGui gui = new SimpleGui();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        button = new JButton("click me");

        button.addActionListener(this);

        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (isPressed) {
            button.setText("click me");
            isPressed = false;
        } else {
            button.setText("I've been clicked");
            isPressed = true;
        }
    }
}