import React from 'react';
import { Link } from 'react-router-dom';

const Dashboard: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Admin Dashboard</h2>
    <p className="mb-6">Manage users, products, sellers, and orders.</p>
    <div className="flex flex-col items-center space-y-2">
      <Link to="/users" className="text-green-700 underline hover:text-green-900">Users</Link>
      <Link to="/products" className="text-green-700 underline hover:text-green-900">Products</Link>
      <Link to="/sellers" className="text-green-700 underline hover:text-green-900">Sellers</Link>
      <Link to="/orders" className="text-green-700 underline hover:text-green-900">Orders</Link>
    </div>
  </div>
);

export default Dashboard;
