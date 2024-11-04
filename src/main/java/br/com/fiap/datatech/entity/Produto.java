package br.com.fiap.datatech.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TECH_PRODUTOS")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NM_PRODUTO_CONC", nullable = false)
    private String nomeProduto;

    @Column(name = "VL_PRODUTO", nullable = false)
    private Double valorProduto;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", nullable = false) // Define a chave estrangeira
    private Empresa empresa;

    public Produto() {}

    public Produto(Long id, String nomeProduto, Double valorProduto, Empresa empresa) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.empresa = empresa;
    }


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
