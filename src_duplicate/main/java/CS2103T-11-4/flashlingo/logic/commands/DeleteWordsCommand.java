public class DeleteWordsCommand extends Command{
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
      + ": Deletes the word identified by the index number used in the wordlist.\n"
      + "Parameters: INDEX (must be a positive integer)\n"
      + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_WORD_SUCCESS = "Deleted word: %1$s";

    private final Index targetIndex;

    public DeleteWordsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCards> lastShownList = model.getAllWordsList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        FlashCard wordToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteWord(wordToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_WORD_SUCCESS, Messages.format(WordToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("targetIndex", targetIndex)
          .toString();
    }
}