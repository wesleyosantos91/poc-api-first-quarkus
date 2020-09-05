package io.github.wesleyosantos91.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.swagger.model.RequestPostPessoa;
import io.swagger.model.RequestPutPessoa;
import io.swagger.model.ResponsePessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    public static Pessoa requestPostPessoatoEntity(RequestPostPessoa requestPostPessoa) {
        return builder()
                .nome(requestPostPessoa.getNome())
                .dataNascimento(requestPostPessoa.getDataNascimento())
                .cpf(requestPostPessoa.getCpf())
                .email(requestPostPessoa.getEmail())
                .build();
    }
    public static void requestPutPessoatoEntity(RequestPutPessoa requestPutPessoa, Pessoa pessoa) {
        pessoa.setNome(requestPutPessoa.getNome());
        pessoa.setEmail(requestPutPessoa.getEmail());
        pessoa.setDataNascimento(requestPutPessoa.getDataNascimento());
    }

    public static ResponsePessoa pessoaToResponsePessoa(Pessoa pessoa) {

        ResponsePessoa responsePessoa = new ResponsePessoa();
        responsePessoa.setCodigo(pessoa.getCodigo());
        responsePessoa.setNome(pessoa.getNome());
        responsePessoa.setCpf(pessoa.getCpf());
        responsePessoa.setDataNascimento(pessoa.getDataNascimento());
        responsePessoa.setEmail(pessoa.getEmail());

        return responsePessoa;
    }
}