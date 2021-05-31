package ar.edu.unahur.obj2.semillasAlViento

import java.lang.Exception

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0 // (des)acoplamiento EN VEZ DE VARIABLE DEBE SER UNA FUNCION SIZE DE LA LISTA PLANTAS

  fun tieneComplicaciones() =
    plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    /*redundancia mínima, utiliza la misma fórmula que emplea el método superficie y no lo reutiliza*/
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  fun plantar(planta: Planta) { //fallas de cohesión ya que realiza mas de una acción al mismo tiempo
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      throw Exception("Ya no hay lugar en esta parcela") //robustez, debería devolver exception
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) { //Simplicidad-KISS
                                                        //debería ser un metodo aparte el saber si tiene complicaciones
      throw Exception("No se puede plantar esto acá, se va a quemar") //robustez, debería devolver exception
    } else {
      plantas.add(planta)
      cantidadPlantas += 1 //consecuencia del (des)acoplamiento en la linea 5
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  /*Fallo en Simplicidad-YAGNI
  * La consigna nos indica que la tierra no puede ser comprada ni vendida, programaron "por las dudas"*/
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    /*Fallo en Simplicidad-YAGNI
  * La consigna nos indica que la tierra no puede ser comprada ni vendida, programaron "por las dudas"*/
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    /*Fallos en mutaciones controladas
 * El método realiza 2 recorridos en 2 listas distintas y genera mutaciones
 * Debería aplicarse abstraccion para mayor legibilidad del código*/
    parcelas.filter {
      parcela -> parcela.plantas.all {
      planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    /*cohesión ya que realiza 2 acciones al mismo tiempo*/
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!! /*simplicidad-KISS deberia hacer un metodo
    aparte que devuelva la parcela con mayor espacio disponible*/
    laElegida.plantas.add(planta) /*Redundancia mínima, repite el método plantar y no lo reutiliza*/


  }

}
