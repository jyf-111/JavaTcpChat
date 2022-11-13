package com.viewer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Viewer extends JFrame {
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
		setSize(800, 500);
		setLocation(200, 200);
		init();
		setVisible(true);
	}

	private void init() {
		inputTextArea = new JTextArea(5, 10);
		outputTextArea = new JTextArea(10, 10);
		outputTextArea.setEditable(false);
		inputPane = new JScrollPane(inputTextArea);
		outputPane = new JScrollPane(outputTextArea);

		inputTextArea.setFont(new Font("雅黑",Font.PLAIN,20));
		outputTextArea.setFont(new Font("雅黑",Font.PLAIN,20));
		

		sendButton = new JButton("send");
		clearButton = new JButton("clear");

		var hbox = Box.createVerticalBox();
		var vbox = Box.createHorizontalBox();
		hbox.add(outputPane);
		hbox.add(Box.createVerticalStrut(1));
		hbox.add(vbox);
		vbox.add(clearButton);
		vbox.add(Box.createHorizontalGlue());
		vbox.add(sendButton);
		hbox.add(Box.createVerticalStrut(1));
		hbox.add(inputPane);

		getContentPane().add(hbox);
	}

	public JTextArea getInputTextArea() {
		return inputTextArea;
	}

	public void setInputTextArea(JTextArea inputTextArea) {
		this.inputTextArea = inputTextArea;
	}

	public JTextArea getOutputTextArea() {
		return outputTextArea;
	}

	public void setOutputTextArea(JTextArea outputTextArea) {
		this.outputTextArea = outputTextArea;
	}

	public void setsendButton(JButton button) {
		this.sendButton = button;
	}

	public JScrollPane getInputPane() {
		return inputPane;
	}

	public void setInputPane(JScrollPane inputPane) {
		this.inputPane = inputPane;
	}

	public JScrollPane getOutputPane() {
		return outputPane;
	}

	public void setOutputPane(JScrollPane outputPane) {
		this.outputPane = outputPane;
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public void setSendButton(JButton sendButton) {
		this.sendButton = sendButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	public JButton getsendButton() {
		return sendButton;
	}
}
