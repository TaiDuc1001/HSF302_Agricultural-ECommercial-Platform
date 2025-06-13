import React from 'react';

export interface EmptyStateBaseProps {
  message?: string;
  children?: React.ReactNode;
}

const EmptyStateBase: React.FC<EmptyStateBaseProps> = ({ message = 'No items found.', children }) => (
  <div className="w-full text-center py-16 text-gray-500 text-lg">
    {message}
    {children}
  </div>
);

export default EmptyStateBase;
