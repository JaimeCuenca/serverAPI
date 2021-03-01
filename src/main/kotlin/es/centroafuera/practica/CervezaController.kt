package es.centroafuera.practica

import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@RestController //Para que los datos que devuelve cada metodo se escriban por pantalla
class CervezaController(private val repositorioCervezas: RepositorioCervezas) {

    @GetMapping("/cervezas") //El value es: curl -v localhost: 8081[...]
    fun getAllCervezas():List<Cerveza>{
        return repositorioCervezas.findAll()
    }

    @PostMapping("/cerveza")
    fun insertCerveza(@RequestBody cerveza: Cerveza){
        repositorioCervezas.save(cerveza)
    }

    @GetMapping("/cerveza/{id}")
    fun getCerveza(@PathVariable id : Long) : Cerveza{
        val encriptator = id.toString()
        val key = "contrasenia"
        val descifrator = descifrar(cifrar(encriptator, key), key)
        return repositorioCervezas.findById(descifrator.toLong()).get()
    }

    @DeleteMapping("/cerveza/{id}")
    fun deleteCerveza(@PathVariable id : Long){
        val encriptator = id.toString()
        val key = "contrasenia"
        val descifrator = descifrar(cifrar(encriptator, key), key)
        repositorioCervezas.deleteById(descifrator.toLong())
    }
}

private fun cifrar(stringText : String, stringKey : String) : String {
    println("Cifrando: $stringText")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, getKey(stringKey))
    val textCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(stringText.toByteArray(Charsets.UTF_8)))
    println("Texto cifrado: $textCifrado")
    return textCifrado
}

@Throws(BadPaddingException::class)
private fun descifrar(cifText : String, stringKey : String) : String {
    println("Descifrando: $cifText")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, getKey(stringKey));
    val textDescifrado = String(cipher.doFinal(Base64.getDecoder().decode(cifText)))
    println("Texto descifrado: $textDescifrado")
    return textDescifrado
}


private fun getKey(stringKey : String): SecretKeySpec {
    var llaveUtf8 = stringKey.toByteArray(Charsets.UTF_8)
    val sha = MessageDigest.getInstance("SHA-1")
    llaveUtf8 = sha.digest(llaveUtf8)
    llaveUtf8 = llaveUtf8.copyOf(16)
    return SecretKeySpec(llaveUtf8, "AES")
}