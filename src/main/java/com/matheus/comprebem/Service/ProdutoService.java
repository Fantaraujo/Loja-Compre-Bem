package com.matheus.comprebem.Service;

import com.matheus.comprebem.Model.Produto;
import com.matheus.comprebem.Repository.ProdutoRepository;
import com.matheus.comprebem.dto.ProdutoRequestDTO;
import com.matheus.comprebem.dto.ProdutoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;


    public Page<ProdutoResponseDTO> findAll(
            int page, int size, String busca) {

        int tamanho = Math.min(size, 50);
        Pageable pageable = PageRequest.of(page, tamanho);

        Page<Produto> produtos;

        if (busca != null && !busca.isBlank()) {
            produtos = produtoRepository
                    .findByNomeContainingIgnoreCase(busca.trim(), pageable);
        } else {
            produtos = produtoRepository.findAll(pageable);
        }

        return produtos.map(ProdutoResponseDTO::toDto);
    }

    public ProdutoResponseDTO findById(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        return ProdutoResponseDTO.toDto(produto);
    }

    public ProdutoResponseDTO save(ProdutoRequestDTO dto) {
        Produto produto = Produto.toEntity(dto);
        Produto salvo = produtoRepository.save(produto);

        return ProdutoResponseDTO.toDto(salvo);
    }

    public ProdutoResponseDTO update(
            UUID produtoId, ProdutoRequestDTO dto) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setImagem(dto.imagem());
        produto.setQuantidade(dto.quantidade());

        return ProdutoResponseDTO.toDto(
                produtoRepository.save(produto)
        );
    }

    public void delete(UUID produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        produtoRepository.delete(produto);
    }
}


