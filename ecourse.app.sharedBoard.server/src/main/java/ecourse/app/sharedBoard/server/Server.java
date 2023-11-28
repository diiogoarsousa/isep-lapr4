package ecourse.app.sharedBoard.server;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import ecourse.app.sharedBoard.server.presentation.SBPServerController;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BasePasswordPolicy;


public class Server {
    public static void main(String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
                new PlainTextEncoder());
        Thread thread = new Thread(new SBPServerController());
        thread.start();
    }
}