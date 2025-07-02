// === Arquivo: locadora_brass/repositorios/IAluguelRepository.java ===

package locadora_brass.repositorios;

public interface IAluguelRepository {
    boolean realizarAluguel(int clienteId, int veiculoId, int dias);
}