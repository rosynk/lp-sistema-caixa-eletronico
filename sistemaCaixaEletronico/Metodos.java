package sistemaCaixaEletronico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Metodos {
	
	double media;
	int divisor = 0;
	int somaSaque = 0;
	int quantidadeCaixa = 0;
	
	public Caixa[] carregarNotas(Caixa[] caixa) {

		int[] notas = { 2, 5, 10, 20, 50, 100, 200 };

		for (int i = 0; i < caixa.length; i++) {
			caixa[i] = new Caixa(); // Inicializa um novo objeto Caixa
			for (int j = 0; j < notas.length; j++) {
				// Inicializa a quantidade de notas e os valores das notas
				caixa[i].vetor[0][j] = 100; // Define a quantidade de notas
				caixa[i].vetor[1][j] = notas[j]; // Define os valores das notas
			}
		}

		for (int i = 0; i < caixa.length; i++) {
			for (int j = 0; j < notas.length; j++) {
				System.out.println("Caixa >>> Quantidade de notas: " + caixa[i].vetor[0][j] + " - Valor da nota: "
						+ caixa[i].vetor[1][j]);
			}
		}

		return caixa;
	}

	public Caixa[] retirarNotas(Caixa[] caixa) {

		int saque;
		boolean saldoPositivo = true;

		// Conferir a quantidade de notas disponíveis
		for (int i = 0; i < caixa.length; i++) {
			for (int j = 0; j < caixa.length; j++) {
				if (caixa[i].vetor[0][j] < 0) {
					saldoPositivo = false;
					System.out.println("VALOR SOLICITADO NÃO PODE SER SACADO");
				}
			}
		}

		saque = Integer.parseInt(JOptionPane.showInputDialog("Insira o valor a ser sacado: "));
		
		somaSaque += saque;
		divisor += 1;
		
		if (saque < 0) { // saque negativo
			saque = Integer.parseInt(
					JOptionPane.showInputDialog("O valor não pode ser negativo.\nInsira o valor a ser sacado: "));
		} else if (saque == 3) { // saque solicitado de 3$
			saque = Integer
					.parseInt(JOptionPane.showInputDialog("O valor não pode ser 3.\nInsira o valor a ser sacado: "));
		} else if (saque < 2 || saque > 3000) { // o menor valor deve ser 2 e o maior 3000
			saque = Integer.parseInt(JOptionPane.showInputDialog(
					"Não é possível fazer o valor: " + saque + "$ de retirada ./\nInsira o valor a ser sacado: "));
		}

		do {
			for (int i = 0; i < caixa.length; i++) {
				for (int j = 0; j < caixa.length; j++) {
					if (saque % 2 == 1) { // saques que vão precisar de 1$

					} else {
						if (saque >= 200 && saldoPositivo == true) {
							caixa[i].vetor[0][6] -= 1;
							saque -= 200;
						} else if (saque >= 100 && saque < 200 && saldoPositivo == true) {
							caixa[i].vetor[0][5] -= 1;
							saque -= 100;
						} else if (saque >= 50 && saque < 100 && saldoPositivo == true) {
							caixa[i].vetor[0][4] -= 1;
							saque -= 50;
						} else if (saque >= 20 && saque < 50 && saldoPositivo == true) {
							caixa[i].vetor[0][3] -= 1;
							saque -= 20;
						} else if (saque >= 10 && saque < 20 && saldoPositivo == true) {
							caixa[i].vetor[0][2] -= 1;
							saque -= 10;
						} else if (saque >= 5 && saque < 10 && saldoPositivo == true) {
							caixa[i].vetor[0][1] -= 1;
							saque -= 5;
						} else if (saque >= 2 && saque < 5 && saldoPositivo == true) {
							caixa[i].vetor[0][0] -= 1;
							saque -= 2;
						}
					}
				}
			}
		} while (saque != 0);

		int[] notas = { 2, 5, 10, 20, 50, 100, 200 };

		for (int i = 0; i < caixa.length; i++) {
			for (int j = 0; j < notas.length; j++) {
				System.out.println("Caixa >>> Quantidade de notas: " + caixa[i].vetor[0][j] + " - Valor da nota: "
						+ caixa[i].vetor[1][j]);
				
				quantidadeCaixa += caixa[i].vetor[i][j];
			}
		}

		return caixa;
	}

	public void gravarArquivos(Caixa[] caixa) throws IOException {
		
		int valorTotal = 38700;
		
		media = somaSaque/divisor;
		
		String nomeArquivo = "resumoCaixa.txt";
		BufferedWriter gravar = new BufferedWriter(new FileWriter(nomeArquivo));
		gravar.write(String.valueOf(valorTotal));
		gravar.newLine();
		gravar.write(String.valueOf(media));
		gravar.newLine();
		gravar.write(String.valueOf(somaSaque));
		gravar.newLine();
		gravar.write(String.valueOf(divisor));
		gravar.newLine();
		gravar.write(String.valueOf(quantidadeCaixa));
		gravar.newLine();
				
		gravar.close();
		
	}

}