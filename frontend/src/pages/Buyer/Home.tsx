import React, { useState } from 'react';
import StatCard from '../../components/StatCard/StatCard';
import NavCard from '../../components/NavCard/NavCard';
import ProductCard from '../../components/Product/ProductCard';
import ArrowButton from '../../components/Button/ArrowButton';

const recommendedProducts = [
  {
    id: 1,
    name: 'Fresh Apples',
    image: 'https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=facearea&w=96&h=96',
    price: 2.99,
    seller: 'Green Farm',
    rating: 4.5,
    solds: 120
  },
  {
    id: 2,
    name: 'Organic Carrots',
    image: 'https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=facearea&w=96&h=96',
    price: 1.49,
    seller: 'Healthy Roots',
    rating: 4.2,
    solds: 80
  },
  {
    id: 3,
    name: 'Sweet Bananas',
    image: 'https://images.unsplash.com/photo-1502741338009-cac2772e18bc?auto=format&fit=facearea&w=96&h=96',
    price: 1.99,
    seller: 'Banana Bros',
    rating: 4.7,
    solds: 95
  },
  {
    id: 4,
    name: 'Juicy Oranges',
    image: 'https://images.unsplash.com/photo-1519864600265-abb23847ef2c?auto=format&fit=facearea&w=96&h=96',
    price: 2.49,
    seller: 'Citrus World',
    rating: 4.3,
    solds: 110
  }
];

const PRODUCTS_PER_VIEW = 2;

const Home: React.FC = () => {
  const [startIdx, setStartIdx] = useState(0);
  const canScrollLeft = startIdx > 0;
  const canScrollRight = startIdx + PRODUCTS_PER_VIEW < recommendedProducts.length;
  const handleLeft = () => setStartIdx(idx => Math.max(0, idx - PRODUCTS_PER_VIEW));
  const handleRight = () => setStartIdx(idx => Math.min(recommendedProducts.length - PRODUCTS_PER_VIEW, idx + PRODUCTS_PER_VIEW));
  const visibleProducts = recommendedProducts.slice(startIdx, startIdx + PRODUCTS_PER_VIEW);

  return (
    <div className="min-h-screen bg-gradient-to-br from-green-50 to-green-100 p-8 flex flex-col items-center">
      <div className="w-full max-w-2xl bg-white rounded-2xl shadow-lg p-8 mb-8 flex flex-col items-center">
        <img src="https://ui-avatars.com/api/?name=User&background=34d399&color=fff&size=96" alt="Profile" className="w-24 h-24 rounded-full mb-4 border-4 border-green-200" />
        <h2 className="text-3xl font-extrabold text-green-800 mb-2">Welcome back, User!</h2>
        <p className="text-gray-600 mb-6">Manage your account, track your orders, and discover new products tailored for you.</p>
        <div className="grid grid-cols-3 gap-4 w-full mb-6">
          <StatCard value={3} label="Orders" />
          <StatCard value={2} label="Cart Items" />
          <StatCard value={1} label="Pending Review" />
        </div>
        <div className="grid grid-cols-2 gap-4 w-full mb-4">
          <NavCard to="/orders" label="My Orders" colorClass="bg-green-600 text-white hover:bg-green-700" />
          <NavCard to="/cart" label="My Cart" colorClass="bg-green-500 text-white hover:bg-green-600" />
          <NavCard to="/profile" label="Manage Profile" colorClass="bg-green-400 text-white hover:bg-green-500" />
          <NavCard to="/logout" label="Logout" colorClass="bg-red-400 text-white hover:bg-red-500" />
        </div>
      </div>
      <div className="w-full max-w-2xl">
        <h3 className="text-xl font-bold text-green-800 mb-4">Recommended for You</h3>
        <div className="flex items-center justify-center gap-4">
          <ArrowButton direction="left" onClick={handleLeft} disabled={!canScrollLeft} variant="outlined" />
          <div className="grid grid-cols-2 gap-4">
            {visibleProducts.map(product => (
              <ProductCard key={product.id} product={product} />
            ))}
          </div>
          <ArrowButton direction="right" onClick={handleRight} disabled={!canScrollRight} variant="outlined" />
        </div>
      </div>
    </div>
  );
};

export default Home;
