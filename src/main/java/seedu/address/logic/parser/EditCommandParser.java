package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_SALARY, PREFIX_LEAVE, PREFIX_ROLE, PREFIX_MANAGER, PREFIX_DEPARTMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_SALARY, PREFIX_LEAVE, PREFIX_ROLE);

        EditCommand.EditEmployeeDescriptor editEmployeeDescriptor = new EditCommand.EditEmployeeDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEmployeeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editEmployeeDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editEmployeeDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editEmployeeDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editEmployeeDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_LEAVE).isPresent()) {
            editEmployeeDescriptor.setLeave(ParserUtil.parseLeave(argMultimap.getValue(PREFIX_LEAVE).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editEmployeeDescriptor.setRole(ParserUtil.parseRole((argMultimap.getValue(PREFIX_ROLE).get())));
        }
        parseDepartmentsForEdit(argMultimap.getAllValues(PREFIX_DEPARTMENT))
                .ifPresent(editEmployeeDescriptor::setDepartments);

        parseSupervisorsForEdit(argMultimap.getAllValues(PREFIX_MANAGER))
                .ifPresent(editEmployeeDescriptor::setSupervisors);

        if (!editEmployeeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEmployeeDescriptor);
    }

    /**
     * Parses {@code Collection<String> supervisors} into a {@code Set<Name>} if
     * {@code supervisors} is non-empty.
     * If {@code supervisors} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Name>} containing zero supervisor names.
     */
    private Optional<Set<EmployeeName>> parseSupervisorsForEdit(Collection<String> supervisors)
            throws ParseException {
        assert supervisors != null;

        if (supervisors.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> nameSet = supervisors.size() == 1 && supervisors.contains("")
                ? Collections.emptySet() : supervisors;
        return Optional.of(ParserUtil.parseSupervisors(nameSet));
    }

    /**
     * Parses {@code Collection<String> departments} into a {@code Set<Department>} if {@code departments} is non-empty.
     * If {@code departments} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Department>} containing zero departments.
     */
    private Optional<Set<DepartmentName>> parseDepartmentsForEdit(Collection<String> departments)
            throws ParseException {
        assert departments != null;

        if (departments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> departmentSet = departments.size() == 1 && departments.contains("")
                ? Collections.emptySet() : departments;
        return Optional.of(ParserUtil.parseDepartments(departmentSet));
    }

}
