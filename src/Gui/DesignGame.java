package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DesignGame extends JFrame implements ActionListener{
    private JButton playButtom;
    private JButton HTPlayButtom;
    private JButton ExitButtom;
    private JPanel mainPanel;
    private JPanel Menu;
    private JPanel playGame;
    private JButton backButtom;
    private JPanel howToPlay;
    private JPanel boardPanel;
    private JButton restart;
    private JLabel Player;
    private JButton returnMenu;
    private Square[][] board;
    int turn=0;
    boolean empty=true;
    boolean band=false;
    public DesignGame(String appTitle){
        super(appTitle);
        this.setMinimumSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setContentPane(mainPanel);
        this.pack();
        board = new Square[3][3];
        boardPanel.setLayout(new GridLayout(3,3));
        playGame.setVisible(false);
        Menu.setVisible(true);
        howToPlay.setVisible(false);
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                board[i][j]=new Square();
                board[i][j].boardButtom.setBackground(new Color(75,37,116));
                board[i][j].boardButtom.setForeground(new Color(255, 255, 255));
                board[i][j].boardButtom.addActionListener(this);
                board[i][j].boardButtom.setBounds((i*100)+10,(j*100)+10,100,100);
                boardPanel.add(board[i][j].boardButtom);
            }
        }
        playButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(false);
                playGame.setVisible(true);
            }
        });

        HTPlayButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(false);
                howToPlay.setVisible(true);
            }
        });
        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        returnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(true);
                playGame.setVisible(false);
            }
        });
        backButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(true);
                howToPlay.setVisible(false);
            }
        });
        ExitButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (e.getSource()==board[i][j].boardButtom && !band){
                    setTurn(board[i][j]);
                    if (isWinner()){
                        JOptionPane.showMessageDialog(null,"Has Ganado X");
                        band=true;
                        break;
                    }
                    else if(!empty && isWinner()==false){
                        JOptionPane.showMessageDialog(null,"Empate");
                        band=true;
                        break;
                    }
                }
                else if(turn%2!=0 && e.getSource()!=board[i][j].boardButtom && !band){
                    int row;
                    int col;
                    int srx;
                    do {
                            srx=generateRobotMovement();
                            row = (srx - 1) / 3;
                            col = (srx - 1) % 3;
                    }while (board[row][col].boardButtom.getText()=="X" || board[row][col].boardButtom.getText()=="O");
                    setTurn(board[row][col]);
                    if (isWinner()){
                        JOptionPane.showMessageDialog(null,"Has Ganado O");
                        band=true;
                        break;
                    }
                    else if(!empty && isWinner()==false){
                        JOptionPane.showMessageDialog(null,"Empate");
                        band=true;
                        break;
                    }
                }
            }
        }
    }
    public void setTurn(Square currentSquare){
        if(turn%2==0){
            currentSquare.boardButtom.setText("X");
            currentSquare.value =1;
            Player.setText("Jugador O");
        }
        else {
            currentSquare.boardButtom.setText("O");
            currentSquare.value =5;
            Player.setText("Jugador X");
        }
        currentSquare.boardButtom.removeActionListener(this);
        turn++;
    }
    private static int generateRobotMovement() {

        Random randGenerator = new Random();
        return randGenerator.nextInt(9) + 1;
    }
    public  boolean isWinner() {
        boolean winner = false;
        int count=0;
        int suma = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                suma+=board[i][j].value;
                if (suma==3 || suma==15){
                    winner=true;
                }
            }
            suma=0;
        }
        suma=0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                suma+=board[i][j].value;
                if (suma==3 || suma==15){
                    winner=true;
                }
            }
            suma=0;
        }
        suma=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i==j){
                    suma+=board[i][j].value;
                    if (suma==3 || suma==15){
                        winner=true;
                    }
                }
            }
        }
        suma=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i+j==2){
                    suma+=board[i][j].value;
                    if (suma==3 || suma==15){
                        winner=true;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].boardButtom.getText()!="X" && board[i][j].boardButtom.getText()!="O"){
                    count++;
                }
            }
        }
        if (count==0){
            empty=false;
        }
        return winner;
    }
    public void resetGame(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                board[i][j].boardButtom.removeActionListener(this);
            }
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                board[i][j].boardButtom.setText("");
                board[i][j].value=0;
                board[i][j].boardButtom.addActionListener(this);
            }
        }
        turn=0;
        empty=true;
        band=false;
    }
}
