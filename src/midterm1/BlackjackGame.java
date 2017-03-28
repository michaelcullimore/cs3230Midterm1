package midterm1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BlackjackGame extends JFrame implements WindowListener, MouseListener, KeyListener {
    /**
     *
     */
    private static final long serialVersionUID = 6;

    public static void main(String[] args) throws IOException {
	new BlackjackGame();
    }

    private JPanel contentPanel = new JPanel();
    private JTextArea message_area = null;
    private JTextField send_area = null;
    String name;
    String username = null;

    public BlackjackGame() throws IOException {

	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(BoxLayout.Y_AXIS));

	add(panel);

	this.addWindowListener(this);
	this.setSize(800, 600);
	this.setResizable(true);
	this.setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	contentPanel = new JPanel();
	contentPanel.setLayout(new FlowLayout());

	message_area = new JTextArea();
	message_area.setEditable(false);
	message_area.setLineWrap(true);
	message_area.setWrapStyleWord(true);
	JScrollPane scroll = new JScrollPane(message_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scroll.setSize(400, 100);

    }

    @Override
    public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }
}
