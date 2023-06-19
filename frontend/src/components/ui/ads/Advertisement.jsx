import React, { useEffect, useRef } from "react";

import "../../../styles/Advertisement.css";

const Advertisement = () => {
  const reference = useRef();

  useEffect(() => {
    reference.current.innerHTML = "";
    const script = document.createElement("script");
    script.id = "_carbonads_js";
    script.src = `//cdn.carbonads.com/carbon.js?serve=CKYIKKJL&placement=getbootstrapcom`;
    reference.current.appendChild(script);
  }, []);

  return (
    <div
      className="mx-auto items-center carbonads-responsive flex justify-center"
      ref={reference}
    ></div>
  );
};

export default Advertisement;
