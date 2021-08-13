package br.com.zup.client

import com.fasterxml.jackson.annotation.JsonProperty

data class EnderecoResponse (


    val bairro: String,
    @JsonProperty("localidade")
    val cidade: String,
    @JsonProperty("uf")
    val estado: String,
    )



