import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Venta from './venta';
import VentaDetail from './venta-detail';
import VentaUpdate from './venta-update';
import VentaDeleteDialog from './venta-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VentaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VentaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VentaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Venta} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VentaDeleteDialog} />
  </>
);

export default Routes;
