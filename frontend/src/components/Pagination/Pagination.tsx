import React from 'react';
import PaginationBase from './PaginationBase';

interface PaginationProps {
  totalPages: number;
  currentPage: number;
  onPageChange: (page: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({ totalPages, currentPage, onPageChange }) => (
  <PaginationBase totalPages={totalPages} currentPage={currentPage} onPageChange={onPageChange}>
    {Array.from({ length: totalPages }, (_, i) => i + 1).map(num => (
      <button
        key={num}
        className={`px-3 py-1 rounded ${num === currentPage ? 'bg-green-600 text-white' : 'bg-white text-green-700 border border-green-600'} hover:bg-green-100 transition`}
        onClick={() => onPageChange(num)}
        disabled={num === currentPage}
      >
        {num}
      </button>
    ))}
  </PaginationBase>
);

export default Pagination;
