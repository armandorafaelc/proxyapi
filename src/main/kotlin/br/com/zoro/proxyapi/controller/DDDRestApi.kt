package br.com.zoro.proxyapi.controller

import br.com.zoro.proxyapi.config.IBrasilApiClient
import br.com.zoro.proxyapi.dto.DddDTO
import br.com.zoro.proxyapi.service.DddService
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.UnknownHostException

@RestController
@RequestMapping("v1/ddd")
class DDDRestApi(
    private val dddService: DddService
) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/{ddd}")
    @Cacheable("ddd")
    fun getByDDD(@PathVariable ddd: Integer): ResponseEntity<DddDTO> {
        log.info("Processando solicitação: $ddd")
        val retorno = dddService.getCidadesByDDD(ddd)
        return ResponseEntity.ok(retorno)
    }
}