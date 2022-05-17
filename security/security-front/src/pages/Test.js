import { Button, Layout } from "antd";
import { Content } from "antd/lib/layout/layout";
import React, { memo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { testAction } from "../reducer/test";

const Test = memo(() => {
  const dispatch = useDispatch();

  const { testData } = useSelector(({ test }) => ({
    testData: test.data,
  }));

  const testClick = () => {
    console.log("순서1");

    dispatch(testAction("hi"));
  };

  return (
    <>
      <Layout>
        <Content>
          <Button onClick={testClick} type="primary">
            test action
          </Button>
          <div>{testData?.age}</div>
        </Content>
      </Layout>
    </>
  );
});

export default Test;
