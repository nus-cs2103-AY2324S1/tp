package seedu.address.logic.commands.mapping;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.model.mapping.MappingComparatorByAttribute;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all mappings in the MappingCatalogue.
 */
public class MappingSortCommand extends MappingCommand {
    public static final String ACTION_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted mappings by %1$s.";

    private final MappingComparatorByAttribute mappingComparator;

    /**
     * Creates a MappingSortCommand to add the specified {@code mappingComparator}
     *
     * @param mappingComparator Search mappingComparator.
     */
    public MappingSortCommand(MappingComparatorByAttribute mappingComparator) {
        super();
        this.mappingComparator = mappingComparator;
    }

    /**
     * Sorts the mapping list and returns a CommandResult.
     *
     * @param seplendidModel {@code SeplendidModel} which the command should operate on.
     * @return {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        // Inject model functions into mappingComparator
        mappingComparator.initialiseGetLocalPartnerCourse(seplendidModel::getLocalCourseIfExists,
                seplendidModel::getPartnerCourseIfExists);
        seplendidModel.updateSortedMappingList(mappingComparator);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, mappingComparator.getAttribute().toString().toLowerCase()),
                UiUtil.ListViewModel.MAPPING_SORT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MappingSortCommand)) {
            return false;
        }

        MappingSortCommand otherMappingSortCommand = (MappingSortCommand) other;
        return mappingComparator.equals(otherMappingSortCommand.mappingComparator);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mappingComparator", mappingComparator)
                .toString();
    }


}
