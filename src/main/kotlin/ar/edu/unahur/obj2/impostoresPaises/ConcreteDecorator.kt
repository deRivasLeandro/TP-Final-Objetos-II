package ar.edu.unahur.obj2.impostoresPaises

class ConcreteDecorator(wrappee: Componente): BaseDecorator(wrappee) {
    override fun continenteMasPlurinacional(): String {
        return "El continente más plurinacional es " + super.continenteMasPlurinacional()
    }
}