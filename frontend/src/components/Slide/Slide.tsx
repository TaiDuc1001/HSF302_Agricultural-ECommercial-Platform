import React from 'react';
import { Link } from 'react-router-dom';

type SlideProps = {
  slide: {
    image: string;
    headline: string;
    alt: string;
  };
  active: boolean;
  fadeDuration: number;
};

const Slide: React.FC<SlideProps> = ({ slide, active, fadeDuration }) => (
  <div
    className={`absolute inset-0 w-full h-full transition-opacity duration-[${fadeDuration}ms] ${active ? 'opacity-100 z-10' : 'opacity-0 z-0'}`}
    aria-hidden={!active}
  >
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
  </div>
);

export default Slide;
