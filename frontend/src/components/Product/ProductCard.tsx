import React from 'react';
import Button from '../Button/Button';

interface ProductCardProps {
  product: {
    id: number;
    name: string;
    image: string;
    seller?: string;
    price?: number;
    rating?: number; // 0-5
    solds?: number;
    [key: string]: any;
  };
}

const renderStars = (rating: number = 0) => {
  const stars = [];
  for (let i = 1; i <= 5; i++) {
    stars.push(
      <svg
        key={i}
        className={`w-4 h-4 inline ${i <= rating ? 'text-yellow-400' : 'text-gray-300'}`}
        fill="currentColor"
        viewBox="0 0 20 20"
      >
        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.286 3.967a1 1 0 00.95.69h4.175c.969 0 1.371 1.24.588 1.81l-3.38 2.455a1 1 0 00-.364 1.118l1.287 3.966c.3.922-.755 1.688-1.54 1.118l-3.38-2.454a1 1 0 00-1.175 0l-3.38 2.454c-.784.57-1.838-.196-1.54-1.118l1.287-3.966a1 1 0 00-.364-1.118L2.05 9.394c-.783-.57-.38-1.81.588-1.81h4.175a1 1 0 00.95-.69l1.286-3.967z" />
      </svg>
    );
  }
  return stars;
};

const ProductCard: React.FC<ProductCardProps> = ({ product }) => (
  <div className="min-w-[200px] w-full max-w-xs bg-white rounded-lg shadow-md p-4 flex flex-col group hover:shadow-lg transition relative border border-gray-100">
    <div className="relative w-full flex justify-center items-center mb-3">
      <img src={product.image} alt={product.name} className="w-28 h-28 object-cover rounded bg-gray-50" />
      {/* Optional: Add a badge or discount label here */}
    </div>
    <h3 className="font-semibold text-base mb-1 text-gray-900 line-clamp-2 text-left w-full min-h-[2.5rem]">{product.name}</h3>
    <div className="flex items-center justify-between w-full mb-1">
      <span className="text-xs text-gray-500">{product.seller || 'Unknown Seller'}</span>
      <span className="text-xs text-gray-400">{product.solds ?? 0} sold</span>
    </div>
    <div className="flex items-center justify-between w-full mb-2">
      <div className="flex items-center">
        {renderStars(product.rating)}
        <span className="ml-1 text-xs text-gray-500">({product.rating ?? 0})</span>
      </div>
      <span className="text-green-700 font-bold text-lg">${product.price?.toFixed(2) ?? '0.00'}</span>
    </div>
    <Button href={`/products/${product.id}`} primary className="mt-auto w-full">View Details</Button>
  </div>
);

export default ProductCard;
