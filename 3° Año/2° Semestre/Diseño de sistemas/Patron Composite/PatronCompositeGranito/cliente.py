from Composite import Composite
from Empleado import Empleado
from SectorAdministracion import SectorAdministracion
from SectorCajas import SectorCajas
from SectorContaduria import SectorContaduria
from SectorGerencia import SectorGerencia
from SectorRRHH import SectorRRHH


if __name__ == "__main__":
    banco = Composite()
    administracion = SectorAdministracion()
    cajas = SectorCajas()
    contaduria = SectorContaduria()
    gerencia = SectorGerencia()
    rrhh = SectorRRHH()

    banco.agrega(gerencia)
    banco.agrega(contaduria)
    banco.agrega(administracion)
    administracion.agrega(cajas)
    administracion.agrega(rrhh)

    cajero1 = Empleado("Lucas Soria", "Cajero", 2000)
    cajero2 = Empleado("Valentina Scalco", "Cajero", 2000)
    cajas.agrega(cajero1)
    cajas.agrega(cajero2)

    gerente = Empleado("Franco Santander", "Gerente", 5000)
    gerencia.agrega(gerente)

    selectora = Empleado("Agustina Capo", "Selectora", 1500)
    rrhh.agrega(selectora)

    contador = Empleado("Philipp von Kesselsttatt", "Contador", 3000)
    contaduria.agrega(contador)

    print("El total de sueldos a pagar es: ", banco.get_sueldo())
