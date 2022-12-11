// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	private boolean hasAlreadyConnected = false;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		if (msg.toString().startsWith("#login")) {
			if (!hasAlreadyConnected) {
				try {
					client.sendToClient("ERROR: You cannot login again! Terminating connection...");
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Message received: " + msg + " from " + client.getInfo("loginID"));
				client.setInfo("loginID", msg.toString().substring(7));
				hasAlreadyConnected = false;
				this.sendToAllClients(client.getInfo("loginID") + " has logged on.");
				System.out.println(client.getInfo("loginID") + " has logged on.");
			}
		} else {
			System.out.println("Message received: " + msg + " from " + client.getInfo("loginID"));
			this.sendToAllClients(client.getInfo("loginID") + ": " + msg);
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	@Override
	synchronized public void clientDisconnected(ConnectionToClient client) {
		System.out.println(client.getInfo("loginID") + " has disconnected.");
		this.sendToAllClients(client.getInfo("loginID") + " has disconnected.");
	}

	@Override
	synchronized public void clientConnected(ConnectionToClient client) {
		System.out.println("A new client is attempting to connect to the server.");
		hasAlreadyConnected = true;
	}

	@Override
	synchronized public void clientException(ConnectionToClient client, Throwable exception) {
		System.out.println(client.getInfo("loginID") + " has disconnected.");
	}

	public void handleMessageFromServerUI(String msg, ServerConsole sc) {
		if (msg.startsWith("#")) {

			msg = msg.substring(1);

			switch (msg) {

			case "quit":
				try {
					close();
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "close":
				try {
					close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case "stop":
				stopListening();
				this.sendToAllClients("WARNING - The server has stopped listening for connections");
				break;
			case "start":
				try {
					if (!isListening()) {
						listen();
					} else {
						System.out.println("ERROR: Server is already started!");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "getport":
				System.out.println("Port: " + getPort());
				break;
			default:
				if (msg.contains("setport")) {
					if (!isListening()) {

						try {
							msg = msg.substring(8);
							setPort(Integer.parseInt(msg));
							System.out.println("Port set to: " + msg);
						} catch (NumberFormatException e) {
							System.out.println("ERROR: Port must be a number!");
						}

					} else
						System.out.println("ERROR: Server must be closed!");
				}
				break;

			}

		} else {
			sc.display(msg);
			sendToAllClients("SERVER MSG> " + msg);
		}
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);
		ServerConsole sc = new ServerConsole(sv);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		sc.accept();
	}
}
//End of EchoServer class