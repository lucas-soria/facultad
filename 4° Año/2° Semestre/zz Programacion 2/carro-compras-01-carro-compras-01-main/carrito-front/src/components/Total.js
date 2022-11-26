import { Button } from "@mui/material";
import React from "react";
import { getTotal, getCantidadProductos } from "../reducer";
import { useStateValue } from '../StateProvider';
import {Link} from 'react-router-dom';


const Total = () => {
    const [{carro},dispatch] = useStateValue();
    
    return(
        <div>
            <h5>Total Items: {getCantidadProductos(carro)} </h5>
            <h5>${getTotal(carro)}</h5>
            <Link to="/comprar" style={{ color: '#FFF', textDecoration: 'inherit'}}>
                <Button variant="contained" color="secondary">Proceder al pago</Button>
            </Link>            
        </div>
    );
}

export default Total