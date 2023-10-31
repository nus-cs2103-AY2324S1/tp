package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the member in the {@code model}'s member list.
     */
    public static Index getMidIndexMember(Model model) {
        return Index.fromOneBased(model.getFilteredMemberList().size() / 2);
    }

    /**
     * Returns the middle index of the applicant in the {@code model}'s applicant list.
     */
    public static Index getMidIndexApplicant(Model model) {
        return Index.fromOneBased(model.getFilteredApplicantList().size() / 2);
    }

    /**
     * Returns the last index of the member in the {@code model}'s member list.
     */
    public static Index getLastIndexMember(Model model) {
        return Index.fromOneBased(model.getFilteredMemberList().size());
    }

    /**
     * Returns the last index of the applicant in the {@code model}'s applicant list.
     */
    public static Index getLastIndexApplicant(Model model) {
        return Index.fromOneBased(model.getFilteredApplicantList().size());
    }

    /**
     * Returns the member in the {@code model}'s member list at {@code index}.
     */
    public static Person getMember(Model model, Index index) {
        return model.getFilteredMemberList().get(index.getZeroBased());
    }

    /**
     * Returns the applicant in the {@code model}'s applicant list at {@code index}.
     */
    public static Person getApplicant(Model model, Index index) {
        return model.getFilteredApplicantList().get(index.getZeroBased());
    }
}
