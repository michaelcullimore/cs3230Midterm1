//Michael Cullimore
//CS3230 - Marsh
//Spring 2017

package midterm1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import blackjack.game.Card;
import blackjack.message.CardMessage;
import blackjack.message.ChatMessage;
import blackjack.message.GameActionMessage;
import blackjack.message.GameStateMessage;
import blackjack.message.LoginMessage;
import blackjack.message.Message;
import blackjack.message.MessageFactory;

public class Client implements Runnable {

    protected static String server_IP = "52.35.72.251";
    protected static String client_IP;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
	BlackjackGame game;
	String ipAddress = "52.35.72.251";
	int init = 0;
	final int PORT_NUMBER = 8989;
	Socket socket = new Socket();

	try {
	    socket.connect(new InetSocketAddress(ipAddress, PORT_NUMBER), 8989);
	    socket.setSoTimeout(0);

	    // System.out.println("hello3");
	    // init = initialize(socket01);
	} catch (SocketException e) {
	    System.out.println("Error: Unable to connect to server port ");
	}

	// Object Streams
	ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
	ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());
	Message serverMessage;

	// Login Attempt
	String username = "MCullimore75";
	LoginMessage login = MessageFactory.getLoginMessage(username);
	outObject.writeObject(login);
	outObject.flush();
	game = new BlackjackGame(socket, outObject, inObject);
	serverMessage = (Message) inObject.readObject();
	if (serverMessage.getType() == Message.MessageType.ACK) {
	    game.addText("Username accepted. Successfully logged in.");
	} else {
	    game.addText("Username not accepted. Login Unsuccessful");
	}
	ArrayList<Card> hand = new ArrayList<>();

	while (true) {
	    try {
		serverMessage = (Message) inObject.readObject();
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    switch (serverMessage.getType()) {
	    case CARD: {
		CardMessage cm = (CardMessage) serverMessage;
		if (cm.getUsername().equals(username)) {
		    hand.add(cm.getCard());
		    for (Card c : hand) {
			game.addText("Your cards: " + c.getValue() + " of " + c.getSuite());
		    }
		} else {
		    game.addText(cm.getUsername() + " was dealt a card.");
		}
		break;
	    }
	    case CHAT: {
		game.addText(
			((ChatMessage) serverMessage).getUsername() + ": " + ((ChatMessage) serverMessage).getText());
		game.addText(
			((ChatMessage) serverMessage).getUsername() + ": " + ((ChatMessage) serverMessage).getText());
		break;
	    }
	    case DENY: {
		if (serverMessage.getUsername().equals(username)) {
		    game.addText("You have been denied by the server.");
		}
		game.addText("You have been denied by the server.");
		break;
	    }
	    case GAME_ACTION: {
		GameActionMessage ga = (GameActionMessage) serverMessage;
		switch (ga.getAction()) {// switchception
		case BUST: {
		    game.addText(ga.getUsername() + " has busted.");
		    break;
		}
		case HIT: {
		    game.addText(ga.getUsername() + " hit.");
		    break;
		}
		case STAY: {
		    game.addText(ga.getUsername() + " has stayed.");
		    break;
		}
		case WIN: {
		    game.addText(ga.getUsername() + " has won the game!");
		}
		default:
		    game.addText("Error. Unknown message from server: " + serverMessage.getType().toString());
		    break;
		}
		serverMessage = null;
	    }
	    case GAME_STATE: {
		GameStateMessage gs = (GameStateMessage) serverMessage;
		switch (gs.getRequestedState()) {// switchception2
		case JOIN: {
		    game.addText("To join the game, please type join: (Timeout in 30 seconds)");
		    break;
		}
		case START: {
		    game.addText("Game has begun.");
		    break;
		}
		default:
		    break;
		}
		break;
	    }
	    case LOGIN: {
		game.addText(serverMessage.getUsername() + "has connected.");
		break;
	    }
	    default:
		break;
	    }

	    try {
		InetAddress iAddress = InetAddress.getLocalHost();
		client_IP = iAddress.getHostAddress();
		System.out.println("Current IP address : " + client_IP);
	    } catch (UnknownHostException e) {
	    }

	    if (init == 0) {
		System.out.println("error: Failed to initialize ");
		System.exit(0);

	    }

	}
    }

    @Override
    public void run() {
    }
}
