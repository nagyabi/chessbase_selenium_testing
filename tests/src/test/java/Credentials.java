
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;
import java.io.InputStream;


public class Credentials {
    private final String email;
    private final String username;
    private final String password;
    private final Map<String, String> cookies;

    public Credentials() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("secrets.json")) {
            if (is == null) {
                throw new RuntimeException("secrets.json not found in test resources.");
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> data = mapper.readValue(is, new TypeReference<Map<String, Object>>() {});
            this.email = (String) data.get("email");
            this.username = (String) data.get("username");
            this.password = (String) data.get("password");

            Object cookieData = data.get("cookies");
            assert (cookieData instanceof Map): "Cookies must be a Map";
            
            this.cookies = (Map<String, String>) cookieData;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to load credentials", e);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}
