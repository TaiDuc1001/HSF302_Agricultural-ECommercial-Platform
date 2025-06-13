import React from 'react';
import { Link } from 'react-router-dom';

const Products: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">All Products</h2>
    <ul className="mb-6">
      <li>Rice - $10</li>
      <li>Wheat - $12</li>
      <li>Corn - $8</li>
    </ul>
    <Link to="/sellers" className="text-green-700 underline hover:text-green-900 mr-4">View Sellers</Link>
    <Link to="/cart" className="text-green-700 underline hover:text-green-900">Go to Cart</Link>
  </div>
);

export default Products;
