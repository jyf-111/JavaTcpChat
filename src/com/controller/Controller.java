package com.controller;

import java.io.IOException;

import com.module.ClientDao;
import com.module.ServerPropertiesDao;
import com.viewer.Viewer;
import java.awt.event.*;

public class Controller {
	private ClientDao clientDao;
	private Viewer viewer;
	private ServerPropertiesDao serverPropertiesDao;

	public Controller() {
		try {
			serverPropertiesDao = new ServerPropertiesDao();
			clientDao = new ClientDao(serverPropertiesDao.getProperty("address"),
				Integer.parseInt(serverPropertiesDao.getProperty("port")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(() -> {
			viewer = new Viewer();
			// send button
			viewer.getsendButton().addActionListener(e -> {
				try {
					String input = viewer.getInputTextArea().getText();
					clientDao.sendMsg(input);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			// clearbutton
			viewer.getClearButton().addActionListener(e -> {
				viewer.getInputTextArea().setText("");
				viewer.getOutputTextArea().setText("");
			});

			// bind enter to send
			viewer.getInputTextArea().addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							String input = viewer.getInputTextArea().getText();
							clientDao.sendMsg(input);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			new Thread(() -> {
				while (true) {
					String recv;
					try {
						// receive and show
						recv = clientDao.recvMsg();
						viewer.getOutputTextArea().append(recv + '\n');
						// clear InputTextArea
						viewer.getInputTextArea().setText("");
						// fix pos last line
						var sBar = viewer.getOutputPane().getVerticalScrollBar();
						var currentPos = viewer.getOutputTextArea().getText().split("\n").length
								* viewer.getOutputTextArea().getFont().getSize();
						sBar.setValue(currentPos);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}).start();
		});
	}
}
