/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package meterPalabraFichero;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author samue
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          String palabra;
        String nomFichero = "palabras.txt";

        //
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter("./" + nomFichero + ".txt"))) {
            do {
                palabra = JOptionPane.showInputDialog("Introduce una palabra 'Si pones Terminar se acabará el programa'");
                if (!palabra.equalsIgnoreCase("Terminar")) {
                    flujo.write(palabra);
                    flujo.newLine();
                } else {
                    flujo.flush();
                }
            } while (!palabra.equalsIgnoreCase("Terminar"));

            System.out.println("Fichero " + nomFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
