package com.sirmaacademy.crm;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ClientManagementApp {
    public static void main(String[] args) {
// Implement file operations for XML/SQLite
        CSVReader fileReader;
        CSVWriter fileWriter = null;
     //   Service service = new ClientService(fileReader, fileWriter);
     //   Manager manager = new ClientManager(service);
        System.out.println("Welcome to the Client Management System");
        displayOptions();
        boolean active = true;
        while (active) {
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