import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import AddShopingCart from '@mui/icons-material/AddShoppingCart';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { actionTypes, seleccionarProducto ,getProductosSeleccionadosCarrito} from '../reducer';
import { useStateValue } from '../StateProvider';

const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
  marginLeft: 'auto',
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function Producto({producto : {id, nombre, descripcion, precio, imagen}}) {
  const [expanded, setExpanded] = React.useState(false);
  const [{carro,tokenProducto,tokenCarrito},dispatch] = useStateValue();
  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const addToCart = async () =>{

    await seleccionarProducto(id,tokenCarrito);

    const carrito = await getProductosSeleccionadosCarrito(tokenProducto,tokenCarrito);

    dispatch({
      type: actionTypes.ADD_TO_CART,      
      carro : carrito    
    })
  };

  return (
    <Card sx={{ maxWidth: 345, marginTop:'50px', }}>
      <CardHeader
        action={
          <Typography
          variant = 'h5'
          color='textSecondary'
          >${precio}
          </Typography>          
        }
        title={nombre}
        subheader="En stock"
      />
      <CardMedia
        component="img"
        height="194"
        image= {"./images/" + imagen}
        alt="generico"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
         {descripcion}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="Añadir al carrito" onClick={addToCart}>
          <AddShopingCart fontSize='large'/>
        </IconButton>
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>          
          <Typography paragraph>
          {descripcion}
          </Typography>       
        </CardContent>
      </Collapse>
    </Card>
  );
}
