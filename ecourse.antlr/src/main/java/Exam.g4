grammar Exam;
@members {
    int totalScore = 0;
    int totalQuestionsValues = 0;
    String student_Answer = "";
    String answer_Correct = "";
    int question_value = 0;
    int i = 0;
}

start : exam end;
exam : title header section+;
title : 'Title' STRING;
header : feedback* grade* description;
feedback : 'Feedback' ('NONE' | 'ON_SUBMISSION' | 'AFTER_CLOSING');
grade : 'Grade' ('NONE' | 'ON_SUBMISSION' | 'AFTER_CLOSING');
description : 'Description' STRING;
section : '{' description questionType+ '}';
questionType : (true_false_question | select_missing_words | numerical | short_answer | multiple_choice | matching) questionValue studentAnswer*;
matching : 'MATCHING' '{' (question_text '-' answer_correct)* '}' '{' answers* '}' ;
multiple_choice : 'MULTIPLE_CHOICE' question_text '{' answer_multiple_choice* '}' answer_correct ;
short_answer : 'SHORT_ANSWER' question_text answer_correct*;
numerical : 'NUMERICAL' question_text answer_correct ;
select_missing_words : 'MISSING_WORDS' '{' question_text answers* '}' answer_correct ;
true_false_question : 'TRUE_FALSE' question_text answer_correct ;
question_text : STRING;
answer_multiple_choice : STRING;
answer_correct : STRING { answer_Correct = $text; } | ('True' | 'False' | INT) { answer_Correct = $text; };
answers : STRING;
questionValue : INT { question_value = $INT.int; };
studentAnswer : ('True' | 'False' | INT | STRING) {
    student_Answer = $text;
    i++;
    if (student_Answer.equals(answer_Correct)) {
        totalScore += question_value;
        System.out.println(i+ ": Correct answer");
    }
    else {
        System.out.println(i+ ": Wrong answer");
    }
    totalQuestionsValues += question_value;
};
end : ('END'| STRING) {
totalScore = (totalScore * 20) / totalQuestionsValues;
System.out.println("Total Score: " + totalScore + "/20");};

///-----------------------------------------------------------------------------//

STRING : '"' ~[\r\n"]* '"';
INT : [0-9]+;
WS : [ \t\n\r]+ -> skip;
///-----------------------------------------------------------------------------//
