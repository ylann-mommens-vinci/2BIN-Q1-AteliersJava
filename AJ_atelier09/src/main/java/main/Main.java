package main;

import server.ProxyServer;

public class Main {
    public static void main(String[] args) {
        ProxyServer monServeur = new ProxyServer();

        monServeur.startServer();
    }
}
