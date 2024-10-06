import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BankRegistrasi {

    // Components declaration
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField nameField;
    private JList<String> accountTypeList;
    private JSpinner transactionFrequencySpinner;
    private JSpinner birthDateSpinner;
    private JPasswordField passwordField, confirmPasswordField;
    
    public BankRegistrasi() {
        // Frame setup
        frame = new JFrame("Bank Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 2));

        // Name field
        JLabel nameLabel = new JLabel("Name: ");
        nameField = new JTextField(20);

        // Account type JList
        JLabel accountTypeLabel = new JLabel("Account Type: ");
        String[] accountTypes = {"Savings", "Current", "Fixed Deposit", "Recurring Deposit"};
        accountTypeList = new JList<>(accountTypes);
        accountTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountTypeList.setVisibleRowCount(4);

        // Transaction frequency spinner
        JLabel transactionFrequencyLabel = new JLabel("Transactions/Month: ");
        transactionFrequencySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        // Birth date spinner
        JLabel birthDateLabel = new JLabel("Birth Date: ");
        birthDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthDateSpinner, "dd/MM/yyyy");
        birthDateSpinner.setEditor(dateEditor);

        // Password field
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(10);

        // Confirm password field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordField = new JPasswordField(10);

        // Output area
        outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);

        // Buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(accountTypeLabel);
        frame.add(new JScrollPane(accountTypeList));
        frame.add(transactionFrequencyLabel);
        frame.add(transactionFrequencySpinner);
        frame.add(birthDateLabel);
        frame.add(birthDateSpinner);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmPasswordLabel);
        frame.add(confirmPasswordField);
        frame.add(submitButton);
        frame.add(resetButton);
        frame.add(new JScrollPane(outputArea));

        // Create menu bar with Reset and Exit options
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.addActionListener(new ResetButtonListener());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(resetMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Add menu bar to frame
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    // Action listener for submit button
    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String accountType = accountTypeList.getSelectedValue();
            int transactionFrequency = (int) transactionFrequencySpinner.getValue();
            Date birthDate = (Date) birthDateSpinner.getValue();
            char[] password = passwordField.getPassword();
            char[] confirmPassword = confirmPasswordField.getPassword();

            // Check if passwords match
            boolean passwordsMatch = String.valueOf(password).equals(String.valueOf(confirmPassword));

            // Output to JTextArea
            StringBuilder output = new StringBuilder();
            output.append("Name: ").append(name).append("\n");
            output.append("Account Type: ").append(accountType).append("\n");
            output.append("Transactions per Month: ").append(transactionFrequency).append("\n");
            output.append("Birth Date: ").append(birthDate).append("\n");
            output.append("Password Match: ").append(passwordsMatch ? "Yes" : "No").append("\n");

            outputArea.setText(output.toString());
        }
    }

    // Action listener for reset button
    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            nameField.setText("");
            accountTypeList.clearSelection();
            transactionFrequencySpinner.setValue(1);
            passwordField.setText("");
            confirmPasswordField.setText("");
            outputArea.setText("");
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new BankRegistrasi());
    }
}
