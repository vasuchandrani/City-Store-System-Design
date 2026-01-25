import { useState, useEffect } from 'react';
import { useNavigate, useSearchParams, Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { ShoppingBag, Store, Truck, Users, Eye, EyeOff, ArrowLeft, MapPin } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Textarea } from '@/components/ui/textarea';
import { useAuthStore, useCityStore } from '@/store/useStore';
import { UserRole, ShopCategory } from '@/types';
import { City } from '@/types';
import { toast } from 'sonner';
import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080', 
});

const roles = [
  { value: 'CUSTOMER', label: 'Customer', icon: Users, description: 'Shop from local stores' },
  { value: 'SHOPKEEPER', label: 'Shopkeeper', icon: Store, description: 'Sell your products online' },
  { value: 'DELIVERER', label: 'Delivery Partner', icon: Truck, description: 'Earn by delivering orders' },
];

const shopCategories = [
  { value: 'grocery', label: 'Grocery' },
  { value: 'electronics', label: 'Electronics' },
  { value: 'clothing', label: 'Clothing & Fashion' },
  { value: 'pharmacy', label: 'Pharmacy' },
  { value: 'restaurant', label: 'Restaurant' },
  { value: 'bakery', label: 'Bakery' },
  { value: 'stationery', label: 'Stationery' },
  { value: 'hardware', label: 'Hardware' },
  { value: 'cosmetics', label: 'Cosmetics' },
  { value: 'general', label: 'General Store' },
];

export default function AuthPage() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const { setUser, setToken, isAuthenticated } = useAuthStore();
  const { setSelectedCity } = useCityStore();
  const mockCities: City[] = [
    {id:'1',cityName:"Nadiad",state:"Gujarat"},
  ];
  const [mockCitiesData,setMockCitiesData] = useState(mockCities);

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const response = await API.get('/cityshop/city/getAll');
        console.log('Fetched cities:', response.data);
        setMockCitiesData(response.data);
      } catch (error) {
        console.error('Error fetching cities:', error);
      }
    };
    
    fetchCities();
  }, []);

  const [mode, setMode] = useState<'signin' | 'signup'>(
    searchParams.get('mode') === 'signup' ? 'signup' : 'signin'
  );
  const [selectedRole, setSelectedRole] = useState<UserRole>(
    (searchParams.get('role') as UserRole) || 'CUSTOMER'
  );

  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: '',
    address: '',
    city: '',
    shopName: '',
    shopDescription: '',
    shopAddress: '',
    shopCategory: '' as ShopCategory | '',
    shopCity: '',
    upiId: '',
    gstNo: '',
    vehicleDetail: '',
  });

  useEffect(() => {
    if (isAuthenticated) redirectToDashboard();
  }, [isAuthenticated]);

  const redirectToDashboard = () => {
    if (selectedRole === 'CUSTOMER') {
      navigate('/customer');
    } else if (selectedRole === 'SHOPKEEPER') {
      navigate('/shopkeeper');
    } else if (selectedRole === 'DELIVERER') {
      navigate('/delivery');
    }
  };


  const login = async () => {

    if(selectedRole === "CUSTOMER") {

      const res = await API.post('/cityshop/auth/customer/login', {
        email: formData.email,
        password: formData.password,
        role: 'CUSTOMER',
      });
      return res.data;
    }

    else if(selectedRole === "SHOPKEEPER") {
      const res = await API.post('/cityshop/auth/shopkeeper/login', {
        email: formData.email,
        password: formData.password,
        role: 'SHOPKEEPER',
      });
      return res.data;

    }
  
    else if(selectedRole === "DELIVERER") {
      const res = await API.post('/cityshop/auth/deliverer/login', {
        email: formData.email,
        password: formData.password,
        role: 'DELIVERER',
      });
      return res.data;
    }
};

  const registerCustomer = async () => {
    const res = await API.post('/cityshop/auth/customer/signup', {
      email: formData.email,
      fullName: formData.name,
      password: formData.password,
      phoneNumber: formData.phone,
      address: formData.address,
      cityName: formData.city,
    });
    return res.data;
  };

  const registerShopkeeper = async () => {
    const res = await API.post('/cityshop/auth/shopkeeper/signup', {
      email: formData.email,
      fullName: formData.name,
      password: formData.password,
      phoneNumber: formData.phone,
      upiId: formData.upiId,
      gstNo: formData.gstNo,
      shopName: formData.shopName,
      shopDescription: formData.shopDescription,
      shopAddress: formData.shopAddress,
      category: formData.shopCategory,
      cityName: formData.shopCity,
    });
    return res.data;
  };

  const registerDelivery = async () => {
    const res = await API.post('/cityshop/auth/deliverer/signup', {
      email: formData.email,
      fullName: formData.name,
      password: formData.password,
      phoneNumber: formData.phone,
      upiId: formData.upiId,
      vehicleDetail: formData.vehicleDetail,
      cityName: formData.city,
    });
    return res.data;
  };


  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);

    try {
      let response;

      if (mode === 'signin') {
        response = await login();
      } else {
        if (selectedRole === "CUSTOMER") response = await registerCustomer();
        if (selectedRole === "SHOPKEEPER") response = await registerShopkeeper();
        if (selectedRole === "DELIVERER") response = await registerDelivery();
      }
      setSelectedCity(formData.city ? mockCitiesData.find(city => city.cityName === formData.city) || null : null);

      setUser(response.user);
      setToken(response.token);

      toast.success(mode === 'signin' ? 'Welcome back!' : 'Account created successfully!');
      redirectToDashboard();
    } catch (err: any) {
      toast.error(err?.response?.data?.message || 'Authentication failed');
    } finally {
      setIsLoading(false);
    }
  };

  const handleInputChange = (e: any) =>
    setFormData((p) => ({ ...p, [e.target.name]: e.target.value }));

  const handleSelectChange = (name: string, value: string) =>
    setFormData((p) => ({ ...p, [name]: value }));


  return (
    <div className="min-h-screen bg-background flex">

      <div className="hidden lg:flex lg:w-1/2 bg-gradient-brand relative overflow-hidden">
        <div className="absolute inset-0 bg-black/10" />
        <div className="relative z-10 flex flex-col justify-between p-12 text-white">
          <Link to="/city-shop" className="flex items-center gap-2">
            <div className="w-12 h-12 rounded-xl bg-white/20 backdrop-blur flex items-center justify-center">
              <ShoppingBag className="w-7 h-7" />
            </div>
            <span className="text-2xl font-bold">CityShop</span>
          </Link>

          <div className="space-y-6">
            <h1 className="text-4xl font-bold leading-tight">
              Welcome to the future of local commerce
            </h1>
            <p className="text-lg text-white/80">
              Connect with trusted local shops, enjoy quick delivery, 
              and support your neighborhood businesses.
            </p>
          </div>

          <div className="flex gap-8">
            <div>
              <div className="text-3xl font-bold">50K+</div>
              <div className="text-sm text-white/70">Happy Customers</div>
            </div>
            <div>
              <div className="text-3xl font-bold">1000+</div>
              <div className="text-sm text-white/70">Local Shops</div>
            </div>
            <div>
              <div className="text-3xl font-bold">15</div>
              <div className="text-sm text-white/70">Cities</div>
            </div>
          </div>
        </div>


        <div className="absolute -bottom-20 -right-20 w-80 h-80 bg-white/10 rounded-full blur-3xl" />
        <div className="absolute top-1/3 -left-20 w-60 h-60 bg-white/5 rounded-full blur-3xl" />
      </div>

      <div className="flex-1 flex items-center justify-center p-8 overflow-y-auto">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="w-full max-w-md"
        >
          <Link 
            to="/city-shop" 
            className="inline-flex items-center gap-2 text-muted-foreground hover:text-foreground mb-8 transition-colors"
          >
            <ArrowLeft className="w-4 h-4" />
            Back to Home
          </Link>

          <Card variant="elevated">
            <CardHeader className="space-y-1">
              <CardTitle className="text-2xl">
                {mode === 'signin' ? 'Welcome back' : 'Create an account'}
              </CardTitle>
              <CardDescription>
                {mode === 'signin' 
                  ? 'Sign in to your account to continue' 
                  : 'Get started with CityShop today'}
              </CardDescription>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmit} className="space-y-4">

                <div className="space-y-3">
                  <Label>I am a</Label>
                  <div className="grid grid-cols-3 gap-3">
                    {roles.map((role) => {
                      const Icon = role.icon;
                      const isSelected = selectedRole === role.value;
                      return (
                        <button
                          key={role.value}
                          type="button"
                          onClick={() => setSelectedRole(role.value as UserRole)}
                          className={`p-3 rounded-xl border-2 transition-all text-center ${
                            isSelected
                              ? 'border-primary bg-brand-light'
                              : 'border-border hover:border-primary/30'
                          }`}
                        >
                          <Icon className={`w-5 h-5 mx-auto mb-1 ${isSelected ? 'text-brand' : 'text-muted-foreground'}`} />
                          <div className={`text-xs font-medium ${isSelected ? 'text-brand-dark' : 'text-foreground'}`}>
                            {role.label}
                          </div>
                        </button>
                      );
                    })}
                  </div>
                </div>

                {mode === 'signup' && (
                  <div className="space-y-2">
                    <Label htmlFor="name">Full Name</Label>
                    <Input
                      id="name"
                      name="name"
                      placeholder="Enter your full name"
                      value={formData.name}
                      onChange={handleInputChange}
                      required
                    />
                  </div>
                )}

                <div className="space-y-2">
                  <Label htmlFor="email">Email</Label>
                  <Input
                    id="email"
                    name="email"
                    type="email"
                    placeholder="Enter your email"
                    value={formData.email}
                    onChange={handleInputChange}
                    required
                  />
                </div>

                {mode === 'signup' && (
                  <div className="space-y-2">
                    <Label htmlFor="phone">Phone Number</Label>
                    <Input
                      id="phone"
                      name="phone"
                      type="tel"
                      placeholder="Enter your phone number"
                      value={formData.phone}
                      onChange={handleInputChange}
                    />
                  </div>
                )}

                <div className="space-y-2">
                  <Label htmlFor="password">Password</Label>
                  <div className="relative">
                    <Input
                      id="password"
                      name="password"
                      type={showPassword ? 'text' : 'password'}
                      placeholder="Enter your password"
                      value={formData.password}
                      onChange={handleInputChange}
                      required
                    />
                    <button
                      type="button"
                      onClick={() => setShowPassword(!showPassword)}
                      className="absolute right-3 top-1/2 -translate-y-1/2 text-muted-foreground hover:text-foreground"
                    >
                      {showPassword ? <EyeOff className="w-4 h-4" /> : <Eye className="w-4 h-4" />}
                    </button>
                  </div>
                </div>

                {mode === 'signup' && (
                  <div className="space-y-2">
                    <Label htmlFor="confirmPassword">Confirm Password</Label>
                    <Input
                      id="confirmPassword"
                      name="confirmPassword"
                      type="password"
                      placeholder="Confirm your password"
                      value={formData.confirmPassword}
                      onChange={handleInputChange}
                      required
                    />
                  </div>
                )}

                {mode === 'signup' && selectedRole === 'CUSTOMER' && (
                  <div className="space-y-4 pt-4 border-t">
                    <h3 className="font-medium flex items-center gap-2">
                      <MapPin className="w-4 h-4 text-primary" />
                      Delivery Address
                    </h3>
                    <div className="space-y-2">
                      <Label htmlFor="city">City *</Label>
                      <Select
                        value={formData.city}
                        onValueChange={(value) => handleSelectChange('city', value)}
                      >
                        <SelectTrigger>
                          <SelectValue placeholder="Select your city" />
                        </SelectTrigger>
                        <SelectContent>
                          {mockCitiesData.map((city) => (
                            <SelectItem key={city.id} value={city.cityName}>
                              {city.cityName}, {city.state}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="address">Street Address *</Label>
                      <Textarea
                        id="address"
                        name="address"
                        placeholder="Enter your complete address"
                        value={formData.address}
                        onChange={handleInputChange}
                        className="min-h-[80px]"
                      />
                    </div>
                  </div>
                )}

                {mode === 'signup' && selectedRole === 'SHOPKEEPER' && (
                  <div className="space-y-4 pt-4 border-t">
                    <h3 className="font-medium flex items-center gap-2">
                      <Store className="w-4 h-4 text-primary" />
                      Shop Details
                    </h3>
                    <div className="space-y-2">
                      <Label htmlFor="shopName">Shop Name *</Label>
                      <Input
                        id="shopName"
                        name="shopName"
                        placeholder="Enter your shop name"
                        value={formData.shopName}
                        onChange={handleInputChange}
                      />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="shopDescription">Shop Description</Label>
                      <Textarea
                        id="shopDescription"
                        name="shopDescription"
                        placeholder="Describe your shop and products"
                        value={formData.shopDescription}
                        onChange={handleInputChange}
                        className="min-h-[80px]"
                      />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="shopCategory">Category *</Label>
                      <Select
                        value={formData.shopCategory}
                        onValueChange={(value) => handleSelectChange('shopCategory', value)}
                      >
                        <SelectTrigger>
                          <SelectValue placeholder="Select shop category" />
                        </SelectTrigger>
                        <SelectContent>
                          {shopCategories.map((cat) => (
                            <SelectItem key={cat.value} value={cat.value}>
                              {cat.label}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="shopCity">City *</Label>
                      <Select
                        value={formData.shopCity}
                        onValueChange={(value) => handleSelectChange('shopCity', value)}
                      >
                        <SelectTrigger>
                          <SelectValue placeholder="Select city" />
                        </SelectTrigger>
                        <SelectContent>
                          {mockCitiesData.map((city) => (
                            <SelectItem key={city.id} value={city.cityName}>
                              {city.cityName}, {city.state}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="shopAddress">Shop Address *</Label>
                      <Textarea
                        id="shopAddress"
                        name="shopAddress"
                        placeholder="Enter complete shop address"
                        value={formData.shopAddress}
                        onChange={handleInputChange}
                        className="min-h-[80px]"
                      />
                    </div>
                  </div>
                )}

                {mode === 'signup' && selectedRole === 'DELIVERER' && (
                  <div className="space-y-4 pt-4 border-t">
                    <h3 className="font-medium flex items-center gap-2">
                      <Truck className="w-4 h-4 text-primary" />
                      Delivery Partner Details
                    </h3>
                    <div className="space-y-2">
                      <Label htmlFor="city">City *</Label>
                      <Select
                        value={formData.city}
                        onValueChange={(value) => handleSelectChange('city', value)}
                      >
                        <SelectTrigger>
                          <SelectValue placeholder="Select your city" />
                        </SelectTrigger>
                        <SelectContent>
                          {mockCitiesData.map((city) => (
                            <SelectItem key={city.id} value={city.cityName}>
                              {city.cityName}, {city.state}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="vehicleDetail">Vehicle Details *</Label>
                      <Input
                        id="vehicleDetail"
                        name="vehicleDetail"
                        placeholder="Enter your vehicle details"
                        value={formData.vehicleDetail}
                        onChange={handleInputChange}
                      />
                    </div>
                  </div>
                )}

                <Button 
                  type="submit" 
                  variant="hero" 
                  size="lg" 
                  className="w-full"
                  disabled={isLoading}
                >
                  {isLoading 
                    ? 'Please wait...' 
                    : mode === 'signin' ? 'Sign In' : 'Create Account'}
                </Button>

                <div className="text-center text-sm">
                  {mode === 'signin' ? (
                    <p className="text-muted-foreground">
                      Don't have an account?{' '}
                      <button
                        type="button"
                        onClick={() => setMode('signup')}
                        className="text-primary font-medium hover:underline"
                      >
                        Sign up
                      </button>
                    </p>
                  ) : (
                    <p className="text-muted-foreground">
                      Already have an account?{' '}
                      <button
                        type="button"
                        onClick={() => setMode('signin')}
                        className="text-primary font-medium hover:underline"
                      >
                        Sign in
                      </button>
                    </p>
                  )}
                </div>
              </form>
            </CardContent>
          </Card>
        </motion.div>
      </div>
    </div>
  );
}
