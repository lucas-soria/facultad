import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Grid from '@mui/material/Grid';
import { useStateValue } from '../StateProvider';
import { getTotal } from '../reducer';


export default function Comprar() {

  const [{carro,user,tokenProducto,tokenCarrito},dispatch] = useStateValue();

  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom>
        <br/>Resumen de Compra
      </Typography>
      <List disablePadding>
        {carro?.map((product) => (
          <ListItem key={product.informacion.nombre} sx={{ py: 1, px: 0 }}>
            <ListItemText primary={product.informacion.nombre} secondary={product.informacion.descripcion} />
            <Typography variant="body2">${product.informacion.precio} x {product.cantidad}</Typography>
          </ListItem>
        ))}

        <ListItem sx={{ py: 1, px: 0 }}>
          <ListItemText primary="Total" />
          <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
            ${getTotal(carro)}
          </Typography>
        </ListItem>
      </List>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }}>
            Detalles de Envío
          </Typography>
          <Typography gutterBottom>{user.firstName} {user.lastName}</Typography>
        </Grid>        
      </Grid>
    </React.Fragment>
  );
}