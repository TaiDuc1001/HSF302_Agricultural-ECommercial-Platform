import React from 'react';

export interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  primary?: boolean;
  secondary?: boolean;
  children: React.ReactNode;
  href?: string;
}

const baseStyle =
  'px-5 py-2 rounded font-semibold transition-all duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2';

const buttonStyle =
  'bg-green-600 text-white hover:bg-yellow-400 hover:text-green-800 focus:ring-green-400';

const secondaryStyle =
  'bg-transparent text-white border-2 border-white hover:bg-white hover:text-green-700 focus:ring-green-400';

const Button: React.FC<ButtonProps> = ({ children, className = '', href, primary, secondary, ...props }) => {
  const style = secondary ? secondaryStyle : buttonStyle;
  if (href) {
    return (
      <a
        href={href}
        className={`${baseStyle} ${style} ${className} inline-block text-center`}
        {...props as any}
      >
        {children}
      </a>
    );
  }
  return (
    <button
      className={`${baseStyle} ${style} ${className}`}
      {...props}
    >
      {children}
    </button>
  );
};

export default Button;
