import React from 'react';

export interface ProductDisplayProps {
  products: any[];
  children?: React.ReactNode;
}

const ProductDisplay: React.FC<ProductDisplayProps> = ({ products, children }) => (
  <div className="w-full max-w-6xl mx-auto">
    {children}
  </div>
);

export default ProductDisplay;
