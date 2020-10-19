function traerPerfil (userid) {
    return db.users.findOne({"_id": userid})
}
