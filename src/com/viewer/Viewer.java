package com.viewer;

import java.awt.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Viewer extends JFrame {
	JScrollPane userScrollPane;
	JList<String> userList;

	JLabel usernameLabel;
	JLabel currentUserCount;

	JTextArea inputTextArea;
	JTextArea outputTextArea;
	JScrollPane inputPane;
	JScrollPane outputPane;
	JButton sendButton;
	JButton clearButton;

	public Viewer() {
		super("chat room");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
		setSize(900, 600);
		setLocation(200, 200);
		setVisible(true);
	}

	public void  init() {
		userList = new JList<>();
		userScrollPane = new JScrollPane(userList);
		userScrollPane.setMaximumSize(new Dimension(100, 100000));
		userList.setFont(new Font("Arial", Font.PLAIN, 15));

		usernameLabel = new JLabel();
		currentUserCount = new JLabel("disconnect");

		usernameLabel.setFont(new Font("雅黑", Font.BOLD, 20));
		currentUserCount.setFont(new Font("雅黑", Font.BOLD, 20));

		inputTextArea = new JTextArea(5, 10);
		outputTextArea = new JTextArea(10, 10);
		outputTextArea.setEditable(false);
		inputPane = new JScrollPane(inputTextArea);
		outputPane = new JScrollPane(outputTextArea);

		inputTextArea.setFont(new Font("雅黑", Font.PLAIN, 20));
		outputTextArea.setFont(new Font("雅黑", Font.PLAIN, 20));

		sendButton = new JButton("send");
		clearButton = new JButton("clear");

		final var userListBox = Box.createHorizontalBox();
		final var labelBox = Box.createHorizontalBox();
		final var hBox = Box.createVerticalBox();
		final var vBox = Box.createHorizontalBox();

		userListBox.add(userScrollPane);
		userListBox.add(hBox);

		labelBox.add(currentUserCount);
		labelBox.add(Box.createHorizontalGlue());
		labelBox.add(usernameLabel);

		hBox.add(labelBox);
		hBox.add(Box.createHorizontalGlue());
		hBox.add(outputPane);
		hBox.add(Box.createVerticalStrut(1));
		hBox.add(vBox);
		vBox.add(clearButton);
		vBox.add(Box.createHorizontalGlue());
		vBox.add(sendButton);
		hBox.add(Box.createVerticalStrut(1));
		hBox.add(inputPane);

		getContentPane().add(userListBox);
	}

	public JScrollPane getUserScrollPane() {
		return userScrollPane;
	}

	public void setUserScrollPane(final JScrollPane userScrollPane) {
		this.userScrollPane = userScrollPane;
	}

	public JList<String> getUserList() {
		return userList;
	}

	public void setUserList(final JList<String> userList) {
		this.userList = userList;
	}

	public JTextArea getInputTextArea() {
		return inputTextArea;
	}

	public void setInputTextArea(final JTextArea inputTextArea) {
		this.inputTextArea = inputTextArea;
	}

	public JTextArea getOutputTextArea() {
		return outputTextArea;
	}

	public void setOutputTextArea(final JTextArea outputTextArea) {
		this.outputTextArea = outputTextArea;
	}

	public JLabel getLabel() {
		return usernameLabel;
	}

	public void setLabel(final JLabel label) {
		this.usernameLabel = label;
	}

	public void setsendButton(final JButton button) {
		this.sendButton = button;
	}

	public JScrollPane getInputPane() {
		return inputPane;
	}

	public void setInputPane(final JScrollPane inputPane) {
		this.inputPane = inputPane;
	}

	public JScrollPane getOutputPane() {
		return outputPane;
	}

	public void setOutputPane(final JScrollPane outputPane) {
		this.outputPane = outputPane;
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public void setSendButton(final JButton sendButton) {
		this.sendButton = sendButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	public void setUsernameLabel(final JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	public JLabel getCurrentUserCount() {
		return currentUserCount;
	}

	public void setCurrentUserCount(final JLabel currentUserCount) {
		this.currentUserCount = currentUserCount;
	}

	public void setClearButton(final JButton clearButton) {
		this.clearButton = clearButton;
	}

	public JButton getsendButton() {
		return sendButton;
	}
}
