db.system.js.save({
    _id: "findLikes",
    value: function(id_usuario) {
        likes = db.users.findOne( { _id: id_usuario }, { likes: 1 } ).likes;
        lista = [];
        docs = db.beauty.find( { _id: { $in: likes } }, { id_usuario: 1 , foto: 1 } ).toArray();
        if (docs.length >= 1) {
            for (var i = 0; i < docs.length; i++) {
                lista.push(docs[i]);
            }
        }
        docs = db.adds.find( { _id: { $in: likes }}, { id_usuario: 1, foto: 1 } ).toArray();
        if (docs.length >= 1) {
            for (var i = 0; i < docs.length; i++) {
                lista.push(docs[i]);
            }
        }
        return lista;
    }
});