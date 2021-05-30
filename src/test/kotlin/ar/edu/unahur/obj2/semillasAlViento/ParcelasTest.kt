package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ParcelasTest : DescribeSpec({

    describe("una planta"){
        val mentita = Menta(1998, 1.21f)
        val sojita = Soja(2000, 0.51f, false)
        val sojitaTransgenica = Soja(2010, 1.32f, true)
        describe("saber si cuantas horas de sol resiste"){
            it("si es de menta"){
                mentita.horasDeSolQueTolera().shouldBe(6)
            }
            it("si es de soja y mide entre 0.5 y 1 metro"){
                sojita.horasDeSolQueTolera().shouldBe(7)
            }
            it("si es de soja trasngenica y mide mas de 1 metro"){
                sojitaTransgenica.horasDeSolQueTolera().shouldBe(18)
            }
        }
        describe("saber si es fuerte"){
            it("si es de menta y mide mas de 0.4"){
                mentita.esFuerte().shouldBe(true)
            }
            it("si es de soja, y su obtencion es anterior a 2007 o su altura es menor a 0.4 metros" ){
                sojita.esFuerte().shouldBe(false)
            }
            it("si es de soja, su obtencion es posterior a 2007 y su altura mayor a 0.4"){
                sojitaTransgenica.esFuerte().shouldBe(true)
            }
        }
    }
})