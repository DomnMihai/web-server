package jenkins.workshops.server;

import java.util.HashMap;
import java.util.Map;

public class QueryHelper {

    protected static Map<String, String> getQueryParameters(String query) {
        final Map<String, String> result = new HashMap<>();

        if (query != null) {
            final String[] params = query.split("&");
            for (String param : params) {
                final String[] entry = param.split("=");
                result.put(entry[0], entry[1]);
            }
        }

        return result;
    }
}
