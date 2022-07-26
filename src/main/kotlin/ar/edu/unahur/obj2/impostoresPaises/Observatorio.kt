package ar.edu.unahur.obj2.impostoresPaises

//Por razones explicadas mediante Discord a Marcelo, encontramos que la mejor aplicación de Singleton
//en este caso es por medio de un "companion object" u object como nos aconseja el IDE aplicarlo
object Observatorio:Componente {
    var paises: MutableSet<Pais> = mutableSetOf()

    fun agregarPaises(paisesAdd: MutableSet<Pais>) { paises.addAll(paisesAdd) }

    fun sacarPaises(paises: MutableSet<Pais>) { paises.removeAll(paises) }

    override fun sonLimitrofes(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.esLimitrofeDe(pais2)
    }

    override fun necesitanTraductor(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.necesitaTraductorCon(pais2)
    }

    override fun sonPotencialesAliados(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.esPotencialAliadoDe(pais2)
    }

    override fun convieneIrDeComprasDesdeUnPaisAOtro(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.convieneIrDeComprasA(pais2)
    }

    override fun aCuantoEquivale(paisNombre: String, pais2Nombre: String, monto: Double): Double {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.aCuantoEquivale(pais2, monto)
    }

    override fun obtenerPaisesConMayorDensidad(): MutableList<String> {
        val paisesIso = paises.sortedByDescending { p -> p.densidadPoblacional() }
        val listaIso: MutableList<String> = mutableListOf()
        for(i in 0..4) {
            listaIso.add(paisesIso[i].iso3)
        }
        return listaIso
    }

    override fun continenteMasPlurinacional(): String {
        val cantidadPorContinente = mutableMapOf<String, Int>()
        for(pais in paises) {
            if(pais.esPlurinacional()) {
                val nuevoValue = cantidadPorContinente.getOrPut(pais.continente) { 0 }
                cantidadPorContinente[pais.continente] = nuevoValue+1
            }
        }
        if (cantidadPorContinente.isEmpty()) {
            error("No hay continentes con países plurinacionales.")
        }
        var continenteConMasPlurinacionales = ""
        var cantidadDePlurinacionales = 0
        for(continente in cantidadPorContinente) {
            if(continente.value > cantidadDePlurinacionales) {
                cantidadDePlurinacionales = continente.value
                continenteConMasPlurinacionales = continente.key
            }
        }
        return continenteConMasPlurinacionales
    }

    override fun promedioDensidadPoblacionalDeIslas(): Double {
        var sumaDensidad = 0
        var cantidadIslas = 0
        for (pais in paises) {
            if (pais.esIsla()) {
                sumaDensidad += pais.densidadPoblacional()
                cantidadIslas += 1
            }
        }
        return sumaDensidad / cantidadIslas.toDouble()
    }

}
