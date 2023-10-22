package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.Model;
import seedu.address.model.person.Remark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

/**
 * Parses special input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkSyntaxHandler {
    public static final String REGEX_KEEP_REMARK = " \\*\\*REMARK\\*\\* ";

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * @param args the string to be parsed
     * @return true if the string contains the special syntax for keeping the remark
     */
    public static boolean IsKeepRemark(String args) {
        requireNonNull(args);
        Pattern pattern = Pattern.compile(REGEX_KEEP_REMARK);
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static Remark generateKeepRemarkCommand(String args, Remark currentRemark) {
        requireNonNull(args);
        String resultString = args.replaceAll(REGEX_KEEP_REMARK, currentRemark.value);
        System.out.println(resultString);
        return new Remark(resultString);
    }

}
