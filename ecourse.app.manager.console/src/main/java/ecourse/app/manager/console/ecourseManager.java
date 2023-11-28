/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ecourse.app.manager.console;


import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import ecourse.app.common.console.BaseApplication;
import ecourse.app.common.console.presentation.authz.LoginUI;
import ecourse.app.manager.console.presentation.MainMenu;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.studentManagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import ecourse.studentManagement.domain.events.NewUserRegisteredFromSignupEvent;
import ecourse.studentManagement.domain.events.SignupAcceptedEvent;
import ecourse.userManagement.application.eventhandlers.SignupAcceptedWatchDog;
import ecourse.userManagement.domain.BasePasswordPolicy;
import ecourse.userManagement.domain.BaseRoles;


public final class ecourseManager extends BaseApplication {

    /**
     * avoid instantiation of this class.
     */
    private ecourseManager() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
                new PlainTextEncoder());

        new ecourseManager().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        // login and go to main menu
        // Only Managers can LOG IN
        if (new LoginUI(BaseRoles.MANAGER).show()) {
            // go to main menu
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }
    }

    @Override
    protected String appTitle() {
        return "Manager";
    }

    @Override
    protected String appGoodbye() {
        return "Manager";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }
}
