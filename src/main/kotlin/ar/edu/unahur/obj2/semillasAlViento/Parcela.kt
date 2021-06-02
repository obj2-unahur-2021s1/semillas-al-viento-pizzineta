package ar.edu.unahur.obj2.semillasAlViento

import java.lang.Exception

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  fun cantidadPlantas() = plantas.size

  fun tieneComplicaciones() =
    plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }

  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() =

    if (ancho > largo)
      this.superficie() / 5
    else
      this.superficie() / 3 + largo

  fun plantar(planta: Planta) {
    when {
        this.cantidadPlantas() == this.cantidadMaximaPlantas() -> {
          throw Exception("Ya no hay lugar en esta parcela")
        }
        horasSolPorDia > planta.horasDeSolQueTolera() + 2 -> {

          throw Exception("No se puede plantar esto acá, se va a quemar")
        }
        else -> {
          plantas.add(planta)
        }
    }
  }
  fun esSemillera() =
    plantas.all {it.daSemillas()}
}

class Agricultora(val parcelas: List<Parcela>) {

  fun parcelasSemilleras() =
    parcelas.filter{it.esSemillera()}

  fun plantarEstrategicamente(planta: Planta) {
    this.parcelaConMasLugar().plantar(planta)
  }

  fun parcelaConMasLugar() =
    parcelas.maxBy{ it.cantidadMaximaPlantas() - it.cantidadPlantas()}!!
}
