import React from 'react';

export interface ChecklistOption {
  label: string;
  value: string;
}

export interface ChecklistProps {
  options: ChecklistOption[];
  selected: string[];
  onChange: (value: string) => void;
  label?: string;
  single?: boolean; // If true, only one can be selected (for radio-like behavior)
}

const CheckList: React.FC<ChecklistProps> = ({ options, selected, onChange, label, single = false }) => (
  <div>
    {label && <h3 className="font-semibold mb-2">{label}</h3>}
    {options.map(opt => (
      <label key={opt.value} className="flex items-center mb-1 cursor-pointer">
        <input
          type={single ? 'radio' : 'checkbox'}
          checked={selected.includes(opt.value)}
          onChange={() => onChange(opt.value)}
          className="mr-2 accent-green-600"
          name={label}
        />
        {opt.label}
      </label>
    ))}
  </div>
);

export default CheckList;
