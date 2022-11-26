import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Producto from './producto';
import ProductoDetail from './producto-detail';
import ProductoUpdate from './producto-update';
import ProductoDeleteDialog from './producto-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProductoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProductoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProductoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Producto} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProductoDeleteDialog} />
  </>
);

export default Routes;
