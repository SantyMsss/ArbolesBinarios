
public class ArbolBinario {

    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar() {
        Nodo nuevo = new Nodo();
        nuevo.leerInfo();
        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo anterior = null, reco;
            reco = raiz;
            while (reco != null) {
                anterior = reco;
                if (nuevo.id < reco.id) {
                    reco = reco.izq;
                } else {
                    reco = reco.der;
                }
            }
            if (nuevo.id < anterior.id) {
                anterior.izq = nuevo;
            } else {
                anterior.der = nuevo;
            }
        }
    }
    
    // Inserta un nuevo nodo en el arbol con los datos enviados desde MenuArbol, donde se generam los datos aleatorios
    public void insertar(Nodo nuevo) { 
        //Nodo nuevo = new Nodo();
        //nuevo.leerInfo();
        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo anterior = null, reco;
            reco = raiz;
            while (reco != null) {
                anterior = reco;
                if (nuevo.id < reco.id) {
                    reco = reco.izq;
                } else {
                    reco = reco.der;
                }
            }
            if (nuevo.id < anterior.id) {
                anterior.izq = nuevo;
            } else {
                anterior.der = nuevo;
            }
        }
    }

    private void imprimirPre(Nodo reco) {
        if (reco != null) {
            System.out.println(reco.id + ";" + reco.ape + ";" + reco.nom + ";" + reco.ciu);
            imprimirPre(reco.izq);
            imprimirPre(reco.der);
        }
    }

    public void imprimirPre() {
        imprimirPre(raiz);
        System.out.println();
    }

    private void imprimirEntre(Nodo reco) {
        if (reco != null) {
            imprimirEntre(reco.izq);
            System.out.println(reco.id + ";" + reco.ape + ";" + reco.nom + ";" + reco.ciu);
            imprimirEntre(reco.der);
        }
    }

    public void imprimirEntre() {
        imprimirEntre(raiz);
        System.out.println();
    }

    private void imprimirPost(Nodo reco) {
        if (reco != null) {
            imprimirPost(reco.izq);
            imprimirPost(reco.der);
            System.out.println(reco.id + ";" + reco.ape + ";" + reco.nom + ";" + reco.ciu);
        }
    }

    public void imprimirPost() {
        imprimirPost(raiz);
        System.out.println();
    }
}
