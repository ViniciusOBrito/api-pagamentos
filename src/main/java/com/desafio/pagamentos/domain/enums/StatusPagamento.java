package com.desafio.pagamentos.domain.enums;

import com.desafio.pagamentos.exceptions.StatusInvalidoException;

public enum StatusPagamento {

    PENDENTE("Pendente") {
        @Override
        public void validarAtualizacaoStatus(StatusPagamento novoStatusPagamento) {
            return;
        }
    },
    PROCESSADO_SUCESSO("Processado com Sucesso") {
        @Override
        public void validarAtualizacaoStatus(StatusPagamento novoStatusPagamento) {
            throw new StatusInvalidoException("O status de um pagamento processado com sucesso não pode ser alterado.");
        }
    },
    PROCESSADO_FALHA("Processado com Falha") {
        @Override
        public void validarAtualizacaoStatus(StatusPagamento novoStatusPagamento) {
            if(!novoStatusPagamento.equals(StatusPagamento.PENDENTE))
                throw new StatusInvalidoException("O status de um pagamento processado com falha pode ser alterado apenas para pendente.");
        }
    };

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


    public abstract void validarAtualizacaoStatus(StatusPagamento status);
}
