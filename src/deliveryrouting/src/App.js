import React from "react";
import SubmitJobComponent from "./SubmitJobComponent";
import { Route, Switch } from "react-router-dom";

function App() {
  return (
    <main>
      <Switch>
        <Route path="/" component={SubmitJobComponent} />
      </Switch>
    </main>
  );
}
export default App;