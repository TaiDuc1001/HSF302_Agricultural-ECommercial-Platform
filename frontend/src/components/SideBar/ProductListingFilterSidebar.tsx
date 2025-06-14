import React from 'react';
import SideBar from './SideBar';
import CheckList from '../CheckList/CheckList';
import RadioGroup from '../Radio/RadioGroup';
import PriceRangeSlider from '../Slider/PriceRangeSlider';
import categories from '../../data/categories';

interface FilterSidebarProps {
  categories?: string[];
  selectedCategories: string[];
  onCategoryChange: (cat: string) => void;
  organicOnly: string;
  onOrganicChange: (val: string) => void;
  priceRange: [number, number];
  onPriceRangeChange: (range: [number, number]) => void;
  children?: React.ReactNode;
  onApplyFilters?: () => void;
}

const ProductListingFilterSidebar: React.FC<FilterSidebarProps> = ({
  categories: categoriesProp,
  selectedCategories,
  onCategoryChange,
  organicOnly,
  onOrganicChange,
  priceRange,
  onPriceRangeChange,
  onApplyFilters,
  children
}) => (
  <SideBar>
    <CheckList
      label="Category"
      options={(categoriesProp ? categoriesProp.map(c => ({ label: c, value: c })) : categories)}
      selected={selectedCategories}
      onChange={onCategoryChange}
    />
    <RadioGroup
      label="Organic"
      options={[
        { label: 'All', value: 'all' },
        { label: 'Only show organic', value: 'organic' },
        { label: 'Only show non-organic', value: 'non-organic' }
      ]}
      selected={organicOnly}
      onChange={onOrganicChange}
    />
    <div className="flex flex-col gap-6">
      <PriceRangeSlider value={priceRange} onChange={onPriceRangeChange} min={0} max={1000} />
    </div>
    {children}
    {onApplyFilters && (
      <button
        className="bg-green-600 text-white py-2 rounded hover:bg-green-700 transition"
        onClick={onApplyFilters}
      >
        Apply Filters
      </button>
    )}
  </SideBar>
);

export default ProductListingFilterSidebar;
