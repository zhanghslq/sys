'use strict';

import React, {
  Component,
} from 'react';
import {
  Router,
  Route,
  IndexRoute,
  browserHistory,
} from 'react-router';

import {
  App,
  Welcome,
  ComponentDemo,
} from './components';

export default (
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      <IndexRoute name="Welcome" component={Welcome} />
      <Route path=":component" component={ComponentDemo} />
    </Route>
  </Router>
);
