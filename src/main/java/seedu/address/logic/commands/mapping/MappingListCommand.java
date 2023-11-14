package seedu.address.logic.commands.mapping;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all mappings in the MappingCatalogue.
 */
public class MappingListCommand extends MappingCommand {
    public static final String ACTION_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all mappings";

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateFilteredMappingList(SeplendidModel.PREDICATE_SHOW_ALL_MAPPINGS);
        return new CommandResult(MESSAGE_SUCCESS,
                UiUtil.ListViewModel.MAPPING_LIST);
    }

}
