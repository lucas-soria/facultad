import React, { useState } from 'react';

const AddElement = ({ setList })  => {

    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (inputValue.trim().length > 0) {
            setList( list => [...list, inputValue] );
            setInputValue('');
        };
    };

    return (
        <>
            <form onSubmit={ handleSubmit }>
                <input
                    type='text'
                    value={ inputValue }
                    onChange={ handleInputChange }
                />
            </form>
        </>
    );
};

export default AddElement;
