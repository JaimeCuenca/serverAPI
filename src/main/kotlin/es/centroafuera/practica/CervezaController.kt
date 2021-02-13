package es.centroafuera.practica

import org.springframework.web.bind.annotation.*

@RestController
class CervezaController(private val repositorioCervezas: RepositorioCervezas) {

    @GetMapping("/cervezas")
    fun getAllCervezas():List<Cerveza>{
        return repositorioCervezas.findAll()
    }

    @PostMapping("/cerveza")
    fun insertCerveza(@RequestBody cerveza: Cerveza){
        repositorioCervezas.save(cerveza)
    }

    @GetMapping("/cerveza/{id}")
    fun getCerveza(@PathVariable id : Long) : Cerveza{
        return repositorioCervezas.findById(id).get()
    }

    @DeleteMapping("/cerveza/{id}")
    fun deleteCerveza(@PathVariable id : Long){
        repositorioCervezas.deleteById(id)
    }
}