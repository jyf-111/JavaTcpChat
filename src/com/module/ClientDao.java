package com.module;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class ClientDao {
	private final Socket client;
	private final DataOutputStream out;
	private final DataInputStream in;
	private final ObjectInputStream ois;

	public ClientDao(final String address, final int port) throws IOException {
		System.out.println("connect server ...");
		client = new Socket(address, port);
		out = new DataOutputStream(client.getOutputStream());
		in = new DataInputStream(client.getInputStream());
		ois = new ObjectInputStream(client.getInputStream());
	}

	public void sendMsg(String s) throws IOException {
		s = InetAddress.getLocalHost().getHostName() + ": " + s;
		System.out.print("client send : " + s);
		out.writeUTF(s);
	}

	public void registerUser(final String hostname, final String ip) throws IOException {
		out.writeUTF(hostname);
		out.writeUTF(ip);
	}

	public int getFlag() throws IOException {
		return in.readInt();
	}

	public String recvMsg() throws IOException {
		final String recv = in.readUTF();
		System.out.println("client receive : " + recv + '\n');
		return recv;
	}

	@SuppressWarnings("unchecked")
	public Vector<String> recvUserList() throws IOException, ClassNotFoundException {
		return (Vector<String>) ois.readObject();
	}
}
