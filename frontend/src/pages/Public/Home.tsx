import React from 'react';
import { Link } from 'react-router-dom';

const Homepage: React.FC = () => (
  <div className="p-8 text-center">
    <h1 className="text-3xl font-bold mb-4">Welcome to AgriMarket!</h1>
    <p className="mb-6">Your platform for agricultural products and services.</p>
    <div className="flex flex-col items-center space-y-2">
      <Link to="/products" className="text-green-700 underline hover:text-green-900">Browse Products</Link>
      <Link to="/sellers" className="text-green-700 underline hover:text-green-900">Meet Sellers</Link>
      <Link to="/login" className="text-green-700 underline hover:text-green-900">Login</Link>
      <Link to="/register" className="text-green-700 underline hover:text-green-900">Register</Link>
    </div>
  </div>
);

export default Homepage;
