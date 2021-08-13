package br.com.zup.client

import io.micronaut.data.annotation.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse,
    val numero: String,
    val cep: String
){
    val bairro = enderecoResponse.bairro
    val cidade = enderecoResponse.cidade
    val estado = enderecoResponse.estado
}






