import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {Link as RLink} from 'react-router-dom';
import { getProductosSeleccionadosCarrito, getUser, logIN, fetchProductos, fetchProductosHabilitados } from '../reducer';
import { useStateValue } from '../StateProvider';
import {useNavigate} from 'react-router-dom';
import { actionTypes } from '../reducer';

const theme = createTheme();

export default function SignIn() {

  const [{tokenProducto,tokenCarrito,user},dispatch] = useStateValue();
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const [tokenP,tokenC] = await logIN(data.get('username'),data.get('password'));
    const user = await getUser(tokenC);
    const currentCarro = await getProductosSeleccionadosCarrito(tokenP,tokenC);
    const productos = await fetchProductos(tokenP);
    const productosHabilitados = await fetchProductosHabilitados(tokenP);
    
    dispatch({
      type: actionTypes.SIGN_IN,
      user: user,
      carro: currentCarro,
      tokenProducto: tokenP,
      tokenCarrito: tokenC,
      productos: productos,
      productosHabilitados: productosHabilitados
    });
    
    navigate("/")
  };

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
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="username"
              label="Nombre de Usuario"
              name="username"
              autoComplete="username"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item style={{ width: "100%", display: 'flex', justifyContent: 'center' }}> 
                <RLink to="/signup">
                  {"¿No tienes una cuenta? Registrate aquí"}
                </RLink>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}