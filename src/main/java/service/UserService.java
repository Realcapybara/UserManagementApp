package service;

import model.SalaryCategory;
import model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserService {

    public User findHighestSalaryUser(List<User> users) {
         return users.stream()
                 .max(Comparator.comparingDouble(User::getSalary))
                 .orElse(null);
    }

    public User findLowestSalaryUser(List<User> users) {
        return users.stream()
                .min(Comparator.comparingDouble(User::getSalary))
                .orElse(null);
    }

    public List<User> getUsersWithSalaryAbove(List<User> users, double minSalary) {

        return users.stream()
                .filter(user -> user.getSalary() > minSalary)
                .toList();
    }

    public User findUserByName(List<User> users, String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public double calculateAverageSalary(List<User> users) {
        return users.stream()
                .mapToDouble(User::getSalary)
                .average()
                .orElse(0);
    }

    public double calculateTotalSalary(List<User> users) {
        return users.stream()
                .mapToDouble(User::getSalary)
                .sum();
    }

    // Why void? Because Collections.sort(...) changes the original list directly.
    public void sortUsersBySalaryAscending(List<User> users) {
        users.sort(Comparator.comparingDouble(User::getSalary));
    }

    // Why void? Because Collections.sort(...) changes the original list directly.
    public void sortUsersBySalaryDescending(List<User> users) {
        users.sort(Comparator.comparingDouble(User::getSalary).reversed());
    }

    public void sortUsersByAgeAscending(List<User> users) {
        users.sort(Comparator.comparingInt(User::getAge));
    }

    public void sortUsersByAgeDescending(List<User> users) {
        users.sort(Comparator.comparingInt(User::getAge).reversed());
    }

    public void sortUsersByNameAscending(List<User> users) {
        users.sort(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public void sortUsersByNameDescending(List<User> users) {
        users.sort(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER).reversed());
    }

    public User readUserFromKeyboard(Scanner scanner, PrintService printService) {
        String name = readValidName(scanner, "Enter user name:");
        int age = readPositiveIntWithTryCatch(scanner, "Enter age:");
        double salary = readPositiveDoubleWithTryCatch(scanner, "Enter salary:");

        return new User(name, age, salary);
    }

    public String readValidName(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);

            String name = scanner.nextLine().trim();

            if (name.length() >= 2 && name.matches(".*[a-zA-Z].*")) {
                return name;
            } else {
                System.out.println("Invalid input. Name must have at least 2 characters and contain a letter.");
            }
        }
    }

    public double readValidDoubleWithTryCatch(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);

            String input = scanner.nextLine();

            try {
                double value = Double.parseDouble(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public double readPositiveDoubleWithTryCatch(Scanner scanner, String message) {
        while (true) {
            double value = readValidDoubleWithTryCatch(scanner, message);

            if (value > 0) {
                return value;
            } else {
                System.out.println("Invalid input. Please enter a positive number.");
            }
        }
    }

    public int readValidIntWithTryCatch(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);

            String input = scanner.nextLine();

            try {
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public int readPositiveIntWithTryCatch(Scanner scanner, String message) {
        while (true) {
            int value = readValidIntWithTryCatch(scanner, message);

            if (value > 0) {
                return value;
            } else {
                System.out.println("Invalid input. Please enter a positive whole number.");
            }
        }
    }

    public SalaryCategory getSalaryCategory(User user) {
        double salary = user.getSalary();

        if (salary >= SalaryConstants.EXCELLENT_SALARY_LIMIT) {
            return SalaryCategory.EXCELLENT;
        } else if (salary > SalaryConstants.GOOD_SALARY_LIMIT) {
            return SalaryCategory.GOOD;
        } else {
            return SalaryCategory.LOW;
        }
    }
    public List<User> getUsersWithSalaryBelow(List<User> users, double maxSalary) {
        return users.stream()
                .filter(user -> user.getSalary() < maxSalary)
                .toList();
    }

    public List<User> getUsersOlderThan(List<User> users, int minAge) {
        return users.stream()
                .filter(user -> user.getAge() > minAge)
                .toList();
    }

    public long countUsersWithSalaryAbove(List<User> users, double minSalary) {
        return users.stream()
                .filter(user -> user.getSalary() > minSalary)
                .count();
    }

    public boolean hasAnyExcellentSalaryUser(List<User> users) {
        return users.stream()
                .anyMatch(user -> getSalaryCategory(user) == SalaryCategory.EXCELLENT);
    }

    public boolean areAllUsersAdults(List<User> users) {
        return users.stream()
                .allMatch(user -> user.getAge() >= 18);
    }

    public List<String> getUserNames(List<User> users) {
        return users.stream()
                .map(user -> user.getName())
                .toList();
    }
    public List<User> createDefaultUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("Nicu", 25, 1500));
        users.add(new User("Dubu", 35, 1000));
        users.add(new User("Bubu", 38, 500));

        return users;
    }
    public boolean updateUserSalary(List<User> users, String name, double newSalary) {
        User user = findUserByName(users, name);

        if (user == null) {
            return false;
        }

        user.setSalary(newSalary);
        return true;
    }

    public boolean deleteUserByName(List<User> users, String name) {
        return users.removeIf(user -> user.getName().equalsIgnoreCase(name));
    }

    public boolean raiseUserSalary(List<User> users, String name, double raiseAmount) {
        User user = findUserByName(users, name);

        if (user == null) {
            return false;
        }

        user.raiseSalary(raiseAmount);
        return true;
    }
}