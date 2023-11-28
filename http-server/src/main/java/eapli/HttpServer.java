package eapli;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.jws.WebService;
import java.net.InetSocketAddress;

@WebService
public class HttpServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(new InetSocketAddress(8080));
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        ServletContextHandler loginHandler = new ServletContextHandler();
        loginHandler.setContextPath("/login");
        loginHandler.addServlet(new ServletHolder(new LoginHandler()), "/");
        contexts.addHandler(loginHandler);

        ServletContextHandler clientHandler = new ServletContextHandler();
        clientHandler.setContextPath("/");
        clientHandler.setResourceBase("src/main/resources");
        clientHandler.addServlet(new ServletHolder(new DefaultServlet()), "/");
        contexts.addHandler(clientHandler);

        server.start();
        server.join();
    }
}