import React from 'react';
import { Link } from 'react-router-dom';

const orders = [
  {
    id: '1234',
    status: 'Delivered',
    date: '2024-06-01',
    total: 28.0
  },
  {
    id: '1235',
    status: 'In Progress',
    date: '2024-06-10',
    total: 15.5
  }
];

const statusColor = (status: string) =>
  status === 'Delivered'
    ? 'bg-green-100 text-green-700'
    : 'bg-yellow-100 text-yellow-700';

const UserOrders: React.FC = () => (
  <div className="min-h-[60vh] flex flex-col items-center justify-center p-8">
    <h2 className="text-2xl font-bold mb-6">My Orders</h2>
    <div className="w-full max-w-2xl bg-white rounded-xl shadow p-6 mb-8">
      <table className="w-full text-left">
        <thead>
          <tr className="border-b">
            <th className="py-2">Order #</th>
            <th className="py-2">Date</th>
            <th className="py-2">Status</th>
            <th className="py-2 text-right">Total</th>
          </tr>
        </thead>
        <tbody>
          {orders.map(order => (
            <tr key={order.id} className="border-b last:border-0">
              <td className="py-2 font-semibold">{order.id}</td>
              <td className="py-2">{order.date}</td>
              <td className="py-2">
                <span className={`px-3 py-1 rounded-full text-xs font-medium ${statusColor(order.status)}`}>{order.status}</span>
              </td>
              <td className="py-2 text-right text-green-700 font-bold">${order.total.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    <div className="flex gap-4">
      <Link to="/profile" className="bg-green-100 text-green-700 px-4 py-2 rounded hover:bg-green-200">Back to Profile</Link>
      <Link to="/products" className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">Shop More</Link>
    </div>
  </div>
);

export default UserOrders;
