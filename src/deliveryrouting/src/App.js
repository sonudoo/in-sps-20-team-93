import React from 'react';
import './App.css';
import GetPathComponent from './GetPathComponent';
import { Route, Switch } from 'react-router-dom';

function App() {
  return (
    <main>
      <Switch>
        <Route path="/admin" component={GetPathComponent} />
      </Switch>
    </main>
  );
}

export default App;