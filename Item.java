package tp3;

import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable, Cloneable {

    // Attributs
    protected String description;
    protected double prix;
    protected int calories;

    // Constructeur par defaut
    public Item() {
        this.description = "";
        this.prix = 0.0;
        this.calories = 0;
    }

    // Constructeur complet
    public Item(String description, double prix, int calories) {
        this.description = description;
        this.prix = prix;
        this.calories = calories;
    }

    // Constructeur avec description seulement
    public Item(String description) {
        this.description = description;
        this.prix = 0.0;
        this.calories = 0;
    }

    // get et set 
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        if(prix < 0)
            throw new IllegalArgumentException("Prix invalide");

        this.prix = prix;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        if(calories < 0)
            throw new IllegalArgumentException("Calories invalides");

        this.calories = calories;
    }

    // equals
    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        Item item = (Item) obj;

        return description.equals(item.description);
    }

    // compareTo
    @Override
    public int compareTo(Item other) {
        return this.description.compareTo(other.description);
    }

    // clone
    @Override
    public Item clone() {
        return new Item(this.description, this.prix, this.calories);
    }

    // toString
    @Override
    public String toString() {
        return "Description: " + description +
               ", Prix: " + prix +
               ", Calories: " + calories;
    }
}


