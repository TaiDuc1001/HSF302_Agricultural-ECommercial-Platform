import React, { createContext, useContext, useState } from 'react';

export type UserRole = 'admin' | 'buyer' | 'seller' | 'guest';
export interface User {
  username: string;
  role: UserRole;
  password: string;
}

interface AuthContextType {
  user: User | null;
  login: (username: string, password: string) => Promise<'success' | 'fail'>;
  logout: () => void;
  register: (username: string, password: string, role: UserRole) => Promise<'success' | 'fail'>;
}

const AuthContext = createContext<AuthContextType>({
  user: null,
  login: async () => 'fail',
  logout: () => {},
  register: async () => 'fail',
});

const initialAccounts: User[] = [
  { username: 'a', password: 'a', role: 'admin' },
  { username: 'b', password: 'b', role: 'buyer' },
  { username: 's', password: 's', role: 'seller' },
];

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);
  const [accounts, setAccounts] = useState<User[]>(initialAccounts);

  const login = async (username: string, password: string) => {
    const found = accounts.find(acc => acc.username === username && acc.password === password);
    if (found) {
      setUser({ username: found.username, role: found.role as UserRole, password: found.password });
      return 'success';
    }
    return 'fail';
  };

  const logout = () => setUser(null);

  const register = async (username: string, password: string, role: UserRole) => {
    if (accounts.find(acc => acc.username === username)) {
      return 'fail'; 
    }
    const newUser: User = { username, password, role };
    setAccounts(prev => [...prev, newUser]);
    setUser(newUser);
    return 'success';
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, register }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
