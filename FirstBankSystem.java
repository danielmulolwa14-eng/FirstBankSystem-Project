package vu.group.firstbanksystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class FirstBankSystem extends JFrame {

    // ================= COMPOSANTS DU FORMULAIRE =================
    private JTextField txtName, txtPhone, txtEmail, txtDeposit, txtNIN;
    private JPasswordField txtPIN; 
    private JComboBox<String> cmbBranch, cmbType;
    private JComboBox<Integer> cmbDay, cmbMonth, cmbYear; // DOB ComboBoxes
    private JTextArea txtAreaOutput;
    private JButton btnSubmit, btnClear;
    private JTextField txtTransactionAmount;
    private JButton btnDeposit, btnWithdraw;
    private JLabel lblAccountStatus;

    private Account currentActiveAccount = null;

    public FirstBankSystem() {
        setTitle("First Bank Uganda - Client Enrollment & Transaction System");
        setSize(850, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        mainContainer.add(createEnrollmentPanel());
        mainContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        mainContainer.add(createTransactionPanel());

        add(mainContainer, BorderLayout.NORTH);
        add(createOutputPanel(), BorderLayout.CENTER);
        add(createStatusPanel(), BorderLayout.SOUTH);

        attachEventListeners();
        setVisible(true);
    }

    private JPanel createEnrollmentPanel() {
        JPanel panel = new JPanel(new GridLayout(12, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), "CLIENT ENROLLMENT FORM"));
        panel.setBackground(new Color(245, 250, 255));

        panel.add(new JLabel("Full Name:")); txtName = new JTextField(); panel.add(txtName);
        panel.add(new JLabel("Branch:")); String[] b = {"KLA", "GUL", "MBA", "JIN", "MBL"}; cmbBranch = new JComboBox<>(b); panel.add(cmbBranch);
        panel.add(new JLabel("Account Type:")); String[] t = {"Savings", "Current", "Student"}; cmbType = new JComboBox<>(t); panel.add(cmbType);
        panel.add(new JLabel("Phone (+256...):")); txtPhone = new JTextField(); panel.add(txtPhone);
        panel.add(new JLabel("Email:")); txtEmail = new JTextField(); panel.add(txtEmail);
        
        // DOB Panels
        panel.add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cmbDay = new JComboBox<>(); cmbMonth = new JComboBox<>(); cmbYear = new JComboBox<>();
        for(int i=1; i<=31; i++) cmbDay.addItem(i);
        for(int i=1; i<=12; i++) cmbMonth.addItem(i);
        for(int i=java.time.Year.now().getValue()-18; i>=1950; i--) cmbYear.addItem(i);
        dobPanel.add(cmbDay); dobPanel.add(cmbMonth); dobPanel.add(cmbYear);
        panel.add(dobPanel);

        // Update days listener
        cmbMonth.addActionListener(e -> updateDays());
        cmbYear.addActionListener(e -> updateDays());

        panel.add(new JLabel("NIN (14 char):")); txtNIN = new JTextField(); panel.add(txtNIN);
        panel.add(new JLabel("PIN (4-6 digits):")); txtPIN = new JPasswordField(); panel.add(txtPIN);
        panel.add(new JLabel("Deposit:")); txtDeposit = new JTextField(); panel.add(txtDeposit);

        btnSubmit = new JButton("Submit Enrollment"); btnSubmit.setBackground(new Color(34, 139, 34)); btnSubmit.setForeground(Color.WHITE);
        btnClear = new JButton("Clear"); btnClear.setBackground(new Color(220, 20, 60)); btnClear.setForeground(Color.WHITE);
        
        panel.add(btnSubmit); panel.add(btnClear);
        return panel;
    }

    private void updateDays() {
        int year = (int)cmbYear.getSelectedItem();
        int month = (int)cmbMonth.getSelectedItem();
        int maxDays = LocalDate.of(year, month, 1).lengthOfMonth();
        cmbDay.removeAllItems();
        for(int i=1; i<=maxDays; i++) cmbDay.addItem(i);
    }

    private JPanel createTransactionPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("ACCOUNT OPERATIONS"));
        txtTransactionAmount = new JTextField(10);
        btnDeposit = new JButton("Deposit"); btnWithdraw = new JButton("Withdraw");
        panel.add(new JLabel("Amount:")); panel.add(txtTransactionAmount); panel.add(btnDeposit); panel.add(btnWithdraw);
        return panel;
    }

    private JPanel createOutputPanel() {
        txtAreaOutput = new JTextArea(10, 50);
        txtAreaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtAreaOutput);
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder("TRANSACTION LOGS"));
        p.add(scroll);
        return p;
    }

    private JPanel createStatusPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblAccountStatus = new JLabel("Status: Waiting for enrollment...");
        p.add(lblAccountStatus);
        return p;
    }

    private void attachEventListeners() {
        btnSubmit.addActionListener(e -> { 
            if(validateAllFields()) { 
                processEnrollment();
                saveToDatabase();
            } 
        });
        btnClear.addActionListener(e -> clearFields());
        btnDeposit.addActionListener(e -> handleTransaction(true));
        btnWithdraw.addActionListener(e -> handleTransaction(false));
    }

    private boolean validateAllFields() {
        // NIN Uppercase conversion
        String nin = txtNIN.getText().toUpperCase();
        txtNIN.setText(nin);
        if (!nin.matches("^[A-Z0-9]{14}$")) { JOptionPane.showMessageDialog(this, "Erreur NIN : 14 caractères alphanumériques."); return false; }
        
        // Phone format
        if (!txtPhone.getText().matches("^\\+256\\d{9}$")) { JOptionPane.showMessageDialog(this, "Erreur Téléphone : Format +256XXXXXXXXX requis."); return false; }

        // PIN
        String pin = new String(txtPIN.getPassword());
        if (!pin.matches("^\\d{4,6}$") || pin.matches("(.)\\1+")) { JOptionPane.showMessageDialog(this, "Erreur PIN : 4-6 chiffres, pas de chiffres identiques."); return false; }
        
        // Age validation
        int age = java.time.Year.now().getValue() - (Integer)cmbYear.getSelectedItem();
        if (age < 18 || age > 75) { JOptionPane.showMessageDialog(this, "Erreur âge : 18-75 ans requis."); return false; }
        return true;
    }

    private void processEnrollment() {
        String name = txtName.getText();
        double dep = Double.parseDouble(txtDeposit.getText());
        String type = cmbType.getSelectedItem().toString();

        if(type.equals("Savings")) currentActiveAccount = new SavingsAccount(name, "KLA", txtPhone.getText(), txtEmail.getText(), "2000-01-01", dep);
        else if(type.equals("Current")) currentActiveAccount = new CurrentAccount(name, "KLA", txtPhone.getText(), txtEmail.getText(), "2000-01-01", dep);
        else currentActiveAccount = new StudentAccount(name, "KLA", txtPhone.getText(), txtEmail.getText(), "2000-01-01", dep);

        currentActiveAccount.setAccountNumber(cmbBranch.getSelectedItem() + "-2026-" + (int)(Math.random()*900000));
        lblAccountStatus.setText("Active: " + currentActiveAccount.getAccountNumber());
        txtAreaOutput.append("Enrollment Successful: " + currentActiveAccount.getAccountNumber() + "\n");
    }

    private void saveToDatabase() {
        String url = "jdbc:ucanaccess://C:/Users/HP/Desktop/Java/BankDatabase.accdb";
        String sql = "INSERT INTO Accounts (AccountNumber, ClientName, AccountType, Balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, currentActiveAccount.getAccountNumber());
            pst.setString(2, txtName.getText());
            pst.setString(3, cmbType.getSelectedItem().toString());
            pst.setDouble(4, Double.parseDouble(txtDeposit.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Enregistrement réussi !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur base de données : " + e.getMessage());
        }
    }

    private void handleTransaction(boolean isDeposit) { }
    private void clearFields() { txtName.setText(""); txtNIN.setText(""); txtPIN.setText(""); }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new FirstBankSystem()); }
}