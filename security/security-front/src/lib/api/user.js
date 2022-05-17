import client from "./client";

export const userfind = (data) => client.get("/user/find");

export const adminGet = (data) => client.get("/admin/get");
