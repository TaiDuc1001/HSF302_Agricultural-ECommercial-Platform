import React, { useState, useMemo } from 'react';
import { useParams } from 'react-router-dom';
import { productDetails } from '../../data/productDetails';
import ProductGrid from '../../components/Product/ProductGrid';
import FilterSidebar from '../../components/SideBar/FilterSidebar';
import Pagination from '../../components/Pagination/Pagination';
import SellerCard from '../../components/Seller/SellerCard';

const CATEGORIES = ['Produce', 'Seeds', 'Fertilizer', 'Dairy', 'Honey', 'Poultry'];
const PRODUCTS_PER_PAGE = 9;
const COLUMNS = 4;

const SellerProfile: React.FC = () => {
  const { sellerName } = useParams<{ sellerName: string }>();
  const seller = decodeURIComponent(sellerName || '');
  // Filter state
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);
  const [organicOnly, setOrganicOnly] = useState<string>('all');
  const [priceRange, setPriceRange] = useState<[number, number]>([0, 1000]);
  // Pagination
  const [page, setPage] = useState(1);

  const sellerProducts = useMemo(() => productDetails.filter(p => p.seller === seller), [seller]);
  const sellerRating = sellerProducts.length
    ? (sellerProducts.reduce((sum, p) => sum + (p.rating || 0), 0) / sellerProducts.length).toFixed(1)
    : '-';
  const sellerSold = sellerProducts.reduce((sum, p) => sum + (p.solds || 0), 0);

  // Example seller info (could be dynamic in a real app)
  const sellerInfo = {
    location: 'California, USA',
    since: '2021',
    description: 'We provide the freshest organic produce directly from our farm to your table. Quality and sustainability are our top priorities.'
  };

  // Filtered products
  const filteredProducts = useMemo(() => {
    let products = sellerProducts;
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
    products = products.filter(p => p.price >= priceRange[0] && p.price <= priceRange[1]);
    return products;
  }, [sellerProducts, selectedCategories, organicOnly, priceRange]);

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
          onApplyFilters={() => {}}
        />
      </div>
      {/* Main Content */}
      <main className="flex-1 flex flex-col items-center px-8 py-10">
        <SellerCard
          seller={seller}
          rating={sellerRating}
          productCount={sellerProducts.length}
          soldCount={sellerSold}
        />
        <div className="w-full max-w-6xl">
          <h2 className="text-xl font-bold mb-4">Products by {seller}</h2>
          <ProductGrid products={paginatedProducts} columns={COLUMNS} />
          {totalPages > 1 && (
            <Pagination totalPages={totalPages} currentPage={page} onPageChange={setPage} />
          )}
        </div>
      </main>
    </div>
  );
};

export default SellerProfile;
