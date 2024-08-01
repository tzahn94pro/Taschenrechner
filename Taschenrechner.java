import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Taschenrechner implements ActionListener{

    JFrame frame;
    JTextField textfeld;
    JButton[] zahlButton = new JButton[10];
    JButton[] funktionenButton = new JButton[9];
    JButton plusButton, minusButton, malButton, geteiltButton;
    JButton gleichButton, loeschButton, decButton, leerenButton, negButton;
    JPanel panel;

    //Schriften
    Font myFont = new Font("Rubik", Font.BOLD, 20);
    Font untereReihe = new Font("Rubik", Font.BOLD, 16);

    //Zahlen
    double n1 = 0;
    double n2 = 0;
    double result = 0;

    //aktuelle Funktion
    char operator;

    Taschenrechner(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setTitle("Tobis Taschenrechner");
        frame.setBackground(Color.DARK_GRAY);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        //Textfeld
        textfeld = new JTextField();
        textfeld.setBounds(50, 25, 300, 50);
        textfeld.setFont(myFont);
        textfeld.setEditable(false);

        //Button mit Funktionen
        plusButton = new JButton("+");
        minusButton = new JButton("-");
        malButton = new JButton("*");
        geteiltButton = new JButton("/");
        gleichButton = new JButton("=");
        loeschButton = new JButton("Löschen");
        decButton = new JButton(".");
        leerenButton = new JButton("Leeren");
        negButton = new JButton("-X");

        //Funktionenbutton
        funktionenButton[0] = plusButton;
        funktionenButton[1] = minusButton;
        funktionenButton[2] = malButton;
        funktionenButton[3] = geteiltButton;
        funktionenButton[4] = gleichButton;
        funktionenButton[5] = loeschButton;
        funktionenButton[6] = decButton;
        funktionenButton[7] = leerenButton;
        funktionenButton[8] = negButton;

        //Initialisieren der Zahlenbutton
        for(int i=0; i<9; i++){
            funktionenButton[i].addActionListener(this);
            funktionenButton[i].setFont(myFont);
            funktionenButton[i].setFocusable(false);
            funktionenButton[i].setBackground(Color.YELLOW);
            funktionenButton[i].setForeground(Color.GRAY);
        }

        //Beschriftung und Design der Zahlenbutton
        for(int i=0; i<10; i++){
            zahlButton[i] = new JButton(String.valueOf(i));
            zahlButton[i].addActionListener(this);
            zahlButton[i].setFont(myFont);
            zahlButton[i].setBackground(Color.GRAY);
            zahlButton[i].setForeground(Color.YELLOW);
            zahlButton[i].setFocusable(false);
        }

        //Anlegen und Beschriftung der unteren Button
        negButton.setBounds(282, 430, 68, 50);
        negButton.setFont(untereReihe);
        negButton.setBackground(Color.DARK_GRAY);
        loeschButton.setBounds(165, 430, 108, 50);
        loeschButton.setFont(untereReihe);
        loeschButton.setBackground(Color.DARK_GRAY);
        leerenButton.setBounds(50, 430, 108, 50);
        leerenButton.setFont(untereReihe);
        leerenButton.setBackground(Color.DARK_GRAY);

        //Design des Panels
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.DARK_GRAY);


        //Hinzufügen der Button in das Panel
        panel.add(zahlButton[7]);
        panel.add(zahlButton[8]);
        panel.add(zahlButton[9]);
        panel.add(plusButton);

        panel.add(zahlButton[4]);
        panel.add(zahlButton[5]);
        panel.add(zahlButton[6]);
        panel.add(minusButton);

        panel.add(zahlButton[1]);
        panel.add(zahlButton[2]);
        panel.add(zahlButton[3]);
        panel.add(malButton);

        panel.add(decButton);
        panel.add(zahlButton[0]);
        panel.add(gleichButton);
        panel.add(geteiltButton);

        frame.add(panel);
        frame.add(loeschButton);
        frame.add(leerenButton);
        frame.add(textfeld);
        frame.add(negButton);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    
    public static void main(String[] args) {
        
        new Taschenrechner();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
       
        //nächste Ziffer ins Textfeld 
        for(int i = 0; i<10; i++){
            if(e.getSource() == zahlButton[i]){
                textfeld.setText(textfeld.getText().concat(String.valueOf(i)));
            }
        }

        //Dezimalpunkt einfügen
        if(e.getSource()==decButton){
            textfeld.setText(textfeld.getText().concat("."));
        }

        //Funktionenauswahl
        if(e.getSource()==plusButton){
            n1 = Double.parseDouble(textfeld.getText());
            operator='+';
            textfeld.setText("");
        }

        if(e.getSource()==minusButton){
            n1 = Double.parseDouble(textfeld.getText());
            operator='-';
            textfeld.setText("");
        }

        if(e.getSource()==malButton){
            n1 = Double.parseDouble(textfeld.getText());
            operator='*';
            textfeld.setText("");
        }

        if(e.getSource()==geteiltButton){
            n1 = Double.parseDouble(textfeld.getText());
            operator='/';
            textfeld.setText("");
        }

        //Berechnung mit gewählter Funktion
        if(e.getSource()==gleichButton){
            n2 = Double.parseDouble(textfeld.getText());

            switch (operator){
                case '+':
                    result = n1 + n2;
                    break;
                case '-':
                    result = n1 - n2;
                    break;
                case '*':
                    result = n1 * n2;
                    break;
                case '/':
                    result = n1 / n2;
                    break;
            }
            textfeld.setText(String.valueOf(result));
            n1 = result;
        }

        //Leeren des Textfeldes
        if(e.getSource()==leerenButton){
            textfeld.setText("");
        }

        //Letzte Ziffer wird glöscht
        if(e.getSource()==loeschButton){
            String string = textfeld.getText();
            textfeld.setText("");

            for (int i=0; i<string.length()-1; i++){
                textfeld.setText(textfeld.getText() + string.charAt(i));
            }

        }

        //Zahl wird negiert
        if(e.getSource()==negButton){
            double temp = Double.parseDouble(textfeld.getText());
            temp*= -1;
            textfeld.setText(String.valueOf(temp));
        }


    }
}
