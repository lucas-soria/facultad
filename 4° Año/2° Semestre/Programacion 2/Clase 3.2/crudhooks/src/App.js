import React, { useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, ModalBody, ModalHeader, ModalFooter } from 'reactstrap';

function App() {

  const dataPaises = [
    { id: 1, name: "Filipinas", minutes: 241 },
    { id: 2, name: "Brasil", minutes: 225 },
    { id: 3, name: "Colombia", minutes: 216 },
    { id: 4, name: "Nigeria", minutes: 216 },
    { id: 5, name: "Argentina", minutes: 207 },
    { id: 6, name: "Indonesia", minutes: 195 },
    { id: 7, name: "Emiratos Árabes Unidos", minutes: 191 },
    { id: 8, name: "México", minutes: 190 },
    { id: 9, name: "Sudáfrica", minutes: 190 },
    { id: 10, name: "Egipto", minutes: 186 },
  ];

  const [ data, setData ] = useState( dataPaises );
  const [ modalEdit, setModalEdit ] = useState( false );
  const [ modalDelete, setModalDelete ] = useState( false );
  const [ modalInsert, setModalInsert ] = useState( false );
  const [ selectedCountry, setSelectedCountry ] = useState( {id: '', name: '', minutes: ''} );

  const seleccionarPais = ( elemento, caso ) => {
    setSelectedCountry( elemento );
    ( caso === 'Editar' ) ? setModalEdit( true ):setModalDelete( true );
  };

  const handleChange = e => {
    const { name, value } = e.target;
    setSelectedCountry( ( prevState ) => ( {
      ...prevState,
      [ name ]: value
    } ) );
  };

  const editar = () => {
    var dataNueva = data;
    // eslint-disable-next-line
    dataNueva.map( pais => {
      if ( pais.id === selectedCountry.id ) {
        pais.minutes = selectedCountry.minutes;
        pais.name = selectedCountry.name;
      };
    } );
    setData( dataNueva );
    setModalEdit( false );
  };

  const eliminar = () => {
    setData( data.filter( pais => pais.id !== selectedCountry.id ) );
    setModalDelete( false );
  };

  const abrirModalInsert = () => {
    setSelectedCountry( null );
    setModalInsert( true );
  };

  const insertar = () => {
    var valorInsertar = selectedCountry;
    valorInsertar.id = data[ data.length-1 ].id+1;
    var dataNueva = data;
    dataNueva.push( valorInsertar );
    setData( dataNueva );
    setModalInsert( false );
  };

  return (
    <div className="App">
      <h2>Países en los que la gente pasa más tiempo en redes sociales (2019)</h2>
      <br/>
      <button className="btn btn-success" onClick={ () => abrirModalInsert() }>Insertar</button>
      <br/><br/>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Minutos (por día)</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          { data.map( elemento => (
            <tr>
              <td>{ elemento.id }</td>
              <td>{ elemento.name }</td>
              <td>{ elemento.minutes }</td>
              <td>
                <button className="btn btn-primary" onClick={ () => seleccionarPais( elemento, 'Editar' ) }>Editar</button>
                {"   "}
                <button className="btn btn-danger" onClick={ () => seleccionarPais( elemento, 'Eliminar' ) }>Eliminar</button>
              </td>
            </tr>
            ) )
          }
        </tbody>
      </table>

      <Modal isOpen={ modalEdit }>
        <ModalHeader>
          <div>
            <h3>Editar País</h3>
          </div>
        </ModalHeader>
        <ModalBody>
          <div className="form-group">
            <label>ID</label>
            <input className="form-control" readOnly type="text" name="id" value={ selectedCountry && selectedCountry.id }/>
            <br/>

            <label>País</label>
            <input className="form-control" type="text" name="name" value={ selectedCountry && selectedCountry.name } onChange={ handleChange }/>
            <br/>

            <label>Minutos</label>
            <input className="form-control" type="text" name="minutes" value={ selectedCountry && selectedCountry.minutes } onChange={ handleChange }/>
            <br/>
          </div>
        </ModalBody>
        <ModalFooter>
          <button className="btn btn-primary" onClick={ () => editar() }>Actualizar</button>
          <button className="btn btn-danger" onClick={ () => setModalEdit( false ) }>Cancelar</button>
        </ModalFooter>
      </Modal>


      <Modal isOpen={ modalDelete }>
        <ModalBody>
          Estás Seguro que deseas eliminar el país { selectedCountry && selectedCountry.name }
        </ModalBody>
        <ModalFooter>
          <button className="btn btn-danger" onClick={ () => eliminar() }>Sí</button>
          <button className="btn btn-secondary" onClick={ () => setModalDelete ( false ) }>No</button>
        </ModalFooter>
      </Modal>
      <Modal isOpen={ modalInsert }>
        <ModalHeader>
          <div>
            <h3>Insertar País</h3>
          </div>
        </ModalHeader>
        <ModalBody>
          <div className="form-group">
            <label>ID</label>
            <input className="form-control" readOnly type="text" name="id" value={ data[ data.length-1 ].id+1 }/>
            <br/>

            <label>País</label>
            <input className="form-control" type="text" name="name" value={ selectedCountry ? selectedCountry.name: '' } onChange={ handleChange }/>
            <br/>

            <label>Minutos</label>
            <input className="form-control" type="text" name="minutes" value={ selectedCountry ? selectedCountry.minutes: '' } onChange={ handleChange } />
            <br/>
          </div>
        </ModalBody>
        <ModalFooter>
          <button className="btn btn-primary" onClick={ () => insertar() }>Insertar</button>
          <button className="btn btn-danger" onClick={ () => setModalInsert( false ) }>Cancelar</button>
        </ModalFooter>
      </Modal>
    </div>
  );
};

export default App;
