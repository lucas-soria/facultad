export const initialState = {
    carro : [],
    user : null,
    tokenProducto: null,
    tokenCarrito: null,
    productosHabilitados: null,
    productos: null,
    ventas:null,
}

export const actionTypes = {
    ADD_TO_CART: "ADD_TO_CART",
    REMOVE_ITEM: "REMOVE_ITEM",
    SET_USER : "SET_USER",
    SIGN_OUT : "SIGN_OUT",
    SIGN_IN : "SIGN_IN",
    GET_VENTAS: "GET_VENTAS"
}

export const getTotal = (carro) =>{ //funcion de javaScript que me permite sumar valores de un array
    return carro?.reduce((acumulador,item) => item.precio * item.cantidad + acumulador,0);
}

export const getCantidadProductos = (carro) =>{
    return carro?.reduce((acumulador,item) => item.cantidad + acumulador,0);
}

export const logIN = async(userName,password) =>{
    const url = 'http://localhost/api/productos/authenticate'
    var userData = {username: userName, password : password, rememberMe: true};
    const response = await fetch(url,{
        method: 'POST',
        body: JSON.stringify(userData),
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
    })

    const url2 = 'http://localhost/api/carrodecompras/authenticate'
    const response2 = await fetch(url2,{
        method: 'POST',
        body: JSON.stringify(userData),
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
    })
    const tokenP = await response.json();
    const tokenC = await response2.json();


    return [tokenP.id_token,tokenC.id_token]
}

export const getUser = async(token) =>{

  const url2 = 'http://localhost/api/carrodecompras/account';
  const bearer = 'Bearer ' + token;
  const responseUser = await fetch(url2,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Accept': 'application/json',
            'Authorization': bearer
        },    
      })
  const userAutenticado = await responseUser.json();

  return userAutenticado
}

export const getProductosSeleccionadosCarrito = async(tokenP,tokenC) =>{

    const url = 'http://localhost/api/carrodecompras/producto-seleccionados/current';
    const bearer = 'Bearer '+ tokenC;
    const response = await fetch(url,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Authorization': bearer
        },    
      })
    const carritoActualSeleccionados = await response.json();

    const carritoActual = await Promise.all( carritoActualSeleccionados.map(async(pSeleccionado) =>{      
        const info = await getCurrentsProducts(pSeleccionado,tokenP);    
        const producto = {...pSeleccionado,informacion:info};
        return producto;
    }))   

    return carritoActual
}

const getCurrentsProducts = async (pSeleccionado,tokenP)=>{
    const url2 = 'http://localhost/api/productos/open/productos/' + pSeleccionado.producto;
    const bearer2 = 'Bearer ' + tokenP;

    const response = await fetch(url2,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Accept': 'application/json',
            'Authorization': bearer2
        },    
      })
      const carritoActual= await response.json();
      return carritoActual
}

export const fetchProductos = async (tp,setProductos) =>{      
    const url = 'http://localhost/api/productos/open/productos'
    const bearer = 'Bearer ' + tp;
      const response = await fetch(url,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Accept': 'application/json',
            'Authorization': bearer
        },    
      })
      const data = await response.json()

      return data
  }


export const fetchProductosHabilitados = async (tp) =>{      
    const url = 'http://localhost/api/productos/open/productos/habilitados'
    const bearer = 'Bearer ' + tp;
      const response = await fetch(url,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Accept': 'application/json',
            'Authorization': bearer
        },    
      })
      const data = await response.json()
      
      return data
  }


export const seleccionarProducto = async(idProducto,tokenC) =>{
    const url = 'http://localhost/api/carrodecompras/producto-seleccionados'
    var data = {producto: idProducto};
    const bearer = 'Bearer ' + tokenC;
    await fetch(url,{
        method: 'POST',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': bearer
          }
    })  
}

export const eliminarProductoSeleccionado = async(idProductoSeleccionado,tokenC,cantidad) =>{
    const url = 'http://localhost/api/carrodecompras/producto-seleccionados/' + idProductoSeleccionado
    var data = {id:idProductoSeleccionado,cantidad: cantidad};
    const bearer = 'Bearer ' + tokenC;
    await fetch(url,{
        method: 'PATCH',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': bearer
          }
    })  
}

export const realizarVenta = async (tokenC) =>{      
    const url = 'http://localhost/carrodecompras/api/carro-compras/current';
    const bearer = 'Bearer '+ tokenC;
    const response = await fetch(url,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Authorization': bearer
        },    
      })
    const carritoActual = await response.json();

    const idCarro = carritoActual.id;

    const url2 = 'http://localhost/api/carrodecompras/carro-compras/' + idCarro
    var data = {id:idCarro,vendido: true,finalizado:true};
    await fetch(url2,{
        method: 'PATCH',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': bearer
          }
    })  
}

export const getAllVentas = async (tc) =>{      
    const url = 'http://localhost/api/carrodecompras/ventas/current/all'
    const bearer = 'Bearer ' + tc;
    const response = await fetch(url,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Accept': 'application/json',
            'Authorization': bearer
        },    
      })
    const ventasSolas = await response.json()

    const ventas = await Promise.all( ventasSolas.map(async(v) =>{      
        const info = await getAllProductosComprados(v.id,tc);    
        const ventaConProductos = {...v,informacion:info};
        return ventaConProductos;
    }))   
     
    return ventas
}

export const getAllProductosComprados = async (idVenta,tc) =>{      
    const url = "http://localhost/api/carrodecompras/producto-comprados/current/"+idVenta;
    const bearer = 'Bearer ' + tc;
      const response = await fetch(url,{
        method: 'GET',
        headers: {
            'Content-Type': "application/json",
            'Accept': 'application/json',
            'Authorization': bearer
        },    
      })
      const data = await response.json()
      
      return data
}


const reducer = (state,action)=> {
    switch(action.type){
        case "ADD_TO_CART" :
            return {
                ...state,
                carro: action.carro,
            };
        case "REMOVE_ITEM":
            return {
                ...state,
                carro: action.carro,
            };
        case "SET_USER" :
            return {
                ...state,
                user: action.user,
            };

        case "SIGN_OUT" :
            return {
                 ...state,
                user: action.user,
                carro: action.carro,
                tokenProducto: action.tokenProducto,
                tokenCarrito: action.tokenCarrito,
                productos : action.productos,
                productosHabilitados: action.productosHabilitados
            };
        case "SIGN_IN" :
            return {
                ...state,
                user: action.user,
                carro: action.carro,
                tokenProducto: action.tokenProducto,
                tokenCarrito: action.tokenCarrito,
                productos : action.productos,
                productosHabilitados : action.productosHabilitados
            };
        case "GET_VENTAS":
            return {
                ...state,
                ventas: action.ventas,
            };
        
        default : return state;
    }
}

export default reducer