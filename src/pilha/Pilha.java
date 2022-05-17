package pilha;


public class Pilha {

	String elementos[];
	int topo;

	public Pilha() {
		elementos = new String[100];
		topo = -1;


	}
	public void push (String e) { // Armazena os dados
		topo++;
		elementos[topo]= e;

	} 
	public String pop() { // Libera os dados 
		String e;
		e = elementos[topo];
		topo--;
		return e;
		
	}
	public boolean isEmpty() { // Verificar se está vazia  
		return (topo == -1);
	
	}
	public boolean isFull() { // Verificar se está vazia ou não 
		return (topo == 1);
		
	}
	public String top() { // Retornar o dado do topo
		return elementos[topo];
	}
}
