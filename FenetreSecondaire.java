
package tp3;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FenetreSecondaire extends JFrame {

    private static final long serialVersionUID = 1L;

    // L'item à modifier (référence directe dans le ArrayList)
    private Item item;

    // Composants
    private JTextField txtPrix;
    private JTextField txtCalories;
    private JTextField txtPortions;
    private JComboBox<Format> cbFormat;
    private JTextField txtPartage;
    private JCheckBox  chckbxSauce;
    private JCheckBox  chckbxFromage;
    private JCheckBox chckbxFlambe;
    private JCheckBox chckbxChaud;

    // Bouton
    private JButton btnMettreAJour;

    // Panneau principal
    private JPanel contentPane;

    // constructeur
    public FenetreSecondaire(Item item) {

        this.item = item;

        // Titre selon le type d'item
        setTitle(item.getClass().getSimpleName() + ": " + item.getDescription());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 450, 350);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // prix
        JLabel lblPrix = new JLabel("Prix");
        lblPrix.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPrix.setBounds(80, 30, 50, 25);
        contentPane.add(lblPrix);

        txtPrix = new JTextField(String.valueOf(item.getPrix()));
        txtPrix.setBounds(130, 30, 100, 25);
        contentPane.add(txtPrix);

        // calorie
        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCalories.setBounds(250, 30, 70, 25);
        contentPane.add(lblCalories);

        txtCalories = new JTextField(String.valueOf(item.getCalories()));
        txtCalories.setBounds(325, 30, 80, 25);
        contentPane.add(txtCalories);

        // 
        voirCases(false, new java.awt.Component[]{});
        afficherChampsSpecifiques();

        //boutton mise a jour
        btnMettreAJour = new JButton("Mettre à jour");
        btnMettreAJour.setBounds(150, 240, 150, 35);
        contentPane.add(btnMettreAJour);

        btnMettreAJour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mettreAJour();
            }
        });
    }

    // affiche les champs selon le type de items
    private void afficherChampsSpecifiques() {

        if (item instanceof Entree) {

            Entree entree = (Entree) item;

            JLabel lblPortions = new JLabel("À partager?");
            lblPortions.setBounds(30, 100, 90, 25);
            contentPane.add(lblPortions);

            txtPortions = new JTextField(String.valueOf(entree.getPortions()));
            txtPortions.setBounds(125, 100, 60, 25);
            contentPane.add(txtPortions);

            JLabel lblFormat = new JLabel("Format");
            lblFormat.setBounds(210, 100, 60, 25);
            contentPane.add(lblFormat);

            cbFormat = new JComboBox<>(Format.values());
            cbFormat.setSelectedItem(entree.getFormat());
            cbFormat.setBounds(270, 100, 100, 25);
            contentPane.add(cbFormat);

        } else if (item instanceof Repas) {

            Repas repas = (Repas) item;

            JLabel lblPartage = new JLabel("À partager?");
            lblPartage.setBounds(30, 100, 90, 25);
            contentPane.add(lblPartage);

            txtPartage = new JTextField(String.valueOf(repas.getPartage()));
            txtPartage.setBounds(125, 100, 60, 25);
            contentPane.add(txtPartage);

            chckbxSauce = new JCheckBox("Extra sauce");
            chckbxSauce.setSelected(repas.isExtra_sauce());
            chckbxSauce.setBounds(210, 100, 120, 25);
            contentPane.add(chckbxSauce);

            chckbxFromage = new JCheckBox("Extra fromage");
            chckbxFromage.setSelected(repas.isExtra_fromage());
            chckbxFromage.setBounds(210, 130, 140, 25);
            contentPane.add(chckbxFromage);

        } else if (item instanceof Dessert) {

            Dessert dessert = (Dessert) item;

            chckbxFlambe = new JCheckBox("Flambé");
            chckbxFlambe.setSelected(dessert.isFlambe());
            chckbxFlambe.setBounds(150, 100, 100, 25);
            contentPane.add(chckbxFlambe);

            chckbxChaud = new JCheckBox("Chaud");
            chckbxChaud.setSelected(dessert.isChaud());
            chckbxChaud.setBounds(270, 100, 100, 25);
            contentPane.add(chckbxChaud);
        }
    }

    // mise a jour des items
    private void mettreAJour() {

        try {
            double prix     = Double.parseDouble(txtPrix.getText());
            int    calories = Integer.parseInt(txtCalories.getText());

            // Mise à jour des champs communs
            item.setPrix(prix);
            item.setCalories(calories);

            // Mise à jour des champs spécifiques
            if (item instanceof Entree) {

                Entree entree = (Entree) item;
                int portions = Integer.parseInt(txtPortions.getText());
                entree.setPortions(portions);
                entree.setFormat((Format) cbFormat.getSelectedItem());

            } else if (item instanceof Repas) {

                Repas repas = (Repas) item;
                int partage = Integer.parseInt(txtPartage.getText());
                repas.setPartage(partage);
                repas.setExtra_sauce(chckbxSauce.isSelected());
                repas.setExtra_fromage(chckbxFromage.isSelected());

            } else if (item instanceof Dessert) {

                Dessert dessert = (Dessert) item;
                dessert.setFlambe(chckbxFlambe.isSelected());
                dessert.setChaud(chckbxChaud.isSelected());
            }

            JOptionPane.showMessageDialog(null, "Item mis à jour avec succès.");
            dispose(); // Ferme la fenêtre secondaire

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Veuillez entrer des valeurs numériques valides.");
        }
    }

 
    public void voirCases(boolean visible, java.awt.Component... tab) {
        for (java.awt.Component c : tab) {
            c.setVisible(visible);
        }
    }
}