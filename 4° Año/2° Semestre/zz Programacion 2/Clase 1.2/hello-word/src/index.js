// funcion
function suma(a, b) {
  	return a + b;
}

// expresion como funcion
const resta = function(a, b) {
  	return a - b;
}

// funcion flecha / lamda
const multiplicacion = (a, b) => {
  	return a * b;
}

// otra funcion flecha
const division = (a, b) => a / b;


console.log("Suma: " + suma(2, 3))
console.log("Resta: " + resta(5, 3))
console.log("Multiplicacion: " + multiplicacion(5, 9))
console.log("Division: " + division(9, 3))


/*
Given two integers a and b, which can be positive or negative, 
find the sum of all the integers between and including them and return it. 
If the two numbers are equal return a or b.

Note: a and b are not ordered!

Examples
GetSum(1, 0) == 1   // 1 + 0 = 1
GetSum(1, 2) == 3   // 1 + 2 = 3
GetSum(0, 1) == 1   // 0 + 1 = 1
GetSum(1, 1) == 1   // 1 Since both are same
GetSum(-1, 0) == -1 // -1 + 0 = -1
GetSum(-1, 2) == 2  // -1 + 0 + 1 + 2 = 2
*/

var a = 1;
var b = 0;

const GetSum = (a, b) => {
  	let sum = 0;
  	if (a > b) {
    	[a, b] = [b, a]
  }
  	for (a;a <= b; a++) {
		sum += a;
  }
	return sum;
}

console.log('Resultado: ' + GetSum(a, b))

const objeto = {
	atributo1: "Hola",
	atributo2: "Mundo",
}

const { atributo1, atributo2 } = objeto;

console.log(atributo1 + " " + atributo2);


const nombre = "Lucas";
const apellido = "Soria";

const nombreCompleto = `${ nombre } ${ apellido }`;

console.log(nombreCompleto);

const saludo = (nombre) => `Hola ${ nombre }`;

console.log(`Mensaje: ${saludo(nombre)}`);

// Una expresion embebida en otra?? -> Wow que lenguaje tan permisivo
console.log(`Mensaje: ${saludo(`${ nombre } ${ apellido }`)}`);

// Clonacion de clases
const persona = {
	"nombre": "Lucas",
	"apellido": "Soria",
	"edad": 21,
};

console.log("persona", persona);

const persona2 = { persona };
persona.edad = 22;

console.log("persona2", persona2);
console.log("persona", persona);


// cosas locas de los arrglos
const arreglo = [1, 2, 3];

arreglo.push(4);

const arreglo2 = [arreglo, 5];

const arreglo3 = [ ...arreglo, 5, ...[6, 7, 8, 9, 10]];

console.log(arreglo, arreglo2, arreglo3);

// Al fin vemos la utilidad de map
const arreglo4 = arreglo3.map( (value) => value * 10 );

console.log(arreglo4);


// Una cosa mas loca todavia entre arrglos y funciones

const useState = (value) => [value, () => `Hola ${ value }`];

const [nombre1, setNombre] = useState('Pepe');

console.log(nombre1, setNombre());


// Esto es una aberracion jeje de nada
const objetoConFunciones = {
	"nombre": "Lucas",
	"apellido": "Soria",
	"todoJunto": () => `${ nombre } ${ apellido }`,
};

console.log(objetoConFunciones.todoJunto());

// Buscando objetos en un arreglo (@ o @)
const autos = [
    {
        id: 1,
        color: "morado",
        tipo: "minivan",
        capacidad: 7
    },
    {
        id: 2,
        color: "rojo",
        tipo: "camioneta",
        capacidad: 5
    }, 
    {
        id: 3,
        color: "blanco",
        tipo: "auto",
        capacidad: 4
    },
    {
        id: 4,
        color: "azul",
        tipo: "auto",
        capacidad: 4
    },
];

/*
const getAutoById = (id) => {
    return autos.find( (auto) => {
        if (auto.id === id) {
            return true;
        }
        return false;
    })
}
*/

const getAutoById = (id) => autos.find( ( auto ) => auto.id === id );

console.log(getAutoById(2));

const getAutoByCapacidad = (capacidad) => autos.filter( (auto) => auto.capacidad === capacidad );

console.log(getAutoByCapacidad(4));

// Promesas wtf
const buscaAuto = (id) => {
	return new Promise( (resolve, reject) => {
		setTimeout( () => {
			const auto = getAutoById(2);
			if ( auto ) {
				resolve(auto);
			} else {
				reject("Auto no existe");
			}
		}, 2000);
	});
};
console.log("Antes de la promesa")

buscaAuto(2).then( (auto) => console.log("Auto", auto) ).catch( err => console.warn(err) );

console.log("Despues de la promesa")
