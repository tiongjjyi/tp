package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.stagemanager.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Student;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());
    @Test
    public void execute_addRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(REMARK_STUB));
        CommandResult commandResult = remarkCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_ADD_REMARK_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_failure() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(""));
        CommandResult commandResult = remarkCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_ADD_REMARK_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_AMY));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_STUDENT,
                new Remark(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_BOB))));
    }
}
