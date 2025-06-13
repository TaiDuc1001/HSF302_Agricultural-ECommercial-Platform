import React from 'react';
import { useParams } from 'react-router-dom';

const ProductDetail: React.FC = () => {
  const { id } = useParams();
  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh]">
      <h1 className="text-2xl font-bold mb-4">Product Detail</h1>
      <p className="text-gray-600">Product ID: <span className="font-mono">{id}</span></p>
      {/* TODO: Fetch and display product details here */}
    </div>
  );
};

export default ProductDetail;
