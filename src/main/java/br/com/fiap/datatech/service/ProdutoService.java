package br.com.fiap.datatech.service;

import br.com.fiap.datatech.dto.ProdutoDTO;
import br.com.fiap.datatech.entity.Produto;
import br.com.fiap.datatech.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProdutoDTO> listarTodosProdutos() {
        return produtoRepository.findAll().stream()
                .map(produto -> modelMapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> encontrarProdutoPorId(Long id) {
        return produtoRepository.findById(id).map(produto -> modelMapper.map(produto, ProdutoDTO.class));
    }

    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        return modelMapper.map(produtoRepository.save(produto), ProdutoDTO.class);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
