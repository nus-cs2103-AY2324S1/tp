package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_UNIVERSITY_NAME;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.localcourse.LocalCourseAddCommand;
import seedu.address.logic.commands.localcourse.LocalCourseCommand;
import seedu.address.logic.commands.localcourse.LocalCourseListCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseAddCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.ReadOnlyNoteCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.university.University;
import seedu.address.storage.JsonLocalCourseCatalogueStorage;
import seedu.address.storage.JsonMappingCatalogueStorage;
import seedu.address.storage.JsonNoteCatalogueStorage;
import seedu.address.storage.JsonPartnerCourseCatalogueStorage;
import seedu.address.storage.JsonUniversityCatalogueStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class SeplendidLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private SeplendidModel model = new SeplendidModelManager();
    private SeplendidLogic logic;

    @BeforeEach
    public void setUp() {
        JsonLocalCourseCatalogueStorage localCourseCatalogueStorage = new JsonLocalCourseCatalogueStorage(
            temporaryFolder.resolve("localcoursecatalogue.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonPartnerCourseCatalogueStorage partnerCourseCatalogueStorage = new JsonPartnerCourseCatalogueStorage(
            temporaryFolder.resolve("partnercoursecatalogue.json"));
        JsonUniversityCatalogueStorage universityCatalogueStorage =
            new JsonUniversityCatalogueStorage(temporaryFolder.resolve("universitycatalogue"));
        JsonNoteCatalogueStorage noteCatalogueStorage = new JsonNoteCatalogueStorage(temporaryFolder.resolve(
            "notecatalogue"));
        JsonMappingCatalogueStorage mappingCatalogueStorage = new JsonMappingCatalogueStorage(temporaryFolder.resolve(
            "mappingcatalogue.json"));
        StorageManager storage = new StorageManager(
            userPrefsStorage,
            localCourseCatalogueStorage,
            partnerCourseCatalogueStorage,
            universityCatalogueStorage,
            mappingCatalogueStorage,
            noteCatalogueStorage);
        model.addUniversity(new University(TYPICAL_PARTNER_UNIVERSITY_NAME));
        logic = new SeplendidLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand,
                             UsageMessage.HELP.toString());
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String localCourseListCommand = LocalCourseCommand.COMMAND_WORD + " " + LocalCourseListCommand.ACTION_WORD;
        assertCommandSuccess(localCourseListCommand, LocalCourseListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(
            DUMMY_IO_EXCEPTION,
            String.format(SeplendidLogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(
            DUMMY_AD_EXCEPTION,
            String.format(SeplendidLogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredLocalCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredLocalCourseCatalogue().remove(0));
    }

    @Test
    public void getFilteredPartnerCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPartnerCourseCatalogue().remove(0));
    }

    @Test
    public void getFilteredUniversityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredUniversityCatalogue().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, SeplendidModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, SeplendidModel expectedModel)
            throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, SeplendidModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, SeplendidModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, SeplendidModel)
     */
    private void assertCommandFailure(
        String inputCommand, Class<? extends Throwable> expectedException, String expectedMessage) {
        SeplendidModel expectedModel = new SeplendidModelManager(
            new UserPrefs(),
            model.getLocalCourseCatalogue(),
            model.getPartnerCourseCatalogue(),
            model.getUniversityCatalogue(),
            model.getMappingCatalogue(),
            model.getNoteCatalogue());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, SeplendidModel)
     */
    private void assertCommandFailure(
        String inputCommand,
        Class<? extends Throwable> expectedException,
        String expectedMessage,
        SeplendidModel expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e               the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        JsonLocalCourseCatalogueStorage localCourseCatalogueStorage = new JsonLocalCourseCatalogueStorage(prefPath) {
            @Override
            public void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonPartnerCourseCatalogueStorage partnerCourseCatalogueStorage =
            new JsonPartnerCourseCatalogueStorage(prefPath) {
                @Override
                public void savePartnerCourseCatalogue(
                    ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue, Path filePath) throws IOException {
                    throw e;
                }
            };

        JsonUniversityCatalogueStorage universityCatalogueStorage = new JsonUniversityCatalogueStorage(prefPath) {
            @Override
            public void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonNoteCatalogueStorage noteCatalogueStorage = new JsonNoteCatalogueStorage(prefPath) {
            @Override
            public void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue, Path filePath) throws IOException {
                throw e;
            }
        };

        JsonMappingCatalogueStorage mappingCatalogueStorage = new JsonMappingCatalogueStorage(prefPath) {
            @Override
            public void saveMappingCatalogue(ReadOnlyMappingCatalogue noteCatalogue, Path filePath) throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve(
            "ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(
            userPrefsStorage,
            localCourseCatalogueStorage,
            partnerCourseCatalogueStorage,
            universityCatalogueStorage,
            mappingCatalogueStorage,
            noteCatalogueStorage);

        logic = new SeplendidLogicManager(model, storage);

        // Triggers the saveLocalCourseCatalogue method by executing an add command
        SeplendidModelManager expectedModel = new SeplendidModelManager();

        String localCourseAddCommand = String.format(
            "%s %s [%s] [%s] [%s] [%s]",
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseAddCommand.ACTION_WORD,
            TYPICAL_LOCAL_COURSE_CODE,
            TYPICAL_LOCAL_COURSE_NAME,
            TYPICAL_LOCAL_COURSE_UNIT,
            TYPICAL_LOCAL_COURSE_DESCRIPTION);
        expectedModel.addLocalCourse(TYPICAL_LOCAL_COURSE);
        assertCommandFailure(localCourseAddCommand, CommandException.class, expectedMessage, expectedModel);

        // Triggers the savePartnerCourseCatalogue method by executing an add command
        String partnerCourseAddCommand = String.format(
            "%s %s [%s] [%s] [%s] [%s] [%s]",
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseAddCommand.ACTION_WORD,
            TYPICAL_PARTNER_UNIVERSITY_NAME,
            TYPICAL_PARTNER_COURSE_CODE,
            TYPICAL_PARTNER_COURSE_NAME,
            TYPICAL_PARTNER_COURSE_UNIT,
            TYPICAL_PARTNER_COURSE_DESCRIPTION);
        expectedModel.addUniversity(new University(TYPICAL_PARTNER_UNIVERSITY_NAME));
        expectedModel.addPartnerCourse(TYPICAL_PARTNER_COURSE);
        assertCommandFailure(partnerCourseAddCommand, CommandException.class, expectedMessage, expectedModel);
    }
    // TBD: Refer to LogicManagerTest to include more appropriate tests after adding future commands
}
