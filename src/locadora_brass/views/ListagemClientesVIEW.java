package locadora_brass.views;

import locadora_brass.dto.ClienteDTO;
import locadora_brass.servicos.ClienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ListagemClientesVIEW extends JFrame {

    private JTable tabelaClientes;
    private final ClienteService clienteService;

    public ListagemClientesVIEW(ClienteService clienteService) {
        this.clienteService = clienteService;
        initComponents();
        carregarClientes();
    }

    private void initComponents() {
        setTitle("Listagem de Clientes");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(45, 62, 80));
        setContentPane(mainPanel);

        tabelaClientes = new JTable();
        tabelaClientes.setFillsViewportHeight(true);
        tabelaClientes.setBackground(new Color(70, 85, 105));
        tabelaClientes.setForeground(Color.WHITE);
        tabelaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaClientes.setRowHeight(25);
        tabelaClientes.setSelectionBackground(new Color(66, 133, 244));
        tabelaClientes.setSelectionForeground(Color.WHITE);

        JTableHeader header = tabelaClientes.getTableHeader();
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.getViewport().setBackground(new Color(70, 85, 105));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.setBackground(new Color(45, 62, 80));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(66, 133, 244));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setPreferredSize(new Dimension(100, 35));
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> dispose());

        botoesPanel.add(btnVoltar);
        mainPanel.add(botoesPanel, BorderLayout.SOUTH);
    }

    private void carregarClientes() {
        List<ClienteDTO> lista = clienteService.listarTodos();

        String[] colunas = {"ID", "Nome", "CPF/CNPJ", "Data Nascimento", "Endereço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (ClienteDTO c : lista) {
            Object[] row = {
                c.getId(),
                c.getNome(),
                c.getCpfCnpj(),
                c.getDataNascimento() != null ? c.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                c.getEndereco()
            };
            model.addRow(row);
        }

        tabelaClientes.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaClientes.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID
        tabelaClientes.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Data Nascimento
    }
}
