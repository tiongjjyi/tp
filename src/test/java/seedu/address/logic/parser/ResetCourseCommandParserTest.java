package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResetCourseCommand;

public class ResetCourseCommandParserTest {
    private ResetCourseCommandParser parser = new ResetCourseCommandParser();

    @Test
    public void parse_validArgs_returnsResetCommand() {
        String userInput = "";
        ResetCourseCommand expectedCommand = new ResetCourseCommand();
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = "extra_argument";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetCourseCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
