import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Welcome, User!</h2>
    <p className="mb-6">This is your homepage. Here you can view your info and manage your account.</p>
    <div className="flex flex-col items-center space-y-2">
      <Link to="/orders" className="text-green-700 underline hover:text-green-900">My Orders</Link>
      <Link to="/cart" className="text-green-700 underline hover:text-green-900">My Cart</Link>
      <Link to="/profile" className="text-green-700 underline hover:text-green-900">Manage Profile</Link>
      <Link to="/logout" className="text-green-700 underline hover:text-green-900">Logout</Link>
    </div>
  </div>
);

export default Home;
