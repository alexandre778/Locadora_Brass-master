package locadora_brass.views;

import locadora_brass.dto.ClienteDTO;
import locadora_brass.dto.VeiculoDTO;
import locadora_brass.servicos.ClienteService;
import locadora_brass.servicos.LocacaoService;
import locadora_brass.servicos.VeiculoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LocacaoVIEW extends JFrame {

    private JComboBox<ClienteDTO> comboClientes;
    private JComboBox<VeiculoDTO> comboVeiculos;
    private JTextField txtDias;
    private JButton btnAlugar;
    private JButton btnCancelar;

    private final LocacaoService locacaoService;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final VeiculoDTO veiculoPreSelecionado;

    public LocacaoVIEW(LocacaoService locacaoService, VeiculoDTO veiculoPreSelecionado,
                       ClienteService clienteService, VeiculoService veiculoService) {
        this.locacaoService = locacaoService;
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
        this.veiculoPreSelecionado = veiculoPreSelecionado;

        initComponents();
        carregarClientes();
        carregarVeiculos();
    }

    private void initComponents() {
        setTitle("Alugar Veículo");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(45, 62, 80));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(criarLabelCombo("Cliente:", comboClientes = new JComboBox<>()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(criarLabelCombo("Veículo:", comboVeiculos = new JComboBox<>()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(criarCampoComLabel("Dias:", txtDias = new JTextField()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoesPanel.setBackground(new Color(45, 62, 80));

        btnAlugar = new JButton("Alugar");
        estilizarBotao(btnAlugar);
        btnCancelar = new JButton("Cancelar");
        estilizarBotao(btnCancelar);

        botoesPanel.add(btnAlugar);
        botoesPanel.add(btnCancelar);

        mainPanel.add(botoesPanel);
        add(mainPanel);

        btnAlugar.addActionListener(e -> confirmarAluguel());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JPanel criarLabelCombo(String texto, JComboBox<?> combo) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(45, 62, 80));

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(new Color(70, 85, 105));
        combo.setForeground(Color.WHITE);

        panel.add(label, BorderLayout.NORTH);
        panel.add(combo, BorderLayout.CENTER);

        return panel;
    }

    private JPanel criarCampoComLabel(String texto, JTextField campo) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(45, 62, 80));

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBackground(new Color(70, 85, 105));
        campo.setForeground(Color.WHITE);
        campo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        panel.add(label, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }

    private void estilizarBotao(JButton botao) {
        botao.setBackground(new Color(66, 133, 244));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(120, 40));
    }

    private void carregarClientes() {
        comboClientes.removeAllItems();
        List<ClienteDTO> clientes = clienteService.listarTodos();
        for (ClienteDTO c : clientes) {
            comboClientes.addItem(c);
        }
    }

    private void carregarVeiculos() {
        comboVeiculos.removeAllItems();
        List<VeiculoDTO> veiculos = veiculoService.listarDisponiveis();

        for (VeiculoDTO v : veiculos) {
            comboVeiculos.addItem(v);
        }

        if (veiculoPreSelecionado != null) {
            for (int i = 0; i < comboVeiculos.getItemCount(); i++) {
                if (comboVeiculos.getItemAt(i).getId() == veiculoPreSelecionado.getId()) {
                    comboVeiculos.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void confirmarAluguel() {
        ClienteDTO clienteSelecionado = (ClienteDTO) comboClientes.getSelectedItem();
        VeiculoDTO veiculoSelecionado = (VeiculoDTO) comboVeiculos.getSelectedItem();

        if (clienteSelecionado == null || veiculoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente e um veículo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int dias;
        try {
            dias = Integer.parseInt(txtDias.getText().trim());
            if (dias <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um número válido de dias (maior que zero).", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean sucesso = locacaoService.alugarVeiculo(clienteSelecionado.getId(), veiculoSelecionado.getId(), dias);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Aluguel registrado com sucesso!");
            dispose();
            // Exemplo: voltar para listagem de veículos após aluguel
            new ListagemVeiculosVIEW(veiculoService, clienteService, locacaoService).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao registrar aluguel. Verifique se o cliente tem 21+ anos e se o veículo está disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
