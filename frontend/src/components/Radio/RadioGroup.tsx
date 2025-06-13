import React from 'react';

export interface RadioOption {
  label: string;
  value: string;
}

export interface RadioGroupProps {
  options: RadioOption[];
  selected: string;
  onChange: (value: string) => void;
  label?: string;
}

const RadioGroup: React.FC<RadioGroupProps> = ({ options, selected, onChange, label }) => (
  <div>
    {label && <h3 className="font-semibold mb-2">{label}</h3>}
    {options.map(opt => (
      <label key={opt.value} className="flex items-center mb-1 cursor-pointer">
        <input
          type="radio"
          checked={selected === opt.value}
          onChange={() => onChange(opt.value)}
          className="mr-2 accent-green-600"
          name={label}
        />
        {opt.label}
      </label>
    ))}
  </div>
);

export default RadioGroup;
