package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt

class Pais(var nombre: String, var iso3: String, var poblacion: Int, var superficie: Double, var continente: String, var codigoMoneda: String, var cotizacionDolar: Double, var bloquesRegionales: MutableSet<String>, var idiomasOficiales: MutableSet<String>) {
    //Este atributo se inicializa vacío porque es posible que los países limítrofes todavía no se encuentren cargados en el programa y como no podemos usar cascade para crearlos dentro del constructor lo solventamos de esta forma
    var paisesLimitrofes: MutableSet<Pais> = mutableSetOf()

    fun esPlurinacional(): Boolean { return idiomasOficiales.size >= 2 }

    fun esIsla(): Boolean { return paisesLimitrofes.isEmpty() }

    fun densidadPoblacional(): Int { return (poblacion/superficie).roundToInt() }

    //Aseguramos que este método nunca va a devolver null porque agregamos al país que lo llama a la lista de limítrofes, entonces si no tiene limítrofes simplemente se retorna a él mismo
    fun vecinoMasPoblado(): Pais { return paisesLimitrofes.plus(this).maxByOrNull { it.poblacion }!! }

    fun esLimitrofeDe(pais: Pais): Boolean {
        if(pais == this){ error("Un país no puede ser limítrofe de sí mismo.") }
        return paisesLimitrofes.contains(pais)
    }

    fun necesitaTraductorCon(pais: Pais): Boolean { return idiomasOficiales.intersect(pais.idiomasOficiales).isEmpty() }

    fun pertenecenAUnMismoBloque(pais:Pais): Boolean { return bloquesRegionales.intersect(pais.bloquesRegionales).isNotEmpty() }

    fun esPotencialAliadoDe(pais: Pais): Boolean { return ! necesitaTraductorCon(pais) && pertenecenAUnMismoBloque(pais) }

    fun convieneIrDeComprasA(pais: Pais): Boolean {
        if(pais == this){ error("La consulta debe hacerse entre dos países diferentes.") }
        return cotizacionDolar < pais.cotizacionDolar
    }

    fun aCuantoEquivale(pais: Pais, monto: Double): Double { return monto/cotizacionDolar*pais.cotizacionDolar }

    fun agregarPaisesLimitrofes(paises: MutableSet<Pais>) { paisesLimitrofes.addAll(paises) }

    fun sacarPaises(paises: MutableSet<Pais>) { paisesLimitrofes.removeAll(paises) }

    override fun toString(): String {
        return nombre
    }
}