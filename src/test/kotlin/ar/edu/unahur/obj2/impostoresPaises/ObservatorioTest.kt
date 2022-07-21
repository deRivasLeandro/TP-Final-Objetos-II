package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class ObservatorioTest: DescribeSpec ({

    describe("Tests unitarios para el observatorio con dos paises") {
        it("Consultar al observatorio si Uruguay es limítrofe de Argentina") {
            assert(observatorio.sonLimitrofes("Argentina", "Uruguay").shouldBeTrue())
        }

        it("Consultar al observatorio si Uruguay no es limítrofe de Canadá") {
            assert(observatorio.sonLimitrofes("Uruguay", "Canadá").shouldBeFalse())
        }

        it("Consultar al observatorio si Argentina es limítrofe de Argentina debe devolver un error") {
            assertThrows<Error> { observatorio.sonLimitrofes("Argentina", "Argentina") }
        }

        it("Consultar al observatorio si Argentina necesita traductor con Canadá") {
            assert(observatorio.necesitanTraductor("Argentina", "Canadá").shouldBeTrue())
        }

        it("Consultar al observatorio si Argentina no necesita traductor con Uruguay") {
            assert(observatorio.necesitanTraductor("Argentina", "Uruguay").shouldBeFalse())
        }

        it("Consultar al observatorio si Argentina es potencial aliado de Uruguay") {
            assert(observatorio.sonPotencialesAliados("Argentina", "Uruguay").shouldBeTrue())
        }

        it("Consultar al observatorio si Canadá no es potencial aliado de Brasil") {
            assert(observatorio.sonPotencialesAliados("Canada", "Brasil").shouldBeFalse())
        }

        it("Consultar al observatorio si Conviene ir de Uruguay a Argentina a comprar") {
            assert(observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Uruguay", "Argentina").shouldBeTrue())
        }

        it("Consultar al observatorio si No conviene ir de Argentina a Brasil a comprar") {
            assert(observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Brasil").shouldBeFalse())
        }

        it("Consultar al observatorio si Consultar si conviene ir a comprar de Argentina a Argentina debe devolver un error") {
            assertThrows<Error> { observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Argentina") }
        }

        it("Consultar al observatorio si 11111123123123123 pesos argentinos equivalen a 12312412412412412 reales de Brasil"){
            assert(observatorio.aCuantoEquivale("Argentina", "Brasil", 123123123124).shouldBe(123123124124))
        }

    }

    describe("Tests unitarios para el observatorio con todos los países") {
        it("Obtener los códigos de los cinco países con mayor densidad poblacional"){
            assert(observatorio.obtenerPaisesConMayorDensidad().shouldBe([lalallalalalalalal, allalala , lalala ,a a,a]))
        }

        it("Obtener el continente con mayor densidad poblacional"){
            assert(observatorio.continenteMasPlurinacional().shouldBe(asia))
        }

        it("Obtener el promedio de la densidad poblacional de las islas") {
            assert(observatorio.promedioDensidadPoblacionalDeIslas).shouldBe(123123123123123123)
        }
    }
})