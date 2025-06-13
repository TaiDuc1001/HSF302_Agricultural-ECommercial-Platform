import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Seller Dashboard</h2>
    <p className="mb-6">Manage your products and orders here.</p>
    <div className="flex flex-col items-center space-y-2">
      <Link to="/my-products" className="text-green-700 underline hover:text-green-900">My Products</Link>
      <Link to="/my-orders" className="text-green-700 underline hover:text-green-900">My Orders</Link>
    </div>
  </div>
);

export default Home;
