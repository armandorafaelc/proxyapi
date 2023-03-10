package br.com.zoro.proxyapi.config

import br.com.zoro.proxyapi.dto.BanksDTO
import br.com.zoro.proxyapi.dto.DddDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "\${feign.name}", url = "\${feign.url}")
interface IBrasilApiClient{
    @RequestMapping(method = [RequestMethod.GET], value = ["/ddd/v1/{ddd}"], produces = ["application/json"])
    fun getCidadesByDDD(@PathVariable("ddd") ddd: Integer): DddDTO

    @RequestMapping(method = [RequestMethod.GET], value = ["/banks/v1"], produces = ["application/json"])
    fun getListaDeBancos(): List<BanksDTO>

    @RequestMapping(method = [RequestMethod.GET], value = ["/banks/v1/{code}"], produces = ["application/json"])
    fun getBancoPorCodigo(@PathVariable("code") codigo: Integer): BanksDTO
}