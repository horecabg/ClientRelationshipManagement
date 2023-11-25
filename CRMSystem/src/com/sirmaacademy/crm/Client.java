package com.sirmaacademy.crm;

public class Client {
private int id;
private String name;
private String industry;
private String contactPerson;
private Double revenue;

    public Client(int id, String name, String industry, String contactPerson, Double revenue) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.contactPerson = contactPerson;
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return this.getId() + ", " + this.getName() + ", " + this.getIndustry() + ", " + this.getContactPerson() + ", " + this.getRevenue();
    }
}
