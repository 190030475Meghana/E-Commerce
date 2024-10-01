import React, { useState } from "react";
import axios from "axios";
import "../style/UpdateProfile.css";
import Nav from "./Nav.tsx";

const UpdateProfile: React.FC = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const updatedUser = { email, name, password };

    try {
      const response = await axios.put(
        ``,
        // `http://localhost:8080/api/users/${userId}`,
        updatedUser
      );
      console.log("Updated user:", response.data);
    } catch (error) {
      console.error("Error updating user:", error);
    }
  };

  return (
    <div>
      <Nav />
      <br></br>
      <div className="update-user-form-container">
        <h2 className="title">Update Your Information</h2>
        <form className="update-user-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="label">Email:</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input-field"
              required
            />
          </div>
          <div className="form-group">
            <label className="label">Name:</label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="input-field"
              required
            />
          </div>
          <div className="form-group">
            <label className="label">Password:</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input-field"
              required
            />
          </div>
          <button type="submit" className="submit-button">
            Update
          </button>
        </form>
      </div>
    </div>
  );
};

export default UpdateProfile;
