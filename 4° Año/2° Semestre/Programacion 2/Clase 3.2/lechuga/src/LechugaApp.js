import React from 'react';
import { useState } from 'react';
import LechugaAdd from './components/LechugaAdd';

const LechugaApp = () => {

    const [ lechugas, setLechugas ] = useState(['Morada', 'Repollada']);

    return (
        <>
            <h2>Lechuga App</h2>

            <LechugaAdd setLechugas={ setLechugas }/>

            <hr />

            <ol>
                {
                    lechugas.map( (lechuga) => <li key={ lechuga }> { lechuga } </li> )
                }
            </ol>
        </>
    );
};

export default LechugaApp;