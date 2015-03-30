/**
 * CSE 214 Homework #1
 * @author Joseph Cullen
 */

import java.text.DecimalFormat;

/**
 * This class is a baseball card. It has several methods relevant to a typical
 * baseball card
 */
public class BaseballCard {
    private String playerName; // The player's name
    private String manufacturer; // The manufacturer's name
    private int year; // The year
    private double price; // The price of the card
    private int[] size = new int[2]; // The size along the x and y axes
                                     // respectively

    /**
     * Creates a new BaseballCard object with blank parameters
     */
    public BaseballCard() {
        playerName = "";
        manufacturer = "";
        year = 0;
        price = 0.0;
        size[0] = 0; //The x size
        size[1] = 0; //The y size
    }

    /**
     * Creates a new BaseballCard object using the given values
     * @param n This represents the player's name
     * @param m This represents the manufacturer
     * @param y This represents the year
     * @param xSize This represents the x axis size of the card
     * @param ySize This represents the y axis size of the card
     * @param p This represents the price of the card
     */
    public BaseballCard(String n, String m, int y, int xSize, int ySize,
        double p) {
        playerName = n;
        manufacturer = m;
        year = y;
        size[0] = xSize;
        size[1] = ySize;
        price = p;
    }

    /**
     * Creates a new BaseballCard object using the given values
     * @param n This represents the player's name
     * @param m This represents the manufacturer
     * @param y This represents the year
     * @param xySize This represents the x and y size of the card respectively
     * @param p This represents the price of the card
     */
    public BaseballCard(String n, String m, int y, int[] xySize, double p) {
        playerName = n;
        manufacturer = m;
        year = y;
        size = xySize;
        price = p;
    }

    /**
     * Returns the name of a particular BaseballCard
     * @return The name of the player as a String
     */
    public String getName() {
        return playerName;
    }

    /**
     * Returns the manufacturer of a particular BaseballCard
     * @return The manufacturer of the card as a String
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Returns the year of a particular BaseballCard
     * @return The year of the card as an int
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the price of a particular BaseballCard 
     * @return The price of the card as a double
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the price of a particular BaseballCard
     * @return The price of the card as a String to 2 decimal places Example:
     * $19.95
     */
    public String getFormattedPrice() {
        return new DecimalFormat("0.00").format(this.price);
    }

    /**
     * Returns the x size of a particular BaseballCard
     * @return The x size of the card as an int
     */
    public int getSizeX() {
        return size[0];
    }

    /**
     * Returns the y size of a particular BaseballCard
     * @return The y size of the card as an int
     */
    public int getSizeY() {
        return size[1];
    }

    /**
     * Changes the old name into a new name
     * @param newName The new name of the card
     */
    public void setName(String newName) {
        playerName = newName;
    }

    /**
     * Changes the old manufacturer into a new manufacturer
     * @param newManufacturer The new manufacturer of the card
     */
    public void setManufacturer(String newManufacturer) {
        manufacturer = newManufacturer;
    }

    /**
     * Changes the old year into a new year
     * @param newYear The new year of the card
     */
    public void setYear(int newYear) {
        year = newYear;
    }

    /**
     * Changes the old price into a new price
     * @param newPrice The new price of the card
     */
    public void setPrice(double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        price = newPrice;
    }

    /**
     * Changes the old x size into a new x size
     * @param newXSize The new x size of the card
     */
    public void setSizeX(int newXSize) {
        size[0] = newXSize;
    }

    /**
     * Changes the old y size into a new y size
     * @param newYSize The new y size of the card
     */
    public void setSizeY(int newYSize) {
        size[1] = newYSize;
    }

    /**
     * Copies all data from a particular BaseballCard into a new BaseballCard
     * reference changing the original will not change the copy, and vice versa.
     * @return a copy of a BaseballCard as an Object
     */
    public Object clone() {
        return new BaseballCard(playerName, manufacturer, year, size[0],
          size[1], price); //Strings are immutable, so this method is correct
    }

    /**
     * Compares a particular object with a BaseballCard object; checks for
     * logical equivalence
     * @param obj the Object to be compared with the current BaseballCard
     * @return true if logically equivalent, false otherwise
     */
    public boolean equals(Object obj) {
        BaseballCard card;
        if (obj instanceof BaseballCard) {
            card = (BaseballCard) obj;
        } else {
            return false; // the object is not a BaseballCard
        }
        if (!playerName.equals(card.getName()))
            return false;
        if (!manufacturer.equals(card.getManufacturer()))
            return false;
        if (year != card.getYear())
            return false;
        if (price != card.getPrice())
            return false;
        if (size[0] != card.getSizeX())
            return false;
        if (size[1] != card.getSizeY())
            return false;

        return true;
    }

    /**
     * Returns the values of the card separated by commas 
     * @return the values of the card separated by commas Example:
     * "Johnny Damon, Topps, 100x300, $9.50"
     */
    public String toString() {
        return getName() + ", " + getManufacturer() + ", " + getSizeX() + "x"
          + getSizeY() + ", $" + getFormattedPrice();
    }
}
