package tp3;

import java.io.*;

public class GestionFichiers {

    /**
     * Écrit un objet dans un fichier binaire
     * @param fichier Le chemin du fichier
     * @param objet L'objet à sérialiser
     * @return true si l'écriture a réussi, false sinon
     */
    public boolean ecrireBinaire(String fichier, Object objet) {
        if (fichier == null || objet == null) {
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fichier))) {
            
            oos.writeObject(objet);
            return true;
            
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture binaire: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lit un objet depuis un fichier binaire
     * @param fichier Le chemin du fichier
     * @return L'objet désérialisé ou null en cas d'erreur
     */
    public Object lireBinaire(String fichier) {
        if (fichier == null) {
            return null;
        }

        File file = new File(fichier);
        if (!file.exists() || !file.canRead()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fichier))) {
            
            return ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la lecture binaire: " + e.getMessage());
            return null;
        }
    }

    /**
     * Écrit du texte dans un fichier
     * @param fichier Le chemin du fichier
     * @param texte Le texte à écrire
     * @param append true pour ajouter à la fin, false pour écraser
     * @return true si l'écriture a réussi, false sinon
     */
    public boolean ecrireTexte(String fichier, String texte, boolean append) {
        if (fichier == null || texte == null) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fichier, append))) {
            
            writer.write(texte);
            return true;
            
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture texte: " + e.getMessage());
            return false;
        }
    }
}