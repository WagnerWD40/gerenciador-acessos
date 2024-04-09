package com.acme.gerenciadoracessos.controller;

import com.acme.gerenciadoracessos.service.UsuarioService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping(value = "/acesso")
public class AcessoController {

    private final MeterRegistry meterRegistry;

    private final UsuarioService usuarioService;

    public AcessoController(MeterRegistry meterRegistry, UsuarioService usuarioService) {
        this.meterRegistry = meterRegistry;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{usuarioId}/{portaId}")
    public ResponseEntity<String> getAcesso(
            @PathVariable("usuarioId")UUID usuarioId,
            @PathVariable("portaId")UUID portaId
    ) {

        Counter acessosAutorizados = Counter
                .builder("acessos_autorizados")
                .register(meterRegistry);

        Counter acessosNegados = Counter
                                    .builder("acessos_negados")
                                    .register(meterRegistry);

        if (usuarioService.checkAcesso(usuarioId, portaId)) {

            acessosAutorizados.increment();
            return ResponseEntity.ok().build();
        }

        acessosNegados.increment();
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);

//        Counter acessosNaoAutorizados = Counter.builder("quantidade de acessos")
//                .tag("acesso", "nao_autorizado")
//                .description("Quantidade de acessos não autorizados")
//                .register(meterRegistry);

    }

    @GetMapping("/adiciona/{usuarioId}/{portaId}")
    public ResponseEntity<String> adicionaAcesso(
        @PathVariable("usuarioId") UUID usuarioid,
        @PathVariable("portaId") UUID portaId
    ) {
        usuarioService.adicionarAcesso(usuarioid, portaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/remove/{usuarioId}/{portaId}")
    public ResponseEntity<String> removeAcesso(
            @PathVariable("usuarioId") UUID usuarioid,
            @PathVariable("portaId") UUID portaId
    ) {
        usuarioService.removerAcesso(usuarioid, portaId);
        return ResponseEntity.ok().build();
    }
}
