package service;

import model.SalaryCategory;
import model.User;

import java.util.List;

public class PrintService {

    public void printMenu() {
        System.out.println("=== MENU ===");
        System.out.println(MenuOptions.PRINT_ALL_USERS + ". Print all users");
        System.out.println(MenuOptions.ADD_USERS + ". Add users");
        System.out.println(MenuOptions.FIND_USER_BY_NAME + ". Find user by name");
        System.out.println(MenuOptions.SHOW_AVERAGE_SALARY + ". Show average salary");
        System.out.println(MenuOptions.SHOW_TOTAL_SALARY + ". Show total salary");
        System.out.println(MenuOptions.SHOW_HIGHEST_SALARY_USER + ". Show highest salary user");
        System.out.println(MenuOptions.SHOW_LOWEST_SALARY_USER + ". Show lowest salary user");
        System.out.println(MenuOptions.SORT_BY_SALARY_ASC + ". Sort users by salary low to high");
        System.out.println(MenuOptions.SORT_BY_SALARY_DESC + ". Sort users by salary high to low");
        System.out.println(MenuOptions.SORT_BY_AGE_ASC + ". Sort users by age low to high");
        System.out.println(MenuOptions.SORT_BY_AGE_DESC + ". Sort users by age high to low");
        System.out.println(MenuOptions.SORT_BY_NAME_ASC + ". Sort users by name A to Z");
        System.out.println(MenuOptions.SORT_BY_NAME_DESC + ". Sort users by name Z to A");
        System.out.println(MenuOptions.FILTER_BY_SALARY + ". Show users with salary above amount");
        System.out.println(MenuOptions.SHOW_SALARY_CATEGORIES + ". Show salary categories for all users");
        System.out.println(MenuOptions.SHOW_USER_NAMES + ". Show user names for all users");
        System.out.println(MenuOptions.SAVE_USERS_TO_FILE + ". Save users to file");
        System.out.println(MenuOptions.UPDATE_USER_SALARY + ". Update user salary");
        System.out.println(MenuOptions.DELETE_USER_BY_NAME + ". Delete user by name");
        System.out.println(MenuOptions.RAISE_USER_SALARY + ". Raise user salary");
        System.out.println(MenuOptions.EXIT + ". Exit");
    }

    public void printAllUsers(List<User> users) {
        System.out.println("=== All users ===");
        for (User user : users) {
            printInfo(user);
            printSeparator();
        }
    }

    public void printAllUserNames(List<User> users) {
        System.out.println("=== All User Names ===");
        for (User user : users) {
            printInfo(user);
            printSeparator();
        }
    }

    public void printInfo(User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Salary: " + user.getSalary());
    }
    public void printSeparator(){
        System.out.println("--------------");

    }
    public void printEnterName(){
        System.out.println("Enter user name:");

    }
    public void printEnterAge(){
        System.out.println("Enter user age:");

    }
    public void printEnterSalary(){
        System.out.println("Enter user salary:");

    }
    public void printSalaryValue(User user, UserService userService) {
        SalaryCategory category = userService.getSalaryCategory(user);

        if (category == SalaryCategory.EXCELLENT) {
            System.out.println(user.getName() + ": " + SalaryConstants.EXCELLENT_SALARY_MESSAGE);
        } else if (category == SalaryCategory.GOOD) {
            System.out.println(user.getName() + ": " + SalaryConstants.GOOD_SALARY_MESSAGE);
        } else {
            System.out.println(user.getName() + ": " + SalaryConstants.LOW_SALARY_MESSAGE);
        }
    }
}
