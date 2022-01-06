import React, { useState } from 'react';
import AddElement from './components/AddElement';
import DeleteElement from './components/DeleteElement';

const ToDoApp = () => {

    const [list, setList] = useState([])

    return (
        <>
            <h1>To-do</h1>
            <div>
                <p>Add element:</p>
                <AddElement setList={ setList }/>
            </div>

            <hr />

            <ul>
                {
                    list.map( (element) =>  <li key={ element }>{ element } <DeleteElement setList={ setList } element={ element }/></li>)
                }
            </ul>

        </>
    );

};
//list.map( (element) =>  <li>{ element } <EditElement setList={ setList }/> <DeleteElement setList={ setList }/></li>)
export default ToDoApp;