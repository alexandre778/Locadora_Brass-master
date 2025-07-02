package locadora_brass.dto;

public class VeiculoDTO {
    private int id;
    private String modelo;
    private String placa;
    private String tipo;
    private int ano;
    private double diaria;
    private String status;

    // Getters e Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public double getDiaria() { return diaria; }
    public void setDiaria(double diaria) { this.diaria = diaria; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + " - " + modelo;
    }
}
