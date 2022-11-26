import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Producto from './producto';
import { useStateValue } from '../StateProvider';



const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));


export default function Productos() {
    
  const [{productosHabilitados},dispatch] = useStateValue();    
  
    return (
    <Box sx={{ flexGrow: 1, padding:'50px'}}>
      <Grid container spacing={2}>
          {
              !productosHabilitados ? [] : productosHabilitados.map(p =>(
                <Grid item xs={12} sm ={6} md={4} lg={3}>
                    <Producto key={p.id} producto={p}/>
                </Grid>
              ))
          }             
      </Grid>     
    </Box>
  );
}
