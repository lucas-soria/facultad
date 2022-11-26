import { createContext,useContext, useReducer } from "react";

export const StateContext = createContext(); //Creo un contexto para envolver toda la app y poder pasar variables de un componente a otro

export const StateProvider = ({reducer,initialState,children}) =>( //provee la herramienta para poder pasar los datos
    <StateContext.Provider value ={useReducer(reducer,initialState)}>
        {children}
    </StateContext.Provider>
);

export const useStateValue = () => useContext(StateContext);//permite consumir los cambios de estados de initialState