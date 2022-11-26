import * as React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Box from '@mui/material/Box';
import { useStateValue } from '../../StateProvider'; 
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';

const Ventas = () =>{
    const [expanded, setExpanded] = React.useState(false);
    const [{allVentas,productos,allUsers},dispatch] = useStateValue();
  
    const handleChange = (panel) => (event, isExpanded) => {
      setExpanded(isExpanded ? panel : false);
    };
  
  
    return (      
      <Box component="main"
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
          <h1>Ventas</h1>
  
          {!allVentas ? []: allVentas.map(venta => {
  
                  const idPanel = 'panel'+venta.id;
                  const bhcontent = idPanel + 'bh-content';
                  const bhHeader = idPanel +"bh-header";
                  
              return(
              <Accordion xs={12} expanded={expanded === idPanel} onChange={handleChange(idPanel)}>
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls= {bhcontent}
                id={bhHeader}
              >
                <Typography sx={{ width: '33%', flexShrink: 0 }}>
                  Usuario: {allUsers.filter(user => user.id == venta.cliente.id)[0].login}
                </Typography>
                <Typography sx={{ width: '33%', flexShrink: 0 }}>
                  Total: {venta.total}
                </Typography>
                <Typography sx={{ color: 'text.secondary' }}>Fecha: {venta.fecha}</Typography>
              </AccordionSummary>
              <AccordionDetails>              
              {venta.informacion.map(p =>(
                          <Typography id = {p.id}>
                          Id: {p.producto} &emsp; 
                          Nombre: {productos.filter(prod => prod.id === p.producto)[0].nombre} &emsp;
                          Precio: {p.precio} &emsp; 
                          Cantidad: {p.cantidad} &emsp; 
                          Total: {p.precio * p.cantidad}  
                        </Typography>
              ))}    
              </AccordionDetails>
            </Accordion>
          )          
           })}
           </Container>
        </Box>
      
    );
  }

export default Ventas;