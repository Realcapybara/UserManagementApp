package app;

import model.User;
import service.*;

import java.util.List;
import java.util.Scanner;

public class UserManagementApp {

    public static void main(String[] args) {

        UserService userService = new UserService();
        PrintService printService = new PrintService();
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService(userService, printService, scanner);
        FileService fileService = new FileService();

        List<User> users = fileService.loadUsersOrDefault(userService);

        // users.add(new User("Nicu", 25, 1500));
        // users.add(new User("Dubu", 35, 1000));
        // users.add(new User("Bubu", 38, 500));

        System.out.println("=== Initial users ===");
        printService.printAllUsers(users);

        boolean running = true;

        while (running) {
            printService.printMenu();

            int option = userService.readValidIntWithTryCatch(scanner, "Choose option:");

            switch (option) {
                case MenuOptions.PRINT_ALL_USERS:
                    System.out.println("=== All users ===");
                    printService.printAllUsers(users);
                    break;

                case MenuOptions.ADD_USERS:
                    menuService.addUsers(users);
                    break;

                case MenuOptions.FIND_USER_BY_NAME:
                    menuService.findUserByName(users);
                    break;

                case MenuOptions.SHOW_AVERAGE_SALARY:
                    menuService.showAverageSalary(users);
                    break;

                case MenuOptions.SHOW_TOTAL_SALARY:
                    menuService.showTotalSalary(users);
                    break;

                case MenuOptions.SHOW_HIGHEST_SALARY_USER:
                    menuService.showHighestSalaryUser(users);
                    break;

                case MenuOptions.SHOW_LOWEST_SALARY_USER:
                    menuService.showLowestSalaryUser(users);
                    break;

                case MenuOptions.SORT_BY_SALARY_ASC:
                    menuService.sortUsersBySalaryAscending(users);
                    break;

                case MenuOptions.SORT_BY_SALARY_DESC:
                    menuService.sortUsersBySalaryDescending(users);
                    break;

                case MenuOptions.SORT_BY_AGE_ASC:
                    menuService.sortUsersByAgeAscending(users);
                    break;

                case MenuOptions.SORT_BY_AGE_DESC:
                    menuService.sortUsersByAgeDescending(users);
                    break;

                case MenuOptions.SORT_BY_NAME_ASC:
                    menuService.sortUsersByNameAscending(users);
                    break;

                case MenuOptions.SORT_BY_NAME_DESC:
                    menuService.sortUsersByNameDescending(users);
                    break;

                case MenuOptions.FILTER_BY_SALARY:
                    menuService.showUsersWithSalaryAbove(users);
                    break;

                case MenuOptions.EXIT:
                    running = false;
                    System.out.println("Exiting program...");
                    break;

                case MenuOptions.SHOW_SALARY_CATEGORIES:
                    menuService.showSalaryCategories(users);
                    break;

                case MenuOptions.SHOW_USER_NAMES:
                    menuService.showUserNames(users);
                    break;

                case MenuOptions.SAVE_USERS_TO_FILE:
                    fileService.saveUsersToFile(users);
                    break;

                case MenuOptions.UPDATE_USER_SALARY:
                    menuService.updateUserSalary(users);
                    break;

                case MenuOptions.DELETE_USER_BY_NAME:
                    menuService.deleteUserByName(users);
                    break;

                case MenuOptions.RAISE_USER_SALARY:
                    menuService.raiseUserSalary(users);
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        scanner.close();
    }
}