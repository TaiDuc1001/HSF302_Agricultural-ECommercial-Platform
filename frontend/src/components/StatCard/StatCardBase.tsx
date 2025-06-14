import React from 'react';

interface StatCardBaseProps {
  value: React.ReactNode;
  label: string;
  className?: string;
}

const StatCardBase: React.FC<StatCardBaseProps> = ({ value, label, className }) => (
  <div className={`rounded-xl p-4 flex flex-col items-center ${className || ''}`}>
    {value}
    <span className="text-xs text-gray-500">{label}</span>
  </div>
);

export default StatCardBase;
