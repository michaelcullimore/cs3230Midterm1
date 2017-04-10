//Michael Cullimore
//CS3230 - Marsh
//Spring 2017

package midterm1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import blackjack.message.MessageFactory;

public class BlackjackGame extends JFrame implements WindowListener, MouseListener, KeyListener {
    /**
     *
     */
    private static final long serialVersionUID = 6;

    public static void main(String[] args) throws IOException {
	// new BlackjackGame();
    }

    private JPanel contentPanel = new JPanel();
    private JTextArea message_area = null;
    private JTextArea send_area = null;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket01;

    public BlackjackGame(Socket socket, ObjectOutputStream oos, ObjectInputStream ois)
	    throws HeadlessException, IOException {
	socket01 = socket;
	out = oos;
	in = ois;

	JPanel contentPanel = new JPanel();
	contentPanel.setLayout(new FlowLayout(BoxLayout.Y_AXIS));

	add(contentPanel);

	this.addWindowListener(this);
	this.setSize(new Dimension(800, 600));
	this.setResizable(true);
	this.setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	message_area = new JTextArea();
	message_area.setEditable(false);
	message_area.setLineWrap(true);
	message_area.setWrapStyleWord(true);
	JScrollPane scroll = new JScrollPane(message_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scroll.setSize(400, 100);
	contentPanel.add(scroll, "Center");

	JPanel inPanel = new JPanel();
	inPanel.setLayout(new FlowLayout());

	send_area = new JTextArea(3, 46);
	send_area.setEditable(true);
	send_area.setWrapStyleWord(true);
	send_area.setLineWrap(true);
	JScrollPane scroll01 = new JScrollPane(send_area);
	scroll01.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	inPanel.add(scroll01);

	send_area.addKeyListener(new KeyListener() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() == KeyEvent.CTRL_MASK) {
		    try {
			sendText();
		    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	    }
	});

	contentPanel.add(send_area);

	JButton send = new JButton("Send");
	send.addMouseListener(this);
	contentPanel.add(send);
	JButton clear = new JButton("Clear");
	clear.addMouseListener(this);
	contentPanel.add(clear);

	// clear button's function
	clear.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		send_area.setText(null);
	    }
	});

	send.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent pE) {
		try {
		    sendText();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});

	contentPanel.add(contentPanel, "South");
	contentPanel.setVisible(true);
	getContentPane().add(contentPanel);
	send_area.requestFocusInWindow();

    }

    public void addText(String chat) {
	message_area.append(chat + "\n");
	message_area.setCaretPosition(message_area.getDocument().getLength());

    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() == KeyEvent.CTRL_MASK) {
	    try {
		sendText();
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}
    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    public void logout() {
	try {
	    socket01.close();
	    out.close();
	    in.close();
	    addText("You have successfully logged out.");
	    send_area.setText("");
	} catch (IOException e) {
	    System.err.println("Socket was unable to be closed...");
	    e.printStackTrace();
	}
	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }

    private void sendText() throws IOException {
	String chatSend;
	// String username = "MCullimore75";
	chatSend = send_area.getText();
	switch (chatSend) {
	case "HIT": {
	    out.writeObject(MessageFactory.getHitMessage());
	    out.flush();
	    break;
	}
	case "JOIN": {
	    out.writeObject(MessageFactory.getJoinMessage());
	    out.flush();
	    break;
	}
	case "START": {
	    out.writeObject(MessageFactory.getStartMessage());
	    out.flush();
	    break;
	}
	case "STAY": {
	    out.writeObject(MessageFactory.getStayMessage());
	    out.flush();
	    break;
	}
	case "quit": {
	    logout();
	    return;
	}
	}
	// message_area.append(username + ": " + chatSend + "\n\n");
	send_area.setText("");
	out.writeObject(MessageFactory.getChatMessage(chatSend));
	out.flush();
    }

    @Override
    public void windowActivated(WindowEvent arg0) {

    }

    @Override
    public void windowClosed(WindowEvent arg0) {

    }

    @Override
    public void windowClosing(WindowEvent arg0) {

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {

    }

    @Override
    public void windowIconified(WindowEvent arg0) {

    }

    @Override
    public void windowOpened(WindowEvent arg0) {

    }

}
