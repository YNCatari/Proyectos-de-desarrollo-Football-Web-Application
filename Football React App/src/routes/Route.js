//import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
const routes = [
    {
      path: "/",
      exact: true,
      main: () => <App/>
    },
    {
      path: "/match",
      main: () => <Partido/>
    },
    {
      path: "*",
      main: () => <div>No match for</div>
    }
];

export default routes;