package br.com.zoro.proxyapi.service

import br.com.zoro.proxyapi.config.IBrasilApiClient
import br.com.zoro.proxyapi.dto.BanksDTO
import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import java.net.UnknownHostException

@Service
class BanksService(
    private val iBrasilApiClient: IBrasilApiClient
)  {
    val log = LoggerFactory.getLogger(this.javaClass)
    @Retryable(
        value = [UnknownHostException::class],
        maxAttemptsExpression = "\${retry.maxAttempts}",
        backoff = Backoff(delayExpression = "\${retry.maxDelay}")
    )
    fun getListOfBanks(): List<BanksDTO> {
        log.info("Recuperando lista de bancos")
        return iBrasilApiClient.getListaDeBancos()
    }

    @Retryable(
        value = [UnknownHostException::class],
        maxAttemptsExpression = "\${retry.maxAttempts}",
        backoff = Backoff(delayExpression = "\${retry.maxDelay}")
    )
    fun getBancoPorCodigo(codigo: Integer): BanksDTO {
        log.info("Buscando banco por código: $codigo")
        return iBrasilApiClient.getBancoPorCodigo(codigo)
    }
}