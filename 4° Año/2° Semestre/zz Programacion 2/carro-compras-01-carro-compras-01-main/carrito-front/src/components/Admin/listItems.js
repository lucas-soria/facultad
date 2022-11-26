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
import VentasFiltradas from './VentasFiltradas';
import { useStateValue } from '../../StateProvider';
import { actionTypes, filtrarVentas30, filtrarVentasEntreFechas, filtrarVentasEsteAño, filtrarVentasEsteMes } from '../../reducer';
import DesktopDatePicker from '@mui/lab/DesktopDatePicker';
import TextField from '@mui/material/TextField';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import Stack from '@mui/material/Stack';
import FilterAltIcon from '@mui/icons-material/FilterAlt';


export const mainListItems = (setReporte) => {

  return(<div>
    
    <ListItem button onClick={e => setReporte(<Ventas></Ventas>)}>
      <ListItemIcon>
        <ShoppingCartIcon />
      </ListItemIcon>
      <ListItemText primary="Ventas" />
    </ListItem>

    <ListItem button onClick={e => setReporte(<Clientes setReporte={setReporte}></Clientes>)}>
      <ListItemIcon>
        <PeopleIcon />
      </ListItemIcon>
      <ListItemText primary="Clientes" />
    </ListItem>


    <ListItem button onClick={e => setReporte(<Distribuidores setReporte={setReporte}></Distribuidores>)}>
      <ListItemIcon>
        <LocalShippingIcon />
      </ListItemIcon>
      <ListItemText primary="Distribuidores" />
    </ListItem>
    <ListItem button onClick={e => setReporte(<AdminProductos setReporte={setReporte}></AdminProductos>)}>
      <ListItemIcon>
        <StyleIcon />
      </ListItemIcon>
      <ListItemText primary="Productos" />
    </ListItem>
  </div>);
  
}

export const SecondaryListItems = (setReporte) => {

  const [{allVentas,ventasFiltradas,tokenCarrito},dispatch] = useStateValue();

  const [fechaInicio, setFechaInicio] = React.useState(new Date());
  const [fechaFin, setFechaFin] = React.useState(new Date());

  const handleChangeFechaInicio = (newValue) => {
    setFechaInicio(newValue);
  };

  const handleChangeFechaFin = (newValue) => {
    setFechaFin(newValue);
  };

  const handleEvent = async(n) =>{

    let ventas = null;
    
    switch(n){
        case 1 :
          ventas = await filtrarVentas30(tokenCarrito);        
          break;
        case 2 :
          ventas = await filtrarVentasEsteMes(tokenCarrito);        
          break;
        case 3 :
          ventas = await filtrarVentasEsteAño(tokenCarrito);        
          break;
        case 4 :
            let fi = fechaInicio.toISOString().split('T')[0]
            let ff = fechaFin.toISOString().split('T')[0]
            ventas = await filtrarVentasEntreFechas(tokenCarrito,fi,ff);        
            break;
        
        default : ventas = allVentas;
    
    }

    dispatch({
      type: actionTypes.FILTRAR_VENTAS,
      ventasFiltradas: ventas
    });
    
    setReporte(<VentasFiltradas></VentasFiltradas>)

  }

  return(
  <div>
    <ListSubheader inset>Reportes</ListSubheader>
    <ListItem button onClick={e => handleEvent(1)}>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Últimos 30 días" />
    </ListItem>
    <ListItem button onClick={e => handleEvent(2)}>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Este mes" />
    </ListItem>
    <ListItem button onClick={e => handleEvent(3)}>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Último año" />
    </ListItem>
    <ListSubheader inset>Filtrar por fechas</ListSubheader>
    <LocalizationProvider dateAdapter={AdapterDateFns}>
      <Stack spacing={3}>
    <DesktopDatePicker
          label="Fecha Inicio"
          mask="____-__-__"
          inputFormat="yyyy-MM-dd"
          value={fechaInicio}
          onChange={handleChangeFechaInicio}
          renderInput={(params) => <TextField {...params} />}
    />
    <DesktopDatePicker
          label="Fecha Fin"
          mask="____-__-__"
          inputFormat="yyyy-MM-dd"
          value={fechaInicio}
          onChange={handleChangeFechaFin}
          renderInput={(params) => <TextField {...params} />}
    />
    </Stack>
    </LocalizationProvider>
    <ListItem button onClick={e => handleEvent(4)}>
      <ListItemIcon>
        <FilterAltIcon />
      </ListItemIcon>
      <ListItemText primary="Filtrar" />
    </ListItem>

  </div>);
};