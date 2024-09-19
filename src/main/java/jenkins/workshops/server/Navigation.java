package jenkins.workshops.server;

public class Navigation {

    protected static String getNavigationLinks() {
        final StringBuilder responseBuilder = new StringBuilder(195);
        responseBuilder.append("<br>\n");
        responseBuilder.append("You can visit the following pages:<br>\n");
        responseBuilder.append("<ul>\n");
        responseBuilder.append("  <li>").append("<a href='/'>home</a>").append("</li>\n");
        responseBuilder.append("  <li>").append("<a href='/ciezka-praca'>ciezka praca</a>").append("</li>\n");
        responseBuilder.append("</ul>\n");
        return responseBuilder.toString();
    }
}
