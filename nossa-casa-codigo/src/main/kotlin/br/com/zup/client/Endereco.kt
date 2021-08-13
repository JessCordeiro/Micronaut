package br.com.zup.client

import io.micronaut.data.annotation.Embeddable

@Embeddable
class Endereco(
    val numero: String,
    val cep: String,
    val bairro: String,
    val cidade: String,
    val estado:String

)



