// Fake detailed product data for demo
export const products = [
  {
    id: 1,
    name: 'Organic Tomatoes',
    image: 'https://images.unsplash.com/photo-1502741338009-cac2772e18bc?auto=format&fit=crop&w=400&q=80',
    description: 'Fresh organic tomatoes grown without pesticides. Perfect for salads and cooking.',
    price: 12.99,
    seller: 'Green Valley Farms',
    isFeatured: true,
    rating: 4.7,
    solds: 120,
    stock: 50,
    category: 'Produce',
    isOrganic: true,
    weight: '1kg',
    origin: 'California, USA',
    reviews: [
      { user: 'Alice', comment: 'Very fresh and tasty!', rating: 5 },
      { user: 'Bob', comment: 'Good quality, will buy again.', rating: 4 }
    ]
  },
  {
    id: 2,
    name: 'Fresh Carrots',
    image: 'https://images.unsplash.com/photo-1506089676908-3592f7389d4d?auto=format&fit=crop&w=400&q=80',
    description: 'Crunchy and sweet carrots, perfect for snacking and cooking.',
    price: 8.50,
    seller: 'Sunrise Farms',
    isFeatured: true,
    rating: 4.3,
    solds: 80,
    stock: 100,
    category: 'Produce',
    isOrganic: false,
    weight: '1kg',
    origin: 'Texas, USA',
    reviews: [
      { user: 'Charlie', comment: 'Very crunchy!', rating: 4 },
      { user: 'Dana', comment: 'Kids love them.', rating: 5 }
    ]
  },
  {
    id: 3,
    name: 'Premium Fertilizer',
    image: 'https://images.unsplash.com/photo-1511690743698-d9d85f2fbf38?auto=format&fit=crop&w=400&q=80',
    description: 'High-quality fertilizer for maximum crop yield.',
    price: 25.00,
    seller: 'AgroPro',
    isFeatured: true,
    rating: 4.8,
    solds: 200,
    stock: 75,
    category: 'Fertilizer',
    isOrganic: true,
    weight: '5kg',
    origin: 'Iowa, USA',
    reviews: [
      { user: 'Eve', comment: 'Plants grew faster!', rating: 5 },
      { user: 'Frank', comment: 'Very effective.', rating: 4 }
    ]
  },
  {
    id: 4,
    name: 'Organic Seeds',
    image: 'https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=crop&w=400&q=80',
    description: 'Certified organic seeds for healthy crops.',
    price: 15.75,
    seller: 'SeedCo',
    isFeatured: true,
    rating: 4.5,
    solds: 90,
    stock: 120,
    category: 'Seeds',
    isOrganic: true,
    weight: '500g',
    origin: 'Oregon, USA',
    reviews: [
      { user: 'Grace', comment: 'Great germination rate.', rating: 5 },
      { user: 'Heidi', comment: 'Healthy plants.', rating: 4 }
    ]
  },
  {
    id: 5,
    name: 'Seasonal Fruits',
    image: 'https://images.unsplash.com/photo-1464983953574-0892a716854b?auto=format&fit=crop&w=400&q=80',
    description: 'A mix of fresh, seasonal fruits.',
    price: 18.99,
    seller: 'Fruit Basket',
    isFeatured: true,
    rating: 4.6,
    solds: 110,
    stock: 60,
    category: 'Produce',
    isOrganic: false,
    weight: '2kg',
    origin: 'Florida, USA',
    reviews: [
      { user: 'Ivan', comment: 'Very fresh!', rating: 5 },
      { user: 'Judy', comment: 'Nice variety.', rating: 4 }
    ]
  },
  {
    id: 6,
    name: 'Fresh Lettuce',
    image: 'https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=400&q=80',
    description: 'Crisp and green lettuce, perfect for salads.',
    price: 6.25,
    seller: 'Green Valley Farms',
    isFeatured: true,
    rating: 4.2,
    solds: 70,
    stock: 80,
    category: 'Produce',
    isOrganic: true,
    weight: '500g',
    origin: 'California, USA',
    reviews: [
      { user: 'Kate', comment: 'Very crisp!', rating: 4 },
      { user: 'Leo', comment: 'Fresh and tasty.', rating: 5 }
    ]
  },
  {
    id: 7,
    name: 'Natural Honey',
    image: 'https://images.unsplash.com/photo-1465101178521-c1a9136a3b99?auto=format&fit=crop&w=400&q=80',
    description: 'Pure, natural honey from local bees.',
    price: 22.00,
    seller: 'Bee Farm',
    isFeatured: true,
    rating: 4.9,
    solds: 150,
    stock: 40,
    category: 'Honey',
    isOrganic: true,
    weight: '1L',
    origin: 'Montana, USA',
    reviews: [
      { user: 'Mona', comment: 'Delicious and pure.', rating: 5 },
      { user: 'Ned', comment: 'Best honey ever.', rating: 5 }
    ]
  },
  {
    id: 8,
    name: 'Farm Eggs',
    image: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=400&q=80',
    description: 'Fresh eggs from free-range chickens.',
    price: 9.99,
    seller: 'Sunny Side Farm',
    isFeatured: true,
    rating: 4.4,
    solds: 95,
    stock: 90,
    category: 'Poultry',
    isOrganic: false,
    weight: '12 eggs',
    origin: 'Nebraska, USA',
    reviews: [
      { user: 'Olga', comment: 'Very fresh eggs.', rating: 4 },
      { user: 'Paul', comment: 'Good quality.', rating: 5 }
    ]
  },
  {
    id: 9,
    name: 'Organic Rice',
    image: 'https://images.unsplash.com/photo-1502741338009-cac2772e18bc?auto=format&fit=crop&w=400&q=80',
    description: 'Long-grain organic rice, perfect for healthy meals.',
    price: 14.50,
    seller: 'RiceLand',
    isFeatured: true,
    rating: 4.3,
    solds: 60,
    stock: 100,
    category: 'Produce',
    isOrganic: true,
    weight: '2kg',
    origin: 'Arkansas, USA',
    reviews: [
      { user: 'Quinn', comment: 'Cooks well.', rating: 4 },
      { user: 'Rita', comment: 'Tastes great.', rating: 5 }
    ]
  },
  {
    id: 10,
    name: 'Fresh Milk',
    image: 'https://images.unsplash.com/photo-1519864600265-abb23847ef2c?auto=format&fit=crop&w=400&q=80',
    description: 'Creamy, fresh milk from grass-fed cows.',
    price: 11.00,
    seller: 'DairyBest',
    isFeatured: true,
    rating: 4.7,
    solds: 130,
    stock: 70,
    category: 'Dairy',
    isOrganic: true,
    weight: '1L',
    origin: 'Wisconsin, USA',
    reviews: [
      { user: 'Sam', comment: 'Very creamy.', rating: 5 },
      { user: 'Tina', comment: 'Kids love it.', rating: 5 }
    ]
  },
  {
    id: 11,
    name: 'Sweet Potatoes',
    image: 'https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=400&q=80',
    description: 'Naturally sweet and nutritious potatoes.',
    price: 10.25,
    seller: 'Root Farm',
    isFeatured: true,
    rating: 4.1,
    solds: 55,
    stock: 60,
    category: 'Produce',
    isOrganic: false,
    weight: '1kg',
    origin: 'Georgia, USA',
    reviews: [
      { user: 'Uma', comment: 'Very sweet.', rating: 4 },
      { user: 'Vic', comment: 'Good for baking.', rating: 4 }
    ]
  },
  {
    id: 12,
    name: 'Organic Corn',
    image: 'https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=crop&w=400&q=80',
    description: 'Sweet organic corn, perfect for grilling.',
    price: 13.00,
    seller: 'CornCo',
    isFeatured: true,
    rating: 4.6,
    solds: 85,
    stock: 80,
    category: 'Produce',
    isOrganic: true,
    weight: '1kg',
    origin: 'Nebraska, USA',
    reviews: [
      { user: 'Wendy', comment: 'Very sweet.', rating: 5 },
      { user: 'Xander', comment: 'Juicy and fresh.', rating: 5 }
    ]
  },
  {
    id: 13,
    name: 'Fresh Spinach',
    image: 'https://images.unsplash.com/photo-1502741338009-cac2772e18bc?auto=format&fit=crop&w=400&q=80',
    description: 'Leafy green spinach, rich in nutrients.',
    price: 7.50,
    seller: 'Green Valley Farms',
    isFeatured: true,
    rating: 4.2,
    solds: 40,
    stock: 90,
    category: 'Produce',
    isOrganic: true,
    weight: '500g',
    origin: 'California, USA',
    reviews: [
      { user: 'Yara', comment: 'Very fresh.', rating: 4 },
      { user: 'Zane', comment: 'Great for salads.', rating: 5 }
    ]
  },
  {
    id: 14,
    name: 'Farm Chicken',
    image: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=400&q=80',
    description: 'Free-range chicken, tender and juicy.',
    price: 19.99,
    seller: 'Sunny Side Farm',
    isFeatured: true,
    rating: 4.5,
    solds: 75,
    stock: 50,
    category: 'Poultry',
    isOrganic: false,
    weight: '2kg',
    origin: 'Nebraska, USA',
    reviews: [
      { user: 'Amy', comment: 'Very tender.', rating: 5 },
      { user: 'Ben', comment: 'Juicy and tasty.', rating: 4 }
    ]
  },
  {
    id: 15,
    name: 'Organic Wheat',
    image: 'https://images.unsplash.com/photo-1511690743698-d9d85f2fbf38?auto=format&fit=crop&w=400&q=80',
    description: 'Whole grain organic wheat.',
    price: 16.00,
    seller: 'WheatLand',
    isFeatured: true,
    rating: 4.7,
    solds: 100,
    stock: 110,
    category: 'Produce',
    isOrganic: true,
    weight: '2kg',
    origin: 'Kansas, USA',
    reviews: [
      { user: 'Cara', comment: 'Bakes well.', rating: 5 },
      { user: 'Dan', comment: 'Good quality.', rating: 4 }
    ]
  },
  {
    id: 16,
    name: 'Goat Cheese',
    image: 'https://images.unsplash.com/photo-1519864600265-abb23847ef2c?auto=format&fit=crop&w=400&q=80',
    description: 'Creamy goat cheese, perfect for salads and snacks.',
    price: 21.50,
    seller: 'DairyBest',
    isFeatured: true,
    rating: 4.8,
    solds: 60,
    stock: 40,
    category: 'Dairy',
    isOrganic: false,
    weight: '500g',
    origin: 'Wisconsin, USA',
    reviews: [
      { user: 'Ella', comment: 'Very creamy.', rating: 5 },
      { user: 'Finn', comment: 'Delicious.', rating: 5 }
    ]
  },
  {
    id: 17,
    name: 'Raw Almonds',
    image: 'https://images.unsplash.com/photo-1464983953574-0892a716854b?auto=format&fit=crop&w=400&q=80',
    description: 'Raw, unsalted almonds packed with nutrients.',
    price: 17.75,
    seller: 'NutriFarm',
    isFeatured: true,
    rating: 4.4,
    solds: 80,
    stock: 70,
    category: 'Produce',
    isOrganic: true,
    weight: '1kg',
    origin: 'California, USA',
    reviews: [
      { user: 'Gina', comment: 'Very crunchy.', rating: 4 },
      { user: 'Hank', comment: 'Healthy snack.', rating: 5 }
    ]
  },
  {
    id: 18,
    name: 'Fresh Strawberries',
    image: 'https://images.unsplash.com/photo-1465101178521-c1a9136a3b99?auto=format&fit=crop&w=400&q=80',
    description: 'Juicy, sweet strawberries picked fresh.',
    price: 13.99,
    seller: 'Berry Farm',
    isFeatured: true,
    rating: 4.9,
    solds: 140,
    stock: 50,
    category: 'Produce',
    isOrganic: true,
    weight: '500g',
    origin: 'Oregon, USA',
    reviews: [
      { user: 'Ivy', comment: 'Very sweet.', rating: 5 },
      { user: 'Jake', comment: 'Kids love them.', rating: 5 }
    ]
  },
  {
    id: 19,
    name: 'Organic Avocado',
    image: 'https://images.unsplash.com/photo-1502741338009-cac2772e18bc?auto=format&fit=crop&w=400&q=80',
    description: 'Creamy organic avocados, perfect for toast.',
    price: 15.25,
    seller: 'Green Valley Farms',
    isFeatured: true,
    rating: 4.6,
    solds: 90,
    stock: 60,
    category: 'Produce',
    isOrganic: true,
    weight: '1kg',
    origin: 'California, USA',
    reviews: [
      { user: 'Liam', comment: 'Very creamy.', rating: 5 },
      { user: 'Mia', comment: 'Perfect for guac.', rating: 5 }
    ]
  },
  {
    id: 20,
    name: 'Farm Yogurt',
    image: 'https://images.unsplash.com/photo-1519864600265-abb23847ef2c?auto=format&fit=crop&w=400&q=80',
    description: 'Thick, creamy yogurt made from fresh milk.',
    price: 12.00,
    seller: 'DairyBest',
    isFeatured: true,
    rating: 4.7,
    solds: 100,
    stock: 80,
    category: 'Dairy',
    isOrganic: true,
    weight: '1L',
    origin: 'Wisconsin, USA',
    reviews: [
      { user: 'Nina', comment: 'Very creamy.', rating: 5 },
      { user: 'Oscar', comment: 'Great taste.', rating: 5 }
    ]
  },
];
