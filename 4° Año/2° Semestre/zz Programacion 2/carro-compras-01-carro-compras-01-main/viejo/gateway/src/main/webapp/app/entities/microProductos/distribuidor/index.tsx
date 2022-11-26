import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Distribuidor from './distribuidor';
import DistribuidorDetail from './distribuidor-detail';
import DistribuidorUpdate from './distribuidor-update';
import DistribuidorDeleteDialog from './distribuidor-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DistribuidorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DistribuidorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DistribuidorDetail} />
      <ErrorBoundaryRoute path={match.url} component={Distribuidor} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DistribuidorDeleteDialog} />
  </>
);

export default Routes;
