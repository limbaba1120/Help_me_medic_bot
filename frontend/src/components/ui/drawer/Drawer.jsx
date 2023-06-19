import React from "react";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";

import Profile from "./Profile";
import { BsChatDots, BsPeople } from "react-icons/bs";

const Drawer = () => {
  const { t } = useTranslation();

  return (
    <div className="drawer-side">
      <label htmlFor="drawer" className="drawer-overlay"></label>
      <aside className="bg-base-200 w-80">
        <div className="z-20 bg-base-200 bg-opacity-90 backdrop-blur sticky top-0 items-center gap-2 px-4 py-2 flex">
          <Link to="/" className="flex-0 btn btn-ghost px-2">
            <div className="font-title text-primary inline-flex text-lg transition-all duration-200 md:text-3xl">
              <span className="lowercase">Medi</span>
              <span className="text-base-content uppercase">Chat</span>
            </div>
          </Link>
        </div>
        <div className="h-4"></div>
        <ul className="menu menu-compact flex flex-col p-0 px-4">
          <li>
            <Link to="/chat" className="flex gap-4">
              <span className="flex-none">
                <BsChatDots size="24px" />
              </span>
              <span className="flex-1">{t("chat")}</span>
            </Link>
          </li>
          <li>
            <Link to="/forum" className="flex gap-4">
              <span className="flex-none">
                <BsPeople size="24px" />
              </span>
              <span className="flex-1">{t("forum")}</span>
            </Link>
          </li>
        </ul>
        <div className="fixed bottom-0 w-full p-4">
          <Profile />
        </div>
      </aside>
    </div>
  );
};

export default Drawer;
