package locadora_brass.servicos;

import locadora_brass.dto.VeiculoDTO;
import locadora_brass.repositorios.IVeiculoRepository;

import java.util.List;

public class VeiculoService {

    private IVeiculoRepository veiculoRepo;

    public VeiculoService(IVeiculoRepository veiculoRepo) {
        this.veiculoRepo = veiculoRepo;
    }

    public boolean cadastrarVeiculo(VeiculoDTO veiculo) {
        return veiculoRepo.salvar(veiculo);
    }

    public List<VeiculoDTO> listarTodos() {
        return veiculoRepo.listarTodos();
    }

    public List<VeiculoDTO> listarDisponiveis() {
        return veiculoRepo.listarDisponiveis();
    }

    public VeiculoDTO buscarPorId(int id) {
        return veiculoRepo.buscarPorId(id);
    }
}
