package server.serverDao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class ServerThread implements Runnable, TcpBroadcastImpl {
	Socket socket;
	DataOutputStream outStream;
	Vector<DataOutputStream> outPutVector;

	public ServerThread(Socket socket, DataOutputStream outStream, Vector<DataOutputStream> outPutVector) {
		this.socket = socket;
		this.outPutVector = outPutVector;
		this.outStream = outStream;
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			while (true) {
				String recv = in.readUTF();
				System.out.println(recv);
				//broadcast
				broadcast(recv);
			}
		} catch (IOException e) {
			//client disconnect
				outPutVector.remove(outStream);
		}
	}

	@Override
	public void broadcast(String s) throws IOException {
		for (var output : outPutVector) {
			output.writeUTF(s);
		}
	}
}
