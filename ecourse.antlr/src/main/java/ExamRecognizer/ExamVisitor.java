package ExamRecognizer;// Generated from C:/Users/diogo/IdeaProjects/sem4pi-22-23-1/ecourse.antlr/src/main/java\Exam.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExamParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ExamParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam(ExamParser.ExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(ExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#grade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrade(ExamParser.GradeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(ExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#questionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionType(ExamParser.QuestionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(ExamParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#multiple_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice(ExamParser.Multiple_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#short_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_answer(ExamParser.Short_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#numerical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical(ExamParser.NumericalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#select_missing_words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_missing_words(ExamParser.Select_missing_wordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#true_false_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue_false_question(ExamParser.True_false_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#question_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_text(ExamParser.Question_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#answer_multiple_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer_multiple_choice(ExamParser.Answer_multiple_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#answer_correct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer_correct(ExamParser.Answer_correctContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#answers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswers(ExamParser.AnswersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#questionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionValue(ExamParser.QuestionValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#studentAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStudentAnswer(ExamParser.StudentAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(ExamParser.EndContext ctx);
}