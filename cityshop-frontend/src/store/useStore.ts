import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { User, City} from '@/types';

interface AuthState {
  user: User | null;
  token: string | null;
  isAuthenticated: boolean;
  setUser: (user: User | null) => void;
  setToken: (token: string | null) => void;
  logout: () => void;
}

interface CityState {
  selectedCity: City | null;
  cities: City[];
  setSelectedCity: (city: City | null) => void;
  setCities: (cities: City[]) => void;
}


// Auth Store
export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      user: null,
      token: null,
      isAuthenticated: false,
      setUser: (user) => set({ user, isAuthenticated: !!user }),
      setToken: (token) => {
        if (token) {
          localStorage.setItem('auth_token', token);
        } else {
          localStorage.removeItem('auth_token');
        }
        set({ token });
      },
      logout: () => {
        localStorage.removeItem('auth_token');
        set({ user: null, token: null, isAuthenticated: false });
      },
    }),
    {
      name: 'auth-storage',
      partialize: (state) => ({ user: state.user, token: state.token, isAuthenticated: state.isAuthenticated }),
    }
  )
);

// City Store
export const useCityStore = create<CityState>()(
  persist(
    (set) => ({
      selectedCity: null,
      cities: [],
      setSelectedCity: (city) => set({ selectedCity: city }),
      setCities: (cities) => set({ cities }),
    }),
    {
      name: 'city-storage',
      partialize: (state) => ({ selectedCity: state.selectedCity }),
    }
  )
);
