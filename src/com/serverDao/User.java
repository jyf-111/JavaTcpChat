package com.serverDao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
	String hostname;
	String ip;
	DataInputStream in;
	DataOutputStream dos;
	ObjectOutputStream oos;

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(final DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(final DataOutputStream dos) {
		this.dos = dos;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(final ObjectOutputStream oos) {
		this.oos = oos;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(final String hostname) {
		this.hostname = hostname;
	}

	@Override
	public String toString() {
		return hostname + "(" + ip + ")";
	}

}
