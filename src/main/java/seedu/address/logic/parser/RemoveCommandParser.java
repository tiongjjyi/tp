package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REMARK, PREFIX_PENDING_QUESTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_REMARK, PREFIX_PENDING_QUESTION);

        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editStudentDescriptor.setRemark(ParserUtil.parseRemark(""));
        }
        if (argMultimap.getValue(PREFIX_PENDING_QUESTION).isPresent()) {
            editStudentDescriptor.setPendingQuestion(ParserUtil.parsePendingQuestion(""));
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(RemoveCommand.MESSAGE_NOT_EDITED);
        }

        return new RemoveCommand(index, editStudentDescriptor);
    }

}
