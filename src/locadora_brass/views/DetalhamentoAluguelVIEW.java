package locadora_brass.views;

import locadora_brass.dto.AluguelDTO;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class DetalhamentoAluguelVIEW extends JFrame {

    public DetalhamentoAluguelVIEW(AluguelDTO aluguel) {
        setTitle("Detalhes do Aluguel");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 62, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titulo = new JLabel("Informações do Aluguel");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));

        // Informações do aluguel
        panel.add(criarLabel("Cliente: " + aluguel.getNomeCliente()));
        panel.add(criarLabel("Veículo: " + aluguel.getModeloVeiculo()));
        panel.add(criarLabel("Dias alugados: " + aluguel.getDias()));
        panel.add(criarLabel("Valor total: R$ " + String.format("%.2f", aluguel.getValorTotal())));

        if (aluguel.getDataAluguel() != null) {
            String dataFormatada = aluguel.getDataAluguel().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            panel.add(criarLabel("Data do aluguel: " + dataFormatada));
        }

        panel.add(Box.createVerticalStrut(25));

        // Botão para fechar a janela
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFechar.setBackground(new Color(219, 68, 55));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnFechar.setPreferredSize(new Dimension(100, 35));
        btnFechar.addActionListener(e -> dispose());

        panel.add(btnFechar);
        panel.add(Box.createVerticalStrut(10));

        setContentPane(panel);
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }
}
