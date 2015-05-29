package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class Cliente {
    private final Socket sc;
    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;
    private final String  HOST = "localhost";
    private final int PUERTO = 5000;
    private String mensajeUsuario;
    private String mensajeServidor;

    public Cliente() throws IOException {
        this.sc = new Socket(HOST, PUERTO);
        this.in = new BufferedReader(new InputStreamReader(this.sc.getInputStream()));
        System.out.println("Conectado con el servidor.");
        this.out = new PrintWriter(this.sc.getOutputStream(), true);
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        this.mensajeServidor = "";
        this.mensajeUsuario = "";
        this.run();
    }

    public void run() throws IOException {
        while((mensajeServidor = in.readLine()) != null && !mensajeUsuario.equals("quit!")) {

            if(!mensajeServidor.equals("ok:!")) {
                System.out.println("> "+mensajeServidor);
            }

            System.out.printf("> ");
            mensajeUsuario = stdIn.readLine();

            if(mensajeUsuario.equals("quit!")) {break;}

            this.out.println(mensajeUsuario);
        }

        System.out.println("Desconectado del servidor.");

        this.close();
    }

    public void close() throws IOException {
        this.sc.close();
        this.in.close();
        this.out.close();
        this.stdIn.close();
    }

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
    }
}
