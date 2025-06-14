import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Homepage from '../pages/Public/Home';
import Products from '../pages/Public/Products';
import Sellers from '../pages/Public/Sellers';
import Login from '../pages/Auth/Login';
import Register from '../pages/Auth/Register';
import Home from '../pages/Buyer/Home';
import Cart from '../pages/Buyer/Cart';
import Logout from '../pages/Auth/Logout';
import UserOrders from '../pages/Buyer/Orders';
import SellerDashboard from '../pages/Seller/Home';
import SellerProducts from '../pages/Seller/Products';
import SellerOrders from '../pages/Seller/Orders';
import AdminDashboard from '../pages/Admin/Dashboard';
import AdminUsers from '../pages/Admin/Users';
import AdminProducts from '../pages/Admin/Products';
import AdminSellers from '../pages/Admin/Sellers';
import AdminOrders from '../pages/Admin/Orders';
import PrivateRoute from './PrivateRoute';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';
import Checkout from '../pages/Buyer/Checkout';
import Required from '../pages/Buyer/Required';
import { useAuth } from '../context/AuthContext';
import ProfileBuyer from '../pages/Buyer/Profile';
import ProfileSeller from '../pages/Seller/Profile';
import ProductDetail from '../pages/Public/ProductDetail';
import SellerProfile from '../pages/Public/SellerProfile';

const AppRouter: React.FC = () => (
  <Router>
    <Header />
    <Routes>
      <Route path="/" element={<Homepage />} />
      <Route path="/products" element={<ProductsPage />} />
      <Route path="/sellers" element={<SellersPage />} />
      <Route path="/users" element={<PrivateRoute><UsersPage /></PrivateRoute>} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/content" element={<PrivateRoute><ContentPage /></PrivateRoute>} />
      <Route path="/cart" element={<PrivateRoute><Cart /></PrivateRoute>} />
      <Route path="/logout" element={<Logout />} />
      <Route path="/checkout" element={<PrivateRoute><Checkout /></PrivateRoute>} />
      <Route path="/required" element={<PrivateRoute><RequiredProfile /></PrivateRoute>} />
      <Route path="/orders" element={<PrivateRoute><OrdersPage /></PrivateRoute>} />
      <Route path="/profile" element={<PrivateRoute><ProfilePage /></PrivateRoute>} />
      <Route path="/products/:id" element={<ProductDetail />} />
      <Route path="/sellers/:sellerName" element={<SellerProfile />} />
    </Routes>
    <Footer />
  </Router>
);

// Wrapper for /required
const RequiredProfile: React.FC = () => {
  const { user } = useAuth();
  if (!user) return null;
  if (user.role === 'seller') return <Required />;
  return <Required />;
};

// Wrapper for /profile
const ProfilePage: React.FC = () => {
  const { user } = useAuth();
  if (!user) return null;
  if (user.role === 'seller') return <ProfileSeller />;
  return <ProfileBuyer />;
};

// Wrapper for /content (dashboard/home)
const ContentPage: React.FC = () => {
  const { user } = useAuth();
  if (!user) return null;
  if (user.role === 'admin') return <AdminDashboard />;
  if (user.role === 'seller') return <SellerDashboard />;
  return <Home />;
};

// Wrapper for /orders
const OrdersPage: React.FC = () => {
  const { user } = useAuth();
  if (!user) return null;
  if (user.role === 'admin') return <AdminOrders />;
  if (user.role === 'seller') return <SellerOrders />;
  return <UserOrders />;
};

// Wrapper for /products
const ProductsPage: React.FC = () => {
  const { user } = useAuth();
  if (!user) return <Products />;
  if (user.role === 'admin') return <AdminProducts />;
  if (user.role === 'seller') return <SellerProducts />;
  return <Products />;
};

// Wrapper for /sellers
const SellersPage: React.FC = () => {
  const { user } = useAuth();
  if (!user) return <Sellers />;
  if (user.role === 'admin') return <AdminSellers />;
  return <Sellers />;
};

// Wrapper for /users
const UsersPage: React.FC = () => {
  const { user } = useAuth();
  if (!user || user.role !== 'admin') return null;
  return <AdminUsers />;
};

export default AppRouter;
