import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Toolbar from '@mui/material/Toolbar';
import Paper from '@mui/material/Paper';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Comprar from './Comprar';
import {useNavigate} from 'react-router-dom';
import { getProductosSeleccionadosCarrito, realizarVenta } from '../reducer';
import { useStateValue } from '../StateProvider';
import { actionTypes } from '../reducer';

const steps = ['Comprar'];

function getStepContent(step) {
  switch (step) {
    case 0:
      return <Comprar/>;
    default:
      throw new Error('Error');
  }
}

const theme = createTheme();

export default function ComprarTarjeta() {
  const [activeStep, setActiveStep] = React.useState(0);
  const navigate = useNavigate(); 
  const [{carro,tokenProducto,tokenCarrito},dispatch] = useStateValue();

  const handleNext = async () => {
    setActiveStep(activeStep + 1);
    await realizarVenta(tokenCarrito)
    const currentCarro = await getProductosSeleccionadosCarrito(tokenProducto,tokenCarrito);

    dispatch({
      type: actionTypes.ADD_TO_CART,
      carro: currentCarro,
    });


  };

  const handleBack = () => {
    navigate("/checkout")
  };


  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
     
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <Paper variant="outlined" sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}>
          <Typography component="h1" variant="h4" align="center">
            Finalizar la compra
          </Typography>
          <React.Fragment>
            {activeStep === steps.length ? (
              <React.Fragment>
                <Typography variant="h5" gutterBottom>
                 <br/>&emsp;&emsp;&ensp;Gracias por comprar en Carrito UM.
                </Typography>
              </React.Fragment>
            ) : (
              <React.Fragment>
                {getStepContent(activeStep)}
                <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                  
                    <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                      Volver
                    </Button>                

                  <Button
                    variant="contained"
                    onClick={handleNext}
                    sx={{ mt: 3, ml: 1 }}
                  > Finalizar
                  </Button>
                </Box>
              </React.Fragment>
            )}
          </React.Fragment>
        </Paper>
      </Container>
    </ThemeProvider>
  );
}