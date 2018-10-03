import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Coarse from './coarse';
import CoarseDetail from './coarse-detail';
import CoarseUpdate from './coarse-update';
import CoarseDeleteDialog from './coarse-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CoarseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CoarseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CoarseDetail} />
      <ErrorBoundaryRoute path={match.url} component={Coarse} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CoarseDeleteDialog} />
  </>
);

export default Routes;
