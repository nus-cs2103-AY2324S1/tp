package seedu.address.logic.commands.mapping;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingsWithDependencies;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.SeplendidModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MappingCatalogue;
import seedu.address.model.SeplendidModel;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.mapping.MappingAttributeContainsKeywordPredicate;

public class MappingSearchCommandTest {

    private SeplendidModel modelStub;

    @BeforeEach
    public void setUp() {
        modelStub = new ModelStubWithMappings(getTypicalMappingsWithDependencies());
    }

    @Test
    public void execute_validCommand_success() {
        String queryKeyword = "";
        MappingSearchCommand mappingSearchCommand = new MappingSearchCommand(
                new MappingAttributeContainsKeywordPredicate(queryKeyword, MappingAttribute.LOCALCODE));
        SeplendidModel expectedModelStub = new ModelStubWithMappings(getTypicalMappingsWithDependencies());
        expectedModelStub.updateFilteredMappingList(new
                MappingAttributeContainsKeywordPredicate(queryKeyword, MappingAttribute.LOCALCODE));
        try {
            mappingSearchCommand.execute(modelStub);
        } catch (CommandException e) {
            // do nothing
        }
        assertEquals(expectedModelStub.getFilteredMappingList(), modelStub.getFilteredMappingList());
    }

    @Test
    public void equals() {
        MappingAttributeContainsKeywordPredicate firstPredicate = new MappingAttributeContainsKeywordPredicate(
                "CS", MappingAttribute.LOCALCODE);
        MappingAttributeContainsKeywordPredicate secondPredicate = new MappingAttributeContainsKeywordPredicate(
                "CS", MappingAttribute.PARTNERCODE);

        MappingSearchCommand searchCommandWithFirstPredicate = new MappingSearchCommand(firstPredicate);
        MappingSearchCommand searchCommandWithSecondPredicate = new MappingSearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicate));

        // same values -> returns true
        MappingSearchCommand searchCommandWithFirstPredicateCopy = new MappingSearchCommand(firstPredicate);
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicateCopy));

        // different types -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(1));

        // null -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(null));

        // different note -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(searchCommandWithSecondPredicate));
    }

    @Test
    public void toStringMethod() {
        MappingAttributeContainsKeywordPredicate predicate = new
                MappingAttributeContainsKeywordPredicate("CS", MappingAttribute.LOCALCODE);
        MappingSearchCommand mappingSearchCommand = new MappingSearchCommand(predicate);
        String expected = MappingSearchCommand.class.getCanonicalName()
                + "{predicate=" + predicate + "}";
        assertEquals(expected, mappingSearchCommand.toString());
    }

    private class ModelStubWithMappings extends SeplendidModelStub {
        private final MappingCatalogue mappingCatalogue = new MappingCatalogue();

        private FilteredList<Mapping> mappings;

        private ModelStubWithMappings(List<Mapping> mappingsToAdd) {
            requireNonNull(mappingsToAdd);
            mappings = new FilteredList<>(mappingCatalogue.getMappingList());
            mappingCatalogue.setMappings(mappingsToAdd);
        }

        @Override
        public void updateFilteredMappingList(Predicate<Mapping> predicate) {
            requireNonNull(predicate);
            mappings.setPredicate(predicate);
        }

        @Override
        public ObservableList<Mapping> getFilteredMappingList() {
            return mappings;
        }
    }

}
