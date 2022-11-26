import * as React from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ListSubheader from '@mui/material/ListSubheader';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import PeopleIcon from '@mui/icons-material/People';
import AssignmentIcon from '@mui/icons-material/Assignment';
import LocalShippingIcon from '@mui/icons-material/LocalShipping';
import StyleIcon from '@mui/icons-material/Style';
import Ventas from './Ventas'
import Clientes from './Clientes'
import Distribuidores from './Distribuidores'
import AdminProductos from './AdminProductos';


export const mainListItems = (setReporte) => {

  return(<div>
    
    <ListItem button onClick={e => setReporte(<Ventas></Ventas>)}>
      <ListItemIcon>
        <ShoppingCartIcon />
      </ListItemIcon>
      <ListItemText primary="Ventas" />
    </ListItem>

    <ListItem button onClick={e => setReporte(<Clientes></Clientes>)}>
      <ListItemIcon>
        <PeopleIcon />
      </ListItemIcon>
      <ListItemText primary="Clientes" />
    </ListItem>


    <ListItem button onClick={e => setReporte(<Distribuidores></Distribuidores>)}>
      <ListItemIcon>
        <LocalShippingIcon />
      </ListItemIcon>
      <ListItemText primary="Distribuidores" />
    </ListItem>
    <ListItem button onClick={e => setReporte(<AdminProductos></AdminProductos>)}>
      <ListItemIcon>
        <StyleIcon />
      </ListItemIcon>
      <ListItemText primary="Productos" />
    </ListItem>
  </div>);
  
}

export const secondaryListItems = (
  <div>
    <ListSubheader inset>Reportes</ListSubheader>
    <ListItem button>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Esta semana" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Este mes" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Último año" />
    </ListItem>
  </div>
);