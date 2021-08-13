package br.com.zup.client

import com.fasterxml.jackson.annotation.JsonProperty

class EnderecoResponse (

    @JsonProperty("cep")
    val cep: String,
    @JsonProperty("bairro")
    val bairro: String,
    @JsonProperty("localidade")
    val cidade: String,
    @JsonProperty("uf")
    val estado: String,
    )
    {
        fun toEndereco(numero: String): Endereco {
            return Endereco(numero, cep, bairro, cidade, estado)
        }

    }


