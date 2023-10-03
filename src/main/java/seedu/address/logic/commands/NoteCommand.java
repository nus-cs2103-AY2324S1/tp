package seedu.address.logic.commands;

import seedu.address.annotation.Nullable;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Note;



/**
 * Sets the note of an existing contact.
 */
public class NoteCommand extends Command {
    /**
     * Every Command subclass exposes a COMMAND_WORD for use in places like the
     * parser.
     */
    public static final String COMMAND_WORD = "note";

    /**
     * Non-trivial Command subclasses usually expose a MESSAGE_USAGE for use in
     * places like the parser.
     */
    public static final String MESSAGE_USAGE = String.format(
        "%s: Sets the note for the contact of the specified number."
        + "\nArguments: [number] o/[your note]."
        + "\nExample: %s 1 o/CS2103 Prof.",
        NoteCommand.COMMAND_WORD,
        NoteCommand.COMMAND_WORD
    );

    public static final String MESSAGE_ARGUMENTS = "index (%d) note (%s)";

    private Index number;
    private Note note;

    /**
     * Constructs a NoteCommand for setting the specified note for the contact
     * with the specified number.
     *
     * @param _number Number of the contact.
     * @param _note Note to set.
     */
    public NoteCommand(Index _number, Note _note) {
        this.number = _number;
        this.note = _note;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        // instanceof also handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }
        NoteCommand otherCommand = (NoteCommand)other;

        if (otherCommand == this) {
            return true;
        }

        return (
            this.number.equals(otherCommand.number)
            && this.note.equals(otherCommand.note)
        );
    }

    @Override
    public CommandResult execute(Model _model) throws CommandException {
        throw new CommandException(
            String.format(
                MESSAGE_ARGUMENTS,
                this.number.getOneBased(),
                this.note.toString()
            )
        );
    }
}
