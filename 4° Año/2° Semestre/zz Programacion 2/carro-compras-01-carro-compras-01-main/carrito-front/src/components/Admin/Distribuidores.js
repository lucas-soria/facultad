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
import EditIcon from '@mui/icons-material/Edit';
import Delete from '@mui/icons-material/Delete';
import IconButton from '@mui/material/IconButton';
import UpdateDistribuidor from './UpdateDistribuidor';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { actionTypes, updateDistribuidor } from '../../reducer';
import ControlPointIcon from '@mui/icons-material/ControlPoint';
import AddDistribuidor from './AddDistribuidor';
import { Grid } from '@mui/material';

const Distribuidores = ({setReporte}) =>{
    const [expanded, setExpanded] = React.useState(false);
    const [{allDistribuidores,productos,tokenProducto},dispatch] = useStateValue();

    const style = {
      position: 'absolute',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)',
      width: 400,
      bgcolor: 'background.paper',
      border: '2px solid #000',
      boxShadow: 24,
      pt: 2,
      px: 4,
      pb: 3,
  };

  const [open, setOpen] = React.useState(false);
  const[distEdit,setDistEdit] = React.useState();

  const handleOpen = (dist) => {
    setDistEdit(dist)
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  
    const handleChange = (panel) => (event, isExpanded) => {
      setExpanded(isExpanded ? panel : false);
    };

    const editDistribuidor = (dist) =>{
      setReporte(<UpdateDistribuidor distEdit={dist} setReporte={setReporte}></UpdateDistribuidor>)
    };

    const addDistribuidor = () =>{
      setReporte(<AddDistribuidor setReporte={setReporte}></AddDistribuidor>)
    };
  
    const remove = async (distEdit) =>{
      const distData = {
        "id": distEdit.id,
        "nombre": distEdit.nombre,
        "descripcion": distEdit.descripcion,
        "habilitado": false        
      }

      const newDist = await updateDistribuidor(tokenProducto,distData);

      dispatch({
          type: actionTypes.UPDATE_DIST,
          allDistribuidores : newDist
      });

      handleClose()
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
          <Grid container>
          <Typography variant="h4">Distribuidores</Typography>                   
              <IconButton aria-label="Agregar" onClick={() => addDistribuidor()}>
                  <ControlPointIcon fontSize='large'/>
              </IconButton>
          </Grid> 
            <br/>
  
          {!allDistribuidores? []: allDistribuidores.map(dist => {
  
                  const idPanel = 'panel'+dist.id;
                  const bhcontent = idPanel + 'bh-content';
                  const bhHeader = idPanel +"bh-header";  
                  const productosDist = productos.filter(p =>p.distribuidor.id === dist.id)
            
              return(
              <Accordion xs={12} expanded={expanded === idPanel} onChange={handleChange(idPanel)}>
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls= {bhcontent}
                id={bhHeader}
              >
                <Typography sx={{ width: '33%', flexShrink: 0 }} variant="h5">
                  {dist.nombre}
                </Typography>
                <IconButton aria-label="Editar" onClick={() => editDistribuidor(dist)}>
                    <EditIcon fontSize='large'/>
                </IconButton>
                <IconButton aria-label="Eliminar" onClick={()=>handleOpen(dist)}>
                    <Delete fontSize='large'/>
                </IconButton>
                
              </AccordionSummary>
              <Typography sx={{ color: 'text.secondary' }}>&emsp;Descripcion: {dist.descripcion}</Typography>
              <Typography sx={{ color: 'text.secondary' }}>&emsp;Habilitado: {dist.habilitado.toString()}</Typography>
              <AccordionDetails>  
                  <h5>Productos</h5>            
              {productosDist.map(p =>(
                           <Typography id = {p.id}>
                           Id: {p.id} &emsp; 
                           Nombre: {p.nombre} &emsp;
                           Precio: {p.precio} &emsp; 
                         </Typography>
              ))}    
              </AccordionDetails>
            </Accordion>
          )         
           })}

            <Modal
                open={open}
                onClose={handleClose}
            >
            <Box sx={{ ...style, width: 400 }}>
                <h2 id="modal-title">¡Alerta!</h2>
                <br/>
                <p id="modal-description">
                  El Distribuidor no sera eliminado, solo se dehabilitara
                </p>
          <Button onClick={() => remove(distEdit)}>Aceptar</Button>
            </Box>
            </Modal>
           </Container>
        </Box>
      
    );


}

export default Distribuidores;