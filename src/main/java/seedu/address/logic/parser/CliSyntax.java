package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_STUDENT_NUMBER = new Prefix("s/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_GPA = new Prefix("g/");
    public static final Prefix PREFIX_PREVIOUS_GRADE = new Prefix("pg/");
    public static final Prefix PREFIX_INTERVIEW_SCORE = new Prefix("is/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_FILE = new Prefix("f/");
    public static final Prefix PREFIX_COMMENT = new Prefix("c/");
    public static final String FIELD_GPA = "gpa";
    public static final String FIELD_PREVIOUS_GRADE = "previousGrade";
    public static final String FIELD_INTERVIEW_SCORE = "interviewScore";
    public static final String FIELD_STUDENT_NUMBER = "studentNo";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_COMMENT = "comment";
    public static final String FIELD_TAGS = "tags";
    public static final String FIELD_HIDDEN = "hidden";
    public static final String FIELD_BOOKMARKED = "bookmarked";
}
