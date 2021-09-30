import React from 'react';

const AddElement = ({ setList, element })  => {

    const handleClick = () => {
        setList( (list) => list.filter(item => !element.includes(item)) );
    };

    return (
        <>
            <button onClick={ handleClick }>Remove</button>
        </>
    );
};

export default AddElement;
