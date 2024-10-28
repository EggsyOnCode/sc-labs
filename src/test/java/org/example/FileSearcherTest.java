package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.lang.reflect.Executable;
import java.util.List;

public class FileSearcherTest {

    private static final String TEST_DIR = "/home/xen/Temp";

    @Test
    public void testFileFoundCaseSensitive() {
        List<String> result = FileSearcher.searchFile(new File(TEST_DIR), "task1.c", true);
        assertFalse(result.isEmpty(), "The file should be found in case-sensitive mode.");
    }

    @Test
    public void testFileFoundCaseInsensitive() {
        List<String> result = FileSearcher.searchFile(new File(TEST_DIR), "TASK1.C", false);
        assertFalse(result.isEmpty(), "The file should be found in case-insensitive mode.");
    }

    @Test
    public void testFileNotFound() {
        List<String> result = FileSearcher.searchFile(new File(TEST_DIR), "non_existent_file.c", true);
        assertTrue(result.isEmpty(), "The file should not be found if it does not exist.");
    }

    @Test
    public void testInvalidDirectory() {
        List<String> result = FileSearcher.searchFile(new File("/invalid/dir"), "task1.c", true);
        assertTrue(result.isEmpty(), "An invalid directory should return no results.");
    }

    @Test
    public void testMultipleOccurrences() {
        List<String> result = FileSearcher.searchFile(new File(TEST_DIR), "out", false);
        assertTrue(result.size() > 1, "The file should appear multiple times in the directory.");
    }


    @Test
    public void testEmptyDirectory() {
        File emptyDir = new File("/home/xen/EmptyTemp");
        List<String> result = FileSearcher.searchFile(emptyDir, "task1.c", true);
        assertTrue(result.isEmpty(), "The result should be empty if the directory has no files.");
    }

    @Test
    public void testFileSearcherMainNoArgs() {
        FileSearcher.main(new String[]{});
        // This test checks if the main method handles no arguments without crashing.
    }

    @Test
    public void testFileSearcherMainWithArgsFileFound() {
        FileSearcher.main(new String[]{TEST_DIR, "task1.c", "true"});
        // Verifies if the file search works correctly when run via main method with case-sensitive search.
    }

    @Test
    public void testFileSearcherMainWithArgsFileNotFound() {
        FileSearcher.main(new String[]{TEST_DIR, "non_existent_file.c", "true"});
        // Verifies if the main method handles the case when the file is not found.
    }

    @Test
    public void testFileSearcherMainWithInvalidDirectory() {
        FileSearcher.main(new String[]{"/invalid/dir", "task1.c", "true"});
        // Ensures the main method handles invalid directory paths gracefully.
    }

    @Test
    public void testNullDirectory() {
        Exception exception = assertThrows(Exception.class, () -> {
            FileSearcher.searchFile(null, "task1.c", true);
        });
        assertEquals("Directory cannot be null", exception.getMessage());
    }

    @Test
    public void testNullFileName() {
        Exception exception = assertThrows(Exception.class, () -> {
            FileSearcher.searchFile(new File(TEST_DIR), null, true);
        });
        assertEquals("File name cannot be null", exception.getMessage());
    }
}
