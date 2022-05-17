import { combineReducers } from "redux";
import { all } from "@redux-saga/core/effects";
import auth, { authSaga } from "./auth";
import user, { userSaga } from "./user";
import test, { testSaga } from "./test";
import loading from "./loading";

//redux를 쓸 떼 단점이, reducer 자체가 순수함수여야 되요. 그래서 중간에 어떤 처리
//그런니까 예를 들어 로그 남기는 것 같은 중간 처리를 할 수 있는 미들웨어가 필요한데, 리덕스 미들웨어 종류 중의 하나에요.
// 얘네가 비동기 처리를 도외주거든요. redux 자체만 쓰면 비동기 처리가 힘들어요. 일단 만들고 나서 통신하는 과정을 보여주고 설명해드릴게요.

const rootReducer = combineReducers({
  loading,
  auth,
  user,
  test,
});

export function* rootSaga() {
  yield all([authSaga(), userSaga(), testSaga()]);
}

export default rootReducer;
