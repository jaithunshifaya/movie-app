import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

export default function LoginPage() {
  const navigate = useNavigate();

  const [isLogin, setIsLogin] = useState(true);

  const [form, setForm] = useState({
    email: "",
    password: "",
    name: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      if (isLogin) {
      
        const res = await API.post("/auth/login", {
          email: form.email,
          password: form.password
        });

        console.log("LOGIN RESPONSE:", res.data); 

        const token = res.data.token;

        
        if (!token || !token.includes(".")) {
          alert("Invalid token received from server");
          return;
        }

       
        localStorage.setItem("token", token);

        alert("Login successful ✅");

        navigate("/dashboard");

      } else {
       
        const res = await API.post("/auth/register", {
          name: form.name,
          email: form.email,
          password: form.password
        });

        console.log("REGISTER RESPONSE:", res.data);

        alert("Account created! Please login.");
        setIsLogin(true);
      }

    } catch (err) {
      console.error("LOGIN ERROR:", err.response?.data || err.message);
      alert(err.response?.data || "Something went wrong");
    }
  };

  return (
    <div className="center-container">
      <div className="login-box glass">

        <h2>{isLogin ? "Login" : "Sign Up"}</h2>

        {/* 🆕 NAME FIELD ONLY FOR SIGNUP */}
        {!isLogin && (
          <input
            type="text"
            name="name"
            placeholder="Enter Name"
            onChange={handleChange}
          />
        )}

        <input
          type="email"
          name="email"
          placeholder="Enter Email"
          onChange={handleChange}
        />

        <input
          type="password"
          name="password"
          placeholder="Enter Password"
          onChange={handleChange}
        />

        <button onClick={handleSubmit}>
          {isLogin ? "Login" : "Register"}
        </button>

        {/* 🔁 TOGGLE */}
        <p style={{ marginTop: "15px", cursor: "pointer" }}>
          {isLogin ? "Don't have an account? " : "Already have an account? "}
          <span onClick={() => setIsLogin(!isLogin)} style={{ color: "#00f" }}>
            {isLogin ? "Sign Up" : "Login"}
          </span>
        </p>

      </div>
    </div>
  );
}