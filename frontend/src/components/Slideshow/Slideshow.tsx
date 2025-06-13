import React, { useState, useEffect, useRef, ReactNode } from 'react';
import ArrowButton from '../Button/ArrowButton';

export type SlideType = {
  image: string;
  alt?: string;
  [key: string]: any;
};

export type SlideshowProps = {
  slides: SlideType[];
  renderSlide: (slide: SlideType, active: boolean) => ReactNode;
  slideDuration?: number;
  fadeDuration?: number;
  className?: string;
  controls?: boolean;
  onSlideChange?: (idx: number) => void;
};

const Slideshow: React.FC<SlideshowProps> = ({
  slides,
  renderSlide,
  slideDuration = 5000,
  fadeDuration = 1500,
  className = '',
  controls = true,
  onSlideChange,
}) => {
  const [current, setCurrent] = useState(0);
  const [paused, setPaused] = useState(false);
  const timeoutRef = useRef<NodeJS.Timeout | null>(null);

  useEffect(() => {
    if (!paused) {
      timeoutRef.current = setTimeout(() => {
        setCurrent((prev) => (prev + 1) % slides.length);
        if (onSlideChange) onSlideChange((current + 1) % slides.length);
      }, slideDuration);
    }
    return () => {
      if (timeoutRef.current) clearTimeout(timeoutRef.current);
    };
    // eslint-disable-next-line
  }, [current, paused, slideDuration, slides.length]);

  const goToSlide = (idx: number) => setCurrent(idx);
  const goToPrev = () => setCurrent((prev) => (prev - 1 + slides.length) % slides.length);
  const goToNext = () => setCurrent((prev) => (prev + 1) % slides.length);

  // Keyboard navigation
  useEffect(() => {
    const handleKey = (e: KeyboardEvent) => {
      if (e.key === 'ArrowLeft') goToPrev();
      if (e.key === 'ArrowRight') goToNext();
    };
    window.addEventListener('keydown', handleKey);
    return () => window.removeEventListener('keydown', handleKey);
  });

  // Pause on hover/tap
  const handlePause = () => setPaused(true);
  const handleResume = () => setPaused(false);

  return (
    <section
      className={`relative w-full overflow-hidden ${className}`}
      style={{ height: '500px' }}
      aria-label="slideshow"
      onMouseEnter={handlePause}
      onMouseLeave={handleResume}
      onTouchStart={handlePause}
      onTouchEnd={handleResume}
    >
      {slides.map((slide, idx) => (
        <div
          key={idx}
          className={`absolute inset-0 transition-opacity duration-[${fadeDuration}ms] ${idx === current ? 'opacity-100 z-10' : 'opacity-0 z-0'}`}
          aria-hidden={idx !== current}
        >
          {renderSlide(slide, idx === current)}
        </div>
      ))}
      {controls && (
        <>
          {/* Arrows */}
          <ArrowButton
            direction="left"
            onClick={goToPrev}
            className="left-4 absolute top-1/2 -translate-y-1/2 z-20 hidden md:block"
            aria-label="Previous slide"
            variant="filled"
          />
          <ArrowButton
            direction="right"
            onClick={goToNext}
            className="right-4 absolute top-1/2 -translate-y-1/2 z-20 hidden md:block"
            aria-label="Next slide"
            variant="filled"
          />
          {/* Pagination dots */}
          <div className="absolute bottom-6 left-1/2 -translate-x-1/2 flex space-x-2 z-20" role="tablist" aria-label="Slideshow pagination">
            {slides.map((_, idx) => (
              <button
                key={idx}
                className={`w-3 h-3 rounded-full border-2 border-white ${idx === current ? 'bg-green-500' : 'bg-white bg-opacity-50'} focus:outline-none focus:ring-2 focus:ring-green-400`}
                onClick={() => goToSlide(idx)}
                aria-label={`Go to slide ${idx + 1}`}
                aria-selected={idx === current}
                tabIndex={0}
                role="tab"
              />
            ))}
          </div>
        </>
      )}
    </section>
  );
};

export default Slideshow;
