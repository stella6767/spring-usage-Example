import React, { memo, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { checkAction } from "../reducer/test";
import useUpdateEffect from "./../lib/useUpdateEffect";
import { Button, Form, InputNumber } from "antd";
import { Input } from "antd";
import { loginAction } from "../reducer/auth";
import loading from "./../reducer/loading";

const Check = memo(() => {
  const dispatch = useDispatch();

  const { loginDone, data, loginRequest, CHECK_REQUEST } = useSelector(
    ({ test, loading, auth }) => ({
      data: test.data,
      loginDone: auth.loginDone,
      loginRequest: loading.LOGIN_REQUEST,
      CHECK_REQUEST: loading.CHECK_REQUEST,
    })
  );

  //   useEffect(() => {
  //     console.log("check compnent 랜더링");
  //   }, []);

  useEffect(() => {
    console.log("check compnent 랜더링");
  }, [data]);

  useUpdateEffect(() => {
    console.log("data", data);
  }, [data]);

  const [itemArray, setItemArray] = useState([]);

  const [item, setItem] = useState({
    id: 5,
    count: 1,
  });

  const apiTest = () => {
    console.log("순서1");
    dispatch(checkAction());
  };

  const onFinish = (values) => {
    console.log("Success:", values);

    setItemArray(itemArray.concat(values));

    //dispatch(loginAction(values));
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  const onChange = (value) => {
    console.log("changed", value);

    // let item = {
    //   id: 3,
    //   count: value,
    // };
    // setItem(item);
    // setItemArray(itemArray.concat(item));
  };

  useUpdateEffect(() => {
    console.log("itemarray", itemArray);
  }, [itemArray]);

  const handleWrite = (e) => {
    //listpage의 setPosts에 무엇을 담아야 함?
    //let post = { id: 6, title: '인풋값' };
    //setPosts({ ...posts, id:no });
    e.preventDefault(); // form태그가 하려는 액션을 중지 시켜야 함.

    //setItem([...posts, { ...post, id: post.id + 1 }]);

    console.log("item", item);

    //setItemArray(itemArray.concat(item));
  };

  const handleForm = (e) => {
    console.log("e.target.name", e.target.name);
    console.log("e.target.value", e.target.value);

    // //computed property names 문법(키 값 동적할당)
    setItem({ ...item, [e.target.name]: e.target.value }); //변수 키값을 동적으로 만들어냄...자바에는 없는 문법
    console.log(item.id);
    console.log(item.count);
    setItem([...item, { ...item, id: item.count + 1 }]);

    //setItemArray(itemArray.concat(item));
  };

  return (
    <div>
      {/* <h1>체크!!!</h1>
      <h1>{data}</h1>
      <Button onClick={apiTest} loading={CHECK_REQUEST}>
        api test
      </Button>
      {loginDone === false && (
        <Form
          name="basic"
          labelCol={{
            span: 8,
          }}
          wrapperCol={{
            span: 16,
          }}
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Form.Item
            label="Username"
            name="username"
            rules={[
              {
                required: true,
                message: "Please input your username!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Password"
            name="password"
            rules={[
              {
                required: true,
                message: "Please input your password!",
              },
            ]}
          >
            <Input.Password />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 8,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit" loading={loginRequest}>
              로그인
            </Button>
          </Form.Item>
        </Form>
      )} */}

      {/* <InputNumber min={1} max={10} defaultValue={1} onChange={onChange} /> */}

      {/* <Form
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item name="id">
          <Input hidden defaultValue={1} />
        </Form.Item>

        <Form.Item name="count">
          <InputNumber min={1} max={10} defaultValue={3} onChange={onChange} />
        </Form.Item>

        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form> */}

      <form>
        <input
          //type="hidden"
          value={item.id} //value에 상태값을 주고
          name="id"
          onChange={handleForm}
        />
        <input
          type="number"
          name="count"
          min={1}
          max={10}
          // defaultValue={3}
          value={item.count}
          onChange={handleForm}
        />

        {/* <button type="button" onClick={handleWrite}>
          전송
        </button> */}
      </form>
    </div>
  );
});

export default Check;
