package tp3;

//La classe Dessert hérite de la classe Item 	
public class Dessert extends Item {
	 // Attributs spécifiques à un dessert
    private boolean flambe;
    private boolean chaud;
    // Constructeur par défaut
    public Dessert() {
        super();	// Appelle le constructeur par défaut de Item
        this.flambe = false;	// Par défaut, le dessert n’est pas flambé
        this.chaud = false;		// Par défaut, le dessert n’est pas chaud
    }
 // Constructeur principal avec tous les attributs
    public Dessert(String description, double prix, int calories, boolean flambe, boolean chaud) {
        super(description, prix, calories);	// Initialise les attributs hérités
        this.flambe = flambe;		// Initialise l’attribut flambe
        this.chaud = chaud;			// Initialise l’attribut chaud
    }

    // Constructeur de copie
    public Dessert(Dessert autre) {
    	// Utilise le constructeur principal pour copier tous les attributs
        this(autre.description, autre.prix, autre.calories, autre.flambe, autre.chaud);
    }
    // Constructeur avec juste une description et un prix
    public Dessert(String description, double prix) {
    	// Par défaut : 0 calories, pas flambé, pas chaud
    	this(description, prix, 0, false, false);
        
    }
 // Redéfinition de la méthode clone pour retourner une copie de l'objet
    @Override
    public Dessert clone() {
        return new Dessert(this);
    }
    public boolean isFlambe() { return flambe; }
    public boolean isChaud() { return chaud; }
 // Redéfinition de la méthode toString pour afficher toutes les infos du dessert
    @Override
    public String toString() {
        return super.toString() + 
               (flambe ? " (flambé)" : "") + 
               (chaud ? " (chaud)" : "");
    }
}