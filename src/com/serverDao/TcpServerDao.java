package com.serverDao;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class TcpServerDao {
	serverPropertiesDao properties;
	private final int port;
	private final ServerSocket serverSocket;
	Vector<User> userVector;

	public TcpServerDao() throws IOException {
		properties = new serverPropertiesDao();
		port = Integer.parseInt(properties.getProperty("port"));
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(0);

		userVector = new Vector<>();
	}

	public void process() {
		while (true) {
			System.out.println("local port is:" + serverSocket.getLocalPort() + "...");
			try {
				// accept
				final Socket socket = serverSocket.accept();
				System.out.println("connect success");
				// new thread
				new Thread(new ServerThread(socket, userVector)).start();
			} catch (final SocketTimeoutException s) {
				System.out.println("Socket timed out!");
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

}
