package ExamRecognizer;// Generated from C:/Users/diogo/IdeaProjects/sem4pi-22-23-1/ecourse.antlr/src/main/java\Exam.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExamParser}.
 */
public interface ExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExamParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ExamParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ExamParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void enterExam(ExamParser.ExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void exitExam(ExamParser.ExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(ExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(ExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void enterFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void exitFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#grade}.
	 * @param ctx the parse tree
	 */
	void enterGrade(ExamParser.GradeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#grade}.
	 * @param ctx the parse tree
	 */
	void exitGrade(ExamParser.GradeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(ExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(ExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionType(ExamParser.QuestionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionType(ExamParser.QuestionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(ExamParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(ExamParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice(ExamParser.Multiple_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice(ExamParser.Multiple_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#short_answer}.
	 * @param ctx the parse tree
	 */
	void enterShort_answer(ExamParser.Short_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#short_answer}.
	 * @param ctx the parse tree
	 */
	void exitShort_answer(ExamParser.Short_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#numerical}.
	 * @param ctx the parse tree
	 */
	void enterNumerical(ExamParser.NumericalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#numerical}.
	 * @param ctx the parse tree
	 */
	void exitNumerical(ExamParser.NumericalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#select_missing_words}.
	 * @param ctx the parse tree
	 */
	void enterSelect_missing_words(ExamParser.Select_missing_wordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#select_missing_words}.
	 * @param ctx the parse tree
	 */
	void exitSelect_missing_words(ExamParser.Select_missing_wordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#true_false_question}.
	 * @param ctx the parse tree
	 */
	void enterTrue_false_question(ExamParser.True_false_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#true_false_question}.
	 * @param ctx the parse tree
	 */
	void exitTrue_false_question(ExamParser.True_false_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#question_text}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_text(ExamParser.Question_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#question_text}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_text(ExamParser.Question_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#answer_multiple_choice}.
	 * @param ctx the parse tree
	 */
	void enterAnswer_multiple_choice(ExamParser.Answer_multiple_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#answer_multiple_choice}.
	 * @param ctx the parse tree
	 */
	void exitAnswer_multiple_choice(ExamParser.Answer_multiple_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#answer_correct}.
	 * @param ctx the parse tree
	 */
	void enterAnswer_correct(ExamParser.Answer_correctContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#answer_correct}.
	 * @param ctx the parse tree
	 */
	void exitAnswer_correct(ExamParser.Answer_correctContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#answers}.
	 * @param ctx the parse tree
	 */
	void enterAnswers(ExamParser.AnswersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#answers}.
	 * @param ctx the parse tree
	 */
	void exitAnswers(ExamParser.AnswersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#questionValue}.
	 * @param ctx the parse tree
	 */
	void enterQuestionValue(ExamParser.QuestionValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#questionValue}.
	 * @param ctx the parse tree
	 */
	void exitQuestionValue(ExamParser.QuestionValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#studentAnswer}.
	 * @param ctx the parse tree
	 */
	void enterStudentAnswer(ExamParser.StudentAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#studentAnswer}.
	 * @param ctx the parse tree
	 */
	void exitStudentAnswer(ExamParser.StudentAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(ExamParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(ExamParser.EndContext ctx);
}