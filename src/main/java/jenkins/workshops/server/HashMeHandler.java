package jenkins.workshops.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jenkins.workshops.hash.Hasher;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.Tag;
import software.amazon.awssdk.services.s3.model.Tagging;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMeHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // Get parameters
        final Map<String, String> params = QueryHelper.getQueryParameters(httpExchange.getRequestURI().getQuery());
        final String text = params.get("text");
        final String method = params.get("method");
        final String key = params.get("key");

        // Calculate hash
        String hash;
        int responseCode = 200;
        try {
            hash = Hasher.hash(text, method);
        } catch (NoSuchAlgorithmException ex) {
            hash = "<span style='color: red'>WRONG METHOD</span>";
            responseCode = 500;
            ex.printStackTrace();
        }

        // Response
        final StringBuilder responseBuilder = new StringBuilder(400);
        responseBuilder.append("<h1>Hash</h1>\n");
        responseBuilder.append("Text: ").append(text).append("<br>\n");
        responseBuilder.append("Method: ").append(method).append("<br>\n");
        responseBuilder.append("Hash: ").append(hash).append("<br>\n");

        responseBuilder.append(Navigation.getNavigationLinks());

        httpExchange.sendResponseHeaders(responseCode, responseBuilder.length());
        final OutputStream os = httpExchange.getResponseBody();
        os.write(responseBuilder.toString().getBytes());
        os.close();

        // Upload to S3
        if (key != null) {
            try {
                uploadToS3(text, key, hash);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void uploadToS3(String content, String key, String hash) {
        final S3Client s3Client = S3Client.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(ProfileCredentialsProvider.create("hash-uploader"))
                .build();

        // Set tags
        final Set<Tag> tags = new HashSet<>();
        tags.add(Tag.builder().key("Server_hash").value(hash).build());

        // Upload
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket("workshops-1-hashed-files-448740566997")
                        .key(key)
                        .tagging(Tagging.builder().tagSet(tags).build())
                        .build(),
                RequestBody.fromString(content)
        );

        s3Client.close();
    }
}
