# Client Answers

This consists of a compilation of questions/answers to the client in order to enlighten the project.

Source: https://moodle.isep.ipp.pt/mod/forum/view.php?id=147606

**LAST UPDATE:** 13/06/2023

## Index

- [Board](#board)
- [Post-it](#post-it)
- [Class](#class)
- [Course](#course)
- [Deployment](#deployment)
- [Exam](#exam)
- [Grades](#grades)
- [Manager](#manager)
- [Meeting](#meeting)
- [Question](#question)
- [Teacher](#teacher)
- [Student](#student)
- [UI](#ui)
- [User](#user)


## Board 
[Back to Index](#index)

**Q:** When a user shares a board with multiples users are the permissions (read or write) defined per user ? Or are they the same for every user it is shared with ?

**A:** The owner of the board should be able to specify the access that each user has to the board (read or write).

---
**Q:** Where should a notification regarding the update of a board post appear? In the user app, the shared board app or both?

**A:** For me, it should be in the Shared Board App.

---
**Q:** As stated in the system specifications document, "Columns and rows may have titles. They may also be identified by an integer number from 1 to the maximum number", we wanted to clarify if it should be possible to have a column with no title and no identifier.

**A:** In order for user to post content into a cell they must identify the cell. Therefore, I think at least, ir should be possible to identify the cell by the number of its column and the number of its row. If the cells have titles, these titles can be used to identify the cells. However, it should always be possible to identify a cell by the column number and row number.

---
**Q:** can the identifier of a column/row change once it's assigned?

**A:** Regarding changing the title of the columns and rows after creating the board, there is nothing explicit about that. Therefore, I would accept the solution that does not support that possibility

---
**Q:** Can a user own more than one board?

**A:** Yes.

---
**Q:** Relativamente ao uso da shared board:
- uma célula pode ter mais do que um post it?
- Se sim, é possível ter post-its de users diferentes na mesma célula? Se sim, até quantos?

**A:** Neste momento (no âmbito deste projeto) isso não será necessário. A ser possível (uma célula com mais do que 1 post-it) isso iria dificultar algumas funcionalidades, como a que permite mudar um post-it.

---
**Q:** In the requirements, it says that the system should maintain the history/log of all the updates on the board. There's someone specific that will be able to see those logs, or is it just something everyone that has access to the board can see?

**A:** The history in the boards is important for the following functional requirements:
- FRB06 - Undo Post-it Change A user undo the last change in a post-it
- FRB07 - View Board History A user views a history of updates in a board

If a user as read access to the board he/she can view the history.

---
**Q:** Regarding the board history of FRB07, which information should be stored? For example: change date, change made,
user?

**A:** I would say all the information that is needed for a possible "restore operation" in the future (so that it can
be used again with "total" functionality).

---
**Q:** O cliente pretende que seja necessária a autenticação de um utilizador no browser antes da visualização da board?
Ou esta autenticação estaria relacionada com a autenticação que é feita na Shared Board App(SBPApp), consequentemente a
visualização da board seria apenas e só possível se o utilizador estiver a usar SBPApp ao mesmo tempo?

**A:** Sim, do ponto de vista do cliente a solução que propõe é aceitável.

---
**Q:** O cliente pretende que seja apenas desenvolvida uma página web para a visualização das boards e autenticação na
linha de comandos(cli), como demonstrado na página 11 da especificação do projeto, ou então também, aceitaria uma
aplicação totalmente desenvolvida numa página web (com autenticação executada na mesma).

**A:** Como cliente, e se percebi bem, a primeira opção será o que eu pretendo. Ou seja, todas as funcionalidades da
Shared Board App são realizadas na aplicação do tipo "consola" em java com a exceção da parte relativa à visualização em
tempo real dos boards.
Notem que esta US tem requisitos não funcionais especificos de RCOMP e devem, em termos de solução técnica, seguir as
orientações dos docentes dessa unidade curricular.

---

## Post-it

[Back to Index](#index)

**Q:** As a client, do you want us to persist post-its in the database or they can be available only while the server is
running (deleted if the server stops)?

**A:** For me the information regarding the shared boards may be available only during the shared board server
execution.
However, if you are also developing the functionality "FRB08 - Archive Board", there should be some way to archive and
the restore the board for "testing" purposes.

---
**Q:** Nas especificações do sistema é dito o seguinte:
"Users with write permission may post content to a cell in the board. The content can be a text or an image."
Quando um User cria um post-it deve passar um link da imagem por
exemplo: "https://www.isep.ipp.pt/img/logo_20230106.png"
Ou devemos anexar uma imagem que está no nosso computador?

**A:** Para o cliente é um pouco indiferente o mecanismo que usam para fazer o "post" de imagens (assim como o(s)
formato(s) suportado(s)).
Podem optar pela solução que for mais simples. Suponho que suportar o "upload" de imagens locais e suportar um formato
comum, como png ou jpeg, seja suficiente.

---
**Q:** As I read all the requirements within these User Stories, I had a few questions:
-Who can see the board's updates?
--All users associated to a board are able to see the updates;
--All users with Write permissions in a board are able tto see its updates; --Only the owner of a board is able to see
its updates.
-As I'm sharing the board, I have to do which of these? --give permissions to the users to Write on it immediately
--only share it with Read permissions and afterwards have the possibility to give the user write permissions
--only share it with Read permissions and lock those permissions as Read
-After sharing the board with someone, should it be considered an update to be listed on User Story 3005?

**A:** I think that you can find the answers to the majority of your questions in the specification document.
"The user that creates the board is its owner. The owner can share the board with other users. Users may have read or
write access to the board.". If a user shares the board with other users (either read or write) these users should be
able to see the updates (otherwise what are we sharing?). Only users with write permissions are able to update the
board.
When a user shares a board he/she must specify the users and, for each user, if the access is read or write. I think it
makes sense to notify current users of a board when some update is done regarding access to the board.

---

## Class

[Back to Index](#index)

**Q:** Boa tarde nos estamos a ter uma duvida sobre qual e a diferença entre meetings e classes

**A:** Os conceitos são bastante próximos mas existem diferenças. Por exemplo, as aulas são regulares e têm
participantes pré-definidos. As reuniões são definidas por qualquer utilizador, têm o conceito de convite, podem-se
especificar os participantes, etc. Ou seja, existem ainda diferenças significativas.

---
**Q:** Can a meeting and class overlap? If so, should the system notify that there is an overlap and for which user(s)?

**A:** Regarding classes: 
- FRC09 - Schedule of Class A teacher schedule a class (always a recurring class, happens every week). System must check if the Teacher is available for the class period

Regarding Meetings:
- FRM01 - Schedule a Meeting A user schedules a meeting. The system must check if all participants are available and send invitations to participants.

In the case of Meetings they should not be created if the participants are not available (i.e., they may have classes or other meetings at the same time).

---
**Q:** With the description of the functionalities/requirements of classes and meetings, wouldn't they be the same? Since both are online, in my point of view, it would make sense if a student could accept/reject a class as well, for example, and the same could be applied for the remaining requirements with the exception of creating classes.

**A:** There is no requirement that "says" the student can accept or reject a class. Only meetings have the possibility that participants may accept or reject the meeting. This does not apply to classes.

---
**Q:** Should we allow the users to choose until what date they want to schedule the weekly classes or should the weakly classes be scheduled until the end of the semester and if so what is the duration of the semester

**A:** Only teachers should be able to schedule classes. 
I think there is no requirement that refers to the course duration or the concept of term or semester. Therefore, if you do not consider the course to have a pre-established ending date, then you also should not consider the end of the recurrence (in fact, it will happen when the manager closes the course). If you consider that a course must have an ending date, then also consider the end of the recurrence of the classes to the same date.
However, since "nothing" happens at the time of the classes, the option you take will have limited implications, I think, only at the validation of availability of participants.

---
**Q:** When the Manager creates a Course, should there already be an assigned group of Teachers and Students associated with the created Course or should they be added later?

**A:** There are specific requirements for all these functionalities and they may take place after the course creation. For instance, FRC04, refers to setting the teachers of the course and FRC06 refers to the bulk enrolment of students. They both can take place after the creation of the course.
However, I think there is no "problem" if the system supports that the manager do all these settings just "after" creating the course.

---
**Q:** Regarding weekly classes recurrence, should be considered as a recurrence class if it happens more than one day per week? For example, the same class can occur on mondays and wednesdays?

**A:** A class should be considered only one class ("aula"). This one class is always a recurring class. If a course has more than one class each week, then each class will be a recurring class.

---
**Q:** We have a question about a class scheduling. When a teacher wants to schedule a class, do they introduce start date/time and end date/time, or start date/time and duration of the class? And in general, how often will the client want to consult the duration of the classes?

**A:** For the class the teacher should entre the start date/time and duration (see Section 5.1.2).

The duration of the class is important when scheduling "events" (such a classes and meetings) because the system must comply with some scheduling rules, for instance, to avoid scheduling classes that are coincident with other classes of the same course (see Section 5.1.2).

---


## Course 
[Back to Index](#index)

**Q:** As for the course, there are 2 things bothering me:
- The unique code is said to be input and the identifier is automated. Should they be different things, or was there any confusion?
- It is mentioned a title for the course and a unique name. Are they the same?

**A:** I should say that maybe the ideia is that a course:
- has unique code inputed by the administrator, e.g., "JAVA-1, that is used to identify it
- it has a name (name and title may refer to the same property of courses, and I would say that there is no need for it to be unique)

---
**Q:** As it is said in the project description, "Courses may have a minimum and a maximum number of enrolled students. This may limit the possibility of opening (i.e. starting) courses that do not satisfy the limits.", that being showed we want to know if it is optional to have limits on the course? If its mandatory to have the limits, who can establish limits of students  to the course?, what are the acceptable limits of students in a course? And what are the conditions for a course to have limits?

**A:** Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits.

---
**Q:** Can one student be enrolled in different courses?

**A:** Yes. I see no reason not to (section 5.1.3: "Students enroll in courses.").

---
**Q:** In the document you provided it says "A course may be open or closed." but I saw a post of yours where you mentioned "(before this course is changed to "in progress")". So does that mean it can be open (to enroll), closed(after it ends) and in progress(decurring, closed for enrollment)?

**A:** Yes, a course may have several states. Section "5.2.1 Course" exemplifies the usual cycle of a course.

---
**Q:** Should a class be already scheduled, recurring or not, for a course to be able to open for enrollments, or is this irrelevant?

**A:**

---
**Q:** Regarding the edition of a course to be taken, are there any attributes to specify it? (such as year and/or semester, for example)(practical example: ESOFT 2023 2nd semester)

**A:** "A course is simply a course". There is no hierarchy or containment relation with other concept, such as program or semester. From the specification: "Different editions of a course are to be considered different courses (e.g., Intro-Math-Sem01, Intro-Math-Sem02)".

---
**Q:** Teacher in charge and normal class teacher responsibilities

**A:** For the requirements of the system you may consider that the roles of teacher and teacher in charge have the same capabilities. The only rule is that each course must have one teacher in charge. So, the system must register the teacher in charge for each course.

The teacher in charge has more responsibilities than a regular teacher but, for the moment, they are outside the system to be developed (for instance, the program and contents of the course are a responsibility of the teacher in charge).

---


## Deployment 
[Back to Index](#index)

**Q:** Na US G004 gostaria de saber se temos que fazer deploy neste sprint e se tem alguma preferencia de onde o podemos alocar.

**A:** Algumas user stories deste sprint são relativamente vagas e dependem do vosso planeamento. Por exemplo, que aplicações (das previstas no capitulo 4 terão, de facto, já alguma "versão" para instalarem?).
De qualquer das formas, a ideia é preparem o "deployment" em função dos objetivos de cada sprint. Do ponto de vista do cliente, seria interessante terem já uma versão "executável" (embora sem funcionalidades) das app Admin, Teacher e Student (e respectiva base de dados).
Nota: Em concreto, e apenas para a UC de LAPR4, existem vários "níveis" de deployment, que estão identificados no critério de avaliação "Implantação/Deployment (10%)".

---


## Exam 
[Back to Index](#index)

**Q:** As for the exam, is the title written in the exam the same as its unique title to identify? They are both mentioned, but in different parts of the project.

**A:** Page 14: "It has a unique title and a small description.". Therefore, my answer should be that the title is unique. Maybe it could be used to identify the exam. It is also mentioned that the contents and structure of exams is to be based on the Moodle platform (particularly for the questions). As such, maybe a study of that platform is a good starting point for understanding how this feature of the eCourse project should/can "work".

---
**Q:** Can the same question be used in different exams? Or are the questions made for a specific exam?

**A:** At the present moment, when designing the exam, the user specifies all its structure (including the questions, as described in section 5.2.4). There is no "database" of questions. NOTE: However, it is possible that new user stories regarding exams will be added (in sprint B and C) for teams/groups with an extended number of students enrolled in LPROG.

---
**Q:** In regard to the exam, what do you mean by feedback and grading? Is it necessary to save the answers of the users showing and comparing them with the correct answer? Furthermore, is it necessary to save the answer of the question?

**A:** The idea is to have feedback and grading similarly to what is available for tests in the moodle platform. How you may achieve this is not up to me to decide.

---
**Q:** Is it necessary to know who created a specific exam?

**A:** I do not think there is any requirement that explicitly requires that. However, I can not answer if the technical solution of the problem may require that.

---
**Q:** Is it necessary to list exams that a specific teacher created?

**A:** Once again, I do not see any requirement that specifies that. However, there is one very similar: FRE03-List Course Exams.

---
**Q:** Regarding the exam's section, our group had thought about 2 viable options in case of a missing student:
- His grade would be considered a 0;
- His grade would be considered N/A and a message/email would be sent to him regarding the missing exam

If neither the options are the intended solution, it would be great to be provided with the best solution for you.

**A:** I think only FRE06 - List Course Grades is related to your question. I think that the system should not display the grade of a student that dit not take the exam, it should display, for instance, N/A.

I also do not see any mention in the specification related to the notification of students when they miss an exam.

---
**Q:** Regarding the questions within the exam, should every type of question have a feedback? 

**A:** The feedback is a text (regarding a question/answer) that may appear to the student when he/she finishes the exam. The teacher can use this text to "justify/explain" why the answer given by the student is wrong (if the student gave the a wrong answer) and what should be the right answer. But, basically, it is only "text". Every possible "wrong answer" may have a feedback. It may also be possible to specify the feedback for the right answer. Taken from the Moodle documentation "...If you wish, add general feedback. This is text that appears to the student after he/she has answered the question..."

---
**Q:** Ao criar um exame, este pode ser marcado para mais do que um dia?

**A:** Relativamente a esta questão o que está na especificação é: "Exams have also an open and a close date. The open date is the time when students can start to take the exam. The close date is the deadline for students to submit the exam.". Portanto, eu diria que tem um "tempo" de abertura e um tempo de "fecho". O tempo de abertura tem de ser antes do tempo de fecho. Nada de especifico é dito sobre a duração do exame ou outro tipo de restrição, pelo que se devem aceitar exames que estão "abertos" por períodos que ultrapassam um dia.

---
**Q:** Regarding the referred functional requirement, it states "The system displays the grades of a class(...)".
Is this a typo? Meaning that classes are not evaluated, but courses instead. If so, how are courses evaluated? Is it an average between all taken exams?

**A:**
Hello.

The grades in FRE05 and FRE06 are related to the exams of the course. There is no need to do any calculation. The
system, should display the grades of each exam that the student did take (FRE05) or display the grades for every exam of
the course and for every student (in the case of FRE06).

---
**Q:** Good afternoon client, I have a question about the exams. After the exam being created, the teacher can still
change it? If yes, can two (or more) teachers edit the same exam?

**A:** There is no explicit statement regarding editing an exam. However, there are several data to be specified for an
exam and its specification can be complex. Therefore, as a Client of the system, I think that FRE01, in the perspective
of a use case, can be a very long use case, including several steps, with, for instance, a step to validate the exam
specification (according to the grammar). All these specifications and steps are prone to user errors. Therefore, maybe
you should consider a final "publish" action to mark the end of the creation process or some other alternative solution
to support this lengthy process.

---
**Q:** Relativamente à funcionalidade de realizar um exame, mais concretamente à parte de mostrar a nota/feedback com a
propriedade "after-closing" (referenciado na especificação do sistema), é esperado o aluno ser notificado mal a data de
fecho do exame seja atingida ou pretende-se apenas que a nota possa ser visualizada a partir da funcionalidade
correspondente à US2005/2006.
Caso pretenda que o aluno seja notificado, pergunto-lhe se isto deve ser feito através de uma notificação do sistema ou
através de um serviço, por exemplo, email.

**A:** Não consigo encontrar nenhuma referência a uma notificação depois da data de fecho do exame. O que vejo é que o
sistema deve mostrar o resultado e o feedback no final do exame.
A questão que se pode colocar é quando é que acontece o "fim do exame". Podemos entender que é quando o aluno submete o
seu exame ou quando atingimos a data de fecho do exame ("close date"). Como cliente posso indicar que aceito a primeira
interpretação, ou seja, assim que o aluno submete o seu exame, este recebe o feedback e nota (de forma sincrona). Penso
que em termos de solução será a situação mais simples.

---
**Q:** Tendo em conta que aceita primeira interpretação, qual será a diferença entre on-submission e after-closing? A
minha dúvida é principalmente acerca do funcionamento da propriedade after-closing.

**A:** A minha indicação anterior é a de que o aluno recebe o feedback e a nota quando submete o exame. Em principio o
aluno tem de submeter o exame antes do tempo de fecho do exame. Se o aluno tentar submeter um exame depois do seu fecho
o sistema deve simplesmente não aceitar a entrega/submissão.

---
**Q:** A propósito das US's 2004 e 2009, de que forma é que os exames serão apresentados ao aluno para o mesmo os
realizar? Será semelhante às boards, em que será criada uma página em Java Script?

**A:** Documento de especificação, página 11, Figura 4.1. É apresentada uma visão da arquitetura da solução.
Apenas a aplicação "shared board app" implementa um servidor http para servir o "board viewer". Todas as outras
funcionalidades da solução devem estar distribuídas pelas outras "apps", que devem ser java console applications. Ou
seja, a "user interface" para a funcionalidade dos exames deve ser implementada como uma console application. A
referencia aos "quiz" do moodle é apenas para ilustrar quais as funcionalidades pretendidas. Mas a sua implementação não
necessita de ser realizada em HTML (ou seja, não é esperado que o façam).

---
**Q:** When a student takes an exam, should we assume that he has only one attempt, or the number of attempts should be
specified in the exam grammar?

**A:** There is no mention regarding attempts in the specification, therefore there is no need to support that
functionality.

---
**Q:** 1-Ao atualizar um exame é necessario calcular de novo a nota do aluno ou continua com a sua nota antiga?(Se for
necessario entao teremos de persistir as respostas do aluno)
2-Em relação ás perguntas 'short answer' estas podem ser corrigidas automaticamente?(o utilizador teria 100% da cotação
se a resposta fosse igual e 0% caso seja diferente)

**A:** Embora possa não estar explicito na especificação, não faz sentido fazer alterações a exames depois da "open
date" ou de haver já exames respondidos.
Quanto às perguntas, todas elas (i.e., todos os tipos de perguntas) devem ser passíveis de correção automática. É esse o
objectivo.

---
**Q:** Acerca dos exames, quanto exames podem existir num ano, para o mesmo curso. Pergunto isto no seguimento de uma
discussao de grupo acerca de como identificar os exames de uma maneira eficaz, no caso de apenas existir 1 exame anual
para cada curso, a combinação do ano e do curso seria uma ótima chave para a procura de um exame.

**A:** Por favor não confundir os "courses" desta solução com unidades curriculares que fazem parte de um curso como a
LEI. Estes "courses" devem ser considerados como cursos de pequena duração.
Na especificação refere "It has a unique title and a small description.". Portanto, os exames têm um titulo único. Não
existe a noção de "exame anual para cada curso", porque não existem "cursos" (em português) e não existem "anos".
Por favor revejam o que é descrito sobre "exams" no documento da especificação.

---
**Q:** Our group has a following question: when a student finished taking exam, will he want at some point review the
exam and see his answers? Do we need to save the exam with the answers of a certain student to be able to show it
later (if needed), or should we just calculate the final grade and show feedback for each question (if applicable)?
Another question is about types of feedback: on-submission means on submission of a question or the whole exam?

**A:** when a student finished taking exam, will he want at some point review the exam and see his answers?
This is not required.
Do we need to save the exam with the answers of a certain student to be able to show it later (if needed), or should we
just calculate the final grade and show feedback for each question (if applicable)?
Just calculate the final grade and show feedback for each question. Unless saving of the answers is required as a
technical solution for some other aspect of your solution you do not need to save the answers.
Another question is about types of feedback: on-submission means on submission of a question or the whole exam?
On submission of the whole exam.

---

## Grades

[Back to Index](#index)

**Q:** After discussing these questions with the OT teacher, we would like to know your opinion about the grades.
First we would like to know if you wish that the grades are saved in the program database. Second we would like for you
to clarify the expected flow of both feedback and grade types.

**A:** Regarding the first question, if you do not save the grades how do you implement the functionalities of FRE05 and
FRE06?
Regarding the second question, the ideia is to have something very similar to the Moodle platform. According to the
specification "The system must also support the automatic production of feedback and grading for the answers given by
students when they take the exam. Usually this is done at the end of the exam." So, the grade and the feedback should be
provided only and the end of the exam. At the end of the exam, the system should display to the student the resulting
grade of the exam as well as the grade and feedback for each question/answer.
You may find a simple workflow on how to create moodle tests(quiz) in https://youtu.be/dCDPS7ufGuQ
Regarding grades, each question will have points when the answer is correct. If you sum all the points form all the
answers you will have the grade of the exam.
Please consider only the question types that are presented in the specification document. For each question type you
will find further details on the specifics of the grading logic.

---

## Manager

[Back to Index](#index)

**Q:** Does the Manager have the same attributes as Students and Teachers? (tay payer number, name)
Is the name of each actor either the full name or the short name?

**A:** A manager should have only the attributes as described in section 5.1.1.

Also in section 5.1.1: "All users should be identified in the system by their email.". It is also stated that "Each user
should also provide its full name and short name". As mentioned before, the short name is like a given name, the short
name someone chooses to be called. Therefore, all users should have a name (full name) and a short name. When later, the
document refers that students and teachers should have names, please consider both full name and shot name (since both
are users).

---
**Q:** Is it necessary to track changes that a manager has made? For example, it is necessary to know which manager created which course?

**A:** I think that what you suggest is a good practice. However, I think there is no explicitly requirement for that, for the role of manager. There are other roles and requirements that need that (for instance, users and boards or users and meetings).

---
**Q:** We would like to know what are the requirements for a student to be accepted or rejected from an application  to a course.

**A:** I think that decision can not be automatically taken by the system, it is a decision of the manager. The responsibility is of the manager according to course rules that ar out of the scope of this implementation. I think the system should only register the reason for the decision.

---
**Q:** Um manager pode conter dados como "Birth Date" e "Tax Payer Number", assim como o Teacher e o Student?

**A:** Para o que se pretende actualmente essa informação não é relevante/útil para os managers. Mas, como cliente, não me parece que seja grave se puder ser registada também para esses utilizadores.

---


## Meeting 
[Back to Index](#index)

**Q:** Boa tarde nos estamos a ter uma duvida sobre qual e a diferença entre meetings e classes

**A:** Os conceitos são bastante próximos mas existem diferenças. Por exemplo, as aulas são regulares e têm participantes pré-definidos. As reuniões são definidas por qualquer utilizador, têm o conceito de convite, podem-se especificar os participantes, etc. Ou seja, existem ainda diferenças significativas.

---
**Q:** Can a meeting and class overlap? If so, should the system notify that there is an overlap and for which user(s)?

**A:** 
- Regarding classes: FRC09 - Schedule of Class A teacher schedule a class (always a recurring class, happens every week). System must check if the Teacher is available for the class period
- Regarding Meetings:
FRM01 - Schedule a Meeting A user schedules a meeting. The system must check if all participants are available and send invitations to participants.

In the case of Meetings they should not be created if the participants are not available (i.e., they may have classes or other meetings at the same time).

---
**Q:** Can any user of the system invite any other user? For example, can a student invite another student who is in a different course, or can a manager can create a meeting with any group of teachers.

**A:** When in the document specification the term "User" is used it usually means "any user" of the system. Therefore, any user of the system can schedule a meeting and be a participant in a meeting.

---
**Q:** As I read the little information we had about meetings, I had some doubts on this functional requirement:
- FRM04 List Participants The system displays the lists of participants in a meeting and the response status (accept or reject meeting).

When mentioning "accept or reject meeting", I though to myself about an invite being sent to the user and 2 options for the response:
- The sent invite has already as a response "Rejected", so that it can either be changed to "Accepted" or stays as it is, seeing that if the user doesn't accept it, he will be rejecting it.
- The sent invite has a response being  "No answer" and, at a certain time near the begining of the meeting, the answer would change to "Rejected". The answer can be changed before it at any time to "Accepted" or "Rejected"

I think that the 1st option would go more towards what you are looking for (by reading FRM04, it would only show the
users who "accept or reject the meeting") but I wanna be sure

**A:** FRM01 relates to the fact that the system should check if participants are available before sending the
invitations. For instance, checking if a user has no other class or meeting at the same time. The system should only
invite participants with a free calendar at the time of the meeting.

- In FRM03, the user accepts or rejects an invitation.
- In FRM04, the status of someone that did not answer should be "no answer" or "unknown".

- To be noticed that there is "nothing" to do by the system at the time of the meeting. Nothing needs to "happen". The
  same applies for classes.

---
**Q:** Em relação a esta US deveríamos simplesmente considerar os "meeting request" que não foram aceites ou rejeitados?
Um "meeting request" que já foi aceite ou rejeitado poderá ser alterado, depois?

**A:** Não sei se discutiu o assunto na aula OT presencial, mas a intenção nesta US é fazer exactamente o que está
descrito: aceitar ou rejeitar um pedido de reunião. A decisão é tomada uma única vez pelo utilizador.

---
**Q:** Relativamente a esta user story, o ficheiro excel providenciado refere o seguinte:
> As User, I want to view a list of participants in my meeting and their status (accept or reject).
> Aqui, pelo menos a meu ver, o termo "my meeting" parece sugerir que esta funcionaliade apenas se a aplica a meetings que
> o utilizador criou (i.e. se um User não for owner de uma meeting não puderá ver os seus participantes).
> No entanto, no documento de especificação, o seguinte é dito:
> The system displays the lists of participants in a meeting and the response status (accept or reject meeting).
> O que não discrimina se esta funcionalidade deve estar disponível a todos os participantes ou apenas ao criador.
> A meu ver, a alternativa que parece fazer mais sentido seria, de facto, qualquer utilizador poder ver a lista de
> participantes de uma meeting, desde que pertença à mesma; no entanto, faço-lhe esta pergunta para me certificar de que a
> feature que vai ser implementada realmente corresponde àquilo que o cliente pretende.
> Aproveito ainda para lhe perguntar se a data da ocorrência da meeting possui alguma importância no que diz respeito a
> este caso de uso; isto é, o utilizador deve poder selecionar a meeting que pretende ver os participantes mesmo que esta
> já tenha ocorrido (e terminado), ou apenas aquelas que estão/irão decorrer é que possuem relevância?

**A:** Relativamente à primeira questão faz sentido a sua segunda interpretação, ou seja, o que está no documento de
especificação está correto e reflete o que o cliente deseja. Ou seja, deve ser possível qualquer utilizador participante
consultar os outros participantes em reuniões na qual ele também é participante.
Relativamente à segunda questão, do ponto de vista do cliente faz sentido também ver reuniões que ocorreram no passado.

---

## Question

[Back to Index](#index)

**Q:** Can the same question be used in different exams? Or are the questions made for a specific exam?

**A:** At the present moment, when designing the exam, the user specifies all its structure (including the questions, as
described in section 5.2.4). There is no "database" of questions. NOTE: However, it is possible that new user stories
regarding exams will be added (in sprint B and C) for teams/groups with an extended number of students enrolled in
LPROG.

---


## Teacher 
[Back to Index](#index)

**Q:** what should the taxpayer number verifications be? Of course, it is unique, but what format does it follow, since it varies from country to country?

**A:** For the project with should focus on supporting the Portuguese taxpayer number. You may consider single person taxpayer (no need to support companies).

---
**Q:** Is there any format rule regarding the acronym for the teacher length-wise or which characters should or should
not be used? Also, should this acronym be unique for each teacher?

**A:** I think a sequence of capitalised words should be used for the teacher acronym. Maybe the length could be a
configuration setting of the application. I think it would be wise to enforce that acronyms be unique.

---
**Q:** It is mentioned that only one teacher can be in charge of a course, but can the same teacher be in charge of
several other courses?

**A:** Yes, a teacher can be in charge of several courses

---
**Q:** Relativamente a esta user story, o ficheiro excel providenciado refere o seguinte: "As Teacher, I want to view a
list of the grades of exams of my courses"
Um professor deve conseguir ver as notas de um curso específico, de todos os seus cursos ao mesmo tempo, ou ambos?

**A:** Penso que seria interessante ter todas as possibilidades que mencionou. Como sugestão, talvez apresentar os
cursos do professor e este poder indicar quais os cursos que quer ver ou "todos".

---
**Q:** As notas dos exames formativos tambem deveriam ser listadas? Ou apenas seriam listadas as notas dos exames "
normais"?

**A:** Os exames formativos são gerados automaticamente e, não existindo um registo das perguntas e respostas desses
exames, não penso que faça sentido armazenar os resultados dessas notas.
No entanto, o sistema deve apresentar para estes exames o feedback e a nota no final.

---

## Student

[Back to Index](#index)

**Q:** Can one student be enrolled in different courses?

**A:** Yes. I see no reason not to (section 5.1.3: "Students enroll in courses.").

---
**Q:** what should the tax payer number verifications be? Of course it is unique, but what format does it follow, since
it varies from country to country?

**A:** For the project with should focus on supporting the Portuguese tax payer number. You may consider single person
tax payer (no need to support companies).

---
**Q:** Is it necessary to list exams that were taken by a specific student?

**A:** FRE03 - List Grades should display the grades of a students. This regards previous exames taken by the student.

---
**Q:** The students enrolled in bulk are students already registered? Or should they be registered and then enrolled
after importing from the CSV file?

**A:** I would argue that "enroll" is different from "register". A student to be enrolled in a course must be a
registered user.
As a manager I only want to enroll students. To be a student you must be registered as student. Enrolment does not
include registration of users as students. FRC06 - Bulk Enroll Students in Course is basically and automated version of
FRC07 - Request Enrollment in Course. FRC07 can only be executes by students (so they are already registered).
I think you can infer that from the specification.

---
**Q:** Relativamente a este caso de uso, devermos assumir que todos os estudantes já estão registados como utilizadores
do sistema? Caso negativo, será necessário efetuar a sua inscrição?

**A:** Dado o que está descrito no documento da especificação, em sua opinião, o que é que acha que faz sentido?
Discutiu o assunto na aula OT presencial?

---

## UI

[Back to Index](#index)

**Q:** Is the UI supposed to be run on the console or should there be an elaborate UI to be developed?

**A:** There are some non-functional requirements relating to that questions. My answer should be that I expect a
solution in accordance with the program of the courses of LEI up until this semester (including this semester).
Therefore, in general, what is expected is a Java Console Application (or set of applications). But there are some
exceptions, as presented in the non-functional requirements.

---
**Q:** Me and my group had some doubts about how the application (s) were supposed to work. So we came up with 3 different ideas. 
- Initially it is asked the role to be analyzed. Then, an application would run for the role chosen. So there would be an application for each role
- The login would be asked within the application, and then it would show the information possible to show within the role of the logged user
- There would be different applications to be run right from the start and only the people with the role of each application could log in into that
We would love to know if any of these ideas are correct or if there is another way we may be missing.

**A:** According to the diagram on page 11, it is clear that the goal is to have a solution with several "parts". Some of these parts are identified as "apps" (from an end user point of view). These apps have specific user roles. From the Aliens perspective, the way you may achieve "that" is not particularly relevant. However, the Client may have the intention to deploy each app/part of the solution into different machines.

---


## User 
[Back to Index](#index)

**Q:** Looking at the users, they are to be given a short name and a full name. Should the short name be part of the full name?

**A:** Usually my suggestion for this type of doubts is to search for the most common approach to solve the issue. I would say that it is part of the analysis of the problem. After that, you should take a decision and justify that decision. Having said that, as a client, I should say that my interpretation of short name is like a given name.

---
**Q:** Do the system has to support an option to create new user roles that are not specified at the system specification documents or the system will always have just the three user roles described? The same question can be applied to the question types (e.g., Multiple Choice and Short Answer). Should the system support the creation of new question types?

**A:** Regarding user roles, the solution should support the ones necessary to cover all the actual functional requirements. Regarding question types, only the referenced in the specification are required.

---
**Q:** should the user be multi role? For example, can a system user be a teacher and a student or a manager at the same time?

**A:** I see no need for that. For me, each user as only one specific role in the system.

---
**Q:** Is it necessary to know who created a specific user

**A** There is no requirement specific to these questions. However, I do not know if the system must support "something" related to these questions. I think this is something related to the design of the solution, that you and your team must decide when designing the solution in order to meet the existing requirements.

---
**Q:** Relative to this US, "As User, I want to list all the courses that are available to me" what should we list according to the User role?
- Student: Courses that Student is enrolled
- Teacher: Courses that Teacher teaches
- Manager: All Courses

**A:** Regarding Teachers and Managers I agree with you.
Regarding students I think it is best to list all the courses in which the student is enrolled or may be enrolled (the enrolments are opened). If not, how would a student know the courses in which he/she can enrol?

---

## CSV Files

**Q:** Will we have a csv file example for the students data?

**A:** No, the client did not prepare any csv example. CSV is a standard file format. You should support the bulk enrolment of students in a course by have their identification in a csv file.

---
