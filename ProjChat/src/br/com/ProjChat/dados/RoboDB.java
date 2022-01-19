package br.com.ProjChat.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ProjChat.db.Conexao;
import br.com.ProjChat.modelo.Robo;

public class RoboDB {

	// PreparedStatement -> responsável por executar e incluir paramentros na
	// query.
	private PreparedStatement ps;
	// responsável por abrir a conexão
	private Connection connection;
	private static RoboDB roboDB;
	private ResultSet rs;

	// Construtor Privado -> Logo a classe AlunoDB não pode ser instanciada
	// externamente.
	private RoboDB() {
		this.connection = Conexao.getConnection();
	}

	// Singleton - JAVA - Método responsável com instanciar a classe AlunoDB
	public static RoboDB getConnection() {
		return roboDB = new RoboDB();
	}

	public boolean insert(Robo robo) {
		boolean status = false;

		try {
			// Construtor de Strings
			// Neste contexto apenas utilizado para concatenar Strings
			StringBuilder sb = new StringBuilder();

			// Query Insert
			sb.append(" insert into tb_robo (pergunta, resposta, nomeusuario) ");
			sb.append(" values (?,?, ?) ");

			// Recebe a instancia do PreparedStatement e passa a query SQL:
			ps = connection.prepareStatement(sb.toString());

			// Inclui as informações na query de acordo com a ordem dos
			// parâmetros (?):
			ps.setString(1, robo.getPergunta());
			ps.setString(2, robo.getResposta());
			ps.setString(3, robo.getCliente().getNome());

			// Executar o comando SQL
			ps.execute();
			// Fecha o PreparedStatement
			ps.close();
			// Fecha a conexão

			status = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return status;
	}

	public List<Robo> getListPerResp() {
		List<Robo> lstItens = new ArrayList<Robo>();
		try {

			ResultSet rs;
			PreparedStatement ps;

			StringBuilder sb = new StringBuilder();

			sb.append("select pergunta, resposta from tb_respostas");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {

				Robo robo = new Robo();
				robo.setPergunta(rs.getString("pergunta"));
				robo.setResposta(rs.getString("resposta"));
				lstItens.add(robo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstItens;
	}

}
