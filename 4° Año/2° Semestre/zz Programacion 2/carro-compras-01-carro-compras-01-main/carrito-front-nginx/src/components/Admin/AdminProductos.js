import React from "react";
import TarjetaProducto from "./TarjetaProducto";
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import { useStateValue } from '../../StateProvider';

const AdminProductos = () =>{

  const [{productos},dispatch] = useStateValue();        

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

          <Grid container spacing={2}>
          {
              !productos ? [] : productos.map(p =>(
                <Grid item xs={12} sm ={6} md={4}>
                    <TarjetaProducto key={p.id} producto={p}/>
                </Grid>
              ))
          }             
          </Grid>
            
          </Container>
        </Box>
);

}

export default AdminProductos;