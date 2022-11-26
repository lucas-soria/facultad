import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CarroCompra from './carro-compra';
import ProductoComprado from './producto-comprado';
import Venta from './venta';
import ProductoSeleccionado from './producto-seleccionado';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}carro-compra`} component={CarroCompra} />
      <ErrorBoundaryRoute path={`${match.url}producto-comprado`} component={ProductoComprado} />
      <ErrorBoundaryRoute path={`${match.url}venta`} component={Venta} />
      <ErrorBoundaryRoute path={`${match.url}producto-seleccionado`} component={ProductoSeleccionado} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
