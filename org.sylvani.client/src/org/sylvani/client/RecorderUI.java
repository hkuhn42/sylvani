package org.sylvani.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RecorderUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private AudioClient client = null;
    private JButton captureButton = null;
    private JButton sendButton;
    private JButton replayButton;

    public RecorderUI() {
        super("Clip recorder client");
        client = new AudioClient();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container content = getContentPane();

        captureButton = new JButton("Record");
        sendButton = new JButton("Stop & Send");
        replayButton = new JButton("Replay");

        captureButton.setEnabled(true);
        sendButton.setEnabled(false);
        replayButton.setEnabled(false);

        ActionListener captureListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                captureButton.setEnabled(false);
                sendButton.setEnabled(true);
                replayButton.setEnabled(false);
                client.captureAudio();
            }
        };
        captureButton.addActionListener(captureListener);
        content.add(captureButton, BorderLayout.WEST);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                captureButton.setEnabled(true);
                sendButton.setEnabled(false);
                replayButton.setEnabled(true);
                client.finished();
            }
        });

        content.add(sendButton, BorderLayout.CENTER);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.playAudio();
            }
        });
        content.add(replayButton, BorderLayout.EAST);
    }

    public static void main(String args[]) {
        JFrame frame = new RecorderUI();
        frame.pack();
        frame.setVisible(true);
    }
}