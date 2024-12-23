package studikasus2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeManagementApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNip, txtNama, txtJabatan, txtDepartemen, txtGaji;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hr_system";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public EmployeeManagementApp() {
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Sistem Manajemen Karyawan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        // Create table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("NIP");
        tableModel.addColumn("Nama Karyawan");
        tableModel.addColumn("Jabatan");
        tableModel.addColumn("Departemen");
        tableModel.addColumn("Gaji");
        
        // Create table
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        txtNip = new JTextField();
        txtNama = new JTextField();
        txtJabatan = new JTextField();
        txtDepartemen = new JTextField();
        txtGaji = new JTextField();
        
        inputPanel.add(new JLabel("NIP:"));
        inputPanel.add(txtNip);
        inputPanel.add(new JLabel("Nama Karyawan:"));
        inputPanel.add(txtNama);
        inputPanel.add(new JLabel("Jabatan:"));
        inputPanel.add(txtJabatan);
        inputPanel.add(new JLabel("Departemen:"));
        inputPanel.add(txtDepartemen);
        inputPanel.add(new JLabel("Gaji:"));
        inputPanel.add(txtGaji);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Ubah");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Bersihkan");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        // Add components to frame
        setLayout(new BorderLayout(5, 5));
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add event listeners
        btnAdd.addActionListener(e -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnClear.addActionListener(e -> clearFields());
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtNip.setText(table.getValueAt(row, 0).toString());
                    txtNama.setText(table.getValueAt(row, 1).toString());
                    txtJabatan.setText(table.getValueAt(row, 2).toString());
                    txtDepartemen.setText(table.getValueAt(row, 3).toString());
                    txtGaji.setText(table.getValueAt(row, 4).toString());
                }
            }
        });
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM employees";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("nip"),
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("departemen"),
                    rs.getDouble("gaji")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void addEmployee() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO employees (nip, nama, jabatan, departemen, gaji) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, txtNip.getText());
            stmt.setString(2, txtNama.getText());
            stmt.setString(3, txtJabatan.getText());
            stmt.setString(4, txtDepartemen.getText());
            stmt.setDouble(5, Double.parseDouble(txtGaji.getText()));
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data karyawan berhasil ditambahkan!");
            loadData();
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Gaji harus berupa angka!");
        }
    }
    
    private void updateEmployee() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE employees SET nama=?, jabatan=?, departemen=?, gaji=? WHERE nip=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, txtNama.getText());
            stmt.setString(2, txtJabatan.getText());
            stmt.setString(3, txtDepartemen.getText());
            stmt.setDouble(4, Double.parseDouble(txtGaji.getText()));
            stmt.setString(5, txtNip.getText());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data karyawan berhasil diperbarui!");
            loadData();
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Gaji harus berupa angka!");
        }
    }
    
    private void deleteEmployee() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM employees WHERE nip=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, txtNip.getText());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data karyawan berhasil dihapus!");
            loadData();
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void clearFields() {
        txtNip.setText("");
        txtNama.setText("");
        txtJabatan.setText("");
        txtDepartemen.setText("");
        txtGaji.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeManagementApp().setVisible(true);
        });
    }
}