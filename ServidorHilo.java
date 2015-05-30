package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServidorHilo extends Thread {
    private final Socket so;
    private final PrintWriter out;
    private final BufferedReader in;
    private String mensajeCliente;

    public ServidorHilo(Socket socket) throws IOException {
        this.so = socket;
        this.mensajeCliente = "";

        this.out = new PrintWriter(so.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(so.getInputStream()));
        out.println("ok:!"); // se envia mensaje al cliente todo ok
    }

    @Override
    public void run() {
        try {
            while((mensajeCliente = in.readLine()) != null) {
                this.out.println("ok:!");
                System.out.printf("> %s\n",mensajeCliente);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Cliente Desconectado.");
        try {
            this.so.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
