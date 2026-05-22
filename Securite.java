

	package tp3;

	import javax.swing.JOptionPane;
	import javax.swing.JPasswordField;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	public class Securite {

	    // Attributs
	    private String fichier;
	    private String defaut;
	    private String password;

	    // Constructeur
	    public Securite() {
	        this.fichier  = "securite.bin";
	        this.defaut   = "secret";

	        // Tente de lire le mot de passe sur le disque
	        // Si échec, utilise le mot de passe par défaut
	        try {
	            Securite s = (Securite) GestionFichiers.lireBinaire(fichier);
	            this.password = s.password;
	        } catch (Exception e) {
	            this.password = defaut;
	        }
	    }

	    // demande le mot de passe, retourne true si il est correct
	    public boolean demanderMotDePasse() {

	        JPasswordField pwdField = new JPasswordField(20);

	        JPanel panel = new JPanel();
	        panel.add(new JLabel("Veuillez saisir votre mot de passe :"));
	        panel.add(pwdField);

	        int result = JOptionPane.showConfirmDialog(
	                null,
	                panel,
	                "Mot de passe",
	                JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.QUESTION_MESSAGE);

	        if (result == JOptionPane.OK_OPTION) {
	            String saisie = new String(pwdField.getPassword());
	            return saisie.equals(password);
	        }

	        // Annuler = quitter
	        return false;
	    }

	    // change le mot de passe
	    public void changerMotDePasse() {

	        JPasswordField pwdNouveau    = new JPasswordField(20);
	        JPasswordField pwdConfirmer  = new JPasswordField(20);

	        JPanel panel = new JPanel();
	        panel.setLayout(new java.awt.GridLayout(4, 1, 5, 5));
	        panel.add(new JLabel("Nouveau mot de passe :"));
	        panel.add(pwdNouveau);
	        panel.add(new JLabel("Confirmer le mot de passe :"));
	        panel.add(pwdConfirmer);

	        int result = JOptionPane.showConfirmDialog(
	                null,
	                panel,
	                "Changer le mot de passe",
	                JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.QUESTION_MESSAGE);

	        if (result == JOptionPane.OK_OPTION) {

	            String nouveau   = new String(pwdNouveau.getPassword());
	            String confirmer = new String(pwdConfirmer.getPassword());

	            // VALIDATION
	            if (nouveau.isBlank()) {
	                JOptionPane.showMessageDialog(null,
	                        "Le mot de passe ne peut pas être vide.");
	                return;
	            }

	            if (!nouveau.equals(confirmer)) {
	                JOptionPane.showMessageDialog(null,
	                        "Les mots de passe ne correspondent pas.");
	                return;
	            }

	            // SAUVEGARDE
	            this.password = nouveau;
	            GestionFichiers.ecrireBinaire(fichier, this);

	            JOptionPane.showMessageDialog(null,
	                    "Mot de passe changé avec succès.");
	        }
	    }

	    // GETTERS / SETTERS
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        if (!password.isBlank()) {
	            this.password = password;
	        }
	    }

	    public String getFichier() {
	        return fichier;
	    }

	    public String getDefaut() {
	        return defaut;
	    }
	}

