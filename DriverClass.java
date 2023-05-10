
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

// Jack Sheehy C22468732
// Semester 2 Assignment 2
public class DriverClass {

    private static ArrayList<MusicProducts> products = new ArrayList<>(); // Define the arraylist to contain
    // MusicProduct objects.

    public static void saveToFile() { // Save music products to a file.
        ObjectOutputStream fileOut;
        try {
            fileOut = new ObjectOutputStream(new FileOutputStream("musicProducts.dat"));
            for (MusicProducts product : products) { // Iterate through the products ArrayList and write each object to
                // the file.
                fileOut.writeObject(product);
            } // Close the ObjectOutputStream and print a success message.
            fileOut.close();
            System.out.println("  All music products have been saved");
        } catch (IOException e) { // Print an error message if IO Expception occurs.
            System.out.println("  IO Error : " + e.getMessage());
        }
    }

    public boolean readFromFile() { // Read music products from a file.
        int index = 0;
        ObjectInputStream fileIn;
        MusicProducts product;

        try { // Create an ObjectInputStream.
            fileIn = new ObjectInputStream(new FileInputStream("musicProducts.dat"));
            System.out.println("  Opened file successfully");
            product = (MusicProducts) fileIn.readObject();
            index = 1; // Loop through the MusicProducts objects in the file.
            while (product != null) {
                products.add(product);
                product = (MusicProducts) fileIn.readObject();
                index++;
            } // Close the ObjectInputStream, return true if successful.
            fileIn.close();
            return true;
        } catch (IOException e) {
            if (index > 0) {
                return true;
            } else { // If an error occurs print an error message and return false.
                System.out.println("  Data file does not exist\n");
                return false;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("  Class Error : " + e.getMessage());
            return false;
        }
    }

    public static void mainMenu() { // Main menu with 3 Options.

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("\f");
            System.out.println("\n  Jack's Vinyl - Stock Control System");
            System.out.println(" =====================================");
            System.out.println("  [1] Customer Menu");
            System.out.println("  [2] Staff Menu");
            System.out.println("  [3] Exit System");

            input = scanner.nextLine();

            switch (input) { // Switch statement for menu, 3 Options.
                case "1":
                    dotDelay();
                    customerMenu(scanner); // Opens customer menu.
                    break;
                case "2":
                    staffMenu(scanner); // Opens staff menu, and password input.
                    break;
                case "3":
                    System.out.println("\f");
                    System.out.print("  Exiting System "); // Exit system.
                    printDelay(600);
                    System.out.print(".");
                    printDelay(600);
                    System.out.print(".");
                    printDelay(600);
                    System.out.print(".\n");
                    printDelay(800);
                    System.out.println("  Stock Control System Closed.\n");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("  Invalid choice. Please try again.");
            }
        }
    }

    private static void customerMenu(Scanner scanner) { // Customer Menu with 5 Options.
        boolean exitOption = false;
        while (!exitOption) {
            System.out.println("\f");
            System.out.println("\n           -Customer Menu- ");
            System.out.println(" =====================================");
            System.out.println("  [1] Display Vinyl for sale");
            System.out.println("  [2] Display Record Players for sale");
            System.out.println("  [3] Buy Vinyl");
            System.out.println("  [4] Buy Record Players");
            System.out.println("  [5] Search for Vinyl by Genre");
            System.out.println("  \n  Enter 'E' to Return to Main Menu");
            // display menu options
            String option = scanner.nextLine();
            switch (option.toUpperCase()) { // Switch statement for menu with 5 options.
                case "1":
                    Customer1(products); // Run "Customer1" Method. Re-used for the other cases.
                    while (true) {
                        String option1 = scanner.nextLine();
                        if (option1.equalsIgnoreCase("e")) {
                            break; // use break statement to exit the inner while loop and return to the customer
                            // menu
                        }
                    }
                    break; // use break statement to exit the switch statement and continue the outer while
                    // loop
                case "2":
                    Customer2(products);
                    while (true) {
                        String option2 = scanner.nextLine();
                        if (option2.equalsIgnoreCase("e")) {
                            break;
                        }
                    }
                    break;
                case "3":
                    Customer3(scanner);
                    while (true) {
                        String option3 = scanner.nextLine();
                        if (option3.equalsIgnoreCase("e")) {
                            break;
                        }
                    }
                    break;
                case "4":
                    Customer4(scanner);
                    while (true) {
                        String option4 = scanner.nextLine();
                        if (option4.equalsIgnoreCase("e")) {
                            break;
                        }
                    }

                    break;
                case "5":
                    Customer5(scanner);
                    while (true) {
                        String option5 = scanner.nextLine();
                        if (option5.equalsIgnoreCase("e")) {
                            break;
                        }
                    }
                    break;
                case "E":
                    exitOption = true; // set exitOption to true to exit the while loop in the customerMenu method
                    break;
                default:
                    System.out.println("  Invalid option. Please try again.");
            }
        }
    }

    public static String Customer1(ArrayList<MusicProducts> products) { // Display Vinyl
        System.out.println("\f");

        System.out.println("\n  [1] Displaying Vinyl for sale\n");

        boolean foundVinyl = false;
        for (MusicProducts product : products) { // For loop iterates through vinyl objects.
            if (product instanceof Vinyl) {
                foundVinyl = true;
                Vinyl vinyl = (Vinyl) product;
                System.out.println("  ALBUM:  " + vinyl.getAlbumName() + "\n ━━━━━━━━━━━━━━━━━━━━ " + "\n" // Prints
                        // vinyls
                        // relevant
                        // information.
                    + "  ARTIST: " + vinyl.getArtistName() + "\n" + "  PRICE: €‎" + vinyl.getPrice() + "\n"
                    + "  GENRE: " + vinyl.getGenre() + "\n" + "  RELEASE YEAR: " + vinyl.getAlbumYear()
                    + "\n  STOCK: " + vinyl.getStockLevel() + "\n");
            }
        }

        if (!foundVinyl) { // If no vinyl is found, print error message.
            System.out.println("  There are no vinyl records available for sale at this time.");
            System.out.println("\n  Enter 'E' to Return to Customer Menu");
            return "";
        }

        System.out.println("  \n  Enter 'E' to Return to Customer Menu");
        return "";
    }

    public static String Customer2(ArrayList<MusicProducts> products) { // Display Record Players
        System.out.println("\f");
        System.out.println("  [2] Displaying Record Players for sale\n");

        boolean foundRecordPlayer = false;
        for (MusicProducts product : products) { // For loop iterates through RecordPlayer objects.
            if (product instanceof RecordPlayer) {
                foundRecordPlayer = true;
                RecordPlayer recordPlayer = (RecordPlayer) product; // Prints record players relevent information.
                System.out.println("  BRAND: " + recordPlayer.getBrand() + "\n ━━━━━━━━━━━━━━━━━━━━ " + "\n  MODEL: "
                    + recordPlayer.getModel() + "\n" + "  PRICE: €‎" + recordPlayer.getPrice() + "\n  STOCK: "
                    + recordPlayer.getStockLevel() + "\n  COLOUR: " + recordPlayer.getColour() + "\n");
            }
        }

        if (!foundRecordPlayer) { // Error message if no record players are found.
            System.out.println("  There are no record players available for sale at this time.");
            System.out.println("\n  Enter 'E' to Return to Customer Menu");
            return "";
        }

        System.out.println("  \n  Enter 'E' to Return to Customer Menu");
        return "";
    }

    public static void Customer3(Scanner scanner) { // Buy Vinyl
        System.out.println("\f");

        boolean foundVinyl = false; // For loop iterates through vinyl objects.
        for (MusicProducts product : products) {
            if (product instanceof Vinyl) {
                foundVinyl = true;
                break;
            }
        }

        if (!foundVinyl) { // Error message if vinyl not found.
            System.out.println("  Sorry, there are no vinyl records in stock.\n");
            System.out.println("  Enter 'E' to return to the Customer Menu\n");
            return;
        }
        System.out.println("  - Buy Vinyl - \n");

        String albumName;
        boolean found = false;
        do {
            System.out.print("  Enter the name of the album you wish to purchase: ");

            albumName = scanner.nextLine();

            found = false; // search for album.
            for (MusicProducts product : products) {
                if (product instanceof Vinyl) {
                    Vinyl vinyl = (Vinyl) product;
                    if (vinyl.getAlbumName().equalsIgnoreCase(albumName)) {
                        found = true;
                        if (vinyl.getStockLevel() == 0) { // Out of stock message prints if stock = 0.
                            System.out.println("  Sorry, this vinyl is currently out of stock.");
                            break;
                        }
                        System.out.println("\n  ALBUM: " + vinyl.getAlbumName() + "\n" + "  ARTIST: " // album found.
                            + vinyl.getArtistName() + "\n" + "  PRICE: €‎" + vinyl.getPrice() + "\n");
                        System.out.print("  Enter the quantity you wish to purchase: "); // User inputs quantinty.
                        while (!scanner.hasNextInt()) {
                            System.out.println("  Invalid quantity. Please enter a valid number: "); // Error message if
                            // left blank.
                            scanner.next(); //
                        }
                        int purchaseQuantity = scanner.nextInt();
                        scanner.nextLine();

                        while (purchaseQuantity <= 0) { // Error Checking.
                            System.out.println("  Invalid quantity. Please enter a positive number: ");
                            purchaseQuantity = scanner.nextInt();
                            scanner.nextLine(); //
                        }

                        while (purchaseQuantity > vinyl.getStockLevel()) { // Insfficiunt stock error.
                            System.out.println(
                                "  Insufficient stock. Only " + vinyl.getStockLevel() + " copies are available.");
                            System.out.print("  Enter the quantity you wish to purchase: ");
                            purchaseQuantity = scanner.nextInt();
                            scanner.nextLine();
                        }

                        vinyl.setStockLevel(vinyl.getStockLevel() - purchaseQuantity); // Update stock level and display
                        // successful purchase.
                        System.out.println("\n  Successfully purchased " + purchaseQuantity + " copies of '"
                            + vinyl.getAlbumName() + "' by " + vinyl.getArtistName() + ".");

                        System.out.println("\n  Please input a Contact Name and Number:  \n"); // input contact info,
                        // used for reciept.

                        Scanner scanContactName = new Scanner(System.in);
                        System.out.println("  Contact Name:  ");
                        while (!scanContactName.hasNextLine()) { // ensures user inputs something.
                            System.out.println("  Invalid input, please enter a name:  ");
                            scanContactName.next();
                        }
                        String contactName = scanContactName.next();

                        Scanner scanContactNumber = new Scanner(System.in);
                        System.out.println("  Contact Number: (+353) ");
                        while (!scanContactNumber.hasNextInt()) { // ensures user inputs a number.
                            System.out.println("  Invalid input, please enter a number:  ");
                            scanContactNumber.next();
                        }
                        int contactNumber = scanContactNumber.nextInt();

                        System.out.println("  Name: " + contactName);
                        System.out.println("  Number: +353 " + contactNumber);

                        printDelay(600);
                        System.out.print(".");
                        printDelay(600);
                        System.out.print(".");
                        printDelay(600);
                        System.out.print(".\n");
                        System.out.println("\f");

                        printDelay(600);

                        Random number = new Random();
                        int orderNumber = number.nextInt(9999999) + 1; // generates a random number between 1 and
                        // 9999999 for order number.

                        String date = new SimpleDateFormat("  dd-MM-yyyy").format(Calendar.getInstance().getTime()); // finds
                        // real
                        // date
                        // of
                        // purchase.

                        System.out.println("\n ********************************* ");

                        System.out.println("  Order: #" + orderNumber + "   " + date + "\n ");

                        System.out.println(vinyl);
                        System.out.println(" ---------------------");

                        System.out.println("  Customer Name: " + contactName);
                        System.out.println("  Contact Number: +353 " + contactNumber);
                        System.out.println(" ---------------------");

                        System.out.println("  Units Bought: " + purchaseQuantity);

                        System.out.println("  Total: €‎" + vinyl.getPrice() * purchaseQuantity);//

                        System.out.println("\n  Thank You!");

                        System.out.println("\n  ******************************* ");
                    }
                }
            }

            if (!found) {
                System.out.println("  Album not found.");
                System.out.println("  [1] Try Again");
                System.out.println("  [2] Exit");
                String choice; // chose a string over an int to reduce the amount of error checking needed.
                do {

                    choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        break; // Continue the loop to ask for album name again
                    } else if (choice.equals("2")) {
                        System.out.println("  Enter 'E' to Return to Customer Menu");
                        return; // Exit the method and return to the customer menu

                    } else {
                        System.out.println("  Invalid choice.");
                        System.out.println("  [1] Try Again");
                        System.out.println("  [2] Exit");
                    }
                } while (true);
            }
        } while (!found);

        System.out.println("\n  Enter 'E' to Return to Customer Menu");
    }

    public static void Customer4(Scanner scanner) { // Buy Record Players
        System.out.println("\f");

        boolean foundPlayer = false;
        for (MusicProducts product : products) { // // For loop iterates through recordPlayer objects.
            if (product instanceof RecordPlayer) {
                foundPlayer = true;
                break;
            }
        }

        if (!foundPlayer) { // Error message if player not found.
            System.out.println("  Sorry, there are no record players in stock.");
            System.out.println("\n  Enter 'E' to return to the Customer Menu");
            return;
        }
        System.out.println("  - Displaying Record Players for sale -");

        String wantedPlayer;
        boolean found = false;
        do {
            System.out.print("  Enter the brand or model of the record player you wish to purchase: ");

            wantedPlayer = scanner.nextLine();

            found = false;
            for (MusicProducts product : products) {
                if (product instanceof RecordPlayer) { // Search for record player.
                    RecordPlayer recordPlayer = (RecordPlayer) product;
                    if (recordPlayer.getBrand().equalsIgnoreCase(wantedPlayer)
                    || recordPlayer.getModel().equalsIgnoreCase(wantedPlayer)) {
                        found = true;
                        if (recordPlayer.getStockLevel() == 0) {
                            System.out.println("  Sorry, this record player is currently out of stock.");
                            break;
                        }
                        System.out.println("\n  BRAND: " + recordPlayer.getBrand() + "\n" + "  MODEL: " // If found
                                // display
                                // player.
                            + recordPlayer.getModel() + "\n" + "  PRICE: €‎" + recordPlayer.getPrice() + "\n");
                        System.out.print("  Enter the quantity you wish to purchase: "); // Ask user for quantity.
                        while (!scanner.hasNextInt()) {
                            System.out.println("  Invalid quantity. Please enter a positive integer: ");
                            scanner.next();
                        }
                        int purchaseQuantity = scanner.nextInt();
                        scanner.nextLine();

                        while (purchaseQuantity <= 0) {
                            System.out.println("  Invalid quantity. Please enter a positive integer: ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("  Invalid quantity. Please enter a valid number: ");
                                scanner.next();
                            }
                            purchaseQuantity = scanner.nextInt();
                            scanner.nextLine();
                        }

                        while (purchaseQuantity > recordPlayer.getStockLevel()) {
                            System.out.println("  Insufficient stock. Only " + recordPlayer.getStockLevel()
                                + " units are available.");
                            System.out.print("  Enter the quantity you wish to purchase: "); // Ask user for quantity.
                            while (!scanner.hasNextInt()) {
                                System.out.println("  Invalid quantity. Please enter a valid number: ");
                                scanner.next();
                            }
                            purchaseQuantity = scanner.nextInt();
                            scanner.nextLine();
                        }

                        recordPlayer.setStockLevel(recordPlayer.getStockLevel() - purchaseQuantity); // Update stock
                        // level.
                        System.out.println("  Successfully purchased " + purchaseQuantity + " "
                            + recordPlayer.getBrand() + " " + recordPlayer.getModel() + " Record Player(s).");
                        System.out.println("\n  Please input a Contact Name and Number:  \n"); // input contact info,
                        // used for reciept.

                        Scanner scanContactName = new Scanner(System.in);
                        System.out.println("  Contact Name:  ");
                        while (!scanContactName.hasNextLine()) { // ensures user inputs something.
                            System.out.println("  Invalid input, please enter a name:  ");
                            scanContactName.next();
                        }
                        String contactName = scanContactName.next();

                        Scanner scanContactNumber = new Scanner(System.in);
                        System.out.println("  Contact Number: (+353) ");
                        while (!scanContactNumber.hasNextInt()) { // ensures user inputs a number.
                            System.out.println("  Invalid input, please enter a number:  ");
                            scanContactNumber.next();
                        }
                        int contactNumber = scanContactNumber.nextInt();

                        System.out.println("  Name: " + contactName);
                        System.out.println("  Number: +353 " + contactNumber);

                        printDelay(600);
                        System.out.print(".");
                        printDelay(600);
                        System.out.print(".");
                        printDelay(600);
                        System.out.print(".\n");
                        System.out.println("\f");

                        printDelay(600);

                        Random number = new Random();
                        int orderNumber = number.nextInt(9999999) + 1; // generates a random number between 1 and
                        // 9999999 for order number.

                        String date = new SimpleDateFormat("  dd-MM-yyyy").format(Calendar.getInstance().getTime()); // finds
                        // real
                        // date
                        // of
                        // purchase.

                        System.out.println("\n ********************************* ");

                        System.out.println("  Order: #" + orderNumber + "   " + date + "\n ");

                        System.out.println(recordPlayer);
                        System.out.println(" ---------------------");

                        System.out.println("  Customer Name: " + contactName);
                        System.out.println("  Contact Number: +353 " + contactNumber);
                        System.out.println(" ---------------------");

                        System.out.println("  Units Bought: " + purchaseQuantity);

                        System.out.println("  Total: €‎" + recordPlayer.getPrice() * purchaseQuantity);//

                        System.out.println("\n  Thank You!");

                        System.out.println("\n  ******************************* ");
                    }
                }
            }

            if (!found) {
                System.out.println("  Record player not found.");
                System.out.println("  [1] Try Again");
                System.out.println("  [2] Exit");
                String choice;
                do {

                    choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        break; // Continue the loop to ask for brand name again
                    } else if (choice.equals("2")) {
                        System.out.println("  Enter 'E' to return to the Customer Menu");
                        return; // Exit the method and return to the customer menu
                    } else {
                        System.out.println("  Invalid choice.");
                        System.out.println("  [1] Try Again");
                        System.out.println("  [2] Exit");
                    }
                } while (true);
            }
        } while (!found);

        System.out.println("\n  Enter 'E' to Return to Customer Menu\n");

    }

    public static void Customer5(Scanner scanner) { // Search Vinyl by Genre.

        System.out.println("\f");
        System.out.println("  - Search for Vinyl by Genre - \n");

        ArrayList<String> genres = new ArrayList<String>(); // create an array list of genres.
        for (MusicProducts product : products) {
            if (product instanceof Vinyl && !genres.contains(((Vinyl) product).getGenre())) { // finds genres and checks
                // for duplicate genres.

                genres.add(((Vinyl) product).getGenre());
            }
        }

        if (genres.isEmpty()) {
            System.out.println("  No Genres found.");
            System.out.println("  Enter 'E' to return to the Customer Menu");
            return;
        }

        for (int i = 0; i < genres.size(); i++) { // Display the genres and the number of albums with each genre.
            String genre = genres.get(i);
            int count = 0;
            for (MusicProducts product : products) {
                if (product instanceof Vinyl && ((Vinyl) product).getGenre().equals(genre)) {
                    count++;
                }
            }
            System.out.println("  [" + (i + 1) + "] " + genre + " (" + count + ")");
        }

        System.out.print("\n  Enter the number of the genre you want to display : \n "); // Ask user to select a genre.
        int selectedGenreIndex = scanner.nextInt();

        int albumIndex = 1; // Display all the albums in the selected genre.
        boolean vinylFound = false;
        for (MusicProducts product : products) {
            if (product instanceof Vinyl && ((Vinyl) product).getGenre().equals(genres.get(selectedGenreIndex - 1))) {
                System.out.println(product.toString());
                vinylFound = true;
            }
        }
        if (!vinylFound) {
            System.out.println("  No vinyls found in selected genre.");
        }
        System.out.println("  Enter 'E' to return to the Customer Menu");
    }

    private static void staffMenu(Scanner scanner) { // Staff Menu with 4 Options.

        System.out.println("\f");
        int attempts = 0; // Password for staff menu, including 3 attempts.
        boolean correctPassword = false;
        while (!correctPassword && attempts < 3) {
            System.out.print("\n  Enter staff password: ");
            String password = scanner.nextLine();
            if (password.equals("9041")) { // Staff password.
                dotDelay();
                correctPassword = true;
            } else {
                attempts++;
                if (attempts < 3) {
                    dotDelay();
                    System.out.println("\n  Incorrect password. Please try again.");
                } else {
                    dotDelay();
                    System.out.println("\n  Incorrect password. Maximum attempts exceeded.");
                    return;
                }
            }
        }

        boolean exitOption = false;
        while (!exitOption) {
            System.out.println("\f");
            System.out.println("\n             -Staff Menu- ");
            System.out.println(" =====================================");
            System.out.println("  [1] Add a new product - Vinyl");
            System.out.println("  [2] Add a new product - Record Player");
            System.out.println("  [3] Update stock level for product");
            System.out.println("  [4] Display Low Stock Products");
            System.out.println("  \n  Enter 'E' to Return to Main Menu");

            String option = scanner.nextLine();
            switch (option.toUpperCase()) { // Switch statement for staff menu.
                case "1":
                    Staff1();
                    while (true) {
                        String option1 = scanner.nextLine();
                        if (option1.equalsIgnoreCase("e")) {
                            break; // use break statement to exit the inner while loop and return.

                        }
                    }
                    break;
                case "2":
                    Staff2();
                    while (true) {
                        String option2 = scanner.nextLine();
                        if (option2.equalsIgnoreCase("e")) {
                            break;
                        }
                    }
                    break;
                case "3":
                    Staff3();
                    while (true) {
                        String option3 = scanner.nextLine();
                        if (option3.equalsIgnoreCase("e")) {
                            break;
                        }
                    }
                    break;
                case "4":
                    Staff4();
                    while (true) {
                        String option4 = scanner.nextLine();
                        if (option4.equalsIgnoreCase("e")) {
                            break;
                        }
                    }

                    break;
                case "E":
                    exitOption = true; // set exitOption to true to exit the while loop.
                    break;
                default:
                    System.out.println("  Invalid option. Please try again.");
            }
        }

    }

    public static void Staff1() { // Add New Vinyl Product.
        System.out.println("\f");
        Scanner scanner = new Scanner(System.in);
        System.out.println("  [1] Add a new product - Vinyl \n");

        System.out.print("  Enter product code: "); // Enter a product code.
        String productCode;
        productCode = scanner.nextLine();
        while (productCode.isEmpty()) {
            System.out.print("  Please enter a valid product code: ");
            productCode = scanner.nextLine();
        }

        System.out.print("  Enter stock level: "); // Enter stock level.
        int stockLevel;
        while (!scanner.hasNextInt()) {
            System.out.print("  Please enter a valid stock level: ");
            scanner.next();
        }
        stockLevel = scanner.nextInt();
        scanner.nextLine();
        while (stockLevel < 0) {
            System.out.print("  Please enter a valid stock level: ");
            while (!scanner.hasNextInt()) {
                System.out.print("  Please enter a valid stock level: ");
                scanner.next();
            }
            stockLevel = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("  Enter product price: "); // Enter prodcut price.
        double productPrice;
        while (!scanner.hasNextDouble()) {
            System.out.print("  Please enter a valid product price: ");
            scanner.next();
        }
        productPrice = scanner.nextDouble();  
        scanner.nextLine();
        while (productPrice < 0) {
            System.out.print("  Please enter a valid product price: ");
            while (!scanner.hasNextDouble()) {
                System.out.print("  Please enter a valid product price: ");
                scanner.next();
            }
            productPrice = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.print("  Enter artist name: "); // Enter artists name
        String artistName;
        artistName = scanner.nextLine();
        while (artistName.isEmpty()) {
            System.out.print("  Please enter a valid artist name: ");
            artistName = scanner.nextLine();
        }

        System.out.print("  Enter album name: ");  // Enter album name.
        String albumName;
        albumName = scanner.nextLine();
        while (albumName.isEmpty()) {
            System.out.print("  Please enter a valid album name: ");
            albumName = scanner.nextLine();
        }

        System.out.print("  Enter genre: "); // Enter a genre.
        String genre;
        genre = scanner.nextLine();
        while (genre.isEmpty()) {
            System.out.print("  Please enter a valid genre: ");
            genre = scanner.nextLine();
        }

        System.out.print("  Enter album year: "); // Enter album year.
        int albumYear;
        while (!scanner.hasNextInt()) {
            System.out.print("  Please enter a valid album year: ");
            scanner.next();
        }
        albumYear = scanner.nextInt();
        scanner.nextLine();
        while (albumYear < 1000) {
            System.out.print("  Please enter a valid album year: "); 
            while (!scanner.hasNextInt()) {
                System.out.print(" Please enter a valid album year: ");
                scanner.next();
            }
            albumYear = scanner.nextInt();
            scanner.nextLine();
        }
        Vinyl newVinyl = new Vinyl(productCode, stockLevel, productPrice, artistName, albumName, genre, albumYear);
        products.add(newVinyl); // Add new vinyl to arraylist.

        System.out.println("  " + newVinyl.getAlbumName() + " has been added successfully!");

        System.out.println("\n  Enter 'E' to return to Staff Menu");

    }

    public static void Staff2() { // Add New Record Player.
        System.out.println("\f");
        Scanner scanner = new Scanner(System.in);
        System.out.println("  [2] Add a new product - Record Player");

        System.out.print("  Enter product code: ");  // Input relevent details for record player.
        String productCode;
        productCode = scanner.nextLine();
        while (productCode.isEmpty()) {
            System.out.print("  Please enter a valid product code: ");
            productCode = scanner.nextLine();
        }

        System.out.print("  Enter stock level: ");
        int stockLevel;
        while (!scanner.hasNextInt()) {
            System.out.print("  Invalid input. Please enter a valid stock level: ");
            scanner.next();
        }
        stockLevel = scanner.nextInt();
        scanner.nextLine();

        System.out.print("  Enter a price value: ");
        double productPrice;
        while (!scanner.hasNextDouble()) {
            System.out.print("  Invalid input. Please enter a valid price: ");
            scanner.next();
        }
        productPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("  Enter brand: ");
        String brand;
        brand = scanner.nextLine();
        while (brand.isEmpty()) {
            System.out.print("  Please enter a valid brand: ");
            brand = scanner.nextLine();
        }

        System.out.print("  Enter model: ");
        String model;
        model = scanner.nextLine();
        while (model.isEmpty()) {
            System.out.print("  Please enter a valid model: ");
            model = scanner.nextLine();
        }

        System.out.println("  Enter colour: ");
        String colour;
        colour = scanner.nextLine();
        while (colour.isEmpty()) {
            System.out.println("  Please enter a colour: ");
            colour = scanner.nextLine();
        }

        RecordPlayer newRecordPlayer = new RecordPlayer(productCode, stockLevel, productPrice, brand, model, colour);
        products.add(newRecordPlayer); // Add new record player to the array list.

        System.out.println(
            "  " + newRecordPlayer.getBrand() + " " + newRecordPlayer.getModel() + " has been added successfully!");
        System.out.println("  \n  Enter 'E' to Return to Staff Menu");
    }

    public static void Staff3() { // Update Stock Level for Product.
        System.out.println("\f");
        System.out.println("  -Update Stock Level for Product-\n");

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("  [1] Update Vinyl Stock");
            System.out.println("  [2] Update Record Player Stock");

            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) {  // Select vinyl or record player to update stock.
                choice = Integer.parseInt(input); // convert string input to int.
                break;
            } else {
                System.out.println("  Invalid input. Please enter 1 or 2.");
            }
        }

        if (choice == 1) {
            boolean albumFound;
            do {
                albumFound = false;
                System.out.println("  Enter the album you want to update stock for:");
                String albumName = scanner.nextLine();

                for (MusicProducts product : products) { // Search for vinyl.
                    if (product instanceof Vinyl && ((Vinyl) product).getAlbumName().equalsIgnoreCase(albumName)) {
                        albumFound = true;
                        dotDelay();
                        System.out.println("\n  Current stock level: " + product.getStockLevel());
                        System.out.println("  Enter the number of units to add to the stock level:"); // Update stock level.
                        int stockToAdd = Integer.parseInt(scanner.nextLine()); // convert to int.
                        product.setStockLevel(product.getStockLevel() + stockToAdd);
                        dotDelay();
                        System.out.println("  Stock level updated. New stock level: " + product.getStockLevel());
                        System.out.println("  \n  Enter 'E' to Return to Staff Menu");
                        break;
                    }
                }

                if (!albumFound) {
                    dotDelay();
                    System.out.println("  Album not found.");
                    System.out.println("  [1] Try Again");
                    System.out.println("  [2] Exit");

                    while (true) {
                        String input = scanner.nextLine();

                        if (input.equals("1")) {
                            break; // Continue the loop to ask for album name again
                        } else if (input.equals("2")) {
                            System.out.println("  Enter 'E' to Return to Staff Menu");
                            return; // Exit the method and return to the customer menu
                        } else {
                            dotDelay();
                            System.out.println("  Invalid choice.");
                            System.out.println("  [1] Try Again");
                            System.out.println("  [2] Exit");
                        }
                    }
                }
            } while (!albumFound);
        }
        if (choice == 2) {
            boolean recordPlayerFound;
            do {
                recordPlayerFound = false;
                System.out.println("  Enter the brand or model you want to update stock for:");
                String searchString = scanner.nextLine();

                for (MusicProducts product : products) {
                    if (product instanceof RecordPlayer // Find record player from brand OR model.
                    && (((RecordPlayer) product).getBrand().equalsIgnoreCase(searchString)
                        || ((RecordPlayer) product).getModel().equalsIgnoreCase(searchString))) {
                        recordPlayerFound = true;
                        dotDelay();
                        System.out.println("  Current stock level: " + product.getStockLevel());
                        System.out.println("  Enter the number of units to add to the stock level:");
                        int stockToAdd = Integer.parseInt(scanner.nextLine()); // convert to int.
                        product.setStockLevel(product.getStockLevel() + stockToAdd); // Update stock level.
                        dotDelay();
                        System.out.println("  Stock level updated. New stock level: " + product.getStockLevel());
                        break;
                    }
                }

                if (!recordPlayerFound) {
                    dotDelay();
                    System.out.println("  Record player not found.");
                    System.out.println("  [1] Try Again");
                    System.out.println("  [2] Exit");

                    while (true) {

                        String input = scanner.nextLine();

                        if (input.equals("1")) {
                            break; // Continue the loop to ask for brand or model again
                        } else if (input.equals("2")) {
                            System.out.println("  Enter 'E' to Return to Staff Menu");
                            return; // Exit the method and return to the customer menu
                        } else {
                            dotDelay();
                            System.out.println("  Invalid choice.");
                            System.out.println("  [1] Try Again");
                            System.out.println("  [2] Exit");
                        }
                    }
                }
            } while (!recordPlayerFound);
        }
    }

    public static void Staff4() { // Display low stock products.
        System.out.println("\f");
        System.out.println("  -Display Low Stock Products-\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("  [1] Low Vinyl Stock");
        System.out.println("  [2] Low Record Player Stock");

        int choice = scanner.nextInt();

        if (choice == 1) {

            for (MusicProducts product : products) {
                if (product instanceof Vinyl) {
                    Vinyl vinyl = (Vinyl) product; // Displays vinyl with stock less than 10.
                    if (vinyl.getStockLevel() <= 10) {
                        System.out.println(vinyl.toStringStock());
                    }
                }
            }
        } else if (choice == 2) {

            for (MusicProducts product : products) {
                if (product instanceof RecordPlayer) {
                    RecordPlayer record = (RecordPlayer) product; // Displays record players with stock less than 5.
                    if (record.getStockLevel() <= 5) {
                        System.out.println(record.toStringStock());
                    }
                }
            }
        } else {
            System.out.println("Invalid choice");
        }
        System.out.println(" Enter 'E' to Return to Staff Menu\n");
    }

    public static void preInputProducts() { // "Hard-wired in" Products.

        // "hard-wired in" products (vinyl).
        Vinyl vinyl1 = new Vinyl("blondeVinyl", 8, 49.99, "Frank Ocean", "Blonde", "R&B", 2016);
        Vinyl vinyl2 = new Vinyl("damnVinyl", 35, 39.99, "Kendrick Lamar", "DAMN.", "Rap", 2017);
        Vinyl vinyl3 = new Vinyl("igorVinyl", 50, 29.99, "Tyler, The Creator", "IGOR", "Soul", 2019);
        Vinyl vinyl4 = new Vinyl("graduationVinyl", 25, 44.99, "Kanye West", "Graduation", "Rap", 2007);
        Vinyl vinyl5 = new Vinyl("postVinyl", 20, 24.99, "Björk", "Post", "Pop", 1995);
        Vinyl vinyl6 = new Vinyl("thrillerVinyl", 50, 19.99, "Michael Jackson", "Thriller", "Pop", 1982);
        Vinyl vinyl7 = new Vinyl("rumoursVinyl", 10, 29.99, "Fleetwood Mac", "Rumours", "Rock", 1977);
        Vinyl vinyl8 = new Vinyl("abbeyRoadVinyl", 15, 49.99, "The Beatles", "Abbey Road", "Rock", 1969);
        Vinyl vinyl9 = new Vinyl("kindOfBlueVinyl", 20, 29.99, "Miles Davis", "Kind of Blue", "Jazz", 1959);
        Vinyl vinyl10 = new Vinyl("theWallVinyl", 40, 59.99, "Pink Floyd", "The Wall", "Rock", 1979);

        // "hard-wired in" products (record players).
        RecordPlayer RecordPlayer1 = new RecordPlayer("sonyPlayer", 65, 299.99, "Sony", "PS-LX310BT", "Black");
        RecordPlayer RecordPlayer2 = new RecordPlayer("musePlayer", 80, 99.99, "Muse", "MT-103DB", "Grey");
        RecordPlayer RecordPlayer3 = new RecordPlayer("audioTechnicaPlayer", 25, 199.99, "Audio-Technica", "AT-453ST",
                "Black");
        RecordPlayer RecordPlayer4 = new RecordPlayer("crosleyPlayer", 95, 149.99, "Crosley", "C6-TW165", "White");
        RecordPlayer RecordPlayer5 = new RecordPlayer("denonPlayer", 50, 399.99, "Denon", "DP-300F", "Silver");
        RecordPlayer RecordPlayer6 = new RecordPlayer("proJectPlayer", 6, 699.99, "Pro-Ject", "Debut Carbon DC",
                "Black");
        RecordPlayer RecordPlayer7 = new RecordPlayer("pioneerPlayer", 20, 299.99, "Pioneer", "PL-30-K", "Black");
        RecordPlayer RecordPlayer8 = new RecordPlayer("regamPlayer", 15, 599.99, "Rega", "Planar 1 Plus", "White");
        RecordPlayer RecordPlayer9 = new RecordPlayer("audioLabPlayer", 10, 799.99, "Audio Lab", "ML-One", "White");
        RecordPlayer RecordPlayer10 = new RecordPlayer("rotelPlayer", 3, 599.99, "Rotel", "RP-1000 MK2", "Black");
        System.out.println("\f");

        System.out.println("\n  Would you like to populate the Array List \n       with 'pre-inputted' products? \n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("  [1] 'Yes'");
        System.out.println("  [2] 'No'");
        int choice = scanner.nextInt();

        if (choice == 1) {

            products.add(vinyl1);
            products.add(vinyl2);
            products.add(vinyl3);
            products.add(vinyl4);
            products.add(vinyl5);
            products.add(vinyl6);
            products.add(vinyl7);
            products.add(vinyl8);
            products.add(vinyl9);
            products.add(vinyl10);

            products.add(RecordPlayer1);
            products.add(RecordPlayer2);
            products.add(RecordPlayer3);
            products.add(RecordPlayer4);
            products.add(RecordPlayer5);
            products.add(RecordPlayer6);
            products.add(RecordPlayer7);
            products.add(RecordPlayer8);
            products.add(RecordPlayer9);
            products.add(RecordPlayer10);
        }
    }

    private static void printDelay(Integer delay) { // Print Delay to add realism.

        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignore) {
        }
    }

    private static void dotDelay() { // 3 Dot Delay "..."

        try {
            System.out.print("  ");
            Thread.sleep(400);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
        } catch (InterruptedException ignore) {
        }
    }

    public static void main(String[] args) {

        preInputProducts(); // Used for testing the program.

        mainMenu(); 

    }

}
