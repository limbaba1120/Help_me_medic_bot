import React, { useRef } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const LogIn = () => {
  const inputId = useRef();
  const inputPw = useRef();

  const handleLogIn = (event) => {
    event.preventDefault();

    axios
      .post("https://메디챗.웹.한국:8443/login", {
        loginId: inputId.current.value,
        password: inputPw.current.value,
      })
      .then((res) => {
        sessionStorage.setItem("user_id", res.data.id);
        sessionStorage.setItem("name", res.data.nickname);
        window.location.reload();
      })
      .catch(() => {
        alert("입력하신 아이디 또는 비밀번호가 일치하지 않습니다.");
        window.location.reload();
      });
  };

  return (
    <>
      <h1 className="font-semibold text-center">
        <div className="text-primary inline-flex text-xl transition-all duration-200 md:text-3xl">
          <span className="lowercase">Medi</span>
          <span className="text-base-content uppercase">Chat</span>
        </div>
      </h1>
      <form className="space-y-4" onSubmit={handleLogIn}>
        <div>
          <label className="label">
            <span className="text-base label-text">ID</span>
          </label>
          <input
            type="text"
            placeholder="ID"
            className="w-full input input-bordered"
            ref={inputId}
          />
        </div>
        <div>
          <label className="label">
            <span className="text-base label-text">Password</span>
          </label>
          <input
            type="password"
            placeholder="Enter Password"
            className="w-full input input-bordered"
            ref={inputPw}
          />
        </div>
        <div className="flex flex-col gap-2">
          <div className="flex gap-2">
            Don't have an account?
            <Link
              to="/signup"
              className="text-gray-600 hover:underline hover:text-blue-600"
            >
              Sign up
            </Link>
          </div>
          <button type="submit" className="btn btn-block">
            Login
          </button>
        </div>
      </form>
    </>
  );
};

export default LogIn;
