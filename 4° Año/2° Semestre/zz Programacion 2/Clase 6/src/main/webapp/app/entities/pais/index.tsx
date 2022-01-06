import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Pais from './pais';
import PaisDetail from './pais-detail';
import PaisUpdate from './pais-update';
import PaisDeleteDialog from './pais-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PaisUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PaisUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PaisDetail} />
      <ErrorBoundaryRoute path={match.url} component={Pais} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PaisDeleteDialog} />
  </>
);

export default Routes;
