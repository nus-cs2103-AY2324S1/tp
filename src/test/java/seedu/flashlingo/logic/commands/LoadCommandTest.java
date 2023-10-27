package seedu.flashlingo.logic.commands;

import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.AMY;
import static seedu.flashlingo.testutil.TypicalFlashCards.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

public class LoadCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LoadCommandTest");
    private static final String MESSAGE_OPEN_FILE_FAIL = "File not found or accessible.";
    private static final String MESSAGE_READ_FILE_FAIL = "File cannot be read due to incorrect format.";
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    private Path addToTestDataPathIfNotNull(String dataFileInTestDataFolder) {
        return dataFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(dataFileInTestDataFolder)
                : null;
    }

    @Test
    public void execute_invalidFileName_throwsCommandException() {
        assertCommandFailure(new LoadCommand("invalidFile.json"), model,
                MESSAGE_OPEN_FILE_FAIL);
    }

    @Test
    public void execute_invalidAndValidDataFormat_throwsCommandException() {
        Path path = addToTestDataPathIfNotNull("invalidAndValidDataFormat.xlsx");
        assertCommandFailure(new LoadCommand(path.toString()),
                model, MESSAGE_READ_FILE_FAIL);
    }

    @Test
    public void execute_duplicateFlashCard_throwsCommandException() {
        model.addFlashCard(AMY);
        Path path = addToTestDataPathIfNotNull("duplicateFlashCard.xlsx");
        assertCommandFailure(new LoadCommand(path.toString()),
                model, AMY.getOriginalWord().getWord() + "-" + AMY.getTranslatedWord().getWord()
                        + LoadCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_validDataFile_success() {
        expectedModel.addFlashCard(AMY);
        expectedModel.addFlashCard(BOB);
        Path path = addToTestDataPathIfNotNull("validDataFile.xlsx");
        assertCommandSuccess(new LoadCommand(path.toString()),
                model, LoadCommand.MESSAGE_SUCCESS + TEST_DATA_FOLDER + "/validDataFile.xlsx",
                expectedModel);
    }

}
