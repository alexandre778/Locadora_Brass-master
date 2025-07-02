package locadora_brass.interfaces;

import locadora_brass.dto.AluguelDTO;
import locadora_brass.dto.VeiculoDTO;
import java.util.List;

public interface IAluguelRepository {
    boolean salvar(AluguelDTO aluguel);
    boolean devolver(int aluguelId, int veiculoId);
    List<VeiculoDTO> listarAlugados();
    List<VeiculoDTO> listarDevolvidos();
    VeiculoDTO buscarPorId(int veiculoId);
}
