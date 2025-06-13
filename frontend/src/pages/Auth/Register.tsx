import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth, UserRole } from '../../context/AuthContext';

const Register: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [role, setRole] = useState<UserRole>('buyer');
  const navigate = useNavigate();
  const { register } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const result = await register(username, password, role as UserRole);
    if (result === 'success') {
      navigate('/profile');
    } else {
      alert('Registration failed: Username already exists.');
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh]">
      <h2 className="text-2xl font-bold mb-4">Register</h2>
      <form onSubmit={handleSubmit} className="w-full max-w-xs space-y-4">
        <input
          className="w-full px-3 py-2 border rounded"
          type="text"
          placeholder="Username"
          value={username}
          onChange={e => setUsername(e.target.value)}
          required
        />
        <input
          className="w-full px-3 py-2 border rounded"
          type="email"
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
          required
        />
        <input
          className="w-full px-3 py-2 border rounded"
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
        />
        <select
          className="w-full px-3 py-2 border rounded"
          value={role}
          onChange={e => setRole(e.target.value as UserRole)}
        >
          <option value="buyer">Buyer</option>
          <option value="seller">Seller</option>
        </select>
        <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">Register</button>
      </form>
    </div>
  );
};

export default Register;
