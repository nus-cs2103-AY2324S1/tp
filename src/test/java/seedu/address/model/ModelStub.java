package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addMember(Member member) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addApplicant(Applicant applicant) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasMember(Member member) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMember(Member target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteApplicant(Applicant target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMember(Member target, Member editedMember) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Member> getFilteredMemberList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredMemberList(Predicate<? super Member> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredApplicantList(Predicate<? super Applicant> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
