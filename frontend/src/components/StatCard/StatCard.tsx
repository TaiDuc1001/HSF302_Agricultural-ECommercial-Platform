import React from 'react';
import StatCardBase from './StatCardBase';

interface StatCardProps {
  value: string | number;
  label: string;
  colorClass?: string;
}

const StatCard: React.FC<StatCardProps> = ({ value, label, colorClass }) => (
  <StatCardBase
    value={<span className={`text-lg font-bold ${colorClass || 'text-green-700'}`}>{value}</span>}
    label={label}
    className="bg-green-100"
  />
);

export default StatCard;
