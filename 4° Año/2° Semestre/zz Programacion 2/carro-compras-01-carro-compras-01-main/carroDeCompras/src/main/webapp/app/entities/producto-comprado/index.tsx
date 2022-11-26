import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProductoComprado from './producto-comprado';
import ProductoCompradoDetail from './producto-comprado-detail';
import ProductoCompradoUpdate from './producto-comprado-update';
import ProductoCompradoDeleteDialog from './producto-comprado-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProductoCompradoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProductoCompradoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProductoCompradoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProductoComprado} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProductoCompradoDeleteDialog} />
  </>
);

export default Routes;
