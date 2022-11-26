import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CarroCompra from './carro-compra';
import CarroCompraDetail from './carro-compra-detail';
import CarroCompraUpdate from './carro-compra-update';
import CarroCompraDeleteDialog from './carro-compra-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CarroCompraUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CarroCompraUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CarroCompraDetail} />
      <ErrorBoundaryRoute path={match.url} component={CarroCompra} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CarroCompraDeleteDialog} />
  </>
);

export default Routes;
