import React, { useEffect, useState } from "react";
import axios from "axios";

import profileImage from "../../assets/images/chatbot.png";
import Modal from "../modal/Modal";

const ResChatBubble = ({ message }) => {
  const [hospList, setHospList] = useState([]);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(async (pos) => {
        axios
          .post("https://메디챗.웹.한국:8443/place", {
            keyword: "병원",
            latitude: pos.coords.latitude,
            longitude: pos.coords.longitude,
            radius: 5000,
            type: "hospital",
          })
          .then((res) => {
            setHospList(res.data.results.slice(0, 5));
          });
      });
    } else {
      window.alert("현재 위치를 알 수 없습니다.");
    }
  }, []);

  return (
    <>
      <div className="chat chat-start ml-2">
        <div className="chat-image avatar">
          <div className="w-10 rounded-full border border-base-content">
            <img src={profileImage} alt="face" />
          </div>
        </div>
        <div className="chat-bubble">{message}</div>
      </div>
      {
        <div className="flex flex-col items-start gap-1 ml-14">
          {hospList.map((hosp, index) => (
            <Modal key={index} hospital={hosp} />
          ))}
        </div>
      }
    </>
  );
};

export default ResChatBubble;
