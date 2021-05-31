package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, val altura: Float) {

  abstract fun horasDeSolQueTolera(): Int

  fun esFuerte() = this.horasDeSolQueTolera() > 10

  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

open class Soja(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  /* (DES)ACOPLAMIENTO, el error de esTransgenica genera la innecesaria creacion de una constante, arrastrando problemas a futuro*/
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {

      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return horasBase

  }

  override fun daSemillas(): Boolean  {
       return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}




class SojaTransgenica(anioObtencionSemilla: Int, altura: Float): Soja(anioObtencionSemilla, altura){
  override fun horasDeSolQueTolera(): Int {
        val horasBase = when {

      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return horasBase * 2
  }



  override fun daSemillas() = false

}