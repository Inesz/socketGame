package com.game.socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final Integer PORT_NUMBER = 6666;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.start(PORT_NUMBER);
    }

    private void start(Integer portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("server start");

            while (true) {
                Socket socket1 = serverSocket.accept();
                Socket socket2 = serverSocket.accept();

                new GameServer.ClientHandler(socket1, socket2).start();
                new GameServer.ClientHandler(socket2, socket1).start();

                PrintWriter outOther = new PrintWriter(socket2.getOutputStream(), true);
                PrintWriter outInitiator = new PrintWriter(socket1.getOutputStream(), true);

                outInitiator.println("initiator");
                outOther.println("other");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private Socket partnerSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket, Socket socket2) {
            this.clientSocket = socket;
            this.partnerSocket = socket2;
        }

        public void run() {
            System.out.println("run process pid" + ProcessHandle.current().pid() + " thread" + currentThread().getId());

            try {
                out = new PrintWriter(partnerSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                Integer i = 0;
                while (true) {
                    inputLine = in.readLine();
                    System.out.println("thread " + currentThread().getId() + "get:" + inputLine + " msgNo:" + ++i);
                    out.println(inputLine);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
