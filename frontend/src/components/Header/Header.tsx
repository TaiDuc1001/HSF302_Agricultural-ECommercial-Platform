import React, { useState } from 'react';
import Button from '../Button/Button';
import { useAuth } from '../../context/AuthContext';
import { useNavigate, Link } from 'react-router-dom';

const NAV_LINKS = [
  { name: 'Home', to: '/' },
  { name: 'Products', to: '/products' },
  { name: 'Sellers', to: '/sellers' },
];

const Header: React.FC = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <nav className="bg-green-700 text-white px-4 py-3 flex items-center justify-between shadow-md relative font-poppins">
      <div className="flex items-center">
        <img src="/logo192.png" alt="AgriMarket Logo" className="h-10 w-10 mr-2" />
        <span className="font-bold text-xl tracking-wide">AgriMarket</span>
      </div>
      <div className="hidden md:flex flex-1 items-center">
        <div className="flex flex-1 justify-center items-center">
          {NAV_LINKS.map(link => (
            <Link
              key={link.name}
              to={link.to}
              className="relative px-3 py-1 font-medium transition-colors duration-200 hover:text-yellow-300 focus:text-yellow-400 group"
            >
              <span className="border-b-2 border-transparent group-hover:border-yellow-300 pb-1 transition-all duration-200">{link.name}</span>
            </Link>
          ))}
        </div>
        {user ? (
          <div className="flex items-center space-x-3 ml-8">
            <div className="w-9 h-9 rounded-full bg-white flex items-center justify-center text-green-700 font-bold text-lg">
              {user.username.charAt(0).toUpperCase()}
            </div>
            <span className="font-semibold">{user.username}</span>
            <Button onClick={handleLogout} secondary>Logout</Button>
          </div>
        ) : (
          <div className="flex space-x-2 ml-8">
            <Button href="/login" primary>Login</Button>
            <Button href="/register" secondary>Register</Button>
          </div>
        )}
      </div>
      <button
        className="md:hidden flex flex-col justify-center items-center focus:outline-none"
        onClick={() => setMenuOpen(!menuOpen)}
        aria-label="Toggle menu"
      >
        <span className={`block w-6 h-0.5 bg-white mb-1 transition-transform ${menuOpen ? 'rotate-45 translate-y-1.5' : ''}`}></span>
        <span className={`block w-6 h-0.5 bg-white mb-1 ${menuOpen ? 'opacity-0' : ''}`}></span>
        <span className={`block w-6 h-0.5 bg-white transition-transform ${menuOpen ? '-rotate-45 -translate-y-1.5' : ''}`}></span>
      </button>
      {menuOpen && (
        <div className="absolute top-16 left-0 w-full bg-green-800 flex flex-col items-center py-4 z-50 md:hidden shadow-lg animate-fade-in">
          {NAV_LINKS.map(link => (
            <Link
              key={link.name}
              to={link.to}
              className="py-2 px-4 w-full text-center hover:bg-green-600 transition-colors font-medium"
              onClick={() => setMenuOpen(false)}
            >
              {link.name}
            </Link>
          ))}
          <Button href="/login" className="w-4/5 my-1" primary>Login</Button>
          <Button href="/register" className="w-4/5 my-1" secondary>Register</Button>
        </div>
      )}
    </nav>
  );
};

export default Header;
