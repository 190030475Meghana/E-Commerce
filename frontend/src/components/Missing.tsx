import React from "react";
import { useNavigate } from "react-router-dom";

const Missing = () => {
  const navigate = useNavigate();
  function move(): any {
    setTimeout(() => {
      navigate("/");
    }, 5000);
  }

  return (
    <>
      <h1>404 Page Not Found.</h1>
      <h1>Navigating back to Login.</h1>
      <>{move()}</>
    </>
  );
};

export default Missing;
