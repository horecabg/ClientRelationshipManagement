package com.sirmaacademy.crm.manager;

import com.sirmaacademy.crm.Client;
import com.sirmaacademy.crm.service.Service;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientManager extends Manager {
    private Service service;

    public ClientManager(Service service) {
        this.service = service;
    }

    @Override
    public void performAction(String command) {
        switch (command) {
            case "1": {
                Scanner scanner = new Scanner(System.in);
                String entity;
                System.out.println("Add Client");
                System.out.println("Use this format to add a new client: (ID, name, industry, contact person, revenue)");
                entity = scanner.nextLine();
                // split input line by comma
                String[] tokens = entity.split(",");
                // check if the number of separated fields are 5. If not - means the wrong number of fields have entered
                if (tokens.length == 5) {
                    try {
                        // we trim all of the fields and check if first filed is integer number and
                        // the last one is the doubled number. If not - Error Exception
                        int i = Integer.parseInt(tokens[0].trim());
                        double d = Double.parseDouble(tokens[tokens.length - 1].trim());
                        // ID must have positive number, Name field is not possible to be empty and
                        // the Revenue have to be initially 0.0 or positive double number - You can edit after that
                        if (i > 0 && !(tokens[1].trim().isEmpty()) && d >= 0.00) {
                            Client client = new Client(Integer.parseInt(tokens[0].trim()), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), Double.parseDouble(tokens[4].trim()));
                            // check if such ID already exist - If not you can add this entity to the list
                            if (this.service.searchById(i) == null) {
                                this.service.addClient(client);
                                System.out.println("You have add successfully this record:");
                                System.out.println(client.toString());
                            } else {
                                System.out.println("=== ERROR === You have yet a record with this ID");
                            }
                        } else {
                            System.out.println("=== ERROR === Wrong ID, Name or Revenue field");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("=== ERROR === Wrong number format");
                    }
                } else {
                    System.out.println("=== ERROR === You entered wrong number of fields");
                }
                break;
            }
            case "2": {
                Scanner scanner = new Scanner(System.in);
                String entity;
                System.out.println("Update Client");
                System.out.println("Use this format to update a client: (ID, name, industry, contact person, revenue)");
                entity = scanner.nextLine();
                // split input line by comma
                String[] tokens = entity.split(",");
                // check if the number of separated fields are 5. If not - means the wrong number of fields have entered
                if (tokens.length == 5) {
                    try {
                        // we trim all of the fields and check if first filed is integer number and
                        // the last one is the doubled number. If not - Error Exception
                        int i = Integer.parseInt(tokens[0].trim());
                        double d = Double.parseDouble(tokens[tokens.length - 1].trim());
                        // ID must have positive number, Name field is not possible to be empty and
                        // the Revenue have to be initially 0.0 or positive double number - You can edit after that
                        if (i > 0 && !(tokens[1].trim().isEmpty()) && d >= 0.00) {
                            Client client;
                            client = this.service.searchById(i);
                            // check if such ID already exist - If yes you can edit this record
                            if (client != null) {
                                System.out.println("You will edit this record:");
                                System.out.println(this.service.searchById(i).toString());
                                client = new Client(Integer.parseInt(tokens[0].trim()), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), Double.parseDouble(tokens[4].trim()));
                                this.service.updateClient(i, client);
                            } else {
                                System.out.println("=== ERROR === You have not a record with this ID");
                            }
                        } else {
                            System.out.println("=== ERROR === Wrong ID, Name or Revenue field");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("=== ERROR === Wrong number format");
                    }
                } else {
                    System.out.println("=== ERROR === You entered wrong number of fields");
                }
                break;
            }
            case "3": {
                Scanner scanner = new Scanner(System.in);
                String entity;
                System.out.println("Remove Client");
                System.out.println("Enter Client ID to be removed");
                entity = scanner.nextLine();
                // split input line by comma
                String[] tokens = entity.split(",");
                // check if the number of separated fields are 5. If not - means the wrong number of fields have entered
                if (tokens.length == 1) {
                    try {
                        // we trim the field and check if the filed is integer number and
                        // If not - Error Exception
                        int i = Integer.parseInt(tokens[0].trim());
                        Client client;
                        client = this.service.searchById(i);
                        if (i > 0) {
                            // check if such ID already exist - If yes you can edit this record
                            if (client != null) {
                                this.service.removeClient(i);
                            } else {
                                System.out.println("=== ERROR === There is no client with such ID");
                            }
                        } else {
                            System.out.println("=== ERROR === Enter positive number");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("=== ERROR === Wrong number format");
                    }
                } else {
                    System.out.println("=== ERROR === You entered wrong number of fields");
                }
                break;
            }
            case "4": {
                System.out.println("View Clients");
                // list active employees
                this.service.viewClients();
                break;
            }
            case "5": {
                try {
                    Scanner scanner = new Scanner(System.in);
                    String entity;
                    Client client;
                    System.out.println("Search");
                    System.out.println("You can search by Id, Name or Industry.");
                    System.out.println("Use these formats: Search Id (id) | Search Name (name) | Search Industry (industry):");
                    entity = scanner.nextLine();
                    if (entity.trim().toLowerCase().startsWith("search ")) {
                        entity = entity.trim().substring(7);
                        if (entity.trim().toLowerCase().startsWith("id ")) {
                            entity = entity.trim().substring(3);
                            entity = entity.trim();
                            try {
                                int i = Integer.parseInt(entity);
                                client = this.service.searchById(i);
                                if (client != null) {
                                    System.out.println(client.toString());
                                } else {
                                    System.out.println("=== ERROR === You have not a record with this ID");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("=== ERROR === Wrong number format");
                            }
                        } else if (entity.trim().toLowerCase().startsWith("name ")) {
                            entity = entity.trim().substring(5);
                            entity = entity.trim();
                            this.service.searchByName(entity);
                        } else if (entity.trim().toLowerCase().startsWith("industry ")) {
                            entity = entity.trim().substring(9);
                            entity = entity.trim();
                            this.service.searchByIndustry(entity);
                        } else {
                            System.out.println("=== ERROR === Wrong format");
                        }
                    } else {
                        System.out.println("=== ERROR === Wrong format");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("=== ERROR === Problem with scanner");
                }
                break;
            }
            case "6": {
                System.out.println("Save & Exit");
                this.service.saveData();
                break;
            }
        }
    }
}
