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
package ecourse.studentManagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.studentManagement.domain.MecanographicNumber;
import ecourse.studentManagement.domain.StudentBuilder;
import ecourse.studentManagement.repositories.StudentRepository;
import ecourse.userManagement.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Component
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentUserRepo) {
        studentRepository = studentUserRepo;
    }

    @Transactional
    public void registerStudent(final SystemUser systemUser, final String number, final int taxNumber, final Calendar birthDate) {
        final var studentBuilder = new StudentBuilder();
        studentBuilder.with(systemUser, number, taxNumber, birthDate);
        final var newStudent = studentBuilder.build();
        studentRepository.save(newStudent);
    }

    public Student findBySystemUser(SystemUser systemUser) {
        return studentRepository.findBySystemUser(systemUser.username()).get();
    }

    /**
     * returns the student with the given mecanographic number
     *
     * @param mecanographicNumber the mecanographic number to search for
     * @return the student with the given mecanographic number
     */
    public Student findByMecanographicNumber(String mecanographicNumber) {
        return studentRepository
                .findByMecanographicNumber(new MecanographicNumber(mecanographicNumber))
                .orElse(null);
    }
}
