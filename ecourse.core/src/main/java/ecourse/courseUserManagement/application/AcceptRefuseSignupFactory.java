/*
 * Copyright (c) 2013-2023 the original author or authors.
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
package ecourse.courseUserManagement.application;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import eapli.framework.util.Utility;
import ecourse.Application;
import ecourse.infrastructure.persistence.PersistenceContext;

/**
 * A simple factory to obtain the desired implementation of the
 * {@link AcceptRefuseSignupRequestController}.
 *
 * @author Paulo Gandra de Sousa 16/05/2019
 */
@Utility
public final class AcceptRefuseSignupFactory {
    private AcceptRefuseSignupFactory() {
        // ensure utility
    }

    public static AcceptRefuseSignupRequestController build() {
        // for pedagogical purposes: play around with the 2 approaches
        if (Application.settings().useEventfulControllers()) {
            // dependency injection - when constructing the object one must inject the dependencies
            // to infrastructure objects it needs. this should be handled by a DI/IoC container like
            // Spring Framework
            return new AcceptRefuseSignupRequestControllerEventfulImpl(
                    PersistenceContext.repositories().signupRequests(), AuthzRegistry.authorizationService(),
                    InProcessPubSub.publisher());
        } else {
            return new AcceptRefuseSignupRequestControllerTxImpl();
        }
    }
}
