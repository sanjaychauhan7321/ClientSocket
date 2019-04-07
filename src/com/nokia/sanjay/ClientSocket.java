package com.nokia.sanjay;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.nokia.sanjay.exceptions.ServerSocketException;

public class ClientSocket {

	public static void main(String[] args) throws ServerSocketException {

		// Init variable
		Socket socket = null;
		Scanner systemInputStreamScanner = null;
		Scanner socketInputStreamScanner = null;
		String inputValue = null;
		PrintStream printStream = null;
		try {
			try {
				socket = new Socket("127.0.0.1", 1234);

			} catch (IOException e) {

				System.out.println("Could not start the server socket on IP : 127.0.0.1 and port : 1234. Reason : "
						+ e.getMessage());
				throw new ServerSocketException("Error_Custom",
						"Could not start the server socket on IP : 127.0.0.1 and port : 1234. Reason : "
								+ e.getMessage());

			}

			System.out.println("Please enter something !");
			systemInputStreamScanner = new Scanner(System.in);

			try {
				printStream = new PrintStream(socket.getOutputStream());

			} catch (IOException e) {

				System.out.println(
						"An I/O error occured while creating the output stream or socket is not connected. Reason: "
								+ e.getMessage());
				throw new ServerSocketException("Error_Custom",
						"An I/O error occured while creating the output stream or socket is not connected. Reason: "
								+ e.getMessage());

			}

			inputValue = systemInputStreamScanner.nextLine();
			printStream.println(inputValue);
			System.out.println("Data sent to server socket : " + inputValue);

			try {
				socketInputStreamScanner = new Scanner(socket.getInputStream());

			} catch (IOException e) {
				System.out.println(
						"An I/O error occured while creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdownusing shutdownInput(). Reason : "
								+ e.getMessage());
				throw new ServerSocketException("Error_Custom",
						"An I/O error occured while creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdownusing shutdownInput(). Reason : "
								+ e.getMessage());

			}

			// Read from the client side socket input stream
			while (socketInputStreamScanner.hasNext()) {
				System.out.println(socketInputStreamScanner.nextLine());
			}
		}

		finally {
			if (null != socket) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Could not close the socket. Reason : " + e.getMessage());
				}
			}
			if (null != systemInputStreamScanner) {
				systemInputStreamScanner.close();
			}
			if (null != socketInputStreamScanner) {
				socketInputStreamScanner.close();
			}
			if (null != printStream) {
				printStream.close();
			}
		}

	}

}
