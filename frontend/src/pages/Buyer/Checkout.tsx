import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const cartItems = [
  {
    id: 1,
    name: 'Rice',
    image: 'https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=facearea&w=96&h=96',
    quantity: 2,
    price: 10
  },
  {
    id: 2,
    name: 'Corn',
    image: 'https://images.unsplash.com/photo-1465101178521-c1a9136a3b43?auto=format&fit=facearea&w=96&h=96',
    quantity: 1,
    price: 8
  }
];

const total = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);

const Checkout: React.FC = () => {
  const navigate = useNavigate();
  const [address, setAddress] = useState('123 Main St');
  const [payment, setPayment] = useState('Credit Card');

  const handleCheckout = (e: React.FormEvent) => {
    e.preventDefault();
    navigate('/orders');
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh] p-8">
      <h2 className="text-2xl font-bold mb-6">Checkout</h2>
      <div className="w-full max-w-2xl bg-white rounded-xl shadow p-8 mb-8">
        <h3 className="text-lg font-semibold mb-4">Order Summary</h3>
        <table className="w-full text-left mb-4">
          <thead>
            <tr className="border-b">
              <th className="py-2">Product</th>
              <th className="py-2">Qty</th>
              <th className="py-2 text-right">Price</th>
              <th className="py-2 text-right">Total</th>
            </tr>
          </thead>
          <tbody>
            {cartItems.map(item => (
              <tr key={item.id} className="border-b last:border-0">
                <td className="py-2 flex items-center gap-3">
                  <img src={item.image} alt={item.name} className="w-10 h-10 rounded object-cover" />
                  <span>{item.name}</span>
                </td>
                <td className="py-2">{item.quantity}</td>
                <td className="py-2 text-right">${item.price.toFixed(2)}</td>
                <td className="py-2 text-right font-semibold">${(item.price * item.quantity).toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="flex justify-between items-center mt-4 mb-6">
          <span className="font-bold text-lg">Total:</span>
          <span className="text-green-700 font-bold text-xl">${total.toFixed(2)}</span>
        </div>
        <h3 className="text-lg font-semibold mb-2">Shipping & Payment</h3>
        <form onSubmit={handleCheckout} className="w-full space-y-4">
          <input
            className="w-full px-3 py-2 border rounded"
            type="text"
            placeholder="Shipping Address"
            value={address}
            onChange={e => setAddress(e.target.value)}
            required
          />
          <select
            className="w-full px-3 py-2 border rounded"
            value={payment}
            onChange={e => setPayment(e.target.value)}
            required
          >
            <option value="Credit Card">Credit Card</option>
            <option value="Bank Transfer">Bank Transfer</option>
            <option value="Cash on Delivery">Cash on Delivery</option>
          </select>
          <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">Place Order</button>
        </form>
      </div>
    </div>
  );
};

export default Checkout;
