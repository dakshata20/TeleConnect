import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [aadhaarNumber, setAadhaarNumber] = useState('');
  const [aadhaarFile, setAadhaarFile] = useState(null);
  const navigate = useNavigate();

  // Get the verified email from sessionStorage and set it as the default email
  useEffect(() => {
    const verifiedEmail = sessionStorage.getItem('verifiedEmail');
    if (verifiedEmail) {
      console.log('Verified email retrieved from session:', verifiedEmail);
      setEmail(verifiedEmail);
    } else {
      alert('Email not verified. Please verify your email before registration.');
      navigate('/email-verification'); // Redirect to email verification if not present
    }
  }, [navigate]);
  

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

    // Split the full name into firstName and lastName
    const [firstName, lastName = ''] = fullName.trim().split(' '); // Default lastName to an empty string

    // Check if firstName is valid
    if (!firstName) {
      alert('Please enter both first and last names.');
      return;
    }

    try {
      const formData = new FormData();
      formData.append('firstName', firstName.trim());
      formData.append('lastName', lastName.trim());
      formData.append('email', email.trim());
      formData.append('password', password.trim());
      formData.append('phoneNumber', phoneNumber.trim());
      formData.append('aadharNumber', aadhaarNumber.trim());
      formData.append('aadharImage', aadhaarFile);

      // Make sure the API URL is correct
      await axios.post('http://localhost:8082/users/register', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      alert('Registration successful!');
      navigate('/login'); // Navigate to the login page
    } catch (err) {
      // Improved error handling
      if (err.response) {
        console.error('Error Response:', err.response.data); // Log server response
        alert(`Registration failed: ${err.response.data.message || 'Unknown error'}`);
      } else {
        console.error('Error:', err.message);
        alert('Registration failed. Please try again.');
      }
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
              placeholder="Enter Full Name"
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
              readOnly
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
              onChange={handleAadhaarFileChange}
            />
          </div>
          <button type="submit" className="btn btn-primary">Register</button>
        </form>
      </div>
    </div>
  );
}

export default Register;
