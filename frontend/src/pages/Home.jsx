import React from "react";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";

import { BsChatDots, BsPeople } from "react-icons/bs";
import Image from "../assets/images/main_banner.jpg";

const Home = () => {
  const { t } = useTranslation();
  const navigation = useNavigate();

  const clickHandler = (path) => {
    navigation(path);
  };

  return (
    <>
      <img src={Image} alt="chatbot" className="w-full object-contain" />
      <div className="flex flex-col gap-2 w-full justify-center mt-8 md:flex-row">
        <div
          className="flex gap-4 btn btn-warning rounded-lg w-full h-24 md:w-1/2"
          onClick={() => clickHandler("/chat")}
        >
          <BsChatDots size="40px" />
          <span className="text-4xl">{t("chat")}</span>
        </div>
        <div
          className="flex gap-4 btn btn-success rounded-lg w-full h-24 md:w-1/2"
          onClick={() => clickHandler("/forum")}
        >
          <BsPeople size="40px" />
          <span className="text-4xl">{t("forum")}</span>
        </div>
      </div>
    </>
  );
};

export default Home;
