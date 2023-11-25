package com.sirmaacademy.crm.service;

import com.sirmaacademy.crm.Client;

public interface Service {
    void addClient(Client client);

    void updateClient(int id, Client client);

    void removeClient(int id);

    void viewClients();

    Client searchById(int id);

    void searchByName(String name);

    void searchByIndustry(String industry);

    public void closeWriter();

    public void loadData();

    void saveData();
}
