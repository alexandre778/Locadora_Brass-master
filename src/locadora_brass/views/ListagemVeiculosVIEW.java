package locadora_brass.views;

import locadora_brass.dto.VeiculoDTO;
import locadora_brass.dto.ClienteDTO;
import locadora_brass.servicos.ClienteService;
import locadora_brass.servicos.LocacaoService;
import locadora_brass.servicos.VeiculoService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ListagemVeiculosVIEW extends JFrame {

    private JTable tabelaVeiculos;
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;
    private final LocacaoService locacaoService;

    public ListagemVeiculosVIEW(VeiculoService veiculoService, ClienteService clienteService, LocacaoService locacaoService) {
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
        this.locacaoService = locacaoService;
        initComponents();
        carregarVeiculosDisponiveis();
    }

    private void initComponents() {
        setTitle("Listagem de Veículos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(45, 62, 80));
        setContentPane(mainPanel);

        tabelaVeiculos = new JTable();
        tabelaVeiculos.setFillsViewportHeight(true);
        tabelaVeiculos.setBackground(new Color(70, 85, 105));
        tabelaVeiculos.setForeground(Color.WHITE);
        tabelaVeiculos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaVeiculos.setRowHeight(25);
        tabelaVeiculos.setSelectionBackground(new Color(66, 133, 244));
        tabelaVeiculos.setSelectionForeground(Color.WHITE);

        JTableHeader header = tabelaVeiculos.getTableHeader();
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(tabelaVeiculos);
        scrollPane.getViewport().setBackground(new Color(70, 85, 105));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.setBackground(new Color(45, 62, 80));

        // Botão para alugar veículo selecionado
        JButton btnAlugarSelecionado = new JButton("Alugar Selecionado");
        btnAlugarSelecionado.setBackground(new Color(15, 157, 88));
        btnAlugarSelecionado.setForeground(Color.WHITE);
        btnAlugarSelecionado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAlugarSelecionado.setPreferredSize(new Dimension(180, 35));
        btnAlugarSelecionado.setFocusPainted(false);
        btnAlugarSelecionado.addActionListener(e -> alugarVeiculoSelecionado());

        // Botão para abrir ListagemClientesVIEW
        JButton btnListarClientes = new JButton("Listar Clientes");
        btnListarClientes.setBackground(new Color(66, 133, 244));
        btnListarClientes.setForeground(Color.WHITE);
        btnListarClientes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnListarClientes.setPreferredSize(new Dimension(140, 35));
        btnListarClientes.setFocusPainted(false);
        btnListarClientes.addActionListener(e -> {
            new ListagemClientesVIEW(clienteService).setVisible(true);
        });

        // Botão voltar (sem MenuPrincipalVIEW)
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(66, 133, 244));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setPreferredSize(new Dimension(100, 35));
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a tela apenas
        });

        botoesPanel.add(btnAlugarSelecionado);
        botoesPanel.add(btnListarClientes);
        botoesPanel.add(btnVoltar);
        mainPanel.add(botoesPanel, BorderLayout.SOUTH);
    }

    private void carregarVeiculosDisponiveis() {
        List<VeiculoDTO> lista = veiculoService.listarDisponiveis();

        String[] colunas = {"ID", "Modelo", "Placa", "Tipo", "Ano", "Diária", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (VeiculoDTO v : lista) {
            Object[] row = {
                v.getId(),
                v.getModelo(),
                v.getPlaca(),
                v.getTipo(),
                v.getAno(),
                v.getDiaria(),
                v.getStatus()
            };
            model.addRow(row);
        }

        tabelaVeiculos.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaVeiculos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID
        tabelaVeiculos.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Ano
        tabelaVeiculos.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Diária
    }

    private void alugarVeiculoSelecionado() {
        int linhaSelecionada = tabelaVeiculos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela para alugar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int veiculoId = (int) tabelaVeiculos.getValueAt(linhaSelecionada, 0);
        VeiculoDTO veiculoSelecionado = veiculoService.buscarPorId(veiculoId);

        if (veiculoSelecionado != null) {
            dispose();
            LocacaoVIEW locacaoView = new LocacaoVIEW(locacaoService, veiculoSelecionado, clienteService, veiculoService);
            locacaoView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veículo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
