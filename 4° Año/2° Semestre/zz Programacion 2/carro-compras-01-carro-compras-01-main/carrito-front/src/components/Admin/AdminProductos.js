import React from "react";
import TarjetaProducto from "./TarjetaProducto";
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import { useStateValue } from '../../StateProvider';
import AddProducto from "./AddProducto";
import ControlPointIcon from '@mui/icons-material/ControlPoint';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';


const AdminProductos = ({setReporte}) =>{

  const [{productos},dispatch] = useStateValue();  
  const addProducto = () =>{
    setReporte(<AddProducto setReporte={setReporte}></AddProducto>)
  };

return(
        <Box
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[100]
                : theme.palette.grey[900],
            flexGrow: 1,
            height: '100vh',
            overflow: 'auto',
          }}
        >
          <Toolbar />
          <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
          <Grid container>
          <Typography variant="h4">Productos</Typography>                   
              <IconButton aria-label="Agregar" onClick={() => addProducto()}>
                  <ControlPointIcon fontSize='large'/>
              </IconButton>
          </Grid> 
            <br/>
          <Grid container spacing={2}>
          {
              !productos ? [] : productos.map(p =>(
                <Grid item xs={12} sm ={6} md={4}>
                    <TarjetaProducto key={p.id} producto={p} producto2={p} setReporte={setReporte}/>
                </Grid>
              ))
          }             
          </Grid>
            
          </Container>
        </Box>
);

}

export default AdminProductos;