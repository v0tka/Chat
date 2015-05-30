package chatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Servidor {
    private final ServerSocket sc;
    private Socket so;
    private final int PUERTO = 5000;

    public Servidor() throws IOException {
        this.sc = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado, esperando clientes");

        while(true) {
            this.so = this.sc.accept();
            System.out.println("Cliente conectado.");
            ((ServidorHilo) new ServidorHilo(so)).start();
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
    }
}
