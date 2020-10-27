function traerPublicacionesBeauty (userid, limit) {
    lista = [];
    seguidos = db.users.findOne({_id: userid}, {seguidos:1}).seguidos;
    for (var i = 0; i < db.beauty.find({id_usuario: {"$in" : seguidos}}, {_id:1}).count(); i++) {
        docs = db.beauty.find({id_usuario: {"$in" : seguidos}}).sort({fecha_creacion:1}).limit(limit).skip(i*limit).toArray();
        if (docs.length >= 1) {
            lista.push(docs);
        }
    }
    return lista;
}