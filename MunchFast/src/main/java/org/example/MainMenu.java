/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 22cri
 */
public class MainMenu extends javax.swing.JFrame {

    Customer current;
    OrderController oc = new OrderController();
    private ResourceBundle bundle;
    // Variables declaration - do not modify
    private javax.swing.JButton backButton;
    private javax.swing.JButton backButton1;
    private javax.swing.JButton backButton2;
    private javax.swing.JButton backButton3;
    private javax.swing.JButton backLoginButton;
    private javax.swing.JPanel breakfastPanel;
    private javax.swing.JSpinner burgerCount;
    private javax.swing.JLabel burgerLabel;
    private javax.swing.JLabel burgerPictureLabel;
    private javax.swing.JLabel burgerPriceLabel1;
    private javax.swing.JLabel burgerStockLabel;
    private javax.swing.JLabel clientNameTiitleLabel;
    private javax.swing.JSpinner coffeeCount;
    private javax.swing.JLabel coffeeLabel;
    private javax.swing.JLabel coffeePictureLabel;
    private javax.swing.JLabel coffeePriceLabel;
    private javax.swing.JLabel coffeeStockLabel;
    private javax.swing.JPanel dinnerPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JSpinner friesCount;
    private javax.swing.JLabel friesLabel;
    private javax.swing.JLabel friesPictureLabel;
    private javax.swing.JLabel friesPriceLabel;
    private javax.swing.JLabel friesStockLabel;
    private javax.swing.JToggleButton languageToggle;
    private javax.swing.JPanel lunchPanel;
    private javax.swing.JTabbedPane menuPanel;
    private javax.swing.JSpinner orangeCount2;
    private javax.swing.JLabel orangeLabel2;
    private javax.swing.JLabel orangePictureLabel;
    private javax.swing.JLabel orangePriceLabel;
    private javax.swing.JLabel orangeStockLabel2;
    private javax.swing.JButton orderButton;
    private javax.swing.JSpinner ribsCount;
    private javax.swing.JLabel ribsLabel;
    private javax.swing.JLabel ribsPictureLabel;
    private javax.swing.JLabel ribsPriceLabel;
    private javax.swing.JLabel ribsStockLabel;
    private javax.swing.JSpinner riceCount;
    private javax.swing.JLabel riceLabel;
    private javax.swing.JLabel ricePictureLabel;
    private javax.swing.JLabel ricePriceLabel;
    private javax.swing.JLabel riceStockLabel;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        setLocationRelativeTo(null);
        configureTabsTime();
        clientNameTiitleLabel.setText("NONE RIGHT NOW");

    }

    public MainMenu(Customer c) {
        current = c;
        initComponents();
        setLocationRelativeTo(null);
        clientNameTiitleLabel.setText(c.getFirstName() + " " + c.getLastName());
        //load icon
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/munchFastIcon.png"));
        setIconImage(icon.getImage());
        loadLanguage(LanguageControl.getLanguage());
        configureTabsTime();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    private void configureTabsTime() {
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();

        if (currentHour < 12) {
            menuPanel.setEnabledAt(1, false);
            menuPanel.setEnabledAt(2, false);
        } else if (currentHour < 18) {
            menuPanel.setEnabledAt(0, false);
            menuPanel.setEnabledAt(2, false);
        } else {
            menuPanel.setEnabledAt(0, false);
            menuPanel.setEnabledAt(1, false);
        }

    }

    //method to load language on components
    private void loadLanguage(String language) {
        //set with language class
        LanguageControl.setLanguage(language);
        //load bundle
        bundle = ResourceBundle.getBundle("language", LanguageControl.getCurrentLocale());

        //update UI
        exitButton.setText(bundle.getString("exit"));
        friesLabel.setText(bundle.getString("fries"));
        ribsLabel.setText(bundle.getString("ribs"));
        coffeeLabel.setText(bundle.getString("coffee"));
        riceLabel.setText(bundle.getString("rice"));
        orangeLabel2.setText(bundle.getString("juice"));
        menuPanel.setTitleAt(0, bundle.getString("breakfast"));
        menuPanel.setTitleAt(1, bundle.getString("dinner"));
        menuPanel.setTitleAt(2, bundle.getString("lunch"));
        orderButton.setText(bundle.getString("placeOrder"));
        backLoginButton.setText(bundle.getString("back"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderButton = new javax.swing.JButton();
        languageToggle = new javax.swing.JToggleButton();
        menuPanel = new javax.swing.JTabbedPane();
        breakfastPanel = new javax.swing.JPanel();
        coffeeLabel = new javax.swing.JLabel();
        coffeeCount = new javax.swing.JSpinner();
        coffeeStockLabel = new javax.swing.JLabel();
        riceLabel = new javax.swing.JLabel();
        riceCount = new javax.swing.JSpinner();
        riceStockLabel = new javax.swing.JLabel();
        ricePictureLabel = new javax.swing.JLabel();
        coffeePictureLabel = new javax.swing.JLabel();
        coffeePriceLabel = new javax.swing.JLabel();
        ricePriceLabel = new javax.swing.JLabel();
        lunchPanel = new javax.swing.JPanel();
        orangeLabel2 = new javax.swing.JLabel();
        orangeCount2 = new javax.swing.JSpinner();
        orangeStockLabel2 = new javax.swing.JLabel();
        friesLabel = new javax.swing.JLabel();
        friesCount = new javax.swing.JSpinner();
        friesStockLabel = new javax.swing.JLabel();
        friesPictureLabel = new javax.swing.JLabel();
        orangePictureLabel = new javax.swing.JLabel();
        orangePriceLabel = new javax.swing.JLabel();
        friesPriceLabel = new javax.swing.JLabel();
        dinnerPanel = new javax.swing.JPanel();
        burgerLabel = new javax.swing.JLabel();
        burgerCount = new javax.swing.JSpinner();
        burgerStockLabel = new javax.swing.JLabel();
        ribsLabel = new javax.swing.JLabel();
        ribsCount = new javax.swing.JSpinner();
        ribsStockLabel = new javax.swing.JLabel();
        ribsPictureLabel = new javax.swing.JLabel();
        burgerPictureLabel = new javax.swing.JLabel();
        burgerPriceLabel1 = new javax.swing.JLabel();
        ribsPriceLabel = new javax.swing.JLabel();
        clientNameTiitleLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        backLoginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        orderButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        orderButton.setText("Place Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        languageToggle.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        languageToggle.setText("English");
        languageToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageToggleActionPerformed(evt);
            }
        });

        menuPanel.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        coffeeLabel.setText("Coffee");

        coffeeStockLabel.setText("Stock: ");

        riceLabel.setText("Rice");

        riceStockLabel.setText("Stock: ");

        ricePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rice.png"))); // NOI18N

        coffeePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coffee.png"))); // NOI18N

        coffeePriceLabel.setText("1.99 $");

        ricePriceLabel.setText("1.99 $");

        javax.swing.GroupLayout breakfastPanelLayout = new javax.swing.GroupLayout(breakfastPanel);
        breakfastPanel.setLayout(breakfastPanelLayout);
        breakfastPanelLayout.setHorizontalGroup(
                breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                                .addComponent(coffeeLabel)
                                                                .addGap(39, 39, 39))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breakfastPanelLayout.createSequentialGroup()
                                                                .addComponent(coffeeStockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)))
                                                .addComponent(coffeeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68)
                                                .addComponent(coffeePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                                .addComponent(riceLabel)
                                                                .addGap(39, 39, 39))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breakfastPanelLayout.createSequentialGroup()
                                                                .addComponent(riceStockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)))
                                                .addComponent(riceCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68)
                                                .addComponent(ricePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(coffeePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ricePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        breakfastPanelLayout.setVerticalGroup(
                breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(coffeeLabel)
                                                        .addComponent(coffeeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(coffeeStockLabel))
                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(coffeePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(coffeePriceLabel)))
                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(breakfastPanelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(ricePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(48, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breakfastPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breakfastPanelLayout.createSequentialGroup()
                                                                .addGroup(breakfastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(riceLabel)
                                                                        .addComponent(riceCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(riceStockLabel)
                                                                .addGap(60, 60, 60))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breakfastPanelLayout.createSequentialGroup()
                                                                .addComponent(ricePriceLabel)
                                                                .addGap(86, 86, 86))))))
        );

        menuPanel.addTab("Breakfast Menu", breakfastPanel);

        orangeLabel2.setText("Orange Juice");

        orangeStockLabel2.setText("Stock: ");

        friesLabel.setText("Fries");

        friesCount.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        friesStockLabel.setText("Stock: ");

        friesPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fries.png"))); // NOI18N

        orangePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/orange.png"))); // NOI18N

        orangePriceLabel.setText("1.99 $");

        friesPriceLabel.setText("1.99 $");

        javax.swing.GroupLayout lunchPanelLayout = new javax.swing.GroupLayout(lunchPanel);
        lunchPanel.setLayout(lunchPanelLayout);
        lunchPanelLayout.setHorizontalGroup(
                lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(orangeStockLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addComponent(orangeLabel2)
                                                .addGap(39, 39, 39)
                                                .addComponent(orangeCount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                                .addComponent(friesLabel)
                                                                .addGap(39, 39, 39))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lunchPanelLayout.createSequentialGroup()
                                                                .addComponent(friesStockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)))
                                                .addComponent(friesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addComponent(orangePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(orangePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addComponent(friesPictureLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(friesPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35))
        );
        lunchPanelLayout.setVerticalGroup(
                lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(orangePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lunchPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(orangeLabel2)
                                                        .addComponent(orangeCount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(orangeStockLabel2)
                                                .addGap(13, 13, 13))
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(orangePriceLabel)))
                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                                .addGap(50, 50, 50)
                                                                .addGroup(lunchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(friesLabel)
                                                                        .addComponent(friesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(friesStockLabel))
                                                        .addGroup(lunchPanelLayout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addComponent(friesPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(39, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lunchPanelLayout.createSequentialGroup()
                                                .addComponent(friesPriceLabel)
                                                .addGap(80, 80, 80))))
        );

        menuPanel.addTab("Lunch Menu", lunchPanel);

        burgerLabel.setText("Burger");

        burgerStockLabel.setText("Stock: ");

        ribsLabel.setText("Ribs");

        ribsStockLabel.setText("Stock: ");

        ribsPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ribs.png"))); // NOI18N

        burgerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/birgir.png"))); // NOI18N

        burgerPriceLabel1.setText("1.99 $");

        ribsPriceLabel.setText("1.99 $");

        javax.swing.GroupLayout dinnerPanelLayout = new javax.swing.GroupLayout(dinnerPanel);
        dinnerPanel.setLayout(dinnerPanelLayout);
        dinnerPanelLayout.setHorizontalGroup(
                dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                                .addGroup(dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                                                .addComponent(ribsLabel)
                                                                .addGap(39, 39, 39))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dinnerPanelLayout.createSequentialGroup()
                                                                .addComponent(ribsStockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)))
                                                .addComponent(ribsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46)
                                                .addComponent(ribsPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                                .addGroup(dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(burgerLabel)
                                                        .addComponent(burgerStockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(27, 27, 27)
                                                .addComponent(burgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(burgerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(burgerPriceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ribsPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(41, Short.MAX_VALUE))
        );
        dinnerPanelLayout.setVerticalGroup(
                dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(burgerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(ribsPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(39, Short.MAX_VALUE))
                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(burgerLabel)
                                        .addComponent(burgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burgerStockLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(dinnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ribsLabel)
                                        .addComponent(ribsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ribsStockLabel)
                                .addGap(51, 51, 51))
                        .addGroup(dinnerPanelLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(burgerPriceLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ribsPriceLabel)
                                .addGap(75, 75, 75))
        );

        menuPanel.addTab("Dinner Menu", dinnerPanel);

        clientNameTiitleLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        clientNameTiitleLabel.setText(" ");

        exitButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        exitButton.setText("Exit");

        backLoginButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        backLoginButton.setText("Back");
        backLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backLoginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(34, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(exitButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backLoginButton)
                                                .addGap(63, 63, 63))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clientNameTiitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(languageToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(661, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(clientNameTiitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(exitButton)
                                        .addComponent(backLoginButton))
                                .addGap(24, 24, 24))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(languageToggle)
                                        .addContainerGap(378, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void languageToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageToggleActionPerformed
        if (languageToggle.isSelected()) {
            loadLanguage(("fr"));
            languageToggle.setText("English");
        } else {
            loadLanguage("en");
            languageToggle.setText("French");
        }
    }//GEN-LAST:event_languageToggleActionPerformed

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        int fries = (int) friesCount.getValue();
        int burger = (int) burgerCount.getValue();
        int ribs = (int) ribsCount.getValue();
        int rice = (int) riceCount.getValue();
        int orange = (int) orangeCount2.getValue();
        int coffee = (int) coffeeCount.getValue();
        if (!(fries > 0 || burger > 0 || ribs > 0 || rice > 0 || coffee > 0 || orange > 0)) {
            JOptionPane.showMessageDialog(this, "You must select at least one item!");
            return;
        }
        Order order = new Order(current.getId(), "ONGOING");
        List<Item> selectedItems = new ArrayList<>();

        ItemDAO itemDAO = new ItemDAO();

        if (burger > 0) {
            Item burgerItem = itemDAO.getItemById(1);
            if (burgerItem != null) {
                burgerItem.setStock(burgerItem.getStock() - burger);
                selectedItems.add(burgerItem);
            }
        }
        if (rice > 0) {
            Item riceItem = itemDAO.getItemById(2);
            if (riceItem != null) {
                riceItem.setStock(riceItem.getStock() - rice);
                selectedItems.add(riceItem);
            }
        }
        if (orange > 0) {
            Item orangeItem = itemDAO.getItemById(3);
            if (orangeItem != null) {
                orangeItem.setStock(orangeItem.getStock() - orange);
                selectedItems.add(orangeItem);
            }
        }
        if (coffee > 0) {
            Item coffeeItem = itemDAO.getItemById(4);
            if (coffeeItem != null) {
                coffeeItem.setStock(coffeeItem.getStock() - coffee);
                selectedItems.add(coffeeItem);
            }
        }
        if (ribs > 0) {
            Item ribsItem = itemDAO.getItemById(5);
            if (ribsItem != null) {
                ribsItem.setStock(ribsItem.getStock() - ribs);
                selectedItems.add(ribsItem);
            }
        }
        if (fries > 0) {
            Item friesItem = itemDAO.getItemById(6);
            if (friesItem != null) {
                friesItem.setStock(friesItem.getStock() - fries);
                selectedItems.add(friesItem);
            }
        }
        oc.addOrderItems(selectedItems, order.getOrderId());
        oc.addOrder(order);

        DriverTask driverTask = new DriverTask();
        Thread driverThread = new Thread(driverTask);
        driverThread.start();

        new OrderUI(current).setVisible(true);
        this.dispose();

    }//GEN-LAST:event_orderButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void backLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backLoginButtonActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backLoginButtonActionPerformed
    // End of variables declaration                   
}
