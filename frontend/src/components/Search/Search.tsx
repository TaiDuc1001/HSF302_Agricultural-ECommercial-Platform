import React from 'react';

export interface SearchProps {
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  children?: React.ReactNode;
}

const Search: React.FC<SearchProps> = ({ value, onChange, children }) => (
  <div className="w-full relative">
    <input
      type="text"
      value={value}
      onChange={onChange}
      className="w-full border border-gray-300 rounded-full py-2 px-5 pr-10 focus:outline-none focus:ring-2 focus:ring-green-400 shadow-sm"
    />
    {children}
  </div>
);

export default Search;
