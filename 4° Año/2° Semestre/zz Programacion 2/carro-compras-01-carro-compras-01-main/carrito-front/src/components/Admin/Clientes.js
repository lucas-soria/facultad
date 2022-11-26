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
import UpdateUser from './UpdateUser';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { actionTypes, updateUser } from '../../reducer';

const Clientes = ({setReporte}) =>{

    const [expanded, setExpanded] = React.useState(false);
    const [{allVentas,allUsers,tokenProducto,tokenCarrito},dispatch] = useStateValue();

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
    const[userEdit,setUserEdit] = React.useState();;
    
    const handleOpen = (user) => {
        setUserEdit(user)
        setOpen(true);
    };
    const handleClose = () => {
    setOpen(false);
    };
  
    const handleChange = (panel) => (event, isExpanded) => {
      setExpanded(isExpanded ? panel : false);
    };

    const editUser = (user) =>{
        setReporte(<UpdateUser userEdit={user} setReporte={setReporte}></UpdateUser>)
    };
  
    const remove = async(userEdit) =>{
        const userData = {
            "activated": false,
            "authorities": userEdit.authorities,
            "createdBy": userEdit.createdBy,
            "createdDate": userEdit.createdDate,
            "email": userEdit.email,
            "firstName": userEdit.firstName,
            "id": userEdit.id,
            "imageUrl": userEdit.imageUrl,
            "langKey": userEdit.langKey,
            "lastModifiedBy": userEdit.lastModifiedBy,
            "lastModifiedDate": userEdit.lastModifiedDate,
            "lastName": userEdit.lastName,
            "login": userEdit.login
        }
    
        const newUsers = await updateUser(tokenProducto,tokenCarrito,userData);
    
    
        dispatch({
            type: actionTypes.UPDATE_USER,
            allUsers : newUsers
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
          <h1>Clientes</h1>
  
          {!allUsers? []: allUsers.map(user => {
  
                  const idPanel = 'panel'+user.id;
                  const bhcontent = idPanel + 'bh-content';
                  const bhHeader = idPanel +"bh-header";  
                  const ventasUser = allVentas.filter(venta =>venta.cliente.id === user.id)
            if(!user.authorities.includes("ROLE_ADMIN")){    
              return(
              <Accordion xs={12} expanded={expanded === idPanel} onChange={handleChange(idPanel)}>
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls= {bhcontent}
                id={bhHeader}
              >
                <Typography sx={{ width: '33%', flexShrink: 0 }}>
                  Usuario: {user.login}
                </Typography>
                <IconButton aria-label="Editar" onClick={() => editUser(user)}>
                    <EditIcon fontsize='large'/>
                </IconButton>
                <IconButton aria-label="Eliminar" onClick={()=>handleOpen(user)}>
                    <Delete fontsize='large'/>
                </IconButton>
                
              </AccordionSummary>
              <AccordionDetails> 
                <Typography>Nombre: {user.firstName} {user.lastName}</Typography>
                <Typography>Email: {user.email}</Typography>
                <Typography>Habilitado: {user.activated.toString()}</Typography>
                  <br/>
                  <h5>Ventas</h5>            
              {ventasUser.map(v =>(
                          <Typography id = {v.id}>
                          Id: {v.id} &emsp;                           
                          Total: ${v.total} &emsp; 
                          Fecha: {v.fecha} &emsp;   
                        </Typography>
              ))}    
              </AccordionDetails>
            </Accordion>
          )  }        
           })}

            <Modal
                open={open}
                onClose={handleClose}
            >
            <Box sx={{ ...style, width: 400 }}>
          <h2 id="modal-title">¡Alerta!</h2>
          <br/>
          <p id="modal-description">
            El Cliente no sera eliminado, solo se dehabilitara
          </p>
          <Button onClick={() => remove(userEdit)}>Aceptar</Button>
            </Box>
            </Modal>
           </Container>           
        </Box>
        
      
    );


}

export default Clientes;