package br.com.fiap.datatech.controller;

import br.com.fiap.datatech.dto.ProdutoDTO;
import br.com.fiap.datatech.service.EmpresaService;
import br.com.fiap.datatech.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        List<ProdutoDTO> produtos = produtoService.listarTodosProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos/listar";
    }

    @GetMapping("/{id}")
    public String obterProdutoPorId(@PathVariable Long id, Model model) {
        Optional<ProdutoDTO> produto = produtoService.encontrarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            model.addAttribute("empresa", empresaService.encontrarEmpresaPorId(produto.get().getEmpresaId()).orElse(null));
            return "produtos/detalhes";
        } else {
            return "error/404"; // Você pode criar um método separado para redirecionar para a página de erro
        }
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("titulo", "Cadastrar Produto");
        model.addAttribute("empresas", empresaService.listarTodasEmpresas());
        return "produtos/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String adicionarProduto(@ModelAttribute @Valid ProdutoDTO produtoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("empresas", empresaService.listarTodasEmpresas());
            return "produtos/cadastrar";
        }
        produtoService.salvarProduto(produtoDTO);
        model.addAttribute("successMessage", "Produto cadastrado com sucesso!");
        return "redirect:/produtos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Optional<ProdutoDTO> produto = produtoService.encontrarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produtoDTO", produto.get());
            model.addAttribute("titulo", "Editar Produto");
            model.addAttribute("empresas", empresaService.listarTodasEmpresas());
            return "produtos/editar";
        } else {
            return "error/404"; // Melhor retornar uma página de erro ao invés de uma string
        }
    }

    @PostMapping("/editar/{id}")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute @Valid ProdutoDTO produtoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            produtoDTO.setId(id);
            model.addAttribute("empresas", empresaService.listarTodasEmpresas());
            return "produtos/editar";
        }
        produtoDTO.setId(id);
        produtoService.salvarProduto(produtoDTO);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/deletar/{id}")
    public String confirmarDelecao(@PathVariable Long id, Model model) {
        Optional<ProdutoDTO> produto = produtoService.encontrarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/deletar";
        } else {
            return "error/404"; // Melhor retornar uma página de erro
        }
    }

    @PostMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return "redirect:/produtos/listar";
    }
}
