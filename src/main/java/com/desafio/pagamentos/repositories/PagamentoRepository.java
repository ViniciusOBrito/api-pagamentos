package com.desafio.pagamentos.repositories;

import com.desafio.pagamentos.domain.entities.Pagamento;
import com.desafio.pagamentos.domain.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface PagamentoRepository extends JpaRepository<Pagamento,Integer> {

    @Query(
            "SELECT p FROM Pagamento p " +
            "WHERE (:codigoDebito IS NULL OR p.codigoDebito = :codigoDebito )" +
             "AND (:cpfCnpj = '' OR p.cpfCnpj = :cpfCnpj )" +
            "AND (:statusPagamento IS NULL OR p.status = :statusPagamento )"
    )
    List<Pagamento> buscarPorCodigoCpfStatus(Integer codigoDebito, String cpfCnpj, StatusPagamento statusPagamento);
}
