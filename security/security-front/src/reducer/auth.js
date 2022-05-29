import { createAction, handleActions } from "redux-actions";
import {
  createRequestActionTypes,
  createRequestSaga,
} from "../lib/createRequestSaga";
import * as authApi from "../lib/api/auth";
import { takeLatest } from "@redux-saga/core/effects";

//api 요청 할 때 액션타입은 무조건 이 3개 만든다고 생각하면 되요.

//액션 타입 생성
const [LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE] =
  createRequestActionTypes("LOGIN");

const [GOOGLE_REQUEST, GOOGLE_SUCCESS, GOOGLE_FAILURE] =
  createRequestActionTypes("GOOGLE");

const [LOADUSER_REQUEST, LOADUSER_SUCCESS, LOADUSER_FAILURE] =
  createRequestActionTypes("LOADUSER");

const [LOGOUT_REQUEST, LOGOUT_SUCCESS, LOGOUT_FAILURE] =
  createRequestActionTypes("LOGOUT");

//액션 생성함수
export const loginAction = createAction(LOGIN_REQUEST, (data) => data);
export const googleAction = createAction(GOOGLE_REQUEST, (data) => data);
export const loadUserAction = createAction(LOADUSER_REQUEST, (data) => data);
export const logoutAction = createAction(LOGOUT_REQUEST, (data) => data);

//사가 생성
const loginSaga = createRequestSaga(LOGIN_REQUEST, authApi.login);
const googleSaga = createRequestSaga(GOOGLE_REQUEST, authApi.sociaLogin);
const loadUserSaga = createRequestSaga(LOADUSER_REQUEST, authApi.loadUser);
const logoutSaga = createRequestSaga(LOGOUT_REQUEST, authApi.logout);

export function* authSaga() {
  //제너레이터 문법인데, 이벤트리스너 랑 비슷하다고

  console.log("감시자");

  //우리가 만든 제너레이터 함수에게 액션타입 인자를 넘겨줘요.
  yield takeLatest(LOGIN_REQUEST, loginSaga); //버튼을 여러번 누르잖아요. 그러면 마지막에 누른 것만 포착해서 실행시켜줘요.
  yield takeLatest(GOOGLE_REQUEST, googleSaga);
  yield takeLatest(LOADUSER_REQUEST, loadUserSaga);
  yield takeLatest(LOGOUT_REQUEST, logoutSaga);
}

// 초기 상태 선언
const initialState = {
  loginRequest: false, //이 상태값들은 전역변수, 어떤 위치의 컴포넌트도 다 가져서 쓸 수가 있어요.
  loginDone: false, //내가 로그인 됐는지 안 됐는지 판단.
  loginError: null,

  googleRequest: false, //이 상태값들은 전역변수, 어떤 위치의 컴포넌트도 다 가져서 쓸 수가 있어요.
  //googleDone: false, //login
  //googleError: null,

  loadUserRequest: false, //이 상태값들은 전역변수, 어떤 위치의 컴포넌트도 다 가져서 쓸 수가 있어요.
  loadUserDone: false,
  loadUserError: null,

  logoutRequest: false, //이 상태값들은 전역변수, 어떤 위치의 컴포넌트도 다 가져서 쓸 수가 있어요.
  logoutDone: false,
  logoutError: null,

  principal: null,
};

const auth = handleActions(
  {
    [LOGIN_SUCCESS]: (state, { payload: response }) => {
      console.log("순서4", response.data);
      localStorage.setItem("jwt", response.headers.authorization);
      return {
        ...state,
        loginDone: true,
        principal: response.data.data,
      };
    },
    [LOGIN_FAILURE]: (state, { payload: error }) => ({
      ...state,
      loginError: error,
    }),

    [GOOGLE_SUCCESS]: (state, { payload: response }) => ({
      ...state,
      loginDone: true,
      principal: response.data.data,
    }),
    [GOOGLE_FAILURE]: (state, { payload: error }) => ({
      ...state,
      loginError: error,
    }),

    [LOADUSER_SUCCESS]: (state, { payload: response }) => ({
      ...state,
      loadUserDone: true,
      loginDone: true,
      principal: response.data.data,
    }),
    [LOADUSER_FAILURE]: (state, { payload: error }) => {
      console.log("error", error);
      return { ...state, loadUserError: error };
    },

    [LOGOUT_SUCCESS]: (state, { payload: data }) => {
      localStorage.clear();

      return {
        ...state,
        logoutDone: true,
        loginDone: false,
        principal: null,
      };
    },
    [LOGOUT_FAILURE]: (state, { payload: error }) => ({
      ...state,
      logoutError: error,
    }),
  },
  initialState
);

export default auth;
