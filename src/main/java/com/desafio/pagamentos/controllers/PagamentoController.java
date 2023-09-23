package com.desafio.pagamentos.controllers;

import com.desafio.pagamentos.domain.dtos.AtualizarStatusPagamento;
import com.desafio.pagamentos.domain.dtos.PagamentoRequest;
import com.desafio.pagamentos.domain.dtos.PagamentoResponse;

import com.desafio.pagamentos.domain.enums.StatusPagamento;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.desafio.pagamentos.services.PagamentoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping
    @ApiOperation("Lista os pagamentos pelos filtros de Código Débito e/ou CPF/CNPJ e/ou Status do Pagament, caso nenhum filtro informado" +
            "lista todos os pagamentos")
    public ResponseEntity<List<PagamentoResponse>> buscarFiltros(
            @RequestParam(value = "codigoDebito",defaultValue = "") @ApiParam(value = "Código do Débito") Integer codigoDebito,
            @RequestParam(value = "cpfCnpj",defaultValue = "")  @ApiParam(value = "CPF/CNPJ") String cpfCnpj,
            @RequestParam(value = "statusPagamento",defaultValue = "") @ApiParam(value = "Status do pagamento") StatusPagamento statusPagamento
    ){
        List<PagamentoResponse> pagamentos = pagamentoService.buscarPorFiltros(codigoDebito,cpfCnpj,statusPagamento);
        return ResponseEntity.status(HttpStatus.OK).body(pagamentos);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> novoPagamento( @RequestBody @Valid PagamentoRequest request ){
        PagamentoResponse pagamentoResponse = pagamentoService.novoPagamento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoResponse);
    }

    @PutMapping("/{codigoDebito}")
    public  ResponseEntity<PagamentoResponse> atualizarStatusPagamento(
            @PathVariable @ApiParam(value = "Código do débito") Integer codigoDebito,
            @RequestBody @Valid AtualizarStatusPagamento novoStatus){
        PagamentoResponse pagamentoResponse = pagamentoService.atualizarStatus(codigoDebito,novoStatus);

        return ResponseEntity.status(HttpStatus.OK).body(pagamentoResponse);
    }

    @DeleteMapping("/{codigoDebito}")
    public void deletarPagamento( @PathVariable @ApiParam (value = "Código do Débito") Integer codigoDebito){
        pagamentoService.deletarPagamento(codigoDebito);
    }
}
