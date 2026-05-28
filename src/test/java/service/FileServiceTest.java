package service;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    private static final String TEST_FILE_NAME = "test-users.txt";
    private FileService fileService;

    @BeforeEach
    public void setUp() {
        fileService = new FileService(TEST_FILE_NAME);
    }

    @AfterEach
    public void cleanUp() {
        File file = new File(TEST_FILE_NAME);

        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void saveUsersToFileShouldCreateFileWithUsers() {

        List<User> users = List.of(
                new User("Nicu", 25, 1500),
                new User("Dubu", 35, 1000)
        );

        fileService.saveUsersToFile(users);

        File file = new File(TEST_FILE_NAME);

        assertTrue(file.exists());
    }

    @Test
    public void loadUsersFromFileShouldReturnSavedUsers() {

        List<User> users = List.of(
                new User("Nicu", 25, 1500),
                new User("Dubu", 35, 1000)
        );

        fileService.saveUsersToFile(users);

        List<User> loadedUsers = fileService.loadUsersFromFile();

        assertAll(
                () -> assertEquals(2, loadedUsers.size()),
                () -> assertEquals("Nicu", loadedUsers.get(0).getName()),
                () -> assertEquals(25, loadedUsers.get(0).getAge()),
                () -> assertEquals(1500, loadedUsers.get(0).getSalary(), 0.001),
                () -> assertEquals("Dubu", loadedUsers.get(1).getName()),
                () -> assertEquals(35, loadedUsers.get(1).getAge()),
                () -> assertEquals(1000, loadedUsers.get(1).getSalary(), 0.001)
        );
    }

    @Test
    public void loadUsersFromFileShouldSkipInvalidLines() throws IOException {
        Files.writeString(
                Path.of(TEST_FILE_NAME),
                "Nicu;25;1500.0\n" +
                        "invalid-line\n" +
                        "Dubu;35;1000.0\n"
        );

        List<User> loadedUsers = fileService.loadUsersFromFile();

        assertAll(
                () -> assertEquals(2, loadedUsers.size()),
                () -> assertEquals("Nicu", loadedUsers.get(0).getName()),
                () -> assertEquals("Dubu", loadedUsers.get(1).getName())
        );
    }
}