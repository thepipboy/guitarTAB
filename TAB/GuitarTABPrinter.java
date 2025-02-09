package com.example.guitartab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuitarTABPrinter extends JFrame {

    private static final String[] STRINGS = {"e", "B", "G", "D", "A", "E"};
    private JTextField[] stringInputs;
    private JTextArea tabDisplay;

    public GuitarTABPrinter() {
        setTitle("Guitar TAB Printer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();
        createTabDisplay();

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(tabDisplay), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        stringInputs = new JTextField[6];

        for (int i = 0; i < STRINGS.length; i++) {
            panel.add(new JLabel(STRINGS[i] + " string:"));
            stringInputs[i] = new JTextField(20);
            panel.add(stringInputs[i]);
        }

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton printButton = new JButton("Print TAB");
        JButton clearButton = new JButton("Clear");

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTAB();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputs();
            }
        });

        panel.add(printButton);
        panel.add(clearButton);
        return panel;
    }

    private void createTabDisplay() {
        tabDisplay = new JTextArea();
        tabDisplay.setEditable(false);
        tabDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void printTAB() {
        StringBuilder tab = new StringBuilder();
        int maxLength = 0;

        // Find the maximum length of input
        for (JTextField input : stringInputs) {
            maxLength = Math.max(maxLength, input.getText().length());
        }

        // Create the TAB
        for (int i = 0; i < STRINGS.length; i++) {
            String input = stringInputs[i].getText();
            tab.append(STRINGS[i]).append("|");

            for (int j = 0; j < maxLength; j++) {
                if (j < input.length()) {
                    char c = input.charAt(j);
                    if (Character.isDigit(c)) {
                        tab.append(c);
                    } else {
                        tab.append('-');
                    }
                } else {
                    tab.append('-');
                }
            }

            tab.append("|\n");
        }

        tabDisplay.setText(tab.toString());
    }

    private void clearInputs() {
        for (JTextField input : stringInputs) {
            input.setText("");
        }
        tabDisplay.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuitarTABPrinter().setVisible(true);
            }
        });
    }
}