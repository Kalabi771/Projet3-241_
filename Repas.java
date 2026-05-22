package tp3;

public class Repas extends Item {

    // Attributs specifiques à Repas
    private int partage;
    private boolean extra_sauce;
    private boolean extra_fromage;

    // Constructeur par defaut
    public Repas() {

        super();
        

        this.partage = 1;
        this.extra_sauce = false;
        this.extra_fromage = false;
    }

    // Constructeur complet
    public Repas(String description,
                 double prix,
                 int calories,
                 int partage,
                 boolean extra_sauce,
                 boolean extra_fromage) {

        super(description, prix, calories);

        this.partage = partage;
        this.extra_sauce = extra_sauce;
        this.extra_fromage = extra_fromage;
    }

    // Constructeur de copie
    public Repas(Repas autre) {

        super(autre.description,
              autre.prix,
              autre.calories);

        this.partage = autre.partage;
        this.extra_sauce = autre.extra_sauce;
        this.extra_fromage = autre.extra_fromage;
    }

    // clone
    @Override
    public Repas clone() {
        return new Repas(this);
    }

    // toString
    @Override
    public String toString() {

        return String.format(
                "Repas: %s, %.2f$, %d calories, pour %d personne(s)%s%s",

                description,
                prix,
                calories,
                partage,

                extra_sauce ? " + sauce" : "",
                extra_fromage ? " + fromage" : ""
        );
    }

    // Get et set

    public int getPartage() {
        return partage;
    }

    public void setPartage(int partage) {

        if(partage < 1)
            throw new IllegalArgumentException("Partage invalide");

        this.partage = partage;
    }

    public boolean isExtraSauce() {
        return extra_sauce;
    }

    public void setExtraSauce(boolean extra_sauce) {
        this.extra_sauce = extra_sauce;
    }

    public boolean isExtraFromage() {
        return extra_fromage;
    }

    public void setExtraFromage(boolean extra_fromage) {
        this.extra_fromage = extra_fromage;
    }
}


