function quienesPublican (tiempo) {
    publicacionesBeauty = db.beauty.find({fecha_publicacion: {$gte: ISODate(tiempo)}}).toArray();
    publicacionesIssues = db.issues.find({fecha_publicacion: {$gte: ISODate(tiempo)}}).toArray();
    publicacionesAdds = db.adds.find({fecha_publicacion: {$gte: ISODate(tiempo)}}).toArray();
    
    var publicadores = [];
    for (var i = 0; i < publicacionesBeauty.length; i++) {
        if (!publicadores.includes(publicacionesBeauty[i].id_usuario)) {
            publicadores.push(publicacionesBeauty[i].id_usuario);
        }
    }
    for (var i = 0; i < publicacionesIssues.length; i++) {
        if (!publicadores.includes(publicacionesIssues[i].id_usuario)) {
            publicadores.push(publicacionesIssues[i].id_usuario);
        }
    }
    for (var i = 0; i < publicacionesAdds.length; i++) {
        if (!publicadores.includes(publicacionesAdds[i].id_usuario)) {
            publicadores.push(publicacionesAdds[i].id_usuario);
        }
    }

    var publicacionesTotales = publicacionesBeauty.length + publicacionesIssues.length + publicacionesAdds.length;
    var publicadoress = "";
    for (var i = 0; i < publicadores.length; i++) {
        publicadoress = publicadoress + "* " + publicadores[i] + "\n"
    }

    return "Se publicaron: " + publicacionesTotales + " fotos\nLas personas que las publicaron fueron:\n" + publicadoress
}

// Output
/*
db.loadServerScripts();
Se publicaron: 15 fotos
Las personas que las publicaron fueron:
* lucasoria
* juanpedrito33
* soriabel
* greenpeace
* b&m_foundation
*/
