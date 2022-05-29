import { createAction, handleActions } from "redux-actions";
import {
  createRequestActionTypes,
  createRequestSaga,
} from "../lib/createRequestSaga";
import * as testApi from "../lib/api/test";
import { takeLatest } from "redux-saga/effects";

//1. 액션 타입을 생성

const [TEST_REQUEST, TEST_SUCCESS, TEST_FAILURE] =
  createRequestActionTypes("TEST");

const [CHECK_REQUEST, CHECK_SUCCESS, CHECK_FAILURE] =
  createRequestActionTypes("CHECK");

const [TEST2_REQUEST, TEST2_SUCCESS, TEST2_FAILURE] =
  createRequestActionTypes("TEST2");

//2. 액션 생성 함수
export const testAction = createAction(TEST_REQUEST, (parame) => parame);
export const test2Action = createAction(TEST2_REQUEST, (data) => data);
export const checkAction = createAction(CHECK_REQUEST);

//3. 사가 생성
const testActionSaga = createRequestSaga(TEST_REQUEST, testApi.test);
const test2ActionSaga = createRequestSaga(TEST2_REQUEST, testApi.test2);
const checkActionSaga = createRequestSaga(CHECK_REQUEST, testApi.check);

export function* testSaga() {
  yield takeLatest(TEST_REQUEST, testActionSaga); //마지막 클릭만
  yield takeLatest(TEST2_REQUEST, test2ActionSaga); //마지막 클릭만
  yield takeLatest(CHECK_REQUEST, checkActionSaga); //마지막 클릭만
}

const initialState = {
  testReuest: null,
  testSuccess: false,
  testfailure: null,
  test2Reuest: null,
  test2Success: false,
  test2failure: null,

  checkRequest: false,
  checkSuccess: false,
  checkFailure: null,

  data: "??",
  test2Data: null,
};

const test = handleActions(
  {
    [TEST_REQUEST]: (state, { payload: resp }) => ({
      ...state,
      testReuest: true,
    }),

    [TEST_SUCCESS]: (state, { payload: resp }) => {
      console.log("test success", resp);

      return {
        ...state,
        testSuccess: true,
        testReuest: false,
        data: resp.data,
      };
    },
    [TEST_FAILURE]: (state, { payload: error }) => ({
      ...state,
      testfailure: error,
    }),

    [CHECK_SUCCESS]: (state, { payload: resp }) => {
      console.log("순서 8 check success", resp);

      return {
        ...state,
        checkSuccess: true,
        checkRequest: false,
        data: resp.data,
      };
    },
    [CHECK_FAILURE]: (state, { payload: error }) => {
      console.log("순서 7");

      return {
        ...state,
        checkFailure: error,
      };
    },

    [TEST2_REQUEST]: (state, { payload: resp }) => ({
      ...state,
      test2Reuest: true,
    }),

    [TEST2_SUCCESS]: (state, { payload: resp }) => {
      console.log("test2 success", resp);

      return {
        ...state,
        test2Success: true,
        test2Reuest: false,
        data: resp.data,
      };
    },
    [TEST2_FAILURE]: (state, { payload: error }) => ({
      ...state,
      test2failure: error,
    }),
  },
  initialState
);

export default test;
