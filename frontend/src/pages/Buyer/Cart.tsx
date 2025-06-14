import React from 'react';
import { Link } from 'react-router-dom';

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

const Cart: React.FC = () => (
  <div className="min-h-[60vh] flex flex-col items-center justify-center p-8">
    <h2 className="text-2xl font-bold mb-6">Your Cart</h2>
    <div className="w-full max-w-2xl bg-white rounded-xl shadow p-6 mb-8">
      {cartItems.length === 0 ? (
        <div className="text-center text-gray-500">Your cart is empty.</div>
      ) : (
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
      )}
      <div className="flex justify-between items-center mt-4">
        <span className="font-bold text-lg">Total:</span>
        <span className="text-green-700 font-bold text-xl">${total.toFixed(2)}</span>
      </div>
    </div>
    <div className="flex gap-4">
      <Link to="/checkout" className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">Checkout</Link>
      <Link to="/orders" className="bg-green-100 text-green-700 px-4 py-2 rounded hover:bg-green-200">View Orders</Link>
      <Link to="/products" className="bg-green-100 text-green-700 px-4 py-2 rounded hover:bg-green-200">Continue Shopping</Link>
    </div>
  </div>
);

export default Cart;
