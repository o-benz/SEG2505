// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import java.io.IOException;

import common.ChatIF;
import ocsf.client.AbstractClient;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	String loginID;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI, String loginID) {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		this.loginID = loginID;
		try {
			openConnection();
			sendToServer("#login " + loginID);
		} catch (IOException exception) {
			System.out.println("Cannot open connection. Awaiting command.");
		}

	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		if (msg.toString().startsWith("SERVER MSG>")) {
			System.out.println(msg.toString());
		} else {
			System.out.println("> " + msg.toString());
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {
		try {

			if (message.startsWith("#")) {

				message = message.substring(1);

				switch (message) {

				case "quit":
					quit();
					break;
				case "logoff":
					closeConnection();
					break;
				case "login":
					if (isConnected())
						System.out.println("ERROR: The client is already connected to the server!");
					openConnection();
					break;
				case "gethost":
					System.out.println("Host: " + getHost());
					break;
				case "getport":
					System.out.println("Port: " + getPort());
					break;
				default:
					if (message.contains("sethost")) {

						if (!isConnected()) {
							message = message.substring(8);
							setHost(message);
							System.out.println("Host set to: " + message);
						} else
							System.out.println("ERROR: Client must be logged off!");

					} else if (message.contains("setport")) {

						if (!isConnected()) {

							try {
								message = message.substring(8);
								setPort(Integer.parseInt(message));
								System.out.println("Port set to: " + message);
							} catch (NumberFormatException e) {
								System.out.println("ERROR: Port must be a number!");
							}

						} else
							System.out.println("ERROR: Client must be logged off!");
					}
					break;
				}
			} else
				sendToServer(message);

		} catch (IOException e) {
			clientUI.display("Could not send message to server.  Terminating client.");
			quit();
		}
	}

	/**
	 * This method closes the client if the server closes
	 */

	@Override
	public void connectionClosed() {
		System.out.println("Connection Closed.");
	}

	@Override
	public void connectionException(Exception exception) {
		System.out.println("The server has shut down. Disconnecting...");
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
//End of ChatClient class
