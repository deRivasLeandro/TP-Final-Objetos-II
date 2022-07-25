import ar.edu.unahur.obj2.impostoresPaises.Pais
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class PaisTest: DescribeSpec({

    val argentina = Pais("Argentina", "ARG", 47327407, 2780400.0,"América", "ARS", 136.0, mutableSetOf("Mercosur"), mutableSetOf("Español"))
    val brasil = Pais("Brasil", "BRA", 217240060, 	8515770.0, "América", "BRL", 5.4, mutableSetOf("Mercosur"), mutableSetOf("Portugués"))
    val uruguay = Pais("Uruguay", "URY", 3554915, 	176215.0, "América", "UYU", 41.4, mutableSetOf("Mercosur"), mutableSetOf("Español"))
    val canada = Pais("Canadá", "CAN", 38246108, 9984670.0, "América", "CAD", 1.29, mutableSetOf("Otán", "G7"), mutableSetOf("Inglés", "Francés"))
    val nuevaZelanda = Pais("Nueva Zelanda", "NZL", 5006020, 268838.0, "Oceanía", "NZD", 1.59, mutableSetOf(), mutableSetOf("Inglés", "Maorí"))

    argentina.agregarPaisesLimitrofes(mutableSetOf(brasil, uruguay))
    uruguay.agregarPaisesLimitrofes(mutableSetOf(brasil, argentina))
    brasil.agregarPaisesLimitrofes(mutableSetOf(argentina, uruguay))

    describe("Tests unitarios de países individuales") {
        it("Canadá es plurinacional"){
            canada.esPlurinacional().shouldBeTrue()
        }

        it("Argentina no es plurinacional"){
            argentina.esPlurinacional().shouldBeFalse()
        }

        it("Nueva Zelanda es una isla"){
            nuevaZelanda.esIsla().shouldBeTrue()
        }

        it("Argentina no es isla"){
            argentina.esIsla().shouldBeFalse()
        }

        it("La densidad poblacional de Argentina es 17"){
            argentina.densidadPoblacional().shouldBe(17)
        }

        it("Brasil es el vecino más poblado de Argentina"){
            argentina.vecinoMasPoblado().shouldBe(brasil)
        }

        it("Brasil es el vecino más poblado de Brasil"){
            brasil.vecinoMasPoblado().shouldBe(brasil)
        }
    }

    describe("Tests unitarios para dos países") {
        it("Uruguay es limítrofe de Argentina") {
            argentina.esLimitrofeDe(uruguay).shouldBeTrue()
        }

        it("Uruguay no es limítrofe de Canadá") {
            uruguay.esLimitrofeDe(canada).shouldBeFalse()
        }

        it("Consultar si Argentina es limítrofe de Argentina debe devolver un error") {
            shouldThrowAny { argentina.esLimitrofeDe(argentina) }
        }

        it("Argentina necesita traductor con Canadá") {
            argentina.necesitaTraductorCon(canada).shouldBeTrue()
        }

        it("Argentina no necesita traductor con Uruguay") {
            argentina.necesitaTraductorCon(uruguay).shouldBeFalse()
        }

        it("Argentina es potencial aliado de Uruguay") {
            argentina.esPotencialAliadoDe(uruguay).shouldBeTrue()
        }

        it("Canadá no es potencial aliado de Brasil") {
            brasil.esPotencialAliadoDe(canada).shouldBeFalse()
        }

        it("Conviene ir de Uruguay a Argentina a comprar") {
            uruguay.convieneIrDeComprasA(argentina).shouldBeTrue()
        }

        it("No conviene ir de Argentina a Brasil a comprar") {
            argentina.convieneIrDeComprasA(brasil).shouldBeFalse()
        }

        it("Consultar si conviene ir a comprar de Argentina a Argentina debe devolver un error") {
            shouldThrowAny { argentina.convieneIrDeComprasA(argentina) }
        }

        it("100 pesos argentinos equivalen a 3.97 reales de Brasil"){
            argentina.aCuantoEquivale(brasil, 100.0).shouldBe(3.97.plusOrMinus(0.1))
        }

    }
})
