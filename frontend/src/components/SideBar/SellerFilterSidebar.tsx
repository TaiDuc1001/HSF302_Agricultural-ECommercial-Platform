import React from 'react';
import SideBar from '../SideBar/SideBar';
import SearchBar from '../Search/SearchBar';
import CheckList, { ChecklistOption } from '../CheckList/CheckList';

interface SellerFilterSidebarProps {
  searchValue: string;
  onSearchChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  categories: ChecklistOption[];
  selectedCategories: string[];
  onCategoryChange: (value: string) => void;
  rating: number;
  onRatingChange: (value: number) => void;
}

const RatingSlider: React.FC<{
  value: number;
  onChange: (value: number) => void;
  min?: number;
  max?: number;
  step?: number;
}> = ({ value, onChange, min = 0, max = 5, step = 0.5 }) => (
  <div>
    <div className="flex justify-between mb-1 text-sm">
      <span>{min}★</span>
      <span>{max}★</span>
    </div>
    <input
      type="range"
      min={min}
      max={max}
      step={step}
      value={value}
      onChange={e => onChange(Number(e.target.value))}
      className="w-full accent-yellow-400"
    />
  </div>
);

const SellerFilterSidebar: React.FC<SellerFilterSidebarProps> = ({
  searchValue,
  onSearchChange,
  categories,
  selectedCategories,
  onCategoryChange,
  rating,
  onRatingChange,
}) => {
  return (
    <SideBar>
      <div className="flex flex-col gap-6">
        {/* Search Bar */}
        <SearchBar
          value={searchValue}
          onChange={onSearchChange}
          placeholder="Search sellers..."
        />

        {/* Category Checklist */}
        <CheckList
          label="Categories"
          options={categories}
          selected={selectedCategories}
          onChange={onCategoryChange}
        />

        {/* Rating Slider */}
        <div>
          <div className="font-semibold mb-2">Minimum Rating</div>
          <RatingSlider
            value={rating}
            onChange={onRatingChange}
            min={0}
            max={5}
            step={0.5}
          />
        </div>
      </div>
    </SideBar>
  );
};

export default SellerFilterSidebar;
