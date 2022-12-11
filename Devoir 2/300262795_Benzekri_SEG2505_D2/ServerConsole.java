import java.util.Scanner;
import common.ChatIF;

public class ServerConsole implements ChatIF {

	Scanner fromConsole;
	EchoServer es;

	public ServerConsole(EchoServer es) {

		this.es = es;
		fromConsole = new Scanner(System.in);

	}

	public void accept() {
		try {

			String message;

			while (true) {
				message = fromConsole.nextLine();
				es.handleMessageFromServerUI(message, this);
			}
		} catch (Exception e) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	@Override
	public void display(String message) {
		System.out.println("SERVER MSG> " + message);
	}

}
