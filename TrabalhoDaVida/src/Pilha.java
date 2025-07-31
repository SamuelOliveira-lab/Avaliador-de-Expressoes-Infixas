import java.util.EmptyStackException;


public class Pilha<T> {

    private T item[];
    private int topo;

    public Pilha(int tamanho) {
        item = (T[]) new Object[tamanho];
        topo = -1;
    }

    public void empilha(T x) {
        topo++;
        this.item[topo] = x;
    }

    public T desempilhar() {
        if (topo == -1) {
            throw new EmptyStackException();
        }

        T aux = item[topo];
        topo--;
        return aux;
    }

    public T peek() {
        if (topo == -1) {
            throw new EmptyStackException();
        }
        return item[topo];
    }

    public int tamanho() {
        return topo + 1;
    }

    public boolean vazia() {
        if (topo == -1) {
            return true;
        } else {
            return false;
        }
    }
}
