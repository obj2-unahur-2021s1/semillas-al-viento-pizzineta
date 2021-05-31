package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ParcelasTest : DescribeSpec({

    describe("una planta"){
        val mentita = Menta(1998, 1.21f)
        val sojita = Soja(2000, 0.51f, false)
        val sojaReciente = Soja(2019, 1.71f, false)
        val sojaAntigua = Soja(2004, 1.55f, false)
        val sojitaTransgenica = Soja(2010, 1.32f, true)
        val parcelaUno = Parcela(6,6, 11)
        val parcelaDos = Parcela(10,5, 10)



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
            it("si es de menta"){
                mentita.esFuerte().shouldBe(false)
            }
            it("si es de soja" ){
                sojita.esFuerte().shouldBe(false)
            }
            it("si es de soja transgenica"){
                sojitaTransgenica.esFuerte().shouldBe(true)
            }
        }
        describe("saber si da semillas"){
            it("si mentita es fuerte o su altura es mayor a 0.4"){
                mentita.daSemillas().shouldBe(true)
            }
            it("Soja da semillas si la obtencion de semilla es mayor a 2007 y su altura es mayor a 1. No debe ser transgenica"){
                sojita.daSemillas().shouldBe(false)
                sojaAntigua.daSemillas().shouldBe(false)
                sojaReciente.daSemillas().shouldBe(true)
            }
            it("si la soja es transgenica no da semillas"){
                sojitaTransgenica.daSemillas().shouldBe(false)
            }
        }
        describe("superficie de una parcela"){
            it("ancho x largo"){
                parcelaUno.superficie().shouldBe(36)
            }
        }
        describe("cantidad maxima de plantas de una parcela"){
            it("el ancho y el largo son iguales"){
                parcelaUno.cantidadMaximaPlantas().shouldBe(18)
            }
            it("el ancho es mayor al largo"){
                parcelaDos.cantidadMaximaPlantas().shouldBe(10)
            }
        }


        describe("si tiene complicaciones"){
            parcelaDos.plantar(mentita)
            parcelaDos.plantar(sojita)
            parcelaUno.plantar(sojaAntigua)
            parcelaUno.plantar(sojaReciente)
            parcelaUno.plantar(sojitaTransgenica)
            it("parcelaUno"){
                parcelaUno.parcelaTieneComplicaciones().shouldBe(true)

            }
            it("parcelaDos"){
                parcelaDos.parcelaTieneComplicaciones().shouldBe(false)
            }

        }

        describe("cantidad de plantas en parcela"){
            it("en parcela uno"){
                parcelaUno.cantidadPlantas = 4
            }
            it ("en parcela dos"){
                parcelaDos.cantidadPlantas = 1
            }
        }
    }
})