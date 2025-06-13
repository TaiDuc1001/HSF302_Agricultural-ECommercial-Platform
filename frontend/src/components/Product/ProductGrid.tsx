import React from 'react';
import ProductDisplay from './ProductDisplay';
import ProductCard from './ProductCard';

interface Product {
  id: number;
  name: string;
  image: string;
  [key: string]: any;
}

interface ProductGridProps {
  products: Product[];
  columns?: number;
}

const ProductGrid: React.FC<ProductGridProps> = ({ products, columns = 4 }) => (
  <ProductDisplay products={products}>
    <div className={`grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-${columns} gap-8 w-full max-w-6xl`}>
      {products.map(product => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  </ProductDisplay>
);

export default ProductGrid;
