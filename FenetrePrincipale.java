
package tp3;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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

public class FenetrePrincipale extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // Tableau
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

    private JButton btnAjouter;
    private JButton btnEffacer;
    private JButton btnVider;

    public static void main(String[] args) {

        FenetrePrincipale frame = new FenetrePrincipale();
        frame.setVisible(true);
    }

    public FenetrePrincipale() {

        setTitle("Gestionnaire de menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 600);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFichier = new JMenu("Fichier");
        menuBar.add(mnFichier);

        JMenuItem mnQuitter = new JMenuItem("Quitter");
        mnFichier.add(mnQuitter);

        mnQuitter.addActionListener(e -> System.exit(0));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Description
        JLabel lblDescription = new JLabel("Description");
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDescription.setBounds(10, 20, 100, 25);
        contentPane.add(lblDescription);

        txtDescription = new JTextField();
        txtDescription.setBounds(120, 20, 200, 25);
        contentPane.add(txtDescription);

        // Categorie
        cbChoix = new JComboBox<>(Categorie.values());
        cbChoix.setBounds(10, 70, 120, 25);
        contentPane.add(cbChoix);

        // Prix
        JLabel lblPrix = new JLabel("Prix");
        lblPrix.setBounds(150, 70, 50, 25);
        contentPane.add(lblPrix);

        txtPrix = new JTextField();
        txtPrix.setBounds(200, 70, 100, 25);
        contentPane.add(txtPrix);

        // Calorie
        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setBounds(320, 70, 70, 25);
        contentPane.add(lblCalories);

        txtCalories = new JTextField();
        txtCalories.setBounds(390, 70, 100, 25);
        contentPane.add(txtCalories);

        // Partage
        JLabel lblPartager = new JLabel("Partager");
        lblPartager.setBounds(10, 120, 80, 25);
        contentPane.add(lblPartager);

        txtPartager = new JTextField();
        txtPartager.setBounds(100, 120, 60, 25);
        contentPane.add(txtPartager);

        // Checkbox
        chckbxSauce = new JCheckBox("Extra sauce");
        chckbxSauce.setBounds(200, 120, 120, 25);
        contentPane.add(chckbxSauce);

        chckbxFromage = new JCheckBox("Extra fromage");
        chckbxFromage.setBounds(330, 120, 150, 25);
        contentPane.add(chckbxFromage);

        // Format
        cbFormat = new JComboBox<>(Format.values());
        cbFormat.setBounds(500, 120, 100, 25);
        contentPane.add(cbFormat);

        // Ajoute
        btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(120, 180, 150, 35);
        contentPane.add(btnAjouter);

        // Text area
        textAreaMenu = new JTextArea();
        textAreaMenu.setBounds(520, 20, 380, 500);
        contentPane.add(textAreaMenu);

        // Selectionne
        txtSelection = new JTextField();
        txtSelection.setBounds(50, 300, 80, 30);
        contentPane.add(txtSelection);

        // Efface
        btnEffacer = new JButton("Effacer");
        btnEffacer.setBounds(150, 300, 120, 30);
        contentPane.add(btnEffacer);

        // Vide
        btnVider = new JButton("Vider");
        btnVider.setBounds(300, 300, 120, 30);
        contentPane.add(btnVider);

        // Actions
        btnAjouter.addActionListener(new BtnAjouterActionListener());
        btnEffacer.addActionListener(new BtnEffacerActionListener());
        btnVider.addActionListener(new BtnViderActionListener());
    }

    // Ajouter
    private class BtnAjouterActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                String description = txtDescription.getText();
                double prix = Double.parseDouble(txtPrix.getText());
                int calories = Integer.parseInt(txtCalories.getText());

                // VALIDATION
                if (description.isBlank()) {
                    JOptionPane.showMessageDialog(null,
                            "Description invalide");
                    return;
                }

                Categorie categorie =
                        (Categorie) cbChoix.getSelectedItem();

                Item item = null;

                switch (categorie) {

                case Entree:

                    int portions =
                            Integer.parseInt(txtPartager.getText());

                    Format format =
                            (Format) cbFormat.getSelectedItem();

                    item = new Entree(
                            description,
                            prix,
                            calories,
                            portions,
                            format);

                    break;

                case Repas:

                    int partage =
                            Integer.parseInt(txtPartager.getText());

                    boolean sauce =
                            chckbxSauce.isSelected();

                    boolean fromage =
                            chckbxFromage.isSelected();

                    item = new Repas(
                            description,
                            prix,
                            calories,
                            partage,
                            sauce,
                            fromage);

                    break;

                case Dessert:

                    boolean chaud =
                            chckbxFromage.isSelected();

                    boolean flambe =
                            chckbxSauce.isSelected();

                    item = new Dessert(
                            description,
                            prix,
                            calories,
                            chaud,
                            flambe);

                    break;
                }

                // arraylist
                menu.add(item);

                // Affiche
                afficherMenu();

                viderChamps();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        "Erreur dans les données");
            }
        }
    }

    // Efface
    private class BtnEffacerActionListener
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                int position =
                        Integer.parseInt(txtSelection.getText());

                position--;

                if(position >= 0 && position < menu.size()) {

                    menu.remove(position);
                    afficherMenu();
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        "Position invalide");
            }
        }
    }

    // Vide
    private class BtnViderActionListener
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            menu.clear();
            afficherMenu();
        }
    }

    // affiche menu
    public void afficherMenu() {

        textAreaMenu.setText("");

        int compteur = 1;

        for(Item item : menu) {

            textAreaMenu.append(
                    compteur + " - " + item + "\n");

            compteur++;
        }
    }

    // Vide les champs
    public void viderChamps() {

        txtDescription.setText("");
        txtPrix.setText("");
        txtCalories.setText("");
        txtPartager.setText("");

        chckbxSauce.setSelected(false);
        chckbxFromage.setSelected(false);
    }
}
