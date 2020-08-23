import React from 'react';
import './App.css';
import SubmitJobComponent from './SubmitJobComponent';
import GetPathComponent from './GetPathComponent';
import { Route, Switch, Link } from 'react-router-dom';

function App() {
  return (
    <main>
      <Switch>
        <Route path="/" component={SubmitJobComponent} exact/>
        <Route path="/admin" component={GetPathComponent} />
        <Route path="/">
          <Link to="/admin">Admin dashboard</Link>
        </Route>
      </Switch>
    </main>
  );
}
export default App;