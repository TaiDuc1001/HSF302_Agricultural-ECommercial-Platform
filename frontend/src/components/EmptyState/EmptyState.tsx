import React from 'react';
import EmptyStateBase from './EmptyStateBase';

interface EmptyStateProps {
  message?: string;
}

const EmptyState: React.FC<EmptyStateProps> = ({ message = 'No items found.' }) => (
  <EmptyStateBase message={message} />
);

export default EmptyState;
