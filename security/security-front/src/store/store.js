import { applyMiddleware, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import createSagaMiddleware from "redux-saga";
import rootReducer, { rootSaga } from "../reducer";

//일단 다 보여주고 설명드릴게요.
const configureStore = () => {
  const sagaMiddlWare = createSagaMiddleware();

  const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(sagaMiddlWare))
  );

  sagaMiddlWare.run(rootSaga);

  return store;
};

export default configureStore;
