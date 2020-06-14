package BeatBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FigureColorChanger implements ActionListener {

    JFrame frame;

    public static void main(String[] args) {
        FigureColorChanger changer = new FigureColorChanger();
        changer.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change Color");
        button.addActionListener(this);

        Oval oval = new Oval();

        frame.getContentPane().add(BorderLayout.SOUTH,button);
        frame.getContentPane().add(BorderLayout.CENTER, oval);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        frame.repaint();
    }
}

class Oval extends JPanel {
    public void paintComponent(Graphics g) {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color ovalColor = new Color(red, green, blue);
        g.setColor(ovalColor);
        g.fillOval(100,50,100,100);
    }
}