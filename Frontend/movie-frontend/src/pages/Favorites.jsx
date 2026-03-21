import { useEffect, useState } from "react";
import API from "../services/api";

export default function Favorites() {
  const [movies, setMovies] = useState([]);

  useEffect(() => {
    const fetchFavorites = async () => {
      try {
        const res = await API.get("/favorites");
        setMovies(res.data);
      } catch (err) {
        console.error("ERROR:", err.response?.data || err.message); // 🔥 better debug
        alert("Failed to load favorites");
      }
    };

    fetchFavorites();
  }, []);

  const deleteFavorite = async (id) => {
  try {
    console.log("Deleting ID:", id); 

    await API.delete(`/favorites/${id}`);

    setMovies(movies.filter((m) => m.id !== id));

    alert("Deleted successfully ✅");

  } catch (err) {
    console.error("DELETE ERROR:", err.response?.data || err.message);
    alert(err.response?.data || "Failed to delete");
  }
};

  return (
    <div className="dashboard">
      <h1>❤️ Favorites</h1>

      <div className="page glass">
        <div className="grid">
          {movies.map((m) => (
            <div className="movie-card" key={m.id}>
              <img src={m.poster} alt="" />
              <h3>{m.title}</h3>
              <p>{m.year}</p>

              <button onClick={() => deleteFavorite(m.id)}>
                ❌ Remove
              </button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}