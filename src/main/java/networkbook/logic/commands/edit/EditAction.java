package networkbook.logic.commands.edit;

@FunctionalInterface
public interface EditAction {
    void edit(EditPersonDescriptor editPersonDescriptor);
}
