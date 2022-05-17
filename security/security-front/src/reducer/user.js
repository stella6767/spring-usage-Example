import { createAction, handleActions } from "redux-actions";
import {
  createRequestActionTypes,
  createRequestSaga,
} from "../lib/createRequestSaga";
import * as userApi from "../lib/api/user";
import { takeLatest } from "@redux-saga/core/effects";

//api 요청 할 때 액션타입은 무조건 이 3개 만든다고 생각하면 되요.

//액션 타입 생성
const [USERFIND_REQUEST, USERFIND_SUCCESS, USERFIND_FAILURE] =
  createRequestActionTypes("USERFIND");

const [ADMINGET_REQUEST, ADMINGET_SUCCESS, ADMINGET_FAILURE] =
  createRequestActionTypes("ADMINGET");

//액션 생성함수
export const userFindAction = createAction(USERFIND_REQUEST, (data) => data);
export const adminGetAction = createAction(ADMINGET_REQUEST, (data) => data);

//사가 생성
const userFindSaga = createRequestSaga(USERFIND_REQUEST, userApi.userfind);
const adminGetSaga = createRequestSaga(ADMINGET_REQUEST, userApi.adminGet);

export function* userSaga() {
  //제너레이터 문법인데, 이벤트리스너 랑 비슷하다고

  console.log("감시자");

  yield takeLatest(USERFIND_REQUEST, userFindSaga); //버튼을 여러번 누르잖아요. 그러면 마지막에 누른 것만 포착해서 실행시켜줘요.
  yield takeLatest(ADMINGET_REQUEST, adminGetSaga); //버튼을 여러번 누르잖아요. 그러면 마지막에 누른 것만 포착해서 실행시켜줘요.
}

// 초기 상태 선언
const initialState = {
  userFindRequest: false, //이 상태값들은 전역변수, 어떤 위치의 컴포넌트도 다 가져서 쓸 수가 있어요.
  userFindDone: false,
  userFindError: null,

  adminGetRequest: false, //이 상태값들은 전역변수, 어떤 위치의 컴포넌트도 다 가져서 쓸 수가 있어요.
  adminGetDone: false,
  adminGetError: null,

  data: null,
};

const user = handleActions(
  {
    [USERFIND_REQUEST]: (state, { payload: data }) => ({
      ...state,
      userFindRequest: true,
    }),

    [USERFIND_SUCCESS]: (state, { payload: data }) => ({
      ...state,
      userFindRequest: false,
      userFindDone: true,
      data: data,
    }),
    [USERFIND_FAILURE]: (state, { payload: error }) => ({
      ...state,
      userFindError: error,
      userFindRequest: false,
    }),

    [ADMINGET_REQUEST]: (state, { payload: data }) => ({
      ...state,
      adminGetRequest: true,
    }),

    [ADMINGET_SUCCESS]: (state, { payload: data }) => ({
      ...state,
      adminGetRequest: false,
      adminGetDone: true,
      data: data,
    }),
    [ADMINGET_FAILURE]: (state, { payload: error }) => ({
      ...state,
      adminGetRequest: false,
      adminGetError: error,
    }),
  },
  initialState
);

export default user;
