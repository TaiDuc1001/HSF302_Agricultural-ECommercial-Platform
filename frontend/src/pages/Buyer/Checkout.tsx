import React from 'react';
import { useNavigate } from 'react-router-dom';

const Checkout: React.FC = () => {
  const navigate = useNavigate();

  const handleCheckout = (e: React.FormEvent) => {
    e.preventDefault();
    navigate('/orders');
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh]">
      <h2 className="text-2xl font-bold mb-4">Checkout</h2>
      <form onSubmit={handleCheckout} className="w-full max-w-xs space-y-4">
        <input
          className="w-full px-3 py-2 border rounded"
          type="text"
          placeholder="Shipping Address"
          required
        />
        <input
          className="w-full px-3 py-2 border rounded"
          type="text"
          placeholder="Payment Method"
          required
        />
        <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">Place Order</button>
      </form>
    </div>
  );
};

export default Checkout;
