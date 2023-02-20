package br.com.zoro.proxyapi.controller

import br.com.zoro.proxyapi.config.IBrasilApiClient
import br.com.zoro.proxyapi.dto.DddDto
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1")
class RestApi(
    private val iBrasilApiClient: IBrasilApiClient
) {
    val log = LoggerFactory.getLogger(this.javaClass)
    @GetMapping("/ddd/{ddd}")
    @Cacheable("ddd")
    fun getByDDD(@PathVariable ddd: Integer): ResponseEntity<DddDto> {
        log.info("Processando solicitação")
        val retorno = iBrasilApiClient.getCidadesByDDD(ddd)
        return ResponseEntity.ok(retorno)
    }
}