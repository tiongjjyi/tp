package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class StudentListTest {

    private final StudentList StudentList = new StudentList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), StudentList.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StudentList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStudentList_replacesData() {
        StudentList newData = getTypicalStudentList();
        StudentList.resetData(newData);
        assertEquals(newData, StudentList);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two Students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        StudentListStub newData = new StudentListStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> StudentList.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StudentList.hasStudent(null));
    }

    @Test
    public void hasStudent_StudentNotInStudentList_returnsFalse() {
        assertFalse(StudentList.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_StudentInStudentList_returnsTrue() {
        StudentList.addStudent(ALICE);
        assertTrue(StudentList.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_StudentWithSameIdentityFieldsInStudentList_returnsTrue() {
        StudentList.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(StudentList.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> StudentList.getStudentList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = StudentList.class.getCanonicalName() + "{Students=" + StudentList.getStudentList() + "}";
        assertEquals(expected, StudentList.toString());
    }

    /**
     * A stub ReadOnlyStudentList whose Students list can violate interface constraints.
     */
    private static class StudentListStub implements ReadOnlyStudentList {
        private final ObservableList<Student> Students = FXCollections.observableArrayList();

        StudentListStub(Collection<Student> Students) {
            this.Students.setAll(Students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return Students;
        }
    }

}
