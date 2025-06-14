import React, { useState, useMemo } from 'react';
import SellerCard from '../../components/Seller/SellerCard';
import Pagination from '../../components/Pagination/Pagination';
import SellerFilterSidebar from '../../components/SideBar/SellerFilterSidebar';
import sellers from '../../data/sellers';
import categories from '../../data/categories';

const pageSize = 5;

const Sellers: React.FC = () => {
  const [search, setSearch] = useState('');
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);
  const [minRating, setMinRating] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);

  const filteredSellers = useMemo(() => {
    return sellers.filter(seller => {
      const matchesSearch = seller.seller.toLowerCase().includes(search.toLowerCase());
      const matchesCategory = selectedCategories.length === 0 || seller.categories.some(cat => selectedCategories.includes(cat));
      const matchesRating = seller.rating >= minRating;
      return matchesSearch && matchesCategory && matchesRating;
    });
  }, [search, selectedCategories, minRating]);

  const totalPages = Math.ceil(filteredSellers.length / pageSize);
  const paginatedSellers = filteredSellers.slice((currentPage - 1) * pageSize, currentPage * pageSize);

  const handleCategoryChange = (cat: string) => {
    setCurrentPage(1);
    setSelectedCategories(prev => prev.includes(cat) ? prev.filter(c => c !== cat) : [...prev, cat]);
  };
  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCurrentPage(1);
    setSearch(e.target.value);
  };
  const handleRatingChange = (value: number) => {
    setCurrentPage(1);
    setMinRating(value);
  };

  return (
    <div className="flex gap-8 p-8">
      <div className="w-80 flex-shrink-0">
        <SellerFilterSidebar
          searchValue={search}
          onSearchChange={handleSearchChange}
          categories={categories}
          selectedCategories={selectedCategories}
          onCategoryChange={handleCategoryChange}
          rating={minRating}
          onRatingChange={handleRatingChange}
        />
      </div>
      <div className="flex-1 flex flex-col items-center">
        <h2 className="text-2xl font-bold mb-4">Top Sellers</h2>
        {paginatedSellers.length === 0 ? (
          <div className="text-gray-500">No sellers found.</div>
        ) : (
          paginatedSellers.map(seller => (
            <SellerCard
              key={seller.seller}
              seller={seller.seller}
              rating={seller.rating}
              productCount={seller.productCount}
              soldCount={seller.soldCount}
            />
          ))
        )}
        <div className="mt-6 w-full flex justify-center">
          <Pagination
            totalPages={totalPages}
            currentPage={currentPage}
            onPageChange={setCurrentPage}
          />
        </div>
      </div>
    </div>
  );
};

export default Sellers;
