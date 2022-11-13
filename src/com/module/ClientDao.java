package com.module;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientDao {
	private Socket client;
	OutputStream outToServer;
	InputStream inFromServer;
	DataOutputStream out;
	DataInputStream in;

	public ClientDao(String address,int port) throws IOException {
		System.out.println("connect server ...");
		client = new Socket(address, port);
		outToServer = client.getOutputStream();
		inFromServer = client.getInputStream();
		out = new DataOutputStream(outToServer);
		in = new DataInputStream(inFromServer);
	}

	public void sendMsg(String s) throws IOException {
		s = InetAddress.getLocalHost().getHostName() + ": "+ s ;
		System.out.print("client send : " + s );
		out.writeUTF(s);
	}

	public String recvMsg() throws IOException {
		String recv = in.readUTF();
		System.out.println("client receive : " + recv + '\n');
		return recv;
	}
}
