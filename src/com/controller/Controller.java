package com.controller;

import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;

import com.module.ClientDao;
import com.module.ServerPropertiesDao;
import com.viewer.Viewer;

public class Controller {
	private ClientDao clientDao;
	private final Viewer viewer;
	private ServerPropertiesDao serverPropertiesDao;

	public Controller() {
		viewer = new Viewer();
		viewer.init();
		try {
			serverPropertiesDao = new ServerPropertiesDao();
			// connect server
			clientDao = new ClientDao(serverPropertiesDao.getProperty("address"),
					Integer.parseInt(serverPropertiesDao.getProperty("port")));
			// register
			clientDao.registerUser(InetAddress.getLocalHost().getHostName(),
					serverPropertiesDao.getProperty("address"));

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void initGui() {
		javax.swing.SwingUtilities.invokeLater(() -> {
			// label
			try {
				viewer.getLabel().setText(InetAddress.getLocalHost().getHostName());
			} catch (final Exception e) {
				e.printStackTrace();
			}
			// send button
			viewer.getsendButton().addActionListener(e -> {
				try {
					final String input = viewer.getInputTextArea().getText();
					clientDao.sendMsg(input);
				} catch (final IOException e1) {
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
				public void keyPressed(final KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							final String input = viewer.getInputTextArea().getText();
							clientDao.sendMsg(input);
						} catch (final IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			// recvive Msg thread
			new Thread(() -> {
				while (true) {
					try {
						final int flag = clientDao.getFlag();
						if (flag == MsgEnum.message.ordinal()) {
							viewer.getOutputTextArea().append(clientDao.recvMsg() + "\n");
							viewer.getInputTextArea().setText("");
							viewer.getOutputTextArea()
									.setCaretPosition(viewer.getOutputTextArea().getDocument().getLength());
						} else if (flag == MsgEnum.userlist.ordinal()) {
							viewer.getUserList().setListData(clientDao.recvUserList());
							viewer.getCurrentUserCount().setText(
									"Current User Count: "
											+ Integer.toString(viewer.getUserList().getModel().getSize()));
						}
					} catch (IOException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}).start();
		});
	}
}
