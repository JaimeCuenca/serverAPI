package es.centroafuera.practica

import org.springframework.data.jpa.repository.JpaRepository

interface RepositorioCervezas : JpaRepository<Cerveza, Long> //el tipo del PK