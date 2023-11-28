package eapli;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class LoginHandler extends HttpServlet {
    private final UserRepository userRepository;

    public LoginHandler() {
        userRepository = new UserRepository() {
            @Override
            public <S extends SystemUser> S save(S entity) {
                return null;
            }

            @Override
            public Iterable<SystemUser> findAll() {
                return null;
            }

            @Override
            public Optional<SystemUser> ofIdentity(Username id) {
                return Optional.empty();
            }

            @Override
            public void delete(SystemUser entity) {

            }

            @Override
            public void deleteOfIdentity(Username entityId) {

            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public Iterable<SystemUser> findByActive(boolean active) {
                return null;
            }
        };
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the request body
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        String requestBody = br.readLine();

        // Parse the request body to get the username and password
        String[] parts = requestBody.split("&");
        String username = parts[0].split("=")[1];
        String password = parts[1].split("=")[1];

        // Validate the user credentials
        boolean isValidUser = validateUser(username, password);

        // Send the response
        if (isValidUser) {
            response.sendRedirect("/SubMenu.html");
        } else {
            String errorPage = getResourceFileAsString("ClientHtml.html");
            errorPage = errorPage.replace("{{error}}", "Invalid credentials");
            response.getWriter().write(errorPage);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginPage = getResourceFileAsString("ClientHtml.html");
        response.getWriter().write(loginPage);
    }

    private String getResourceFileAsString(String fileName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + fileName);
            }
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
        }
    }

    private boolean validateUser(String username, String password) {
        Optional<SystemUser> userOptional = userRepository.ofIdentity(Username.valueOf(username));
        if (userOptional.isPresent()) {
            SystemUser user = userOptional.get();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return user.passwordMatches(password, encoder);
        }
        return false;
    }
}