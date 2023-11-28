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

import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.validations.Preconditions;
import ecourse.studentManagement.domain.SignupRequest;
import ecourse.studentManagement.domain.events.SignupAcceptedEvent;
import ecourse.studentManagement.repositories.SignupRequestRepository;
import ecourse.userManagement.domain.BaseRoles;
import lombok.RequiredArgsConstructor;

/**
 * The controller for the use case "Accept or refuse signup request".
 * <p>
 * This implementation makes use of domain events to (1) follow the rule that
 * one controller should only modify one aggregate, and (2) notify other parts
 * of the system to react accordingly. For an alternative transactional approach
 * see {@link AcceptRefuseSignupRequestControllerTxImpl}.
 * </p>
 * <p>
 * In order to follow an eventful approach, first it is necessary to register
 * the event handlers ("watch dogs").
 * </p>
 * <p>
 * <img src="seq-init.svg">
 * </p>
 * <p>
 * Afterwards, during the use case execution, the actual three steps are divided
 * and executed in separate transactions (and eventually threads) according to
 * the publishing of events. The first step is "Accept the signup".
 * </p>
 * <p>
 * <img src="seq-accept-sign-up.svg">
 * </p>
 * <p>
 * The second step is "Register System User".
 * </p>
 * <p>
 * <img src="seq-register-system-user.svg">
 * </p>
 * <p>
 * The third step is "Create Course User".
 * </p>
 * <p>
 * <img src="seq-create-course-user.svg">
 * </p>
 * <p>
 * All the steps are shown in the following sequence diagram.
 * </p>
 * <p>
 * <img src="seq-use-case.svg">
 * </p>
 * <!--
 *
 * @author Paulo Gandra de Sousa
 * @startuml seq-init.svg title Subscribe event handlers participant App
 * participant dispatcher participant SignupAcceptedWatchDog create
 * SignupAcceptedWatchDog App -> SignupAcceptedWatchDog : new App ->
 * dispatcher: subscribe(signupWatchDog) create
 * NewUserRegisteredFromSignupWatchDog App ->
 * NewUserRegisteredFromSignupWatchDog:new App -> dispatcher:
 * subscribe(newUserWatchDog)
 * @enduml --> <!--
 * @startuml seq-use-case.svg title Full Use case execution participant
 * dispatcher participant UI participant
 * AcceptRefuseSignupRequestController participant SignupRepository
 * participant SignupAcceptedEvent participant SignupAcceptedWatchDog
 * participant AddUserOnSignupAcceptedController participant
 * UserRepository participant NewUserRegisteredFromSignupEvent
 * participant NewUserRegisteredFromSignupWatchDog participant
 * AddCourseUserOnSignupAcceptedController participant
 * CourseUserRepository group Accept the signup UI ->
 * AcceptRefuseSignupRequestController:acceptSignupRequest activate
 * AcceptRefuseSignupRequestController
 * AcceptRefuseSignupRequestController -> SignupRepository:save create
 * SignupAcceptedEvent AcceptRefuseSignupRequestController ->
 * "SignupAcceptedEvent":new AcceptRefuseSignupRequestController ->
 * dispatcher:publish deactivate AcceptRefuseSignupRequestController
 * end group Register system user dispatcher ->
 * SignupAcceptedWatchDog: onEvent activate SignupAcceptedWatchDog
 * SignupAcceptedWatchDog -> AddUserOnSignupAcceptedController:addUser
 * activate AddUserOnSignupAcceptedController
 * AddUserOnSignupAcceptedController -> UserRepository:save create
 * NewUserRegisteredFromSignupEvent AddUserOnSignupAcceptedController
 * -> NewUserRegisteredFromSignupEvent:new
 * AddUserOnSignupAcceptedController -> dispatcher:publish deactivate
 * AddUserOnSignupAcceptedController deactivate SignupAcceptedWatchDog
 * end group Create course user dispatcher ->
 * NewUserRegisteredFromSignupWatchDog:onEvent activate
 * NewUserRegisteredFromSignupWatchDog
 * NewUserRegisteredFromSignupWatchDog ->
 * AddCourseUserOnSignupAcceptedController:addCourseUser
 * activate AddCourseUserOnSignupAcceptedController
 * AddCourseUserOnSignupAcceptedController ->
 * CourseUserRepository:save deactivate
 * AddCourseUserOnSignupAcceptedController deactivate
 * NewUserRegisteredFromSignupWatchDog end
 * @enduml --> <!--
 * @startuml seq-accept-sign-up.svg
 * title Accept the Signup
 * participant dispatcher
 * participant UI
 * participant AcceptRefuseSignupRequestController
 * participant SignupRepository
 * participant SignupAcceptedEvent
 * group Accept the signup
 * UI -> AcceptRefuseSignupRequestController:acceptSignupRequest
 * activate AcceptRefuseSignupRequestController
 * AcceptRefuseSignupRequestController -> SignupRepository:save
 * create SignupAcceptedEvent
 * AcceptRefuseSignupRequestController -> "SignupAcceptedEvent":new
 * AcceptRefuseSignupRequestController -> dispatcher:publish
 * deactivate AcceptRefuseSignupRequestController
 * end
 * @enduml --> <!--
 * @startuml seq-register-system-user.svg
 * title Register System User
 * participant dispatcher
 * participant SignupAcceptedWatchDog
 * participant AddUserOnSignupAcceptedController
 * participant UserRepository
 * participant NewUserRegisteredFromSignupEvent
 * group Register system user
 * dispatcher -> SignupAcceptedWatchDog: onEvent
 * activate SignupAcceptedWatchDog
 * SignupAcceptedWatchDog -> AddUserOnSignupAcceptedController:addUser
 * activate AddUserOnSignupAcceptedController
 * AddUserOnSignupAcceptedController -> UserRepository:save
 * create NewUserRegisteredFromSignupEvent
 * AddUserOnSignupAcceptedController -> NewUserRegisteredFromSignupEvent:new
 * AddUserOnSignupAcceptedController -> dispatcher:publish
 * deactivate AddUserOnSignupAcceptedController
 * deactivate SignupAcceptedWatchDog
 * end
 * @enduml --> <!--
 * @startuml seq-create-course-user.svg
 * title Create Course User
 * participant dispatcher
 * participant NewUserRegisteredFromSignupWatchDog
 * participant AddCourseUserOnSignupAcceptedController
 * participant CourseUserRepository
 * group Create Course User
 * dispatcher -> NewUserRegisteredFromSignupWatchDog:onEvent
 * activate NewUserRegisteredFromSignupWatchDog
 * NewUserRegisteredFromSignupWatchDog ->
 * AddCourseUserOnSignupAcceptedController:addCourseUser
 * activate AddCourseUserOnSignupAcceptedController
 * AddCourseUserOnSignupAcceptedController -> CourseUserRepository:save
 * deactivate AddCourseUserOnSignupAcceptedController
 * deactivate NewUserRegisteredFromSignupWatchDog
 * end
 * @enduml -->
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerEventfulImpl implements AcceptRefuseSignupRequestController {

    private final SignupRequestRepository signupRequestsRepository;
    private final AuthorizationService authorizationService;
    private final EventPublisher dispatcher;

    /**
     * Constructor.
     * <p>
     * We are using constructor dependency injection to facilitate testing of this controller.
     * <p>
     * This boilerplate code could be avoided by leveraging Lombok's {@link RequiredArgsConstructor}
     *
     * @param signupRequestsRepository
     * @param authorizationService
     * @param dispatcher
     */
    public AcceptRefuseSignupRequestControllerEventfulImpl(final SignupRequestRepository signupRequestsRepository,
                                                           final AuthorizationService authorizationService, final EventPublisher dispatcher) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.signupRequestsRepository = signupRequestsRepository;
        this.authorizationService = authorizationService;
        this.dispatcher = dispatcher;
    }

    @Override
    @SuppressWarnings("squid:S1226")
    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER, BaseRoles.MANAGER);

        Preconditions.nonNull(theSignupRequest);

        theSignupRequest = markSignupRequestAsAccepted(theSignupRequest);
        return theSignupRequest;
    }

    /**
     * Modify Signup Request to accepted.
     *
     * @param theSignupRequest
     * @return
     * @throws ConcurrencyException
     * @throws IntegrityViolationException
     */
    @SuppressWarnings("squid:S1226")
    private SignupRequest markSignupRequestAsAccepted(SignupRequest theSignupRequest) {
        // do just what is needed in the scope of this use case
        theSignupRequest.accept();
        theSignupRequest = signupRequestsRepository.save(theSignupRequest);

        // notify interested parties (if any)
        final DomainEvent event = new SignupAcceptedEvent(theSignupRequest);
        dispatcher.publish(event);

        return theSignupRequest;
    }

    @Override
    public SignupRequest refuseSignupRequest(final SignupRequest theSignupRequest) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER, BaseRoles.MANAGER);

        Preconditions.nonNull(theSignupRequest);

        theSignupRequest.refuse();
        return signupRequestsRepository.save(theSignupRequest);
    }

    /**
     * @return
     */
    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return signupRequestsRepository.pendingSignupRequests();
    }
}
