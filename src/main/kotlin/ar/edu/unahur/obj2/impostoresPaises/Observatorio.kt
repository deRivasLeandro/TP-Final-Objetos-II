package ar.edu.unahur.obj2.impostoresPaises

//Por razones explicadas mediante Discord a Marcelo, encontramos que la mejor aplicación de Singleton
//en este caso es por medio de un "companion object" u object como nos aconseja el IDE aplicarlo
object Observatorio {
    var paises: MutableSet<Pais> = mutableSetOf()

    fun agregarPaises(paises: MutableSet<Pais>) { paises.addAll(paises) }

    fun sacarPaises(paises: MutableSet<Pais>) { paises.removeAll(paises) }

    fun sonLimitrofes(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.esLimitrofeDe(pais2)
    }

    fun necesitanTraductor(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.necesitaTraductorCon(pais2)
    }

    fun sonPotencialesAliados(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.esPotencialAliadoDe(pais2)
    }

    fun convieneIrDeComprasDesdeUnPaisAOtro(paisNombre: String, pais2Nombre: String): Boolean {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.convieneIrDeCompras(pais2)
    }

    fun aCuantoEquivale(paisNombre: String, pais2Nombre: String, monto: Double): Double {
        val pais = paises.find { p -> p.nombre == paisNombre }
        val pais2 = paises.find { p -> p.nombre == pais2Nombre }
        if(pais == null && pais2 == null) { error("Los países $paisNombre y $pais2Nombre no se encuentran registrados en el observatorio.") }
        if(pais == null){ error("El país $paisNombre no se encuentra registrado en el observatorio.") }
        if(pais2 == null){ error("El país $pais2Nombre no se encuentra registrado en el observatorio.") }
        return pais.aCuantoEquivale(pais2, monto)
    }

    fun obtenerPaisesConMayorDensidad(): MutableList<String> {
        val paisesIso = paises.sortedBy { p -> p.densidadPoblacional() }.toList()
        val listaIso: MutableList<String> = mutableListOf()
        for(i in 0..4) {
            listaIso.add(paisesIso[i].iso3)
        }
        return listaIso
    }

    fun contienenteMasPlurinacional(): String? {
        val cantidadPorContinente = mutableMapOf<String, Int>()
        for(pais in paises) {
            if(pais.esPlurinacional()) {
                val nuevoValue = cantidadPorContinente.getOrPut(pais.continente) { 0 }
                cantidadPorContinente[pais.continente] = nuevoValue+1
            }
        }
        return cantidadPorContinente.maxOfOrNull { c -> c.key }
    }

    fun promedioDensidadPoblacionalDeIslas(): Double {
        var sumaDensidad = 0
        var cantidadIslas = 0
        for(pais in paises) {
            if(pais.esIsla()) {
                sumaDensidad += pais.densidadPoblacional()
                cantidadIslas += 1
            }
        }
        return sumaDensidad/cantidadIslas.toDouble()
    }

}
