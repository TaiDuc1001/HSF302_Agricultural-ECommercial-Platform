import React from 'react';

export interface SideBarProps {
  children?: React.ReactNode;
  className?: string;
}

const SideBar: React.FC<SideBarProps> = ({ children, className = '' }) => (
  <aside
    className={`w-72 bg-gradient-to-b from-white via-green-50 to-green-100 border border-green-200 rounded-2xl shadow-xl p-6 flex flex-col gap-8 sticky top-6 transition-all duration-300 hover:shadow-2xl focus-within:shadow-2xl ${className}`}
    tabIndex={0}
  >
    <div className="mb-2 flex items-center gap-2">
      <svg className="w-6 h-6 text-green-600" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
        <path strokeLinecap="round" strokeLinejoin="round" d="M3 7h18M3 12h18M3 17h18" />
      </svg>
      <span className="text-lg font-bold text-green-700 tracking-wide">Filters</span>
    </div>
    {children}
  </aside>
);

export default SideBar;
