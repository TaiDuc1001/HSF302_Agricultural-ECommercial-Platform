import React from 'react';

export interface PaginationBaseProps {
  totalPages: number;
  currentPage: number;
  onPageChange: (page: number) => void;
  children?: React.ReactNode;
}

const PaginationBase: React.FC<PaginationBaseProps> = ({ totalPages, currentPage, onPageChange, children }) => (
  <div className="flex justify-center items-center gap-2 mt-10">
    {children}
  </div>
);

export default PaginationBase;
