package io.github.wesleyosantos91.resource;

import io.github.wesleyosantos91.service.PessoaService;
import io.swagger.api.PessoasApi;
import io.swagger.model.RequestPostPessoa;
import io.swagger.model.RequestPutPessoa;
import io.swagger.model.ResponsePessoa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RequestScoped
public class PessoaResource implements PessoasApi {

    @Inject
    PessoaService pessoaService;

    @Override
    public ResponsePessoa alterarPessoa(Long codigo, @Valid RequestPutPessoa body) {
        return pessoaService.alterarPessoa(codigo, body);
    }

    @Override
    public ResponsePessoa cadastrarPessoa(@Valid RequestPostPessoa body) {
        return pessoaService.cadastrarPessoa(body);
    }

    @Override
    public ResponsePessoa consultarPeloCodigo(Long codigo) {
        return pessoaService.consultarPeloCodigo(codigo);
    }

    @Override
    public List<ResponsePessoa> consultarTodos() {
        return pessoaService.consultarTodos();
    }

    @Override
    public void excluirPessoa(Long codigo) {
        pessoaService.excluirPessoa(codigo);
    }
}
