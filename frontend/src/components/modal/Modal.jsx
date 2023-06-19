import React from "react";
import ReactDOM from "react-dom";

import NaverMap from "./NaverMap";
import "../../styles/StarRating.css";

const ModalOverlay = ({ id, hospital }) => {
  const handleNavigate = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(async (pos) => {
        const link = `http://map.naver.com/index.nhn?slng=${pos.coords.longitude}&slat=${pos.coords.latitude}&stext=Current%20Location&elng=${hospital.geometry.location.lng}&elat=${hospital.geometry.location.lat}&etext=${hospital.name}&menu=route&pathType=1`;
        window.open(link, "_blank");
      });
    } else {
      window.alert("현재 위치를 알 수 없습니다.");
    }
  };

  return (
    <>
      <input type="checkbox" id={id} className="modal-toggle" />
      <label htmlFor={id} className="modal cursor-pointer">
        <label className="modal-box relative w-11/12 max-w-5xl">
          <label
            htmlFor={id}
            className="btn btn-sm btn-circle absolute right-2 top-2"
          >
            ✕
          </label>
          <h3 className="text-lg font-bold">{hospital.name}</h3>
          <div className="py-4">
            <p>주소: {hospital.vicinity}</p>
            <p>
              평점: {hospital.rating} (리뷰 {hospital.user_ratings_total}개)
            </p>
            <div className="star-ratings">
              <div
                className="star-ratings-fill space-x-2 text-lg"
                style={{ width: hospital.rating * 20 + 0.9 + "%" }}
              >
                <span>★</span>
                <span>★</span>
                <span>★</span>
                <span>★</span>
                <span>★</span>
              </div>
              <div className="star-ratings-base space-x-2 text-lg">
                <span>★</span>
                <span>★</span>
                <span>★</span>
                <span>★</span>
                <span>★</span>
              </div>
            </div>
          </div>
          <NaverMap
            label={hospital.name}
            lat={hospital.geometry.location.lat}
            lng={hospital.geometry.location.lng}
          />
          <button className="btn btn-info mt-4" onClick={handleNavigate}>
            병원 길안내
          </button>
        </label>
      </label>
    </>
  );
};

const Modal = ({ hospital }) => {
  const id = `modal-${hospital.name.toLowerCase().replace(/\s+/g, "-")}`;

  return (
    <>
      <label htmlFor={id} className="btn btn-outline btn-primary rounded-xl">
        {hospital.name}
      </label>
      {ReactDOM.createPortal(
        <ModalOverlay id={id} hospital={hospital} />,
        document.getElementById("overlay-root")
      )}
    </>
  );
};

export default Modal;
