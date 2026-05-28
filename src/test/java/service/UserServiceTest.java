package service;

import model.SalaryCategory;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;
    private List<User> users;

    @BeforeEach
    public void setUp() {
        userService = new UserService();

        users = new ArrayList<>(List.of(
                new User("Nicu", 25, 1500),
                new User("Dubu", 35, 1000),
                new User("Bubu", 38, 500)
        ));
    }

    @Test
    public void calculateTotalSalaryShouldReturnSumOfAllSalaries() {

        double total = userService.calculateTotalSalary(users);

        assertEquals(3000, total);
    }

    @Test
    public void calucalteAverageSalaryShouldReturnAverageOfAllSalaries() {


        double average = userService.calculateAverageSalary(users);
        //for doubles we also write 0.001 as a toleration delta
        assertEquals(1000, average, 0.001);
    }

    @Test
    public void calculateAverageSalaryShouldReturnZeroWhenListIsEmpty() {

        List<User> users = List.of();

        double average = userService.calculateAverageSalary(users);

        assertEquals(0, average, 0.001);
    }

    @Test
    public void getSalaryCategoryShouldReturnExcellentForHighSalary() {

        User user = new User("Alex", 30, 15000);

        SalaryCategory category = userService.getSalaryCategory(user);

        assertEquals(SalaryCategory.EXCELLENT, category);
    }

    @Test
    public void getSalaryCategoryShouldReturnGoodForMediumSalary() {

        User user = new User("Maria", 28, 7000);

        SalaryCategory category = userService.getSalaryCategory(user);

        assertEquals(SalaryCategory.GOOD, category);
    }

    @Test
    public void getSalaryCategoryShouldReturnLowForLowSalary() {

        User user = new User("Bubu", 38, 500);

        SalaryCategory category = userService.getSalaryCategory(user);

        assertEquals(SalaryCategory.LOW, category);
    }

    @Test
    public void findUserByNameShouldReturnUserWhenNameExists() {

        User foundUser = userService.findUserByName(users, "Nicu");

        assertEquals("Nicu", foundUser.getName());
    }

    @Test
    public void findUserByNameShouldReturnNullWhenNameDoesNotExist() {


        User foundUser = userService.findUserByName(users, "Alex");

        assertNull(foundUser);
    }

    @Test
    public void getUsersWithSalaryAboveShouldReturnOnlyUsersAboveGivenSalary() {

        List<User> usersWithHighSalaryUser = List.of(
                new User("Nicu", 25, 1500),
                new User("Dubu", 35, 1000),
                new User("Bubu", 38, 500),
                new User("Alex", 30, 7000)
        );

        List<User> result = userService.getUsersWithSalaryAbove(usersWithHighSalaryUser, 5000);

        assertEquals(1, result.size());
        assertEquals("Alex", result.get(0).getName());
    }

    @Test
    public void getUserNamesShouldReturnOnlyNames() {


        List<String> names = userService.getUserNames(users);

        assertAll(
                () -> assertEquals(3, names.size()),
                () -> assertEquals("Nicu", names.get(0)),
                () -> assertEquals("Dubu", names.get(1)),
                () -> assertEquals("Bubu", names.get(2))
        );
    }

    @Test
    public void hasAnyExcellentSalaryUserShouldReturnTrueWhenExcellentUserExists() {

        List<User> usersWithExcellentSalary = List.of(
                new User("Nicu", 25, 1500),
                new User("Dubu", 35, 1000),
                new User("Alex", 30, 15000)
        );

        boolean result = userService.hasAnyExcellentSalaryUser(usersWithExcellentSalary);

        assertTrue(result);
    }

    @Test
    public void hasAnyExcellentSalaryUserShouldReturnFalseWhenNoExcellentUserExists() {

        boolean result = userService.hasAnyExcellentSalaryUser(users);

        assertFalse(result);
    }

    @Test
    public void areAllUsersAdultsShouldReturnTrueWhenAllUsersAreAdults() {

        boolean result = userService.areAllUsersAdults(users);

        assertTrue(result);
    }

    @Test
    public void areAllUsersAdultsShouldReturnFalseWhenAtLeastOneUserIsUnder18() {

        List<User> usersWithMinor = List.of(
                new User("Nicu", 25, 1500),
                new User("Dubu", 17, 1000),
                new User("Bubu", 38, 500)
        );

        boolean result = userService.areAllUsersAdults(usersWithMinor);

        assertFalse(result);
    }

    @Test
    public void getUsersWithSalaryAboveShouldReturnEmptyListWhenNoUserMatches() {
        List<User> result = userService.getUsersWithSalaryAbove(users, 10000);

        assertTrue(result.isEmpty());
    }

    @Test
    public void getUserNamesShouldReturnEmptyListWhenUsersListIsEmpty() {
        List<User> emptyUsers = List.of();

        List<String> names = userService.getUserNames(emptyUsers);

        assertTrue(names.isEmpty());
    }

    @Test
    public void updateUserSalaryShouldReturnTrueAndUpdateSalaryWhenUserExists() {
        boolean result = userService.updateUserSalary(users, "Nicu", 3000);

        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(3000, users.get(0).getSalary(), 0.001)
        );
    }

    @Test
    public void updateUserSalaryShouldReturnFalseWhenUserDoesNotExist() {
        boolean result = userService.updateUserSalary(users, "Alex", 3000);

        assertFalse(result);
    }
    @Test
    public void deleteUserByNameShouldReturnTrueAndRemoveUserWhenUserExists() {
        boolean result = userService.deleteUserByName(users, "Nicu");

        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(2, users.size()),
                () -> assertNull(userService.findUserByName(users, "Nicu"))
        );
    }
    @Test
    public void deleteUserByNameShouldReturnFalseWhenUserDoesNotExist() {
        boolean result = userService.deleteUserByName(users, "Alex");

        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(3, users.size())
        );
    }
    @Test
    public void raiseUserSalaryShouldReturnTrueAndIncreaseSalaryWhenUserExists() {
        boolean result = userService.raiseUserSalary(users, "Nicu", 500);

        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(2000, users.get(0).getSalary(), 0.001)
        );
    }
    @Test
    public void raiseUserSalaryShouldReturnFalseWhenUserDoesNotExist() {
        boolean result = userService.raiseUserSalary(users, "Alex", 500);

        assertFalse(result);
    }
}