package com.acme.gerenciadoracessos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UsuarioJaPossuiAcessoException extends RuntimeException {
    public UsuarioJaPossuiAcessoException() {
        super("Usuário já possui permissão para o acesso.");
    }
}
