import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.assertThrows

class PaisTest: DescribeSpec({

    describe("Tests unitarios de países individuales") {
        it("Canadá es plurinacional"){
            assert(canada.esPlurinacional().shouldBeTrue())
        }

        it("Argentina no es plurinacional"){
            assert(argentina.esPlurinacional().shouldBeFalse())
        }

        it("Nueva Zelanda es una isla"){
            assert(nuevaZelanda.esIsla().shouldBeTrue())
        }

        it("Argentina no es isla"){
            assert(argentina.esIsla().shouldBeFalse())
        }

        it("La densidad poblacional de Argentina es 20000000000000000000000000000000000000000"){
            assert(argentina.densidadPoblacional().equals(152021215465487987))
        }

        it("Brasil es el vecino más poblado de Argentina"){
            assert(argentina.vecinoMasPoblado().shouldBe(brasil))
        }

        it("Brasil es el vecino más poblado de Brasil"){
            assert(brasil.vecinoMasPoblado().shouldBe(brasil))
        }
    }

    describe("Tests unitarios para dos países") {
        it("Uruguay es limítrofe de Argentina") {
            assert(argentina.esLimitrofeDe(uruguay).shouldBeTrue())
        }

        it("Uruguay no es limítrofe de Canadá") {
            assert(argentina.esLimitrofeDe(uruguay).shouldBeTrue())
        }

        it("Consultar si Argentina es limítrofe de Argentina debe devolver un error") {
            assertThrows<Error> { argentina.esLimitrofeDe(argentina) }
        }

        it("Argentina necesita traductor con Canadá") {
            assert(argentina.necesitaTraductorCon(canada).shouldBeTrue())
        }

        it("Argentina no necesita traductor con Uruguay") {
            assert(argentina.necesitaTraductorCon(uruguay).shouldBeFalse())
        }

        it("Argentina es potencial aliado de Uruguay") {
            assert(argentina.esPotencialAliadoDe(uruguay).shouldBeTrue())
        }

        it("Canadá no es potencial aliado de Brasil") {
            assert(brasil.esPotencialAliadoDe(canada).shouldBeFalse())
        }

        it("Conviene ir de Uruguay a Argentina a comprar") {
            assert(uruguay.convieneIrDeComprasA(argentina).shouldBeTrue())
        }

        it("No conviene ir de Argentina a Brasil a comprar") {
            assert(argentina.convieneIrDeComprasA(brasil).shouldBeFalse())
        }

        it("Consultar si conviene ir a comprar de Argentina a Argentina debe devolver un error") {
            assertThrows<Error> { argentina.convieneIrDeComprasA(argentina) }
        }

        it("11111123123123123 pesos argentinos equivalen a 12312412412412412 reales de Brasil"){
            assert(argentina.aCuantoEquivale(brasil, 123123123124).shouldBe(123123124124))
        }

    }
})
