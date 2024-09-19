package jenkins.workshops.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jenkins.workshops.Main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class RootHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final StringBuilder responseBuilder = new StringBuilder(300);
        responseBuilder.append("<h1>Server running</h1>\n");
        responseBuilder.append("Server is running for ").append(getRunningSeconds()).append(" seconds<br>\n");
        responseBuilder.append(Navigation.getNavigationLinks());

        httpExchange.sendResponseHeaders(200, responseBuilder.length());
        final OutputStream os = httpExchange.getResponseBody();
        os.write(responseBuilder.toString().getBytes());
        os.close();
    }

    private long getRunningSeconds() {
        final Date dateNow = new Date();
        final long millisStart = Main.startDate.getTime();
        final long millisNow = dateNow.getTime();
        final long millisDiff = millisNow - millisStart;
        return millisDiff / 1000;
    }
}
