package io.github.wesleyosantos91.service;

import io.github.wesleyosantos91.enumeration.ErroEnum;
import io.github.wesleyosantos91.exception.core.ObjectNotFoundException;
import io.github.wesleyosantos91.model.Pessoa;
import io.swagger.model.RequestPostPessoa;
import io.swagger.model.RequestPutPessoa;
import io.swagger.model.ResponsePessoa;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.github.wesleyosantos91.model.Pessoa.*;

@ApplicationScoped
public class PessoaService {

    @Transactional
    public ResponsePessoa alterarPessoa(Long codigo, RequestPutPessoa body) {

        Optional<Pessoa> pessoaOptional = findByIdOptional(codigo);

        if (pessoaOptional.isEmpty()) {
            throw new ObjectNotFoundException(ErroEnum.PESSOA_COM_ESSE_CODIGO_NAO_EXISTE.getDetalhe() + codigo);
        }

        Pessoa pessoa = pessoaOptional.get();

        requestPutPessoatoEntity(body, pessoa);

        pessoa.persist();

        return pessoaToResponsePessoa(pessoa);
    }

    @Transactional
    public ResponsePessoa cadastrarPessoa(RequestPostPessoa body) {

        Pessoa pessoa = requestPostPessoatoEntity(body);
        pessoa.persist();

        return pessoaToResponsePessoa(pessoa);
    }

    public List<ResponsePessoa> consultarTodos() {

        return findAll().list().stream().map(p -> pessoaToResponsePessoa((Pessoa) p)).collect(Collectors.toList());
    }

    public ResponsePessoa consultarPeloCodigo(Long codigo) {

        if (!exist(codigo)) {
            throw new ObjectNotFoundException(ErroEnum.PESSOA_COM_ESSE_CODIGO_NAO_EXISTE.getDetalhe() + codigo);
        }

        Optional<Pessoa> pessoaOptional = findByIdOptional(codigo);

        return pessoaToResponsePessoa(pessoaOptional.orElseThrow(() -> new ObjectNotFoundException(ErroEnum.PESSOA_COM_ESSE_CODIGO_NAO_EXISTE.getDetalhe() + codigo)));

    }

    @Transactional
    public void excluirPessoa(Long codigo) {

        if (!exist(codigo)) {
            throw new ObjectNotFoundException(ErroEnum.PESSOA_COM_ESSE_CODIGO_NAO_EXISTE.getDetalhe() + codigo);
        }

        deleteById(codigo);
    }

    private boolean exist(Long codigo) {
        return findByIdOptional(codigo).isPresent();
    }

}
