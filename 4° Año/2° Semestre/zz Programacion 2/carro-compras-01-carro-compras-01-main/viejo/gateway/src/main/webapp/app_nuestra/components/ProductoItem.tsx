import React from 'react';

const ProductoItem = ({data, addToCart}) => {
    const id = data.id;
    const name = data.nombre;
    const price = data.precio;
    return (
        <div style={{ border: "thin solid gray", padding: "1rem" }}>
        <h3>Hola</h3>
        <h4>{name}</h4>
        <h5>${price}.00</h5>
        <button onClick={() => addToCart(id)}>Agregar</button>
      </div>
    )
}

export default ProductoItem
