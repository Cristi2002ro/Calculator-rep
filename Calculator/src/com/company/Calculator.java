package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculator implements ActionListener {
    JFrame frame = new JFrame("Calculator");
    JTextField field = new JTextField();
    Font font = new Font("Ink free", Font.BOLD, 35);
    JPanel panel = new JPanel();
    JButton[] numbers = new JButton[10];
    JButton[] operators = new JButton[7];
    JButton plus, minus, mul, div, equals, clear, backspace, point;
    double nr1 = 0, nr2 = 0, result = 0;
    char operator;

    Calculator() {
        //frame
        frame.setSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("C:\\Users\\Alexandru Duna\\IdeaProjects\\Calculator\\res\\1-2.png").getImage());

        //textfield
        field.setPreferredSize(new Dimension(350, 50));
        field.setEditable(false);
        field.setBackground(Color.lightGray);
        field.setFont(font);

        //numbers
        numbers[0] = new JButton("1");
        numbers[1] = new JButton("2");
        numbers[2] = new JButton("3");
        numbers[3] = new JButton("4");
        numbers[4] = new JButton("5");
        numbers[5] = new JButton("6");
        numbers[6] = new JButton("7");
        numbers[7] = new JButton("8");
        numbers[8] = new JButton("9");
        numbers[9] = new JButton("0");

        //operators
        plus = new JButton("+");
        minus = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        equals = new JButton("=");
        clear = new JButton("Clr");
        backspace = new JButton("Del");

        operators[0] = plus;
        operators[1] = minus;
        operators[2] = mul;
        operators[3] = backspace;
        operators[4] = clear;
        operators[5] = div;
        operators[6] = equals;

        point = new JButton(".");

        //panel

        //rows 1-3(numbers 1-9):
        panel.setPreferredSize(new Dimension(350, 370));
        for (int b = 0; b < numbers.length - 1; b++) {
            numbers[b].setPreferredSize(new Dimension(85, 50));
            numbers[b].setFocusable(false);
            numbers[b].addActionListener(this);
            numbers[b].setFont(font);
            panel.add(numbers[b]);
        }

        //row 4:
        minus.setPreferredSize(new Dimension(85, 50));
        minus.setFocusable(false);
        minus.addActionListener(this);
        minus.setFont(font);
        panel.add(minus);

        numbers[9].setPreferredSize(new Dimension(85, 50));
        numbers[9].setFocusable(false);
        numbers[9].addActionListener(this);
        numbers[9].setFont(font);
        panel.add(numbers[9]);

        plus.setPreferredSize(new Dimension(85, 50));
        plus.setFocusable(false);
        plus.addActionListener(this);
        plus.setFont(font);
        panel.add(plus);

        //row 5:
        div.setPreferredSize(new Dimension(85, 50));
        div.setFocusable(false);
        div.addActionListener(this);
        div.setFont(font);
        panel.add(div);

        point.setPreferredSize(new Dimension(85, 50));
        point.setFocusable(false);
        point.addActionListener(this);
        point.setFont(font);
        panel.add(point);

        mul.setPreferredSize(new Dimension(85, 50));
        mul.setFocusable(false);
        mul.addActionListener(this);
        mul.setFont(font);
        panel.add(mul);

        //row 6:
        clear.setPreferredSize(new Dimension(85, 50));
        clear.setFocusable(false);
        clear.addActionListener(this);
        clear.setFont(font);
        panel.add(clear);

        equals.setPreferredSize(new Dimension(85, 50));
        equals.setFocusable(false);
        equals.addActionListener(this);
        equals.setFont(font);
        panel.add(equals);

        backspace.setPreferredSize(new Dimension(85, 50));
        backspace.setFocusable(false);
        backspace.addActionListener(this);
        backspace.setFont(font);
        panel.add(backspace);

        //adds
        frame.add(field);
        frame.add(panel);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                for (JButton b:
                     numbers) {
                    if(e.getKeyChar()==b.getText().charAt(0)) field.setText(field.getText().concat(b.getText()));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //numbers
            for (JButton b :
                    numbers) {
                if (e.getSource() == b) field.setText(field.getText().concat(b.getText()));
            }

            //point
            if (e.getSource() == point) field.setText(field.getText().concat(point.getText()));

            //arithmetical operators
            if (e.getSource() == plus) {
                nr1 = Double.parseDouble(field.getText());
                operator = '+';
                field.setText("");
            }
            if (e.getSource() == minus) {
                if (field.getText().isEmpty() || field.getText().isBlank()) {
                    field.setText("-");
                }
                nr1 = Double.parseDouble(field.getText());
                operator = '-';
                field.setText("");
            }
            if (e.getSource() == mul) {
                nr1 = Double.parseDouble(field.getText());
                operator = '*';
                field.setText("");
            }
            if (e.getSource() == div) {
                nr1 = Double.parseDouble(field.getText());
                operator = '/';
                field.setText("");
            }

            //equals
            if (e.getSource() == equals) {
                nr2 = Double.parseDouble(field.getText());

                switch (operator) {
                    case '+':
                        result = nr1 + nr2;
                        break;
                    case '-':
                        result = nr1 - nr2;
                        break;
                    case '*':
                        result = nr1 * nr2;
                        break;
                    case '/':
                        result = nr1 / nr2;
                        break;
                }
                field.setText(String.valueOf(result));
                nr1 = result;
            }

            //del, clear
            if (e.getSource() == backspace) {
                field.setText(field.getText().substring(0, field.getText().length() - 1));
            }

            if (e.getSource() == clear) {
                field.setText("");
            }

        } catch (Exception f) {

        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}
