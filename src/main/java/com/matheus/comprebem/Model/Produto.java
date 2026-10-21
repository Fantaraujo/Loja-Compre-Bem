package com.matheus.comprebem.Model;

import com.matheus.comprebem.dto.ProdutoRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(length = 100)
    private String categoria;

    @Column(length = 500)
    private String descricao;

    private String imagem;

    @Column(nullable = false)
    private Integer quantidade;

    public static Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setDescricao(dto.descricao());
        produto.setImagem(dto.imagem());
        produto.setQuantidade(dto.quantidade());

        return produto;
    }
}