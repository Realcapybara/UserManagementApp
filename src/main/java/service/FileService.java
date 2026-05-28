package service;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private final String fileName;

    public FileService() {
        this.fileName = "users.txt";
    }

    public FileService(String fileName) {
        this.fileName = fileName;
    }

    public void saveUsersToFile(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (User user : users) {
                writer.write(user.getName() + ";" + user.getAge() + ";" + user.getSalary());
                writer.newLine();
            }

            System.out.println("Users saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving users to file.");
        }
    }

    public List<User> loadUsersFromFile() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length != 3) {
                    System.out.println("Invalid line skipped: " + line);
                    continue;
                }

                try {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    double salary = Double.parseDouble(parts[2]);

                    User user = new User(name, age, salary);
                    users.add(user);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format, line skipped: " + line);
                }
            }

            System.out.println("Users loaded successfully.");

        } catch (IOException e) {
            System.out.println("Could not load users from file.");
        }

        return users;
    }

    public List<User> loadUsersOrDefault(UserService userService) {
        List<User> users = loadUsersFromFile();

        if (users.isEmpty()) {
            System.out.println("No users loaded. Creating default users.");
            return userService.createDefaultUsers();
        }

        return users;
    }
}