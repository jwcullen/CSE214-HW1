/**
 * CSE 214 Homework #1
 * @author Joseph Cullen
 */

import java.lang.IllegalArgumentException;
import java.text.DecimalFormat;

/**
 * This class is a collection manager for BaseballCards
 * it contains several methods relevant to maintaining and updating a collection
 */
public class CardCollection {
    final static int MAX_CARDS = 100; // Maximum number of cards this collection holds
    private BaseballCard[] cards; // An array to hold the BaseballCards
    private String name; // The name of the collection
    private int size; // The number of cards currently in the collection

    /**
     * Create an empty card collection with the no name with no cards in it
     * <dt><b>Postcondition:</b><dd> The CardCollection is initialized to an
     * empty list of BaseballCards 
     */
    public CardCollection() {
        cards = new BaseballCard[MAX_CARDS + 1]; //index 0 will not be used
        name = "";
        size = 0;
    }

    /**
     * Create an empty card collection with the given name with no cards in it
     * @param myName The name this class will be called
     * <dt><b>Postcondition:</b><dd> The CardCollection is initialized to an
     * empty list of BaseballCards 
     */
    public CardCollection(String myName) {
        cards = new BaseballCard[MAX_CARDS + 1]; // index 0 will not be used
        name = myName;
        size = 0;
    }

    /**
     * Returns the number of cards currently in the collection
     * @return the number of cards currently in the collection
     * <dt><b>Precondition</b><dd> The CardCollection has been instantiated
     */
    public int size() {
        return size;
    }

    /**
     * Adds a card at the specified position in this CardCollection
     * @param newCard the new BallballCard object to add to this CardCollection
     * @param position the position in the CardCollection where the newCard will
     * be inserted
     * @throws FullCollectionException Indicated the collection is full
     * @throws IllegalArgumentExceptin Indicated that position is not in the 
     * valid range
     * <dt><b>Precondition</b><dd> The CardCollection object has been 
     * instantiated and 1 < position < items_currently_in_list + 1. The number 
     * of BallballCard objects in this menu is less than MAX_CARDS
     * <dt><b>Postcondition</b><dd> The new BaseballCard is stored at the 
     * indicated position in the CardCollection. All cards that were originally
     * in positions greater than or equal to position are moved back one 
     * position.
     */
    public void addCard(BaseballCard newCard, int position) 
      throws FullCollectionException {
        if (size() == MAX_CARDS) {
            throw new FullCollectionException();
        }
        if (isInvalidPosition(position, 1)) {
            throw new IllegalArgumentException("Position is out of valid "
              + "range.");
        }
        for (int i = size() + 1; i > position; i--) {
            cards[i] = cards[i - 1];
        }
        cards[position] = newCard;
        size++;
    }

    /**
     * Adds a card to the end of this CardCollection
     * @param newCard the new BallballCard object to add to this CardCollection
     * @throws FullCollectionException Indicates the collection is full
     * * <dt><b>Precondition</b><dd> The CardCollection object has been 
     * instantiated . The number of BallballCard objects in this menu is less 
     * than MAX_CARDS
     * <dt><b>Postcondition</b><dd> The new BaseballCard is stored at the 
     *  end of the CardCollection.
     */
    public void addCard(BaseballCard newCard) throws FullCollectionException {
        try {
            addCard(newCard, size() + 1);
        } catch (FullCollectionException e) {
            throw e;
        }
    }

    /**
     * Removes a BaseballCard in the specified position from this CardCollection
     * @param position the position in the CardCollection where the newCard will
     * be inserted
     * @throws IllegalArgumentExceptin Indicated that position is not in the 
     * valid range
     * <dt><b>Precondition</b><dd> The CardCollection object has been 
     * instantiated and 1 < position < items_currently_in_list. 
     * <dt><b>Postcondition</b><dd> The  BaseballCard at the indicated position
     * is removed. All cards that were originally in positions greater than or 
     * equal to position are moved forward one position.
     */
    public void removeCard(int position) {
        if (isInvalidPosition(position, 0)) {
            throw new IllegalArgumentException(
              "Position is out of valid range.");
        }
        for (int i = position; i < size(); i++) {
            cards[i] = cards[i + 1];
        }
        cards[size()] = null;
        size--;
    }

    /**
     * Get the BaseballCard at the given position in this CardCollection object.
     * @param position the position in the CardCollection where the newCard will
     * be inserted
     * @return the card at the specified position in this CardCollection object
     * @throws IllegalArgumentException Indicates that position is not in the 
     * valid range
     * <dt><b>Precondition</b><dd> The CardCollection object has been 
     * instantiated and 1 < position < items_currently_in_list. 
     */
    public BaseballCard getCard(int position) {
        if (isInvalidPosition(position, 0)) {
            throw new IllegalArgumentException(
              "Position is out of valid range.");
        }
        return cards[position];
    }

    /**
     * Exchange a card from this collection for a card from another collection
     * @param other The CardCollection to trade with
     * @param myPosition The position in this CardCollection to trade from
     * @param theirPosition The position in the other CardCollection to trade 
     * @throws FullCollectionException Indicates the collection is full
     * dt><b>Precondition</b><dd> Both CardCollection object has been 
     * instantiated and 1 < position < items_currently_in_list for both 
     * collections
     */
    public void trade(CardCollection other, int myPosition, int theirPosition)
      throws FullCollectionException {
        if (isInvalidPosition(myPosition, 0)) {
            throw new IllegalArgumentException(
              "Position is out of valid range.");
        }
        if (theirPosition < 1 || theirPosition > other.size()) {
            throw new IllegalArgumentException(
              "Position is out of valid range.");
        }
        BaseballCard theirCard = other.getCard(theirPosition);
        BaseballCard myCard = getCard(myPosition);

        removeCard(myPosition);
        other.removeCard(theirPosition);

        try {
            addCard(theirCard, myPosition);
            other.addCard(myCard, theirPosition);
        } catch (FullCollectionException e) {
            throw e;
        }

    }

    /**
     * Checks whether this CardCollection contains the given card
     * BaseballCard have both been instantiated
     * @param card the BaseballCard we are looking for
     * @return True if this CardCollection contains the card false otherwise
     * <dt><b>Precondition</b><dd> The CardCollection object and the 
     */
    public boolean exists(BaseballCard card) {
        for (int i = 1; i <= size(); i++) {
            if (cards[i].equals(card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints a formatted list of all the cards in this collection
     * <dt><b>Precondition</b><dd> The CardCollection object has been 
     * instantiated
     * <dt><b>Postcondition</b><dd> A neatly formatted list of all of the cards
     * in this CardCollection has been displayed to the user
     */
    public void printAllCards() {
        System.out.print(toString());
    }

    /**
     * Gets the String representation of the cards in this CardCollection
     * @return  A neatly formatted list of the cards in this CardCollection
     */
    public String toString() {
        if (this.size() == 0) {
            return "There are no cards in this collection\n\n";
        }
        String s = String.format("%-4s%-25s%-25s%-6s%-8s%-8s\n", "#", "Name",
          "Manufacturer", "Year", "Price", "Size");
        s += String.format("%-4s%-25s%-25s%-6s%-8s%-8s\n", "--", "----",
          "------------", "----", "-----", "----");
        for (int i = 1; i <= size(); i++) {
            BaseballCard c = cards[i];
            s += String.format("%-4d%-25s%-25s%-6d$%-8s%-1dx%d\n", i,
              c.getName(), c.getManufacturer(), c.getYear(),
              c.getFormattedPrice(), c.getSizeX(), c.getSizeY());
        }
        s += "\n";
        return s;
    }

    /**
     * Returns a comma separated list of the attributes of a card at the 
     * specified position
     * @param position the position in the CardCollection of the BaseballCard
     * @return Returns a comma separated list of the attributes of a card at 
     * the specified position
     * <dt><b>Precondition</b><dd> The CardCollection object has been 
     * instantiated and 1 < position < items_currently_in_list.
     */
    public String cardToString(int position) {
        if (isInvalidPosition(position, 0)) {
            throw new IllegalArgumentException(
              "Position is out of valid range.");
        } else {
            return cards[position].toString();
        }
    }

    /**
     * Gets the total value of the cards
     * @return String The total value of this CardCollection represented by a
     * String to 2 decimal places
     */
    public String totalValue() {
        double value = 0;
        for (int i = 1; i <= size(); i++) {
            value += cards[i].getPrice();
        }
        return new DecimalFormat("0.00").format(value);
    }

    /**
     * Gets the name of this CardCollection
     * @return the name of this CardCollection
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if pos is an invalid position.
     * @param pos The position to check
     * @param x Value added to size check. Use 1 if a card can be added at the 
     * end of the collection
     * @return true, if the position is invalid, false otherwise
     */
    public boolean isInvalidPosition(int pos, int x) {
        if (pos < 1 || pos > size() + x)
            return true;
        else
            return false;
    }
}
