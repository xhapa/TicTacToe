/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import Gui.DesignGame;

import javax.swing.*;

public class GameEngine {

    public static void main(String[] args) {
        startGameGUI();
    }

    private static void startGameGUI() {

        JFrame Game= new DesignGame("Tic Tac Toe");
        Game.setVisible(true);
    }
}

