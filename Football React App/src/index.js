import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Match from './pages/Match';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import './App.css';

const routes = [
    {
      path: "/",
      exact: true,
      main: () => <App/>
    },
    {
      path: "/match/:id",
      exact: true,
      main: () => <Match/>
    },
    {
      path: "*",
      main: () => <div>No match for</div>
    }
];

function Application()
{
  return(
    <Router>
      <Switch>
        {routes.map((route, index) => (
        <Route
          key={index}
          path={route.path}
          exact={route.exact}
          children={<route.main />}
        />
        ))}
      </Switch>
    </Router>
  );
}

ReactDOM.render(<Application />, document.getElementById('app'));

serviceWorker.unregister();
