package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalCourses.CS1101S;
import static seedu.address.testutil.TypicalCourses.CS1231S;
import static seedu.address.testutil.TypicalCourses.CS2030S;
import static seedu.address.testutil.TypicalCourses.CS2040S;
import static seedu.address.testutil.TypicalCourses.CS3230;
import static seedu.address.testutil.TypicalCourses.getTypicalCourses;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Student;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalStudents;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    public static List<Course> activateStudent0() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS1101S.addStudent(typicalStudents.get(0));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent1() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS1231S.addStudent(typicalStudents.get(0));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent2() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS2030S.addStudent(typicalStudents.get(0));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent3() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS2040S.addStudent(typicalStudents.get(2));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent4() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS3230.addStudent(typicalStudents.get(1));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }


    @Test
    public void execute_addRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse);

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(REMARK_STUB));
        CommandResult commandResult = remarkCommand.execute(model);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = getTypicalCourses().get(1);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse);


        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(""));
        CommandResult commandResult = remarkCommand.execute(model);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_filteredList_success() {
        Course validCourse = getTypicalCourses().get(1);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse);

        showStudentAtIndex(validCourse, INDEX_FIRST_STUDENT);

        Student firstPerson = validCourse.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(validCourse.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(REMARK_STUB));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);


        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        validCourse.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Course validCourse0 = activateStudent0().get(0);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse0);
        Index outOfBoundIndex = Index.fromOneBased(validCourse0.getFilteredStudentList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        Course validCourse4 = activateStudent4().get(4);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse4);
        showStudentAtIndex(validCourse4, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertFalse(outOfBoundIndex.getZeroBased() < validCourse4.getStudentList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
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
