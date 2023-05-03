/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sacarCSVdeclasesVehiculos;

/**
 *
 * @author samue
 */
public class MainVehiculos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           /*
    En un proyecto Maven nuevo llamado ej7D+tunombre, implementa un programa para leer los ficheros de texto del ejercicio 7B (turismos.txt, deportivos.txt, furgonetas.txt), 
    de forma que se guarden en una única lista de Vehículos los objetos leídos de cada fichero. 
    El programa, además, debe realizar lo siguiente:
    >>Copiar los tres ficheros *.csv a un directorio previamente creado en “./copias”.
    >>Mostrar los ficheros contenidos en “copias”.
    >>Leer los ficheros de la carpeta “copias” e ir guardando los objetos en una lista de vehículos.
    >>Imprimir la lista por pantalla. 
    >>Ordena la lista por bastidor.
    >>Imprimir la lista ordenada.
    >>Borrar los ficheros *.csv originales.
    >>Mostrar el contenido de la carpeta donde estaban los *.csv originales.
    <<Usando Streams, realiza las siguientes acciones sobre la lista de vehículos:
    >>Imprime por pantalla todas las matrículas ordenadas alfabéticamente de todos  los coches grises distintos
    >>Imprime por pantalla todas las marcas de coches distintas de aquellos coches que estén disponibles.
    >>Saber la cantidad de vehículos Citroën.
    >>Comprobar si hay algún Peugeot negro disponible en la lista.
    >>Obtener una lista con todas las tarifas diferentes que se aplican a los vehículos.
     */
    public static void main(String[] args) {
        //---
        crearDirectorio("copias");
        //---
        copiarFicheros("./Turismos.csv", "./copias/Turismos.csv");
        copiarFicheros("./Deportivos.csv", "./copias/Deportivos.csv");
        copiarFicheros("./Furgonetas.csv", "./copias/Furgonetas.csv");
        //---
        List<Vehiculo> listaGeneral = unirListas(generarListaConTurismos("./copias/Turismos", "csv", ":"),
                generarListaConDeportivos("./copias/Deportivos", "csv", ":"),
                generarListaConFurgonetas("./copias/Furgonetas", "csv", ":"));
        //---
        System.out.println("---*** Imprimimos la lista ***---");
        listaGeneral.forEach(System.out::println);
        //---
        Comparator<Vehiculo> criterioBastidor = (v1, v2) -> Long.compare(v1.getBastidor(), v2.getBastidor());
        Collections.sort(listaGeneral, criterioBastidor);
        //---
        System.out.println("---*** Imprimimos la lista ordenada ***---");
        listaGeneral.forEach(System.out::println);
        //---
        borrarElemento("./Turismos.csv");
        borrarElemento("./Deportivos.csv");
        borrarElemento("./Furgonetas.csv");
        //---
        System.out.println("---*** Mostramos que contiene nuestro directorio principal ***---");
        listarDirectorio("./");
        //---
        System.out.println("---*** Mostramos ordenados por matrícula los vehiculos grises ***---");
        listaGeneral.stream()
                .filter(v -> v.getColor().equalsIgnoreCase("gris"))
                .sorted((v1, v2) -> v1.getMatricula().compareToIgnoreCase(v2.getMatricula()))
                .forEach(System.out::println);
        //---
        System.out.println("---*** Mostramos las marcas de los vehiculos disponibles ***---");
        //--- Usamos el ! porque por defecto la disponibilidad está a false
        listaGeneral.stream()
                .filter(v -> !v.isDisponible())
                .map(v -> v.getMarca())
                .distinct()
                .forEach(System.out::println);
        //---
        System.out.println("---*** Mostramos cuantos vehículos son de la marca Citroën ***---");
        System.out.println(listaGeneral.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("Citroën"))
                .count());
        //---
        System.out.println("---*** Mostramos si hay algún Peugeot negro disponible ***---");
        System.out.println(listaGeneral.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("Peugeot"))
                .filter(v -> v.getColor().equalsIgnoreCase("Negro"))
                .filter(v -> v.isDisponible())
                .count());
        //---
        List<Double> tarifasUnicas = listaGeneral.stream()
                .map(v -> v.getTarifa())
                .distinct()
                .toList();
        System.out.println("---*** Mostramos la lista de tarifas únicas ***---");
        tarifasUnicas.forEach(System.out::println);

    }

    public static List<Vehiculo> generarListaConTurismos(String nomFichero, String formato, String separador) {
        List<Vehiculo> lista = new ArrayList<>();
        //---
        String idFichero = nomFichero + "." + formato;

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        //---

        //---
        Vehiculo v = null;
        //---
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split(separador);
                // Convierte en String tokens
                //--- Dependiendo de como empiece la línea sabremos que tipo de objeto es
                //--- Usamos los tokens[] para guardar cada atributo según el constructor
                tokens[0] = tokens[0].replaceAll("0 - ", "");
                v = new Turismo(Long.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], Double.parseDouble(tokens[5]), Integer.parseInt(tokens[7]), Boolean.parseBoolean(tokens[8]));

                //--- Añadimos el Vehículo a la lista
                lista.add(v);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //---

        return lista;

    }

    public static List<Vehiculo> generarListaConDeportivos(String nomFichero, String formato, String separador) {
        List<Vehiculo> lista = new ArrayList<>();
        //---
        String idFichero = nomFichero + "." + formato;

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        //---

        //---
        Vehiculo v = null;
        //---
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split(separador);
                // Convierte en String tokens
                //--- Dependiendo de como empiece la línea sabremos que tipo de objeto es
                //--- Usamos los tokens[] para guardar cada atributo según el constructor

                //--- Si es un deportivo                   
                tokens[0] = tokens[0].replaceAll("1 - ", "");
                v = new Deportivo(Long.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], Double.parseDouble(tokens[5]), Integer.parseInt(tokens[7]));

                //--- Añadimos el Vehículo a la lista
                lista.add(v);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return lista;

    }

    public static List<Vehiculo> generarListaConFurgonetas(String nomFichero, String formato, String separador) {
        List<Vehiculo> lista = new ArrayList<>();
        //---
        String idFichero = nomFichero + "." + formato;

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        //---

        //---
        Vehiculo v = null;
        //---
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split(separador);
                // Convierte en String tokens
                //--- Dependiendo de como empiece la línea sabremos que tipo de objeto es
                //--- Usamos los tokens[] para guardar cada atributo según el constructor

                //--- Si es una furgoneta
                tokens[0] = tokens[0].replaceAll("2 - ", "");
                v = new Furgoneta(Long.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], Double.parseDouble(tokens[5]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));

                //--- Añadimos el Vehículo a la lista
                lista.add(v);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return lista;

    }

    public static List<Vehiculo> unirListas(List<Vehiculo> l1, List<Vehiculo> l2, List<Vehiculo> l3) {
        List<Vehiculo> lista = new ArrayList<>();

        lista.addAll(l1);
        lista.addAll(l2);
        lista.addAll(l3);

        return lista;
    }

    public static void crearDirectorio(String ruta) {

        Path directory = Paths.get(ruta);
        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }

    }

    public static void copiarFicheros(String rutaOrigen, String rutaDestino) {
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);
        try {
            Files.copy(origen, destino);
        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());
        }
    }

    public static void borrarElemento(String ruta) {
        Path file = Paths.get(ruta);
        try {
            Files.delete(file);
        } catch (NoSuchFileException nsfe) {
            System.out.println("No se puede borrar " + ruta + " porque no existe");
        } catch (DirectoryNotEmptyException dnee) {
            System.out.println("No se puede borrar el directorio porque no está vacío");
        } catch (IOException e) {
            System.out.println("Problema borrando el elemento " + ruta);
        }
    }

    public static void listarDirectorio(String ruta) {

        File f = new File(ruta);
        if (f.exists()) {
            // Obtiene los ficheros y directorios dentro de f y los 
            // devuelve en un array
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }
    }
    }
    

