package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.Field;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SortCriteria;
import seedu.address.testutil.SortCriteriaBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalCourses.getTypicalCourses;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

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

    @Test
    public void equals() {
        final ResetCommand standardCommand = new ResetCommand();

        // same values -> returns true
        ResetCommand commandWithSameValues = new ResetCommand();
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
    }
}
