// === Arquivo: locadora_brass/servicos/LocacaoService.java ===

package locadora_brass.servicos;

import locadora_brass.dto.ClienteDTO;
import locadora_brass.dto.VeiculoDTO;
import locadora_brass.repositorios.IAluguelRepository;
import locadora_brass.repositorios.IClienteRepository;
import locadora_brass.repositorios.IVeiculoRepository;

import java.time.LocalDate;
import java.time.Period;

public class LocacaoService {

    private final IClienteRepository clienteRepo;
    private final IVeiculoRepository veiculoRepo;
    private final IAluguelRepository aluguelRepo;

    public LocacaoService(IClienteRepository clienteRepo, IVeiculoRepository veiculoRepo, IAluguelRepository aluguelRepo) {
        this.clienteRepo = clienteRepo;
        this.veiculoRepo = veiculoRepo;
        this.aluguelRepo = aluguelRepo;
    }

    public boolean alugarVeiculo(int clienteId, int veiculoId, int dias) {
        ClienteDTO cliente = clienteRepo.buscarPorId(clienteId);
        VeiculoDTO veiculo = veiculoRepo.buscarPorId(veiculoId);

        if (cliente == null || veiculo == null || dias <= 0) return false;

        LocalDate nascimento = cliente.getDataNascimento();
        int idade = Period.between(nascimento, LocalDate.now()).getYears();

        if (idade < 21 || !"Disponível".equalsIgnoreCase(veiculo.getStatus())) return false;

        return aluguelRepo.realizarAluguel(clienteId, veiculoId, dias);
    }
}
