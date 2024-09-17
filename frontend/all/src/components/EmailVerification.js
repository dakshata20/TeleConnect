import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/EmailVerification.css'; // Import custom styles if needed

const EmailVerification = () => {
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [otpSent, setOtpSent] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSendOtp = async () => {
    try {
      await axios.post('/api/send-otp', { email });
      setOtpSent(true);
      setError(null);
      alert('OTP has been sent to your email!');
    } catch (err) {
      setError('Failed to send OTP. Please try again.');
    }
  };

  const handleVerifyOtp = async () => {
    try {
      await axios.post('/api/verify-otp', { email, otp });
      setError(null);
      alert('Email verified successfully!');
      // Store email in session storage
      sessionStorage.setItem('verifiedEmail', email);
      navigate('/register'); // Navigate to the registration page
    } catch (err) {
      setError('Invalid OTP. Please try again.');
    }
  };

  return (
    <div className="page-wrapper">
      <div className="email-verification-container">
        <h2>Email Verification</h2>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            disabled={otpSent}
            required
          />
          <button
            className="btn btn-primary"
            onClick={handleSendOtp}
            disabled={otpSent || !email}
          >
            Send OTP
          </button>
        </div>

        {otpSent && (
          <div className="form-group">
            <label htmlFor="otp">OTP</label>
            <input
              type="text"
              id="otp"
              className="form-control"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              required
            />
            <button
              className="btn btn-success"
              onClick={handleVerifyOtp}
              disabled={!otp}
            >
              Verify
            </button>
          </div>
        )}

        {error && <p className="error-message">{error}</p>}
      </div>
    </div>
  );
};

export default EmailVerification;
