import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';

const Login: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const result = await login(username, password);
    if (result === 'success') {
      const from = (location.state as any)?.from?.pathname || '/content';
      navigate(from, { replace: true });
    } else {
      setError('Invalid credentials. Try admin/admin123, buyer/buyer123, or seller/seller123.');
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh]">
      <h2 className="text-2xl font-bold mb-4">Login</h2>
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
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
        />
        {error && <div className="text-red-600 text-sm">{error}</div>}
        <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">Login</button>
      </form>
    </div>
  );
};

export default Login;
