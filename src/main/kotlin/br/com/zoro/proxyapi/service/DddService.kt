package br.com.zoro.proxyapi.service

import br.com.zoro.proxyapi.config.IBrasilApiClient
import br.com.zoro.proxyapi.dto.DddDTO
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import java.net.UnknownHostException

@Service
class DddService(
    private val iBrasilApiClient: IBrasilApiClient
)  {
    @Retryable(
        value = [UnknownHostException::class],
        maxAttemptsExpression = "\${retry.maxAttempts}",
        backoff = Backoff(delayExpression = "\${retry.maxDelay}")
    )
    fun getCidadesByDDD(ddd: Integer): DddDTO {
        return iBrasilApiClient.getCidadesByDDD(ddd)
    }
}