package client;

import shared.model.Contact;
import shared.model.DataValidationException;

import java.util.Scanner;
import java.util.Set;


public class Main {

    public static ServerAPI api;
    public static enum RequestMethod {
        POST, PUT, GET, DELETE
    }
    private static RequestMethod requestMethod;

    public static void displayMenu () {
        System.out.println("-------------------------------\n" +
                "Choose a menu option from below: \n" +
                "\t(1) Enter endpoints indefinitely\n\t(Format: \"[requestType] [path]\"; e.g. \"POST /contact\")\n" +
                "\t(2) Exit\n"
        );
    }

    /* TO DO: Make this form active; in this case, fill contact object & report errors for invalid input */
    public static Contact promptForContactInformation (Scanner in) throws DataValidationException {
        int selection = 0;
    /*
        while (selection < 1 || selection > 2) {
            System.out.println("Would you like to enter any additional contact information?\n" +
                    "\t (1) YES!\n" +
                    "\t (2) No\n");
            selection = in.nextInt();
        }
    */

        Contact empty = new Contact();
        switch(selection) {
            case 1:
                Contact contact = new Contact("Dummy", "fake", "test", "foo");
                System.out.println("To do: contact form that outputs error errors here");
                return contact;

            case 2:
                return new Contact();


        }
        return empty;
    }

    public static String validateInput(String endPoint) throws ClientException {
        String path = "";
        if(startsWith(endPoint, "POST")){
            path = endPoint.substring("POST".length());
            requestMethod = RequestMethod.POST;

        } else if(startsWith(endPoint, "GET")){
            path = endPoint.substring("GET".length() + 1);
            requestMethod = RequestMethod.GET;

        } else if(startsWith(endPoint, "PUT")){
            path = endPoint.substring("PUT".length());
            requestMethod = RequestMethod.PUT;
        } else if(startsWith(endPoint, "DELETE")){
            path = endPoint.substring("DELETE".length());
            requestMethod = RequestMethod.DELETE;
        } else {
            throw new ClientException(ClientException.INVALID_REQUEST_METHOD);
        }

        // Validate path
        if(!startsWith(path.trim(), "/contact")){
            throw new ClientException(ClientException.INVALID_PATH);
        }

        return path;
    }

    private static String callAPI(String url, Contact contact){
        String results = "";
        switch (requestMethod){
            case POST:
                results = api.createContact(url, contact);
                break;
            case GET:
                Set<Contact> contactResults = api.getContacts(url);
                StringBuilder sb = new StringBuilder();
                for(Contact con : contactResults){
                    sb.append(con.toString());
                }
                results = sb.toString();
                break;
            case DELETE:
                results = api.deleteContact(url);
                break;
            case PUT:
                results = api.updateContact(url, contact);
                break;
            default:
                break;
        }

        return results;

    }

    private static boolean startsWith(String str1, String str2){
        return (str1.substring(0, Math.min(str1.length(), str2.length())) // Safe substring (takes min of length & str2 so doesn't go out of bounds)
                .toUpperCase() // Normalize toUpper, so that it will compare accurately
                .equals(str2.toUpperCase())); // Compare, and normalize toUpper, so that it will compare accurately
    }

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("ERROR. Please enter serverHost (your computer's IP) and serverPort (probably 8080) as command line arguments (in that order).");
        } else {
            String serverHost = args[0];
            String serverPort = args[1];

            api = new ServerAPI();
            api.init(serverHost, serverPort);

            Scanner scanner = new Scanner(System.in);
            int selection = 0;

            do {
                while (selection != 1 && selection != 2) {
                    displayMenu();
                    selection = scanner.nextInt();
                }

                switch (selection) {
                    case 1:
                        try {
                            System.out.print("Enter your endpoint now: ");
                            String endPoint = scanner.nextLine();

                            String path = validateInput(endPoint).trim();

                            Contact contact = new Contact();//promptForContactInformation(scanner);

                            String results = callAPI(path, contact);

                            System.out.println(results);


                        } catch (ClientException e) {
                            System.out.println(e.getMessage());
                        } /*catch (DataValidationException e) {
                            // Print problem.
                            System.out.println(e.getMessage());
                        }*/

                        break;
                    case 2:
                        break;
                }
            } while (selection != 2);


        }
    }
}
