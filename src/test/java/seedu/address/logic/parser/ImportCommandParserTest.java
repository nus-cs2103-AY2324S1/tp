package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.model.person.Student;

class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();
    @Test
    void parseCsvSuccess() {
        // Create a relative path to the ImportDataTest directory
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");

        String fileName1 = relativePath + "\\" + "student_data_test_success.csv";
        List<Student> expectedList1 = new ArrayList<>();
        expectedList1.add(AMY);
        expectedList1.add(BOB);

        assertParseSuccess(parser, fileName1, new ImportCommand(expectedList1, fileName1));

        String fileName2 = relativePath + "\\" + "student_data_test_success_enrol_date.csv";
        List<Student> expectedList2 = new ArrayList<>();
        expectedList2.add(AMY);
        expectedList2.add(BOB);

        assertParseSuccess(parser, fileName2, new ImportCommand(expectedList2, fileName2));
    }

    @Test
    void parseCsvFail() {
        // Create a relative path to the ImportDataTest directory
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            "Header Error!\n" + ImportCommand.MESSAGE_USAGE);

        String fileName1 = relativePath + "\\" + "student_data_test_fail_wrong_column.csv";
        assertParseFailure(parser, fileName1, expectedMessage);

        String fileName2 = relativePath + "\\" + "student_data_test_fail_missing_attributes.csv";
        assertParseFailure(parser, fileName2, expectedMessage);

        String fileName3 = relativePath + "\\" + "student_data_test_fail_empty.csv";
        assertParseFailure(parser, fileName3, expectedMessage);

        String fileName4 = relativePath + "\\" + "student_data_test_fail_wrong_extra_attributes.csv";
        assertParseFailure(parser, fileName4, expectedMessage);
    }

}
