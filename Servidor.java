package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public final class Servidor {
    private final ServerSocket sc;
    private Socket so;
    private PrintWriter out;
    private BufferedReader in;
    private final int PUERTO = 5000;
    private String mensajeCliente;

    public Servidor() throws IOException {
        this.sc = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado, esperando clientes");
        this.mensajeCliente = "";
        this.waitClient();
        System.out.println("Cliente conectado.");
        this.run();
    }

    public void waitClient() throws IOException {
        this.so = this.sc.accept();
        this.out = new PrintWriter(so.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(so.getInputStream()));
        out.println("ok:!");
    }

    public void run() throws IOException {

        while((mensajeCliente = in.readLine()) != null) {
            this.out.println("ok:!");
            System.out.printf("> %s\n",mensajeCliente);
        }

        System.out.println("Cliente Desconectado.");
        System.out.println("Servidor Apagado.!");
        this.close();
    }

    public void close() throws IOException {
        this.sc.close();
        this.so.close();
        this.out.close();
        this.in.close();
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
    }
}
