/**
 * CSE 214 Homework #1
 * @author Joseph Cullen
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class CollectionManager {
    /**
     * The main method runs a menu driven application which first creates two
     * empty CardCollections (referred to by the user as A and B), and then
     * prompts the user for a command selecting the operation. Once an operation
     * is selected, the program prompts for any additional information required
     * to perform the operation, and then actually performs the operation. The
     * operations, their action letter, and additional information required are
     * listed below.
     */
    public static void main(String[] args) {
        String input; // String to hold user input
        final int MAX_COLLECTIONS = 2;
        CardCollection[] collections = new CardCollection[MAX_COLLECTIONS];
        for (int i = 0; i < MAX_COLLECTIONS; i++) {
            collections[i] = new CardCollection("Collection "
                + (char) ('A' + i));
        }
        Scanner scan = new Scanner(System.in);

        System.out.println("Homework #1: Baseball Card Manager:");

        while (true) {
            printMenu();
            try {
                input = scan.next().toUpperCase();

                System.out.print("\n");

                if (input.equals("A")) {
                    addCard(collections);
                    continue;
                }

                if (input.equals("C")) {
                    copyCard(collections);
                    continue;
                }

                if (input.equals("E")) {
                    changePrice(collections);
                    continue;
                }

                if (input.equals("G")) {
                    getCard(collections);
                    continue;
                }

                if (input.equals("L")) {
                    locateCard(collections);
                    continue;
                }

                if (input.equals("N")) {
                    changeName(collections);
                    continue;
                }

                if (input.equals("P")) {
                    printAllCards(collections);
                    continue;
                }
                if (input.equals("R")) {
                    removeCard(collections);
                    continue;
                }
                if (input.equals("S")) {
                    size(collections);
                    continue;
                }
                if (input.equals("T")) {
                    tradeCards(collections);
                    continue;
                }
                if (input.equals("V")) {
                    totalValue(collections);
                    continue;
                }
                if (input.equals("Q")) {
                    break;
                }
                System.out.println("You did not select a valid operation.");
            } catch (IllegalArgumentException ex) {
                System.out.println("\n" + ex.getMessage());
                continue;
            } catch (FullCollectionException ex) {
                System.out.println("This collection is full");
                continue;
            }
        }
        scan.close();
        System.out.println("Quitting.");

    }

    /**
     * Prints the menu. Which shows the user all the options available to work
     * with his collections 
     * <dt><b>Postcondition</b><dd>The menu has been displayed to the user
     */
    public static void printMenu() {
        System.out.println("\nMain menu: \n");

        System.out.println("A) Add Card");
        System.out.println("C) Copy");
        System.out.println("E) Update Price");
        System.out.println("G) Get Card");
        System.out.println("L) Locate Card");
        System.out.println("N) Update name");
        System.out.println("P) Print All Cards");
        System.out.println("R) Remove Card");
        System.out.println("S) Size");
        System.out.println("T) Trade");
        System.out.println("V) Value");
        System.out.println("Q) Quit");

        System.out.print("\nSelect an operation: ");
    }

    /**
     * Constructs a card using input from the user
     * @param collections The collections available to interact with
     * @throws FullCollectionException When the collection is full
     */
    public static void addCard(CardCollection[] collections)
        throws FullCollectionException {
        CardCollection curCol = collections[inputCollection()];
        BaseballCard toAdd = new BaseballCard(inputName(), inputManufacturer(),
          inputYear(), inputSize(), inputPrice());
        int pos = inputPosition();
        curCol.addCard(toAdd, pos);

        System.out.println("\nAdded " + toAdd.toString() + " at position "
          + pos + " of " + curCol.getName());
    }

    /**
     * Copies the card at the specified position from the first collection to 
     * the end of the second specified collection. This copy is not affected by
     * changes made to the original and vice versa.
     * @param collections The collections available to interact with
     * @throws FullCollectionException When the collection is full
     */
    public static void copyCard(CardCollection[] collections)
        throws FullCollectionException {
        CardCollection curCol = collections[inputCollection("Enter the collection to copy from: ")];
        int pos = inputPosition();
        CardCollection otherCol = collections[inputCollection("Enter the collection to copy to: ")];
        BaseballCard toAdd = (BaseballCard) curCol.getCard(pos).clone();

        otherCol.addCard(toAdd);

        System.out.println("\nCopied " + toAdd.toString() + " into position "
          + (pos + 1) + " of " + curCol.getName());

    }

    /**
     * Change the price of the card in the user specified collection in the 
     * user specified position to the user specified price
     * @param collections The collections available to interact with
     */
    public static void changePrice(CardCollection[] collections) {
        CardCollection curCol = collections[inputCollection()];
        int pos = inputPosition();

        BaseballCard search = curCol.getCard(pos);// rename vars
        String oldPrice = search.getFormattedPrice();

        search.setPrice(inputPrice());

        String newPrice = search.getFormattedPrice();

        System.out.println("\nChanged price of " + curCol.getName()
          + " position " + pos + " from " + oldPrice + " to " + newPrice + ".");
    }

    /**
     * Prints out a comma separated list of the attributes of a card at the 
     * user specified position
     * @param The collections available to interact with
     */
    public static void getCard(CardCollection[] collections) {
        CardCollection curCol = collections[inputCollection()];
        int index = inputPosition();

        BaseballCard inputCard = curCol.getCard(index);
        System.out.println("The card in " + curCol.getName() + " at position "
          + index + " is " + inputCard.toString());
    }

    /**
     * Locates a card with all of the values that the user specifies
     * @param collections The collections available to interact with
     */
    public static void locateCard(CardCollection[] collections) {
        BaseballCard search = new BaseballCard(inputName(),
          inputManufacturer(), inputYear(), inputSize(), inputPrice());

        System.out.print("\n");

        for (int i = 0; i < collections.length; i++) {
            CardCollection curCol = collections[i];
            if (curCol.exists(search)) {
                System.out.println("This card is in " + curCol.getName() + ".");
            } else {
                System.out.println("This card is not in " + curCol.getName()
                  + ".");
            }
        }
    }

    /**
     * Change the name of the card in the user specified collection in the 
     * user specified position to the user specified name
     * @param collections The collections available to interact with
     */
    public static void changeName(CardCollection[] collections) {
        CardCollection curCol = collections[inputCollection()];
        int pos = inputPosition();

        BaseballCard search = curCol.getCard(pos);// rename vars
        String oldName = search.getName();

        search.setName(inputName());
        String newName = search.getName();

        System.out.println("\nChanged name of " + curCol.getName()
           + " position " + pos + " from \"" + oldName + "\" to \"" + newName
          + "\".");
    }

    /**
     * Prints a neatly formatted table of all cards in both collections
     * @param collections The collections available to interact with
     */
    public static void printAllCards(CardCollection[] collections) {
        for (int i = 0; i < collections.length; i++) {
            CardCollection curCol = collections[i];
            System.out.println(curCol.getName() + ":\n");
            curCol.printAllCards();
        }
    }

    /**
     * Removes the card from the user specified position in the user specified 
     * location
     * @param collections The collections available to interact with
     */
    public static void removeCard(CardCollection[] collections) {
        CardCollection curCol = collections[inputCollection("Enter the "
          + "collection to remove from: ")];
        int index = inputPosition();
        System.out.println("\nRemoved " + curCol.cardToString(index) + " from "
          + curCol.getName());
        curCol.removeCard(index);

    }

    /**
     * Prints the number of cards in each collection
     * @param collections The collections available to interact with
     */
    public static void size(CardCollection[] collections) {
        for (int i = 0; i < collections.length; i++) {
            CardCollection curCol = collections[i];
            System.out.println(curCol.getName() + " has " + curCol.size()
              + " cards.");
        }
    }

    /**
     * Trade cards between the user specified position of collection A to the 
     * user specified position of collection B
     * @param collections The collections available to interact with
     */
    public static void tradeCards(CardCollection[] collections)
        throws FullCollectionException {
        int posA = inputPosition("Enter the position of the card to trade from "
          + "collection A: ");
        int posB = inputPosition("Enter the position of the card to trade from "
          + "collection B: ");
        collections[0].trade(collections[1], posA, posB);

        System.out.println("\nTraded "
          + collections[1].getCard(posB).toString() + " for "
          + collections[0].getCard(posA).toString());

    }

    /**
     * Prints the total value of all the collections
     * @param collections The collections available to interact with
     */
    public static void totalValue(CardCollection[] collections) {
        for (int i = 0; i < collections.length; i++) {
            CardCollection curCol = collections[i];
            System.out.println("The total value of " + curCol.getName()
              + " is $" + curCol.totalValue() + ". ");
        }
    }

    /**
     * Lets user input the collection and displays the default message
     * "Enter the collection: "
     * 
     * @return The int representing the collection. 0 for Collection A and 1 for
     *         Collection B
     */
    public static int inputCollection() {
        return inputCollection("Enter the collection: ");
    }

    /**
     * Lets user input the collection.
     * @param message The message displayed to the user
     * @return The int representing the collection. 0 for Collection A and 1 for
     * Collection B
     */
    public static int inputCollection(String message) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String s = sc.next().toUpperCase();

            if (s.equals("A")) {
                return 0;
            } else if (s.equals("B")) {
                return 1;
            } else {
                System.out.println("\nYou entered an invalid collection"
                  + " name.\n");
            }
        }

    }

    /**
     * Lets user input the name.
     * @return The name the user entered
     */
    public static String inputName() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the name: ");
            String s = sc.nextLine();

            if (s.length() <= 25) {
                return s;
            } else {
                System.out.println("\nThe name cannot be longer than 25 "
                  + "characters.\n");
                continue;
            }
        }

    }

    /**
     * Lets user input the manufacturer.
     * @return The manufacturer the user entered
     */
    public static String inputManufacturer() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the manufacturer: ");
            String s = sc.nextLine();
            if (s.length() <= 25) {
                return s;
            } else {
                System.out.println("\nThe manufacturer cannot be longer than 25"
                  + " characters.\n");
                continue;
            }

        }
    }

    /**
     * Lets user input the year.
     * @return The year the user entered
     * */
    public static int inputYear() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the year: ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("\nYou entered an invalid year.\n");
                sc.nextLine();
                continue;
            }
        }

    }

    /**
     * Lets user input the price.
     * @return The price the user entered
     */
    public static double inputPrice() {
        Scanner sc = new Scanner(System.in);
        double price;

        while (true) {
            System.out.print("Enter the price: ");
            try {
                price = sc.nextDouble();

                if (price < 0) {
                    System.out.println("\nYour price cannot be negative.\n");
                    continue;
                }
                return price;
            } catch (InputMismatchException ex) {
                System.out.println("\nYou entered an invalid price.\n");
                continue;
            }
        }
    }

    /**
     * Lets user input the position and displays the default message
     * "Enter the position: "
     * @return The position the user entered
     */
    public static int inputPosition() {
        return inputPosition("Enter a position: ");
    }

    /**
     * Lets user input the position.
     * @param message The message displayed to the user
     * @return The position the user entered
     */
    public static int inputPosition(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);

        try {
            return sc.nextInt();
        } catch (InputMismatchException ex) {
            throw new IllegalArgumentException(
                "You entered an invalid position.");
        }
    }

    /**
     * Lets user input the size in the format "xSize ySize"
     * @return An array with 2 elements. The first element represents the xSize,
     * the second element represents the ySize
     */
    public static int[] inputSize() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the size: ");
            try {
                String size = sc.nextLine();
                String[] sizes = size.split(" ");
                if (sizes.length != 2) {
                    System.out.println("\nYou entered an invalid size\n");
                    continue;
                }
                return new int[] { Integer.parseInt(sizes[0]),
                        Integer.parseInt(sizes[1]) };
            } catch (InputMismatchException ex) {
                System.out.println("\nYou entered an invalid size\n");
                continue;
            } catch (NumberFormatException ex) {
                System.out.println("\nYou entered an invalid size\n");
                continue;
            }
        }

    }

}
