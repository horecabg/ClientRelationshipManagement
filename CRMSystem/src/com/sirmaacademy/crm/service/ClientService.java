package com.sirmaacademy.crm.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.sirmaacademy.crm.Client;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements Service {
    public List<Client> clients;
    public CSVReader reader;
    public CSVWriter writer;

    public ClientService(CSVReader reader, CSVWriter writer) {
        this.clients = new ArrayList<>();
        this.reader = reader;
        this.writer = writer;
        loadData();
    }

    @Override
    public void addClient(Client client) {
        clients.add(client);
    }


    @Override
    public void updateClient(int id, Client client) {
        for (Client c : clients) {
            if (c.getId() == id) {
                c.setName(client.getName());
                c.setIndustry(client.getIndustry());
                c.setContactPerson(client.getContactPerson());
                c.setRevenue(client.getRevenue());
                System.out.printf("You have edit successfully the record with ID: %d%n", id);
                System.out.println(c.toString());
            }
        }
    }

    @Override
    public void removeClient(int id) {
        for (Client c : this.clients) {
            if (c.getId() == id) {
                System.out.println("You have removed this client:");
                System.out.println(c.toString());
                clients.remove(c);
                break;
            }
        }
    }

    @Override
    public void viewClients() {
        for (Client c : this.clients) {
            System.out.println(c.toString());
        }
    }

    @Override
    public Client searchById(int id) {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void searchByName(String name) {
        // you can search using any part of the name  - not case sensitive
        // make a list with all matches
        List<Client> clientsByName = new ArrayList<>();
        clientsByName = this.clients.stream()
                .filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (!clientsByName.isEmpty()) {
            for (Client c : clientsByName) {
                System.out.println(c.toString());
            }
        } else {
            System.out.println("=== ERROR === There is no records found with this criteria");
        }
    }

    @Override
    public void searchByIndustry(String industry) {
        // you can search using any part of the industry  - not case sensitive
        // make a list with all matches
        List<Client> clientsByIndustry = new ArrayList<>();
        clientsByIndustry = this.clients.stream()
                .filter(e -> e.getIndustry().toLowerCase().contains(industry.toLowerCase()))
                .collect(Collectors.toList());
        if (!clientsByIndustry.isEmpty()) {
            for (Client c : clientsByIndustry) {
                System.out.println(c.toString());
            }
        } else {
            System.out.println("=== ERROR === There is no records found with this criteria");
        }
    }

    @Override
    public void closeWriter() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("=== ERROR === IOException");
        }
    }

    @Override
    public void loadData() {
// load date initially and during check up process only valid items save in the list it computer memory
        // check for unique id and have to be positive number also
        // the name is not possible to be empty string and only with white spaces also
        // the salary has to be positive number
        // also check the date format
        String[] entity;
        while (true) {
            try {
                if ((entity = reader.readNext()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Arrays.toString(entity));
            if (entity.length == 5) {
                try {
                    // we trim all of the fields and check if first filed is integer number and
                    // the last one is the doubled number. If not - Error Exception
                    int i = Integer.parseInt(entity[0].trim());
                    double d = Double.parseDouble(entity[entity.length - 1].trim());
                    // ID must have positive number, Name field is not possible to be empty and
                    // the Salary have to be initially 0.0 or positive double number - You can edit after that
                    if (i > 0 && !(entity[1].trim().isEmpty()) && d >= 0.00) {
                        Client client = new Client(i, entity[1].trim(), entity[2].trim(), entity[3].trim(), d);
                        // check if such ID already exist - If not you can add this entity to the list
                        if (this.searchById(i) == null) {
                            this.addClient(client);
                            System.out.println("You have add successfully this record:");
                        } else {
                            System.out.println("=== ERROR === You have yet a record with this ID");
                        }
                    } else {
                        System.out.println("=== ERROR === Wrong ID, Name or Salary field");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("=== ERROR === Wrong format");
                }
            } else {
                System.out.println("=== ERROR === Wrong number of fields");
            }
        }
    }

    @Override
    public void saveData() {
        try {
            try {
                writer = new CSVWriter(new FileWriter("clientsDB.csv"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Client c : clients) {
                String[] stringLine = {String.valueOf(c.getId()), c.getName(), c.getIndustry(), c.getContactPerson(), String.valueOf(c.getRevenue())};
                this.writer.writeNext(stringLine);
            }
        } finally {
            ((ClientService) this).closeWriter();
        }
    }
}
