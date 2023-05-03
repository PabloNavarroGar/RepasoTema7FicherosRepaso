/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package Repaso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class RepasoTema7FicherosParaExamen {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //- Se pide hacer ,generar un fichero de texto plano a traves de JOptionPane,
        //- Vaya guardado las palabras que le vayamos escribiendo linea por linea, 
        //- En el momento que le introducimos "terminar", el programa no guarda esa palabra, sino que genera el archivo de texto

        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String idFichero = "fichero.txt";
        // Crear el cuadro de diálogo para seleccionar la ubicación donde se guardará el archivo
        //- Por si me intersa directamente crearlo para guardarlo en un sitio
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                // Crear un BufferedWriter para escribir en el archivo
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                String palabra = "";
                //Bucle para que mientras no sea la palabra terminar, se volvera a mostar el JOption
                while (!palabra.equalsIgnoreCase("terminar")) {
                    // Mostrar un cuadro de diálogo para que el usuario ingrese una palabra
                    palabra = JOptionPane.showInputDialog(null, "Ingrese una palabra (Escriba 'terminar' para finalizar):");
                    if (!palabra.equalsIgnoreCase("terminar")) {
                        // Escribir la palabra en el archivo y agregar un salto de línea
                        bw.write(palabra);
                        bw.newLine();
                    }
                }
                // Cerrar el BufferedWriter y mostrar un mensaje de confirmación
                bw.close();
                JOptionPane.showMessageDialog(null, "Archivo guardado con éxito.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo.");
            }

        }
    }
}
