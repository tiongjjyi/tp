package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.Test;

import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCourseCommandTest {
    @Test
    public void execute_emptyCourseList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCourseCommand(), model, ClearCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCourseList_success() {
        Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalCourseList(), new UserPrefs());
        expectedModel.setCourseList(new CourseList());

        assertCommandSuccess(new ClearCourseCommand(), model, ClearCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
