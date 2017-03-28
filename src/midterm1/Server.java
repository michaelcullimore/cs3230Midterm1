package midterm1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    protected static String server_IP;

    public static void main(String[] args) throws IOException {

	int server_Port = 8989;

	try {
	    InetAddress IPAddress = InetAddress.getByName("52.35.72.251");
	    server_IP = IPAddress.getHostAddress();
	    System.out.println("Server IP address : " + server_IP);
	} catch (UnknownHostException e) {
	}

	ServerSocket serverSocket = new ServerSocket(server_Port);

	while (true) {
	    Socket socket = serverSocket.accept();

	    OutputStream os = socket.getOutputStream();
	    PrintWriter pw = new PrintWriter(os, true);
	    InputStreamReader isr = new InputStreamReader(socket.getInputStream());
	    pw.println("Connection confirmed...");
	    BufferedReader br = new BufferedReader(isr);
	    String str = br.readLine();

	    pw.println("Your IP address is " + str + "...");

	    pw.close();
	    serverSocket.close();
	}
    }
}
