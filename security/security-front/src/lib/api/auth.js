import client from "./client";

export const login = (data) => client.post("/mylogin", JSON.stringify(data));
//export const login = (data) => client.post("/login", JSON.stringify(data));

export const sociaLogin = (data) =>
  client.post("/socialLogin", JSON.stringify(data));

export const logout = (data) => client.get("/mylogout"); //자동로그인 할 게요.

export const loadUser = (data) => client.get("/loadUser");

//어제 여러분 한 거 좀 봤는데, 제 생각보다 잘 하시는 것 같아서 세세한 설명 같은 거 다 넘어갈게요.

//이거는 간단하게 저희가 구현한 거고 이론적인 걸 조금 얘기할게요.
//소셜 로그인 너무 프로젝트 여러분 잘 하신 거 구

//제일 구현하는 가장 간단한 방법이 client credential 방식이거드요. 이거 그냥 라이브러리 갖다 쓰면 돼요.
//이론 프 same site 쿠키랑 jwt의 보안 이런 개념을 알아야되거든요.\
