import ReactDOM from 'react-dom';
import RecorredorArregloObjetos from './RecorredorArregloObjetos';

const divRoot = document.querySelector('#root');

const objects = [
    {nombre: "Lucas",
     apellido: "Soria",
     edad: 21,
    },
    {nombre: "Alejandro",
     apellido: "Marotta",
     edad: 24,
    },
    {nombre: "Franco",
     apellido: "Santander",
     edad: 21,
    },
];

ReactDOM.render(<RecorredorArregloObjetos objects={objects} value={0} />, divRoot);
