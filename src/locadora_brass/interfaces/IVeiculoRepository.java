// src/locadora_brass/interfaces/IVeiculoRepository.java
package locadora_brass.interfaces;

import locadora_brass.dto.VeiculoDTO;
import java.util.List;

public interface IVeiculoRepository {
    boolean salvar(VeiculoDTO veiculo);
    List<VeiculoDTO> buscarDisponiveis();
    List<VeiculoDTO> buscarTodos();

    public List<VeiculoDTO> listarDisponiveis();
}
