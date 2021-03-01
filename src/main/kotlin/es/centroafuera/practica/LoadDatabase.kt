package es.centroafuera.practica

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDatabase {
    companion object {
        val logger = LoggerFactory.getLogger(LoadDatabase.javaClass) //para sacar la info por la consola
    }

    @Bean
    fun initDatabase(repositorioCervezas: RepositorioCervezas) : CommandLineRunner{
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("Cargando "+repositorioCervezas.save(Cerveza(1, "BUZZ", "https://i.pinimg.com/originals/8b/76/68/8b766823d86cee158d84ae95e1e39ab7.jpg")))
            logger.info("Cargando "+repositorioCervezas.save(Cerveza(2, "LAGGER", "https://www.gourmets.net/img/Productos/569590.jpg")))
            logger.info("Cargando "+repositorioCervezas.save(Cerveza(3, "XX", "https://www.despensamexicana.es/large/Cerveza-XX-Lager-i698.jpg")))

        }
    }
}