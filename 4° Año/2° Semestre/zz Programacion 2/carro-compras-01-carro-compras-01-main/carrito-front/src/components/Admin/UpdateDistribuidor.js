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
import { actionTypes, updateDistribuidor, updateUser } from '../../reducer';
import Clientes from './Clientes';
import Distribuidores from './Distribuidores';

const theme = createTheme();

export default function UpdateDistribuidor({distEdit,setReporte}) {

  const [activate, setActivate] = React.useState(distEdit.habilitado );
  const [{user,tokenProducto,tokenCarrito},dispatch] = useStateValue();
  
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    const distData = {
            "id": distEdit.id,
            "nombre": data.get('firstName'),
            "descripcion": data.get('descripcion'),
            "habilitado": activate        
    }

    const newDist = await updateDistribuidor(tokenProducto,distData);


    dispatch({
        type: actionTypes.UPDATE_DIST,
        allDistribuidores : newDist
    });

    setReporte(<Distribuidores setReporte={setReporte}></Distribuidores>)
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
                  defaultValue={distEdit.nombre}
                  autoFocus
                  label="Nombre"
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="descripcion"
                  defaultValue={distEdit.descripcion}
                  name="descripcion"
                  label="Descripcion"
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