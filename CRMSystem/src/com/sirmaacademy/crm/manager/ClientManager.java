package com.sirmaacademy.crm.manager;

public class ClientManager extends Manager {
    /*    private Service service;

        public ClientManager(Service service) {
            this.service = service;
        }
    */
    @Override
    public void performAction(String command) {
        switch (command){
            case "1": {
                System.out.println("Add Client");
                break;
            }
            case "2": {
                System.out.println("Update Client");
                break;
            }
            case "3": {
                System.out.println("Remove Client");
                break;
            }
            case "4": {
                System.out.println("View Clients\"");
                break;
            }
            case "5": {
                System.out.println("Search");
                break;
            }
            case "6": {
                System.out.println("Save & Exit");
                break;
            }
        }
    }
}
