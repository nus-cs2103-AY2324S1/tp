package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.DepartmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.department.Department;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;


/**
 * Parses input arguments and creates a new DepartmentCommand object
 */
public class DepartmentCommandParser implements Parser<DepartmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DepartmentCommand
     * and returns an DepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DepartmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DepartmentCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE, PREFIX_NAME);

        DepartmentCommand.Type action = null;
        DepartmentName departmentName = null;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            departmentName = ParserUtil.parseDepartment(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            action = ParserUtil.parseAction(argMultimap.getValue(PREFIX_TYPE).get());
        }
        requireNonNull(departmentName);
        requireNonNull(action);
        return new DepartmentCommand(new Department(departmentName), action);
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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
