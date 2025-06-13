import React from 'react';
import Slideshow, { SlideType } from '../Slideshow/Slideshow';
import { Link } from 'react-router-dom';

const slides: SlideType[] = [
  {
    image: 'https://images.unsplash.com/photo-1502741338009-cac2772e18bc?auto=format&fit=crop&w=1200&q=80',
    headline: 'Fresh Tomatoes This Season!',
    alt: 'Fresh tomatoes in a basket',
  },
  {
    image: 'https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=crop&w=1200&q=80',
    headline: 'Organic Seeds for Your Farm',
    alt: 'Organic seeds in hand',
  },
  {
    image: 'https://images.unsplash.com/photo-1506089676908-3592f7389d4d?auto=format&fit=crop&w=1200&q=80',
    headline: 'Farm-Fresh Vegetables',
    alt: 'Assorted fresh vegetables',
  },
  {
    image: 'https://images.unsplash.com/photo-1511690743698-d9d85f2fbf38?auto=format&fit=crop&w=1200&q=80',
    headline: 'Premium Organic Fertilizer',
    alt: 'Organic fertilizer in a sack',
  },
  {
    image: 'https://images.unsplash.com/photo-1464983953574-0892a716854b?auto=format&fit=crop&w=1200&q=80',
    headline: 'Seasonal Fruits Now Available',
    alt: 'Fresh fruits on a table',
  },
];

const HeroSection: React.FC = () => (
  <Slideshow
    slides={slides}
    slideDuration={5000}
    fadeDuration={1500}
    renderSlide={(slide, active) => (
      <>
        <img
          src={slide.image}
          alt={slide.alt}
          className="w-full h-full object-cover select-none pointer-events-none"
          draggable="false"
        />
        <div className="absolute inset-0 bg-black bg-opacity-40 flex flex-col items-center justify-center text-white px-4">
          <h2 className="text-2xl md:text-4xl font-bold mb-4 text-center drop-shadow-lg">
            {slide.headline}
          </h2>
          <Link
            to="/products"
            className="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-6 rounded shadow-lg focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-offset-2 transition"
            aria-label="Shop now for products"
          >
            Shop Now
          </Link>
        </div>
      </>
    )}
  />
);

export default HeroSection;
