package com.desafio.pagamentos.services;

import com.desafio.pagamentos.domain.dtos.AtualizarStatusPagamento;
import com.desafio.pagamentos.domain.dtos.PagamentoRequest;
import com.desafio.pagamentos.domain.dtos.PagamentoResponse;
import com.desafio.pagamentos.domain.entities.Pagamento;
import com.desafio.pagamentos.domain.enums.StatusPagamento;
import com.desafio.pagamentos.exceptions.NaoEncontradoException;
import com.desafio.pagamentos.exceptions.NumeroCartaoException;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.desafio.pagamentos.repositories.PagamentoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Transactional
    public PagamentoResponse novoPagamento(PagamentoRequest pagamentoRequest) {
        if (pagamentoRequest.deveInformarNumeroDeCartao()) {
            throw new NumeroCartaoException("O número do cartão deve ser informado");
        }

        Pagamento pagamento = pagamentoRepository.save(pagamentoRequest.newPagamento());
        return new PagamentoResponse(pagamento);
    }

    @Transactional
    public PagamentoResponse atualizarStatus(Integer codigoDebito, AtualizarStatusPagamento novoStatus) {
        Pagamento pagamento = pagamentoRepository.findById(codigoDebito).orElseThrow(() -> new NaoEncontradoException("Pagamento não encontrado!"));

        pagamento.getStatus().validarAtualizacaoStatus(novoStatus.getStatus());
        pagamento.setStatus(novoStatus.getStatus());

        pagamento = pagamentoRepository.save(pagamento);

        return new PagamentoResponse(pagamento);
    }

    @Transactional
    public void deletarPagamento(Integer codigoDebito) {
        Pagamento pagamento = pagamentoRepository.findById(codigoDebito).orElseThrow(() -> new NaoEncontradoException("Pagamento não encontrado!"));

        pagamento.validarExclusao();
        pagamentoRepository.delete(pagamento);
    }

    @Transactional(readOnly = true)
    public List<PagamentoResponse> buscarPorFiltros(Integer codigoDebito, String cpfCnpj, StatusPagamento statusPagamento) {
        return pagamentoRepository.buscarPorCodigoCpfStatus(codigoDebito, cpfCnpj, statusPagamento).stream().map(PagamentoResponse::new).collect(Collectors.toList());
    }

}
