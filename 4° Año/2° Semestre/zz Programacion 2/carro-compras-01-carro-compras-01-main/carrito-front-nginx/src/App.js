import './App.css';
import NavBar from './components/NavBar';
import Productos from './components/productos';
import {Routes,BrowserRouter as Router, Route} from "react-router-dom"
import CheckOutPage from './components/CheckOutPage';
import SignIn from './components/SingIn';
import SignUp from './components/SignUp';
import { useEffect } from 'react';
import ComprarTarjeta from './components/ComprarTarjeta'; 
import Copyright from './components/Copyright';
import Dashboard from './components/Admin/Dashboard';
import {useNavigate} from 'react-router-dom';
import { useStateValue } from './StateProvider';
import VentasUsuario from './components/VentasUsuario';

function App() {
  
  useEffect(()=>{
    //Aca deberia obtener el nombre del usuario para devolverlo, usando el dispatch

  },[])

  const [{user},dispatch] = useStateValue();

  if(!user){
      return(
        <Router>
        <div className="App">
        <NavBar/>
        <Routes>   
            <Route path="/signin" element = {<SignIn/>}/>
        
            <Route path="/signup" element = {<SignUp/>}/>   

            <Route path="/" element = {<SignIn/>}/>              
        </Routes>
        <Copyright sx={{ mt: 8, mb: 4 }}></Copyright>
      </div>    
      </Router>

      );
  }
  else
  {

    if(user.authorities.includes("ROLE_ADMIN")){
      return (
        <Router>
          <div className="App">
          <NavBar/>
          <Routes>               
              <Route path="/" element = {<Dashboard/>}/>                        
          </Routes>
          <Copyright sx={{ mt: 8, mb: 4 }}></Copyright>
        </div>    
        </Router>    
      );
    }
    else
    {
      return (
        <Router>
          <div className="App">
          <NavBar/>
          <Routes>
              <Route path="/checkout" element = {<CheckOutPage/>}/>
              
              <Route path="/comprar" element = {<ComprarTarjeta/>}/>
              
              <Route path="/misVentas" element = {<VentasUsuario/>}/>
               
              <Route path="/" element = {<Productos/>}/>
                        
          </Routes>
          <Copyright sx={{ mt: 8, mb: 4 }}></Copyright>
        </div>    
        </Router>    
      );
    } 
  }  
}

export default App;
