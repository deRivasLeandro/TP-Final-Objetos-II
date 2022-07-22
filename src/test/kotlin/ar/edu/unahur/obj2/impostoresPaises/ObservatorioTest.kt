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

        it("Consultar al observatorio si Uruguay es limítrofe de Chad, pero Chad no está registrado") {
            assertThrows<Error> { (observatorio.sonLimitrofes("Uruguay", "Chad")) }
        }

        it("Consultar al observatorio si Panamá es limítrofe de Argentina, pero Panamá no está registrado") {
            assertThrows<Error> { (observatorio.sonLimitrofes("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si Panamá es limítrofe de Chad, pero ninguno de los dos están registrados") {
            assertThrows<Error> { (observatorio.sonLimitrofes("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Argentina es limítrofe de Argentina debe devolver un error") {
            assertThrows<Error> { observatorio.sonLimitrofes("Argentina", "Argentina") }
        }

        it("Consultar al observatorio si Argentina necesita traductor con Canadá") {
            assert(observatorio.necesitanTraductor("Argentina", "Canadá").shouldBeTrue())
        }

        it("Consultar al observatorio si Argentina necesita traductor con Uruguay") {
            assert(observatorio.necesitanTraductor("Argentina", "Uruguay").shouldBeFalse())
        }

        it("Consultar al observatorio si Argentina necesita traductor con Chad, pero Chad no está registrado") {
            assertThrows<Error> { (observatorio.necesitanTraductor("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si Panamá necesita traductor con Argentina, pero Panamá no está registrado") {
            assertThrows<Error> { (observatorio.necesitanTraductor("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si Panamá necesita traductor con Chad, pero ninguno de los dos están registrados") {
            assertThrows<Error> { (observatorio.necesitanTraductor("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Argentina es potencial aliado de Uruguay") {
            assert(observatorio.sonPotencialesAliados("Argentina", "Uruguay").shouldBeTrue())
        }

        it("Consultar al observatorio si Canadá es potencial aliado de Brasil") {
            assert(observatorio.sonPotencialesAliados("Canada", "Brasil").shouldBeFalse())
        }

        it("Consultar al observatorio si Argentina es potencial aliado de Chad, pero Chad no está registrado") {
            assertThrows<Error> { (observatorio.sonPotencialesAliados("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si Panamá es potencial aliado de Argentina, pero Panamá no está registrado") {
            assertThrows<Error> { (observatorio.sonPotencialesAliados("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si Panamá es potencial aliado de Chad, pero ninguno de los dos están registrados") {
            assertThrows<Error> { (observatorio.sonPotencialesAliados("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Conviene ir de Uruguay a Argentina a comprar") {
            assert(observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Uruguay", "Argentina").shouldBeTrue())
        }

        it("Consultar al observatorio si conviene ir de Argentina a Brasil a comprar") {
            assert(observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Brasil").shouldBeFalse())
        }

        it("Consultar al observatorio si conviene ir de Argentina a Chad a comprar, pero Chad no está registrado") {
            assertThrows<Error> { (observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si conviene ir de Panamá a Argentina a comprar, pero Panamá no está registrado") {
            assertThrows<Error> { (observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si conviene ir de Panamá a Chad a comprar, pero ninguno de los dos están registrados") {
            assertThrows<Error> { (observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Chad")) }
        }

        it("Consultar al observatorio si Consultar si conviene ir a comprar de Argentina a Argentina debe devolver un error") {
            assertThrows<Error> { observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Argentina") }
        }

        it("Consultar al observatorio si 11111123123123123 pesos argentinos equivalen a 12312412412412412 reales de Brasil"){
            assert(observatorio.aCuantoEquivale("Argentina", "Brasil", 123123123124).shouldBe(123123124124))
        }

        it("Consultar al observatorio si 11111123123123123 pesos argentinos equivalen a 12312412412412412 francos de Chad, pero Chad no está registrado") {
            assertThrows<Error> { (observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Argentina", "Chad")) }
        }

        it("Consultar al observatorio si 11111123123123123 balboas panameños equivalen a 12312412412412412 pesos argentinos, pero Panamá no está registrado") {
            assertThrows<Error> { (observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Argentina")) }
        }

        it("Consultar al observatorio si 11111123123123123 balboas panameños equivalen a 12312412412412412 francos de Chad, pero ninguno de los dos están registrados") {
            assertThrows<Error> { (observatorio.convieneIrDeComprasDesdeUnPaisAOtro("Panamá", "Chad")) }
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