package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.SellerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newSeller_success() {
        Seller validSeller = new SellerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addSeller(validSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(new AddSellerCommand(validSeller), model,
                String.format(AddSellerCommand.MESSAGE_SUCCESS, Messages.format(validSeller)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Seller sellerInList = model.getAddressBook().getSellerList().get(0);
        assertCommandFailure(new AddSellerCommand(sellerInList), model,
                AddSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }

}
