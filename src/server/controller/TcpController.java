package server.controller;

import java.io.IOException;

import server.serverDao.TcpServerDao;

public class TcpController {
	public TcpController() {
		try {
			new TcpServerDao(7788);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
