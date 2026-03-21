import { useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div className="navbar glass">
      <h2>🎬 CineVerse</h2>

      <div className="nav-actions">
        <button onClick={() => navigate("/dashboard")}>Home</button>
        <button onClick={() => navigate("/favorites")}>❤️ Favorites</button>
        <button onClick={() => navigate("/history")}>🕘 History</button>
        <button onClick={handleLogout}>Logout</button>
      </div>
    </div>
  );
}