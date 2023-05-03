/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contarLocalizaciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author samue
 */
public class ServiciosFicheros {
    
       public static void leerFichero(String nomFichero, String formato) {

        // Fichero a leer con datos de ejemplo
        String idFichero = nomFichero + "." + formato;

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

        System.out.println("Leyendo el fichero: " + idFichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split(";");

                System.out.println(tokens[0]);

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void generarDocumento(Map<String, Integer> map, String nomFichero, String formato) {
        String idFichero = nomFichero + "." + formato;
        String tmp;
        //---
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                tmp = key + " - " + val;
                flujo.write(tmp);
                flujo.newLine();
            }
            flujo.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }   
}
