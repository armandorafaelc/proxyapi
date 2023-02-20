package br.com.zoro.proxyapi.controller

import br.com.zoro.proxyapi.dto.BanksDTO
import br.com.zoro.proxyapi.exception.NenhumBancoEncontradoException
import br.com.zoro.proxyapi.service.BanksService
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects
import javax.print.attribute.IntegerSyntax

@RequestMapping("v1/bancos")
@RestController
class BanksRestApi(
    private val banksService: BanksService
) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    @Cacheable("banks")
    fun getListBanks(): ResponseEntity<List<BanksDTO>> {
        log.info("Processando solicitação de listagem de bancos")
        return ResponseEntity.ok(banksService.getListOfBanks())
    }

    @GetMapping("/ispb/{ispb}")
    @Cacheable("banco")
    fun findByIspnb(@PathVariable ispb: String): ResponseEntity<BanksDTO> {
        log.info("Processando banco por ispb: $ispb")
        val listBanks = banksService.getListOfBanks()
        val banco = listBanks.filter { i -> i.ispb == ispb }.firstOrNull()

        if (Objects.nonNull(banco))
            return ResponseEntity.ok(banco)

        throw NenhumBancoEncontradoException("Nenhum banco com ISPB $ispb encontrado")
    }

    @GetMapping("/codigo/{codigo}")
    @Cacheable("banco")
    fun findByCodigo(@PathVariable codigo: Integer): ResponseEntity<BanksDTO> {
        log.info("Processando banco por codigo: $codigo")
        val banco = banksService.getBancoPorCodigo(codigo)
        if (Objects.nonNull(banco))
            return ResponseEntity.ok(banco)
        throw NenhumBancoEncontradoException("Nenhum banco com código $codigo encontrado")
    }
}