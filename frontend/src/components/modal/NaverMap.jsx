import React, { useEffect } from "react";

const { naver } = window;

const NaverMap = ({ label, lat, lng }) => {
  const id = `map-${label.toLowerCase().replace(/\s+/g, "-")}`;

  useEffect(() => {
    const map = new naver.maps.Map(id, {
      center: new naver.maps.LatLng(lat, lng),
      zoom: 15,
    });

    const marker = new naver.maps.Marker({
      position: new naver.maps.LatLng(lat, lng),
      map: map,
    });
  }, [id, lat, lng]);

  return <div id={id} style={{ width: "100%", height: "350px" }}></div>;
};

export default NaverMap;
