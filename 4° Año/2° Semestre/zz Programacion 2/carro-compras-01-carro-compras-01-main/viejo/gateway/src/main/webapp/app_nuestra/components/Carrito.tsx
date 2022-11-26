import axios from 'axios';
import React, {Component,useState,useEffect} from 'react';
import { useReducer } from 'react';
import { carritoInitialState, carritoReducer } from "../reducers/carritoReducer";
import Productos from "./Productos";

const Carrito = () =>{
  const [state, dispatch] = useReducer(carritoReducer, carritoInitialState);
  const productos = state.productos;
  const carrito = state.carrito;  
  const addToCart = (id) => {}
  const deleteFromCart = () => {}
  const clearCart = () => {} 

    const [p,setp] = useState([]) 

  const consumeApiProducto = async() =>{
    const data = await fetch('http://localhost:9000/services/microproductos/api/productos',{
      method: 'GET',
      headers: {
          'Content-Type': "application/json",
          'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYzODIxMTgxMH0.daZBWB8cXzPCqT4EXnQXyyFzizz2MOqK-InjPdCQlCc3sMEtMLWudeGc74U2Tuw9PTS7bvfMmvI8W6ICEPV4ZQ'
      },    
    }).then(promesa => promesa.json()).then(contenido => {return contenido});
    setp(data)
  }

  useEffect(() => {
    consumeApiProducto()
},[])
 
  return(
    <div>
      <h2>Carrito de Compras</h2>      
              
      <Productos productos={consumeApiProducto()} addToCart={addToCart} />      
      <h3>Carrito</h3>
      <article className='cajaProducto'></article>
    </div>
  )

}

export default Carrito
