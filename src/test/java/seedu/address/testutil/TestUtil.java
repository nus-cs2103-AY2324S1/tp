package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

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
     * Returns the middle index of the buyer in the {@code model}'s buyer list.
     */
    public static Index getBuyerMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredBuyerList().size() / 2);
    }

    /**
     * Returns the middle index of the seller in the {@code model}'s seller list.
     */
    public static Index getSellerMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredSellerList().size() / 2);
    }

    /**
     * Returns the last index of the buyer in the {@code model}'s buyer list.
     */
    public static Index getBuyerLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredBuyerList().size());
    }

    /**
     * Returns the last index of the seller in the {@code model}'s seller list.
     */
    public static Index getSellerLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredSellerList().size());
    }

    /**
     * Returns the buyer in the {@code model}'s buyer list at {@code index}.
     */
    public static Buyer getBuyer(Model model, Index index) {
        return model.getFilteredBuyerList().get(index.getZeroBased());
    }

    /**
     * Returns the seller in the {@code model}'s seller list at {@code index}.
     */
    public static Seller getSeller(Model model, Index index) {
        return model.getFilteredSellerList().get(index.getZeroBased());
    }
}
