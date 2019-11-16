package model;

import java.util.ArrayList;

public class ClientManager {

    // ============================================================
    // Member Variables
    // ============================================================

    DatabaseManager databaseManager;

    // ============================================================
    // Constructors
    // ============================================================

    public ClientManager() {
        databaseManager = new DatabaseManager();
        loadDataFromInputFile();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public ArrayList<String[]> getClientList() {
        ArrayList<Client> clientList = databaseManager.getClientsFromDB();
        ArrayList<String[]> clientListAsStrings = new ArrayList<String[]>();

        for (Client client : clientList) {
            String clientID = Integer.toString(client.getClientID());
            String clientName = client.getFirstName() + " " + client.getLastName();
            String clientType = String.valueOf(client.getClientType());

            String[] clientReduced = new String[] { clientID, clientName, clientType };
            clientListAsStrings.add(clientReduced);
        }

        return clientListAsStrings;
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void loadDataFromInputFile() {
        DataLoader dataLoader = new DataLoader();
        ArrayList<Client> clientList = dataLoader.loadClientData();

        // TODO: Have this check to see if both the database and table exists.
        boolean tempTest = false;
        if (tempTest) {
            databaseManager.createClientTable();

            for (Client client : clientList) {
                databaseManager.addItemToDB(client);
            }
        }
    }
}