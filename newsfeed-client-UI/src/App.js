import "./App.css";

import { BrowserRouter } from "react-router-dom";
import Home from "./pages/Home";
import Navbar from "./pages/Navbar";
import React from "react";
import uuid from "react-uuid";

const userId = uuid();

function App() {
  return (
    <BrowserRouter>
      <Navbar userId={userId} />
      <Home userId={userId} />
    </BrowserRouter>
  );
}

export default App;
