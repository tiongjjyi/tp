package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.PendingQuestion;
import seedu.address.model.person.Student;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalIndexes;

import java.nio.file.Path;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class RemoveCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, new RemoveCommand.EditStudentDescriptor()));
    }

    @Test
    public void constructor_nullStudentDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_studentAcceptedByModel_removeSuccessful() throws CommandException {
        Student validStudent = new StudentBuilder().build();
        Course validCourse = new CourseBuilder().build();
        ModelStubWithCourse modelStub = new ModelStubWithCourse(validCourse, validStudent);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        editStudentDescriptor.setPendingQuestion(new PendingQuestion(""));
        CommandResult commandResult = new RemoveCommand(TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptor).execute(modelStub);

        assertEquals(String.format(RemoveCommand.MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(validStudent)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_studentOutOfIndex_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        Course validCourse = new CourseBuilder().build();
        ModelStubWithCourse modelStub = new ModelStubWithCourse(validCourse,validStudent);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        editStudentDescriptor.setPendingQuestion(new PendingQuestion(""));
        RemoveCommand removeCommand = new RemoveCommand(TypicalIndexes.INDEX_SECOND_STUDENT, editStudentDescriptor);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX,
                () -> removeCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        editStudentDescriptor.setPendingQuestion(new PendingQuestion(""));
        RemoveCommand removeFirst = new RemoveCommand(TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptor);
        RemoveCommand removeSecond = new RemoveCommand(TypicalIndexes.INDEX_SECOND_STUDENT, editStudentDescriptor);

        // same object -> returns true
        assertTrue(removeFirst.equals(removeFirst));

        // same value for Index -> returns true
        RemoveCommand removeFirstCopy = new RemoveCommand(Index.fromZeroBased(0), editStudentDescriptor);
        assertTrue(removeFirst.equals(removeFirstCopy));

        // same value for editStudentDescriptor -> returns true
        RemoveCommand.EditStudentDescriptor editStudentDescriptorCopy = new RemoveCommand.EditStudentDescriptor();
        editStudentDescriptorCopy.setPendingQuestion(new PendingQuestion(""));
        RemoveCommand removeFirstCopyESD = new RemoveCommand(TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptorCopy);
        assertTrue(removeFirst.equals(removeFirstCopyESD));

        // different types -> returns false
        assertFalse(removeFirst.equals(1));

        // null -> returns false
        assertFalse(removeFirst.equals(null));

        // different Index -> returns false
        assertFalse(removeFirst.equals(removeSecond));
    }

    @Test
    public void toStringMethod() {
        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        editStudentDescriptor.setPendingQuestion(new PendingQuestion(""));
        RemoveCommand removeCommand = new RemoveCommand(TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptor);
        String expected = RemoveCommand.class.getCanonicalName() + "{index=" + TypicalIndexes.INDEX_FIRST_STUDENT
                + ", editStudentDescriptor=" + editStudentDescriptor.toString() + "}";

        assertEquals(expected, removeCommand.toString());
    }


    /**
     * A default model stub that have all methods except updateFilteredCourseList methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourseList(ReadOnlyCourseList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCourseList getCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCourse(Course target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourse(Course target, Course editedCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Course> getFilteredCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Remove Command will use this method
         */
        @Override
        public void updateFilteredCourseList(Predicate<Course> predicate) {
        }
    }

    /**
     * A Model stub that contains a single Course that contains a single student.
     */
    private class ModelStubWithCourse extends RemoveCommandTest.ModelStub {
        private final Course course;

        ModelStubWithCourse(Course course, Student student) {
            requireNonNull(course);
            this.course = course;
            course.addStudent(student);
        }
    }
}
