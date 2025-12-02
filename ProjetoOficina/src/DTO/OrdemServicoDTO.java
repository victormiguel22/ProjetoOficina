package DTO;

import java.util.Date;

public class OrdemServicoDTO {
    private int id;
    private Date dataEntrada;
    private Date dataSaida;
    private String descricao;
    private String servicos;
    private double valorPecas;
    private double valorMaoObra;
    private double valorTotal;
    private String status;
    private int veiculoId;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(Date dataEntrada) { this.dataEntrada = dataEntrada; }
    public Date getDataSaida() { return dataSaida; }
    public void setDataSaida(Date dataSaida) { this.dataSaida = dataSaida; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getServicos() { return servicos; }
    public void setServicos(String servicos) { this.servicos = servicos; }
    public double getValorPecas() { return valorPecas; }
    public void setValorPecas(double valorPecas) { this.valorPecas = valorPecas; }
    public double getValorMaoObra() { return valorMaoObra; }
    public void setValorMaoObra(double valorMaoObra) { this.valorMaoObra = valorMaoObra; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getVeiculoId() { return veiculoId; }
    public void setVeiculoId(int veiculoId) { this.veiculoId = veiculoId; }
}