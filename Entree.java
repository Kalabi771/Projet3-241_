package tp3;

public class Entree extends Item {
    private int portions;
    private Format format;
   
    public Entree() {
    	 // Constructeur par défaut
        super();		// Appel du constructeur par défaut de Item
        this.portions = 1;// Par défaut, 1 portion
        this.format = Format.Moyen;// Format par défaut : Moyen
    }
   
    
    
 // Constructeur avec toute les paramètres
    public Entree(String description, double prix, int calories, int portions, Format format) {
        super(description, prix, calories);		// Appel du constructeur de Item
        this.portions = portions;		 // Initialisation du nombre de portions
        this.format = format;			// Initialisation du format
    }
    //Consturcteur de copie 
    public Entree(Entree autre) {
    	// Appel du constructeur principal avec les valeurs copiées
        this(autre.description, autre.prix, autre.calories, autre.portions, autre.format);
    }
 // Constructeur avec seulement description et prix
    public Entree(String description, double prix) {
    	// Par défaut : 0 calories, 1 portion, format Moyen
        this(description, prix, 0, 1, Format.Moyen);
    }
 // Redéfinition de la méthode clone pour créer une copie de l'objet
    @Override
    public Entree clone() {
        return new Entree(this);// Utilise le constructeur de copie
    }
 // Redéfinition de toString pour afficher les informations complètes
    @Override
    public String toString() {
        return super.toString() + " - " + portions + " portion(s), format " + format;
    }



    // Méthodes getFormat et getPortions à compléter si nécessaire
	public String getFormat() {
		// TODO Auto-generated method stub retourner le format en chaine de caractère
		return null;
	}
	public int setPortions(int portions) {return portions;}
	public Format setFormat(Format format) {return format;}





	public String getPortions() {
		// Retourner le nombre de portions en chaîne de caractères
		return null;
	}
}