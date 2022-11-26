import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProductoSeleccionado from './producto-seleccionado';
import ProductoSeleccionadoDetail from './producto-seleccionado-detail';
import ProductoSeleccionadoUpdate from './producto-seleccionado-update';
import ProductoSeleccionadoDeleteDialog from './producto-seleccionado-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProductoSeleccionadoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProductoSeleccionadoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProductoSeleccionadoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProductoSeleccionado} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProductoSeleccionadoDeleteDialog} />
  </>
);

export default Routes;
