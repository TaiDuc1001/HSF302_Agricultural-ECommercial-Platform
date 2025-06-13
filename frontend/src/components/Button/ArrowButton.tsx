import React from 'react';

interface ArrowButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  direction: 'left' | 'right';
  variant?: 'filled' | 'outlined';
}

const ArrowButton: React.FC<ArrowButtonProps> = ({ direction, variant = 'filled', ...props }) => {
  const base =
    'p-2 rounded-full flex items-center justify-center transition-colors duration-150';
  const filled =
    'bg-green-600 text-white hover:bg-green-700 disabled:bg-gray-200 disabled:text-gray-400';
  const outlined =
    'border border-green-600 text-green-600 bg-white hover:bg-green-50 disabled:border-gray-200 disabled:text-gray-300';
  const icon = direction === 'left' ? (
    <svg width="24" height="24" fill="none" viewBox="0 0 24 24"><path d="M15 6l-6 6 6 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/></svg>
  ) : (
    <svg width="24" height="24" fill="none" viewBox="0 0 24 24"><path d="M9 6l6 6-6 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/></svg>
  );
  return (
    <button
      {...props}
      className={[
        base,
        variant === 'filled' ? filled : outlined,
        props.className || '',
      ].join(' ')}
      aria-label={direction === 'left' ? 'Scroll left' : 'Scroll right'}
    >
      {icon}
    </button>
  );
};

export default ArrowButton;
