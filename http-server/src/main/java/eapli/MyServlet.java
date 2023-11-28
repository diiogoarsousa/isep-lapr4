package eapli;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebService
public class MyServlet extends HttpServlet {

    @WebServiceRef
    private UserRepository userRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/ClientHtml.html");
        if (resourceAsStream == null) {
            System.err.println("Could not find ClientHtml.html file");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String html = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        System.out.println("HTML contents: " + html);
        resp.getWriter().write(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method called");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isValidUser = validateUser(username, password);

        if (isValidUser) {
            try {
                resp.sendRedirect("/SubMenu.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>Login failed</h1>");
            out.println("</body></html>");
        }
    }

    @WebMethod
    private boolean validateUser(String username, String password) {
        Optional<SystemUser> userOptional = userRepository.ofIdentity(Username.valueOf(username));
        if (userOptional.isPresent()) {
            SystemUser user = userOptional.get();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return user.passwordMatches(password, encoder) && user.isActive();
        }
        return false;
    }
}