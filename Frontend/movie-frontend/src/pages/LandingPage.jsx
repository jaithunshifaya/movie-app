import { useNavigate } from "react-router-dom";

export default function LandingPage() {
  const navigate = useNavigate();

  return (
    <div className="center-container">   
  <div className="overlay glass">   
    
    <h1>🎬 CineVerse</h1>
    <p>Discover movies beyond imagination 🍿</p>

    <button onClick={() => navigate("/login")}>
      Enter Universe →
    </button>

  </div>
 </div>
  );
}