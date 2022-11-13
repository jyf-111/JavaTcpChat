package server.serverDao;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class TcpServerDao {
	private int port;
	private ServerSocket serverSocket;
	Vector<DataOutputStream> outPutVector;

	public TcpServerDao(int serverPort) throws IOException {
		port = serverPort;
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(0);

		outPutVector = new Vector<>();
		process();
	}

	public void process() {
		while (true) {
			System.out.println("local port is:" + serverSocket.getLocalPort() + "...");
			try {
				//accept
				Socket socket = serverSocket.accept();
				System.out.println("connect success");
				// register client outputstream
				var outStream = new DataOutputStream(socket.getOutputStream());
				outPutVector.add(outStream);
				//new thread
				new Thread(new ServerThread(socket,outStream, outPutVector)).start();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
