import React, { useState } from 'react';

const CounterApp = ({ value }) => {

    const [counter, setCounter] = useState(value);

    const handlerSumaUno = () => {
        setCounter(counter + 1);
    };

    const handlerRestaUno = () => {
        setCounter(counter - 1);
    };
    
    // <button onClick={ () => {setCounter(counter + 1)} }>+1</button>
    return(
        <>
            <h1>Hola Mundo</h1>
            <p>{ counter }</p>
            <button onClick={ handlerSumaUno }>+1</button>
            <button onClick={ handlerRestaUno }>-1</button>
        </>
    );
};

export default CounterApp;
