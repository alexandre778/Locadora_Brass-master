package locadora_brass.views;

import locadora_brass.dto.AluguelDTO;
import locadora_brass.repositorios.impl.AluguelRepositoryImpl;
import locadora_brass.repositorios.impl.ClienteRepositoryImpl;
import locadora_brass.repositorios.impl.VeiculoRepositoryImpl;
import locadora_brass.servicos.ClienteService;
import locadora_brass.servicos.LocacaoService;
import locadora_brass.servicos.VeiculoService;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalVIEW extends JFrame {

    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final LocacaoService locacaoService;

    public MenuPrincipalVIEW() {
        // Inicializando os repositórios manuais (mock/impl)
        ClienteRepositoryImpl clienteRepo = new ClienteRepositoryImpl();
        VeiculoRepositoryImpl veiculoRepo = new VeiculoRepositoryImpl();
        AluguelRepositoryImpl aluguelRepo = new AluguelRepositoryImpl();

        this.clienteService = new ClienteService(clienteRepo);
        this.veiculoService = new VeiculoService(veiculoRepo);
        this.locacaoService = new LocacaoService(clienteRepo, veiculoRepo, aluguelRepo);

        initComponents();
    }

    private void initComponents() {
        setTitle("Locadora Brass - Menu Principal");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 62, 80));
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        Font fonte = new Font("Segoe UI", Font.BOLD, 16);

        JButton btnCliente = criarBotao("Cadastro de Cliente", fonte);
        btnCliente.addActionListener(e -> new CadastroClienteVIEW(clienteService, veiculoService).setVisible(true));

        JButton btnVeiculo = criarBotao("Cadastro de Veículo", fonte);
        btnVeiculo.addActionListener(e -> new CadastroVeiculoVIEW(veiculoService, clienteService).setVisible(true));

        JButton btnListarClientes = criarBotao("Listar Clientes", fonte);
        btnListarClientes.addActionListener(e -> new ListagemClientesVIEW(clienteService).setVisible(true));

        JButton btnListarVeiculos = criarBotao("Listar Veículos", fonte);
        btnListarVeiculos.addActionListener(e -> new ListagemVeiculosVIEW(veiculoService, clienteService, locacaoService).setVisible(true));

        JButton btnLocacao = criarBotao("Alugar Veículo", fonte);
        btnLocacao.addActionListener(e -> new LocacaoVIEW(locacaoService, null, clienteService, veiculoService).setVisible(true));

        JButton btnDetalharAluguel = criarBotao("Detalhar Aluguel (Exemplo)", fonte);
        btnDetalharAluguel.addActionListener(e -> {
            AluguelDTO aluguel = new AluguelDTO();
            aluguel.setNomeCliente("João da Silva");
            aluguel.setModeloVeiculo("Fiat Uno");
            aluguel.setDias(3);
            aluguel.setValorTotal(270.00);
            aluguel.setDataAluguel(java.time.LocalDate.now());

            new DetalhamentoAluguelVIEW(aluguel).setVisible(true);
        });

        JButton btnSair = criarBotao("Sair", fonte);
        btnSair.setBackground(new Color(219, 68, 55));
        btnSair.addActionListener(e -> System.exit(0));

        panel.add(btnCliente);
        panel.add(btnVeiculo);
        panel.add(btnListarClientes);
        panel.add(btnListarVeiculos);
        panel.add(btnLocacao);
        panel.add(btnDetalharAluguel);
        panel.add(btnSair);

        add(panel);
    }

    private JButton criarBotao(String texto, Font fonte) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(66, 133, 244));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(fonte);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipalVIEW().setVisible(true));
    }
}
