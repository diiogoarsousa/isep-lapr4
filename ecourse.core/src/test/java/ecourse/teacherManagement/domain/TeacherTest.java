package ecourse.teacherManagement.domain;

import eapli.framework.infrastructure.authz.domain.model.*;
import ecourse.userManagement.domain.BaseRoles;

public class TeacherTest {

    private final String acronym = "ABC";
    private final String anotheracronym = "XYZ";

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.TEACHER);
    }

    private SystemUser getNewDummyUserTwo() {
        return dummyUser("dummy-two", BaseRoles.TEACHER);
    }
}
