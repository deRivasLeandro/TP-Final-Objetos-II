package ar.edu.unahur.obj2.impostoresPaises

open class BaseDecorator(var wrappee: Componente): Componente {

    fun decorar(componente: Componente) {
        wrappee = componente
    }

    override fun sonLimitrofes(paisNombre: String, pais2Nombre: String): Boolean {
        return wrappee.sonLimitrofes(paisNombre, pais2Nombre)
    }

    override fun necesitanTraductor(paisNombre: String, pais2Nombre: String): Boolean {
        return wrappee.necesitanTraductor(paisNombre, pais2Nombre)
    }

    override fun sonPotencialesAliados(paisNombre: String, pais2Nombre: String): Boolean {
        return wrappee.sonPotencialesAliados(paisNombre, pais2Nombre)
    }

    override fun convieneIrDeComprasDesdeUnPaisAOtro(paisNombre: String, pais2Nombre: String): Boolean {
        return wrappee.convieneIrDeComprasDesdeUnPaisAOtro(paisNombre, pais2Nombre)
    }

    override fun aCuantoEquivale(paisNombre: String, pais2Nombre: String, monto: Double): Double {
        return wrappee.aCuantoEquivale(paisNombre, pais2Nombre, monto)
    }

    override fun obtenerPaisesConMayorDensidad(): MutableList<String> {
        return wrappee.obtenerPaisesConMayorDensidad()
    }

    override fun continenteMasPlurinacional(): String {
        return wrappee.continenteMasPlurinacional()
    }

    override fun promedioDensidadPoblacionalDeIslas(): Double {
        return wrappee.promedioDensidadPoblacionalDeIslas()
    }

}