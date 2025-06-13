import React from 'react';
import Search from './Search';

interface SearchBarProps {
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  suggestions?: string[];
  onSuggestionClick?: (s: string) => void;
  showDropdown?: boolean;
  onFocus?: () => void;
  onBlur?: () => void;
  placeholder?: string;
}

const SearchBar: React.FC<SearchBarProps> = ({
  value,
  onChange,
  suggestions = [],
  onSuggestionClick,
  showDropdown = false,
  onFocus,
  onBlur,
  placeholder = 'Search...'
}) => (
  <Search value={value} onChange={onChange}>
    {/* Search Icon */}
    <span className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 pointer-events-none">
      <svg width="20" height="20" fill="none" viewBox="0 0 24 24">
        <circle cx="11" cy="11" r="7" stroke="currentColor" strokeWidth="2" />
        <path d="M20 20l-3.5-3.5" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />
      </svg>
    </span>
    {showDropdown && suggestions.length > 0 && (
      <ul className="absolute left-0 right-0 bg-white border border-gray-200 rounded shadow-lg mt-1 z-10">
        {suggestions.map(s => (
          <li
            key={s}
            className="px-4 py-2 hover:bg-green-50 cursor-pointer"
            onMouseDown={() => onSuggestionClick && onSuggestionClick(s)}
          >
            {s}
          </li>
        ))}
      </ul>
    )}
  </Search>
);

export default SearchBar;
