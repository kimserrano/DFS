import java.util.*;  // Importa las clases de la biblioteca de utilidades de Java, como Map, List, HashMap, ArrayList

public class DFS {  // Definición de la clase DFS
    private Map<Character, List<Character>> adjList;  // Mapa para representar la lista de adyacencia del grafo
    private Map<Character, Character> padres;  // Mapa para almacenar el nodo padre de cada nodo en el recorrido DFS
    private Map<Character, String> estado;  // Mapa para llevar el estado de cada nodo: No Visitado, Descubierto, Visitado

    public DFS() {  // Constructor de la clase DFS
        adjList = new HashMap<>();  // Inicializa el mapa de lista de adyacencia
        padres = new HashMap<>();  // Inicializa el mapa de padres
        estado = new HashMap<>();  // Inicializa el mapa de estados
    }

    // Método para agregar aristas entre dos nodos
    public void agregarArista(char u, char v) {
        adjList.putIfAbsent(u, new ArrayList<>());  // Si el nodo u no existe en la lista de adyacencia, se agrega con una lista vacía
        adjList.putIfAbsent(v, new ArrayList<>());  // Lo mismo para el nodo v
        adjList.get(u).add(v);  // Agrega una arista de u a v en la lista de adyacencia
        
        // Inicializa el estado de u y v como "No Visitado" si aún no están en el mapa de estados
        estado.putIfAbsent(u, "No Visitado");
        estado.putIfAbsent(v, "No Visitado");
    }

    // Método para mostrar los estados actuales de todos los nodos
    public void mostrarEstados() {
        System.out.println("Estados de los nodos:");
        // Recorre cada entrada en el mapa de estados y muestra el nodo y su estado
        for (Map.Entry<Character, String> entry : estado.entrySet()) {
            System.out.println("Nodo: " + entry.getKey() + " Estado: " + entry.getValue());
        }
        System.out.println();  // Línea en blanco para separar visualmente la salida
    }

    // DFS para un solo nodo
    public void dfs(char u) {
        // Marca el nodo u como "Descubierto"
        estado.put(u, "Descubierto");
        System.out.println("Descubriendo nodo: " + u);  // Imprime un mensaje indicando que el nodo está siendo descubierto
        mostrarEstados();  // Muestra los estados actuales

        // Recorre los nodos adyacentes de u
        for (char v : adjList.getOrDefault(u, new ArrayList<>())) {
            // Si el nodo adyacente v no ha sido visitado, se recorre recursivamente
            if (estado.get(v).equals("No Visitado")) {
                padres.put(v, u);  // Asigna a u como el padre de v
                dfs(v);  // Llama recursivamente al DFS para el nodo v
            }
        }

        // Una vez completado el recorrido, marca el nodo u como "Visitado"
        estado.put(u, "Visitado");
        System.out.println("Marcando nodo como visitado: " + u);  // Imprime mensaje indicando que u ha sido visitado
        mostrarEstados();  // Muestra los estados actuales después de visitar el nodo
    }

    // DFS para recorrer todo el bosque (todos los componentes del grafo)
    public void dfsForest() {
        mostrarEstados();  // Muestra los estados iniciales de los nodos

        // Recorre cada nodo en el grafo (cada clave en el mapa de adyacencia)
        for (char v : adjList.keySet()) {
            // Si el nodo no ha sido visitado, inicia un DFS desde ese nodo
            if (estado.get(v).equals("No Visitado")) {
                System.out.println("Iniciando DFS en componente: " + v);  // Imprime mensaje cuando inicia DFS en un componente
                dfs(v);  // Llama a DFS para el nodo v
            }
        }
    }

    // Método para imprimir los padres de cada nodo
    public void imprimirPadres() {
        System.out.println("Padres del DFS:");
        // Recorre el mapa de padres y muestra el nodo y su padre
        for (Map.Entry<Character, Character> entry : padres.entrySet()) {
            System.out.println("Nodo: " + entry.getKey() + " Padre: " + entry.getValue());
        }
    }

    // Método main para ejecutar el programa
    public static void main(String[] args) {
        DFS grafo = new DFS();  // Crea una instancia de la clase DFS

        // Agrega aristas al grafo
        grafo.agregarArista('A', 'B');
        grafo.agregarArista('A', 'C');
        grafo.agregarArista('B', 'D');
        grafo.agregarArista('D', 'C');
        grafo.agregarArista('C', 'B');
        grafo.agregarArista('E', 'D');
        grafo.agregarArista('E', 'F');

        // Realiza DFS en todos los componentes del grafo
        grafo.dfsForest();

        // Imprime los padres de cada nodo en el DFS
        grafo.imprimirPadres();
    }
}
