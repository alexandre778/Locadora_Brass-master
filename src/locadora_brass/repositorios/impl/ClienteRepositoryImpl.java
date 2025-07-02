package locadora_brass.repositorios.impl;

import locadora_brass.dto.ClienteDTO;
import locadora_brass.repositorios.IClienteRepository;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements IClienteRepository {

    private final List<ClienteDTO> clientes = new ArrayList<>();
    private int contadorId = 1;

    @Override
    public boolean salvar(ClienteDTO cliente) {
        if (cliente.getId() == 0) {
            cliente.setId(contadorId++);
            clientes.add(cliente);
            return true;
        } else {
            // Atualiza cliente existente (simplificado)
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getId() == cliente.getId()) {
                    clientes.set(i, cliente);
                    return true;
                }
            }
            return false; // Cliente não encontrado para atualizar
        }
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public ClienteDTO buscarPorId(int id) {
        for (ClienteDTO c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // Não encontrado
    }
}
