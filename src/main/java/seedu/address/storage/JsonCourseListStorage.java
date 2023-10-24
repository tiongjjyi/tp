package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyCourseList;

/**
 * A class to access CodeSphere data stored as a json file on the hard disk.
 */
public class JsonCourseListStorage implements CourseListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCourseListStorage.class);

    private Path filePath;

    public JsonCourseListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCourseListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCourseList> readCourseList() throws DataLoadingException {
        return readCourseList(filePath);
    }

    /**
     * Similar to {@link #readCourseList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyCourseList> readCourseList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableCourseList> jsonCourseList = JsonUtil.readJsonFile(
                filePath, JsonSerializableCourseList.class);
        if (!jsonCourseList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCourseList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCourseList(ReadOnlyCourseList courseList) throws IOException {
        saveCourseList(courseList, filePath);
    }

    /**
     * Similar to {@link #saveCourseList(ReadOnlyCourseList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCourseList(ReadOnlyCourseList courseList, Path filePath) throws IOException {
        requireNonNull(courseList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCourseList(courseList), filePath);
    }

}
