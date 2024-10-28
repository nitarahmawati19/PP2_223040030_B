import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryApp extends JFrame {
    private JTextField titleField;
    private JTextField authorField;
    private JComboBox<String> categoryComboBox;
    private JRadioButton availableRadio, borrowedRadio;
    private JCheckBox bahasaIndonesiaCheck, bahasaInggrisCheck;
    private JTable table;
    private DefaultTableModel tableModel;

    public LibraryApp() {
        setTitle("Perpustakaan App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem = new JMenuItem("Input Buku");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel untuk form input
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        // JTextField untuk Judul Buku
        formPanel.add(new JLabel("Judul Buku:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        // JTextField untuk Penulis
        formPanel.add(new JLabel("Penulis:"));
        authorField = new JTextField();
        formPanel.add(authorField);

        // JComboBox untuk Kategori
        formPanel.add(new JLabel("Kategori:"));
        categoryComboBox = new JComboBox<>(new String[] { "Fiksi", "Non-Fiksi", "Sejarah", "Teknologi" });
        formPanel.add(categoryComboBox);

        // JRadioButton untuk Status
        formPanel.add(new JLabel("Status:"));
        availableRadio = new JRadioButton("Tersedia");
        borrowedRadio = new JRadioButton("Dipinjam");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(availableRadio);
        statusGroup.add(borrowedRadio);
        JPanel radioPanel = new JPanel();
        radioPanel.add(availableRadio);
        radioPanel.add(borrowedRadio);
        formPanel.add(radioPanel);

        // JCheckBox untuk Bahasa
        formPanel.add(new JLabel("Bahasa:"));
        bahasaIndonesiaCheck = new JCheckBox("Indonesia");
        bahasaInggrisCheck = new JCheckBox("Inggris");
        JPanel languagePanel = new JPanel();
        languagePanel.add(bahasaIndonesiaCheck);
        languagePanel.add(bahasaInggrisCheck);
        formPanel.add(languagePanel);

        // Tabel untuk menampilkan data buku
        tableModel = new DefaultTableModel(new String[] { "Judul Buku", "Penulis", "Kategori", "Status", "Bahasa" }, 0);
        table = new JTable(tableModel);

        // Tombol untuk menambah data ke tabel
        JButton addButton = new JButton("Tambah Buku");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookToTable();
            }
        });

        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        getContentPane().add(addButton, BorderLayout.SOUTH);
    }

    private void addBookToTable() {
        // Ambil data dari form input
        String title = titleField.getText();
        String author = authorField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String status = availableRadio.isSelected() ? "Tersedia"
                : borrowedRadio.isSelected() ? "Dipinjam" : "Tidak Diketahui";

        // Ambil data bahasa
        StringBuilder languages = new StringBuilder();
        if (bahasaIndonesiaCheck.isSelected())
            languages.append("Indonesia ");
        if (bahasaInggrisCheck.isSelected())
            languages.append("Inggris");

        // Tambahkan data ke tabel
        tableModel.addRow(new Object[] { title, author, category, status, languages.toString() });

        // Kosongkan input setelah ditambahkan ke tabel
        clearForm();
    }

    private void clearForm() {
        titleField.setText("");
        authorField.setText("");
        categoryComboBox.setSelectedIndex(0);
        availableRadio.setSelected(true);
        bahasaIndonesiaCheck.setSelected(false);
        bahasaInggrisCheck.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}
