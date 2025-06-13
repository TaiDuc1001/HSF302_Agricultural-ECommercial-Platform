import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Required: React.FC = () => {
  const [businessName, setBusinessName] = useState('');
  const [address, setAddress] = useState('');
  const [phone, setPhone] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    navigate('/required');
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh]">
      <h2 className="text-2xl font-bold mb-4">Complete Your Seller Profile</h2>
      <form onSubmit={handleSubmit} className="w-full max-w-xs space-y-4">
        <input
          className="w-full px-3 py-2 border rounded"
          type="text"
          placeholder="Business Name"
          value={businessName}
          onChange={e => setBusinessName(e.target.value)}
          required
        />
        <input
          className="w-full px-3 py-2 border rounded"
          type="text"
          placeholder="Business Address"
          value={address}
          onChange={e => setAddress(e.target.value)}
          required
        />
        <input
          className="w-full px-3 py-2 border rounded"
          type="tel"
          placeholder="Phone Number"
          value={phone}
          onChange={e => setPhone(e.target.value)}
          required
        />
        <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">Save</button>
      </form>
    </div>
  );
};

export default Required;
