import React from "react";
import { Grid, Typography } from "@mui/material"
import CheckOutProduct from "./CheckOutProduct"
import Total from "./Total";
import { useStateValue } from '../StateProvider';


const CheckOutPage = () =>{
    const [{carro},dispatch] = useStateValue();

    function FilaAnidada(){ 
        return(      
            <>
            {carro?.map((producto)=> {
                                
                if (producto.cantidad>0) {
                    return(
                    <Grid item xs={12} sm={8} md={6} lg={4} container spacing={2} align='center'>
                    <CheckOutProduct key={producto.id} producto={producto.informacion} cantidad={producto.cantidad} idProductoSeleccionado={producto.id} ></CheckOutProduct>
                    </Grid>  )
                }                  
            }                                      
            )}
            </> );              
    }

return(
<div>
    <Grid container spacing={3} padding={'50px'} align='center'>
        <Grid item xs={12}>
            <Typography align='center' gutterBottom variant='h4'>
                    CheckOut
            </Typography>
        </Grid>
        <Grid item xs={12} sm={8} md={9} container spacing={2} >
            <FilaAnidada/>
        </Grid>
        <Grid item xs={12}  sm={4} md={3} >
            <Typography align='center' gutterBottom variant='h4'>
                <Total></Total>
            </Typography>
        </Grid>
    </Grid>
</div>
);
}

export default CheckOutPage;
