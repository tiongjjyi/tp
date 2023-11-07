package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalCourses.getTypicalCourses;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ResetCommand.
 */
public class ResetCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    void execute_resetCommand_success() {
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new ResetCommand().execute(model);
        String expectedMessage = ResetCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }
}
