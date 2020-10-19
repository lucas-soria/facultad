function ganancias(fecha, coste) {
    cantidad = db.adds.find({"fecha_publicacion": {$gte: ISODate(fecha)}}).count();
    return cantidad * coste;
}