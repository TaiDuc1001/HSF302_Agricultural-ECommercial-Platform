import React from 'react';
import { Link } from 'react-router-dom';

interface NavCardBaseProps {
  children: React.ReactNode;
  className?: string;
  to: string;
}

const NavCardBase: React.FC<NavCardBaseProps> = ({ children, className, to }) => (
  <Link to={to} className={`rounded-lg py-3 font-semibold shadow transition flex items-center justify-center ${className || ''}`}>
    {children}
  </Link>
);

export default NavCardBase;
