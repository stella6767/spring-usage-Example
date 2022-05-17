import React from "react";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { Provider } from "react-redux";
import configureStore from "./store/store";
import reactDom from "react-dom";
import { BrowserRouter } from "react-router-dom";

const store = configureStore();

reactDom.render(
  <Provider store={store}>
    <BrowserRouter basename="">
      <App />
    </BrowserRouter>
    ,
  </Provider>,

  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
