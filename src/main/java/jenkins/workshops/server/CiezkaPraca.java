package jenkins.workshops.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CiezkaPraca implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // Work hard
        final Random random = new Random();
        final int sleepTime = random.nextInt(100, 3000);
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Response
        final StringBuilder responseBuilder = new StringBuilder(300);
        responseBuilder.append("<h1>Ciezka praca</h1>\n");
        responseBuilder.append("Sleep: ").append(sleepTime).append("<br>\n");

        responseBuilder.append(Navigation.getNavigationLinks());

        httpExchange.sendResponseHeaders(200, responseBuilder.length());
        final OutputStream os = httpExchange.getResponseBody();
        os.write(responseBuilder.toString().getBytes());
        os.close();
    }
}
