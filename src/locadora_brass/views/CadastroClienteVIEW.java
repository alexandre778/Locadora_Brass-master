package locadora_brass.views;

import locadora_brass.dto.ClienteDTO;
import locadora_brass.servicos.ClienteService;
import locadora_brass.servicos.VeiculoService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadastroClienteVIEW extends JFrame {

    private JTextField txtNome, txtCpfCnpj, txtDataNascimento, txtEndereco;
    private ClienteService clienteService;
    private VeiculoService veiculoService;

    public CadastroClienteVIEW(ClienteService clienteService, VeiculoService veiculoService) {
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Cliente");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(45, 62, 80));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(criarCampoComLabel("Nome:", txtNome = new JTextField()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(criarCampoComLabel("CPF/CNPJ:", txtCpfCnpj = new JTextField()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(criarCampoComLabel("Data Nascimento (dd/MM/yyyy):", txtDataNascimento = new JTextField()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(criarCampoComLabel("Endereço:", txtEndereco = new JTextField()));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel botoesPanel = new JPanel();
        botoesPanel.setBackground(new Color(45, 62, 80));
        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnSalvar = new JButton("Salvar");
        estilizarBotao(btnSalvar);
        btnSalvar.addActionListener(e -> salvarCliente());

        JButton btnCancelar = new JButton("Cancelar");
        estilizarBotao(btnCancelar);
        btnCancelar.addActionListener(e -> dispose());

        JButton btnProximo = new JButton("Próximo: Veículo");
        estilizarBotaoVerde(btnProximo);
        btnProximo.addActionListener(e -> {
            dispose();
            new CadastroVeiculoVIEW(veiculoService, clienteService).setVisible(true);
        });

        botoesPanel.add(btnSalvar);
        botoesPanel.add(btnCancelar);
        botoesPanel.add(btnProximo);

        mainPanel.add(botoesPanel);

        add(mainPanel);
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

    private void estilizarBotao(JButton btn) {
        btn.setBackground(new Color(66, 133, 244));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(100, 40));
    }

    private void estilizarBotaoVerde(JButton btn) {
        btn.setBackground(new Color(15, 157, 88));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(140, 40));
    }

    private void salvarCliente() {
        String nome = txtNome.getText().trim();
        String cpfCnpj = txtCpfCnpj.getText().trim();
        String dataNascimentoStr = txtDataNascimento.getText().trim();
        String endereco = txtEndereco.getText().trim();

        if (nome.isEmpty() || cpfCnpj.isEmpty() || dataNascimentoStr.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome(nome);
        cliente.setCpfCnpj(cpfCnpj);
        cliente.setDataNascimento(dataNascimento);
        cliente.setEndereco(endereco);

        boolean sucesso = clienteService.cadastrar(cliente);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
