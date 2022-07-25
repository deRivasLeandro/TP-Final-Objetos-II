package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.assertThrows
class ObservatorioTest: DescribeSpec ({

    val argentina: Pais = Pais("Argentina", "ARG", 47327407, 2780400.0,"América", "ARS", 136.0, mutableSetOf("Mercosur"), mutableSetOf("Español"))
    val brasil = Pais("Brasil", "BRA", 217240060, 	8515770.0, "América", "BRL", 5.4, mutableSetOf("Mercosur"), mutableSetOf("Portugués"))
    val uruguay = Pais("Uruguay", "URY", 3554915, 	176215.0, "América", "UYU", 41.4, mutableSetOf("Mercosur"), mutableSetOf("Español"))
    val canada = Pais("Canadá", "CAN", 38246108, 9984670.0, "América", "CAD", 1.29, mutableSetOf("Otán", "G7"), mutableSetOf("Inglés", "Francés"))
    val nuevaZelanda = Pais("Nueva Zelanda", "NZL", 5006020, 268838.0, "Oceanía", "NZD", 1.59, mutableSetOf(), mutableSetOf("Inglés", "Maorí"))
    val eeuu = Pais("Estados Unidos", "USA", 331449281, 9147593.0, "América", "USD", 1.0, mutableSetOf("Otán", "G7"), mutableSetOf("Inglés", "Español"))
    val madagascar = Pais("Madagascar", "MAD", 26251309, 587041.0, "África", "MGA", 4161.62, mutableSetOf("UA"), mutableSetOf("Francés", "Malgache"))
    val china = Pais("China", "CHN", 1403500365, 9596960.0, "Asia", "CNY", 6.75, mutableSetOf(), mutableSetOf("Chino"))
    val india = Pais("India", "IND", 1372065957, 3287263.0, "Asia", "INR", 79.72, mutableSetOf(), mutableSetOf("Hindi", "Inglés"))
    val italia = Pais("Italia", "ITA", 60257566, 301340.0, "Europa", "EUR", 0.97, mutableSetOf("UEA", "Otán", "G7"), mutableSetOf("Italiano"))
    val francia = Pais("Francia", "FRA", 67407241, 675417.0, "Europa", "EUR", 0.97, mutableSetOf("UEA"), mutableSetOf("Francés"))

    argentina.agregarPaisesLimitrofes(mutableSetOf(brasil, uruguay))
    uruguay.agregarPaisesLimitrofes(mutableSetOf(brasil, argentina))
    brasil.agregarPaisesLimitrofes(mutableSetOf(argentina, uruguay))
    canada.agregarPaisesLimitrofes(mutableSetOf(eeuu))
    eeuu.agregarPaisesLimitrofes(mutableSetOf(canada))
    china.agregarPaisesLimitrofes(mutableSetOf(india))
    india.agregarPaisesLimitrofes(mutableSetOf(china))
    italia.agregarPaisesLimitrofes(mutableSetOf(francia))
    francia.agregarPaisesLimitrofes(mutableSetOf(italia))
    Observatorio.paises = mutableSetOf(argentina, uruguay, brasil, canada, eeuu, nuevaZelanda, madagascar, china, india, italia, francia)

    describe("Tests unitarios para el observatorio con dos países") {
        it("Consultar al observatorio si Uruguay es limítrofe de Argentina") {
            Observatorio.sonLimitrofes("Argentina", "Uruguay").shouldBeTrue()
        }

        it("Consultar al observatorio si Uruguay no es limítrofe de Canadá") {
            Observatorio.sonLimitrofes("Uruguay", "Canadá").shouldBeFalse()
        }

        it("Consultar al observatorio si Uruguay es limítrofe de Chad, pero Chad no está registrado") {
            shouldThrowAny { (Observatorio.sonLimitrofes("Uruguay", "Chad")) }
        }

        it("Consultar al observatorio si Panamá es limítrofe de Argentina, pero Panamá no está registrado") {
            shouldThrowAny { (Observatorio.sonLimitrofes("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si Panamá es limítrofe de Chad, pero ninguno de los dos están registrados") {
            shouldThrowAny { (Observatorio.sonLimitrofes("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Argentina es limítrofe de Argentina debe devolver un error") {
            shouldThrowAny { Observatorio.sonLimitrofes("Argentina", "Argentina") }
        }

        it("Consultar al observatorio si Argentina necesita traductor con Canadá") {
            Observatorio.necesitanTraductor("Argentina", "Canadá").shouldBeTrue()
        }

        it("Consultar al observatorio si Argentina necesita traductor con Uruguay") {
            Observatorio.necesitanTraductor("Argentina", "Uruguay").shouldBeFalse()
        }

        it("Consultar al observatorio si Argentina necesita traductor con Chad, pero Chad no está registrado") {
            shouldThrowAny { (Observatorio.necesitanTraductor("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si Panamá necesita traductor con Argentina, pero Panamá no está registrado") {
            shouldThrowAny { (Observatorio.necesitanTraductor("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si Panamá necesita traductor con Chad, pero ninguno de los dos están registrados") {
            shouldThrowAny { (Observatorio.necesitanTraductor("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Argentina es potencial aliado de Uruguay") {
            Observatorio.sonPotencialesAliados("Argentina", "Uruguay").shouldBeTrue()
        }

        it("Consultar al observatorio si Canadá es potencial aliado de Brasil") {
            Observatorio.sonPotencialesAliados("Canadá", "Brasil").shouldBeFalse()
        }

        it("Consultar al observatorio si Argentina es potencial aliado de Chad, pero Chad no está registrado") {
            shouldThrowAny { (Observatorio.sonPotencialesAliados("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si Panamá es potencial aliado de Argentina, pero Panamá no está registrado") {
            shouldThrowAny { (Observatorio.sonPotencialesAliados("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si Panamá es potencial aliado de Chad, pero ninguno de los dos están registrados") {
            shouldThrowAny { (Observatorio.sonPotencialesAliados("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Conviene ir de Uruguay a Argentina a comprar") {
            Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Uruguay", "Argentina").shouldBeTrue()
        }

        it("Consultar al observatorio si conviene ir de Argentina a Brasil a comprar") {
            Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Brasil").shouldBeFalse()
        }

        it("Consultar al observatorio si conviene ir de Argentina a Chad a comprar, pero Chad no está registrado") {
            shouldThrowAny { (Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si conviene ir de Panamá a Argentina a comprar, pero Panamá no está registrado") {
            shouldThrowAny { (Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si conviene ir de Panamá a Chad a comprar, pero ninguno de los dos están registrados") {
            shouldThrowAny { (Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Consultar si conviene ir a comprar de Argentina a Argentina debe devolver un error") {
            shouldThrowAny { Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Argentina") }
        }

        it("Consultar al observatorio a cuánto equivalen $5000 argentinos en reales brasileros"){
            Observatorio.aCuantoEquivale("Argentina", "Brasil", 5000.0).shouldBe(198.52.plusOrMinus(0.1))
        }

        it("Consultar al observatorio a cuánto equivalen $2000 argentinos en francos de Chad, pero Chad no está registrado") {
            shouldThrowAny { (Observatorio.aCuantoEquivale("Argentina", "Chad", 2000.0)) }
        }

        it("Consultar al observatorio a cuánto equivalen $10000 balboas panameñas en pesos argentinos, pero Panamá no está registrado") {
            shouldThrowAny { (Observatorio.aCuantoEquivale("Panamá", "Argentina", 10000.0)) }
        }

        it("Consultar al observatorio a cuánto equivalen $5000 balboas panameñas en francos de Chad, pero ninguno de los dos están registrados") {
            shouldThrowAny { (Observatorio.aCuantoEquivale("Panamá", "Chad", 5000.0)) }
        }
    }

    describe("Tests unitarios para el observatorio con todos los países") {
        it("Obtener los códigos de los cinco países con mayor densidad poblacional"){
            //Los cinco países son: IND(417.3), ITA(199.9), CHN(146.2), FRA(99.8), MAD(44.7)
            Observatorio.obtenerPaisesConMayorDensidad().shouldBe(mutableSetOf("IND", "ITA", "CHN", "FRA", "MAD"))
        }

        it("Obtener el continente con más países plurinacionales"){
            // El único continente con dos países plurinacionales es América (Canadá y EEUU)
           Observatorio.continenteMasPlurinacional().shouldBe("América")
        }

        it("Obtener el promedio de la densidad poblacional de las islas") {
            // 19 (NZL) + 45 (MAD) = 64 / 2(cantIslas) = 32
            Observatorio.promedioDensidadPoblacionalDeIslas().shouldBe(32)
        }
    }
})