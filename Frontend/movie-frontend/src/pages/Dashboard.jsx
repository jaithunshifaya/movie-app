import { useState } from "react";
import API from "../services/api";
import MovieCard from "../components/MovieCard";
import Navbar from "../components/Navbar";

export default function Dashboard() {
  const [movies, setMovies] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
  if (!search) return;

  try {
    setLoading(true);
    const res = await API.get(`/movies?name=${search}`);

    if (res.data && res.data.Search) {
      setMovies(res.data.Search);
    } else {
      setMovies([]);
    }

  } catch (error) {
    console.error(error.response?.data || error.message);
    alert("Search failed. Check console.");
  } finally {
    setLoading(false);
  }
};
  return (
    <>
      <Navbar />

      <div className="dashboard">
        <h1>🎬 Movie Search</h1>

        <div className="page glass">
          <input
            placeholder="Search movie..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
        </div>

        {/* 🔥 Loading */}
        {loading && <h2>Loading...</h2>}

        {/* 🔥 Empty state */}
        {!loading && movies.length === 0 && <p>No movies found</p>}

        <div className="grid">
          {movies.map((m) => (
            <MovieCard key={m.imdbID} movie={m} />
          ))}
        </div>
      </div>
    </>
  );
}