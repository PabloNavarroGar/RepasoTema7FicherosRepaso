/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package contarLocalizaciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author samue
 */
public class MainContar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            //---
        ServiciosFicheros.leerFichero("localizaciones", "txt");
        //---
        List<String> listaLocations = sacarListaDelFichero("localizaciones", "txt");
        //---
        listaLocations.forEach(System.out::println);
        //---
        List<String> listaCardianl = sacarListaCardinal(listaLocations);
        //---
        listaCardianl.forEach(System.out::println);
        //--
        Map<String, Integer> mapCardinal = contadorCardinal(listaCardianl);
        //---
        System.out.println("---*****---");
        for (Map.Entry<String, Integer> entry : mapCardinal.entrySet()) {
            String key = entry.getKey();
            Integer val = entry.getValue();
            //---
            System.out.println(key + " - " + val);
        }
        //---
        ServiciosFicheros.generarDocumento(mapCardinal, "contadorLocalizaciones", "txt");
    }

    public static List<String> sacarListaDelFichero(String nomFichero, String formato) {
        // Fichero a leer con datos de ejemplo
        String idFichero = nomFichero + "." + formato;

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        List<String> lista = new ArrayList<>();

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
                //---
                lista.add(tokens[0].trim());

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public static List<String> sacarListaCardinal(List<String> l) {
        List<String> lista = new ArrayList<>();

        String regex = "[NSEO]";
        Pattern pat = Pattern.compile(regex);
        Matcher matcher;

        for (int i = 0; i < l.size(); i++) {
            String txt = l.get(i);
            matcher = pat.matcher(txt);
            while (matcher.find()) {

                lista.add(matcher.group());
            }
        }
        //---

        return lista;
    }

    public static Map<String, Integer> contadorCardinal(List<String> l) {
        Map<String, Integer> map = new HashMap<>();

        for (String s : l) {
            switch (s) {
                case "N" -> {
                    if (map.containsKey(s)) {
                        map.replace(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1);
                    }
                }
                case "S" -> {
                    if (map.containsKey(s)) {
                        map.replace(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1);
                    }
                }
                case "E" -> {
                    if (map.containsKey(s)) {
                        map.replace(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1);
                    }
                }
                case "O" -> {
                    if (map.containsKey(s)) {
                        map.replace(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1);
                    }
                }

            }
        }

        return map;
    }
    }


