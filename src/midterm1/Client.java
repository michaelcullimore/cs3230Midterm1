package midterm1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client implements Runnable {

    protected static String server_IP = "137.190.250.174";
    protected static String client_IP;

    private static int initialize(Socket socket) throws IOException {
	System.out.println("hello");
	OutputStream os = socket.getOutputStream();
	BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter pw = new PrintWriter(os, true);

	System.out.println("server: " + br.readLine());
	pw.println("137.190.250.174");

	socket.close();
	return 1;

    }

    public static void main(String[] args) throws IOException {
	int init = 0;

	try {
	    InetAddress iAddress = InetAddress.getLocalHost();
	    client_IP = iAddress.getHostAddress();
	    System.out.println("Current IP address : " + client_IP);
	} catch (UnknownHostException e) {
	}

	try {
	    System.out.println("hello1");
	    Socket socket = new Socket("137.190.250.174", 8989);
	    System.out.println("hello3");
	    init = initialize(socket);

	} catch (SocketException e) {
	    System.out.println("Error: Unable to connect to server port ");
	}

	if (init == 0) {
	    System.out.println("error: Failed to initialize ");
	    System.exit(0);

	}

    }

    @Override
    public void run() {

    }
}
