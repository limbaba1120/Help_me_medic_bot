import React, { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";

import Main from "./pages/Main";
import LogIn from "./pages/LogIn";
import SignUp from "./pages/SignUp";

const App = () => {
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    if (sessionStorage.getItem("name") !== null) setIsLogin(true);
  }, []);

  return isLogin ? (
    <Main />
  ) : (
    <div className="flex justify-center h-screen overflow-hidden">
      <div className="w-[80%] p-6 space-y-4 m-auto bg-base-200 rounded-md shadow-md ring-2 ring-gray-800/50 md:max-w-md">
        <Routes>
          <Route path="/" element={<LogIn />} />
          <Route path="/signup" element={<SignUp />} />
        </Routes>
      </div>
    </div>
  );
};

export default App;
