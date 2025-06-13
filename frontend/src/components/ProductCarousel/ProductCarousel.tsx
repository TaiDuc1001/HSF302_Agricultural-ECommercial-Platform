import React, { useState } from 'react';
import Button from '../Button/Button';
import ArrowButton from '../Button/ArrowButton';

interface Product {
  id: number;
  name: string;
  image: string;
}

interface ProductCarouselProps {
  products: Product[];
}

const PRODUCTS_PER_ROW = 5;

const ProductCarousel: React.FC<ProductCarouselProps> = ({ products }) => {
  const [startIdx, setStartIdx] = useState(0);
  const maxVisible = PRODUCTS_PER_ROW; // Only 1 row
  const canScrollLeft = startIdx > 0;
  const canScrollRight = startIdx + maxVisible < products.length;

  const handleLeft = () => {
    setStartIdx(idx => Math.max(0, idx - PRODUCTS_PER_ROW));
  };

  const handleRight = () => {
    setStartIdx(idx =>
      Math.min(products.length - maxVisible, idx + PRODUCTS_PER_ROW)
    );
  };

  const visibleProducts = products.slice(startIdx, startIdx + maxVisible);

  return (
    <section className="my-12">
      <h2 className="text-2xl font-bold text-center mb-6">Featured Products</h2>
      <div className="flex items-center justify-center gap-4">
        <ArrowButton
          direction="left"
          onClick={handleLeft}
          disabled={!canScrollLeft}
          variant="outlined"
        />
        <div className="flex gap-6 justify-center">
          {visibleProducts.map(product => (
            <div key={product.id} className="min-w-[180px] bg-white rounded-lg shadow-md p-4 flex flex-col items-center">
              <img src={product.image} alt={product.name} className="w-24 h-24 object-cover rounded mb-3" />
              <h3 className="font-semibold text-base mb-2 text-center">{product.name}</h3>
              <Button href={`/products/${product.id}`} primary className="mt-auto w-full">View Details</Button>
            </div>
          ))}
        </div>
        <ArrowButton
          direction="right"
          onClick={handleRight}
          disabled={!canScrollRight}
          variant="outlined"
        />
      </div>
    </section>
  );
};

export default ProductCarousel;
