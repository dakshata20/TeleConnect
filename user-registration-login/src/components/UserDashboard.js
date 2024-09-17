import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/UserDashboard.css'; // Import custom styles if needed

const UserDashboard = () => {
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch user data from the backend
    const fetchUserData = async () => {
      try {
        const response = await axios.get('/api/user-data', {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`, // Assuming the token is stored in localStorage
          },
        });
        setUserData(response.data);
      } catch (error) {
        console.error('Error fetching user data:', error);
        // Redirect to login if not authenticated
        navigate('/login');
      }
    };

    fetchUserData();
  }, [navigate]);

  if (!userData) {
    return <div>Loading...</div>; // Show a loading state while fetching data
  }

  return (
    <div className="dashboard-container">
      <h2>User Dashboard</h2>
      <div className="user-info">
        <h3>Personal Information</h3>
        <p><strong>Full Name:</strong> {userData.fullName}</p>
        <p><strong>Email:</strong> {userData.email}</p>
        <p><strong>Phone Number:</strong> {userData.phoneNumber}</p>
        <p><strong>Aadhaar Number:</strong> {userData.aadhaarNumber}</p>
      </div>
    </div>
  );
};

export default UserDashboard;
