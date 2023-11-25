package com.sirmaacademy.crm;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sirmaacademy.crm.service.Service;
import com.sirmaacademy.crm.service.ClientService;
import com.sirmaacademy.crm.manager.Manager;
import com.sirmaacademy.crm.manager.ClientManager;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ClientManagementApp {
    public static void main(String[] args) {
// Implement file operations
        CSVReader fileReader = null;
        CSVWriter fileWriter = null;
        try {
            Path outputPath = Paths.get("clientsDB.csv");
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath);
            }
            fileReader = new CSVReader(new FileReader("clientsDB.csv"));
            String command;
            Scanner scanner = new Scanner(System.in);
            Service service = new ClientService(fileReader, fileWriter);
            Manager manager = new ClientManager(service);
            System.out.println();
            System.out.println("Welcome to the Client Management System");
            displayOptions();
            boolean active = true;
            while (active) {
                try {
                    command = scanner.nextLine();
                    int choice = Integer.parseInt(command);
                    if (1 <= choice && choice <= 6) {
                        if (choice == 6) active = false;
                        manager.performAction(command);
                    } else System.out.printf("=== ERROR === Invalid choice. Please try again.%n%n");
                } catch (NumberFormatException ee) {
                    System.out.printf("=== ERROR === Invalid input. Please enter a number.%n%n");
                }
                if (active) displayOptions();

                //       manager.performAction(command);
// Add Client
// 1, Oceanic Enterprises, Finance, Sarah Smith, 500000.00
// Update Client
// 1, Oceanic Enterprises, Tech, Sarah Smith, 750000.00
// View Clients
// Search Industry Tech
// Search ID 1
// Remove Client 1
// Search Name Oceanic
// Save & Exit
            }
        } catch (IOException e) {
            System.out.println("=== ERROR === IOException");
        }
    }

    private static void displayOptions() {
        System.out.println();
        System.out.println("1. Add Client");
        System.out.println("2. Update Client");
        System.out.println("3. Remove Client");
        System.out.println("4. View Clients");
        System.out.println("5. Search");
        System.out.println("6. Save & Exit");
        System.out.println();
        System.out.println("Enter your choice (from 1 to 6): ");
    }
}