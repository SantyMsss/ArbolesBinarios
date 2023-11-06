
import javax.swing.JOptionPane;

public class Nodo {

    int id;
    String ape, nom, ciu, edad, semestre, sexo;
    Nodo izq, der;

    public Nodo() {
        id = 0;
        ape = nom = ciu = edad = semestre = sexo ="";
        izq = der = null;
    }

    public void leerInfo() {
        id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cedula: "));
        ape = JOptionPane.showInputDialog("Ingrese apellido: ");
        nom = JOptionPane.showInputDialog("Ingrese nombre: ");
        ciu = JOptionPane.showInputDialog("Ingrese ciudad: ");
        edad = JOptionPane.showInputDialog("Ingrese edad: ");
        semestre = JOptionPane.showInputDialog("Ingrese semestre: ");
        sexo = JOptionPane.showInputDialog("Ingrese sexo: ");

    }

    public Nodo getInfo() {
        Nodo tmp = new Nodo();
        tmp.id = id;
        tmp.ape = ape;
        tmp.nom = nom;
        tmp.ciu = ciu;
        tmp.edad = edad;
        tmp.semestre = semestre;
        tmp.sexo = sexo;


        return tmp;
    }
}
