package es.centroafuera.practica

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication //Para el escaneo de las cervezas
class PracticaApplication

fun main(args: Array<String>) {
	runApplication<PracticaApplication>(*args)
}
