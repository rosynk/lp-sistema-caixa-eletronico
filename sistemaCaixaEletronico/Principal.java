package sistemaCaixaEletronico;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Principal {
	public static void main(String[] args) throws IOException {
		
		int n = 0;
		Caixa[] caixa = new Caixa[1];
		Metodos m = new Metodos();
		
		do {
			
			n = Integer.parseInt(JOptionPane.showInputDialog("Menu Principal\r\n"
					+ "1 – Carregar Notas\r\n"
					+ "2 – Retirar Notas\r\n"
					+ "3 – Estatística\r\n"
					+ "9 – Fim\r\n"));
			
			
			switch (n) {
			case 1: 
				m.carregarNotas(caixa);
				break;
			case 2: 
				m.retirarNotas(caixa);
				break;
			case 3: 
				m.gravarArquivos(caixa);
				break;
			case 9: 
				break;
			default:
				System.out.println("Tente Novamente");
			}
			
		} while (n!=9);
		
	}
}
