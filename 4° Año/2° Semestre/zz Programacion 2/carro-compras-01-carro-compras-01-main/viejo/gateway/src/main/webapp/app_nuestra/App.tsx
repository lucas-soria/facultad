import React from 'react';
import Carrito from "./components/Carrito"
import { useAppSelector } from 'app/config/store';


const App = () => {
    const account = useAppSelector(state => state.authentication.account);

    return(
        <>
            <h1>Bienvenidos a UM Compras {account.login} </h1>
            <hr/>
            <Carrito/>

        </>

    );
}

export default App;
