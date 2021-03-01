package es.centroafuera.practica

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity //Para que el objeto creado se pueda almacenar en la bbdd
class Cerveza (var num: Int, var name : String, var foto : String){
    @Id
    @GeneratedValue //Para la clave primaria
    private val id:Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cerveza

        if (num != other.num) return false
        if (name != other.name) return false
        if (foto != other.foto) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = num
        result = 31 * result + name.hashCode()
        result = 31 * result + foto.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Cerveza(num=$num, name='$name', foto='$foto', id=$id)"
    }
}