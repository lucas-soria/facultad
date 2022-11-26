import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import EditIcon from '@mui/icons-material/Edit';
import Delete from '@mui/icons-material/Delete';



export default function TarjetaProducto({producto : {id, nombre, descripcion, precio, imagen,habilitado}}) {
  const editProducto = () =>{
   
  };

  const remove = () =>{
   
};

  return (
    <Card sx={{ maxWidth: 345, marginTop:'50px', }}>
      <CardHeader
        action={
          <Typography
          variant = 'h5'
          color='textSecondary'
          >${precio}
          </Typography>          
        }
        title={nombre}
        subheader="En stock"
      />
      <CardMedia
        component="img"
        height="194"
        image= {"./images/" + imagen}
        alt="generico"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
         {descripcion} <br/> Habilitado: {habilitado.toString()} <br/> ID: {id}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="Añadir al carrito" onClick={editProducto}>
          <EditIcon fontsize='large'/>
        </IconButton>
        <IconButton aria-label="Eliminar" onClick={remove}>
          <Delete fontsize='large'/>
        </IconButton> 
      </CardActions>
    </Card>
  );
}
