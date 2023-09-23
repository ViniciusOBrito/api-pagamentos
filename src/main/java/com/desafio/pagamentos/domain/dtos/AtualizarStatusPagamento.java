package com.desafio.pagamentos.domain.dtos;

import com.desafio.pagamentos.domain.enums.StatusPagamento;

import javax.validation.constraints.NotNull;

public class AtualizarStatusPagamento {
    @NotNull(message = "Campo status do pagamento é obrigatório.")
    private StatusPagamento status;

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
