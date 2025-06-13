import React from 'react';
import { Link } from 'react-router-dom';

const Cart: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
    <ul className="mb-6">
      <li>Rice x 2 - $20</li>
      <li>Corn x 1 - $8</li>
    </ul>
    <Link to="/checkout" className="text-green-700 underline hover:text-green-900 mr-4">Checkout</Link>
    <Link to="/orders" className="text-green-700 underline hover:text-green-900 mr-4">View Orders</Link>
    <Link to="/products" className="text-green-700 underline hover:text-green-900">Continue Shopping</Link>
  </div>
);

export default Cart;
