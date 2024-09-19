package jenkins.workshops;

import com.sun.net.httpserver.HttpServer;
import jenkins.workshops.server.CiezkaPraca;
import jenkins.workshops.server.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

public class Main {

    public static Date startDate;

    public static void main(String[] args) {
        final int port = 14724;
        final InetSocketAddress inetSocketAddress = new InetSocketAddress(port);

        try {
            final HttpServer server = HttpServer.create(inetSocketAddress, 0);
            server.createContext("/", new RootHandler());
            server.createContext("/ciezka-praca", new CiezkaPraca());
            server.start();
            System.out.println("I am listening at 127.0.0.1:" + port);

            Main.startDate = new Date();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
