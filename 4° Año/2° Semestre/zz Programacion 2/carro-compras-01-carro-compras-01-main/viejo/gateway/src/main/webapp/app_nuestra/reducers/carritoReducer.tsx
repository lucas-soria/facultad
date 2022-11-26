import { TYPES } from "../actions/Actions";
import React from 'react';

const consumeApiProducto = async() =>{
    const data = await fetch('http://localhost:9000/services/microproductos/api/productos',{
      method: 'GET',
      headers: {
          'Content-Type': "application/json",
          'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYzODIxMTgxMH0.daZBWB8cXzPCqT4EXnQXyyFzizz2MOqK-InjPdCQlCc3sMEtMLWudeGc74U2Tuw9PTS7bvfMmvI8W6ICEPV4ZQ'
      },    
    }).then(promesa => promesa.json()).then(contenido => {return contenido});
    return data
  }

export const carritoInitialState = {  
    productos: consumeApiProducto(),
    carrito: [],
}

export function carritoReducer(state,action){
   switch (action.type) {
    /* case TYPES.ADD_TO_CART:{}
        case TYPES.REMOVE_ONE_FROM_CART: {}
        case TYPES.REMOVE_ALL_FROM_CART: {}
        case TYPES.CLEAR_CART:{} */
        default : return state;
   }
}