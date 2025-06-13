import React from 'react';
import { Link } from 'react-router-dom';

const SellerProducts: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">My Products</h2>
    <ul className="mb-6">
      <li>Organic Rice - 100kg</li>
      <li>Fresh Corn - 50kg</li>
    </ul>
    <Link to="/my-orders" className="text-green-700 underline hover:text-green-900 mr-4">View Orders</Link>
    <Link to="/content" className="text-green-700 underline hover:text-green-900">Back to Dashboard</Link>
  </div>
);

export default SellerProducts;
