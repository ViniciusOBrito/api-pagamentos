package com.desafio.pagamentos.domain.dtos;

import com.desafio.pagamentos.domain.entities.Pagamento;
import com.desafio.pagamentos.domain.enums.MetodoPagamento;

import java.math.BigDecimal;

public class PagamentoResponse {

    private Integer codigoDebito;

    private String cpfCnpj;

    private MetodoPagamento metodoPagamento;

    private String numeroCartao;

    private BigDecimal valor;

    private String statusPagamento;

    public PagamentoResponse() {
    }

    public PagamentoResponse(Pagamento pagamento) {
        cpfCnpj = pagamento.getCpfCnpj();
        codigoDebito = pagamento.getCodigoDebito();
        metodoPagamento = pagamento.getMetodoPagamento();
        numeroCartao = pagamento.getNumeroCartao();
        valor = pagamento.getValor();
        statusPagamento = pagamento.getStatus().getDescricao();

    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Integer getCodigoDebito() {
        return codigoDebito;
    }

    public void setCodigoDebito(Integer codigoDebito) {
        this.codigoDebito = codigoDebito;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
