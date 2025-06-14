import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Profile: React.FC = () => {
  const [address, setAddress] = useState('123 Main St');
  const [phone, setPhone] = useState('');
  const navigate = useNavigate();

  const handleSave = (e: React.FormEvent) => {
    e.preventDefault();
    alert('Profile updated!');
    navigate('/content');
  };

  return (
    <div className="min-h-[60vh] flex flex-col items-center justify-center p-8">
      <div className="w-full max-w-md bg-white rounded-xl shadow p-8 flex flex-col items-center">
        <img src="https://ui-avatars.com/api/?name=User&background=34d399&color=fff&size=96" alt="Profile" className="w-24 h-24 rounded-full mb-4 border-4 border-green-200" />
        <h2 className="text-2xl font-bold mb-4">Manage Buyer Profile</h2>
        <form onSubmit={handleSave} className="w-full space-y-4">
          <input
            className="w-full px-3 py-2 border rounded"
            type="text"
            placeholder="Shipping Address"
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
          <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">Save Changes</button>
        </form>
      </div>
    </div>
  );
};

export default Profile;
