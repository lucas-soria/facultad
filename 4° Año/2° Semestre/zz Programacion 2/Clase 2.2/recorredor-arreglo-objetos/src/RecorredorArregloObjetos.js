import React, { useState } from 'react';

const RecorredorArregloObjetos = ({ objects, value }) => {

    const [index, setIndex] = useState(value);

    const handlerNextObject = () => {
        if (index < objects.length - 1){
            setIndex(index + 1);
        };
    };

    const handlerPreviousObject = () => {
        if (index > 0){
            setIndex(index - 1);
        };
    };

    return(
        <>
            <h1>Recorredor de arreglos de objetos</h1>
            <p>{ JSON.stringify(objects[index]) }</p>
            <button onClick={ handlerNextObject }>+</button>
            <button onClick={ handlerPreviousObject }>-</button>
        </>
    );
};

export default RecorredorArregloObjetos;