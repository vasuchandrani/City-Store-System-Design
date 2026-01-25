// User Roles
export type UserRole = 'CUSTOMER' | 'SHOPKEEPER' | 'DELIVERER';

// User Types
export interface User {
  id: string;
  email: string;
  name: string;
  phone?: string;
  role: UserRole;
  avatar?: string;
  createdAt: string;
  isVerified: boolean;
}

export interface Customer extends User {
  role: 'CUSTOMER';
  selectedCityId?: string;
  addresses: String [];
  favoriteShops: string[];
}

export interface Shopkeeper extends User {
  role: 'SHOPKEEPER';
  shopId?: string;
  subscriptionStatus: 'active' | 'pending' | 'expired';
}

export interface DeliveryPartner extends User {
  role: 'DELIVERER';
  isAvailable: boolean;
  vehicleType: 'bike' | 'scooter' | 'car';
  currentLocation?: Location;
  totalDeliveries: number;
  rating: number;
}


// City
export interface City {
  id: string;
  cityName: string;
  state: string;
}

export type ShopCategory = 
  | 'grocery'
  | 'electronics'
  | 'clothing'
  | 'pharmacy'
  | 'restaurant'
  | 'bakery'
  | 'stationery'
  | 'hardware'
  | 'cosmetics'
  | 'general';

