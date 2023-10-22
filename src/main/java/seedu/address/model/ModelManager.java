package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.course.Course;
import seedu.address.model.person.Student;
/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentList studentList;
    private final CourseList courseList;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Course> filteredCourses;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyStudentList addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.studentList = new StudentList(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studentList.getStudentList());

        this.courseList = new CourseList();
        filteredCourses = new FilteredList<>(this.courseList.getCourseList());
    }

    public ModelManager() {
        this(new StudentList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getStudentListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setStudentList(ReadOnlyStudentList addressBook) {
        this.studentList.resetData(addressBook);
    }

    @Override
    public ReadOnlyStudentList getStudentList() {
        return studentList;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studentList.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        studentList.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        studentList.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        studentList.setStudent(target, editedStudent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return studentList.equals(otherModelManager.studentList)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }

    //=========== CodeSphere ================================================================================

    @Override
    public void setCourseList(ReadOnlyCourseList addressBook) {
        this.courseList.resetData(addressBook);
    }

    @Override
    public ReadOnlyCourseList getCourseList() {
        return courseList;
    }

    @Override
    public boolean hasCourse(Course Course) {
        requireNonNull(Course);
        return courseList.hasCourse(Course);
    }

    @Override
    public void deleteCourse(Course target) {
        courseList.removeCourse(target);
    }

    @Override
    public void addCourse(Course Course) {
        courseList.addCourse(Course);
        updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
    }

    @Override
    public void setCourse(Course target, Course editedCourse) {
        requireAllNonNull(target, editedCourse);

        courseList.setCourse(target, editedCourse);
    }

    //=========== Filtered Course List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Course} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Course> getFilteredCourseList() {
        return filteredCourses;
    }

    @Override
    public void updateFilteredCourseList(Predicate<Course> predicate) {
        requireNonNull(predicate);
        filteredCourses.setPredicate(predicate);
    }

    // @Override
    // public boolean equals(Object other) {
    //     if (other == this) {
    //         return true;
    //     }

    //     // instanceof handles nulls
    //     if (!(other instanceof ModelManager)) {
    //         return false;
    //     }

    //     ModelManager otherModelManager = (ModelManager) other;
    //     return courseList.equals(otherModelManager.courseList)
    //             && userPrefs.equals(otherModelManager.userPrefs)
    //             && filteredCourses.equals(otherModelManager.filteredCourses);
    // }
}
