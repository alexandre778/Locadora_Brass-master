package locadora_brass.repositorios;

import locadora_brass.dto.VeiculoDTO;
import java.util.List;

public interface IVeiculoRepository {
    boolean salvar(VeiculoDTO veiculo);
    List<VeiculoDTO> listarTodos();
    List<VeiculoDTO> listarDisponiveis();
    VeiculoDTO buscarPorId(int id);
}
