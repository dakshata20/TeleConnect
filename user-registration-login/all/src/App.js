import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import "./App.css";
import LandingPage from './components/LandingPage';
import EmailVerification from './components/EmailVerification';
import UserDashboard from './components/UserDashboard';

function App() {
  return (
    <Router>
      {}
      <div className="App">
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<LandingPage />} />
          <Route path="/" element={<LandingPage />} />
          <Route path="/email-verification" element={<EmailVerification />} />
          <Route path="/ud" element={<UserDashboard />} />
          
        </Routes>
      </div>
    </Router>
  );
}

export default App;
