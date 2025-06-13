import React, { useEffect } from 'react';
import { useAuth } from '../../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const Logout: React.FC = () => {
  const { logout, user } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    logout();
  }, []);

  useEffect(() => {
    if (user === null) {
      navigate('/');
    }
  }, [user, navigate]);

  return (
    <div className="p-8 text-center">
      <h2 className="text-2xl font-bold mb-4">You have been logged out.</h2>
      <p className="mb-6">Thank you for visiting AgriMarket!</p>
    </div>
  );
};

export default Logout;
