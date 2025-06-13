import React from 'react';
import { Link } from 'react-router-dom';

const Sellers: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Top Sellers</h2>
    <ul className="mb-6">
      <li>Farmer John</li>
      <li>Green Valley Co-op</li>
      <li>AgroMart</li>
    </ul>
    <Link to="/products" className="text-green-700 underline hover:text-green-900 mr-4">Browse Products</Link>
    <Link to="/register" className="text-green-700 underline hover:text-green-900">Become a Seller</Link>
  </div>
);

export default Sellers;
