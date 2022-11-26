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
import UpdateProducto from './UpdateProducto';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { actionTypes, updateProducto } from '../../reducer';
import { useStateValue } from '../../StateProvider'; 
import Box from '@mui/material/Box';



export default function TarjetaProducto({producto : {id, nombre, descripcion, precio, imagen,habilitado,cantidadVendidos,distribuidor},producto2,setReporte}) {
  
  const [{tokenProducto},dispatch] = useStateValue();
  
  const editProducto = (p) =>{
    setReporte(<UpdateProducto productoEdit={p}  setReporte={setReporte}></UpdateProducto>)
  };

  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    pt: 2,
    px: 4,
    pb: 3,
};

  const remove = async() =>{

    const productoData = {
      "id": id,
      "nombre": nombre,
      "descripcion": descripcion,
      "precio": precio,
      "cantidadVendidos": cantidadVendidos,
      "habilitado": false,
      "imagen": imagen,
      "distribuidor": {
        "id": distribuidor.id,
        "nombre": null,
        "descripcion": null,
        "habilitado": null
      }       
    }

    const newProducto = await updateProducto(tokenProducto,productoData);


    dispatch({
        type: actionTypes.UPDATE_PRODUCTOS,
        productos : newProducto
    });

    handleClose();
   
  };

const [open, setOpen] = React.useState(false);
const handleOpen = () => {
  setOpen(true);
};
const handleClose = () => {
setOpen(false);
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
         {descripcion} <br/> Habilitado: {habilitado.toString()} <br/> ID: {id} <br/> Vendidos: {cantidadVendidos}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="Añadir al carrito" onClick={() => editProducto(producto2)}>
          <EditIcon fontsize='large'/>
        </IconButton>
        <IconButton aria-label="Eliminar" onClick={()=>handleOpen()}>
          <Delete fontsize='large'/>
        </IconButton> 
      </CardActions>
      <Modal
                open={open}
                onClose={handleClose}
            >
            <Box sx={{ ...style, width: 400 }}>
          <h2 id="modal-title">¡Alerta!</h2>
          <br/>
          <p id="modal-description">
            El Producto no sera eliminado, solo se dehabilitara
          </p>
          <Button onClick={() => remove()}>Aceptar</Button>
            </Box>
            </Modal>
    </Card>
  );
}
