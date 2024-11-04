package br.com.fiap.datatech.service;

import br.com.fiap.datatech.dto.EmpresaDTO;
import br.com.fiap.datatech.entity.Empresa;
import br.com.fiap.datatech.repository.EmpresaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException; // Importar a exceção
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.typeMap(EmpresaDTO.class, Empresa.class).addMappings(mapper -> {
            mapper.map(EmpresaDTO::getNomeEmpresa, Empresa::setNomeEmpresa);
            mapper.map(EmpresaDTO::getNomeFantasia, Empresa::setNomeFantasia);
            mapper.map(EmpresaDTO::getCnpj, Empresa::setCnpj);
        });
        modelMapper.typeMap(Empresa.class, EmpresaDTO.class).addMappings(mapper -> {
            mapper.map(Empresa::getNomeEmpresa, EmpresaDTO::setNomeEmpresa);
            mapper.map(Empresa::getNomeFantasia, EmpresaDTO::setNomeFantasia);
            mapper.map(Empresa::getCnpj, EmpresaDTO::setCnpj);
        });
    }

    public List<EmpresaDTO> listarTodasEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<EmpresaDTO> encontrarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).map(empresa -> modelMapper.map(empresa, EmpresaDTO.class));
    }

    public EmpresaDTO salvarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);
        return modelMapper.map(empresaRepository.save(empresa), EmpresaDTO.class);
    }

    public void deletarEmpresa(Long id) {
        // Verifica se a empresa existe antes de tentar excluir
        if (!empresaRepository.existsById(id)) {
            throw new EntityNotFoundException("Empresa não encontrada com id: " + id);
        }
        empresaRepository.deleteById(id);
    }

    public void atualizarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresaExistente = empresaRepository.findById(empresaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));


        empresaExistente.setNomeEmpresa(empresaDTO.getNomeEmpresa());
        empresaExistente.setCnpj(empresaDTO.getCnpj());

        empresaRepository.save(empresaExistente);
    }
}
