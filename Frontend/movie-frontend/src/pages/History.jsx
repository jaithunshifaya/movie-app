import { useEffect, useState } from "react";
import API from "../services/api";

export default function History() {
  const [history, setHistory] = useState([]);

  useEffect(() => {
  const fetchHistory = async () => {
    try {
      const res = await API.get("/movies/history");
      setHistory(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  fetchHistory();
}, []);

  
  const deleteHistory = async (id) => {
    try {
      await API.delete(`/movies/history/${id}`);

      
      setHistory(history.filter((h) => h.id !== id));

    } catch (err) {
      console.error(err);
      alert("Delete failed");
    }
  };

  
  const clearAll = async () => {
    try {
      await API.delete("/movies/history/clear");
      setHistory([]);
    } catch (err) {
      console.error(err);
      alert("Clear failed");
    }
  };

  return (
    <div className="dashboard">
      <h1>🕘 Search History</h1>

      {/* 🔥 CLEAR BUTTON */}
      {history.length > 0 && (
        <button onClick={clearAll}>Clear All</button>
      )}

      {history.map((h) => (
        <div className="card glass" key={h.id}>
          {/* ✅ FIXED FIELD */}
          <p>🔍 {h.searchQuery}</p>

          <small>
            {new Date(h.searchedAt).toLocaleString()}
          </small>

          {/* 🔥 DELETE BUTTON */}
          <button onClick={() => deleteHistory(h.id)}>
            ❌ Delete
          </button>
        </div>
      ))}
    </div>
  );
}