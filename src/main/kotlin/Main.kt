import java.util.Scanner
import java.util.Random

fun main() {
    val rm = ReproductorMidi("pugnodollari.mid")
    println("Cargando juego...")
    Thread.sleep(7500)

    juego()

    //Colores
    val green = "\u001B[32m"
    val red = "\u001B[31m"
    val reset = "\u001B[0m"

    do {
        print("¿Quieres jugar de nuevo? ("+green+"S"+reset+"/"+red+"N"+reset+"): ")
        val volver = readln()
        if (volver.lowercase() == "s"){
            println()
            juego()
        } else {
            rm.cerrar()
        }
    } while (volver.lowercase() == "s")
}

fun juego() {

    //Colores
    val green = "\u001B[32m"
    val red = "\u001B[31m"
    val blue = "\u001B[34m"
    val yellow = "\u001B[33m"
    val reset = "\u001B[0m"

    var palabras = arrayOf("")
    var modoJuego: Int

    do {
        println("$yellow-----------------------------------------------$reset")
        println("$yellow|             $red Juego del ahorcado$yellow             |")
        println("|                                             |")
        println("|        $blue Elige un modo de juego:$yellow             |")
        println("|             $blue 1.$green Frutas$yellow                      |")
        println("|             $blue 2.$green Marcas de ordenadores$yellow       |")
        println("|             $blue 3.$green Marcas de coche$yellow             |")
        println("|                                             |")
        println("-----------------------------------------------$reset")
        modoJuego = readlnOrNull()?.toIntOrNull() ?: 0
    }while (modoJuego !in 1..3)

    when(modoJuego){
        1 -> {
            palabras = arrayOf("manzana", "pera", "melocoton", "fresa", "naranja", "mandarina", "aguacate")
        }
        2 -> {
            palabras = arrayOf("hp", "lenovo", "dell", "alienware", "apple", "acer", "asus")
        }
        3 -> {
            palabras = arrayOf("peugeot", "citroen", "ford", "pagani", "saab", "seat", "fiat")
        }
    }

    val palabra = palabras[Random().nextInt(palabras.size)]
    val palabraOculta = CharArray(palabra.length) { '_' }
    var intentos = 0
    var letra: Char
    var acierto: Boolean
    val entrada = Scanner(System.`in`)

    while (intentos < 7 && palabraOculta.contains('_')) {
        println()
        println("Palabra: ${palabraOculta.joinToString(" ")}")
        print("Introduce una letra: ")
        letra = entrada.nextLine()[0]
        acierto = false
        for (i in palabra.indices) {
            if (palabra[i] == letra) {
                palabraOculta[i] = letra
                acierto = true
            }
        }
        if (!acierto) {
            intentos++
            println(red+"Fallaste."+reset+" Te quedan"+blue+" ${7 - intentos}"+reset+" intentos.")
            DibujoAhorcado.dibujar(intentos)
        }
    }

    if (intentos == 7) {
        println(red+"Perdiste."+reset+" La palabra era "+blue+"$palabra.\n"+reset)
    } else {
        println(green+"Ganaste."+reset+" La palabra era "+blue+"$palabra.\n"+reset)
    }
    return
}