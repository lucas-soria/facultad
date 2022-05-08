~~~toc
  min_depth: 1
~~~
# Generalidades
- .jsx es un modulo de react, sirve para poder ubicarse mejor en el codigo
- Cada componente debe tener un id, para que React pueda crear el arbol de componentes correctamente y pueda diferenciar bien a cada uno... Ej: `<NombreComponente key={algunaKeyOId} atributoQueNecesiteParaOperar={atributo}/>` .... Sinó tira un warning
- Los elementos se deben "expandir" del Componente mas general al mas especifico y los eventos deben ir del mas especifico al mas general
- localStorage solo guarda strings (debemos convertir los objetos a json)

# Para devolver + de 1 tag HTML
Debemos usar Fragment:
~~~javascript
import { Fragment } from 'react';
<Fragment>
	<p>hola</p>
	<p>mundo</p>
</Fragment>
~~~

# onClick handler
`onClick={ handleFunction }` 


---
# React hooks

## useState
**useState** permite re-renderizar cualquier cosa que guardemos en el ante un cambio.

~~~javascript
import { useState } from 'react';
const [estado, setEstado] = useState([]);
setEstado( (prevEstado) => {
	return [...prevEstado, "Hola"]
});
~~~ 

## useRef
Para poder captar los valores que hay en un input

~~~javascript
import { useRef } from 'react';
const todoTaskRef = useRef();
const handleTodoAdd = () => {
  const task = todoTaskRef.current.value;
  if (task == "") return;
  setTodos ( (prevTodos) = {
    return [... prevTodos, { id: uuidv4 (), task, completed: false }];
  });
 todoTaskRef.current.value = null; 
};
~~~

## useEffect
para poder guardar cosas al localStorage por ejemplo
`useEffect( () => {}, [])`  Importantisimo en la lista poner los elementos que queremos que este escuchando, para tomar accion cuando cambien

~~~javascript
// Este codigo se ejecuta al inicio y trae todo lo que este en memoria
import { useEffect } from 'react';
KEY = "App.ItemAGuardar" // Para poder identificarlo en el almacenamiento
useEffect(() => {
  const storedTodos = JSON.parse (localStorage.getItem (KEY));
  if (storedTodos) {
    setTodos storedTodos);
  }
}, []);
~~~

 ~~~javascript
 // Cada vez que el estado de todos cambie, se va a guardar
 import { useEffect } from 'react';
 KEY = "App.ItemAGuardar" // Para poder identificarlo en el almacenamiento
 useEffect (() => {
   localstorage.setItem(KEY, JSON.stringify(todos));
}, [todos]);
 ~~~

https://www.youtube.com/watch?v=vyJU9efvUtQ