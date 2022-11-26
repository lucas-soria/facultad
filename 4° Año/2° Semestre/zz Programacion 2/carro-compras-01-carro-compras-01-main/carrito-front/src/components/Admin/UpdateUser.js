import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import ModeEditOutlineOutlinedIcon from '@mui/icons-material/ModeEditOutlineOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {Link as RLink, useNavigate} from 'react-router-dom';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import { useStateValue } from '../../StateProvider';
import { actionTypes, updateUser } from '../../reducer';
import Clientes from './Clientes';

const theme = createTheme();

export default function UpdateUser({userEdit,setReporte}) {

  const navigate = useNavigate(); //Da acceso al historial de paginas que visitamos, entonces puedo ir a la pagina que visite anteriormente
  const [activate, setActivate] = React.useState(userEdit.activated );
  const [{user,tokenProducto,tokenCarrito,allUsers},dispatch] = useStateValue();
  
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const tiempoTranscurrido = Date.now();
    const hoy = new Date(tiempoTranscurrido).toISOString();

    const userData = {
        "activated": activate,
        "authorities": userEdit.authorities,
        "createdBy": userEdit.createdBy,
        "createdDate": userEdit.createdDate,
        "email": data.get('email'),
        "firstName": data.get('firstName'),
        "id": userEdit.id,
        "imageUrl": userEdit.imageUrl,
        "langKey": userEdit.langKey,
        "lastModifiedBy": user.login,
        "lastModifiedDate": hoy,
        "lastName": data.get('lastName'),
        "login": data.get('login')
    }

    const newUsers = await updateUser(tokenProducto,tokenCarrito,userData);


    dispatch({
        type: actionTypes.UPDATE_USER,
        allUsers : newUsers
    });

    setReporte(<Clientes setReporte={setReporte}></Clientes>)
  };
  

  const handleActivated =() => {
        setActivate(!activate)
  }


  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <ModeEditOutlineOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Editar
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  autoComplete="given-name"
                  name="firstName"
                  fullWidth
                  id="firstName"
                  defaultValue={userEdit.firstName}
                  autoFocus
                  label="Nombre"
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="lastName"
                  defaultValue={userEdit.lastName}
                  name="lastName"
                  autoComplete="family-name"
                  label="Apellido"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  defaultValue={userEdit.email}
                  name="email"
                  autoComplete="email"
                  label="Email"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="login"
                  defaultValue={userEdit.login}
                  id="login"
                  label="UserName"
                />
              </Grid>

              <Grid item xs={12}>
              <FormControlLabel control={<Checkbox
                                checked={activate}
                                onChange={()=>handleActivated()}
                                inputProps={{ 'aria-label': 'controlled' }}
                                />} label="Habilitado" />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Aceptar
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}