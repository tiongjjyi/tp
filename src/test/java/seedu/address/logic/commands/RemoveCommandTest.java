package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RemoveCommand.MESSAGE_EDIT_STUDENT_SUCCESS;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.Student;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.StudentBuilder;

public class RemoveCommandTest {

    private static final String REMARK_STUB = "";
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_removeRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark("").build();
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();

        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_STUDENT, studentDescriptor);
        CommandResult commandResult = removeCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_removePendingQuestionUnfilteredList_success() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion("").build();
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();

        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_STUDENT, studentDescriptor);
        CommandResult commandResult = removeCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_removePendingQuestionUnfilteredList_failure() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion("some remarks").build();
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();

        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_STUDENT, studentDescriptor);
        CommandResult commandResult = removeCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }
    @Test
    public void equals() {
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();
        final RemoveCommand standardCommand = new RemoveCommand(INDEX_FIRST_STUDENT,
                studentDescriptor);

        // same values -> returns true
        RemoveCommand commandWithSameValues = new RemoveCommand(INDEX_FIRST_STUDENT,
                studentDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemoveCommand(INDEX_SECOND_STUDENT,
                studentDescriptor)));

    }
}
