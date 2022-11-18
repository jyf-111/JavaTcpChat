package com.serverDao;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class TcpServerDao {
	private int port;
	private ServerSocket serverSocket;
	Vector<User> userVector;

	public TcpServerDao(int serverPort) throws IOException {
		port = serverPort;
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(0);

		userVector = new Vector<>();
	}

	public void process() {
		while (true) {
			System.out.println("local port is:" + serverSocket.getLocalPort() + "...");
			try {
				// accept
				Socket socket = serverSocket.accept();
				System.out.println("connect success");
				// new thread
				new Thread(new ServerThread(socket, userVector)).start();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
