package br.com.zoro.proxyapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableFeignClients
@EnableRetry
class ProxyapiApplication

fun main(args: Array<String>) {
	runApplication<ProxyapiApplication>(*args)
}
