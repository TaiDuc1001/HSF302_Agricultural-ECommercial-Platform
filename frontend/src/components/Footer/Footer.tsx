import React from 'react';

const Footer: React.FC = () => (
  <footer className="bg-green-800 text-white text-center py-4 w-full z-50">
    <div>© {new Date().getFullYear()} AgriMarket. All rights reserved.</div>
  </footer>
);

export default Footer;
