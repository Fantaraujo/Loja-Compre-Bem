package com.matheus.comprebem.Teste;

import com.matheus.comprebem.Model.Produto;
import com.matheus.comprebem.Repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CargaInicial {

    @Bean
    CommandLineRunner carregarProdutos(ProdutoRepository repository){
        return args -> {
            if(repository.count() > 0){
                return;
            }

            List<Produto> produtos = new ArrayList<>();

            String[] nomes = {

                    "Cadeira de escritório",
                    "Cadeira gamer",
                    "Cadeira de madeira",
                    "Cadeira dobrável",
                    "Cadeira de jantar",
                    "Mesa de escritório",
                    "Mesa de jantar",
                    "Sofá de 2 lugares",
                    "Estante para livros",
                    "Guarda-roupa",
                    "Arroz 5kg",
                    "Feijão 1kg",
                    "Macarrão 500g",
                    "Açúcar 1kg",
                    "Café 500g",
                    "Leite 1L",
                    "Óleo de soja",
                    "Farinha de trigo",
                    "Sal refinado",
                    "Biscoito recheado"
            };

            for (int i=0; i<60; i++){
                Produto produto = new Produto();

                String nome = nomes[i % nomes.length];

                produto.setNome(nome + " " + (i+1));
                produto.setDescricao("Produto da Loja Compre Bem");
                produto.setCategoria(i<10 ? "Móveis" : "Mercearia");
                produto.setPreco(BigDecimal.valueOf(10L + i + 2L));
                produto.setQuantidade(10 + i);
                produto.setImagem(null);

                produtos.add(produto);
            }

            repository.saveAll(produtos);
        };

    }

}
