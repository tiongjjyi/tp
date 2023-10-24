package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.ReadOnlyStudentList;
import seedu.address.model.CourseList;

/**
 * Represents a storage for {@link CourseList}.
 */
public interface CodeSphereStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCourseListFilePath();

    /**
     * Returns CodeSphere data as a {@link ReadOnlyCourseList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCourseList> readCourseList() throws DataLoadingException;

    /**
     * @see #getCourseListFilePath() ()
     */
    Optional<ReadOnlyCourseList> readCourseList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCourseList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStudentList(ReadOnlyStudentList addressBook) throws IOException;

    /**
     * @see #saveStudentList(ReadOnlyStudentList)
     */
    void saveStudentList(ReadOnlyStudentList addressBook, Path filePath) throws IOException;

}
