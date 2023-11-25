package com.sirmaacademy.crm.service;

import com.sirmaacademy.crm.Client;

public interface Service {
    void addClient(Client client); // from console
    void addClientFile(Client client); // from file
    void updateClient(int id, Client client);
    void removeClient(int id);
    void viewClients();
    Client searchById(int id);
    void searchByName(String name);
    void searchByIndustry(String industry);
    boolean isActive(Client client);
    public void closeWriter();
    public void loadData();
    void saveData();
}
