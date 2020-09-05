package io.github.wesleyosantos91.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErroEnum {

    PESSOA_COM_ESSE_CODIGO_NAO_EXISTE("Pessoa com esse codigo não existe: ");

    private String detalhe;
}
