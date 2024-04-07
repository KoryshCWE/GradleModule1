package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

class User {
    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Main_User {
    public static void main(String[] args) {
        String inputFile = "src/main/java/file.txt";
        String outputFile = "src/main/java/org/example/user.json";

        List<User> users = readUsersFromFile(inputFile);
        writeUsersToJsonFile(users, outputFile);
    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                users.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(users);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
