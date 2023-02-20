package br.com.zoro.proxyapi.dto

data class ErrorMessageModel(
    var errorCode: Int? = null,
    var message: String? = null
)