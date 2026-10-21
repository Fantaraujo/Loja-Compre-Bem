package com.matheus.comprebem.Controller;

import com.matheus.comprebem.Model.Produto;
import com.matheus.comprebem.Service.ProdutoService;
import com.matheus.comprebem.dto.ProdutoRequestDTO;
import com.matheus.comprebem.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {


    private final ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String busca) {

        if (page < 0) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "A página não pode ser negativa"
            );
        }

        if (size < 1) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "O tamanho deve ser maior que zero"
            );
        }

        size = Math.min(size, 50);

        return produtoService.findAll(page, size, busca);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO findById(@PathVariable UUID id) {
        return produtoService.findById(id);
    }

    @PostMapping
    public ProdutoResponseDTO save(
            @Valid @RequestBody ProdutoRequestDTO produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoResponseDTO update(
            @PathVariable UUID produtoId,
            @Valid @RequestBody ProdutoRequestDTO produto) {
        return produtoService.update(produtoId, produto);
    }

    @DeleteMapping("/{produtoId}")
    public void delete(@PathVariable UUID produtoId) {
        produtoService.delete(produtoId);
    }
}
