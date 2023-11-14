package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperties.AQUAVISTA;
import static seedu.address.testutil.TypicalProperties.SKYVISTA;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FilterPropertyCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Price;
import seedu.address.model.property.PriceAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;

public class FilterPropertyCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        String smallPriceString = "100000";
        String bigPriceString = "1000000000";

        String firstTagString = "sunny";
        String secondTagString = "bright";

        String filterFirstPropertyCommandString = FilterPropertyCommand.COMMAND_WORD + " "
                + PREFIX_PRICE + smallPriceString + " " + PREFIX_TAG + firstTagString;
        String filterSecondPropertyCommandString = FilterPropertyCommand.COMMAND_WORD + " "
                + PREFIX_PRICE + bigPriceString + " " + PREFIX_TAG + secondTagString;

        FilterPropertyCommand filterFirstPropertyCommand = preparePredicate(filterFirstPropertyCommandString);
        FilterPropertyCommand filterSecondPropertyCommand = preparePredicate(filterSecondPropertyCommandString);

        // same object -> returns true
        assertTrue(filterFirstPropertyCommand.equals(filterFirstPropertyCommand));

        // same values -> returns true
        FilterPropertyCommand filterFirstPropertyCommandCopy = preparePredicate(filterFirstPropertyCommandString);
        assertTrue(filterFirstPropertyCommand.equals(filterFirstPropertyCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstPropertyCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstPropertyCommand.equals(null));

        // different property -> returns false
        assertFalse(filterFirstPropertyCommand.equals(filterSecondPropertyCommand));
    }

    @Test
    public void execute_noPriceSingleTag_noPropertyFound() {
        // no property found
        String notFoundTagString = "notfound";
        Tag notFoundTag = new Tag(notFoundTagString);

        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(null, notFoundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_TAG + notFoundTagString;
        FilterPropertyCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredPropertyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void execute_noPriceSingleTag_propertyFound() {
        // properties found
        String foundTagString = "pink";
        Tag foundTag = new Tag(foundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(null, foundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_TAG + foundTagString;
        FilterPropertyCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AQUAVISTA), model.getFilteredPropertyList());
    }

    @Test
    public void execute_noPricveSomeTags_noPropertyFound() {
        // no properrty found
        String notFoundTagString = "notfound";
        String otherNotFoundString = "othernotfound";

        Tag notFoundTag = new Tag(notFoundTagString);
        Tag otherNotFoundTag = new Tag(otherNotFoundString);

        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);
        notFoundTags.add(otherNotFoundTag);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(null, notFoundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_TAG + notFoundTagString + " "
                + PREFIX_TAG + otherNotFoundString;
        FilterPropertyCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredPropertyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void execute_noPriceSomeTags_propertyFound() {
        // properties found
        String foundTagString = "square";
        String otherFoundTagString = "garden";

        Tag foundTag = new Tag(foundTagString);
        Tag otherFoundTag = new Tag(otherFoundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);
        foundTags.add(otherFoundTag);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(null, foundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_TAG + foundTagString + " "
                + PREFIX_TAG + otherFoundTagString;
        FilterPropertyCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SKYVISTA), model.getFilteredPropertyList());
    }

    @Test
    public void execute_withPriceSingleTag_noPropertyFound() {
        // no property found
        String notFoundTagString = "notfound";
        Tag notFoundTag = new Tag(notFoundTagString);


        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);

        String priceString = "100000";
        Price price = new Price(priceString);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(price, notFoundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_PRICE + priceString + " "
                + PREFIX_TAG + notFoundTagString;
        FilterPropertyCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredPropertyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void execute_withPricetSingleTag_propertyFound() {
        // properties found
        String foundTagString = "pink";
        Tag foundTag = new Tag(foundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);

        String priceString = "100000000";
        Price price = new Price(priceString);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(price, foundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_PRICE + priceString + " "
                + PREFIX_TAG + foundTagString;
        FilterPropertyCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AQUAVISTA), model.getFilteredPropertyList());
    }

    @Test
    public void execute_withPriceSomeTags_noPropertyFound() {
        // no property found
        String notFoundTagString = "notfound";
        String otherNotFoundString = "othernotfound";

        Tag notFoundTag = new Tag(notFoundTagString);
        Tag otherNotFoundTag = new Tag(otherNotFoundString);

        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);
        notFoundTags.add(otherNotFoundTag);

        String priceString = "10000";
        Price price = new Price(priceString);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(price, notFoundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_PRICE + priceString + " "
                + PREFIX_TAG + notFoundTagString + " " + PREFIX_TAG + otherNotFoundString;
        FilterPropertyCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredPropertyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void execute_withPriceSomeTags_propertyFound() {
        // properties found
        String foundTagString = "square";
        String otherFoundTagString = "garden";

        Tag foundTag = new Tag(foundTagString);
        Tag otherFoundTag = new Tag(otherFoundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);
        foundTags.add(otherFoundTag);

        String priceString = "100000000";
        Price price = new Price(priceString);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(price, foundTags);
        String commandString = FilterPropertyCommand.COMMAND_WORD + " " + PREFIX_TAG + foundTagString + " "
                + PREFIX_TAG + otherFoundTagString;
        FilterPropertyCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SKYVISTA), model.getFilteredPropertyList());
    }

    @Test
    public void toStringMethod() {
        String firstTagString = "square";
        String secondTagString = "garden";

        Tag firstTag = new Tag(firstTagString);
        Tag secondTag = new Tag(secondTagString);

        Set<Tag> tags = new HashSet<>();
        tags.add(firstTag);
        tags.add(secondTag);

        String priceString = "100000000";
        Price price = new Price(priceString);

        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(price, tags);
        FilterPropertyCommand filterPropertyCommand = new FilterPropertyCommand(predicate);
        String expected = FilterPropertyCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterPropertyCommand.toString());
    }

    private FilterPropertyCommand preparePredicate(String message) {
        try {
            return (new FilterPropertyCommandParser()).parse(message);
        } catch (ParseException e) {
            assert false;
            return null;
        }
    }
}
