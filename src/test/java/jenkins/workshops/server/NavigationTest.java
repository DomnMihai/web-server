package jenkins.workshops.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationTest {

    @Test
    public void testGetNavigationLinks() {
        final StringBuilder responseBuilder = new StringBuilder(139);
        responseBuilder.append("<br>\n");
        responseBuilder.append("You can visit the following pages:<br>\n");
        responseBuilder.append("<ul>\n");
        responseBuilder.append("  <li>").append("<a href='/'>home</a>").append("</li>\n");
        responseBuilder.append("  <li>").append("<a href='/ciezka-praca'>ciezka praca</a>").append("</li>\n");
        responseBuilder.append("</ul>\n");

        final String navigationLinks = Navigation.getNavigationLinks();

        assertEquals(responseBuilder.toString(), navigationLinks, "Navigation links must be equal");
        assertEquals(139, responseBuilder.length(), "Navigation string length must match");
    }
}
