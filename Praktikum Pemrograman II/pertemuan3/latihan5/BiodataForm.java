import java.awt.event.*;
import javax.swing.*;

public class BiodataForm extends JFrame {
    public BiodataForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label and text field for Name
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(50, 40, 100, 30); // Adjusted height for better alignment

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 40, 150, 30);

        // Label and text field for Phone Number
        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(50, 80, 100, 30); // Adjusted height for better alignment

        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(150, 80, 150, 30);

        // Label for Gender
        JLabel labelGender = new JLabel("Jenis Kelamin:");
        labelGender.setBounds(50, 120, 100, 30); // Adjusted height for better alignment

        // Radio buttons for Gender
        JRadioButton maleRadioButton = new JRadioButton("Laki-Laki", true);
        maleRadioButton.setBounds(150, 120, 100, 30);

        JRadioButton femaleRadioButton = new JRadioButton("Perempuan");
        femaleRadioButton.setBounds(150, 150, 100, 30);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Check box for WNA status
        JCheckBox checkBoxAktif = new JCheckBox("Warga Negara Asing", false);
        checkBoxAktif.setBounds(50, 200, 200, 30);

        // Save Button
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(50, 240, 100, 40);

        // Output TextArea
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(50, 300, 300, 150);
        txtOutput.setEditable(false);

        // ActionListener for Save button
        buttonSimpan.addActionListener((ActionEvent e) -> {
            String nama = textFieldNama.getText();
            String telepon = textFieldTelepon.getText();
            String gender = maleRadioButton.isSelected() ? "Laki-Laki" : "Perempuan";
            boolean isWNA = checkBoxAktif.isSelected();
            
            if (nama.isEmpty() || telepon.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama dan Nomor Telepon tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Append data to output
            txtOutput.append("Nama : " + nama + "\n");
            txtOutput.append("Nomor HP : " + telepon + "\n");
            txtOutput.append("Jenis Kelamin : " + gender + "\n");
            txtOutput.append("WNA : " + (isWNA ? "Ya" : "Bukan") + "\n");
            txtOutput.append("=============================================\n");
            
            // Clear inputs after saving
            textFieldNama.setText("");
            textFieldTelepon.setText("");
            genderGroup.clearSelection();
            maleRadioButton.setSelected(true); // Default back to male
            checkBoxAktif.setSelected(false);
        });

        // Add components to the frame
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(labelGender);
        this.add(maleRadioButton);
        this.add(femaleRadioButton);
        this.add(checkBoxAktif);
        this.add(buttonSimpan);
        this.add(txtOutput);

        // Set frame size and layout
        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            BiodataForm frame = new BiodataForm();
            frame.setVisible(true);
        });
    }
}
