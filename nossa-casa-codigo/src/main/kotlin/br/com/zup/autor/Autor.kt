package br.com.zup.autor

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity // por padrão introspected
class Autor (
    val nome:String,
    val email:String,
    var descricao:String
        ){
    @Id
    @GeneratedValue
    var id : Long? = null
            val criadoEm: LocalDateTime = LocalDateTime.now()

}
