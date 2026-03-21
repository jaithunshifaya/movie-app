import { useState } from "react";
import API from "../services/api";

export default function MovieCard({ movie }) {
  const [details, setDetails] = useState(null);

  const getDetails = async () => {
    try {
      const res = await API.get(`/movies/details?id=${movie.imdbID}`);
      setDetails(res.data);
    } catch (err) {
      console.error(err);
      alert("Failed to fetch details");
    }
  };

  const addToFavorites = async () => {
  try {
    await API.post("/favorites", {
      imdbID: movie.imdbID,
      title: movie.Title,
      poster: movie.Poster,
      year: movie.Year,
    });
    alert("Added to Favorites ❤️");
  } catch (err) {
    console.error(err);
  }
};

  
  const image =
    movie.Poster && movie.Poster !== "N/A"
      ? movie.Poster
      : "https://via.placeholder.com/300x400?text=No+Image";

  return (
    <>
      {/* 🎬 MOVIE CARD */}
      <div className="movie-card" onClick={getDetails}>
        <img src={image} alt={movie.Title} />
        <h3>{movie.Title}</h3>
        <p>{movie.Year}</p>
      </div>

      {/* 🔥 MODAL */}
      {details && (
        <div className="modal" onClick={() => setDetails(null)}>
          <div
            className="modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <h2>{details.Title}</h2>

            <img
              src={
                details.Poster !== "N/A"
                  ? details.Poster
                  : "https://via.placeholder.com/300x400?text=No+Image"
              }
              alt=""
            />

            <p><b>Year:</b> {details.Year}</p>
            <p><b>Genre:</b> {details.Genre}</p>
            <p><b>Plot:</b> {details.Plot}</p>

            <button onClick={() => setDetails(null)}>Close</button>
            <button onClick={(e) => { e.stopPropagation(); 
            addToFavorites(); }}> ❤️ Favorite
            </button>
          </div>
        </div>
      )}
    </>
  );
}