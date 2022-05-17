import axios from "axios";

const client = axios.create();

//const client2 = axios.create();
//client.defaults.baseURL = "http://localhost:8080/";

client.defaults.headers.post["Content-Type"] =
  "application/json; charset=UTF-8";

client.defaults.baseURL = process.env.REACT_APP_URL;
//client.defaults.baseURL = "http://localhost:8085";

//client.defaults.headers.common["Authorization"] = localStorage.getItem("jwt");

client.interceptors.request.use(
  (request) => {
    console.log("Starting Request", JSON.stringify(request, null, 2));
    //console.log("request", request);

    request.headers.Authorization = localStorage.getItem("jwt");

    console.log("순서5");
    return request;
  },
  (error) => {
    console.log("error", error);

    return Promise.reject(error);
  }
);

client.interceptors.response.use(
  (response) => {
    //console.log("Starting Response", JSON.stringify(response, null, 2));

    console.log("순서 6");

    return response;
  },
  (error) => {
    console.log("error", error);
    return Promise.reject(error);
  }
);

export default client;
