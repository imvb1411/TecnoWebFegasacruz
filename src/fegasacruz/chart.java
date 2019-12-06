/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

import java.util.ArrayList;

/**
 *
 * @author Mijael
 */
public class chart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PieChart demo = new PieChart("Comparison", "Which operating system are you using?",new ArrayList<>());
        demo.pack();
        demo.setVisible(true);   
    }

}
