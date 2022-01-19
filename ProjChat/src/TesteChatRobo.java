import javax.swing.JOptionPane;

import br.com.ProjChat.dados.RoboDB;
import br.com.ProjChat.modelo.Robo;
import br.com.ProjChat.modelo.Usuario;

public class TesteChatRobo {

	public static void main(String[] args) {

		String entrada = "";

		do {

			entrada = JOptionPane.showInputDialog("Msg / 0 para sair");
			RoboUser r = new RoboUser();

			String retornoRobo = r.processaTexto(entrada);

			Robo c = new Robo();
			Usuario u = new Usuario("Isa");

			c.setPergunta(entrada);
			c.setResposta(retornoRobo);
			c.setCliente(u);

			System.out.println(c.getCliente().getNome() + ": " + entrada);
			System.out.println("Robô: " + retornoRobo);

			RoboDB.getConnection().insert(c);

		} while (!entrada.equals("0"));
		
	}
}
