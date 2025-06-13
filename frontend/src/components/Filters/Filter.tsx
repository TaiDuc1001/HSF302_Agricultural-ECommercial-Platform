import React from 'react';

export interface FilterProps {
  label: string;
  children?: React.ReactNode;
}

const Filter: React.FC<FilterProps> = ({ label, children }) => (
  <div className="mb-4">
    <h3 className="font-semibold mb-2">{label}</h3>
    {children}
  </div>
);

export default Filter;
