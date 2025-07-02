package locadora_brass.views;

import locadora_brass.dto.VeiculoDTO;
import locadora_brass.servicos.ClienteService;
import locadora_brass.servicos.VeiculoService;

import javax.swing.*;
import java.awt.*;

public class CadastroVeiculoVIEW extends JFrame {

    private JTextField txtModelo, txtPlaca, txtTipo, txtAno, txtDiaria, txtStatus;
    private JButton btnSalvar, btnCancelar, btnVoltar;
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;

    public CadastroVeiculoVIEW(VeiculoService veiculoService, ClienteService clienteService) {
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Veículo");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = criarPainelFormulario();
        add(panel);

        configurarAcoes();
    }

    private JPanel criarPainelFormulario() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 62, 80));
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        txtModelo = adicionarLabelETextField(panel, "Modelo:", labelFont, fieldFont);
        txtPlaca = adicionarLabelETextField(panel, "Placa:", labelFont, fieldFont);
        txtTipo = adicionarLabelETextField(panel, "Tipo:", labelFont, fieldFont);
        txtAno = adicionarLabelETextField(panel, "Ano:", labelFont, fieldFont);
        txtDiaria = adicionarLabelETextField(panel, "Diária:", labelFont, fieldFont);
        txtStatus = adicionarLabelETextField(panel, "Status:", labelFont, fieldFont, "Disponível");

        btnSalvar = criarBotao("Salvar");
        btnCancelar = criarBotao("Cancelar");
        btnVoltar = criarBotao("Voltar");

        panel.add(btnSalvar);
        panel.add(btnCancelar);
        panel.add(btnVoltar);

        return panel;
    }

    private JTextField adicionarLabelETextField(JPanel panel, String textoLabel, Font labelFont, Font fieldFont) {
        return adicionarLabelETextField(panel, textoLabel, labelFont, fieldFont, "");
    }

    private JTextField adicionarLabelETextField(JPanel panel, String textoLabel, Font labelFont, Font fieldFont, String textoInicial) {
        JLabel label = new JLabel(textoLabel);
        label.setForeground(Color.WHITE);
        label.setFont(labelFont);

        JTextField textField = new JTextField(textoInicial);
        textField.setFont(fieldFont);

        panel.add(label);
        panel.add(textField);

        return textField;
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(66, 133, 244));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return btn;
    }

    private void configurarAcoes() {
        btnSalvar.addActionListener(e -> salvarVeiculo());
        btnCancelar.addActionListener(e -> dispose());
        btnVoltar.addActionListener(e -> {
            dispose();
            new CadastroClienteVIEW(clienteService, veiculoService).setVisible(true);
        });
    }

    private void salvarVeiculo() {
        try {
            String modelo = txtModelo.getText().trim();
            String placa = txtPlaca.getText().trim();
            String tipo = txtTipo.getText().trim();
            int ano = Integer.parseInt(txtAno.getText().trim());
            double diaria = Double.parseDouble(txtDiaria.getText().trim());
            String status = txtStatus.getText().trim();

            if (modelo.isEmpty() || placa.isEmpty() || tipo.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            VeiculoDTO veiculo = new VeiculoDTO();
            veiculo.setModelo(modelo);
            veiculo.setPlaca(placa);
            veiculo.setTipo(tipo);
            veiculo.setAno(ano);
            veiculo.setDiaria(diaria);
            veiculo.setStatus(status);

            boolean sucesso = veiculoService.cadastrarVeiculo(veiculo);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso! ID: " + veiculo.getId());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar veículo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e Diária devem ser numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
