package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {   //mutación controlada ya que la altura no se deberia poder cambiar
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
  /* (DES)ACOPLAMIENTO, el error de esTransgenica genera la innecesaria creacion de una constante, arrastrando problemas a futuro*/
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when { // simplicidad-KISS, variable innecesaria ya que el return de la linea 28 devolveria directamente
                           // las horas de sol toleradas segun la altura
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return if (esTransgenica) horasBase * 2 else horasBase
    //consecuencia de (des)acoplamiento ademas de falta de cohesión ya que resuelve 2 problemas al mismo tiempo
  }


  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) { //consecuencia de (des)acoplamiento del constructor de la linea 18
      return false
    }

    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}

