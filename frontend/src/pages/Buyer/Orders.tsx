import React from 'react';
import { Link } from 'react-router-dom';

const UserOrders: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">My Orders</h2>
    <ul className="mb-6">
      <li>Order #1234 - Delivered</li>
      <li>Order #1235 - In Progress</li>
    </ul>
    <Link to="/content" className="text-green-700 underline hover:text-green-900 mr-4">Back to Profile</Link>
    <Link to="/products" className="text-green-700 underline hover:text-green-900">Shop More</Link>
  </div>
);

export default UserOrders;
