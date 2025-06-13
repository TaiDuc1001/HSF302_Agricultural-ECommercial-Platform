import React from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

interface PriceRangeSliderProps {
  value: [number, number];
  onChange: (value: [number, number]) => void;
  min?: number;
  max?: number;
}

const PriceRangeSlider: React.FC<PriceRangeSliderProps> = ({ value, onChange, min = 0, max = 1000 }) => (
  <div>
    <h3 className="font-semibold mb-2">Price Range</h3>
    <div className="flex flex-col items-center gap-2 w-full">
      <div className="flex justify-between w-full text-sm">
        <span>${value[0]}</span>
        <span>${value[1]}</span>
      </div>
      <div className="w-full px-2">
        <Slider
          range
          min={min}
          max={max}
          value={value}
          onChange={(vals: number | number[]) => {
            if (Array.isArray(vals)) onChange([vals[0], vals[1]]);
          }}
          allowCross={false}
          className="h-6"
        />
      </div>
    </div>
  </div>
);

export default PriceRangeSlider;
