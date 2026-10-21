package com.matheus.comprebem.Repository;

import com.matheus.comprebem.Model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Page<Produto> findByNomeContainingIgnoreCase(
            String nome,
            Pageable pageable
    );
}
