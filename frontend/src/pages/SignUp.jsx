import React, { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const SignUp = () => {
  const navigate = useNavigate();

  const inputId = useRef();
  const inputPw = useRef();
  const inputName = useRef();
  const inputNickname = useRef();
  const inputCountry = useRef();

  const handleSignUp = (event) => {
    event.preventDefault();

    if (
      inputCountry.current.value === "Select Country" ||
      inputId.current.value === "" ||
      inputName.current.value === "" ||
      inputNickname.current.value === "" ||
      inputPw.current.value === ""
    ) {
      alert("모두 입력해주세요.");
      return;
    }

    axios
      .post("https://메디챗.웹.한국:8443/users/join", {
        country: inputCountry.current.value,
        loginId: inputId.current.value,
        name: inputName.current.value,
        nickname: inputNickname.current.value,
        password: inputPw.current.value,
      })
      .then((res) => {
        alert("회원가입이 완료되었습니다.");
        navigate("/");
      })
      .catch(() => {
        alert("이미 존재하는 아이디입니다.");
      });
  };

  return (
    <>
      <h1 className="text-primary text-xl font-semibold text-center transition-all duration-200 md:text-3xl">
        <span>Create </span>
        <span className="text-base-content">Account</span>
      </h1>
      <form className="space-y-4" onSubmit={handleSignUp}>
        <div>
          <label className="label">
            <span className="label-text text-base">ID</span>
          </label>
          <input
            type="id"
            placeholder="Enter ID"
            className="w-full input input-bordered"
            ref={inputId}
          />
        </div>
        <div>
          <label className="label">
            <span className="label-text text-base">Password</span>
          </label>
          <input
            type="password"
            placeholder="Enter Password"
            className="w-full input input-bordered"
            ref={inputPw}
          />
        </div>
        <div>
          <label className="label">
            <span className="label-text text-base">Name</span>
          </label>
          <input
            type="text"
            placeholder="Name"
            className="w-full input input-bordered"
            ref={inputName}
          />
        </div>
        <div>
          <label className="label">
            <span className="label-text text-base">Nickname</span>
          </label>
          <input
            type="text"
            placeholder="Nickname"
            className="w-full input input-bordered"
            ref={inputNickname}
          />
        </div>
        <div>
          <label className="label">
            <span className="label-text text-base">Country</span>
          </label>
          <select
            className="select select-bordered w-full"
            defaultValue="Select Country"
            ref={inputCountry}
          >
            <option disabled>Select Country</option>
            <option>United Kingdom</option>
            <option>Spain</option>
            <option>France</option>
            <option>Indonesia</option>
            <option>Japan</option>
            <option>Korea</option>
            <option>Portugal</option>
            <option>Russia</option>
            <option>China</option>
            <option>Taiwan</option>
          </select>
        </div>
        <div className="flex flex-col gap-2">
          <div className="flex gap-2">
            Already have an account?
            <Link
              to="/"
              className="text-gray-600 hover:underline hover:text-blue-600"
            >
              Login
            </Link>
          </div>
          <button type="submit" className="btn btn-block">
            Sign Up
          </button>
        </div>
      </form>
    </>
  );
};

export default SignUp;
