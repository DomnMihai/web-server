package jenkins.workshops.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

public class QueryHelperTest {

    @Test
    public void testGetQueryParameters() {
        final String query = "zona=alfa&tag=ing&ups=biga";
        final Map<String, String> queryMap = QueryHelper.getQueryParameters(query);

        assertEquals(3, queryMap.size(), "Query map should have 3 elements");
        assertEquals("alfa", queryMap.get("zona"), "Wrong value for 'zona' key");
        assertEquals("ing", queryMap.get("tag"), "Wrong value for 'tag' key");
        assertEquals("biga", queryMap.get("ups"), "Wrong value for 'ups' key");
    }

    @Test
    public void testGetQueryParametersForNullQuery() {
        final Map<String, String> queryMap = QueryHelper.getQueryParameters(null);
        assertEquals(0, queryMap.size(), "Query map should have 0 elements");
    }
}
