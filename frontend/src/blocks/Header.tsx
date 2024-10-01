import React from "react";
import "../style/Nav.css";
import "../style/Header.css";
import { useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();

  const handleRegister = () => {
    navigate("/register");
  };

  const handleLogin = () => {
    navigate("/login");
  };

  return (
    <div>
      <header className="header">
        <h3 style={{ fontSize: "24px" }}>ElectriMart</h3>
        <button className="register-button" onClick={handleRegister}>
          Sign-Up
        </button>
        <button className="login-button" onClick={handleLogin}>
          Sign-In
        </button>
      </header>
    </div>
  );
};

export default Header;
