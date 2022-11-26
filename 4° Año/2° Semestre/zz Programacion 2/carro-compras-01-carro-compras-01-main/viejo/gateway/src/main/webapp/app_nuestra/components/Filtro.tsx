import React from "react";
import { useState } from "react";

const Filtro = ({ setPersonas, personasObjeto }) => {

    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (inputValue.trim().length > 0) {
            setPersonas(
                personasObjeto.filter( (persona) => persona.nationality.toLowerCase() === inputValue.toLowerCase() )
            );
            setInputValue('');
        };
    };

    return (
        <form onSubmit={ handleSubmit }>
            <input
                type='text'
                value={ inputValue }
                onChange={ handleInputChange }
            />
        </form>
    );
};

export default Filtro;
