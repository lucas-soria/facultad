import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import ReceiptIcon from '@mui/icons-material/Receipt';
import ShoppingCart from '@mui/icons-material/ShoppingCartOutlined';
import { Badge } from '@mui/material';
import { Link } from 'react-router-dom';
import { useStateValue } from '../StateProvider';
import {useNavigate} from 'react-router-dom';
import { actionTypes, getAllVentas, getCantidadProductos } from '../reducer';

export default function NavBar() {
  
  const [{carro,user,ventas,tokenCarrito},dispatch] = useStateValue();
  const navigate = useNavigate();

  const [listaCompras,setListaCompras] = React.useState()

  const ventasClick = async() =>{
    const allVentas = await getAllVentas(tokenCarrito);
    dispatch({
      type: actionTypes.GET_VENTAS,
      ventas: allVentas
    });
    navigate("/misVentas")
  }

  const handleClick = ()=>{
    if(user){
      //deslogear
      dispatch({
        type: actionTypes.SIGN_OUT,
        user: null,
        carro: [],
        tokenProducto :null,
        tokenCarrito: null,
        productosHabilitados: null,
        productos: null
      });
      navigate("/")
    };
  };



  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>   
          
        {(!user?.authorities.includes("ROLE_ADMIN") && user) && (

              <IconButton
                  size="large"
                  edge="start"
                  color="inherit"
                  aria-label="Mis compras"
                  sx={{ mr: 2 }}
                  onClick ={ventasClick}
                    ><ReceiptIcon/>
              </IconButton>  
        )} 
          
        
          <Link to="/" style={{ color: '#FFF', textDecoration: 'inherit'}}>
            <Typography variant="h6" component="div">
              Carrito UM
           </Typography>          
          </Link>
          
          <div style={{flexGrow: 1}}/>
          <Typography variant="h6" component="div">
            Hola {user ? user.firstName : "Invitado"}           
          </Typography>
          <div style={{flexGrow: 1}}/>
          <Link to="/signin" style={{ color: '#FFF', textDecoration: 'inherit'}}>
              <Button color="inherit" onClick ={handleClick}>
                {user ? "Salir" : "Ingresar"}
              </Button>
          </Link>          

          {(!user?.authorities.includes("ROLE_ADMIN") && user) && (
            <Link to="/checkout" style={{ color: '#FFF', textDecoration: 'inherit'}}>
            <IconButton aria-label="Carrito" color="inherit">
                <Badge badgeContent={getCantidadProductos(carro)} color='secondary'>
                  <ShoppingCart fontSize ="large"/>
                </Badge>             
            </IconButton>
            </Link>
          )}          
      
          
        </Toolbar>
      </AppBar>
    </Box>
  );
}
