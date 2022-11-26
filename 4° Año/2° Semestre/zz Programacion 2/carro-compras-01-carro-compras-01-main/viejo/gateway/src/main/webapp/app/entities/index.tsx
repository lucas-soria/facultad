import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CarroCompra from './microCarroDeCompras/carro-compra';
import Distribuidor from './microProductos/distribuidor';
import Producto from './microProductos/producto';
import ProductoComprado from './microCarroDeCompras/producto-comprado';
import ProductoSeleccionado from './microCarroDeCompras/producto-seleccionado';
import Venta from './microCarroDeCompras/venta';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}carro-compra`} component={CarroCompra} />
      <ErrorBoundaryRoute path={`${match.url}distribuidor`} component={Distribuidor} />
      <ErrorBoundaryRoute path={`${match.url}producto`} component={Producto} />
      <ErrorBoundaryRoute path={`${match.url}producto-comprado`} component={ProductoComprado} />
      <ErrorBoundaryRoute path={`${match.url}producto-seleccionado`} component={ProductoSeleccionado} />
      <ErrorBoundaryRoute path={`${match.url}venta`} component={Venta} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
