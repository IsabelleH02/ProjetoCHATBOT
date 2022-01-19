import java.util.ArrayList;
import java.util.List;

import br.com.ProjChat.dados.RoboDB;
import br.com.ProjChat.modelo.Robo;

public class RoboUser {

	public String processaTexto(String str) {

		String[] valor = str.split(" ");
		return consultaBanco(valor);
	}

	public String consultaBanco(String[] valor) {

		List<String> lstDados = new ArrayList<>();
		int qtdPalavrasEncontradas = 0;
		String resposta = "";

		// Implementação
		// 1 - Consultar na base de dados as respostas disponiveis

		for (Robo robo : RoboDB.getConnection().getListPerResp()) {

			/*
			 * lstDados.add("Bom"); lstDados.add("dia"); String resposta =
			 * "Bom dia Sr(a)!";
			 */

			String[] valorPer = robo.getPergunta().split(" ");
			int qtdElementos = valorPer.length;
			int i = 0;

			while (i < qtdElementos) {
				lstDados.add(valorPer[i]);
				i++;
				resposta = robo.getResposta();
				for (String string : lstDados) {
					for (int j = 0; j < valor.length; j++) {
						if (string.equals(valor[j])) {
							qtdPalavrasEncontradas++;
						}
					}
					
				}

			}
			
			if (qtdPalavrasEncontradas >= lstDados.size()) {
				return resposta;
			}

			
		}
		return "Desculpe, Não entendi!";
		}

		}



	// Comparar com o select

		// Fim da consulta na base de dados
