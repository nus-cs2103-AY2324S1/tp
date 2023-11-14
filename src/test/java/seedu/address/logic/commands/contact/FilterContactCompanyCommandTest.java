package seedu.address.logic.commands.contact;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.CompanyContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterContactCompanyCommand}.
 */
public class FilterContactCompanyCommandTest {

    @Test
    public void filterWithNull_companyExists_successful() {

    }

    @Test
    public void filter_fullNameAndCompanyDoesNotExist_successful() {

    }

    @Test
    public void equals_companyDoesNotExist_null() {

    }

    /**
     * Parses {@code userInput} into a {@code CompanyContainsKeywordsPredicate}.
     */
    private CompanyContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CompanyContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
