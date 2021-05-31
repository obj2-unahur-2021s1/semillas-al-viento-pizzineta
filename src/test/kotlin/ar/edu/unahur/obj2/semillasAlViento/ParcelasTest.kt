package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ParcelasTest : DescribeSpec({
    val mentita = Menta(1998, 1.21f)
    val sojita = Soja(2000, 0.51f)
    val sojaReciente = Soja(2019, 1.71f)
    val sojaAntigua = Soja(2004, 1.55f)
    val sojitaTransgenica = SojaTransgenica(2010, 1.32f)
    val parcelaUno = Parcela(6,6, 11)
    val parcelaDos = Parcela(10,5, 10)
    describe("una planta") {
        describe("saber si cuantas horas de sol resiste") {
            it("si es de menta") {
                mentita.horasDeSolQueTolera().shouldBe(6)
            }
            it("si es de soja y mide entre 0.5 y 1 metro") {
                sojita.horasDeSolQueTolera().shouldBe(7)
            }
            it("si es de soja trasngenica y mide mas de 1 metro") {
                sojitaTransgenica.horasDeSolQueTolera().shouldBe(18)
            }
        }
        describe("saber si es fuerte") {
            it("si es de menta") {
                mentita.esFuerte().shouldBe(false)
            }
            it("si es de soja") {
                sojita.esFuerte().shouldBe(false)
            }
            it("si es de soja transgenica") {
                sojitaTransgenica.esFuerte().shouldBe(true)
            }
        }
        describe("saber si da semillas") {
            it("si mentita es fuerte o su altura es mayor a 0.4") {
                mentita.daSemillas().shouldBe(true)
            }
            it("Soja da semillas si la obtencion de semilla es mayor a 2007 y su altura es mayor a 1. No debe ser transgenica") {
                sojaReciente.daSemillas().shouldBe(true)
            }
            it("si la soja es transgenica") {
                sojitaTransgenica.daSemillas().shouldBe(false)
            }
        }
    }
    describe("una parcela") {
        describe("superficie de una parcela") {
            it("ancho x largo") {
                parcelaUno.superficie().shouldBe(36)
            }
        }
        describe("cantidad maxima de plantas de una parcela") {
            it("el ancho y el largo son iguales") {
                parcelaUno.cantidadMaximaPlantas().shouldBe(18)
            }
            it("el ancho es mayor al largo") {
                parcelaDos.cantidadMaximaPlantas().shouldBe(10)
            }
        }

        describe("saber si se puede plantar") {
            it("si las horas de sol por dia de la parcela son mayores que las horas de sol que toleta + 2") {
                shouldThrowAny { parcelaUno.plantar(mentita) }
            }
            it("la parcela no esta llena y soporta las horas de sol que recibe la parcela") {
                parcelaUno.plantar(sojaReciente)
            }
        }
        describe("si tiene complicaciones") {
            parcelaUno.plantar(sojaAntigua)
            parcelaUno.plantar(sojaReciente)
            parcelaUno.plantar(sojitaTransgenica)
            it("tiene al menos una planta que tolera menos horas de sol que las que recibe la parcela") {
                parcelaUno.tieneComplicaciones().shouldBe(true)
            }
        }
    }
    describe("agricultoras"){
        val agricultora1 = Agricultora(mutableListOf(parcelaUno, parcelaDos))
        val sojaSemillera = Soja(2009, 1.8f)

        parcelaUno.plantar(sojaAntigua)
        parcelaUno.plantar(sojaReciente)
        parcelaUno.plantar(sojitaTransgenica)
        parcelaDos.plantar(sojaSemillera)

        describe("parcelas semilleras"){
            it("lista de parcelas semilleras "){
                agricultora1.parcelasSemilleras().shouldBe(listOf(parcelaDos))
            }
        }
    }
})
