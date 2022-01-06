import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Provincia from './provincia';
import ProvinciaDetail from './provincia-detail';
import ProvinciaUpdate from './provincia-update';
import ProvinciaDeleteDialog from './provincia-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProvinciaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProvinciaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProvinciaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Provincia} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProvinciaDeleteDialog} />
  </>
);

export default Routes;
