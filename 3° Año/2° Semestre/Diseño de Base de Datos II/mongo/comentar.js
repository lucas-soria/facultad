function comentar (seccion, publicacion, userid, comentario) {
    if (seccion == "beauty") {
        db.beauty.findOneAndUpdate({"_id": publicacion}, {"$push": {"comentarios": {"userid": userid, "comentario": comentario} }})
    } else {
        db.issues.findOneAndUpdate({"_id": publicacion}, {"$push": {"comentarios": {"userid": userid, "comentario": comentario} }})
    }
}