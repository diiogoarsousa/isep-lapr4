package ExamRecognizer;// Generated from C:/Users/diogo/IdeaProjects/sem4pi-22-23-1/ecourse.antlr/src/main/java\Exam.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, STRING=20, INT=21, WS=22;
	public static final int
		RULE_start = 0, RULE_exam = 1, RULE_title = 2, RULE_header = 3, RULE_feedback = 4, 
		RULE_grade = 5, RULE_description = 6, RULE_section = 7, RULE_questionType = 8, 
		RULE_matching = 9, RULE_multiple_choice = 10, RULE_short_answer = 11, 
		RULE_numerical = 12, RULE_select_missing_words = 13, RULE_true_false_question = 14, 
		RULE_question_text = 15, RULE_answer_multiple_choice = 16, RULE_answer_correct = 17, 
		RULE_answers = 18, RULE_questionValue = 19, RULE_studentAnswer = 20, RULE_end = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "exam", "title", "header", "feedback", "grade", "description", 
			"section", "questionType", "matching", "multiple_choice", "short_answer", 
			"numerical", "select_missing_words", "true_false_question", "question_text", 
			"answer_multiple_choice", "answer_correct", "answers", "questionValue", 
			"studentAnswer", "end"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Title'", "'Feedback'", "'NONE'", "'ON_SUBMISSION'", "'AFTER_CLOSING'", 
			"'Grade'", "'Description'", "'{'", "'}'", "'MATCHING'", "'-'", "'MULTIPLE_CHOICE'", 
			"'SHORT_ANSWER'", "'NUMERICAL'", "'MISSING_WORDS'", "'TRUE_FALSE'", "'True'", 
			"'False'", "'END'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "STRING", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Exam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    public int totalScore = 0;
	    int totalQuestionsValues = 0;
	    String student_Answer = "";
	    String answer_Correct = "";
	    int question_value = 0;
	    int i = 0;

	public ExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ExamContext exam() {
			return getRuleContext(ExamContext.class,0);
		}
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			exam();
			setState(45);
			end();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExamContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public ExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExamContext exam() throws RecognitionException {
		ExamContext _localctx = new ExamContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			title();
			setState(48);
			header();
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				section();
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__7 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TitleContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__0);
			setState(55);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeaderContext extends ParserRuleContext {
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public List<FeedbackContext> feedback() {
			return getRuleContexts(FeedbackContext.class);
		}
		public FeedbackContext feedback(int i) {
			return getRuleContext(FeedbackContext.class,i);
		}
		public List<GradeContext> grade() {
			return getRuleContexts(GradeContext.class);
		}
		public GradeContext grade(int i) {
			return getRuleContext(GradeContext.class,i);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(57);
				feedback();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(63);
				grade();
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
			description();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeedbackContext extends ParserRuleContext {
		public FeedbackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterFeedback(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitFeedback(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitFeedback(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_feedback);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__1);
			setState(72);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GradeContext extends ParserRuleContext {
		public GradeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterGrade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitGrade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitGrade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeContext grade() throws RecognitionException {
		GradeContext _localctx = new GradeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_grade);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__5);
			setState(75);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__6);
			setState(78);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SectionContext extends ParserRuleContext {
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public List<QuestionTypeContext> questionType() {
			return getRuleContexts(QuestionTypeContext.class);
		}
		public QuestionTypeContext questionType(int i) {
			return getRuleContext(QuestionTypeContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__7);
			setState(81);
			description();
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				questionType();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 128000L) != 0) );
			setState(87);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionTypeContext extends ParserRuleContext {
		public QuestionValueContext questionValue() {
			return getRuleContext(QuestionValueContext.class,0);
		}
		public True_false_questionContext true_false_question() {
			return getRuleContext(True_false_questionContext.class,0);
		}
		public Select_missing_wordsContext select_missing_words() {
			return getRuleContext(Select_missing_wordsContext.class,0);
		}
		public NumericalContext numerical() {
			return getRuleContext(NumericalContext.class,0);
		}
		public Short_answerContext short_answer() {
			return getRuleContext(Short_answerContext.class,0);
		}
		public Multiple_choiceContext multiple_choice() {
			return getRuleContext(Multiple_choiceContext.class,0);
		}
		public MatchingContext matching() {
			return getRuleContext(MatchingContext.class,0);
		}
		public List<StudentAnswerContext> studentAnswer() {
			return getRuleContexts(StudentAnswerContext.class);
		}
		public StudentAnswerContext studentAnswer(int i) {
			return getRuleContext(StudentAnswerContext.class,i);
		}
		public QuestionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterQuestionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitQuestionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTypeContext questionType() throws RecognitionException {
		QuestionTypeContext _localctx = new QuestionTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_questionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				{
				setState(89);
				true_false_question();
				}
				break;
			case T__14:
				{
				setState(90);
				select_missing_words();
				}
				break;
			case T__13:
				{
				setState(91);
				numerical();
				}
				break;
			case T__12:
				{
				setState(92);
				short_answer();
				}
				break;
			case T__11:
				{
				setState(93);
				multiple_choice();
				}
				break;
			case T__9:
				{
				setState(94);
				matching();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(97);
			questionValue();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3538944L) != 0)) {
				{
				{
				setState(98);
				studentAnswer();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchingContext extends ParserRuleContext {
		public List<Question_textContext> question_text() {
			return getRuleContexts(Question_textContext.class);
		}
		public Question_textContext question_text(int i) {
			return getRuleContext(Question_textContext.class,i);
		}
		public List<Answer_correctContext> answer_correct() {
			return getRuleContexts(Answer_correctContext.class);
		}
		public Answer_correctContext answer_correct(int i) {
			return getRuleContext(Answer_correctContext.class,i);
		}
		public List<AnswersContext> answers() {
			return getRuleContexts(AnswersContext.class);
		}
		public AnswersContext answers(int i) {
			return getRuleContext(AnswersContext.class,i);
		}
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_matching);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__9);
			setState(105);
			match(T__7);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(106);
				question_text();
				setState(107);
				match(T__10);
				setState(108);
				answer_correct();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			match(T__8);
			setState(116);
			match(T__7);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(117);
				answers();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multiple_choiceContext extends ParserRuleContext {
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public Answer_correctContext answer_correct() {
			return getRuleContext(Answer_correctContext.class,0);
		}
		public List<Answer_multiple_choiceContext> answer_multiple_choice() {
			return getRuleContexts(Answer_multiple_choiceContext.class);
		}
		public Answer_multiple_choiceContext answer_multiple_choice(int i) {
			return getRuleContext(Answer_multiple_choiceContext.class,i);
		}
		public Multiple_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterMultiple_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitMultiple_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultiple_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choiceContext multiple_choice() throws RecognitionException {
		Multiple_choiceContext _localctx = new Multiple_choiceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_multiple_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__11);
			setState(126);
			question_text();
			setState(127);
			match(T__7);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(128);
				answer_multiple_choice();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
			match(T__8);
			setState(135);
			answer_correct();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Short_answerContext extends ParserRuleContext {
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public List<Answer_correctContext> answer_correct() {
			return getRuleContexts(Answer_correctContext.class);
		}
		public Answer_correctContext answer_correct(int i) {
			return getRuleContext(Answer_correctContext.class,i);
		}
		public Short_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterShort_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitShort_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShort_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Short_answerContext short_answer() throws RecognitionException {
		Short_answerContext _localctx = new Short_answerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_short_answer);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__12);
			setState(138);
			question_text();
			setState(142);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(139);
					answer_correct();
					}
					} 
				}
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericalContext extends ParserRuleContext {
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public Answer_correctContext answer_correct() {
			return getRuleContext(Answer_correctContext.class,0);
		}
		public NumericalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterNumerical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitNumerical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumerical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalContext numerical() throws RecognitionException {
		NumericalContext _localctx = new NumericalContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_numerical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__13);
			setState(146);
			question_text();
			setState(147);
			answer_correct();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Select_missing_wordsContext extends ParserRuleContext {
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public Answer_correctContext answer_correct() {
			return getRuleContext(Answer_correctContext.class,0);
		}
		public List<AnswersContext> answers() {
			return getRuleContexts(AnswersContext.class);
		}
		public AnswersContext answers(int i) {
			return getRuleContext(AnswersContext.class,i);
		}
		public Select_missing_wordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_missing_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterSelect_missing_words(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitSelect_missing_words(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSelect_missing_words(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_missing_wordsContext select_missing_words() throws RecognitionException {
		Select_missing_wordsContext _localctx = new Select_missing_wordsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_select_missing_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__14);
			setState(150);
			match(T__7);
			setState(151);
			question_text();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(152);
				answers();
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(158);
			match(T__8);
			setState(159);
			answer_correct();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class True_false_questionContext extends ParserRuleContext {
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public Answer_correctContext answer_correct() {
			return getRuleContext(Answer_correctContext.class,0);
		}
		public True_false_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterTrue_false_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitTrue_false_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrue_false_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_false_questionContext true_false_question() throws RecognitionException {
		True_false_questionContext _localctx = new True_false_questionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_true_false_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__15);
			setState(162);
			question_text();
			setState(163);
			answer_correct();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Question_textContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public Question_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterQuestion_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitQuestion_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestion_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_textContext question_text() throws RecognitionException {
		Question_textContext _localctx = new Question_textContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_question_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Answer_multiple_choiceContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public Answer_multiple_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer_multiple_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterAnswer_multiple_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitAnswer_multiple_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitAnswer_multiple_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Answer_multiple_choiceContext answer_multiple_choice() throws RecognitionException {
		Answer_multiple_choiceContext _localctx = new Answer_multiple_choiceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_answer_multiple_choice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Answer_correctContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode INT() { return getToken(ExamParser.INT, 0); }
		public Answer_correctContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer_correct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterAnswer_correct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitAnswer_correct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitAnswer_correct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Answer_correctContext answer_correct() throws RecognitionException {
		Answer_correctContext _localctx = new Answer_correctContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_answer_correct);
		int _la;
		try {
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				match(STRING);
				 answer_Correct = _input.getText(_localctx.start, _input.LT(-1)); 
				}
				break;
			case T__16:
			case T__17:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2490368L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				 answer_Correct = _input.getText(_localctx.start, _input.LT(-1)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswersContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public AnswersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterAnswers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitAnswers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitAnswers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswersContext answers() throws RecognitionException {
		AnswersContext _localctx = new AnswersContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_answers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionValueContext extends ParserRuleContext {
		public Token INT;
		public TerminalNode INT() { return getToken(ExamParser.INT, 0); }
		public QuestionValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterQuestionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitQuestionValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestionValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionValueContext questionValue() throws RecognitionException {
		QuestionValueContext _localctx = new QuestionValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_questionValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			((QuestionValueContext)_localctx).INT = match(INT);
			 question_value = (((QuestionValueContext)_localctx).INT!=null?Integer.valueOf(((QuestionValueContext)_localctx).INT.getText()):0); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StudentAnswerContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ExamParser.INT, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public StudentAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_studentAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterStudentAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitStudentAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStudentAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StudentAnswerContext studentAnswer() throws RecognitionException {
		StudentAnswerContext _localctx = new StudentAnswerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_studentAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3538944L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			    student_Answer = _input.getText(_localctx.start, _input.LT(-1));
			    i++;
			    if (student_Answer.equals(answer_Correct)) {
			        totalScore += question_value;
			        System.out.println(i+ ": Correct answer");
			    }
			    else {
			        System.out.println(i+ ": Wrong answer");
			    }
			    totalQuestionsValues += question_value;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EndContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).enterEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener) ((ExamListener)listener).exitEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_end);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_la = _input.LA(1);
			if ( !(_la==T__18 || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			totalScore = (totalScore * 20) / totalQuestionsValues;
			System.out.println("Total Score: " + totalScore + "/20");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0016\u00bb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0004\u00013\b\u0001\u000b\u0001\f\u00014\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0005\u0003;\b\u0003\n\u0003\f\u0003>\t\u0003"+
		"\u0001\u0003\u0005\u0003A\b\u0003\n\u0003\f\u0003D\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0004\u0007T\b\u0007\u000b\u0007\f\u0007U\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b`\b\b\u0001"+
		"\b\u0001\b\u0005\bd\b\b\n\b\f\bg\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0005\to\b\t\n\t\f\tr\t\t\u0001\t\u0001\t\u0001\t\u0005\tw"+
		"\b\t\n\t\f\tz\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u0082\b\n\n\n\f\n\u0085\t\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u008d\b\u000b\n\u000b\f\u000b\u0090\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005"+
		"\r\u009a\b\r\n\r\f\r\u009d\t\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00ae"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0000\u0000\u0016\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*\u0000\u0004\u0001\u0000\u0003"+
		"\u0005\u0002\u0000\u0011\u0012\u0015\u0015\u0002\u0000\u0011\u0012\u0014"+
		"\u0015\u0001\u0000\u0013\u0014\u00b4\u0000,\u0001\u0000\u0000\u0000\u0002"+
		"/\u0001\u0000\u0000\u0000\u00046\u0001\u0000\u0000\u0000\u0006<\u0001"+
		"\u0000\u0000\u0000\bG\u0001\u0000\u0000\u0000\nJ\u0001\u0000\u0000\u0000"+
		"\fM\u0001\u0000\u0000\u0000\u000eP\u0001\u0000\u0000\u0000\u0010_\u0001"+
		"\u0000\u0000\u0000\u0012h\u0001\u0000\u0000\u0000\u0014}\u0001\u0000\u0000"+
		"\u0000\u0016\u0089\u0001\u0000\u0000\u0000\u0018\u0091\u0001\u0000\u0000"+
		"\u0000\u001a\u0095\u0001\u0000\u0000\u0000\u001c\u00a1\u0001\u0000\u0000"+
		"\u0000\u001e\u00a5\u0001\u0000\u0000\u0000 \u00a7\u0001\u0000\u0000\u0000"+
		"\"\u00ad\u0001\u0000\u0000\u0000$\u00af\u0001\u0000\u0000\u0000&\u00b1"+
		"\u0001\u0000\u0000\u0000(\u00b4\u0001\u0000\u0000\u0000*\u00b7\u0001\u0000"+
		"\u0000\u0000,-\u0003\u0002\u0001\u0000-.\u0003*\u0015\u0000.\u0001\u0001"+
		"\u0000\u0000\u0000/0\u0003\u0004\u0002\u000002\u0003\u0006\u0003\u0000"+
		"13\u0003\u000e\u0007\u000021\u0001\u0000\u0000\u000034\u0001\u0000\u0000"+
		"\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u00005\u0003\u0001"+
		"\u0000\u0000\u000067\u0005\u0001\u0000\u000078\u0005\u0014\u0000\u0000"+
		"8\u0005\u0001\u0000\u0000\u00009;\u0003\b\u0004\u0000:9\u0001\u0000\u0000"+
		"\u0000;>\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000<=\u0001\u0000"+
		"\u0000\u0000=B\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000?A\u0003"+
		"\n\u0005\u0000@?\u0001\u0000\u0000\u0000AD\u0001\u0000\u0000\u0000B@\u0001"+
		"\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CE\u0001\u0000\u0000\u0000"+
		"DB\u0001\u0000\u0000\u0000EF\u0003\f\u0006\u0000F\u0007\u0001\u0000\u0000"+
		"\u0000GH\u0005\u0002\u0000\u0000HI\u0007\u0000\u0000\u0000I\t\u0001\u0000"+
		"\u0000\u0000JK\u0005\u0006\u0000\u0000KL\u0007\u0000\u0000\u0000L\u000b"+
		"\u0001\u0000\u0000\u0000MN\u0005\u0007\u0000\u0000NO\u0005\u0014\u0000"+
		"\u0000O\r\u0001\u0000\u0000\u0000PQ\u0005\b\u0000\u0000QS\u0003\f\u0006"+
		"\u0000RT\u0003\u0010\b\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000"+
		"\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000WX\u0005\t\u0000\u0000X\u000f\u0001\u0000\u0000\u0000Y`\u0003"+
		"\u001c\u000e\u0000Z`\u0003\u001a\r\u0000[`\u0003\u0018\f\u0000\\`\u0003"+
		"\u0016\u000b\u0000]`\u0003\u0014\n\u0000^`\u0003\u0012\t\u0000_Y\u0001"+
		"\u0000\u0000\u0000_Z\u0001\u0000\u0000\u0000_[\u0001\u0000\u0000\u0000"+
		"_\\\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_^\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ae\u0003&\u0013\u0000bd\u0003(\u0014\u0000"+
		"cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000"+
		"\u0000ef\u0001\u0000\u0000\u0000f\u0011\u0001\u0000\u0000\u0000ge\u0001"+
		"\u0000\u0000\u0000hi\u0005\n\u0000\u0000ip\u0005\b\u0000\u0000jk\u0003"+
		"\u001e\u000f\u0000kl\u0005\u000b\u0000\u0000lm\u0003\"\u0011\u0000mo\u0001"+
		"\u0000\u0000\u0000nj\u0001\u0000\u0000\u0000or\u0001\u0000\u0000\u0000"+
		"pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000\u0000"+
		"\u0000rp\u0001\u0000\u0000\u0000st\u0005\t\u0000\u0000tx\u0005\b\u0000"+
		"\u0000uw\u0003$\u0012\u0000vu\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000"+
		"\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y{\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000{|\u0005\t\u0000\u0000|\u0013\u0001"+
		"\u0000\u0000\u0000}~\u0005\f\u0000\u0000~\u007f\u0003\u001e\u000f\u0000"+
		"\u007f\u0083\u0005\b\u0000\u0000\u0080\u0082\u0003 \u0010\u0000\u0081"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084"+
		"\u0086\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005\t\u0000\u0000\u0087\u0088\u0003\"\u0011\u0000\u0088\u0015"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0005\r\u0000\u0000\u008a\u008e\u0003"+
		"\u001e\u000f\u0000\u008b\u008d\u0003\"\u0011\u0000\u008c\u008b\u0001\u0000"+
		"\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000"+
		"\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0017\u0001\u0000"+
		"\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u000e"+
		"\u0000\u0000\u0092\u0093\u0003\u001e\u000f\u0000\u0093\u0094\u0003\"\u0011"+
		"\u0000\u0094\u0019\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u000f\u0000"+
		"\u0000\u0096\u0097\u0005\b\u0000\u0000\u0097\u009b\u0003\u001e\u000f\u0000"+
		"\u0098\u009a\u0003$\u0012\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u009a"+
		"\u009d\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0001\u0000\u0000\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d"+
		"\u009b\u0001\u0000\u0000\u0000\u009e\u009f\u0005\t\u0000\u0000\u009f\u00a0"+
		"\u0003\"\u0011\u0000\u00a0\u001b\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005"+
		"\u0010\u0000\u0000\u00a2\u00a3\u0003\u001e\u000f\u0000\u00a3\u00a4\u0003"+
		"\"\u0011\u0000\u00a4\u001d\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005\u0014"+
		"\u0000\u0000\u00a6\u001f\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u0014"+
		"\u0000\u0000\u00a8!\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005\u0014\u0000"+
		"\u0000\u00aa\u00ae\u0006\u0011\uffff\uffff\u0000\u00ab\u00ac\u0007\u0001"+
		"\u0000\u0000\u00ac\u00ae\u0006\u0011\uffff\uffff\u0000\u00ad\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae#\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0005\u0014\u0000\u0000\u00b0%\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0005\u0015\u0000\u0000\u00b2\u00b3\u0006\u0013\uffff"+
		"\uffff\u0000\u00b3\'\u0001\u0000\u0000\u0000\u00b4\u00b5\u0007\u0002\u0000"+
		"\u0000\u00b5\u00b6\u0006\u0014\uffff\uffff\u0000\u00b6)\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0007\u0003\u0000\u0000\u00b8\u00b9\u0006\u0015\uffff"+
		"\uffff\u0000\u00b9+\u0001\u0000\u0000\u0000\f4<BU_epx\u0083\u008e\u009b"+
		"\u00ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}