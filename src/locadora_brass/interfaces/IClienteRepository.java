package locadora_brass.interfaces;

import locadora_brass.dto.ClienteDTO;
import java.util.List;

public interface IClienteRepository {
    boolean cadastrar(ClienteDTO cliente);
    List<ClienteDTO> listarTodos();
    // outros métodos, se houver
}
