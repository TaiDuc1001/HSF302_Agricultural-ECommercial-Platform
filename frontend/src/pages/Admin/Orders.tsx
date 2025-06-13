import React from 'react';
import { Link } from 'react-router-dom';

const AdminOrders: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Manage Orders</h2>
    <ul className="mb-6">
      <li>Order #A-2001 - Delivered</li>
      <li>Order #A-2002 - Pending</li>
    </ul>
    <Link to="/content" className="text-green-700 underline hover:text-green-900">Back to Dashboard</Link>
  </div>
);

export default AdminOrders;
