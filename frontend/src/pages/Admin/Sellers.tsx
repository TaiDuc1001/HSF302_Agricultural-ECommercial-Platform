import React from 'react';
import { Link } from 'react-router-dom';

const AdminSellers: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Manage Sellers</h2>
    <ul className="mb-6">
      <li>Seller: Farmer John</li>
      <li>Seller: Green Valley Co-op</li>
    </ul>
    <Link to="/content" className="text-green-700 underline hover:text-green-900">Back to Dashboard</Link>
  </div>
);

export default AdminSellers;
