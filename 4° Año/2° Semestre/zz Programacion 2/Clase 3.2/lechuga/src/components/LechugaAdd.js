import React from "react";
import { useState } from "react";

const LechugaAdd = ({ setLechugas }) => {

    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (inputValue.trim().length > 0) {
            setLechugas( lechugas => [...lechugas, inputValue] );
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

export default LechugaAdd;
