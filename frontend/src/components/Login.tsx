import React, { useState } from "react";
import "../style/Register.css";
import Footer from "../blocks/Footer.tsx";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Header from "../blocks/Header.tsx";
import { useDispatch, useSelector } from "react-redux";
import { setSavedToken } from "../feature/redux/slice/tokenSlice.ts";

const Login = () => {
  const dispatch = useDispatch();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    if (!username || !password) {
      setErrorMessage("Please fill in all fields.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:6789/auth/generateToken",
        {
          username,
          password,
        }
      );

      dispatch(
        setSavedToken({
          token: response.data.token,
          userId: response.data.userId,
          username: response.data.username,
        })
      );

      console.log("Login successful:", response.data);
      navigate("/home");
    } catch (error) {
      console.error("Login error:", error);
      if (
        error.response &&
        error.response.data &&
        error.response.data.message
      ) {
        setErrorMessage(error.response.data.message);
      } else {
        setErrorMessage("Credentials are wrong. Try again!!");
      }
    }
  };

  return (
    <div>
      {" "}
      <Header />
      <div className="register-page">
        <div className="registration-form-container">
          <h2 style={{ textAlign: "center" }}>Login</h2>
          <form className="registration-form" onSubmit={handleRegister}>
            <label>Email:</label>
            <input
              type="email"
              placeholder="Enter email"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="input-field"
            />

            <label>Password:</label>
            <input
              type="password"
              placeholder="Enter password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input-field"
            />

            {errorMessage && <div className="error">{errorMessage}</div>}

            <button type="submit" className="login-button">
              Login
            </button>

            <p className="right-align">
              Didn't have an account? <a href="/">Sign Up</a>
            </p>
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
