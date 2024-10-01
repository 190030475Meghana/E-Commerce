import React, { useState } from "react";
import "../style/Register.css";
import Footer from "../blocks/Footer.tsx";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Header from "../blocks/Header.tsx";

const Register = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [street, setStreet] = useState("");
  const [city, setCity] = useState("");
  const [zipcode, setZipCode] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    if (!email || !name || !password || !street || !city || !zipcode) {
      setErrorMessage("Please fill in all fields.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:6789/auth/addNewUser",
        {
          email,
          name,
          password,
          street,
          city,
          zipcode,
        }
      );

      console.log("Registration successful:", response.data);
      navigate("/login");
    } catch (error) {
      console.error("Registration error:", error);
      if (
        error.response &&
        error.response.data &&
        error.response.data.message
      ) {
        setErrorMessage(error.response.data.message);
      } else {
        setErrorMessage("Something went wrong. Please try again later.");
      }
    }
  };

  return (
    <div>
      <Header />
      <br></br>
      <div className="register-page">
        <div className="registration-form-container">
          <h2 style={{ textAlign: "center" }}>Registration</h2>
          <form className="registration-form" onSubmit={handleRegister}>
            <label>Email:</label>
            <input
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input-field"
            />

            <label>Name:</label>
            <input
              type="text"
              placeholder="Enter name"
              value={name}
              onChange={(e) => setName(e.target.value)}
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

            <label>Street Address:</label>
            <input
              type="text"
              placeholder="Enter street address"
              value={street}
              onChange={(e) => setStreet(e.target.value)}
              className="input-field"
            />

            <label>City:</label>
            <input
              type="text"
              placeholder="Enter city"
              value={city}
              onChange={(e) => setCity(e.target.value)}
              className="input-field"
            />

            <label>Zip Code:</label>
            <input
              type="text"
              placeholder="Enter zip code"
              value={zipcode}
              onChange={(e) => setZipCode(e.target.value)}
              className="input-field"
            />

            {errorMessage && <div className="error">{errorMessage}</div>}

            <button type="submit" className="login-button">
              Sign-Up
            </button>

            <p className="right-align">
              Already have an account? <a href="/login">Sign In</a>
            </p>
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Register;
