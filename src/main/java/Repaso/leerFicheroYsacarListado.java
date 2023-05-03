/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Repaso;

/**
 *
 * @author samue
 */
public class leerFicheroYsacarListado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         //-- Mostramos nuestras líneas de bus desde el fichero
        leerFichero("autobuses", "txt", " ");
    }
    
     public static void leerFichero(String nomFichero, String formato, String separador) {
        //
        String idFichero = nomFichero + "." + formato;
        // Variables para guardar los datos que se van leyendo
        String linea;
        //

        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer

            while (datosFichero.hasNextLine()) {
                List<LocalTime> horas = new ArrayList<>(); //
                RutaBus rB = new RutaBus();
                //-En este punto se van sacando cada linea dle bus, con la clase hay que ahcerla
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();

                //--- Sacamos el id de la linea de bus
                String regex = "[0-9]+[A-Z]{1}";
                Pattern pat = Pattern.compile(regex);
                Matcher matcher = pat.matcher(linea);
                while (matcher.find()) {

                    rB.setNumLinea(matcher.group());
                }

                //--- Conseguimos el origen y el destino
                regex = "([A-Za-z\\s]+)-\\s([A-Za-z\\s]+)";
                pat = Pattern.compile(regex);
                matcher = pat.matcher(linea);
                if (matcher.find()) {
                    String origen = matcher.group(1).trim();
                    String destino = matcher.group(2).trim();
                    rB.setOrigen(origen);
                    rB.setDestino(destino);
                }

                //--- Conseguimos las horas
                regex = "[0-9]{2}:[0-9]{2}";
                pat = Pattern.compile(regex);
                matcher = pat.matcher(linea);
                while (matcher.find()) {
                    horas.add(LocalTime.parse(matcher.group()));
                }
                rB.setHorario(horas);

                //--- Sacamos la información
                System.out.println(rB);

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    
    
    
}
