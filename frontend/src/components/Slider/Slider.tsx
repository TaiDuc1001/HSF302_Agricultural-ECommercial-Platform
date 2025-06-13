import React from 'react';

export interface SliderProps {
  min?: number;
  max?: number;
  value: number | [number, number];
  onChange: (value: number | [number, number]) => void;
  children?: React.ReactNode;
}

const Slider: React.FC<SliderProps> = ({ min = 0, max = 100, value, onChange, children }) => {
  // This is a base slider, can be extended for single or range
  // For a range, value should be [number, number]
  if (Array.isArray(value)) {
    return (
      <div className="flex items-center gap-2">
        <input
          type="range"
          min={min}
          max={max}
          value={value[0]}
          onChange={e => onChange([Number(e.target.value), value[1]])}
          className="w-20 accent-green-600"
        />
        <span className="text-sm">${value[0]}</span>
        <input
          type="range"
          min={min}
          max={max}
          value={value[1]}
          onChange={e => onChange([value[0], Number(e.target.value)])}
          className="w-20 accent-green-600"
        />
        <span className="text-sm">${value[1]}</span>
        {children}
      </div>
    );
  }
  return (
    <div className="flex items-center gap-2">
      <input
        type="range"
        min={min}
        max={max}
        value={value}
        onChange={e => onChange(Number(e.target.value))}
        className="w-40 accent-green-600"
      />
      <span className="text-sm">${value}</span>
      {children}
    </div>
  );
};

export default Slider;
