package locadora_brass.utils;

import java.time.LocalDate;
import java.time.Period;

public class ValidadorCliente {

    public static boolean isMaiorDeIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) return false;
        LocalDate hoje = LocalDate.now();
        Period idade = Period.between(dataNascimento, hoje);
        return idade.getYears() >= 18;
    }
}
