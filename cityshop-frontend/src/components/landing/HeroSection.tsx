import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';
import { ArrowRight, Store, Truck, ShoppingCart, Star, MapPin, Clock } from 'lucide-react';
import { Button } from '@/components/ui/button';

export function HeroSection() {
  return (
    <section className="relative min-h-screen flex items-center section-gradient overflow-hidden pt-20">
      {/* Background Elements */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute top-1/4 -left-20 w-72 h-72 bg-primary/10 rounded-full blur-3xl" />
        <div className="absolute bottom-1/4 -right-20 w-96 h-96 bg-accent-teal/10 rounded-full blur-3xl" />
      </div>

      <div className="container mx-auto px-4 py-12 md:py-20 relative z-10">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          {/* Left Content */}
          <motion.div
            initial={{ opacity: 0, x: -50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.6 }}
            className="space-y-8"
          >
            <div className="inline-flex items-center gap-2 bg-brand-light text-brand-dark px-4 py-2 rounded-full text-sm font-medium">
              <Store className="w-4 h-4" />
              <span>Supporting 1000+ Local Shops</span>
            </div>

            <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold leading-tight">
              Your Local Shops,{' '}
              <span className="text-gradient-brand">Delivered Fast</span>
            </h1>

            <p className="text-lg md:text-xl text-muted-foreground max-w-xl">
              CityShop bridges traditional local shops with modern e-commerce convenience. 
              Shop from trusted neighborhood stores with quick, reliable delivery.
            </p>

            <div className="flex flex-col sm:flex-row gap-4">
              <Link to="/auth?mode=signup">
                <Button variant="hero" size="xl" className="w-full sm:w-auto">
                  Start Shopping
                  <ArrowRight className="w-5 h-5" />
                </Button>
              </Link>
              <Link to="/auth?mode=signup&role=shopkeeper">
                <Button variant="heroOutline" size="xl" className="w-full sm:w-auto">
                  Register Your Shop
                </Button>
              </Link>
            </div>

            {/* Stats */}
            <div className="flex flex-wrap gap-8 pt-4">
              <div>
                <div className="text-3xl font-bold text-foreground">50K+</div>
                <div className="text-sm text-muted-foreground">Happy Customers</div>
              </div>
              <div>
                <div className="text-3xl font-bold text-foreground">1000+</div>
                <div className="text-sm text-muted-foreground">Local Shops</div>
              </div>
              <div>
                <div className="text-3xl font-bold text-foreground">15</div>
                <div className="text-sm text-muted-foreground">Cities</div>
              </div>
            </div>
          </motion.div>

          {/* Right Content - Hero Illustration */}
          <motion.div
            initial={{ opacity: 0, x: 50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.6, delay: 0.2 }}
            className="relative"
          >
            <div className="relative">
              {/* Main Card */}
              <div className="bg-card rounded-3xl shadow-xl p-6 md:p-8">
                <img
                  src="https://images.unsplash.com/photo-1604719312566-8912e9227c6a?w=600&h=400&fit=crop"
                  alt="Local shop"
                  className="w-full h-64 md:h-80 object-cover rounded-2xl"
                />
                <div className="mt-6 space-y-4">
                  <div className="flex items-center justify-between">
                    <div>
                      <h3 className="font-bold text-lg">Fresh Mart Grocery</h3>
                      <p className="text-sm text-muted-foreground">123 Market Street</p>
                    </div>
                    <div className="flex items-center gap-1 text-warning">
                      <Star className="w-4 h-4 fill-current" />
                      <span className="font-medium">4.8</span>
                    </div>
                  </div>
                  <div className="flex items-center gap-4 text-sm text-muted-foreground">
                    <span className="flex items-center gap-1">
                      <MapPin className="w-4 h-4" />
                      0.8 km
                    </span>
                    <span className="flex items-center gap-1">
                      <Clock className="w-4 h-4" />
                      15-20 min
                    </span>
                  </div>
                </div>
              </div>

              {/* Floating Cards */}
              <motion.div
                animate={{ y: [0, -10, 0] }}
                transition={{ duration: 3, repeat: Infinity, ease: "easeInOut" }}
                className="absolute -top-4 -right-4 bg-card rounded-2xl shadow-lg p-4 flex items-center gap-3"
              >
                <div className="w-12 h-12 rounded-xl bg-success-light flex items-center justify-center">
                  <Truck className="w-6 h-6 text-success" />
                </div>
                <div>
                  <div className="font-semibold text-sm">Fast Delivery</div>
                  <div className="text-xs text-muted-foreground">Under 30 mins</div>
                </div>
              </motion.div>

              <motion.div
                animate={{ y: [0, 10, 0] }}
                transition={{ duration: 3, repeat: Infinity, ease: "easeInOut", delay: 1 }}
                className="absolute -bottom-4 -left-4 bg-card rounded-2xl shadow-lg p-4 flex items-center gap-3"
              >
                <div className="w-12 h-12 rounded-xl bg-brand-light flex items-center justify-center">
                  <ShoppingCart className="w-6 h-6 text-brand" />
                </div>
                <div>
                  <div className="font-semibold text-sm">Multi-Shop Cart</div>
                  <div className="text-xs text-muted-foreground">One checkout</div>
                </div>
              </motion.div>
            </div>
          </motion.div>
        </div>
      </div>
    </section>
  );
}
