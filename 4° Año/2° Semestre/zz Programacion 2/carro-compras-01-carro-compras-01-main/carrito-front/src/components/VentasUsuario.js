import * as React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Box from '@mui/material/Box';
import { useStateValue } from '../StateProvider';


export default function VentasUsuario() {
  const [expanded, setExpanded] = React.useState(false);
  const [{ventas,tokenCarrito,productos},dispatch] = useStateValue();

  const handleChange = (panel) => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };


  return (
    <div>
    <Box sx={{ flexGrow: 1, padding:'50px'}}>
        <h1>Mis Compras</h1>

        {!ventas ? []: ventas.map(venta => {

                const idPanel = 'panel'+venta.id;
                const bhcontent = idPanel + 'bh-content';
                const bhHeader = idPanel +"bh-header";
                
            return(
            <Accordion expanded={expanded === idPanel} onChange={handleChange(idPanel)}>
            <AccordionSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls= {bhcontent}
              id={bhHeader}
            >
              <Typography sx={{ width: '33%', flexShrink: 0 }}>
                Total: {venta.total}
              </Typography>
              <Typography sx={{ color: 'text.secondary' }}>Fecha: {venta.fecha}</Typography>
            </AccordionSummary>
            <AccordionDetails>
            {venta.informacion.map(p =>(
                        <Typography>
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
      </Box>
    </div>
  );
}