import React, { useState, useMemo } from 'react';
import { Link } from 'react-router-dom';
import FilterSidebar from '../../components/SideBar/FilterSidebar';
import SearchBar from '../../components/Search/SearchBar';
import ProductGrid from '../../components/Product/ProductGrid';
import Pagination from '../../components/Pagination/Pagination';
import EmptyState from '../../components/EmptyState/EmptyState';
import { productDetails } from '../../data/productDetails';

const CATEGORIES = ['Produce', 'Seeds', 'Fertilizer', 'Dairy', 'Honey', 'Poultry'];
const SUGGESTIONS = [
  'Organic Tomatoes',
  'Fresh Carrots',
  'Premium Fertilizer',
  'Organic Seeds',
  'Seasonal Fruits',
  'Natural Honey',
  'Farm Eggs',
  'Organic Rice',
  'Fresh Milk',
  'Sweet Potatoes',
  'Organic Corn',
  'Fresh Spinach',
  'Farm Chicken',
  'Organic Wheat',
];

const PRODUCTS_PER_PAGE = 9;
const COLUMNS = 4;

const Products: React.FC = () => {
  // Search bar state
  const [search, setSearch] = useState('');
  const [showDropdown, setShowDropdown] = useState(false);
  // Filter state
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);
  const [organicOnly, setOrganicOnly] = useState<string>('all');
  const [priceRange, setPriceRange] = useState<[number, number]>([0, 1000]);
  // Pagination
  const [page, setPage] = useState(1);

  // Autocomplete suggestions
  const filteredSuggestions = useMemo(() => {
    if (!search) return [];
    return SUGGESTIONS.filter(s => s.toLowerCase().includes(search.toLowerCase())).slice(0, 5);
  }, [search]);

  // Filtered products
  const filteredProducts = useMemo(() => {
    let products = productDetails;
    if (search) {
      products = products.filter(p => p.name.toLowerCase().includes(search.toLowerCase()));
    }
    if (selectedCategories.length > 0) {
      products = products.filter(p =>
        selectedCategories.some(cat => p.category && p.category.toLowerCase() === cat.toLowerCase())
      );
    }
    if (organicOnly === 'organic') {
      products = products.filter(p => p.isOrganic);
    } else if (organicOnly === 'non-organic') {
      products = products.filter(p => !p.isOrganic);
    }
    // Price filter
    products = products.filter(p => p.price >= priceRange[0] && p.price <= priceRange[1]);
    return products;
  }, [search, selectedCategories, organicOnly, priceRange]);

  // Pagination logic
  const totalPages = Math.ceil(filteredProducts.length / PRODUCTS_PER_PAGE);
  const paginatedProducts = filteredProducts.slice(
    (page - 1) * PRODUCTS_PER_PAGE,
    page * PRODUCTS_PER_PAGE
  );

  return (
    <div className="flex min-h-screen bg-gray-50">
      {/* Filter Sidebar */}
      <div className="pt-10 pl-8">
        <FilterSidebar
          categories={CATEGORIES}
          selectedCategories={selectedCategories}
          onCategoryChange={cat => setSelectedCategories(prev => prev.includes(cat) ? prev.filter(c => c !== cat) : [...prev, cat])}
          organicOnly={organicOnly}
          onOrganicChange={setOrganicOnly}
          priceRange={priceRange}
          onPriceRangeChange={setPriceRange}
          onApplyFilters={() => setPage(1)}
        />
      </div>
      {/* Main Content */}
      <main className="flex-1 flex flex-col items-center px-8 py-10">
        {/* Search Bar */}
        <div className="w-full flex justify-center mb-8 relative">
          <div className="w-[420px] relative">
            <SearchBar
              value={search}
              onChange={e => { setSearch(e.target.value); setShowDropdown(true); setPage(1); }}
              suggestions={filteredSuggestions}
              onSuggestionClick={s => { setSearch(s); setShowDropdown(false); setPage(1); }}
              showDropdown={showDropdown}
              onFocus={() => setShowDropdown(true)}
              onBlur={() => setTimeout(() => setShowDropdown(false), 150)}
              placeholder="Search products..."
            />
          </div>
        </div>
        {/* Product Grid */}
        {paginatedProducts.length === 0 ? (
          <EmptyState message="No products found." />
        ) : (
          <ProductGrid products={paginatedProducts} columns={COLUMNS} />
        )}
        {/* Pagination */}
        {totalPages > 1 && (
          <Pagination totalPages={totalPages} currentPage={page} onPageChange={setPage} />
        )}
        {/* Links */}
        <div className="flex justify-center gap-6 mt-12">
          <Link to="/sellers" className="text-green-700 underline hover:text-green-900">View Sellers</Link>
          <Link to="/cart" className="text-green-700 underline hover:text-green-900">Go to Cart</Link>
        </div>
      </main>
    </div>
  );
};

export default Products;
