function nuevoUsuario (tiempo) {
    usuarios = db.users.find({fecha_creacion: {$gte: ISODate(tiempo)}}).toArray();
    lista = [];
    for (var i = 0; i < usuarios.length; i++) {
        lista.push(usuarios[i]._id);
    }
    return lista
}

// Output
/*
[
	"lucasoria",
	"greenpeace",
	"juanpedrito33",
	"b&m_foundation",
	"soriabel",
	"usuarioinsertado",
	"insertado"
] 
*/