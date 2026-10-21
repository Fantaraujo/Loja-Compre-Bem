package com.matheus.comprebem.dto;

import com.matheus.comprebem.Model.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO (

        UUID produtoId,
        String nome,
        String descricao,
        BigDecimal preco,
        String categoria,
        String imagem,
        Integer quantidade
) {
    public static ProdutoResponseDTO toDto(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getImagem(),
                produto.getQuantidade()
        );
    }
}

