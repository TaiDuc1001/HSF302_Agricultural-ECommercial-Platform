import React from 'react';
import { Link } from 'react-router-dom';

const AdminUsers: React.FC = () => (
  <div className="p-8 text-center">
    <h2 className="text-2xl font-bold mb-4">Manage Users</h2>
    <ul className="mb-6">
      <li>User: Alice</li>
      <li>User: Bob</li>
    </ul>
    <Link to="/content" className="text-green-700 underline hover:text-green-900">Back to Dashboard</Link>
  </div>
);

export default AdminUsers;
