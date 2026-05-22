package tp3;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Fenêtre principale du gestionnaire de menu
 * 
 */
public class FenetrePrincipale extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private ArrayList<Item> menu = new ArrayList<>();

    // Composants
    private JTextField txtDescription;
    private JTextField txtPrix;
    private JTextField txtCalories;
    private JTextField txtPartager;
    private JTextField txtSelection;
    private JTextArea textAreaMenu;

    private JComboBox<Categorie> cbChoix;
    private JComboBox<Format> cbFormat;

    private JCheckBox chckbxSauce;
    private JCheckBox chckbxFromage;
    private JCheckBox chckbxFlambe;
    private JCheckBox chckbxChaud;

    private JButton btnAjouter;
    private JButton btnEffacer;
    private JButton btnVider;

    public static void main(String[] args) {
        FenetrePrincipale frame = new FenetrePrincipale();
        frame.setVisible(true);
    }

    public FenetrePrincipale() {
        setTitle("Gestionnaire de menu du Resto Ahuntsic par NOM Prénom"); // ← Mets tes noms
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 980, 620);

        // ==================== MENU BAR ====================
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu Fichier
        JMenu mnFichier = new JMenu("Fichier");
        menuBar.add(mnFichier);
        JMenuItem mntmLire = new JMenuItem("Lire les données");
        JMenuItem mntmEcrire = new JMenuItem("Écrire les données");
        JMenuItem mntmQuitter = new JMenuItem("Sortir");
        mnFichier.add(mntmLire);
        mnFichier.add(mntmEcrire);
        mnFichier.addSeparator();
        mnFichier.add(mntmQuitter);

        // Menu Menu
        JMenu mnMenu = new JMenu("Menu");
        menuBar.add(mnMenu);
        JMenuItem mntmProduire = new JMenuItem("Produire le menu");
        mnMenu.add(mntmProduire);

        // Menu Sécurité
        JMenu mnSecurite = new JMenu("Sécurité");
        menuBar.add(mnSecurite);
        JMenuItem mntmChangerMDP = new JMenuItem("Changer le mot de passe");
        mnSecurite.add(mntmChangerMDP);

        // Actions Menu
        mntmQuitter.addActionListener(e -> System.exit(0));

        // ==================== CONTENT PANE ====================
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Description
        JLabel lblDescription = new JLabel("Description");
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDescription.setBounds(20, 20, 100, 25);
        contentPane.add(lblDescription);

        txtDescription = new JTextField();
        txtDescription.setBounds(130, 20, 250, 25);
        contentPane.add(txtDescription);

        // Catégorie
        cbChoix = new JComboBox<>(Categorie.values());
        cbChoix.setBounds(20, 70, 130, 25);
        contentPane.add(cbChoix);

        // Prix
        JLabel lblPrix = new JLabel("Prix");
        lblPrix.setBounds(170, 70, 50, 25);
        contentPane.add(lblPrix);

        txtPrix = new JTextField();
        txtPrix.setBounds(220, 70, 100, 25);
        contentPane.add(txtPrix);

        // Calories
        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setBounds(340, 70, 70, 25);
        contentPane.add(lblCalories);

        txtCalories = new JTextField();
        txtCalories.setBounds(410, 70, 100, 25);
        contentPane.add(txtCalories);

        // Champs dynamiques
        JLabel lblPartager = new JLabel("À partager?");
        lblPartager.setBounds(20, 120, 100, 25);
        contentPane.add(lblPartager);

        txtPartager = new JTextField();
        txtPartager.setBounds(130, 120, 60, 25);
        contentPane.add(txtPartager);

        cbFormat = new JComboBox<>(Format.values());
        cbFormat.setBounds(220, 120, 130, 25);
        contentPane.add(cbFormat);

        chckbxSauce = new JCheckBox("Extra sauce");
        chckbxSauce.setBounds(380, 120, 130, 25);
        contentPane.add(chckbxSauce);

        chckbxFromage = new JCheckBox("Extra fromage");
        chckbxFromage.setBounds(520, 120, 150, 25);
        contentPane.add(chckbxFromage);

        chckbxFlambe = new JCheckBox("Flambé");
        chckbxFlambe.setBounds(380, 120, 100, 25);
        contentPane.add(chckbxFlambe);

        chckbxChaud = new JCheckBox("Chaud");
        chckbxChaud.setBounds(500, 120, 100, 25);
        contentPane.add(chckbxChaud);

        // Boutons
        btnAjouter = new JButton("Ajouter au menu");
        btnAjouter.setBounds(150, 180, 160, 40);
        contentPane.add(btnAjouter);

        txtSelection = new JTextField();
        txtSelection.setBounds(50, 280, 80, 35);
        contentPane.add(txtSelection);

        btnEffacer = new JButton("Effacer");
        btnEffacer.setBounds(150, 280, 120, 35);
        contentPane.add(btnEffacer);

        btnVider = new JButton("Vider");
        btnVider.setBounds(290, 280, 120, 35);
        contentPane.add(btnVider);

        // Zone de texte menu
        textAreaMenu = new JTextArea();
        textAreaMenu.setBounds(520, 20, 420, 500);
        contentPane.add(textAreaMenu);

        // ==================== LISTENERS ====================
        cbChoix.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    afficherChampsSelonCategorie();
                }
            }
        });

        btnAjouter.addActionListener(new BtnAjouterActionListener());
        btnEffacer.addActionListener(new BtnEffacerActionListener());
        btnVider.addActionListener(new BtnViderActionListener());

        // Affichage initial
        afficherChampsSelonCategorie();
    }

    /**
     * Affiche / masque les champs selon la catégorie sélectionnée
     */
    private void afficherChampsSelonCategorie() {
        Categorie cat = (Categorie) cbChoix.getSelectedItem();

        // Réinitialiser visibilité
        txtPartager.setVisible(false);
        cbFormat.setVisible(false);
        chckbxSauce.setVisible(false);
        chckbxFromage.setVisible(false);
        chckbxFlambe.setVisible(false);
        chckbxChaud.setVisible(false);

        if (cat == Categorie.Entree) {
            txtPartager.setVisible(true);
            cbFormat.setVisible(true);
        } 
        else if (cat == Categorie.Repas) {
            txtPartager.setVisible(true);
            chckbxSauce.setVisible(true);
            chckbxFromage.setVisible(true);
        } 
        else if (cat == Categorie.Dessert) {
            chckbxFlambe.setVisible(true);
            chckbxChaud.setVisible(true);
        }
    }

    // ==================== CLASSES INTERNES (Actions) ====================
    private class BtnAjouterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String description = txtDescription.getText().trim();
                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Description obligatoire !");
                    return;
                }

                double prix = Double.parseDouble(txtPrix.getText().trim());
                int calories = Integer.parseInt(txtCalories.getText().trim());
                Categorie categorie = (Categorie) cbChoix.getSelectedItem();

                Item item = null;

                switch (categorie) {
                    case Entree:
                        int portions = Integer.parseInt(txtPartager.getText().trim());
                        Format format = (Format) cbFormat.getSelectedItem();
                        item = new Entree(description, prix, calories, portions, format);
                        break;

                    case Repas:
                        int partage = Integer.parseInt(txtPartager.getText().trim());
                        item = new Repas(description, prix, calories, partage,
                                chckbxSauce.isSelected(), chckbxFromage.isSelected());
                        break;

                    case Dessert:
                        item = new Dessert(description, prix, calories,
                                chckbxChaud.isSelected(), chckbxFlambe.isSelected());
                        break;
                }

                menu.add(item);
                afficherMenu();
                viderChamps();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : Vérifiez vos données numériques.");
            }
        }
    }

    private class BtnEffacerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int pos = Integer.parseInt(txtSelection.getText().trim()) - 1;
                if (pos >= 0 && pos < menu.size()) {
                    menu.remove(pos);
                    afficherMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Position invalide");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Position invalide");
            }
        }
    }

    private class BtnViderActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.clear();
            afficherMenu();
        }
    }

    public void afficherMenu() {
        textAreaMenu.setText("");
        for (int i = 0; i < menu.size(); i++) {
            textAreaMenu.append((i + 1) + " - " + menu.get(i) + "\n");
        }
    }

    private void viderChamps() {
        txtDescription.setText("");
        txtPrix.setText("");
        txtCalories.setText("");
        txtPartager.setText("");
        chckbxSauce.setSelected(false);
        chckbxFromage.setSelected(false);
        chckbxFlambe.setSelected(false);
        chckbxChaud.setSelected(false);
    }
}
