import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faLock, faUserCircle } from "@fortawesome/free-solid-svg-icons";
import "./Login.css";
import { toast } from "react-toastify";

const ForgotPassword = () => {
    const navigate = useNavigate();
  const [error, setError] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!isValidEmail(email)) {
      setError("Please enter a valid email address");
      return;
    }

    try {
      // Send a request to the server to modify the data for the specific email
      const response = await fetch("http://localhost:8080/api/reset-password", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email }),
      });

      if (response.ok) {
        // Password reset successful
        navigate("/");
        toast.success("Password set successfully");
        setEmail("");
      } else {
        // Failed to reset password
        setError("Failed to reset password");
      }
    } catch (error) {
      console.error("Error:", error);
      setError("An error occurred");
    }
  };

  const isValidEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };
  return (
    <>
      <h1 className="fixed-top">Employee Management System</h1>
      <div className="context">
        <div className="contain">
          <div className="form-login">
            <form className="login" onSubmit={handleSubmit}>
              <div className="login-header">
                <FontAwesomeIcon icon={faLock} className="user-icon" />
                <h2>Forgot Password</h2>
              </div><br />
              {error && (
                <div
                  style={{ marginBottom: "10px", color: "red" }}
                  className="error"
                >
                  {error}
                </div>
              )}
              <div className="input-field">
                <FontAwesomeIcon icon={faUser} className="icon" />
                <input
                  type="text"
                  name="email"
                  placeholder="Email"
                  id="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <br />
              <button
                className="text-decoration-none btn btn-sm btn-light"
                type="submit"
              >
                Reset Password
              </button>
              <div className="login-link">
                Remember your password?{" "}
                <Link to="/login" className="link">
                  Login
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default ForgotPassword;
