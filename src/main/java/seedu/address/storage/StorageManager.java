package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of CodeSphere data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final CourseListStorage courseListStorage;
    private final UserPrefsStorage userPrefsStorage;
    private final CommandStorage commandStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CourseListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CourseListStorage courseListStorage, UserPrefsStorage userPrefsStorage,
                          CommandStorage commandStorage) {
        this.courseListStorage = courseListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.commandStorage = commandStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ CourseList methods ==============================

    @Override
    public Path getCourseListFilePath() {
        return courseListStorage.getCourseListFilePath();
    }

    @Override
    public Optional<ReadOnlyCourseList> readCourseList() throws DataLoadingException {
        return readCourseList(courseListStorage.getCourseListFilePath());
    }

    @Override
    public Optional<ReadOnlyCourseList> readCourseList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return courseListStorage.readCourseList(filePath);
    }

    @Override
    public void saveCourseList(ReadOnlyCourseList courseList) throws IOException {
        saveCourseList(courseList, courseListStorage.getCourseListFilePath());
    }

    @Override
    public void saveCourseList(ReadOnlyCourseList courseList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        courseListStorage.saveCourseList(courseList, filePath);
    }

    // ================ commandStorage methods ==============================

    @Override
    public void addInvalidCommand(String text) {
        commandStorage.addCommand(false, text);
    }

    @Override
    public void addValidCommand(String text) {
        commandStorage.addCommand(true, text);
    }

    @Override
    public Pair<Boolean, String> getCommand() {
        return commandStorage.getCommand();
    }

    @Override
    public Pair<Boolean, String> previousCommand() {
        commandStorage.decrementPointer();
        return getCommand();
    }

    @Override
    public Pair<Boolean, String> nextCommand() {
        commandStorage.incrementPointer();
        return getCommand();
    }

}
