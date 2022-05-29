import { Route } from "react-router-dom";
import { Switch } from "react-router-dom";
import Home from "./pages/Home";
import Test from "./pages/Test";
import Test2 from "./pages/Test2";
import Check from "./pages/Check";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { loadUserAction } from "./reducer/auth";

function App() {
  const dispatch = useDispatch();

  //dispath 선언
  useEffect(() => {
    console.log("맨 처음 랜더링 될 때 실행");
    //dispatch(loadUserAction());
  }, []);
  return (
    <>
      <Switch>
        <Route path="/" exact={true} component={Home} />
        <Route path="/check" exact={true} component={Check} />
        <Route path="/test" exact={true} component={Test} />
        <Route path="/test2" exact={true} component={Test2} />
      </Switch>
    </>
  );
}

export default App;

//
