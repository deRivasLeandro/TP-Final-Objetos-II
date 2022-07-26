package ar.edu.unahur.obj2.impostoresPaises

interface Componente {

    fun sonLimitrofes(paisNombre: String, pais2Nombre: String): Boolean

    fun necesitanTraductor(paisNombre: String, pais2Nombre: String): Boolean

    fun sonPotencialesAliados(paisNombre: String, pais2Nombre: String): Boolean

    fun convieneIrDeComprasDesdeUnPaisAOtro(paisNombre: String, pais2Nombre: String): Boolean

    fun aCuantoEquivale(paisNombre: String, pais2Nombre: String, monto: Double): Double

    fun obtenerPaisesConMayorDensidad(): MutableList<String>

    fun continenteMasPlurinacional(): String

    fun promedioDensidadPoblacionalDeIslas(): Double

}