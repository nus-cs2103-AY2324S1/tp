package seedu.address.logic.commands.mapping;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;
import seedu.address.seplendidui.UiUtil;

/**
 * Deletes a mapping identified using its local code, university and partner code.
 */
public class MappingDeleteCommand extends MappingCommand {
    public static final String ACTION_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Deleted Mapping: %1$s";
    public static final String MESSAGE_NONEXISTENT_MAPPING = "This course mapping does not exist in SEPlendid.";

    private final LocalCode localCodeOfMapping;
    private final PartnerCode partnerCodeOfMapping;
    private final UniversityName universityNameOfMapping;

    /**
     * Creates a LocalCourseDeleteCommand to delete the specified primary key value.
     *
     * @param localCode      The localCode that partially identifies the mapping to be deleted.
     * @param universityName The university that partially identifies the mapping to be deleted.
     * @param partnerCode    The partnerCode that partially identifies the mapping to be deleted.
     */
    public MappingDeleteCommand(LocalCode localCode, UniversityName universityName, PartnerCode partnerCode) {
        requireAllNonNull(localCode, universityName, partnerCode);
        localCodeOfMapping = localCode;
        partnerCodeOfMapping = partnerCode;
        universityNameOfMapping = universityName;
    }
    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        // There should be no duplicates
        Optional<Mapping> mappingToDelete = seplendidModel.getMappingIfExists(localCodeOfMapping,
                universityNameOfMapping, partnerCodeOfMapping);
        if (mappingToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_MAPPING);
        }
        mappingToDelete.ifPresent(seplendidModel::deleteMapping);
        // At this point we can already assume mappingToDelete is not empty.
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(mappingToDelete.get())),
                UiUtil.ListViewModel.MAPPING_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MappingDeleteCommand)) {
            return false;
        }

        MappingDeleteCommand otherMappingDeleteCommand = (MappingDeleteCommand) other;
        return localCodeOfMapping.equals(otherMappingDeleteCommand.localCodeOfMapping)
                && partnerCodeOfMapping.equals(otherMappingDeleteCommand.partnerCodeOfMapping)
                && universityNameOfMapping.equals(otherMappingDeleteCommand.universityNameOfMapping);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("localCodeToDelete", localCodeOfMapping)
                .add("universityNameToDelete", universityNameOfMapping)
                .add("partnerCodeToDelete", partnerCodeOfMapping)
                .toString();
    }
}
