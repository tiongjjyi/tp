package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;

/**
 * Parses input arguments and creates a new {@code AttendanceCommand} object.
 */
public class AttendanceCommandParser implements Parser<AttendanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AttendanceCommand}
     * and returns an {@code AttendanceCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE), pe);
        }

        String attendanceStatus = argMultimap.getValue(PREFIX_ATTENDANCE).orElse("");
        Attendance isPresent = ParserUtil.parseAttendance(attendanceStatus);

        return new AttendanceCommand(index, new Attendance(attendanceStatus.equals("present") ? true : false));
    }
}
