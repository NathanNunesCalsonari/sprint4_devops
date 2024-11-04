package br.com.fiap.datatech.controller;

import br.com.fiap.datatech.dto.EmpresaDTO;
import br.com.fiap.datatech.dto.ProdutoDTO;
import br.com.fiap.datatech.service.EmpresaService;
import br.com.fiap.datatech.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProdutoService produtoService; // Injeta o ProdutoService

    @GetMapping("/listar")
    public String listarEmpresas(Model model) {
        List<EmpresaDTO> empresas = empresaService.listarTodasEmpresas();
        model.addAttribute("empresas", empresas);
        return "empresas/listar";
    }

    @GetMapping("/{id}")
    public String obterEmpresaPorId(@PathVariable Long id, Model model) {
        return empresaService.encontrarEmpresaPorId(id)
                .map(empresa -> {
                    model.addAttribute("empresa", empresa);
                    return "empresas/detalhes";
                })
                .orElse("error/404");
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("empresaDTO", new EmpresaDTO());
        return "empresas/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarEmpresa(@ModelAttribute EmpresaDTO empresaDTO) {
        empresaService.salvarEmpresa(empresaDTO);
        return "redirect:/empresas/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        return empresaService.encontrarEmpresaPorId(id)
                .map(empresa -> {
                    model.addAttribute("empresaDTO", empresa);
                    return "empresas/editar";
                })
                .orElse("error/404");
    }

    @PostMapping("/editar/{id}")
    public String atualizarEmpresa(@PathVariable Long id, @ModelAttribute EmpresaDTO empresaDTO) {
        empresaDTO.setId(id); // Define o ID para a atualização
        empresaService.atualizarEmpresa(empresaDTO); // Use o método de atualização
        return "redirect:/empresas/listar";
    }

    // Novo método GET para confirmar a deleção
    @GetMapping("/deletar/{id}")
    public String confirmarDelecao(@PathVariable Long id, Model model) {
        return empresaService.encontrarEmpresaPorId(id)
                .map(empresa -> {
                    model.addAttribute("empresa", empresa);
                    return "empresas/deletar"; // Página de confirmação de deleção
                })
                .orElse("error/404");
    }

    // Método POST para deletar a empresa
    @PostMapping("/deletar/{id}")
    public String deletarEmpresa(@PathVariable Long id, Model model) {
        try {
            empresaService.deletarEmpresa(id);
            return "redirect:/empresas/listar";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Empresa não encontrada");
            return "error/404"; // Redirecionar para uma página de erro se a empresa não for encontrada
        }
    }

    @GetMapping("/{id}/produtos")
    public String listarProdutosDaEmpresa(@PathVariable Long id, Model model) {
        List<ProdutoDTO> produtos = produtoService.listarTodosProdutos(); // Aqui você pode adicionar a lógica para filtrar os produtos relacionados à empresa
        model.addAttribute("produtos", produtos);
        return "empresas/produtos"; // Página que lista os produtos da empresa
    }

    @GetMapping("/{id}/produtos/cadastrar")
    public String mostrarFormularioCadastroProduto(@PathVariable Long id, Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("empresaId", id); // Para associar o produto à empresa
        return "empresas/cadastrar-produto"; // Página para cadastrar novo produto
    }

    @PostMapping("/{id}/produtos/cadastrar")
    public String cadastrarProduto(@PathVariable Long id, @ModelAttribute ProdutoDTO produtoDTO) {
        produtoService.salvarProduto(produtoDTO); // Salva o produto
        return "redirect:/empresas/{id}/produtos";
    }

    @GetMapping("/editar/produto/{produtoId}")
    public String mostrarFormularioEdicaoProduto(@PathVariable Long produtoId, Model model) {
        ProdutoDTO produto = produtoService.encontrarProdutoPorId(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        model.addAttribute("produtoDTO", produto);
        return "empresas/editar-produto"; // Página para editar produto
    }

    @PostMapping("/editar/produto/{produtoId}")
    public String atualizarProduto(@PathVariable Long produtoId, @ModelAttribute ProdutoDTO produtoDTO) {
        produtoDTO.setId(produtoId); // Atualiza o ID do produto
        produtoService.salvarProduto(produtoDTO); // Atualiza o produto
        return "redirect:/empresas/{id}/produtos"; // Redireciona para a lista de produtos da empresa
    }
}
