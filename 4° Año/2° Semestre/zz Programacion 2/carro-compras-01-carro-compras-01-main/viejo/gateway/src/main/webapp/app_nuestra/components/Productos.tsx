import React, {Component,useState,useEffect} from 'react';
import ProductoItem from "./ProductoItem";


const Productos = ({productos,addToCart})  =>{   

    return(
        <div>
             <h3>Productos</h3>
            <article className="box grid-responsive">
        {productos.map((product) => (
          <ProductoItem key={product.id} data={product} addToCart={addToCart} />
        ))}
      </article>
        </div>
    )
}

export default Productos