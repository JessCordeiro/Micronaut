package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid
import io.micronaut.http.annotation.Controller

@Validated
@Controller( value="/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        println("Requisição => ${request}")
        val autor = request.paraAutor()
        println("Autor => ${autor.nome}")

        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))// para parametro id atribui valor de autor id
        return HttpResponse.created(uri)
    }

    @Get("/{id}")
    @Transactional
    fun Lista(): HttpResponse<List<DetalhesDoAutorResponse>> {
        val autores = autorRepository.findAll()
        val resposta = autores.map { autor -> DetalhesDoAutorResponse(autor) }
        return HttpResponse.ok(resposta)
    }

    @Get
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()) {
            val autores = autorRepository.findAll()
            val resposta = autores.map { autor -> DetalhesDoAutorResponse(autor) }
            return HttpResponse.ok()
        }
        val possivelAutor = autorRepository.findByEmail(email)
        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
            val autor = possivelAutor.get()
        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }



    @Put("{/id}")
    @Transactional
    fun atualiza(@PathVariable id:Long, descricao:String) : HttpResponse<Any>{
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        autor.descricao = descricao
        autorRepository.update(autor)

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }

    @Delete("/{id}")
    @Transactional
    fun deleta(@PathVariable id:Long) : HttpResponse<Any>{
        val possivelAutor = autorRepository.findById(id)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autorRepository.delete(autor)
        return HttpResponse.ok()
    }

}