package web;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.JSONObject;

/**
 * Useful stuff for parsing Web requests/datatypes.
 */
public abstract class WebUtils {

    public static JSONObject getHttpRequestBodyAsJson(
        HttpServletRequest request
    ) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line).append("\n");
            }
        }
        return new JSONObject(requestBody.toString());
    }
}
