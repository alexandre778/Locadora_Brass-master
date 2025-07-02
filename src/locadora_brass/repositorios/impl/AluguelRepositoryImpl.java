package locadora_brass.repositorios.impl;

import locadora_brass.repositorios.IAluguelRepository;

public class AluguelRepositoryImpl implements IAluguelRepository {

    @Override
    public boolean realizarAluguel(int clienteId, int veiculoId, int dias) {
        // Aqui vai a implementação para realizar o aluguel, ex:
        // salvar no banco, verificar regras, etc.
        // Exemplo simples (substitua pela lógica real):
        System.out.println("Aluguel realizado: clienteId=" + clienteId + ", veiculoId=" + veiculoId + ", dias=" + dias);
        return true;
    }
}
