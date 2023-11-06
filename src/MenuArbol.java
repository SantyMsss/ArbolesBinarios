import javax.swing.JOptionPane;

public class MenuArbol {

    ArbolBinario ab = new ArbolBinario();
    String noms[] = {"Ana", "Sandra", "Lucia", "Caro", "Lorena", "Camilo", "Pablo", "Juan", "Luis", "Pepe"};
    String apes[] = {"Rios", "Roa", "Perez", "Mena", "Garcia", "Martinez", "Hernandez", "Arias", "Castro", "Cobo"};
    String ciuds[] = {"Buga", "Cali", "Tulua", "Zarzal", "Trujillo", "Darien"};
    int ids[] = new int[100000];
    
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
    
    public void crearDatos(){
        crearIds(); // primero crear IDS aleatorios y que no se repitan
        for (int i = 0; i < ids.length; i++) {
            Nodo nuevo = new Nodo(); // crear el nodo
            nuevo.id = ids[i];
            nuevo.ape = apes[(int)(Math.random() * apes.length)];
            nuevo.nom = noms[(int)(Math.random() * noms.length)];
            nuevo.ciu = ciuds[(int)(Math.random() * ciuds.length)];
            ab.insertar(nuevo); // insertar el nodo en el arbol
        }
    }
    
    public void menu() {
        boolean salir = false;
        do {
            String opc = JOptionPane.showInputDialog(
                    """
                    1. Adicionar
                    2. Pre-orden
                    3. Entre-orden
                    4. Pos-orden
                    0. Salir
                    Seleccione una opcion: """);
            switch (opc) {
                case "1" -> crearDatos();
                case "2" -> ab.imprimirPre();
                case "3" -> ab.imprimirEntre();
                case "4" -> ab.imprimirPost();
                case "0" -> salir = true;
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
                RAM: 16 GB
                Procesador: AMD Ryzen™ 5 5600H - 3.3GHz - 6 nucleos / 12 hilos
                Sistema operativo Windows 11
                Arquitectura 64 bits
                """);
        ma.menu();
    }
    
}
