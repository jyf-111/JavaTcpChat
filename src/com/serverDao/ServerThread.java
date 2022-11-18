package com.serverDao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ServerThread implements Runnable, TcpBroadcastImpl {
	private final Socket socket;
	private User user;
	private final Vector<User> userVector;

	public ServerThread(final Socket socket, final Vector<User> userVector) {
		this.socket = socket;
		this.userVector = userVector;
	}

	@Override
	public void run() {
		try {
			// registerUser
			registerUser();
			while (true) {
				// recv
				final String recv = recvMsg();
				// broadcast
				broadcast(recv);
			}
		} catch (final IOException e) {
			// client disconnect
			try {
				user.getIn().close();
				user.getDos().close();
				user.getOos().close();
				socket.close();
				// disconnect broadcast
				System.out.println("client disconnect");
				userVector.remove(user);
				broadcast();
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
			// remove user
		}
	}

	@Override
	public void broadcast(final String s) throws IOException {
		for (final var user : userVector) {
			user.getDos().writeInt(MsgEnum.message.ordinal());
			user.getDos().writeUTF(s);
		}
	}

	@Override
	public void broadcast() throws IOException {
		final Vector<String> userInfoVector = new Vector<>();
		for (final var user2 : userVector) {
			userInfoVector.add(user2.getHostname() + "(" + user2.getIp() + ")");
		}
		for (final var user : userVector) {
			user.getDos().writeInt(MsgEnum.userlist.ordinal());
			user.getOos().writeObject(userInfoVector);
		}
	}

	private String recvMsg() throws IOException {
		String msg = null;
		msg = user.getIn().readUTF();
		System.out.println(msg);
		return msg;
	}

	private void registerUser() throws IOException {
		user = new User();
		user.setIn(new DataInputStream(socket.getInputStream()));
		user.setDos(new DataOutputStream(socket.getOutputStream()));
		user.setOos(new ObjectOutputStream(socket.getOutputStream()));
		final var hostname = user.getIn().readUTF();
		final var ip = user.getIn().readUTF();
		user.setHostname(hostname);
		user.setIp(ip);
		userVector.add(user);

		System.out.println("register: " + user);
		// broadcast user list
		broadcast();
	}
}
