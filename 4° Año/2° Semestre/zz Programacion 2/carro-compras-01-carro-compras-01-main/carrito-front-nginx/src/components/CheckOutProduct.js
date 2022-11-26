import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Delete from '@mui/icons-material/Delete';
import { ClassNames } from '@emotion/react';
import { useStateValue } from '../StateProvider';
import { actionTypes, eliminarProductoSeleccionado, getProductosSeleccionadosCarrito } from '../reducer';


export default function CheckOutProduct({producto : {id, nombre, descripcion, precio,imagen},cantidad, idProductoSeleccionado}) {
  const [{carro,tokenProducto,tokenCarrito},dispatch] = useStateValue();


  const remove = async () =>{

    const cantidadRestada = cantidad - 1 ;    
    await eliminarProductoSeleccionado(idProductoSeleccionado,tokenCarrito,cantidadRestada);

    const carrito = await getProductosSeleccionadosCarrito(tokenProducto,tokenCarrito);

    dispatch({
      type: actionTypes.ADD_TO_CART,      
      carro : carrito    
    })
  };

  return (
    <Card sx={{ maxWidth: 345, marginTop:'50px', }}>
      <CardHeader
        action={
          <Typography
          variant = 'h6'
          color='textSecondary'
          >${precio}
          </Typography>          
        }
        title={nombre}
      />
      <CardMedia
        component="img"
        height="194"
        image={"./images/" + imagen}
        alt="generico"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
         {descripcion}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="Eliminar" onClick={remove}>
          <Delete fontSize='large'/>
        </IconButton> 
        <Typography variant="body2" color="text.secondary">
         X {cantidad}
        </Typography>
      </CardActions>
      
    </Card>
  );
}
