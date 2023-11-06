
import javax.swing.JOptionPane;

public class Nodo {

    int id;
    String ape, nom, ciu;
    Nodo izq, der;

    public Nodo() {
        id = 0;
        ape = nom = ciu = "";
        izq = der = null;
    }

    public void leerInfo() {
        id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cedula: "));
        ape = JOptionPane.showInputDialog("Ingrese apellido: ");
        nom = JOptionPane.showInputDialog("Ingrese nombre: ");
    }

    public Nodo getInfo() {
        Nodo tmp = new Nodo();
        tmp.id = id;
        tmp.ape = ape;
        tmp.nom = nom;
        tmp.ciu = ciu;
        return tmp;
    }
}
