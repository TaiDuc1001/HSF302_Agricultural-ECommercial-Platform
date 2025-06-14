import React from 'react';
import { Link } from 'react-router-dom';

interface SellerCardProps {
  seller: string;
  rating: string | number;
  productCount: number;
  soldCount: number;
}

const SellerCard: React.FC<SellerCardProps> = ({ seller, rating, productCount, soldCount }) => (
  <div className="w-full max-w-6xl bg-white rounded-lg shadow p-8 flex flex-col md:flex-row items-center gap-8 mb-8">
    <div className="w-24 h-24 rounded-full bg-green-100 flex items-center justify-center text-4xl font-bold text-green-700">
      {seller[0]}
    </div>
    <div className="flex-1 w-full">
      <h1 className="text-3xl font-bold text-green-700 mb-2">{seller}</h1>
      <div className="flex items-center gap-6 mt-2 mb-2">
        <span className="text-base text-gray-700">Rating: {rating}</span>
        <span className="text-base text-gray-700">Products: {productCount}</span>
        <span className="text-base text-gray-700">Total Sold: {soldCount}</span>
      </div>
    </div>
    <div className="flex-shrink-0 flex items-center justify-center h-full">
      <Link to={`/sellers/${encodeURIComponent(seller)}`} className="px-6 py-2 bg-green-700 text-white rounded font-semibold hover:bg-green-800 transition whitespace-nowrap">Shop now</Link>
    </div>
  </div>
);

export default SellerCard;
