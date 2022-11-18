package com.controller;

import java.io.IOException;

import com.serverDao.TcpServerDao;

public class TcpController {
	public TcpController() {
		try {
			new TcpServerDao(7788).process();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
