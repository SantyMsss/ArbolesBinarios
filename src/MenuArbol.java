import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MenuArbol {

    ArbolBinario ab = new ArbolBinario();
    String noms[] = {"Ana", "Sandra", "Lucia", "Caro", "Lorena", "Camilo", "Pablo", "Juan", "Luis", "Pepe"};
    String apes[] = {"Rios", "Serna", "Perez", "Mena", "Garcia", "Martinez", "Hernandez", "Arias", "Castro", "Cobo"};
    String ciuds[] = {"Buga", "Cali", "Tulua", "Zarzal", "Trujillo", "Darien", "Cartago", "Palmira"};

    // SE AGREGAN LAS TRES CAMPOS MAS DEL CLIENTE
    String edades[] = {"18", "19", "20", "21", "22", "23", "24", "25", "26", "27"};
    String semestres[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String sexos[] = {"Masculino", "Femenino"};



    int ids[] = new int[100000];

    
    // FUNCIONES QUE SE ENCARGAN DE LA CREACION DE LOS DATOS EN UN ARCHIVO CSV
    public void crearIds(){
        for (int i = 0; i < ids.length; ) {
            int n = 1 + (int)(Math.random() * 1000000);
            if(!existe(n)){
                ids[i] = n;
                i++;
            }
        }
    }
    
    private boolean existe(int id) {
        for (int i = 0; i < ids.length; i++) {            
            if(ids[i] == id) return true;
        }
        return false;
    }


    public void crearDatos() {
        // Crear IDs aleatorios y únicos
        crearIds();
    
        // Crear un objeto FileWriter para escribir en el archivo CSV
        try (FileWriter writer = new FileWriter("datos_generados.csv")) {
            for (int i = 0; i < ids.length; i++) {
                Nodo nuevo = new Nodo();
                nuevo.id = ids[i];
                nuevo.ape = apes[(int)(Math.random() * apes.length)];
                nuevo.nom = noms[(int)(Math.random() * noms.length)];
                nuevo.ciu = ciuds[(int)(Math.random() * ciuds.length)];

                nuevo.edad = edades[(int)(Math.random() * edades.length)];
                nuevo.semestre = semestres[(int)(Math.random() * semestres.length)];
                nuevo.sexo = sexos[(int)(Math.random() * sexos.length)];
                ab.insertar(nuevo);
    
                // Escribir el registro en el archivo CSV
                String registro = String.format("%d;%s;%s;%s;%s;%s;%s\n", nuevo.id, nuevo.ape, nuevo.nom, nuevo.ciu, nuevo.edad, nuevo.semestre, nuevo.sexo);
                writer.write(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void CargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("datos_generados.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 7) {
                    int id = Integer.parseInt(partes[0]);
                    String ape = partes[1];
                    String nom = partes[2];
                    String ciu = partes[3];
                    String edad = partes[4];
                    String semestre = partes[5];
                    String sexo = partes[6];
                    Nodo nuevo = new Nodo();
                    nuevo.id = id;
                    nuevo.ape = ape;
                    nuevo.nom = nom;
                    nuevo.ciu = ciu;
                    nuevo.edad = edad;
                    nuevo.semestre = semestre;              
                    nuevo.sexo = sexo;
                    ab.insertar(nuevo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // FUNCIONES ENCARGADAS DE RECORRER EL ARBOL EN ENTRE ORDEN Y GUARDAR LOS DATOS EN UN ARCHIVO CSV LLAMADO "datos_ordenados.csv"
    public void RecorrerOrden() {
        
        CargarDatos();

        long tiempoInicio = System.currentTimeMillis();


        try (FileWriter writer = new FileWriter("datos_ordenados.csv")) {
            RecorrerOrden(ab.raiz, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Detener el cronómetro
    long tiempoFin = System.currentTimeMillis();

    // Calcular la duración del proceso
    long tiempoTotal = tiempoFin - tiempoInicio;
    JOptionPane.showMessageDialog(null, "Tiempo de ejecucion: " + tiempoTotal + " milisegundos");
    JOptionPane.showMessageDialog(null, "Archivo datos_ordenados.csv creado exitosamente");

    }
    
    private void RecorrerOrden(Nodo reco, FileWriter writer) throws IOException {

        if (reco != null) {
            RecorrerOrden(reco.izq, writer);
            String registro = String.format("%d;%s;%s;%s;%s;%s;%s\n", reco.id, reco.ape, reco.nom, reco.ciu, reco.edad, reco.semestre, reco.sexo);
            writer.write(registro);
            RecorrerOrden(reco.der, writer);
        }
    }

    public void Consultas() {
        boolean salir = false;
        do {
            String opc = JOptionPane.showInputDialog(
                    """
                    MENU CONSULTAS
                    1. Consulta por la (ID)
                    





                    0. Volver al menú principal
                    Seleccione una opción: """);
            switch (opc) {
                case "1" -> consultaPorPK();
                case "0" -> salir = true;
                default ->
                    JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }
        } while (!salir);
    }
    
    public void consultaPorPK() {


        CargarDatos();


        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a consultar:"));
        visitasRealizadas = 0; // Reiniciar el contador de visitas
        long tiempoInicio = System.currentTimeMillis();
        Nodo encontrado = buscarPorID(ab.raiz, id);
        long tiempoFin = System.currentTimeMillis();
            



        if (encontrado != null) {
            JOptionPane.showMessageDialog(null, "Nodo encontrado:\n" +
                    "ID: " + encontrado.id + "\n" +
                    "Apellido: " + encontrado.ape + "\n" +
                    "Nombre: " + encontrado.nom + "\n" +
                    "Visitas realizadas: " + visitasRealizadas);
        } else {
            JOptionPane.showMessageDialog(null, "Nodo con ID " + id + " no encontrado");
        }
        mostrarTiempoEjecucion(tiempoInicio, tiempoFin);
}

        private void mostrarTiempoEjecucion(long tiempoInicio, long tiempoFin) {
            long tiempoTotal = tiempoFin - tiempoInicio;
            JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + tiempoTotal + " milisegundos");
        }
            
    
            private int visitasRealizadas = 0;
            
            private Nodo buscarPorID(Nodo reco, int id) {
                if (reco != null) {
                    visitasRealizadas++;
                    if (id == reco.id) {
                        return reco; // Nodo encontrado
                    } else if (id < reco.id) {
                        return buscarPorID(reco.izq, id); // Buscar en el subárbol izquierdo
                    } else {
                        return buscarPorID(reco.der, id); // Buscar en el subárbol derecho
                    }
                }
                return null; // Nodo no encontrado
            }
            





    
    public void menu() {
        boolean salir = false;
        do {
            String opc = JOptionPane.showInputDialog(
                    """
                    MENU TALLER ARBOL BINARIO
                    1. Recorrido del árbol
                    2. Menu Consultas
                    3. 
                    4. 
                    0. Salir
                    100. Crear datos aleatorios
                    Seleccione una opcion: """);
            switch (opc) {
                case "1" -> RecorrerOrden();
                case "2" -> Consultas();
                case "3" -> ab.imprimirEntre();
                case "4" -> ab.imprimirPost();
                case "0" -> salir = true;
                case "100" -> crearDatos();
                default -> 
                    JOptionPane.showMessageDialog(null, "Opcion incorrecta");
            }
        } while (!salir);
    }









    public static void main(String[] args) {
        MenuArbol ma = new MenuArbol();
        JOptionPane.showMessageDialog(null, 
                """
                CODIGO IMPLEMENTADO EN PC:
                Procesador: AMD Ryzen™ 5 5600H - 3.3GHz - 6 nucleos / 12 hilos
                Ram: 16GB DDR4 
                Almacenamiento: 512GB SSD
                Sistema operativo Windows 11
                Arquitectura 64 bits
                """);
        ma.menu();
    }
    
}
