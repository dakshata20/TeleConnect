import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // Import useNavigate

function Register() {
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [aadhaarNumber, setAadhaarNumber] = useState('');
  const [aadhaarFile, setAadhaarFile] = useState(null); // State for Aadhaar file
  const navigate = useNavigate(); // Initialize navigate

  // Get the verified email from sessionStorage and set it as the default email
  useEffect(() => {
    const verifiedEmail = sessionStorage.getItem('verifiedEmail');
    if (verifiedEmail) {
      setEmail(verifiedEmail);
    }
  }, []);

  // Handle Aadhaar file change
  const handleAadhaarFileChange = (event) => {
    const file = event.target.files[0];
    const fileType = file.type;

    // Validate file type
    if (fileType === 'image/jpeg' || fileType === 'image/png') {
      setAadhaarFile(file);
    } else {
      alert('Only JPG and PNG files are allowed.');
      event.target.value = ''; // Clear the file input
    }
  };

  async function handleRegister(event) {
    event.preventDefault();

    if (!aadhaarFile) {
      alert('Please upload your Aadhaar file.');
      return;
    }

    try {
      const formData = new FormData();
      formData.append('fullName', fullName);
      formData.append('email', email);
      formData.append('password', password);
      formData.append('phoneNumber', phoneNumber);
      formData.append('aadhaarNumber', aadhaarNumber);
      formData.append('aadhaarFile', aadhaarFile); // Add Aadhaar file to form data

      await axios.post('/api/register', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      alert('Registration successful!');
      navigate('/login'); // Navigate to the login page
    } catch (err) {
      alert('Registration failed. Please try again.');
      console.error(err);
    }
  }

  return (
    <div className="whole">
      <div className="form-container">
        <h1>Registration</h1>
        <form onSubmit={handleRegister}>
          <div className="form-group">
            <label>Name</label>
            <input
              type="text"
              id="fullname"
              name="fullname"
              placeholder="Enter Name"
              value={fullName}
              onChange={(event) => setFullName(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Enter Email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
              readOnly // Make this field read-only
            />
          </div>
          <div className="form-group">
            <label>Aadhaar Number</label>
            <input
              type="text"
              id="aadhaar"
              name="aadhaar"
              placeholder="Enter Aadhaar number"
              value={aadhaarNumber}
              onChange={(event) => setAadhaarNumber(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Phone</label>
            <input
              type="text"
              id="phone"
              name="phone"
              placeholder="Enter Phone number"
              value={phoneNumber}
              onChange={(event) => setPhoneNumber(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Upload Aadhaar</label>
            <input
              type="file"
              id="aadhaarFile"
              name="aadhaarFile"
              onChange={handleAadhaarFileChange} // Handle file change
            />
          </div>
          <button type="submit" className="btn btn-primary">Register</button>
        </form>
      </div>
    </div>
  );
}

export default Register;
