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
package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingDate;
import ecourse.scheduleMeeting.domain.MeetingDuration;
import ecourse.scheduleMeeting.domain.MeetingParticipant;
import ecourse.scheduleMeeting.repositories.MeetingRepository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, Long, Long>
        implements MeetingRepository {

    public JpaMeetingRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaMeetingRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Meeting> findMeetingsByUser(SystemUser who) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", who);
        return match("e.organizer = :user", params);
    }

    @Override
    public Optional<Meeting> findByDayAndUser(MeetingDate date, SystemUser systemUser, MeetingDuration duration) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", systemUser);
        Calendar endDate = (Calendar) date.calendar().clone();
        endDate.add(Calendar.MINUTE, duration.duration());
        params.put("start", date.calendar());
        params.put("end", endDate);

        return matchOne("e.organizer = :user AND e.date.day >= :start AND e.date.day <= :end", params);
    }

    public Optional<Meeting> findByParticipant(MeetingDate day, MeetingParticipant participant) {
        return Optional.empty();
    }
}
