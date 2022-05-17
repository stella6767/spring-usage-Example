import client from "./client";

export const selectOrderItem = (orderid) => client.get(`/order/orderItems/${orderid}`);

export const orderCancel = (data) => client.get("/customer/order/cancel/1");
