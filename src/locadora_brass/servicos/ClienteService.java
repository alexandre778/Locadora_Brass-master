package locadora_brass.servicos;

import locadora_brass.dto.ClienteDTO;
import locadora_brass.repositorios.IClienteRepository;

import java.util.List;

public class ClienteService {

    private final IClienteRepository clienteRepo;

    public ClienteService(IClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public boolean cadastrar(ClienteDTO cliente) {
        // Aqui pode ter validações extras
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            return false;
        }
        return clienteRepo.salvar(cliente);
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepo.listarTodos();
    }

    public ClienteDTO buscarPorId(int id) {
        return clienteRepo.buscarPorId(id);
    }
}
