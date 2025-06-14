import React from 'react';
import { useParams, Link } from 'react-router-dom';
import { productDetails } from '../../data/productDetails';
import ProductCarousel from '../../components/ProductCarousel/ProductCarousel';
import SellerCard from '../../components/Seller/SellerCard';

const renderStars = (rating: number = 0) => {
  const stars = [];
  for (let i = 1; i <= 5; i++) {
    stars.push(
      <svg
        key={i}
        className={`w-5 h-5 inline ${i <= Math.round(rating) ? 'text-yellow-400' : 'text-gray-300'}`}
        fill="currentColor"
        viewBox="0 0 20 20"
      >
        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.286 3.967a1 1 0 00.95.69h4.175c.969 0 1.371 1.24.588 1.81l-3.38 2.455a1 1 0 00-.364 1.118l1.287 3.966c.3.922-.755 1.688-1.54 1.118l-3.38-2.454a1 1 0 00-1.175 0l-3.38 2.454c-.784.57-1.838-.196-1.54-1.118l1.287-3.966a1 1 0 00-.364-1.118L2.05 9.394c-.783-.57-.38-1.81.588-1.81h4.175a1 1 0 00.95-.69l1.286-3.967z" />
      </svg>
    );
  }
  return stars;
};

const ProductDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const product = productDetails.find(p => p.id === Number(id));

  if (!product) {
    return (
      <div className="flex flex-col items-center justify-center min-h-[60vh]">
        <h1 className="text-2xl font-bold mb-4">Product Not Found</h1>
        <p className="text-gray-600">No product found for ID: <span className="font-mono">{id}</span></p>
      </div>
    );
  }

  // Find related products (same category, not this product)
  const relatedProducts = productDetails.filter(
    p => p.category === product.category && p.id !== product.id
  );
  // Seller info
  const sellerProducts = productDetails.filter(p => p.seller === product.seller);
  const sellerRating = sellerProducts.length
    ? (sellerProducts.reduce((sum, p) => sum + (p.rating || 0), 0) / sellerProducts.length).toFixed(1)
    : product.rating;
  const sellerSold = sellerProducts.reduce((sum, p) => sum + (p.solds || 0), 0);

  return (
    <div className="flex flex-col items-center min-h-[60vh] gap-10 p-8">
      {/* Product Detail Section */}
      <div className="flex flex-col md:flex-row items-center justify-center w-full gap-10">
        <img src={product.image} alt={product.name} className="w-64 h-64 object-cover rounded shadow mb-6 md:mb-0" />
        <div className="max-w-lg w-full">
          <h1 className="text-3xl font-bold mb-2">{product.name}</h1>
          <div className="flex items-center mb-2">
            {renderStars(product.rating)}
            <span className="ml-2 text-gray-600">({product.rating})</span>
            <span className="ml-4 text-xs text-gray-500">{product.solds} sold</span>
          </div>
          <div className="mb-2">
            <span className="text-green-700 font-bold text-2xl">${product.price.toFixed(2)}</span>
            <span className="ml-4 text-sm text-gray-500">Stock: {product.stock}</span>
          </div>
          <div className="mb-2">
            <span className="text-sm text-gray-700">Seller: <Link to={`/sellers/${encodeURIComponent(product.seller)}`} className="font-semibold text-green-700 underline hover:text-green-900">{product.seller}</Link></span>
          </div>
          <div className="mb-2">
            <span className="text-sm text-gray-700">Category: {product.category}</span>
            <span className="ml-4 text-sm text-gray-700">Origin: {product.origin}</span>
          </div>
          <div className="mb-2">
            <span className="text-sm text-gray-700">Weight: {product.weight}</span>
            <span className="ml-4 text-sm text-gray-700">{product.isOrganic ? 'Organic' : 'Non-Organic'}</span>
          </div>
          <p className="text-gray-800 mt-4 mb-6">{product.description}</p>
          <button className="bg-green-700 text-white px-6 py-2 rounded font-semibold hover:bg-green-800 transition">Add to Cart</button>
        </div>
      </div>
      {/* Seller Section */}
      <div className="w-full max-w-6xl flex justify-center">
        <div className="w-full">
          <SellerCard
            seller={product.seller}
            rating={sellerRating}
            productCount={sellerProducts.length}
            soldCount={sellerSold}
          />
        </div>
      </div>
      {/* Related Products Section */}
      {relatedProducts.length > 0 && (
        <div className="w-full max-w-6xl mt-12">
          <h2 className="text-xl font-bold mb-4">Related Products</h2>
          <ProductCarousel products={relatedProducts} />
        </div>
      )}
      {/* Reviews Section at the end with filter bar */}
      <div className="w-full max-w-3xl mt-12">
        <h2 className="text-lg font-bold mb-2">Reviews</h2>
        {/* Simple filter bar for demo */}
        <div className="flex gap-4 mb-4">
          <button className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300 text-sm">All</button>
          <button className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300 text-sm">5 Stars</button>
          <button className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300 text-sm">4 Stars</button>
          <button className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300 text-sm">3 Stars</button>
          <button className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300 text-sm">2 Stars</button>
          <button className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300 text-sm">1 Star</button>
        </div>
        {product.reviews && product.reviews.length > 0 ? (
          <ul className="space-y-2">
            {product.reviews.map((review, idx) => (
              <li key={idx} className="bg-gray-100 rounded p-3">
                <div className="flex items-center mb-1">
                  <span className="font-semibold mr-2">{review.user}</span>
                  {renderStars(review.rating)}
                </div>
                <p className="text-gray-700 text-sm">{review.comment}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p className="text-gray-500">No reviews yet.</p>
        )}
      </div>
    </div>
  );
};

export default ProductDetail;
