package com.serverDao;

import java.io.IOException;

public interface TcpBroadcastImpl {
	public void broadcast(String s) throws IOException;
	public void broadcast() throws IOException;
}
