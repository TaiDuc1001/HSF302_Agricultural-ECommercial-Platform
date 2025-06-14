import React from 'react';
import NavCardBase from './NavCardBase';

interface NavCardProps {
  to: string;
  label: string;
  colorClass?: string;
}

const NavCard: React.FC<NavCardProps> = ({ to, label, colorClass }) => (
  <NavCardBase to={to} className={colorClass || 'bg-green-600 text-white hover:bg-green-700'}>
    {label}
  </NavCardBase>
);

export default NavCard;
