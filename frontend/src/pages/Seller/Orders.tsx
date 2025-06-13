import React from 'react';
import { Link } from 'react-router-dom';

const SellerOrders: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">My Orders</h2>
    <ul className="mb-6">
      <li>Order #S-1001 - Pending</li>
      <li>Order #S-1002 - Shipped</li>
    </ul>
    <Link to="/my-products" className="text-green-700 underline hover:text-green-900 mr-4">My Products</Link>
    <Link to="/content" className="text-green-700 underline hover:text-green-900">Back to Dashboard</Link>
  </div>
);

export default SellerOrders;
