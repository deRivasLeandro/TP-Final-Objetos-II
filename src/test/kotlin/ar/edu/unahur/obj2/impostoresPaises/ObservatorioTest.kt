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
    //ARREGLAR ESTADOS UNIDOS
    val eeuu = Pais("Estados Unidos", "USA", 38246108, 9984670.0, "América", "CAD", 1.29, mutableSetOf("Otán", "G7"), mutableSetOf("Inglés", "Francés"))
    argentina.agregarPaisesLimitrofes(mutableSetOf(brasil, uruguay))
    uruguay.agregarPaisesLimitrofes(mutableSetOf(brasil, argentina))
    brasil.agregarPaisesLimitrofes(mutableSetOf(argentina, uruguay))
    canada.agregarPaisesLimitrofes(mutableSetOf(eeuu))
    eeuu.agregarPaisesLimitrofes(mutableSetOf(canada))
    Observatorio.paises = mutableSetOf(argentina, uruguay, brasil, canada, eeuu, nuevaZelanda)

    describe("Tests unitarios para el observatorio con dos paises") {
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

        it("Consultar al observatorio si 5000 pesos argentinos equivalen a 198.5 reales de Brasil"){
            Observatorio.aCuantoEquivale("Argentina", "Brasil", 5000.0).shouldBe(198.52.plusOrMinus(0.1))
        }

        it("Consultar al observatorio si 11111123123123123 pesos argentinos equivalen a 12312412412412412 francos de Chad, pero Chad no está registrado") {
            shouldThrowAny { (Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si 11111123123123123 balboas panameños equivalen a 12312412412412412 pesos argentinos, pero Panamá no está registrado") {
            shouldThrowAny { (Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si 11111123123123123 balboas panameños equivalen a 12312412412412412 francos de Chad, pero ninguno de los dos están registrados") {
            shouldThrowAny { (Observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Chad")) }
        }

    }

    describe("Tests unitarios para el observatorio con todos los países") {
        it("Obtener los códigos de los cinco países con mayor densidad poblacional"){
            Observatorio.obtenerPaisesConMayorDensidad().shouldBe(mutableSetOf("BRA", "URY", "NZL", "ARG", "CAN"))
        }

        it("Obtener el continente con más países plurinacionales"){
           Observatorio.continenteMasPlurinacional().shouldBe("Oceanía")
        }

        it("Obtener el promedio de la densidad poblacional de las islas") {
            Observatorio.promedioDensidadPoblacionalDeIslas().shouldBe(19)
        }
    }
})