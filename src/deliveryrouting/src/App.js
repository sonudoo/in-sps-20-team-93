import React from "react";
import "./App.css";
import SubmitJobComponent from "./SubmitJobComponent";
import GetPathComponent from "./GetPathComponent";
import { Route, Switch } from "react-router-dom";

function App() {
  return (
    <main>
      <Switch>
        <Route path="/" component={SubmitJobComponent} exact />
        <Route path="/admin" component={GetPathComponent} />
      </Switch>
    </main>
  );
}
export default App;