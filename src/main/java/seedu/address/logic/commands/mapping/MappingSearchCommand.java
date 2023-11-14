package seedu.address.logic.commands.mapping;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.model.mapping.MappingAttributeContainsKeywordPredicate;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all mappings in the MappingCatalogue.
 */
public class MappingSearchCommand extends MappingCommand {
    public static final String ACTION_WORD = "search";

    public static final String MESSAGE_SUCCESS = "Searched mappings where %1$s contains %2$s.";

    private final MappingAttributeContainsKeywordPredicate predicate;

    /**
     * Creates a MappingSearchCommand to add the specified {@code predicate}
     *
     * @param predicate Search predicate.
     */
    public MappingSearchCommand(MappingAttributeContainsKeywordPredicate predicate) {
        super();
        this.predicate = predicate;
    }

    /**
     * Searches the mapping list and returns a CommandResult.
     *
     * @param seplendidModel {@code SeplendidModel} which the command should operate on.
     * @return {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        // Inject model functions into predicate
        predicate.initialiseGetLocalPartnercourse(seplendidModel::getLocalCourseIfExists,
                seplendidModel::getPartnerCourseIfExists);
        seplendidModel.updateFilteredMappingList(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, predicate.getAttribute().toString().toLowerCase(),
                predicate.getKeyword()),
                UiUtil.ListViewModel.MAPPING_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MappingSearchCommand)) {
            return false;
        }

        MappingSearchCommand otherMappingSearchCommand = (MappingSearchCommand) other;
        return predicate.equals(otherMappingSearchCommand.predicate);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }


}
