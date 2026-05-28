package service;

import model.User;

import java.util.List;
import java.util.Scanner;

public class MenuService {

    private final UserService userService;
    private final PrintService printService;
    private final Scanner scanner;

    public MenuService(UserService userService, PrintService printService, Scanner scanner) {
        this.userService = userService;
        this.printService = printService;
        this.scanner = scanner;
    }

    public void addUsers(List<User> users) {
        int numberOfUsersToAdd = userService.readPositiveIntWithTryCatch(
                scanner,
                "How many users do you want to add?"
        );

        for (int i = 0; i < numberOfUsersToAdd; i++) {
            System.out.println("=== Add user " + (i + 1) + " ===");

            User newUser = userService.readUserFromKeyboard(scanner, printService);
            users.add(newUser);
        }

        System.out.println("=== Users after adding new users ===");
        printService.printAllUsers(users);
    }

    public void findUserByName(List<User> users) {
        System.out.println("Enter name to search:");
        String searchName = scanner.nextLine();

        User foundUser = userService.findUserByName(users, searchName);

        if (foundUser != null) {
            System.out.println("=== User found ===");
            printService.printInfo(foundUser);
        } else {
            System.out.println("User not found");
        }

        printService.printSeparator();
    }

    public void showAverageSalary(List<User> users) {
        System.out.println("=== Average salary ===");
        double avg = userService.calculateAverageSalary(users);
        System.out.println("Average salary: " + avg);
        printService.printSeparator();
    }

    public void showTotalSalary(List<User> users) {
        System.out.println("=== Total salary ===");
        double total = userService.calculateTotalSalary(users);
        System.out.println("Total salary: " + total);
        printService.printSeparator();
    }

    public void showHighestSalaryUser(List<User> users) {
        System.out.println("=== Highest salary user ===");
        User highest = userService.findHighestSalaryUser(users);
        printService.printInfo(highest);
        printService.printSeparator();
    }

    public void showLowestSalaryUser(List<User> users) {
        System.out.println("=== Lowest salary user ===");
        User lowest = userService.findLowestSalaryUser(users);
        printService.printInfo(lowest);
        printService.printSeparator();
    }

    public void sortUsersBySalaryAscending(List<User> users) {
        System.out.println("=== Users sorted by salary low to high ===");
        userService.sortUsersBySalaryAscending(users);
        printService.printAllUsers(users);
    }

    public void sortUsersBySalaryDescending(List<User> users) {
        System.out.println("=== Users sorted by salary high to low ===");
        userService.sortUsersBySalaryDescending(users);
        printService.printAllUsers(users);
    }

    public void sortUsersByAgeAscending(List<User> users) {
        System.out.println("=== Users sorted by age low to high ===");
        userService.sortUsersByAgeAscending(users);
        printService.printAllUsers(users);
    }

    public void sortUsersByAgeDescending(List<User> users) {
        System.out.println("=== Users sorted by age high to low ===");
        userService.sortUsersByAgeDescending(users);
        printService.printAllUsers(users);
    }

    public void sortUsersByNameAscending(List<User> users) {
        System.out.println("=== Users sorted by name A to Z ===");
        userService.sortUsersByNameAscending(users);
        printService.printAllUsers(users);
    }

    public void sortUsersByNameDescending(List<User> users) {
        System.out.println("=== Users sorted by name Z to A ===");
        userService.sortUsersByNameDescending(users);
        printService.printAllUsers(users);
    }

    public void showUsersWithSalaryAbove(List<User> users) {
        double minSalary = userService.readPositiveDoubleWithTryCatch(
                scanner,
                "Enter minimum salary:"
        );

        List<User> filteredUsers = userService.getUsersWithSalaryAbove(users, minSalary);

        System.out.println("=== Users with salary above " + minSalary + " ===");
        printService.printAllUsers(filteredUsers);
    }

    public void showSalaryCategories(List<User> users) {
        System.out.println("=== Salary categories for all users ===");

        for (User user : users) {
            printService.printSalaryValue(user, userService);
        }

        printService.printSeparator();
    }

    public void showUserNames(List<User> users) {
        System.out.println("=== User names ===");

        List<String> names = userService.getUserNames(users);

        for (String name : names) {
            System.out.println(name);
        }

        printService.printSeparator();
    }

    public void updateUserSalary(List<User> users) {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();

        double newSalary = userService.readPositiveDoubleWithTryCatch(scanner, "Enter new salary:");

        boolean updated = userService.updateUserSalary(users, name, newSalary);

        if (updated) {
            System.out.println("Salary updated successfully.");

            User updatedUser = userService.findUserByName(users, name);
            printService.printInfo(updatedUser);
        } else {
            System.out.println("User not found.");
        }

        printService.printSeparator();
    }

    public void deleteUserByName(List<User> users) {
        System.out.println("Enter user name to delete:");
        String name = scanner.nextLine();

        boolean deleted = userService.deleteUserByName(users, name);

        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }

        printService.printSeparator();
    }
    public void raiseUserSalary(List<User> users) {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();

        double raiseAmount = userService.readPositiveDoubleWithTryCatch(scanner, "Enter raise amount:");

        boolean raised = userService.raiseUserSalary(users, name, raiseAmount);

        if (raised) {
            System.out.println("Salary raised successfully.");

            User updatedUser = userService.findUserByName(users, name);
            printService.printInfo(updatedUser);
        } else {
            System.out.println("User not found.");
        }

        printService.printSeparator();
    }
}