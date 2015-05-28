package com.zzy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class LogClient extends Thread {

	static Logger logger = Logger.getLogger(LogClient.class.getName());
	private Socket client;

	public LogClient(Socket c) {
		this.client = c;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream());
			while (true) {
				String str = in.readLine();
				logger.info(str);

				System.out.println(str);
				if (str.equals("end")) {
					break;
				}
			}
			client.close();
		} catch (IOException ex) {

		} finally {

		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket server = new ServerSocket(5001);
		System.out.println("lgogoo");
		while (true) {
			// transfer location change Single User or Multi User
			LogClient mu = new LogClient(server.accept());
			mu.start();
		}

	}

}