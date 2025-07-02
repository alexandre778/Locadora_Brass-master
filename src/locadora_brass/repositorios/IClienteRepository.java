package locadora_brass.repositorios;

import locadora_brass.dto.ClienteDTO;

import java.util.List;

public interface IClienteRepository {
    boolean salvar(ClienteDTO cliente);
    List<ClienteDTO> listarTodos();
    ClienteDTO buscarPorId(int id);
}
