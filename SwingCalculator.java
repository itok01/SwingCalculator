import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * SwingCalculator
 */
public class SwingCalculator extends JFrame {
    private static final char PLUS_SYMBOL = '+';
    private static final char MINUS_SYMBOL = '-';
    private static final char MULTIPLY_SYMBOL = '×';
    private static final char DIVIDE_SYMBOL = '÷';
    private static final String SYMBOL_REGEX = "\\+|-|×|÷";
    private static final String NUMBER_REGEX = "\\d+(?:\\.\\d+)?";

    /**
     * 式
     */
    private static JTextField textField;

    private JFrame f;

    /**
     * 計算する。
     */
    private static double calc() {
        List<Double> numberList = fetchNumber();
        List<Character> symbolList = fetchSymbol();

        if (numberList.size() == 1) {
            return numberList.get(0);
        } else if (numberList.size() == 0) {
            return 0.0;
        }

        while (symbolList.size() > 0) {
            for (int i = 0; i < symbolList.size(); i++) {
                if (symbolList.contains(MULTIPLY_SYMBOL) || symbolList.contains(DIVIDE_SYMBOL)) {
                    if (isMultiplySymbol(symbolList.get(i))) {
                        double result = numberList.get(i) * numberList.get(i + 1);
                        numberList.set(i, result);
                        numberList.remove(i + 1);
                        symbolList.remove(i);
                        break;
                    } else if (isDivideSymbol(symbolList.get(i))) {
                        double result = numberList.get(i) / numberList.get(i + 1);
                        numberList.set(i, result);
                        numberList.remove(i + 1);
                        symbolList.remove(i);
                        break;
                    }
                } else {
                    if (isPlusSymbol(symbolList.get(i))) {
                        double result = numberList.get(i) + numberList.get(i + 1);
                        numberList.set(i, result);
                        numberList.remove(i + 1);
                        symbolList.remove(i);
                        break;
                    } else if (isMinusSymbol(symbolList.get(i))) {
                        double result = numberList.get(i) - numberList.get(i + 1);
                        numberList.set(i, result);
                        numberList.remove(i + 1);
                        symbolList.remove(i);
                        break;
                    }
                }
            }
        }

        return numberList.get(0);
    }

    /**
     * 数を取り出す。
     */
    private static List<Double> fetchNumber() {
        String[] splitedNumberString = textField.getText().split(SYMBOL_REGEX);
        List<Double> splitedNumber = new ArrayList<>();

        for (int i = 0; i < splitedNumberString.length; i++) {
            splitedNumber.add(Double.parseDouble(splitedNumberString[i]));
        }

        return splitedNumber;
    }

    /**
     * 記号を取り出す。
     */
    private static List<Character> fetchSymbol() {
        String[] splitedNumberString = textField.getText().split(NUMBER_REGEX);
        List<Character> splitedSymbol = new ArrayList<>();

        for (int i = 1; i < splitedNumberString.length; i++) {
            splitedSymbol.add(splitedNumberString[i].charAt(0));
        }

        return splitedSymbol;
    }

    /**
     * 記号かどうか確かめる。
     */
    private static boolean isSymbol(char c) {
        if (isPlusSymbol(c) || isMinusSymbol(c) || isMultiplySymbol(c) || isDivideSymbol(c)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 足し算の記号かどうか確かめる。
     */
    private static boolean isPlusSymbol(char c) {
        if (c == PLUS_SYMBOL) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 引き算の記号かどうか確かめる。
     */
    private static boolean isMinusSymbol(char c) {
        if (c == MINUS_SYMBOL) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 掛け算の記号かどうか確かめる。
     */
    private static boolean isMultiplySymbol(char c) {
        if (c == MULTIPLY_SYMBOL) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 割り算の記号かどうか確かめる。
     */
    private static boolean isDivideSymbol(char c) {
        if (c == DIVIDE_SYMBOL) {
            return true;
        } else {
            return false;
        }
    }

    SwingCalculator() {
        setTitle("Swing Calculator");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 4));

        JButton button0 = new JButton("0");
        button0.addActionListener(new ButtonNumberPush(0));
        JButton button1 = new JButton("1");
        button1.addActionListener(new ButtonNumberPush(1));
        JButton button2 = new JButton("2");
        button2.addActionListener(new ButtonNumberPush(2));
        JButton button3 = new JButton("3");
        button3.addActionListener(new ButtonNumberPush(3));
        JButton button4 = new JButton("4");
        button4.addActionListener(new ButtonNumberPush(4));
        JButton button5 = new JButton("5");
        button5.addActionListener(new ButtonNumberPush(5));
        JButton button6 = new JButton("6");
        button6.addActionListener(new ButtonNumberPush(6));
        JButton button7 = new JButton("7");
        button7.addActionListener(new ButtonNumberPush(7));
        JButton button8 = new JButton("8");
        button8.addActionListener(new ButtonNumberPush(8));
        JButton button9 = new JButton("9");
        button9.addActionListener(new ButtonNumberPush(9));
        JButton buttonPlus = new JButton(String.valueOf(PLUS_SYMBOL));
        buttonPlus.addActionListener(new ButtonSymbolPush(PLUS_SYMBOL));
        JButton buttonMinus = new JButton(String.valueOf(MINUS_SYMBOL));
        buttonMinus.addActionListener(new ButtonSymbolPush(MINUS_SYMBOL));
        JButton buttonMultiply = new JButton(String.valueOf(MULTIPLY_SYMBOL));
        buttonMultiply.addActionListener(new ButtonSymbolPush(MULTIPLY_SYMBOL));
        JButton buttonDivide = new JButton(String.valueOf(DIVIDE_SYMBOL));
        buttonDivide.addActionListener(new ButtonSymbolPush(DIVIDE_SYMBOL));
        JButton buttonEqual = new JButton("=");
        buttonEqual.addActionListener(new ButtonEqualPush());
        JButton buttonClear = new JButton("C");
        buttonClear.addActionListener(new ButtonClearPush());

        p.add(button7);
        p.add(button8);
        p.add(button9);
        p.add(buttonDivide);
        p.add(button4);
        p.add(button5);
        p.add(button6);
        p.add(buttonMultiply);
        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(buttonMinus);
        p.add(button0);
        p.add(buttonClear);
        p.add(buttonEqual);
        p.add(buttonPlus);

        getContentPane().add(p, BorderLayout.CENTER);

        textField = new JTextField();

        getContentPane().add(textField, BorderLayout.NORTH);

        setVisible(true);
        f = this;
    }

    class ButtonNumberPush implements ActionListener {
        private int number;

        ButtonNumberPush(int n) {
            number = n;
        }

        public void actionPerformed(ActionEvent e) {
            textField.setText(textField.getText() + number);
        }
    }

    class ButtonSymbolPush implements ActionListener {
        private char symbol;

        ButtonSymbolPush(char c) {
            symbol = c;
        }

        public void actionPerformed(ActionEvent e) {
            if (!textField.getText().isEmpty()) {
                if (!isSymbol(textField.getText().charAt(textField.getText().length() - 1))) {
                    textField.setText(textField.getText() + symbol);
                } else {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1) + symbol);
                }
            }
        }
    }

    class ButtonEqualPush implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!textField.getText().isEmpty()) {
                if (isSymbol(textField.getText().charAt(textField.getText().length() - 1))) {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                }
            } else {
                textField.setText("0");
            }
            double r = calc();
            textField.setText(String.valueOf(r));
        }
    }

    class ButtonClearPush implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new SwingCalculator();
    }
}
