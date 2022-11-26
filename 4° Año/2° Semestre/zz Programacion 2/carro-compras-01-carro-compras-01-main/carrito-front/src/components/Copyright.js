import * as React from 'react';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';


export default function Copyright(props) {
    return (
      <Typography variant="body2" color="text.secondary" align="center" {...props} >
        {'Copyright © '}
        <Link color="inherit" href="https://github.com/um-programacion-II/carro-compras-01-carro-compras-01">
          Carrito UM - Soria y Marotta
        </Link>{' '}
        {new Date().getFullYear()}
        {'.'}
      </Typography>
    );
  }